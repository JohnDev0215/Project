package org.chromium.media;

/* JADX INFO: loaded from: classes3.dex */
class VideoCaptureFormat {
    final int mFramerate;
    int mHeight;
    final int mPixelFormat;
    int mWidth;

    public VideoCaptureFormat(int i, int i2, int i3, int i4) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mFramerate = i3;
        this.mPixelFormat = i4;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getFramerate() {
        return this.mFramerate;
    }

    public int getPixelFormat() {
        return this.mPixelFormat;
    }
}
