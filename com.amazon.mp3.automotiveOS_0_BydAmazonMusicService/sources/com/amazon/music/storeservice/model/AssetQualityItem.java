package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class AssetQualityItem implements Comparable<AssetQualityItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.AssetQualityItem");
    private String pid;
    private String quality;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AssetQualityItem assetQualityItem) {
        if (assetQualityItem == null) {
            return -1;
        }
        if (assetQualityItem == this) {
            return 0;
        }
        String quality = getQuality();
        String quality2 = assetQualityItem.getQuality();
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
        String pid = getPid();
        String pid2 = assetQualityItem.getPid();
        if (pid != pid2) {
            if (pid == null) {
                return -1;
            }
            if (pid2 == null) {
                return 1;
            }
            int iCompareTo2 = pid.compareTo(pid2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AssetQualityItem)) {
            return false;
        }
        AssetQualityItem assetQualityItem = (AssetQualityItem) obj;
        return internalEqualityCheck(getQuality(), assetQualityItem.getQuality()) && internalEqualityCheck(getPid(), assetQualityItem.getPid());
    }

    public String getPid() {
        return this.pid;
    }

    public String getQuality() {
        return this.quality;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getQuality(), getPid());
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public void setQuality(String str) {
        this.quality = str;
    }
}
