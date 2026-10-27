package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Image implements Comparable<Image> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.Image");
    private int height;
    private String url;
    private int width;

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
        String url = getUrl();
        String url2 = image.getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo = url.compareTo(url2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (getWidth() < image.getWidth()) {
            return -1;
        }
        if (getWidth() > image.getWidth()) {
            return 1;
        }
        if (getHeight() < image.getHeight()) {
            return -1;
        }
        return getHeight() > image.getHeight() ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return internalEqualityCheck(getUrl(), image.getUrl()) && internalEqualityCheck(Integer.valueOf(getWidth()), Integer.valueOf(image.getWidth())) && internalEqualityCheck(Integer.valueOf(getHeight()), Integer.valueOf(image.getHeight()));
    }

    public int getHeight() {
        return this.height;
    }

    public String getUrl() {
        return this.url;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getUrl(), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setWidth(int i) {
        this.width = i;
    }
}
