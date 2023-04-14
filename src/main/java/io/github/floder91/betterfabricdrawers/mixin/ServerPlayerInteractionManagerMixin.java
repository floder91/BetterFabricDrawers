package io.github.floder91.betterfabricdrawers.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.floder91.betterfabricdrawers.config.CommonConfig;
import io.github.floder91.betterfabricdrawers.misc.CreativeExtractionBehaviour;
import io.github.floder91.betterfabricdrawers.block.base.CreativeBreakBlocker;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Shadow protected ServerWorld world;
    
    @ModifyExpressionValue(method = "processBlockBreakingAction", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerInteractionManager;isCreative()Z"))
    private boolean extended_drawers$stopCreativeBreaking(boolean original, BlockPos pos, PlayerActionC2SPacket.Action action, Direction direction, int worldHeight) {
        if (CommonConfig.HANDLE.get().creativeExtractionMode() == CreativeExtractionBehaviour.NORMAL) return original;
        if (world == null) return original;
        if (world.getBlockState(pos).getBlock() instanceof CreativeBreakBlocker blocker) {
            if (!CommonConfig.HANDLE.get().creativeExtractionMode().isFrontOnly() || blocker.shouldBlock(world, pos, direction)) {
                return false;
            }
        }
        return original;
    }
}
