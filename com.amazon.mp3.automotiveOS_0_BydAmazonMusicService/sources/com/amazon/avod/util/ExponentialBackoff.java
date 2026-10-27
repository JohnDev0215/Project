package com.amazon.avod.util;

import com.google.common.base.MoreObjects;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class ExponentialBackoff implements BackoffPolicy {
    private final float mBackoffGrowthFactor;
    private final long mMaxBackoffMillis;
    private final long mMinBackoffMillis;
    private long mNextBackoffMillis;

    public ExponentialBackoff(long j, long j2, float f) {
        long jMax = Math.max(j, 0L);
        this.mMinBackoffMillis = jMax;
        this.mMaxBackoffMillis = Math.max(j2, 0L);
        this.mBackoffGrowthFactor = Math.max(f, 1.0f);
        this.mNextBackoffMillis = jMax;
    }

    @Override // com.amazon.avod.util.BackoffPolicy
    public long nextBackoffMillis() {
        long j = this.mNextBackoffMillis;
        this.mNextBackoffMillis = Math.min((long) (j * this.mBackoffGrowthFactor), this.mMaxBackoffMillis);
        return j;
    }

    public void reset() {
        this.mNextBackoffMillis = this.mMinBackoffMillis;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass().getSimpleName()).add("nextSleep", String.format(Locale.US, "%dms", Long.valueOf(this.mNextBackoffMillis))).toString();
    }
}
