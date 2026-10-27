package com.amazon.avod.drm.event;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import java.net.URL;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface LicenseAcquisitionEventReporter {
    void reportLicensingError(@Nonnull String str, @Nonnull String str2);

    void reportLicensingError(@Nonnull String str, @Nonnull String str2, @Nullable URL url);

    void reportLicensingStart(boolean z, String str);

    void reportLicensingSuccess(@Nonnull String str);

    void reportQOS2LicenseAcquisition(boolean z, @Nonnull DownloadStatistics downloadStatistics, int i, boolean z2, @Nullable String str, @Nonnull String str2);
}
