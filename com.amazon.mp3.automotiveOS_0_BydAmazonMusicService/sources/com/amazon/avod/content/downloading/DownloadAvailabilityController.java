package com.amazon.avod.content.downloading;

import com.amazon.avod.media.downloadservice.DownloadService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadAvailabilityController {
    boolean onBufferSizeChange(@Nonnull DownloadService downloadService, long j, boolean z);

    void onPrimaryDownloadStateChange(boolean z);
}
