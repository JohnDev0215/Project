package com.amplifyframework.storage.result;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageTransferProgress {
    private final long currentBytes;
    private final long totalBytes;

    public StorageTransferProgress(long j, long j2) {
        this.currentBytes = j;
        this.totalBytes = j2;
    }

    public long getCurrentBytes() {
        return this.currentBytes;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public double getFractionCompleted() {
        return this.currentBytes / this.totalBytes;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StorageTransferProgress.class != obj.getClass()) {
            return false;
        }
        StorageTransferProgress storageTransferProgress = (StorageTransferProgress) obj;
        return getCurrentBytes() == storageTransferProgress.getCurrentBytes() && getTotalBytes() == storageTransferProgress.getTotalBytes();
    }

    public int hashCode() {
        return (((int) (getCurrentBytes() ^ (getCurrentBytes() >>> 32))) * 31) + ((int) (getTotalBytes() ^ (getTotalBytes() >>> 32)));
    }

    public String toString() {
        return "StorageTransferProgress{currentBytes=" + getCurrentBytes() + ", totalBytes=" + getTotalBytes() + ", fractionCompleted=" + getFractionCompleted() + '}';
    }
}
