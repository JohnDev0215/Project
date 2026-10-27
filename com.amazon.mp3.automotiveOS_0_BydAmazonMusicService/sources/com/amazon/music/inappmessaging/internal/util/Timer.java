package com.amazon.music.inappmessaging.internal.util;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.perf.plugin.util.AsmString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class Timer {
    private final Clock clock;
    private String intervalName;
    private long intervalTime;

    @Nullable
    private final String name;
    private long startTime;
    private static final Long MS_TO_NS = 1000000L;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) Timer.class);

    @VisibleForTesting
    static class Clock {
        Clock() {
        }

        public long getTime() {
            return System.nanoTime();
        }
    }

    public static class Interval {
        public final long intervalTime;
        public final long totalTime;

        private Interval(final long intervalTime, final long totalTime) {
            this.intervalTime = intervalTime;
            this.totalTime = totalTime;
        }
    }

    @VisibleForTesting
    Timer(@Nullable final String name, final Clock clock) {
        this.name = name;
        this.clock = clock;
    }

    public Timer(@Nullable final String name) {
        this(name, new Clock());
    }

    public void start() {
        long time = this.clock.getTime();
        this.startTime = time;
        this.intervalTime = time;
        this.intervalName = AsmString.METHOD_FIREBASE_PERF_TRACE_START;
    }

    public Interval logInterval(final String intervalName) {
        long time = this.clock.getTime();
        long jNanosToMillis = nanosToMillis(time - this.startTime);
        long jNanosToMillis2 = nanosToMillis(time - this.intervalTime);
        LOG.debug("[" + this.name + ":" + intervalName + "]: total elapsed = " + jNanosToMillis + "ms, time since " + this.intervalName + " = " + jNanosToMillis2 + "ms");
        this.intervalTime = time;
        this.intervalName = intervalName;
        return new Interval(jNanosToMillis2, jNanosToMillis);
    }

    private static long nanosToMillis(long time) {
        return time / MS_TO_NS.longValue();
    }
}
