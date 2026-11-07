package com.craigtec.leakyshulkerbox.block.custom.entity.renderer;

import com.craigtec.leakyshulkerbox.LeakyShulkerBoxUtil;
import com.craigtec.leakyshulkerbox.block.custom.LeakyShulkerBoxBlock;
import com.craigtec.leakyshulkerbox.block.custom.entity.custom.LeakyShulkerBoxBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.function.Consumer;

public class LeakyShulkerBoxBlockEntityRenderer implements BlockEntityRenderer<LeakyShulkerBoxBlockEntity, LeakyShulkerBoxBlockEntityRenderState> {
    private static final Map<Direction, Transformation> TRANSFORMATIONS = Util.makeEnumMap(Direction.class, LeakyShulkerBoxBlockEntityRenderer::createModelTransform);
    private final LeakyShulkerBoxBlockEntityRenderer.LeakyShulkerBoxModel model;

    public LeakyShulkerBoxBlockEntityRenderer(final BlockEntityRendererProvider.Context context) {
        this(context.entityModelSet(), context.sprites());
    }

    public LeakyShulkerBoxBlockEntityRenderer(final SpecialModelRenderer.BakingContext context) {
        this(context.entityModelSet(), context.sprites());
    }

    public LeakyShulkerBoxBlockEntityRenderer(final EntityModelSet context, final SpriteGetter sprites) {
        this.model = new LeakyShulkerBoxBlockEntityRenderer.LeakyShulkerBoxModel(context.bakeLayer(ModelLayers.SHULKER_BOX));
    }

    public @NonNull LeakyShulkerBoxBlockEntityRenderState createRenderState() {
        return new LeakyShulkerBoxBlockEntityRenderState();
    }

    public void extractRenderState(
            final @NonNull LeakyShulkerBoxBlockEntity blockEntity,
            final @NonNull LeakyShulkerBoxBlockEntityRenderState state,
            final float partialTicks,
            final @NonNull Vec3 cameraPosition,
            final ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
    ) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.direction = blockEntity.getBlockState().getValueOrElse(LeakyShulkerBoxBlock.FACING, Direction.UP);
        state.color = blockEntity.getColor();
        state.progress = blockEntity.getProgress(partialTicks);
    }

    public void submit(final LeakyShulkerBoxBlockEntityRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        DyeColor color = state.color;
        this.submit(poseStack, submitNodeCollector, state.lightCoords, state.direction, state.progress, state.breakProgress, LeakyShulkerBoxUtil.getLeakyShulkerBoxTextureLocation(color));
    }

    private void submit(
            final PoseStack poseStack,
            final SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final Direction direction,
            final float progress,
            final ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress,
            final Identifier texture
    ) {
        poseStack.pushPose();
        poseStack.mulPose(modelTransform(direction));
        this.submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, progress, breakProgress, texture, 0);
        poseStack.popPose();
    }

    public void submit(
            final PoseStack poseStack,
            final SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final int overlayCoords,
            final float progress,
            final ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress,
            final Identifier texture,
            final int outlineColor
    ) {
        this.model.setupAnim(progress);
        submitNodeCollector.submitModel(this.model, progress, poseStack, texture, lightCoords, overlayCoords, outlineColor, breakProgress);
    }

    private static Transformation createModelTransform(final Direction direction) {
        return new Transformation(
                new Matrix4f()
                        .translation(0.5F, 0.5F, 0.5F)
                        .scale(0.9995F, 0.9995F, 0.9995F)
                        .rotate(direction.getRotation())
                        .scale(1.0F, -1.0F, -1.0F)
                        .translate(0.0F, -1.0F, 0.0F)
        );
    }

    public static Transformation modelTransform(final Direction direction) {
        return TRANSFORMATIONS.get(direction);
    }

    public void getExtents(final float progress, final Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.model.setupAnim(progress);
        this.model.root().getExtentsForGui(poseStack, output);
    }

    @Environment(EnvType.CLIENT)
    private static class LeakyShulkerBoxModel extends Model<Float> {
        private final ModelPart lid;

        public LeakyShulkerBoxModel(final ModelPart root) {
            super(root, RenderTypes::entityCutout);
            this.lid = root.getChild("lid");
        }

        public void setupAnim(final @NonNull Float progress) {
            super.setupAnim(progress);
            this.lid.setPos(0.0F, 24.0F - progress * 0.5F * 16.0F, 0.0F);
            this.lid.yRot = 270.0F * progress * (float) (Math.PI / 180.0);
        }
    }
}
