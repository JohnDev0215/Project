package com.amazon.music.metrics.mts.event;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class EventTimeConverter {
    private static final long MILLI_SECONDS_PER_HALF_SECOND;
    private static final long MILLI_SECONDS_PER_SECOND;

    static {
        long millis = TimeUnit.SECONDS.toMillis(1L);
        MILLI_SECONDS_PER_SECOND = millis;
        MILLI_SECONDS_PER_HALF_SECOND = millis / 2;
    }

    public long convertToRoundedSeconds(long j) {
        return (j + MILLI_SECONDS_PER_HALF_SECOND) / MILLI_SECONDS_PER_SECOND;
    }
}
