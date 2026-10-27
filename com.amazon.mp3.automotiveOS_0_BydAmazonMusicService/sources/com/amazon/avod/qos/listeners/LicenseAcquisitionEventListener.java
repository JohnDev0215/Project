package com.amazon.avod.qos.listeners;

import com.amazon.avod.drm.event.LicenseAcquisitionEndEvent;
import com.amazon.avod.drm.event.LicenseAcquisitionErrorEvent;
import com.amazon.avod.drm.event.LicenseAcquisitionStartEvent;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.qos.reporter.LicenseAcquisitionEventReporter;
import com.google.common.eventbus.Subscribe;

/* JADX INFO: loaded from: classes2.dex */
public class LicenseAcquisitionEventListener extends QosEventListenerBase {
    private LicenseAcquisitionEventReporter mReporter;

    public void bind(EventDispatcher eventDispatcher, LicenseAcquisitionEventReporter licenseAcquisitionEventReporter) {
        this.mReporter = licenseAcquisitionEventReporter;
        super.bind(eventDispatcher);
    }

    @Subscribe
    public void acquireLicenseStart(LicenseAcquisitionStartEvent licenseAcquisitionStartEvent) {
        this.mReporter.reportLicenseStart(licenseAcquisitionStartEvent.getTime(), licenseAcquisitionStartEvent.getNotes());
    }

    @Subscribe
    public void acquireLicenseEnd(LicenseAcquisitionEndEvent licenseAcquisitionEndEvent) {
        this.mReporter.reportLicenseAcquired(licenseAcquisitionEndEvent.getTime(), licenseAcquisitionEndEvent.getNotes());
    }

    @Subscribe
    public void licensingError(LicenseAcquisitionErrorEvent licenseAcquisitionErrorEvent) {
        this.mReporter.reportLicensingError(licenseAcquisitionErrorEvent.getTime(), licenseAcquisitionErrorEvent.getErrorMessage(), licenseAcquisitionErrorEvent.getNotes(), "" + licenseAcquisitionErrorEvent.getUrl());
    }
}
