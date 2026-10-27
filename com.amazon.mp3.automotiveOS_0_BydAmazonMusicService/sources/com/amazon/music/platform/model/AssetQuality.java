package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class AssetQuality implements Comparable<AssetQuality> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.AssetQuality");
    private String playbackId;
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
    public int compareTo(@Deprecated AssetQuality assetQuality) {
        if (assetQuality == null) {
            return -1;
        }
        if (assetQuality == this) {
            return 0;
        }
        String playbackId = getPlaybackId();
        String playbackId2 = assetQuality.getPlaybackId();
        if (playbackId != playbackId2) {
            if (playbackId == null) {
                return -1;
            }
            if (playbackId2 == null) {
                return 1;
            }
            int iCompareTo = playbackId.compareTo(playbackId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
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
            int iCompareTo2 = quality.compareTo(quality2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer rank = getRank();
        Integer rank2 = assetQuality.getRank();
        if (rank != rank2) {
            if (rank == null) {
                return -1;
            }
            if (rank2 == null) {
                return 1;
            }
            int iCompareTo3 = rank.compareTo(rank2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AssetQuality)) {
            return false;
        }
        AssetQuality assetQuality = (AssetQuality) obj;
        return internalEqualityCheck(getPlaybackId(), assetQuality.getPlaybackId()) && internalEqualityCheck(getQuality(), assetQuality.getQuality()) && internalEqualityCheck(getRank(), assetQuality.getRank());
    }

    public String getPlaybackId() {
        return this.playbackId;
    }

    public String getQuality() {
        return this.quality;
    }

    public Integer getRank() {
        return this.rank;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlaybackId(), getQuality(), getRank());
    }

    public void setPlaybackId(String str) {
        this.playbackId = str;
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public void setRank(Integer num) {
        this.rank = num;
    }
}
