package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.qos.reporter.HeuristicPlatform;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusPlayerHeuristicReporter implements MediaEventReporter, AloysiusPlayerHeuristicReporterInterface {

    @Nonnull
    private final MediaEventQueue mMediaEventQueue;

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    public AloysiusPlayerHeuristicReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlayerHeuristicReporterInterface
    public void handlePlaybackStartEvent(@Nonnull PlaybackStartEvent playbackStartEvent) {
        reportAloysiusPlayerHeuristicEvent(playbackStartEvent.getHeuristicsSettingsId(), HeuristicPlatform.Miyagi);
    }

    public void reportAloysiusPlayerHeuristicEvent(@Nullable String str, @Nonnull HeuristicPlatform heuristicPlatform) {
        AloysiusPlayerHeuristicEvent.Builder builder = new AloysiusPlayerHeuristicEvent.Builder();
        builder.treatment(str);
        builder.platform(heuristicPlatform);
        this.mMediaEventQueue.add(builder.build());
    }

    @VisibleForTesting
    static class AloysiusPlayerHeuristicEvent implements MediaEvent {

        @Nonnull
        private final HeuristicPlatform mPlatform;

        @Nullable
        private final String mTreatment;

        @Nullable
        public String getTreatment() {
            return this.mTreatment;
        }

        @Nonnull
        public HeuristicPlatform getPlatform() {
            return this.mPlatform;
        }

        private AloysiusPlayerHeuristicEvent(@Nonnull Builder builder) {
            Preconditions.checkNotNull(builder, "builder");
            this.mTreatment = builder.mTreatment;
            this.mPlatform = builder.mPlatform;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.PlayerHeuristic;
        }

        private static class Builder {
            private HeuristicPlatform mPlatform;
            private String mTreatment;

            private Builder() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder treatment(@Nullable String str) {
                this.mTreatment = str;
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder platform(@Nonnull HeuristicPlatform heuristicPlatform) {
                Preconditions.checkNotNull(heuristicPlatform, " PlayerHeuristic Platform");
                this.mPlatform = heuristicPlatform;
                return this;
            }

            @VisibleForTesting
            MediaEvent build() {
                return new AloysiusPlayerHeuristicEvent(this);
            }
        }
    }
}
