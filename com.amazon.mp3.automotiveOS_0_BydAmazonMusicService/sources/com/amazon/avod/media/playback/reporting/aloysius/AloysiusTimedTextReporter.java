package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.playback.event.playback.TimedTextChangedEvent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusTimedTextReporter implements MediaEventReporter {

    @Nullable
    private TimedTextChangedEvent mLastTimedTextSettings = null;

    @Nonnull
    private final MediaEventQueue mMediaEventQueue;

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    public AloysiusTimedTextReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
    }

    public void handleTimedTextEvent(@Nonnull TimedTextChangedEvent timedTextChangedEvent) {
        Preconditions.checkNotNull(timedTextChangedEvent, NotificationCompat.CATEGORY_EVENT);
        if (timedTextChangedEvent.equals(this.mLastTimedTextSettings)) {
            return;
        }
        this.mMediaEventQueue.add(createMediaEvent(timedTextChangedEvent.isSubtitlesEnabled(), timedTextChangedEvent.getLanguageCode(), timedTextChangedEvent.getSubtitleType(), timedTextChangedEvent.getSubtitleSubtype()));
        this.mLastTimedTextSettings = timedTextChangedEvent;
    }

    public MediaEvent createMediaEvent(boolean z, @Nullable String str, @Nullable TimedTextChangedEvent.AloysiusTimedTextType aloysiusTimedTextType, @Nullable TimedTextChangedEvent.AloysiusTimedTextSubtype aloysiusTimedTextSubtype) {
        return new TimedTextEvent.Builder().isEnabled(z).language(str).textType(aloysiusTimedTextType).textSubtype(aloysiusTimedTextSubtype).build();
    }

    static class TimedTextEvent implements MediaEvent {
        private final boolean mEnabled;

        @Nullable
        private final String mLanguage;

        @Nullable
        private final TimedTextChangedEvent.AloysiusTimedTextSubtype mTextSubtype;

        @Nullable
        private final TimedTextChangedEvent.AloysiusTimedTextType mTextType;

        @Nullable
        public String getLanguage() {
            return this.mLanguage;
        }

        @Nullable
        public TimedTextChangedEvent.AloysiusTimedTextType getTextType() {
            return this.mTextType;
        }

        @Nullable
        public TimedTextChangedEvent.AloysiusTimedTextSubtype getTextSubtype() {
            return this.mTextSubtype;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        private TimedTextEvent(@Nonnull Builder builder) {
            Preconditions.checkNotNull(builder, "builder");
            this.mEnabled = builder.mEnabled;
            this.mLanguage = builder.mLanguage;
            this.mTextType = builder.mTextType;
            this.mTextSubtype = builder.mTextSubtype;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.TimedText;
        }

        static class Builder {
            private boolean mEnabled;
            private String mLanguage;
            private TimedTextChangedEvent.AloysiusTimedTextSubtype mTextSubtype;
            private TimedTextChangedEvent.AloysiusTimedTextType mTextType;

            Builder() {
            }

            @Nonnull
            public Builder language(@Nullable String str) {
                this.mLanguage = str;
                return this;
            }

            @Nonnull
            public Builder textType(@Nullable TimedTextChangedEvent.AloysiusTimedTextType aloysiusTimedTextType) {
                this.mTextType = aloysiusTimedTextType;
                return this;
            }

            @Nonnull
            public Builder textSubtype(@Nullable TimedTextChangedEvent.AloysiusTimedTextSubtype aloysiusTimedTextSubtype) {
                this.mTextSubtype = aloysiusTimedTextSubtype;
                return this;
            }

            public Builder isEnabled(boolean z) {
                this.mEnabled = z;
                return this;
            }

            MediaEvent build() {
                return new TimedTextEvent(this);
            }
        }
    }
}
