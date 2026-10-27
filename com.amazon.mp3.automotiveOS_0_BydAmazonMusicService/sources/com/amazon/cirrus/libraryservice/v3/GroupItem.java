package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class GroupItem extends Item {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GroupItem");
    private Integer numTracks;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.libraryservice.v3.Item, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Item item) {
        if (item == null) {
            return -1;
        }
        if (item == this) {
            return 0;
        }
        if (!(item instanceof GroupItem)) {
            return 1;
        }
        Integer numTracks = getNumTracks();
        Integer numTracks2 = ((GroupItem) item).getNumTracks();
        if (numTracks != numTracks2) {
            if (numTracks == null) {
                return -1;
            }
            if (numTracks2 == null) {
                return 1;
            }
            int iCompareTo = numTracks.compareTo(numTracks2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(item);
    }

    @Override // com.amazon.cirrus.libraryservice.v3.Item
    public boolean equals(Object obj) {
        if (obj instanceof GroupItem) {
            return super.equals(obj) && internalEqualityCheck(getNumTracks(), ((GroupItem) obj).getNumTracks());
        }
        return false;
    }

    public Integer getNumTracks() {
        return this.numTracks;
    }

    @Override // com.amazon.cirrus.libraryservice.v3.Item
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getNumTracks());
    }

    public void setNumTracks(Integer num) {
        this.numTracks = num;
    }
}
