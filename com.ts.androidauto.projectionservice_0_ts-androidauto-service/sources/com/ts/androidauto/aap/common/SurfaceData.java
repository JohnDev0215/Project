package com.ts.androidauto.aap.common;

import android.view.Surface;

/* JADX INFO: loaded from: classes.dex */
public class SurfaceData {
    private int mHeight;
    private Surface mSurface;
    private int mWidth;

    public Surface getSurface() {
        return this.mSurface;
    }

    public void setSurface(Surface surface) {
        this.mSurface = surface;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }
}
