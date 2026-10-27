package com.amazon.minerva.client.thirdparty.metric;

import java.util.Objects;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes3.dex */
public final class Timestamp {
    private final long epochMillis;
    private final TimeZone timeZone;

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis(), TimeZone.getDefault());
    }

    public static Timestamp of(long j, TimeZone timeZone) {
        Objects.requireNonNull(timeZone, "Time zone cannot be null");
        return new Timestamp(j, (TimeZone) timeZone.clone());
    }

    private Timestamp(long j, TimeZone timeZone) {
        this.epochMillis = j;
        this.timeZone = timeZone;
    }

    public long getEpochMillis() {
        return this.epochMillis;
    }

    public TimeZone getTimeZone() {
        return (TimeZone) this.timeZone.clone();
    }

    public int getTimeZoneOffset() {
        return this.timeZone.getOffset(this.epochMillis);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Timestamp.class != obj.getClass()) {
            return false;
        }
        Timestamp timestamp = (Timestamp) obj;
        return this.epochMillis == timestamp.epochMillis && Objects.equals(this.timeZone, timestamp.timeZone);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.epochMillis), this.timeZone);
    }

    public String toString() {
        return this.epochMillis + "@" + this.timeZone.getID();
    }
}
