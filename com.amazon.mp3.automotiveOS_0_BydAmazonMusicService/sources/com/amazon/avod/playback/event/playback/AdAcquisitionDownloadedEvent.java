package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdAcquisitionDownloadedEvent {

    @Nullable
    private final String mConsumptionId;

    @Nonnull
    private final String mRequestUrl;

    @Nullable
    private final DownloadStatistics mStatistics;

    public AdAcquisitionDownloadedEvent(@Nonnull String str, @Nullable DownloadStatistics downloadStatistics, @Nullable String str2) {
        this.mRequestUrl = (String) Preconditions.checkNotNull(str, "requestUrl");
        this.mStatistics = downloadStatistics;
        this.mConsumptionId = str2;
    }

    @Nonnull
    public String getRequestUrl() {
        return this.mRequestUrl;
    }

    @Nullable
    public DownloadStatistics getStatistics() {
        return this.mStatistics;
    }

    @Nullable
    public String getConsumptionId() {
        return this.mConsumptionId;
    }
}
