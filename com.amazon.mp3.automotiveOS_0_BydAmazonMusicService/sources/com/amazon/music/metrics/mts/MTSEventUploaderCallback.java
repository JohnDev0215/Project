package com.amazon.music.metrics.mts;

/* JADX INFO: loaded from: classes4.dex */
public interface MTSEventUploaderCallback {
    void onError(Exception exc);

    void onSuccess();

    void onUnrecoverableError(Exception exc);
}
