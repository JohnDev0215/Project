package com.amazon.mp3.voice.metrics;

import com.amazon.music.voice.metrics.SystemTimeProvider;

/* JADX INFO: loaded from: classes4.dex */
public class ClockMillisecondsTimeProvider implements SystemTimeProvider {
    @Override // com.amazon.music.voice.metrics.SystemTimeProvider
    public long getCurrentTimeMilliseconds() {
        return System.currentTimeMillis();
    }
}
