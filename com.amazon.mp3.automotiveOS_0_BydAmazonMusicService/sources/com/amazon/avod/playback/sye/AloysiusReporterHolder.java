package com.amazon.avod.playback.sye;

import com.amazon.avod.media.playback.reporting.aloysius.AloysiusAdIdentifierReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class AloysiusReporterHolder {
    private PlaybackMediaEventReporters mMediaEventReporters;

    AloysiusReporterHolder() {
    }

    @Nonnull
    PlaybackMediaEventReporters getMediaEventReporters() {
        Preconditions.checkState(this.mMediaEventReporters != null, "calling before reporter is set");
        return this.mMediaEventReporters;
    }

    @Nonnull
    AloysiusAdIdentifierReporter getAdIdentifierReporter() {
        Preconditions.checkState(this.mMediaEventReporters != null, "calling before reporter is set");
        return this.mMediaEventReporters.getAloysiusAdIdentifierReporter();
    }

    void setMediaEventReporters(@Nonnull PlaybackMediaEventReporters playbackMediaEventReporters) {
        this.mMediaEventReporters = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "mediaEventReporters");
    }
}
