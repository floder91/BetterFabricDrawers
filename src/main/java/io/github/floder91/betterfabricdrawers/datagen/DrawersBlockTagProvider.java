package io.github.floder91.betterfabricdrawers.datagen;

import io.github.floder91.betterfabricdrawers.registry.ModBlocks;
import io.github.floder91.betterfabricdrawers.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

class DrawersBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public DrawersBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.BlockTags.DRAWERS).add(ModBlocks.SHADOW_DRAWER, ModBlocks.SINGLE_DRAWER, ModBlocks.DOUBLE_DRAWER, ModBlocks.QUAD_DRAWER);
        getOrCreateTagBuilder(ModTags.BlockTags.NETWORK_COMPONENTS).addTag(ModTags.BlockTags.DRAWERS).add(ModBlocks.ACCESS_POINT, ModBlocks.CONNECTOR);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.SINGLE_DRAWER, ModBlocks.DOUBLE_DRAWER, ModBlocks.QUAD_DRAWER, ModBlocks.CONNECTOR);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(ModBlocks.ACCESS_POINT, ModBlocks.SHADOW_DRAWER);
    }
}
