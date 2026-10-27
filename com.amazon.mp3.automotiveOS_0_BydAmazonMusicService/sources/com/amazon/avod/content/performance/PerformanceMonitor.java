package com.amazon.avod.content.performance;

import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PerformanceMonitor {
    void begin(@Nonnull ContentAccessor contentAccessor, @Nonnull ContentSessionState contentSessionState, @Nonnull StreamIndex streamIndex);

    void end();

    void setPlaybackPerformanceReport(@Nullable PlaybackPerformanceReport playbackPerformanceReport);
}
