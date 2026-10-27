package com.amazon.layout.music.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BlockContainer extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.BlockContainer");
    private List<Block> blocks;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Block, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Block block) {
        if (block == null) {
            return -1;
        }
        if (block == this) {
            return 0;
        }
        if (!(block instanceof BlockContainer)) {
            return 1;
        }
        List<Block> blocks = getBlocks();
        List<Block> blocks2 = ((BlockContainer) block).getBlocks();
        if (blocks != blocks2) {
            if (blocks == null) {
                return -1;
            }
            if (blocks2 == null) {
                return 1;
            }
            if (blocks instanceof Comparable) {
                int iCompareTo = ((Comparable) blocks).compareTo(blocks2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!blocks.equals(blocks2)) {
                int iHashCode = blocks.hashCode();
                int iHashCode2 = blocks2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (obj instanceof BlockContainer) {
            return super.equals(obj) && internalEqualityCheck(getBlocks(), ((BlockContainer) obj).getBlocks());
        }
        return false;
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBlocks());
    }

    public void setBlocks(List<Block> list) {
        this.blocks = list;
    }
}
