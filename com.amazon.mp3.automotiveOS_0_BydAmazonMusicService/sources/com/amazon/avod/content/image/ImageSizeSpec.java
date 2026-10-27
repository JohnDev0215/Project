package com.amazon.avod.content.image;

import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class ImageSizeSpec {
    private final int mHashCode;
    private final int mHeight;
    private final int mWidth;

    private ImageSizeSpec() {
        this(0, 0);
    }

    public ImageSizeSpec(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mHashCode = Arrays.hashCode(new int[]{i, i2});
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        ImageSizeSpec imageSizeSpec = (ImageSizeSpec) obj;
        return this.mWidth == imageSizeSpec.mWidth && this.mHeight == imageSizeSpec.mHeight;
    }

    public int hashCode() {
        return this.mHashCode;
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        int i = this.mWidth;
        objArr[0] = i >= 0 ? String.valueOf(i) : "?";
        int i2 = this.mHeight;
        objArr[1] = i2 >= 0 ? String.valueOf(i2) : "?";
        return String.format(locale, "%sx%s", objArr);
    }
}
