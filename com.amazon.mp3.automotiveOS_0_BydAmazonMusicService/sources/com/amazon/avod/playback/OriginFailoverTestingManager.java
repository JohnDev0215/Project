package com.amazon.avod.playback;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsConfig;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class OriginFailoverTestingManager {

    @Nullable
    private final ScheduledExecutorService mExecutor;

    @Nullable
    private ScheduledFuture<?> mZigZagScheduledTask;

    public OriginFailoverTestingManager() {
        this(DiagnosticsConfig.getInstance().isDiagnosticsViewAvailable() ? ScheduledExecutorBuilder.newBuilderFor("OriginFailoverTestingManager", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build() : null);
    }

    @VisibleForTesting
    OriginFailoverTestingManager(@Nullable ScheduledExecutorService scheduledExecutorService) {
        this.mExecutor = scheduledExecutorService;
    }

    public void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode, @Nonnull PlaybackSession playbackSession) {
        ScheduledExecutorService scheduledExecutorService = this.mExecutor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        Preconditions.checkNotNull(failoverType, "failoverType");
        Preconditions.checkNotNull(playbackSession, "playbackSession");
        ContentSession contentSession = playbackSession.getContentSession();
        if (contentSession == null) {
            DLog.logf("Unable to trigger failover, session was null");
            return;
        }
        ContentUrlSelector contentUrlSelector = contentSession.getContentUrlSelector();
        if (contentUrlSelector == null) {
            DLog.logf("Unable to trigger failover, urlSelector was null");
            return;
        }
        VideoPlaybackEngine playbackEngine = playbackSession.getPlaybackEngine();
        if (playbackEngine == null) {
            DLog.logf("Unable to trigger failover, engine was null");
        } else {
            contentUrlSelector.manuallyTriggerFailover(failoverType, failoverMode, playbackEngine.getCurrentPositionInNanos());
        }
    }

    public void setFailoverZigZagSpeed(final long j, @Nonnull final PlaybackSession playbackSession) {
        ScheduledExecutorService scheduledExecutorService = this.mExecutor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        Preconditions.checkNotNull(playbackSession, "playbackSession");
        cancelRepeatingTask();
        if (j > 0) {
            this.mZigZagScheduledTask = this.mExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.amazon.avod.playback.OriginFailoverTestingManager.1
                @Override // java.lang.Runnable
                public void run() {
                    DLog.devf("Origin Failover Zig Zag triggered, interval is %s", Long.valueOf(j));
                    OriginFailoverTestingManager.this.manuallyTriggerFailover(FailoverType.ORIGIN, null, playbackSession);
                }
            }, j, j, TimeUnit.SECONDS);
        }
    }

    public void terminate() {
        ScheduledExecutorService scheduledExecutorService = this.mExecutor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        cancelRepeatingTask();
        this.mExecutor.shutdown();
    }

    private void cancelRepeatingTask() {
        ScheduledFuture<?> scheduledFuture = this.mZigZagScheduledTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }
}
