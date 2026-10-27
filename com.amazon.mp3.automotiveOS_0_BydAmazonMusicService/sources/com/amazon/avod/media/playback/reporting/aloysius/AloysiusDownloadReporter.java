package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusAcquisitionReporter;
import com.amazon.avod.metrics.aloysius.AloysiusDownloadEventReporter;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusDownloadReporter extends AloysiusDownloadEventReporter {
    private static final Set<AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type> ANDROID_DOWNLOAD_ASSETS = Sets.newHashSet(AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type.Audio, AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type.DRMLicense, AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type.Manifest, AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type.TimedText, AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type.Video);
    private final MediaEventQueue mMediaEventQueue;

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    public AloysiusDownloadReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
    }

    @Override // com.amazon.avod.metrics.aloysius.AloysiusDownloadEventReporter
    public void reportDownloadEvent(@Nonnull AloysiusDownloadEventReporter.Action action, int i, @Nonnull String str) {
        this.mMediaEventQueue.add(new DownloadMediaEvent(action, i, str, ANDROID_DOWNLOAD_ASSETS));
    }

    static class DownloadMediaEvent implements MediaEvent {
        private final int mAcquired;

        @Nonnull
        private final AloysiusDownloadEventReporter.Action mAction;

        @Nonnull
        private final Set<AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type> mAssets;

        @Nonnull
        private final String mConsumptionId;

        public DownloadMediaEvent(@Nonnull AloysiusDownloadEventReporter.Action action, int i, @Nonnull String str, @Nonnull Set<AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type> set) {
            this.mAction = (AloysiusDownloadEventReporter.Action) Preconditions.checkNotNull(action, "action");
            this.mAcquired = i;
            this.mConsumptionId = (String) Preconditions.checkNotNull(str, "consumptionId");
            this.mAssets = (Set) Preconditions.checkNotNull(set, "assets");
        }

        @Nonnull
        public AloysiusDownloadEventReporter.Action getAction() {
            return this.mAction;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Download;
        }

        public int getAcquired() {
            return this.mAcquired;
        }

        @Nonnull
        public String getConsumptionId() {
            return this.mConsumptionId;
        }

        @Nonnull
        public Set<AloysiusAcquisitionReporter.AcquisitionMediaEvent.Asset.Type> getAssets() {
            return this.mAssets;
        }
    }
}
