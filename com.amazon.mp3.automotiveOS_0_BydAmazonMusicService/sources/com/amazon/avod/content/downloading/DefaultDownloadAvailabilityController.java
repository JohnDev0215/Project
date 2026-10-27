package com.amazon.avod.content.downloading;

import com.amazon.avod.media.downloadservice.DownloadService;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultDownloadAvailabilityController implements DownloadAvailabilityController {
    @Override // com.amazon.avod.content.downloading.DownloadAvailabilityController
    public void onPrimaryDownloadStateChange(boolean z) {
    }

    public boolean secondaryDownloadsAllowed() {
        return true;
    }

    @Override // com.amazon.avod.content.downloading.DownloadAvailabilityController
    public boolean onBufferSizeChange(@Nonnull DownloadService downloadService, long j, boolean z) {
        Preconditions.checkNotNull(downloadService, "downloadService");
        downloadService.enableSecondaryDownloads(secondaryDownloadsAllowed(), z);
        return secondaryDownloadsAllowed();
    }
}
