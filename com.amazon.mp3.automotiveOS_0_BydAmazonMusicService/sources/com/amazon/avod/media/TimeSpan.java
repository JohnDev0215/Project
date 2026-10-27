package com.amazon.avod.media;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.primitives.Longs;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public final class TimeSpan implements Comparable<TimeSpan> {
    public static final TimeSpan MAX_VALUE = new TimeSpan(0, 0, 2147483647L, 0, 0);
    public static final TimeSpan ZERO = new TimeSpan(0, 0, 0, 0, 0);
    private final long mTimeNanoSeconds;

    public TimeSpan(long j) {
        Preconditions.checkArgument(j >= 0, "Cannot construct negative TimeSpan!");
        this.mTimeNanoSeconds = j;
    }

    public TimeSpan(@Nonnull Stopwatch stopwatch) {
        this(stopwatch.elapsed(TimeUnit.NANOSECONDS));
    }

    public TimeSpan(long j, long j2) {
        this(nanosecondsFromTimeScale(j, j2));
    }

    public static long nanosecondsFromTimeScale(long j, long j2) {
        Preconditions.checkArgument(j2 > 0, "timeScale should be bigger than 0");
        return (long) ((j / j2) * 1.0E9d);
    }

    public TimeSpan(long j, long j2, long j3, long j4, long j5) {
        this((j * 60 * 60 * 1000000000) + (j2 * 60 * 1000000000) + (j3 * 1000000000) + (j4 * 1000000) + (j5 * 1000));
    }

    public long getTotalNanoSeconds() {
        return this.mTimeNanoSeconds;
    }

    private long getMicroseconds() {
        return getTotalMicroseconds() % 1000;
    }

    public long getTotalMicroseconds() {
        return this.mTimeNanoSeconds / 1000;
    }

    private long getMilliseconds() {
        return getTotalMilliseconds() % 1000;
    }

    public long getTotalMilliseconds() {
        return this.mTimeNanoSeconds / 1000000;
    }

    public int getTotalSeconds() {
        return (int) (this.mTimeNanoSeconds / 1000000000);
    }

    private int getSeconds() {
        return getTotalSeconds() % 60;
    }

    public int getTotalMinutes() {
        return getTotalSeconds() / 60;
    }

    private int getMinutes() {
        return getTotalMinutes() % 60;
    }

    public int getTotalHours() {
        return getTotalMinutes() / 60;
    }

    private int getHours() {
        return getTotalHours() % 60;
    }

    public static TimeSpan add(TimeSpan timeSpan, TimeSpan timeSpan2) {
        return new TimeSpan(timeSpan.mTimeNanoSeconds + timeSpan2.mTimeNanoSeconds);
    }

    public static TimeSpan difference(TimeSpan timeSpan, TimeSpan timeSpan2) {
        return new TimeSpan(Math.abs(timeSpan.mTimeNanoSeconds - timeSpan2.mTimeNanoSeconds));
    }

    public static TimeSpan min(TimeSpan timeSpan, TimeSpan timeSpan2) {
        return timeSpan.compareTo(timeSpan2) < 0 ? timeSpan : timeSpan2;
    }

    public static TimeSpan max(TimeSpan timeSpan, TimeSpan timeSpan2) {
        return timeSpan.compareTo(timeSpan2) < 0 ? timeSpan2 : timeSpan;
    }

    public static TimeSpan now() {
        return fromMilliseconds(System.currentTimeMillis());
    }

    public static final TimeSpan fromMilliseconds(long j) {
        return new TimeSpan(0L, 0L, 0L, j, 0L);
    }

    public static final TimeSpan fromMicroseconds(long j) {
        return new TimeSpan(0L, 0L, 0L, 0L, j);
    }

    public static TimeSpan fromSeconds(double d) {
        return new TimeSpan(0L, 0L, 0L, 0L, (long) (d * 1000000.0d));
    }

    public static TimeSpan fromMinutes(long j) {
        return new TimeSpan(0L, j, 0L, 0L, 0L);
    }

    public static TimeSpan fromHours(long j) {
        return new TimeSpan(j, 0L, 0L, 0L, 0L);
    }

    public boolean greaterThan(TimeSpan timeSpan) {
        return compareTo(timeSpan) > 0;
    }

    public boolean greaterThanEquals(TimeSpan timeSpan) {
        return compareTo(timeSpan) >= 0;
    }

    public boolean lessThan(TimeSpan timeSpan) {
        return compareTo(timeSpan) < 0;
    }

    public boolean lessThanEquals(TimeSpan timeSpan) {
        return compareTo(timeSpan) <= 0;
    }

    @Override // java.lang.Comparable
    public int compareTo(TimeSpan timeSpan) {
        long j = this.mTimeNanoSeconds - timeSpan.mTimeNanoSeconds;
        if (j < 0) {
            return -1;
        }
        return j > 0 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof TimeSpan) && ((TimeSpan) obj).mTimeNanoSeconds == this.mTimeNanoSeconds;
    }

    public String toString() {
        return String.format(Locale.US, "%02d:%02d:%02d.%03d(%03d)", Integer.valueOf(getHours()), Integer.valueOf(getMinutes()), Integer.valueOf(getSeconds()), Long.valueOf(getMilliseconds()), Long.valueOf(getMicroseconds()));
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.TimeSpan$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            $SwitchMap$java$util$concurrent$TimeUnit = iArr;
            try {
                iArr[TimeUnit.HOURS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String toString(TimeUnit timeUnit) {
        int i = AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[timeUnit.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? toString() : String.format(Locale.US, "%02d:%02d:%02d.%03d", Integer.valueOf(getHours()), Integer.valueOf(getMinutes()), Integer.valueOf(getSeconds()), Long.valueOf(getMilliseconds())) : String.format(Locale.US, "%02d:%02d:%02d", Integer.valueOf(getHours()), Integer.valueOf(getMinutes()), Integer.valueOf(getSeconds())) : String.format(Locale.US, "%02d:%02d", Integer.valueOf(getHours()), Integer.valueOf(getMinutes())) : String.format(Locale.US, "%02d", Integer.valueOf(getHours()));
    }

    public int hashCode() {
        return Longs.hashCode(this.mTimeNanoSeconds);
    }

    public boolean isZero() {
        return this.mTimeNanoSeconds == 0;
    }
}
