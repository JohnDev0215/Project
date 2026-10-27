package com.ts.androidauto.aap.video;

/* JADX INFO: loaded from: classes2.dex */
public interface AapVideoCallback {
    default void onVideoFocusStatusChanged(int videoFocusStatus) {
    }

    default void onClusterVideoFocusStatusChanged(int videoFocusStatus) {
    }

    default void onClusterVideoUpdateUiConfigReply(boolean replyOk) {
    }
}
