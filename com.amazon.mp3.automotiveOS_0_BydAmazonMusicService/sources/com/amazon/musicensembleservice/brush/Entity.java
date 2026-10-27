package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Entity implements Comparable<Entity> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Entity");
    private String blockRef;
    private Integer rankedIndex;

    public static abstract class Builder {
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        String blockRef = getBlockRef();
        String blockRef2 = entity.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo = blockRef.compareTo(blockRef2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer rankedIndex = getRankedIndex();
        Integer rankedIndex2 = entity.getRankedIndex();
        if (rankedIndex != rankedIndex2) {
            if (rankedIndex == null) {
                return -1;
            }
            if (rankedIndex2 == null) {
                return 1;
            }
            int iCompareTo2 = rankedIndex.compareTo(rankedIndex2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) obj;
        return internalEqualityCheck(getBlockRef(), entity.getBlockRef()) && internalEqualityCheck(getRankedIndex(), entity.getRankedIndex());
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public Integer getRankedIndex() {
        return this.rankedIndex;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBlockRef(), getRankedIndex());
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setRankedIndex(Integer num) {
        this.rankedIndex = num;
    }
}
