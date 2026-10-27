package com.amazon.avod.media.playback.render;

/* JADX INFO: loaded from: classes2.dex */
public class SubmitBufferResult {
    private int mBytesRead = 0;
    private long mAdjustedReleaseTimeNs = -3;

    public int getBytesRead() {
        return this.mBytesRead;
    }

    public void setBytesRead(int i) {
        this.mBytesRead = i;
    }

    public long getAdjustedReleaseTimeNs() {
        return this.mAdjustedReleaseTimeNs;
    }

    public void setAdjustedReleaseTimeNs(long j) {
        this.mAdjustedReleaseTimeNs = j;
    }
}
