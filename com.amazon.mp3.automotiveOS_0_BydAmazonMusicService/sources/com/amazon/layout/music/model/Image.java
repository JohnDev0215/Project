package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class Image implements Comparable<Image> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.Image");
    private String aspectRatio;
    private String url;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Image image) {
        if (image == null) {
            return -1;
        }
        if (image == this) {
            return 0;
        }
        String aspectRatio = getAspectRatio();
        String aspectRatio2 = image.getAspectRatio();
        if (aspectRatio != aspectRatio2) {
            if (aspectRatio == null) {
                return -1;
            }
            if (aspectRatio2 == null) {
                return 1;
            }
            int iCompareTo = aspectRatio.compareTo(aspectRatio2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String url = getUrl();
        String url2 = image.getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo2 = url.compareTo(url2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return internalEqualityCheck(getAspectRatio(), image.getAspectRatio()) && internalEqualityCheck(getUrl(), image.getUrl());
    }

    public String getAspectRatio() {
        return this.aspectRatio;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAspectRatio(), getUrl());
    }

    public void setAspectRatio(String str) {
        this.aspectRatio = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
