package com.amazon.music.media.playback.util;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Clock {
    public static final Clock ELAPSED_REALTIME_CLOCK;
    public static final Clock SYSTEM_CURRENT_TIME_MILLIS_CLOCK = new Clock() { // from class: com.amazon.music.media.playback.util.Clock.1
        @Override // com.amazon.music.media.playback.util.Clock
        public long getSystemTimeOffsetMillis() {
            return 0L;
        }

        @Override // com.amazon.music.media.playback.util.Clock
        public long getTime() {
            return System.currentTimeMillis();
        }
    };
    public static final Clock UPTIME_MILLIS_CLOCK;
    private static Clock singletonClock;
    private long systemTimeOffset = Long.MAX_VALUE;

    public abstract long getTime();

    static {
        Clock clock = new Clock() { // from class: com.amazon.music.media.playback.util.Clock.2
            @Override // com.amazon.music.media.playback.util.Clock
            public long getTime() {
                return SystemClock.elapsedRealtime();
            }
        };
        ELAPSED_REALTIME_CLOCK = clock;
        UPTIME_MILLIS_CLOCK = new Clock() { // from class: com.amazon.music.media.playback.util.Clock.3
            @Override // com.amazon.music.media.playback.util.Clock
            public long getTime() {
                return SystemClock.uptimeMillis();
            }
        };
        singletonClock = clock;
    }

    public static Clock getClock() {
        return singletonClock;
    }

    public static long now() {
        return singletonClock.getTime();
    }

    protected Clock() {
    }

    public long getSystemTimeOffsetMillis() {
        long jMin = Long.MAX_VALUE;
        if (this.systemTimeOffset != Long.MAX_VALUE && Math.abs((getTime() - System.currentTimeMillis()) - this.systemTimeOffset) < 100) {
            return this.systemTimeOffset;
        }
        int i = 3;
        long jMin2 = Long.MAX_VALUE;
        while (true) {
            i--;
            if (i >= 0) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long time = getTime();
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                jMin = Math.min(jMin, time - jCurrentTimeMillis);
                jMin2 = Math.min(jMin2, jCurrentTimeMillis2 - time);
            } else {
                long j = (jMin - jMin2) / 2;
                this.systemTimeOffset = j;
                return j;
            }
        }
    }
}
