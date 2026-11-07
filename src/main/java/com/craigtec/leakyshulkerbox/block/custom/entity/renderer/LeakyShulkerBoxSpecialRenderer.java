package com.craigtec.leakyshulkerbox.block.custom.entity.renderer;

import com.craigtec.leakyshulkerbox.LeakyShulkerBoxUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class LeakyShulkerBoxSpecialRenderer implements NoDataSpecialModelRenderer {
    private final LeakyShulkerBoxBlockEntityRenderer shulkerBoxRenderer;
    private final float openness;
    private final Identifier texture;

    public LeakyShulkerBoxSpecialRenderer(final LeakyShulkerBoxBlockEntityRenderer shulkerBoxRenderer, final float openness, final Identifier texture) {
        this.shulkerBoxRenderer = shulkerBoxRenderer;
        this.openness = openness;
        this.texture = texture;
    }

    @Override
    public void submit(
            final @NonNull PoseStack poseStack,
            final @NonNull SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final int overlayCoords,
            final boolean hasFoil,
            final int outlineColor
    ) {
        this.shulkerBoxRenderer.submit(poseStack, submitNodeCollector, lightCoords, overlayCoords, this.openness, null, this.texture, outlineColor);
    }

    @Override
    public void getExtents(final @NonNull Consumer<Vector3fc> output) {
        this.shulkerBoxRenderer.getExtents(this.openness, output);
    }

    @Environment(EnvType.CLIENT)
    public record Unbaked(Identifier texture, float openness) implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<LeakyShulkerBoxSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                                Identifier.CODEC.fieldOf("texture").forGetter(LeakyShulkerBoxSpecialRenderer.Unbaked::texture),
                                Codec.FLOAT.optionalFieldOf("openness", 0.0F).forGetter(LeakyShulkerBoxSpecialRenderer.Unbaked::openness)
                        )
                        .apply(i, LeakyShulkerBoxSpecialRenderer.Unbaked::new)
        );

        public Unbaked(final @Nullable DyeColor color) {
            this(LeakyShulkerBoxUtil.getLeakyShulkerBoxTextureLocation(color), 0.0F);
        }

        @Override
        public @NonNull MapCodec<LeakyShulkerBoxSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public LeakyShulkerBoxSpecialRenderer bake(final SpecialModelRenderer.@NonNull BakingContext context) {
            return new LeakyShulkerBoxSpecialRenderer(new LeakyShulkerBoxBlockEntityRenderer(context), this.openness, this.texture);
        }
    }
}