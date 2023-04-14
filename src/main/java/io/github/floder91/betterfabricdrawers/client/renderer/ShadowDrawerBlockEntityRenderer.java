package io.github.floder91.betterfabricdrawers.client.renderer;

import io.github.floder91.betterfabricdrawers.block.ShadowDrawerBlock;
import io.github.floder91.betterfabricdrawers.config.ClientConfig;
import io.github.floder91.betterfabricdrawers.block.entity.ShadowDrawerBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import java.util.List;
import java.util.Objects;

public class ShadowDrawerBlockEntityRenderer extends AbstractDrawerBlockEntityRenderer<ShadowDrawerBlockEntity> {
    public ShadowDrawerBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(context);
    }
    
    @Override
    public int getRenderDistance() {
        var config = ClientConfig.HANDLE.get();
        return Math.max(config.textRenderDistance(), config.itemRenderDistance());
    }
    
    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void render(ShadowDrawerBlockEntity drawer, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        var dir = drawer.getCachedState().get(ShadowDrawerBlock.FACING);
        var pos = dir.getUnitVector();
        if (!shouldRender(drawer, dir)) return;
        
        matrices.push();
        matrices.translate(pos.x / 2 + 0.5, pos.y / 2 + 0.5, pos.z / 2 + 0.5);
        matrices.multiply(dir.getRotationQuaternion());
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));
        matrices.translate(0, 0, 0.01);
    
        light = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(drawer.getWorld()), drawer.getPos().offset(dir));

        List<Sprite> icons = drawer.isHidden() ? List.of(MinecraftClient.getInstance()
                .getSpriteAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE)
                .apply(new Identifier("minecraft", "item/black_dye"))) : List.of();
        renderSlot(drawer.isHidden() ? ItemVariant.blank() : drawer.item, drawer.item.isBlank() || ClientConfig.HANDLE.get().displayEmptyCount() ? null : drawer.countCache, icons, matrices, vertexConsumers, light, overlay, (int) drawer.getPos().asLong(), drawer.getPos());
        matrices.pop();
    }
}
