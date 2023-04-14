package io.github.floder91.betterfabricdrawers.client;

import io.github.floder91.betterfabricdrawers.client.renderer.DrawerBlockEntityRenderer;
import io.github.floder91.betterfabricdrawers.client.renderer.ShadowDrawerBlockEntityRenderer;
import io.github.floder91.betterfabricdrawers.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class BetterFabricDrawersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlocks.DRAWER_BLOCK_ENTITY, DrawerBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlocks.SHADOW_DRAWER_BLOCK_ENTITY, ShadowDrawerBlockEntityRenderer::new);
    }
}
