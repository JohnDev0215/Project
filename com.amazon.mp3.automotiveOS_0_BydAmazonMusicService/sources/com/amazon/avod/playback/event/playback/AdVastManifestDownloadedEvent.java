package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.bolthttp.BoltException;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdVastManifestDownloadedEvent {

    @Nullable
    private final String mConsumptionId;

    @Nonnull
    private final DownloadStatistics mDownloadStatistics;

    @Nullable
    private final BoltException mException;

    @Nonnull
    private final String mUrl;

    public AdVastManifestDownloadedEvent(@Nonnull DownloadStatistics downloadStatistics, @Nullable String str, @Nonnull String str2, @Nullable BoltException boltException) {
        this.mDownloadStatistics = (DownloadStatistics) Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
        this.mConsumptionId = str;
        this.mUrl = (String) Preconditions.checkNotNull(str2, ImagesContract.URL);
        this.mException = boltException;
    }

    @Nonnull
    public DownloadStatistics getDownloadStatistics() {
        return this.mDownloadStatistics;
    }

    @Nullable
    public String getConsumptionId() {
        return this.mConsumptionId;
    }

    @Nonnull
    public String getUrl() {
        return this.mUrl;
    }

    @Nullable
    public BoltException getException() {
        return this.mException;
    }
}
