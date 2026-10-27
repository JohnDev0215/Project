package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import com.google.common.base.MoreObjects;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class HeuristicsDownloadInfo {
    private int mBytesDownloaded;
    private final long mDownloadHandle;
    private final int mFragmentIndex;
    private int mSizeInBytes;
    private final long mStartedTimeMillis;
    private final int mTimeAllowedMillis;

    HeuristicsDownloadInfo(int i, long j, int i2, long j2, int i3) {
        this.mFragmentIndex = i;
        this.mDownloadHandle = j;
        this.mSizeInBytes = i2;
        this.mStartedTimeMillis = j2;
        this.mTimeAllowedMillis = i3;
    }

    int getFragmentIndex() {
        return this.mFragmentIndex;
    }

    long getDownloadHandle() {
        return this.mDownloadHandle;
    }

    int getBytesDownloaded() {
        return this.mBytesDownloaded;
    }

    int getSizeInBytes() {
        return this.mSizeInBytes;
    }

    long getStartedTimeMillis() {
        return this.mStartedTimeMillis;
    }

    void setBytesDownloaded(int i) {
        this.mBytesDownloaded = i;
    }

    void setSizeInBytes(int i) {
        this.mSizeInBytes = i;
    }

    int getTimeAllowedMillis() {
        return this.mTimeAllowedMillis;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mFragmentIndex", this.mFragmentIndex).add("mDownloadHandle", this.mDownloadHandle).add("mSizeInBytes", this.mSizeInBytes).add("mStartedTimeMillis", this.mStartedTimeMillis).add("mTimeAllowedMillis", this.mTimeAllowedMillis).add("mBytesDownloaded", this.mBytesDownloaded).toString();
    }
}
