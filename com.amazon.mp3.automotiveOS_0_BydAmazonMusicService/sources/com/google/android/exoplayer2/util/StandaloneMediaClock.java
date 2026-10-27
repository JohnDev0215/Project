package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackParameters;

/* JADX INFO: loaded from: classes6.dex */
public final class StandaloneMediaClock implements MediaClock {
    private long baseElapsedMs;
    private long baseUs;
    private PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
    private boolean started;

    public void start() {
        if (this.started) {
            return;
        }
        this.baseElapsedMs = android.os.SystemClock.elapsedRealtime();
        this.started = true;
    }

    public void stop() {
        if (this.started) {
            setPositionUs(getPositionUs());
            this.started = false;
        }
    }

    public void setPositionUs(long j) {
        this.baseUs = j;
        if (this.started) {
            this.baseElapsedMs = android.os.SystemClock.elapsedRealtime();
        }
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public long getPositionUs() {
        long speedAdjustedDurationUs;
        long j = this.baseUs;
        if (!this.started) {
            return j;
        }
        long jElapsedRealtime = android.os.SystemClock.elapsedRealtime() - this.baseElapsedMs;
        PlaybackParameters playbackParameters = this.playbackParameters;
        if (playbackParameters.speed == 1.0f) {
            speedAdjustedDurationUs = C.msToUs(jElapsedRealtime);
        } else {
            speedAdjustedDurationUs = playbackParameters.getSpeedAdjustedDurationUs(jElapsedRealtime);
        }
        return j + speedAdjustedDurationUs;
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (this.started) {
            setPositionUs(getPositionUs());
        }
        this.playbackParameters = playbackParameters;
        return playbackParameters;
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }
}
