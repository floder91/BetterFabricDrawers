package io.github.floder91.betterfabricdrawers.client.renderer;

import io.github.floder91.betterfabricdrawers.BetterFabricDrawers;
import io.github.floder91.betterfabricdrawers.block.DrawerBlock;
import io.github.floder91.betterfabricdrawers.config.ClientConfig;
import io.github.floder91.betterfabricdrawers.drawer.DrawerSlot;
import io.github.floder91.betterfabricdrawers.block.entity.DrawerBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("UnstableApiUsage")
public class DrawerBlockEntityRenderer extends AbstractDrawerBlockEntityRenderer<DrawerBlockEntity> {
    public DrawerBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(context);
    }
    
    @Override
    public int getRenderDistance() {
        var config = ClientConfig.HANDLE.get();
        return Math.max(config.iconRenderDistance(), Math.max(config.textRenderDistance(), config.itemRenderDistance()));
    }
    
    @Override
    public void render(DrawerBlockEntity drawer, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        var dir = drawer.getCachedState().get(DrawerBlock.FACING);
        var pos = dir.getUnitVector();
        var world = drawer.getWorld();
        
        if (!shouldRender(drawer, dir)) return;
        
        matrices.push();
        matrices.translate(pos.x / 2 + 0.5, pos.y / 2 + 0.5, pos.z / 2 + 0.5);
        matrices.multiply(dir.getRotationQuaternion());
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));
        matrices.translate(0, 0, 0.01);
    
        light = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(drawer.getWorld()), drawer.getPos().offset(dir));
        var slots = ((DrawerBlock)drawer.getCachedState().getBlock()).slots;
        var blockPos = drawer.getPos();
        
        switch (slots) {
            case 1 -> renderSlot(drawer.storages[0], light, matrices, vertexConsumers, (int) drawer.getPos().asLong(), overlay, blockPos, world);
            case 2 -> {
                matrices.scale(0.5f, 0.5f, 1);
                matrices.translate(-0.5, 0, 0);
                renderSlot(drawer.storages[0], light, matrices, vertexConsumers, (int) drawer.getPos().asLong(), overlay, blockPos, world);
                matrices.translate(1, 0, 0);
                renderSlot(drawer.storages[1], light, matrices, vertexConsumers, (int) drawer.getPos().asLong(), overlay, blockPos, world);
            }
            case 4 -> {
                matrices.scale(0.5f, 0.5f, 1);
                matrices.translate(-0.5, 0.5, 0);
                renderSlot(drawer.storages[0], light, matrices, vertexConsumers, (int) drawer.getPos().asLong(), overlay, blockPos, world);
                matrices.translate(1, 0, 0);
                renderSlot(drawer.storages[1], light, matrices, vertexConsumers, (int) drawer.getPos().asLong(), overlay, blockPos, world);
                matrices.translate(-1, -1, 0);
                renderSlot(drawer.storages[2], light, matrices, vertexConsumers, (int) drawer.getPos().asLong(), overlay, blockPos, world);
                matrices.translate(1, 0, 0);
                renderSlot(drawer.storages[3], light, matrices, vertexConsumers, (int) drawer.getPos().asLong(), overlay, blockPos, world);
            }
            default -> BetterFabricDrawers.LOGGER.error("Unexpected drawer slot count, skipping rendering. Are you an addon dev adding more configurations? If so please mixin into DrawerBlockEntityRenderer and add your layout.");
        }
        
        matrices.pop();
    }
    
    private void renderSlot(DrawerSlot storage, int light, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int seed, int overlay, BlockPos pos, World world) {
        var icons = new ArrayList<Sprite>();
        var blockAtlas = MinecraftClient.getInstance().getSpriteAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
        
        if (storage.isLocked()) icons.add(blockAtlas.apply(BetterFabricDrawers.id("item/lock")));
        if (storage.isVoiding()) icons.add(blockAtlas.apply(new Identifier("minecraft", "item/lava_bucket")));
        if (storage.isHidden()) icons.add(blockAtlas.apply(new Identifier("minecraft", "item/black_dye")));
        if (storage.getUpgrade() != null) icons.add(blockAtlas.apply(storage.getUpgrade().sprite));
        
        renderSlot(storage.isHidden() ? ItemVariant.blank() : storage.getItem(), ((storage.getAmount() == 0) || ClientConfig.HANDLE.get().displayEmptyCount()) ? null : storage.getAmount(), icons, matrices, vertexConsumers, light, overlay, seed, pos, world);
    }
}
