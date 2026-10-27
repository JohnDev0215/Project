package com.amazon.music.media.playback.player;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.metrics.PlaybackMetricsHandler;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.media.playback.util.StrictMode;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class PlayRequest {
    private static final AtomicInteger ID_MAKER = new AtomicInteger();
    private long amountBuffered;
    private final MediaItem mediaItem;
    private MediaLink mediaLink;
    private PlaybackMetricsHandler metricsHandler;
    private final MTSMetricsSender sender;
    private PlayRequestStatus status;
    private boolean autoSend = true;
    private final String id = "PlayRequest-" + ID_MAKER.addAndGet(1);
    private final PositionTracker positionTracker = new PositionTracker();

    public PlayRequest(@NonNull MediaItem mediaItem, @NonNull PlayRequestStatus playRequestStatus, @NonNull MTSMetricsSender mTSMetricsSender) {
        this.mediaItem = (MediaItem) Validate.notNull(mediaItem);
        this.status = (PlayRequestStatus) Validate.notNull(playRequestStatus);
        this.sender = (MTSMetricsSender) Validate.notNull(mTSMetricsSender);
        PlaybackMetricsHandler playbackMetricsHandler = new PlaybackMetricsHandler(getMediaItem());
        this.metricsHandler = playbackMetricsHandler;
        playbackMetricsHandler.setSender(mTSMetricsSender);
    }

    public MTSMetricsSender getMetricsSender() {
        return this.sender;
    }

    public void setMediaLink(MediaLink mediaLink) {
        if (this.mediaLink != mediaLink) {
            this.mediaLink = mediaLink;
        }
    }

    public MediaLink getMediaLink() {
        return this.mediaLink;
    }

    public long getAmountBuffered() {
        return this.amountBuffered;
    }

    public void setAmountBuffered(long j) {
        this.amountBuffered = j;
    }

    public void setPlayRequestStatus(@NonNull PlayRequestStatus playRequestStatus, long j) {
        if (this.status == PlayRequestStatus.TERMINATED && playRequestStatus != PlayRequestStatus.NOT_STARTED) {
            StrictMode.crashIfStrict(this + " is already terminated - cannot set to " + playRequestStatus + " - to restart, set to NOT_STARTED first.");
            return;
        }
        this.status = playRequestStatus;
        if (j >= 0) {
            if (playRequestStatus == PlayRequestStatus.RENDERING) {
                this.positionTracker.start(j);
                return;
            } else {
                this.positionTracker.pause(j);
                return;
            }
        }
        if (playRequestStatus == PlayRequestStatus.RENDERING) {
            this.positionTracker.start();
        } else {
            this.positionTracker.pause();
        }
    }

    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public PlaybackMetricsHandler getMetricsHandler() {
        return this.metricsHandler;
    }

    public PositionTracker getPositionTracker() {
        return this.positionTracker;
    }

    public PlayRequestStatus getPlayRequestStatus() {
        return this.status;
    }

    public void resetMetrics() {
        PlaybackMetricsHandler playbackMetricsHandler = new PlaybackMetricsHandler(getMediaItem());
        this.metricsHandler = playbackMetricsHandler;
        playbackMetricsHandler.setSender(this.autoSend ? this.sender : null);
    }

    public void setAutoSend(boolean z) {
        this.autoSend = z;
        this.metricsHandler.setSender(z ? this.sender : null);
    }

    public String toString() {
        return "PlayRequest{id='" + this.id + "', mediaItem=" + this.mediaItem + ", status=" + this.status + '}';
    }
}
