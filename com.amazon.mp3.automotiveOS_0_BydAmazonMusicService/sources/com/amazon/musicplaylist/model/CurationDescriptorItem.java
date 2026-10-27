package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CurationDescriptorItem implements Comparable<CurationDescriptorItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.CurationDescriptorItem");
    private String quality;
    private int rank;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CurationDescriptorItem curationDescriptorItem) {
        if (curationDescriptorItem == null) {
            return -1;
        }
        if (curationDescriptorItem == this) {
            return 0;
        }
        String quality = getQuality();
        String quality2 = curationDescriptorItem.getQuality();
        if (quality != quality2) {
            if (quality == null) {
                return -1;
            }
            if (quality2 == null) {
                return 1;
            }
            int iCompareTo = quality.compareTo(quality2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (getRank() < curationDescriptorItem.getRank()) {
            return -1;
        }
        return getRank() > curationDescriptorItem.getRank() ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CurationDescriptorItem)) {
            return false;
        }
        CurationDescriptorItem curationDescriptorItem = (CurationDescriptorItem) obj;
        return internalEqualityCheck(getQuality(), curationDescriptorItem.getQuality()) && internalEqualityCheck(Integer.valueOf(getRank()), Integer.valueOf(curationDescriptorItem.getRank()));
    }

    public String getQuality() {
        return this.quality;
    }

    public int getRank() {
        return this.rank;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getQuality(), Integer.valueOf(getRank()));
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public void setRank(int i) {
        this.rank = i;
    }
}
