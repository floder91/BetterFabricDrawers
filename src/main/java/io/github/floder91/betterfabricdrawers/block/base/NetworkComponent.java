package io.github.floder91.betterfabricdrawers.block.base;

import com.kneelawk.graphlib.graph.BlockNode;

import java.util.Collection;

public interface NetworkComponent {
    Collection<BlockNode> createNodes();
}
