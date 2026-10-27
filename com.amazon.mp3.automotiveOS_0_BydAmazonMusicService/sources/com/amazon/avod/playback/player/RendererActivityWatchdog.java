package com.amazon.avod.playback.player;

import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RendererActivityWatchdog {
    private final boolean mIsRendererActivityWatchdogEnabled;
    private long mLastRenderedTimestampInNanos;
    private long mLastSystemClockTimestampInNanos;
    private final long mRendererInactivityThresholdInNanos;
    private final RendererSchemeType mSelectedRendererSchemeType;

    public RendererActivityWatchdog(@Nonnull PlaybackConfig playbackConfig, @Nonnull RendererScheme rendererScheme) {
        this(rendererScheme.getSchemeType(), playbackConfig.isRendererActivityWatchdogEnabled(), playbackConfig.getRendererInactivityThreshold().getTotalNanoSeconds());
    }

    RendererActivityWatchdog(@Nonnull RendererSchemeType rendererSchemeType, boolean z, long j) {
        this.mLastRenderedTimestampInNanos = 0L;
        this.mLastSystemClockTimestampInNanos = 0L;
        this.mSelectedRendererSchemeType = (RendererSchemeType) Preconditions.checkNotNull(rendererSchemeType, "selectedRendererSchemeType");
        this.mIsRendererActivityWatchdogEnabled = z;
        this.mRendererInactivityThresholdInNanos = j;
    }

    public void reset(long j) {
        this.mLastRenderedTimestampInNanos = j;
        this.mLastSystemClockTimestampInNanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }

    public void validateRendererTimestamp(long j) throws PlaybackException {
        if (this.mIsRendererActivityWatchdogEnabled) {
            if (Math.abs(j - this.mLastRenderedTimestampInNanos) > 0) {
                reset(j);
                return;
            }
            long jAbs = Math.abs(this.mLastSystemClockTimestampInNanos - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()));
            if (jAbs <= this.mRendererInactivityThresholdInNanos) {
                return;
            }
            Locale locale = Locale.US;
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            String str = String.format(locale, "RendererActivityWatchdog triggering player restart: System time elapsed since last renderer activity %d ms, Inactivity threshold %s ms, lastRenderedTimeInMs: %s", Long.valueOf(timeUnit.toMillis(jAbs)), Long.valueOf(timeUnit.toMillis(this.mRendererInactivityThresholdInNanos)), Long.valueOf(timeUnit.toMillis(j)));
            DLog.errorf(str);
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_DECODER_STALLED, str);
        }
    }
}
