package com.amazon.avod.qos.reporter;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeSmoothStreamingEventReporter {
    private final ContentManagementEventReporter mContentEventReporter;
    private final LicenseAcquisitionEventReporter mLicensingEventReporter;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final SmoothStreamingReporter mSmoothStreamingReporter;

    CompositeSmoothStreamingEventReporter(PlaybackEventReporter playbackEventReporter, ContentManagementEventReporter contentManagementEventReporter, LicenseAcquisitionEventReporter licenseAcquisitionEventReporter, SmoothStreamingReporter smoothStreamingReporter) {
        this.mPlaybackEventReporter = playbackEventReporter;
        this.mContentEventReporter = (ContentManagementEventReporter) Preconditions.checkNotNull(contentManagementEventReporter);
        this.mLicensingEventReporter = (LicenseAcquisitionEventReporter) Preconditions.checkNotNull(licenseAcquisitionEventReporter);
        this.mSmoothStreamingReporter = (SmoothStreamingReporter) Preconditions.checkNotNull(smoothStreamingReporter);
    }

    @Nullable
    public PlaybackEventReporter getPlaybackReporter() {
        return this.mPlaybackEventReporter;
    }

    @Nonnull
    public ContentManagementEventReporter getContentReporter() {
        return this.mContentEventReporter;
    }

    @Nonnull
    public LicenseAcquisitionEventReporter getLicensingReporter() {
        return this.mLicensingEventReporter;
    }

    @Nonnull
    public SmoothStreamingReporter getSmoothStreamingReporter() {
        return this.mSmoothStreamingReporter;
    }
}
