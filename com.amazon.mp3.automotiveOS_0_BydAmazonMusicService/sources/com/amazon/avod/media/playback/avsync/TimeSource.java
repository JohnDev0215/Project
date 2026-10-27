package com.amazon.avod.media.playback.avsync;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public interface TimeSource {
    public static final TimeSource DEFAULT_INSTANCE = new TimeSource() { // from class: com.amazon.avod.media.playback.avsync.TimeSource.1
        @Override // com.amazon.avod.media.playback.avsync.TimeSource
        public boolean hasStartedTicking() {
            return true;
        }

        @Override // com.amazon.avod.media.playback.avsync.TimeSource
        public long getCurrentRealTimeUs() {
            return TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
        }
    };

    long getCurrentRealTimeUs();

    boolean hasStartedTicking();
}
