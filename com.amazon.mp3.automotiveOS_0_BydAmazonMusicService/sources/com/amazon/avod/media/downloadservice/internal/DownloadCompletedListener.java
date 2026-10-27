package com.amazon.avod.media.downloadservice.internal;

import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
interface DownloadCompletedListener {
    void onDownloadComplete(@Nonnull DownloadRequest downloadRequest, @Nonnull DownloadStatistics downloadStatistics);
}
