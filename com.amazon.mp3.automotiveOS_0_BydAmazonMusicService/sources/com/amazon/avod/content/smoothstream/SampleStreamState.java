package com.amazon.avod.content.smoothstream;

/* JADX INFO: loaded from: classes2.dex */
public class SampleStreamState {
    private volatile long mAvSyncOffsetInNanoseconds = 0;

    public void adjustAvSyncOffsetByNanoseconds(long j) {
        this.mAvSyncOffsetInNanoseconds += j;
    }

    public long getAvSyncOffsetInNanoseconds() {
        return this.mAvSyncOffsetInNanoseconds;
    }

    public void resetAvSyncOffset() {
        this.mAvSyncOffsetInNanoseconds = 0L;
    }
}
