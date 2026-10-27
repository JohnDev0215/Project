package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.playback.event.PlaybackContentEvent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusContentReporter implements MediaEventReporter {

    @Nonnull
    private final MediaEventQueue mMediaEventQueue;

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    public AloysiusContentReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
    }

    public void handlePlaybackContentEvent(@Nonnull PlaybackContentEvent playbackContentEvent) {
        reportAloysiusContentEvent(playbackContentEvent.getContentId(), playbackContentEvent.getPlayhead(), playbackContentEvent.getContentType());
    }

    private void reportAloysiusContentEvent(@Nonnull String str, long j, @Nullable String str2) {
        AloysiusContentEvent.Builder builder = new AloysiusContentEvent.Builder(str);
        builder.playhead(j);
        builder.contentType(str2);
        this.mMediaEventQueue.add(builder.build());
    }

    @VisibleForTesting
    static class AloysiusContentEvent implements MediaEvent {

        @Nonnull
        private final String mContentId;

        @Nullable
        private final String mContentType;

        @Nonnegative
        private final long mPlayhead;

        @Nonnull
        public String getContentId() {
            return this.mContentId;
        }

        @Nonnegative
        public long getPlayhead() {
            return this.mPlayhead;
        }

        @Nullable
        public String getContentType() {
            return this.mContentType;
        }

        private AloysiusContentEvent(@Nonnull Builder builder) {
            Preconditions.checkNotNull(builder, "builder");
            this.mContentId = builder.mContentId;
            this.mPlayhead = builder.mPlayhead;
            this.mContentType = builder.mContentType;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Content;
        }

        private static class Builder {

            @Nonnull
            private String mContentId;

            @Nullable
            private String mContentType;

            @Nonnegative
            private long mPlayhead;

            private Builder() {
                this.mContentId = "";
            }

            public Builder(@Nonnull String str) {
                this.mContentId = str;
            }

            private Builder contentId(@Nonnull String str) {
                this.mContentId = (String) Preconditions.checkNotNull(str, "ContentId");
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder contentType(@Nullable String str) {
                this.mContentType = str;
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder playhead(long j) {
                this.mPlayhead = j;
                return this;
            }

            @VisibleForTesting
            MediaEvent build() {
                return new AloysiusContentEvent(this);
            }
        }
    }
}
