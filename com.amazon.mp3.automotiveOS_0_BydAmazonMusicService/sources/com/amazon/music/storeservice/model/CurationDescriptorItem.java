package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class CurationDescriptorItem implements Comparable<CurationDescriptorItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.CurationDescriptorItem");
    private String quality;
    private Integer rank;

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
        Integer rank = getRank();
        Integer rank2 = curationDescriptorItem.getRank();
        if (rank != rank2) {
            if (rank == null) {
                return -1;
            }
            if (rank2 == null) {
                return 1;
            }
            int iCompareTo = rank.compareTo(rank2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
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
            int iCompareTo2 = quality.compareTo(quality2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CurationDescriptorItem)) {
            return false;
        }
        CurationDescriptorItem curationDescriptorItem = (CurationDescriptorItem) obj;
        return internalEqualityCheck(getRank(), curationDescriptorItem.getRank()) && internalEqualityCheck(getQuality(), curationDescriptorItem.getQuality());
    }

    public String getQuality() {
        return this.quality;
    }

    public Integer getRank() {
        return this.rank;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRank(), getQuality());
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public void setRank(Integer num) {
        this.rank = num;
    }
}
