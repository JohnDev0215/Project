package com.amazon.podcast.downloads;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadProgress {
    private final long maxProgress;
    private final long progress;

    public DownloadProgress(final long progress, final long maxProgress) {
        this.progress = progress;
        this.maxProgress = maxProgress;
    }

    public long getProgress() {
        return this.progress;
    }

    public long getMaxProgress() {
        return this.maxProgress;
    }
}
