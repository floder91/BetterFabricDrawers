package io.github.floder91.betterfabricdrawers.network.node;

import com.kneelawk.graphlib.graph.BlockNodeHolder;
import com.kneelawk.graphlib.graph.struct.Node;
import io.github.floder91.betterfabricdrawers.BetterFabricDrawers;
import io.github.floder91.betterfabricdrawers.block.entity.ShadowDrawerBlockEntity;
import io.github.floder91.betterfabricdrawers.network.UpdateHandler;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShadowDrawerBlockNode implements DrawerNetworkBlockNode {
    public static final Identifier ID = BetterFabricDrawers.id("shadow_drawers");
    public static final ShadowDrawerBlockNode INSTANCE = new ShadowDrawerBlockNode();

    private ShadowDrawerBlockNode() {
    }
    
    @Override
    public @NotNull Identifier getTypeId() {
        return ID;
    }
    
    @Override
    public @Nullable NbtElement toTag() {
        return null;
    }
    
    @Override
    public void update(ServerWorld world, Node<BlockNodeHolder> node, UpdateHandler.ChangeType type) {
        var pos = node.data().getPos();

        if (world.getBlockEntity(pos) instanceof ShadowDrawerBlockEntity drawer)
            drawer.recalculateContents();
    }
}
