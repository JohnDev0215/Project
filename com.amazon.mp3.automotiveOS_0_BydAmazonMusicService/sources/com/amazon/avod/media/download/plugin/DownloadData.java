package com.amazon.avod.media.download.plugin;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class DownloadData<T> {
    private final T mDownloadData;
    private final long mDownloadTime;

    public DownloadData(@Nullable T t, long j) {
        this.mDownloadData = t;
        this.mDownloadTime = j;
    }

    @Nullable
    public T getDownloadData() {
        return this.mDownloadData;
    }

    public long getDownloadTime() {
        return this.mDownloadTime;
    }
}
