package com.byd.view;

import b.a.i.a;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoPlayerBaseView {
    a getOnPlayerListener();

    int getVideoHeight();

    int getVideoWidth();

    boolean hasFocus();

    boolean isSurfaceCreated();

    void setCanZoom(boolean z);

    void setOnPlayerListener(a aVar);

    void setOnVideoViewListener(OnVideoViewListener onVideoViewListener);

    void setVideoSize(int i, int i2);
}
