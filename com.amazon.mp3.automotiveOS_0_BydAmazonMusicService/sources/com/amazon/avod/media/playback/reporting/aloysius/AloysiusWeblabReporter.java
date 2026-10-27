package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class AloysiusWeblabReporter implements MediaEventProducer {

    @Nonnull
    private final MediaEventQueue mMediaEventQueue;
    private final boolean mSendEventOncePerSession;
    private boolean mWeblabEventSent;

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    public AloysiusWeblabReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        this(mediaEventQueue, AloysiusConfig.getInstance().sendWeblabEventOncePerSession());
    }

    @VisibleForTesting
    AloysiusWeblabReporter(@Nonnull MediaEventQueue mediaEventQueue, boolean z) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mSendEventOncePerSession = z;
    }

    @Override // com.amazon.avod.media.events.MediaEventProducer
    public void produce() {
        if (this.mWeblabEventSent && this.mSendEventOncePerSession) {
            return;
        }
        UnmodifiableIterator<MobileWeblab> it = PlaybackWeblabs.getPlaybackWeblabs().values().iterator();
        while (it.hasNext()) {
            MobileWeblab next = it.next();
            this.mMediaEventQueue.add(createMediaEvent(next.getName(), next.getCurrentTreatment().getValue()));
        }
        this.mWeblabEventSent = true;
    }

    @VisibleForTesting
    MediaEvent createMediaEvent(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "name");
        Preconditions.checkNotNull(str2, "treatment");
        return new WeblabMediaEvent.Builder().name(str).treatment(str2).build();
    }

    @VisibleForTesting
    static class WeblabMediaEvent implements MediaEvent {

        @Nullable
        private final String mName;

        @Nullable
        private final String mTreatment;

        private WeblabMediaEvent(@Nonnull Builder builder) {
            this.mName = ((Builder) Preconditions.checkNotNull(builder, "builder")).mName;
            this.mTreatment = builder.mTreatment;
        }

        @Nonnull
        public String getName() {
            String str = this.mName;
            return str != null ? str : "";
        }

        @Nonnull
        public String getTreatment() {
            String str = this.mTreatment;
            return str != null ? str : "";
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Weblab;
        }

        @VisibleForTesting
        static class Builder {
            public String mName;
            public String mTreatment;

            Builder() {
            }

            public Builder name(String str) {
                this.mName = str;
                return this;
            }

            public Builder treatment(String str) {
                this.mTreatment = str;
                return this;
            }

            @VisibleForTesting
            MediaEvent build() {
                return new WeblabMediaEvent(this);
            }
        }
    }
}
