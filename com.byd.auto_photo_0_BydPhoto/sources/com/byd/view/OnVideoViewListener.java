package com.byd.view;

import android.view.Surface;

/* JADX INFO: loaded from: classes2.dex */
public interface OnVideoViewListener {
    void onMotionEventDown();

    void onMotionEventUp();

    void onSurfaceDestroy();

    void onSurfaceTextureAvailable(Surface surface, int i, int i2);

    void onViewSizeChange(int i, int i2);
}
