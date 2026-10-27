package com.amazon.avod.drm.reporting;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DrmReporter {
    private final PlaybackEventReporter mEventReporter;

    public DrmReporter(@Nonnull EventReporterFactory eventReporterFactory, @Nonnull String str) {
        this(eventReporterFactory.newStandaloneEventReporter(str));
    }

    public DrmReporter(@Nonnull PlaybackEventReporter playbackEventReporter) {
        this.mEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter);
    }

    public void reportRetriedLicensingException(@Nonnull MediaException mediaException) {
        Preconditions.checkNotNull(mediaException);
        this.mEventReporter.reportError("RetriedLicensingException", mediaException.getMessage(), mediaException.toString());
    }

    public void reportDrmReset(MediaException mediaException) {
        this.mEventReporter.reportError("DrmReset", mediaException.getMessage(), null);
    }

    public void reportDrmResetFailure(MediaException mediaException) {
        this.mEventReporter.reportError("DrmResetFailure", mediaException.getMessage(), "DRM auto reset system decided to perform a reset, but drm reset call failed");
    }
}
