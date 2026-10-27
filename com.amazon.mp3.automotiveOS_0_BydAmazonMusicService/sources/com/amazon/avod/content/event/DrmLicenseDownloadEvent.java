package com.amazon.avod.content.event;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DrmLicenseDownloadEvent {

    @Nullable
    private String mConsumptionId;

    @Nonnull
    private DownloadStatistics mDownloadStatistics;
    private boolean mIsCaching;
    private int mResponseCode;
    private final boolean mSuccess;

    @Nonnull
    private String mUrl;

    public DrmLicenseDownloadEvent(boolean z, @Nonnull DownloadStatistics downloadStatistics, int i, @Nonnull String str, boolean z2, @Nullable String str2) {
        this.mSuccess = z;
        this.mDownloadStatistics = (DownloadStatistics) Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
        this.mResponseCode = i;
        this.mIsCaching = z2;
        this.mUrl = str;
        this.mConsumptionId = str2;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    @Nonnull
    public String getUrl() {
        return this.mUrl;
    }

    public boolean isCaching() {
        return this.mIsCaching;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    @Nonnull
    public DownloadStatistics getStatistics() {
        return this.mDownloadStatistics;
    }

    @Nullable
    public String getConsumptionId() {
        return this.mConsumptionId;
    }
}
