package com.amazon.avod.ads.api;

import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class Duration {
    private final long mMilliseconds;

    public Duration(VastTimeSpan vastTimeSpan) {
        this.mMilliseconds = ((VastTimeSpan) Preconditions.checkNotNull(vastTimeSpan)).getTotalMilliseconds();
    }

    public Duration(long j) {
        this.mMilliseconds = j;
    }

    public long getTotalSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds(this.mMilliseconds);
    }

    public long getTotalMilliseconds() {
        return this.mMilliseconds;
    }

    public String toBeaconString() {
        Locale locale = Locale.US;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        return String.format(locale, "%02d:%02d:%02d.%03d", Long.valueOf(timeUnit.toHours(this.mMilliseconds)), Long.valueOf(timeUnit.toMinutes(this.mMilliseconds) % 60), Long.valueOf(timeUnit.toSeconds(this.mMilliseconds) % 60), Long.valueOf(this.mMilliseconds % 1000));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mMilliseconds == ((Duration) obj).mMilliseconds;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.mMilliseconds));
    }

    public String toString() {
        return toBeaconString();
    }
}
