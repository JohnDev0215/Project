package com.amazon.weblab.mobile.settings;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class Interval {
    private long durationInMillis;

    public Interval(long j, TimeUnit timeUnit) {
        this.durationInMillis = 0L;
        if (j < 0) {
            throw new IllegalArgumentException("duration can't be less than 0.");
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit can't be null.");
        }
        this.durationInMillis = timeUnit.toMillis(j);
    }

    public long convertTo(TimeUnit timeUnit) {
        return timeUnit.convert(this.durationInMillis, TimeUnit.MILLISECONDS);
    }

    public String toString(TimeUnit timeUnit) {
        return String.format("%d %s", Long.valueOf(convertTo(timeUnit)), timeUnit.toString().toLowerCase());
    }

    public String toString() {
        return toString(TimeUnit.MINUTES);
    }

    public int hashCode() {
        long j = this.durationInMillis;
        return 31 + ((int) (j ^ (j >>> 32)));
    }

    public boolean equals(Object obj) {
        return (obj instanceof Interval) && ((Interval) obj).durationInMillis == this.durationInMillis;
    }
}
