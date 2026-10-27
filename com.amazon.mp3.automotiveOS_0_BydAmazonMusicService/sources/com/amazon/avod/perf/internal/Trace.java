package com.amazon.avod.perf.internal;

import android.os.SystemClock;
import com.amazon.avod.perf.TraceKey;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class Trace {
    private final TraceKey mKey;
    private long mStartTime = 0;
    private long mEndTime = 0;
    private String mOverriddenName = null;
    private boolean mHasExactStartTime = false;
    private boolean mHasExactEndTime = false;

    public Trace(@Nonnull TraceKey traceKey) {
        this.mKey = traceKey;
    }

    @Nonnull
    public TraceKey getKey() {
        return this.mKey;
    }

    @Nonnull
    public String getName() {
        String str = this.mOverriddenName;
        return str != null ? str : this.mKey.getName();
    }

    public void begin() {
        this.mHasExactStartTime = true;
        this.mStartTime = SystemClock.elapsedRealtime();
    }

    public void end(@Nullable String str, long j) {
        this.mOverriddenName = str;
        this.mEndTime = SystemClock.elapsedRealtime();
        this.mHasExactEndTime = true;
        long j2 = this.mStartTime;
        boolean z = j2 >= j;
        this.mHasExactStartTime = z;
        if (z) {
            j = j2;
        }
        this.mStartTime = j;
    }

    public void endRetroactively(@Nullable String str, long j, long j2) {
        this.mOverriddenName = str;
        this.mEndTime = j2;
        this.mHasExactEndTime = true;
        long j3 = this.mStartTime;
        boolean z = j3 >= j;
        this.mHasExactStartTime = z;
        if (z) {
            j = j3;
        }
        this.mStartTime = j;
    }

    public void endPrematurely(@Nullable String str, long j) {
        end(str, j);
        this.mHasExactEndTime = false;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getDuration() {
        return this.mEndTime - this.mStartTime;
    }

    public String toString() {
        return String.format(Locale.US, "Trace[key=%s, duration=%s]", this.mKey.toString(), Long.valueOf(this.mEndTime - this.mStartTime));
    }
}
