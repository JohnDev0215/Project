package com.amazon.avod.drm.event;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import java.net.URL;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultLicenseAcquisitionEventReporter implements LicenseAcquisitionEventReporter {
    public static final DefaultLicenseAcquisitionEventReporter INSTANCE = new DefaultLicenseAcquisitionEventReporter();

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportLicensingError(String str, String str2) {
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportLicensingError(String str, String str2, URL url) {
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportLicensingStart(boolean z, String str) {
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportLicensingSuccess(String str) {
    }

    @Override // com.amazon.avod.drm.event.LicenseAcquisitionEventReporter
    public void reportQOS2LicenseAcquisition(boolean z, @Nonnull DownloadStatistics downloadStatistics, int i, boolean z2, @Nullable String str, @Nonnull String str2) {
    }

    private DefaultLicenseAcquisitionEventReporter() {
    }
}
