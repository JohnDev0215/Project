package com.facebook.imagepipeline.producers;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface Consumer<T> {
    void onCancellation();

    void onFailure(Throwable t);

    void onNewResult(@Nullable T newResult, int status);

    void onProgressUpdate(float progress);
}
