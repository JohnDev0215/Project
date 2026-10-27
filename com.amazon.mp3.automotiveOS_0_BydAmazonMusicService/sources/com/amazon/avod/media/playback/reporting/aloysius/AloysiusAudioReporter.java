package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.framework.volume.VolumeChangeListener;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusAudioReporter implements VolumeChangeListener, MediaEventProducer {
    private final MediaEventQueue mMediaEventQueue;
    private final VolumeManager mVolumeManager;

    public AloysiusAudioReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull VolumeManager volumeManager) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mVolumeManager = (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager");
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        this.mVolumeManager.startListening(this);
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        this.mVolumeManager.stopListening();
    }

    @Override // com.amazon.avod.media.framework.volume.VolumeChangeListener
    public void onVolumeChange(int i, int i2) {
        if (i != i2) {
            addEventToQueue(i2);
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventProducer
    public void produce() {
        addEventToQueue(this.mVolumeManager.getCurrentVolume());
    }

    private void addEventToQueue(int i) {
        this.mMediaEventQueue.add(createMediaEvent(i));
    }

    private MediaEvent createMediaEvent(int i) {
        return new AudioMediaEvent.Builder().volume(getSanitizedQuantileVolume(i)).build();
    }

    private int getSanitizedQuantileVolume(int i) {
        int iFloor = (int) Math.floor((i / this.mVolumeManager.getMaximumVolume()) * 100.0f);
        if (iFloor < 0) {
            return 0;
        }
        if (iFloor > 100) {
            return 100;
        }
        return iFloor;
    }

    @VisibleForTesting
    static class AudioMediaEvent implements MediaEvent {
        private final int mVolume;

        private AudioMediaEvent(@Nonnull Builder builder) {
            this.mVolume = ((Builder) Preconditions.checkNotNull(builder, "builder")).mVolume;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Audio;
        }

        @Nonnegative
        public int getVolume() {
            return this.mVolume;
        }

        private static class Builder {
            private int mVolume;

            private Builder() {
                this.mVolume = -1;
            }

            public Builder volume(int i) {
                Preconditions.checkArgument(i >= 0 && i <= 100, "volume values must be in between 0 and 100");
                this.mVolume = i;
                return this;
            }

            public MediaEvent build() {
                Preconditions.checkArgument(this.mVolume != -1, "volume isn't defined");
                return new AudioMediaEvent(this);
            }
        }
    }
}
