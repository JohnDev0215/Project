package com.amazon.avod.drm.event;

import com.amazon.avod.content.event.DrmLicenseDownloadEvent;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.google.common.base.Preconditions;
import java.net.URL;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class EventDispatchingLicenseAcquisitionEventReporter implements LicenseAcquisitionEventReporter {
    public static Factory FACTORY = new Factory();
    private final EventDispatcher mEventTransport;

    public static class Factory {
        private Factory() {
        }

        @Nonnull
        public LicenseAcquisitionEventReporter newEventReporter(@Nonnull EventDispatcher eventDispatcher) {
            return new EventDispatchingLicenseAcquisitionEventReporter(eventDispatcher);
        }
    }

    EventDispatchingLicenseAcquisitionEventReporter(@Nonnull EventDispatcher eventDispatcher) {
        this.mEventTransport = (EventDispatcher) Preconditions.checkNotNull(eventDispatcher, "dispatcher");
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportLicensingStart(boolean z, String str) {
        this.mEventTransport.postEvent(new LicenseAcquisitionStartEvent(z, str));
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportLicensingSuccess(@Nonnull String str) {
        Preconditions.checkNotNull(str, "notes");
        this.mEventTransport.postEvent(new LicenseAcquisitionEndEvent(str));
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportLicensingError(@Nonnull String str, @Nonnull String str2) {
        reportLicensingError(str, str2, null);
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportLicensingError(@Nonnull String str, @Nonnull String str2, @Nullable URL url) {
        Preconditions.checkNotNull(str, "notes");
        Preconditions.checkNotNull(str2, "errorMessage");
        this.mEventTransport.postEvent(new LicenseAcquisitionErrorEvent(str, str2, url));
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportQOS2LicenseAcquisition(boolean z, @Nonnull DownloadStatistics downloadStatistics, int i, boolean z2, @Nullable String str, @Nonnull String str2) {
        this.mEventTransport.postEvent(new DrmLicenseDownloadEvent(z, downloadStatistics, i, str2, z2, str));
    }
}
