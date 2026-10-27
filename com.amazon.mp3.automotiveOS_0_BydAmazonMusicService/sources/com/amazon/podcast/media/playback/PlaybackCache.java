package com.amazon.podcast.media.playback;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class PlaybackCache {
    private final String mediaId;
    private final PlaybackData playbackData;

    public static final PlaybackCache get(final String mediaId) {
        PlaybackData playbackData = (PlaybackData) Cache.INSTANCE.data.get(mediaId);
        if (playbackData == null) {
            playbackData = new PlaybackData();
        }
        return new PlaybackCache(mediaId, playbackData);
    }

    public final long getProgressMilliseconds() {
        if (this.playbackData.progressMS == null) {
            return 0L;
        }
        return this.playbackData.progressMS.longValue();
    }

    public final long getDurationMilliseconds() {
        if (this.playbackData.durationMS == null) {
            return 0L;
        }
        return this.playbackData.durationMS.longValue();
    }

    public final int getRebufferCount() {
        if (this.playbackData.rebufferCount == null) {
            return 0;
        }
        return this.playbackData.rebufferCount.intValue();
    }

    public final float getPlaybackSpeed() {
        if (this.playbackData.playbackSpeed == null) {
            return 0.0f;
        }
        return this.playbackData.playbackSpeed.floatValue();
    }

    public final boolean isDownloaded() {
        if (this.playbackData.isDownloaded == null) {
            return false;
        }
        return this.playbackData.isDownloaded.booleanValue();
    }

    public final long getPlaybackStartOffsetMilliseconds() {
        if (this.playbackData.playbackStartOffsetMS == null) {
            return 0L;
        }
        return this.playbackData.playbackStartOffsetMS.longValue();
    }

    public final long getPlaybackStartedAtTimestamp() {
        if (this.playbackData.playbackStartedAtTimestampMS == null) {
            return 0L;
        }
        return this.playbackData.playbackStartedAtTimestampMS.longValue();
    }

    public final long getInitialPlaybackDelayMilliseconds() {
        if (this.playbackData.playbackStartedAtTimestampMS == null || this.playbackData.playbackRequestedAtTimestampMS == null) {
            return 0L;
        }
        return this.playbackData.playbackStartedAtTimestampMS.longValue() - this.playbackData.playbackRequestedAtTimestampMS.longValue();
    }

    public final long getRebufferDurationMilliseconds() {
        if (this.playbackData.rebufferDurationMS == null) {
            return 0L;
        }
        return this.playbackData.rebufferDurationMS.longValue();
    }

    public final String getIngressSource() {
        return this.playbackData.ingressSource;
    }

    public final String getAudioUri() {
        return this.playbackData.audioUri;
    }

    public final String getErrorException() {
        return this.playbackData.errorException;
    }

    public final String getPlaybackEngineType() {
        return this.playbackData.playbackEngineType;
    }

    public final long getInitialPlaybackDelayFromPlayerMilliseconds() {
        if (this.playbackData.initialPlaybackDelayFromPlayer == null) {
            return 0L;
        }
        return this.playbackData.initialPlaybackDelayFromPlayer.longValue();
    }

    final void updateDuration(final Long durationMS) {
        this.playbackData.durationMS = durationMS;
        updateCache();
    }

    final void addRebufferCount() {
        Integer num = this.playbackData.rebufferCount;
        this.playbackData.rebufferCount = Integer.valueOf(num != null ? 1 + num.intValue() : 1);
        updateCache();
    }

    final void resetRebufferCount() {
        this.playbackData.rebufferCount = 0;
        updateCache();
    }

    public final void updateProgress(final Long progressMs) {
        this.playbackData.progressMS = progressMs;
        updateCache();
    }

    final void updatePlaybackSpeed(final Float playbackSpeed) {
        this.playbackData.playbackSpeed = playbackSpeed;
        updateCache();
    }

    final void updateIsDownloaded(final Boolean isDownloaded) {
        this.playbackData.isDownloaded = isDownloaded;
        updateCache();
    }

    final void updatePlaybackStartOffset(final Long offsetMilliseconds) {
        this.playbackData.playbackStartOffsetMS = offsetMilliseconds;
        updateCache();
    }

    final void updatePlaybackRequestedAtTimstampMS(final Long playbackRequestedAtTimestampMS) {
        this.playbackData.playbackRequestedAtTimestampMS = playbackRequestedAtTimestampMS;
        updateCache();
    }

    final void updateInitialPlaybackDelayFromPlayer(final Long initialPlaybackDelayFromPlayer) {
        this.playbackData.initialPlaybackDelayFromPlayer = initialPlaybackDelayFromPlayer;
        updateCache();
    }

    final void updatePlayButtonTapAtMS(final Long playButtonTapAtTimestampMS) {
        this.playbackData.playButtonTapAtTimestampMS = playButtonTapAtTimestampMS;
        updateCache();
    }

    final void updatePlaybackStartedAt(final Long timestampMilliseconds) {
        this.playbackData.playbackStartedAtTimestampMS = timestampMilliseconds;
        updateCache();
    }

    final void updateRebufferDurationMilliseconds(final Long rebufferDurationMilliseconds) {
        this.playbackData.rebufferDurationMS = rebufferDurationMilliseconds;
        updateCache();
    }

    final void resetRebufferDurationMillisecond() {
        this.playbackData.rebufferDurationMS = 0L;
        updateCache();
    }

    final void updateIngressSource(final String ingressSource) {
        this.playbackData.ingressSource = ingressSource;
        updateCache();
    }

    final void updateAudioUri(final String audioUri) {
        this.playbackData.audioUri = audioUri;
        updateCache();
    }

    final void updateErrorException(final String errorException) {
        this.playbackData.errorException = errorException;
        updateCache();
    }

    final void updatePlaybackEngineType(final String playbackEngineType) {
        this.playbackData.playbackEngineType = playbackEngineType;
        updateCache();
    }

    private PlaybackCache(final String mediaId, final PlaybackData playbackData) {
        this.mediaId = mediaId;
        this.playbackData = playbackData;
    }

    private synchronized void updateCache() {
        Cache.INSTANCE.data.put(this.mediaId, this.playbackData);
    }

    private enum Cache {
        INSTANCE;

        private final Map<String, PlaybackData> data = new HashMap();

        Cache() {
        }
    }

    private static final class PlaybackData {
        private String audioUri;
        private Long durationMS;
        private String errorException;
        private String ingressSource;
        private Long initialPlaybackDelayFromPlayer;
        private Boolean isDownloaded;
        private Long playButtonTapAtTimestampMS;
        private String playbackEngineType;
        private Long playbackRequestedAtTimestampMS;
        private Float playbackSpeed;
        private Long playbackStartOffsetMS;
        private Long playbackStartedAtTimestampMS;
        private Long progressMS;
        private Integer rebufferCount;
        private Long rebufferDurationMS;

        private PlaybackData() {
        }
    }
}
