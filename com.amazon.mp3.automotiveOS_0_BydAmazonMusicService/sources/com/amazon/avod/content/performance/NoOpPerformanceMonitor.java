package com.amazon.avod.content.performance;

import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class NoOpPerformanceMonitor implements PerformanceMonitor {
    @Override // com.amazon.avod.content.performance.PerformanceMonitor
    public void begin(@Nonnull ContentAccessor contentAccessor, @Nonnull ContentSessionState contentSessionState, @Nonnull StreamIndex streamIndex) {
    }

    @Override // com.amazon.avod.content.performance.PerformanceMonitor
    public void end() {
    }

    @Override // com.amazon.avod.content.performance.PerformanceMonitor
    public void setPlaybackPerformanceReport(@Nullable PlaybackPerformanceReport playbackPerformanceReport) {
    }
}
