package com.neusoft.na.common.presentation;

import android.graphics.SurfaceTexture;

/* JADX INFO: loaded from: classes2.dex */
public interface MapPresentationCallback {
    void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture);

    void onSurfaceTextureDestroyed();

    void switchDisplayMode(int i);
}
