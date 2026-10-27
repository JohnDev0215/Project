package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AssetQuality implements Comparable<AssetQuality> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.AssetQuality");
    private String pid;
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
    public int compareTo(@Deprecated AssetQuality assetQuality) {
        if (assetQuality == null) {
            return -1;
        }
        if (assetQuality == this) {
            return 0;
        }
        if (getRank() < assetQuality.getRank()) {
            return -1;
        }
        if (getRank() > assetQuality.getRank()) {
            return 1;
        }
        String quality = getQuality();
        String quality2 = assetQuality.getQuality();
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
        String pid2 = assetQuality.getPid();
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
        if (!(obj instanceof AssetQuality)) {
            return false;
        }
        AssetQuality assetQuality = (AssetQuality) obj;
        return internalEqualityCheck(Integer.valueOf(getRank()), Integer.valueOf(assetQuality.getRank())) && internalEqualityCheck(getQuality(), assetQuality.getQuality()) && internalEqualityCheck(getPid(), assetQuality.getPid());
    }

    public String getPid() {
        return this.pid;
    }

    public String getQuality() {
        return this.quality;
    }

    public int getRank() {
        return this.rank;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getRank()), getQuality(), getPid());
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public void setRank(int i) {
        this.rank = i;
    }
}
