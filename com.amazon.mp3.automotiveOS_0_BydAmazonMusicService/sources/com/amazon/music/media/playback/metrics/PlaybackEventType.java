package com.amazon.music.media.playback.metrics;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.util.StrictMode;

/* JADX INFO: loaded from: classes4.dex */
public enum PlaybackEventType implements MetricsEventType<PlaybackEvent> {
    EVENT_MEDIA_ITEM_KNOWN { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.1
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public void onAddingTo(@NonNull PlaybackEvent playbackEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo(playbackEvent, metricsInfo);
            metricsInfo.set(PlaybackEvent.PROPERTY_MEDIA_ITEM, playbackEvent.getMediaItem());
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public void validate(@NonNull PlaybackEvent playbackEvent) {
            super.validate(playbackEvent);
            if (playbackEvent.getMediaItem() != null) {
                return;
            }
            throw new IllegalArgumentException("MediaItem is null for " + playbackEvent);
        }
    },
    EVENT_MEDIA_LINK_SELECTED { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.2
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public void onAddingTo(@NonNull PlaybackEvent playbackEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo(playbackEvent, metricsInfo);
            metricsInfo.set(PlaybackEvent.PROPERTY_MEDIA_LINK, playbackEvent.getMediaLink());
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public void validate(@NonNull PlaybackEvent playbackEvent) {
            super.validate(playbackEvent);
            if (playbackEvent.getMediaLink() != null) {
                return;
            }
            throw new IllegalArgumentException("MediaLink is null for " + playbackEvent);
        }
    },
    EVENT_PLAYBACK_EXPECTED { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.3
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public void validate(@NonNull PlaybackEvent playbackEvent) {
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public void onAddingTo(@NonNull PlaybackEvent playbackEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo(playbackEvent, metricsInfo);
            metricsInfo.set(PlaybackEvent.PROPERTY_PLAYBACK_EXPECTED, Boolean.TRUE);
        }
    },
    EVENT_PLAYBACK_NOT_EXPECTED { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.4
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public void validate(@NonNull PlaybackEvent playbackEvent) {
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public void onAddingTo(@NonNull PlaybackEvent playbackEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo(playbackEvent, metricsInfo);
            metricsInfo.set(PlaybackEvent.PROPERTY_PLAYBACK_EXPECTED, Boolean.FALSE);
        }
    },
    EVENT_PLAYBACK_LOADING { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.5
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public /* bridge */ /* synthetic */ void onAddingTo(@NonNull MetricsEvent metricsEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo((PlaybackEvent) metricsEvent, metricsInfo);
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public /* bridge */ /* synthetic */ void validate(@NonNull MetricsEvent metricsEvent) {
            super.validate((PlaybackEvent) metricsEvent);
        }
    },
    EVENT_PLAYBACK_LOADED,
    EVENT_PLAYBACK_ERROR_LOADING { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.6
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public /* bridge */ /* synthetic */ void onAddingTo(@NonNull MetricsEvent metricsEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo((PlaybackEvent) metricsEvent, metricsInfo);
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public void validate(@NonNull PlaybackEvent playbackEvent) {
            if (playbackEvent.getPlaybackException() == null) {
                StrictMode.crashIfStrict("Event for EVENT_PLAYBACK_ERROR_LOADING does not contain a PlaybackException: " + playbackEvent);
            }
        }
    },
    EVENT_PLAYBACK_PREPARING,
    EVENT_PLAYBACK_PREPARED,
    EVENT_PLAYBACK_ERROR_PREPARING { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.7
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public /* bridge */ /* synthetic */ void onAddingTo(@NonNull MetricsEvent metricsEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo((PlaybackEvent) metricsEvent, metricsInfo);
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public void validate(@NonNull PlaybackEvent playbackEvent) {
            if (playbackEvent.getPlaybackException() == null) {
                StrictMode.crashIfStrict("Event for EVENT_PLAYBACK_ERROR_PREPARING does not contain a PlaybackException: " + playbackEvent);
            }
        }
    },
    EVENT_PLAYBACK_PLAYING { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.8
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public /* bridge */ /* synthetic */ void validate(@NonNull MetricsEvent metricsEvent) {
            super.validate((PlaybackEvent) metricsEvent);
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public void onAddingTo(@NonNull PlaybackEvent playbackEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo(playbackEvent, metricsInfo);
            metricsInfo.set(PlaybackEvent.PROPERTY_PLAYBACK_EXPECTED, Boolean.FALSE);
        }
    },
    EVENT_PLAYBACK_ERROR_PLAYING,
    EVENT_PLAYBACK_PAUSED,
    EVENT_PLAYBACK_SEEK_START,
    EVENT_PLAYBACK_SEEK_END,
    EVENT_PLAYBACK_ERROR_SEEKING,
    EVENT_PLAYBACK_ERROR_BUFFERING,
    EVENT_PLAYBACK_TERMINATED { // from class: com.amazon.music.media.playback.metrics.PlaybackEventType.9
        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType
        public /* bridge */ /* synthetic */ void validate(@NonNull MetricsEvent metricsEvent) {
            super.validate((PlaybackEvent) metricsEvent);
        }

        @Override // com.amazon.music.media.playback.metrics.PlaybackEventType, com.amazon.music.media.playback.metrics.MetricsEventType
        public void onAddingTo(@NonNull PlaybackEvent playbackEvent, @NonNull MetricsInfo metricsInfo) {
            super.onAddingTo(playbackEvent, metricsInfo);
            metricsInfo.set(PlaybackEvent.PROPERTY_PLAYBACK_EXPECTED, Boolean.FALSE);
        }
    };

    @Override // 
    public void validate(@NonNull PlaybackEvent playbackEvent) {
    }

    @Override // com.amazon.music.media.playback.metrics.MetricsEventType
    public Class<PlaybackEvent> getMetricsEventClass() {
        return PlaybackEvent.class;
    }

    @Override // com.amazon.music.media.playback.metrics.MetricsEventType
    public void onAddingTo(@NonNull PlaybackEvent playbackEvent, @NonNull MetricsInfo metricsInfo) {
        validate(playbackEvent);
    }
}
