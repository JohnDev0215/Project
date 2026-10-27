package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.playback.event.playback.AudioTrackChangeEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playbackclient.live.LiveLanguageTransformer;
import com.amazon.avod.qos.reporter.TrackType;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusAudioTrackReporter implements MediaEventReporter {
    private boolean mIsPlaybackStartProcessed;
    private final LiveLanguageTransformer mLiveLanguageTransformer;
    private final MediaEventQueue mMediaEventQueue;
    private final PlaybackEventTransport mPlaybackEventTransport;

    public AloysiusAudioTrackReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull PlaybackEventTransport playbackEventTransport) {
        this(mediaEventQueue, playbackEventTransport, new LiveLanguageTransformer());
    }

    @VisibleForTesting
    AloysiusAudioTrackReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull LiveLanguageTransformer liveLanguageTransformer) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mLiveLanguageTransformer = (LiveLanguageTransformer) Preconditions.checkNotNull(liveLanguageTransformer, "liveLanguageTransformer");
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        this.mPlaybackEventTransport.registerEventBusHandler(this);
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        this.mPlaybackEventTransport.unregisterEventBusHandler(this);
    }

    @Subscribe
    public synchronized void handlePlaybackStartEvent(@Nonnull PlaybackStartEvent playbackStartEvent) {
        String languageCode;
        if (!this.mIsPlaybackStartProcessed) {
            TrackType trackType = TrackType.Audio;
            String consumptionId = playbackStartEvent.getConsumptionId();
            String audioTrackId = playbackStartEvent.getAudioTrackId();
            if (playbackStartEvent.isLiveStream()) {
                languageCode = this.mLiveLanguageTransformer.transformManifestCodeToLanguageCode(playbackStartEvent.getLanguageCode());
            } else {
                languageCode = playbackStartEvent.getLanguageCode();
            }
            reportAudioTrackEvent(trackType, consumptionId, audioTrackId, languageCode);
            this.mIsPlaybackStartProcessed = true;
        }
    }

    @Subscribe
    public void handleAudioTrackChangeEvent(@Nonnull AudioTrackChangeEvent audioTrackChangeEvent) {
        reportAudioTrackEvent(TrackType.Audio, audioTrackChangeEvent.getConsumptionId(), audioTrackChangeEvent.getAudioTrackId(), audioTrackChangeEvent.getLanguageCode());
    }

    private void reportAudioTrackEvent(@Nonnull TrackType trackType, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        AudioTrackEvent.Builder builder = new AudioTrackEvent.Builder();
        builder.trackType(trackType).consumptionId(str).languageCode(str3).trackId(str2);
        this.mMediaEventQueue.add(builder.build());
    }

    @VisibleForTesting
    static class AudioTrackEvent implements MediaEvent {

        @Nonnull
        private final Track mTrack;

        private AudioTrackEvent(Builder builder) {
            Preconditions.checkNotNull(builder, "builder");
            this.mTrack = builder.mTrack;
        }

        @Nonnull
        public Track getTrack() {
            return this.mTrack;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Track;
        }

        @VisibleForTesting
        static class Builder {
            private String mConsumptionId;
            private String mLanguage;
            public Track mTrack;
            private String mTrackId;
            private TrackType mType;

            Builder() {
            }

            public Builder trackType(@Nonnull TrackType trackType) {
                this.mType = (TrackType) Preconditions.checkNotNull(trackType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                return this;
            }

            public Builder consumptionId(@Nullable String str) {
                this.mConsumptionId = str;
                return this;
            }

            public Builder languageCode(@Nullable String str) {
                this.mLanguage = str;
                return this;
            }

            public Builder trackId(@Nullable String str) {
                this.mTrackId = str;
                return this;
            }

            @VisibleForTesting
            MediaEvent build() {
                this.mTrack = new Track(this.mType, this.mConsumptionId, this.mTrackId, this.mLanguage);
                return new AudioTrackEvent(this);
            }
        }

        @VisibleForTesting
        static class Track {

            @Nullable
            private String mConsumptionId;

            @Nullable
            private String mLanguage;

            @Nullable
            private String mTrackId;

            @Nonnull
            private TrackType mType;

            private Track(@Nonnull TrackType trackType, @Nullable String str, @Nullable String str2, @Nullable String str3) {
                this.mType = (TrackType) Preconditions.checkNotNull(trackType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                this.mConsumptionId = str;
                this.mTrackId = str2;
                this.mLanguage = str3;
            }

            @Nullable
            public String getConsumptionId() {
                return this.mConsumptionId;
            }

            @JsonProperty("_type")
            @Nonnull
            public TrackType getType() {
                return this.mType;
            }

            @Nullable
            public String getTrackId() {
                return this.mTrackId;
            }

            @Nullable
            public String getLanguage() {
                return this.mLanguage;
            }
        }
    }
}
