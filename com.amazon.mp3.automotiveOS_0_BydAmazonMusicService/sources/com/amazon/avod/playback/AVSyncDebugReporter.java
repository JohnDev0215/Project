package com.amazon.avod.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class AVSyncDebugReporter {
    private Long mFirstAudioSamplePtsInNs = null;
    private Long mFirstVideoSamplePtsInNs = null;
    private final PlaybackEventReporter mPlaybackEventReporter;

    public AVSyncDebugReporter(@Nonnull PlaybackEventReporter playbackEventReporter) {
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
    }

    public void flush() {
        DLog.devf("RendererDebug: Clearing stored sample pts values");
        this.mFirstAudioSamplePtsInNs = null;
        this.mFirstVideoSamplePtsInNs = null;
    }

    public void updateFirstAudioSamplePTS(long j) {
        if (this.mFirstAudioSamplePtsInNs != null) {
            return;
        }
        this.mFirstAudioSamplePtsInNs = Long.valueOf(j);
        reportPTSDelta();
    }

    public void updateFirstVideoSamplePTS(long j) {
        if (this.mFirstVideoSamplePtsInNs != null) {
            return;
        }
        this.mFirstVideoSamplePtsInNs = Long.valueOf(j);
        reportPTSDelta();
    }

    public void reportPTSDelta() {
        Long l = this.mFirstAudioSamplePtsInNs;
        if (l == null || this.mFirstVideoSamplePtsInNs == null) {
            return;
        }
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long jLongValue = l.longValue();
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        long jConvert = timeUnit.convert(jLongValue, timeUnit2);
        long jConvert2 = timeUnit.convert(this.mFirstVideoSamplePtsInNs.longValue(), timeUnit2);
        long jAbs = Math.abs(jConvert - jConvert2);
        String str = String.format(Locale.US, "RendererDebug Info AudioSamplePTSInMs:%d, VideoSamplePTSInMs:%d, Diff:%d", Long.valueOf(jConvert), Long.valueOf(jConvert2), Long.valueOf(jAbs));
        DLog.logf(str);
        this.mPlaybackEventReporter.reportMetric(QOSEventName.Information.toString(), "RendererDebugInformation", TimeSpan.fromMilliseconds(jAbs), str, null);
    }
}
