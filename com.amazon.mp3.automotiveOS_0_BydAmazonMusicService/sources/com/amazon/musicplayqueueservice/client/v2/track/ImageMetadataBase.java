package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ImageMetadataBase implements Comparable<ImageMetadataBase> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.ImageMetadataBase");
    private String clickUrl;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ImageMetadataBase imageMetadataBase) {
        String clickUrl;
        String clickUrl2;
        if (imageMetadataBase == null) {
            return -1;
        }
        if (imageMetadataBase != this && (clickUrl = getClickUrl()) != (clickUrl2 = imageMetadataBase.getClickUrl())) {
            if (clickUrl == null) {
                return -1;
            }
            if (clickUrl2 == null) {
                return 1;
            }
            int iCompareTo = clickUrl.compareTo(clickUrl2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageMetadataBase) {
            return internalEqualityCheck(getClickUrl(), ((ImageMetadataBase) obj).getClickUrl());
        }
        return false;
    }

    public String getClickUrl() {
        return this.clickUrl;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getClickUrl());
    }

    public void setClickUrl(String str) {
        this.clickUrl = str;
    }
}
