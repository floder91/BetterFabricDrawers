package io.github.floder91.betterfabricdrawers.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.floder91.betterfabricdrawers.config.CommonConfig;
import io.github.floder91.betterfabricdrawers.block.base.CreativeBreakBlocker;
import io.github.floder91.betterfabricdrawers.misc.CreativeExtractionBehaviour;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Shadow private float currentBreakingProgress;
    
    @ModifyExpressionValue(method = {"attackBlock", "updateBlockBreakingProgress"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameMode;isCreative()Z"))
    private boolean extended_drawers$stopCreativeBreaking(boolean original, BlockPos pos, Direction direction) {
        if (CommonConfig.HANDLE.get().creativeExtractionMode() == CreativeExtractionBehaviour.NORMAL) return original;
        var world = MinecraftClient.getInstance().world;
        if (world == null) return original;
        if (world.getBlockState(pos).getBlock() instanceof CreativeBreakBlocker blocker) {
            if (!CommonConfig.HANDLE.get().creativeExtractionMode().isFrontOnly() || blocker.shouldBlock(world, pos, direction)) {
                return false;
            }
        }
        return original;
    }
    
    
    
    @Inject(method = "updateBlockBreakingProgress",
            at = @At(value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;currentBreakingProgress:F",
                    shift = At.Shift.AFTER,
                    ordinal = 0),
            slice = @Slice(from = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;calcBlockBreakingDelta(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F")))
    private void extended_drawers$stopCreativeBreaking(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        var world = MinecraftClient.getInstance().world;
        if (world == null) return;
        if (world.getBlockState(pos).getBlock() instanceof CreativeBreakBlocker blocker && !CommonConfig.HANDLE.get().creativeExtractionMode().isAllowMine()) {
            if (!CommonConfig.HANDLE.get().creativeExtractionMode().isFrontOnly() || blocker.shouldBlock(world, pos, direction)) {
                currentBreakingProgress = 0;
            }
        }
    }
}
