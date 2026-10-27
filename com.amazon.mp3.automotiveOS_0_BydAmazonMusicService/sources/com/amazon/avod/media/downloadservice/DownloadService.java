package com.amazon.avod.media.downloadservice;

import java.util.Collection;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadService {

    public interface GlobalEventListener {
        void onSecondaryDownloadsEnabledStateChange(boolean z, boolean z2);
    }

    @Nonnull
    Collection<DownloadRequest> cancelAuxiliaryDownloadRequests();

    void cancelDownloadRequest(DownloadRequest downloadRequest);

    @Nonnull
    Collection<DownloadRequest> enableSecondaryDownloads(boolean z, boolean z2);

    @Nonnull
    BandwidthStatistics getBandwidthStatistics();

    void restrictBackgroundDownloads(boolean z);

    default void setMaxPrimaryContentBufferInNanos(long j) {
    }

    void submitDownloadRequest(DownloadRequest downloadRequest, boolean z);

    void updatePrimaryContentBufferInNanos(long j);
}
