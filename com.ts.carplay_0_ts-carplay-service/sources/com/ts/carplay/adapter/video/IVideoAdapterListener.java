package com.ts.carplay.adapter.video;

/* JADX INFO: loaded from: classes.dex */
public interface IVideoAdapterListener {
    default void onNativeUiTouched() {
    }

    default void onVideoFocusChanged(int priority, boolean backgroundSupport) {
    }
}
