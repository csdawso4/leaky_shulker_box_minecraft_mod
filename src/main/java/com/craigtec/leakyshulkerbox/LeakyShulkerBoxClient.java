package com.craigtec.leakyshulkerbox;

import com.craigtec.leakyshulkerbox.block.custom.entity.ModBlockEntities;
import com.craigtec.leakyshulkerbox.block.custom.entity.renderer.LeakyShulkerBoxBlockEntityRenderer;
import com.craigtec.leakyshulkerbox.block.custom.entity.renderer.LeakyShulkerBoxSpecialRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.Identifier;

public class LeakyShulkerBoxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.LEAKY_SHULKER_BOX, LeakyShulkerBoxBlockEntityRenderer::new);
        SpecialModelRenderers.ID_MAPPER.put(Identifier.withDefaultNamespace("leaky_shulker_boxes"), LeakyShulkerBoxSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
