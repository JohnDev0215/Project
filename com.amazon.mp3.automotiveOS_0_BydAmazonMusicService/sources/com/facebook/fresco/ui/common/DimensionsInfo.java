package com.facebook.fresco.ui.common;

/* JADX INFO: loaded from: classes5.dex */
public class DimensionsInfo {
    private final int mDecodedImageHeight;
    private final int mDecodedImageWidth;
    private final int mEncodedImageHeight;
    private final int mEncodedImageWidth;
    private final String mScaleType;
    private final int mViewportHeight;
    private final int mViewportWidth;

    public int getViewportWidth() {
        return this.mViewportWidth;
    }

    public int getViewportHeight() {
        return this.mViewportHeight;
    }

    public int getEncodedImageWidth() {
        return this.mEncodedImageWidth;
    }

    public int getEncodedImageHeight() {
        return this.mEncodedImageHeight;
    }

    public int getDecodedImageWidth() {
        return this.mDecodedImageWidth;
    }

    public int getDecodedImageHeight() {
        return this.mDecodedImageHeight;
    }

    public String toString() {
        return "DimensionsInfo{mViewportWidth=" + this.mViewportWidth + ", mViewportHeight=" + this.mViewportHeight + ", mEncodedImageWidth=" + this.mEncodedImageWidth + ", mEncodedImageHeight=" + this.mEncodedImageHeight + ", mDecodedImageWidth=" + this.mDecodedImageWidth + ", mDecodedImageHeight=" + this.mDecodedImageHeight + ", mScaleType='" + this.mScaleType + "'}";
    }
}
