package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

/* JADX INFO: loaded from: classes2.dex */
public class LiveDynamicFrontMargin {
    private long mCurrentFrontMarginNanos = 0;
    private final long mIncreaseStepNanos;
    private final long mMaxFrontMarginNanos;

    public LiveDynamicFrontMargin(long j, long j2) {
        this.mMaxFrontMarginNanos = j;
        this.mIncreaseStepNanos = j2;
    }

    public void onFragmentNotFound() {
        this.mCurrentFrontMarginNanos = Math.min(this.mMaxFrontMarginNanos, this.mCurrentFrontMarginNanos + this.mIncreaseStepNanos);
    }

    public long getCurrentFrontMarginNanos() {
        return this.mCurrentFrontMarginNanos;
    }
}
