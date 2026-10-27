package com.amazon.music.downloads.worker;

/* JADX INFO: loaded from: classes4.dex */
public class ProgressUpdatePolicy {
    private long mLastProgressUpdate = 0;
    private final long mProgressUpdatePosInBytes;
    private final long mTotalFileSizeInBytes;

    public ProgressUpdatePolicy(long j, float f) {
        this.mTotalFileSizeInBytes = j;
        this.mProgressUpdatePosInBytes = (long) (j * f);
    }

    public boolean shouldUpdate(long j) {
        if (j - this.mLastProgressUpdate < this.mProgressUpdatePosInBytes && this.mTotalFileSizeInBytes != j) {
            return false;
        }
        this.mLastProgressUpdate = j;
        return true;
    }
}
