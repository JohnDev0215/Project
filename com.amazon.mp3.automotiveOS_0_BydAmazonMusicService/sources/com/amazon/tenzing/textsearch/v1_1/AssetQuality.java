package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AssetQuality implements Comparable<AssetQuality> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.AssetQuality");
    private List<String> quality;

    public static class Builder {
        protected List<String> quality;

        public AssetQuality build() {
            AssetQuality assetQuality = new AssetQuality();
            populate(assetQuality);
            return assetQuality;
        }

        protected void populate(AssetQuality assetQuality) {
            assetQuality.setQuality(this.quality);
        }

        public Builder withQuality(List<String> list) {
            this.quality = list;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AssetQuality assetQuality) {
        List<String> quality;
        List<String> quality2;
        if (assetQuality == null) {
            return -1;
        }
        if (assetQuality != this && (quality = getQuality()) != (quality2 = assetQuality.getQuality())) {
            if (quality == null) {
                return -1;
            }
            if (quality2 == null) {
                return 1;
            }
            if (quality instanceof Comparable) {
                int iCompareTo = ((Comparable) quality).compareTo(quality2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!quality.equals(quality2)) {
                int iHashCode = quality.hashCode();
                int iHashCode2 = quality2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AssetQuality) {
            return internalEqualityCheck(getQuality(), ((AssetQuality) obj).getQuality());
        }
        return false;
    }

    public List<String> getQuality() {
        return this.quality;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getQuality());
    }

    public void setQuality(List<String> list) {
        this.quality = list;
    }
}
