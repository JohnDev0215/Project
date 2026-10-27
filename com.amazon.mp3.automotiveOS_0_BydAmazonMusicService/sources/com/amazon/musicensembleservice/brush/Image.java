package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Image implements Comparable<Image> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Image");
    private Boolean fallback;
    private String type;
    private String url;

    public static class Builder {
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
        String type = getType();
        String type2 = image.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo2 = type.compareTo(type2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsFallback = isFallback();
        Boolean boolIsFallback2 = image.isFallback();
        if (boolIsFallback != boolIsFallback2) {
            if (boolIsFallback == null) {
                return -1;
            }
            if (boolIsFallback2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsFallback.compareTo(boolIsFallback2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return internalEqualityCheck(getUrl(), image.getUrl()) && internalEqualityCheck(getType(), image.getType()) && internalEqualityCheck(isFallback(), image.isFallback());
    }

    public String getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getUrl(), getType(), isFallback());
    }

    public Boolean isFallback() {
        return this.fallback;
    }

    public void setFallback(Boolean bool) {
        this.fallback = bool;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
