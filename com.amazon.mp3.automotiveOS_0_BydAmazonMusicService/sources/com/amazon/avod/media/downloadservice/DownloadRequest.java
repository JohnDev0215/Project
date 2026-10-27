package com.amazon.avod.media.downloadservice;

import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadRequest {
    @Nonnull
    DownloadCallback getDownloadCallback();

    @Nonnull
    DownloadRequestPriority getEnqueuePriority();

    long getExpectedDownloadSize();

    @Nonnull
    Map<String, String> getHeaders();

    @Nonnull
    DownloadListener getListener();

    @Nonnull
    DownloadRequestPriority getPriority();

    int getStatsTag();

    @Nullable
    long getTimeoutInNanos();

    @Nonnull
    String getUrl();

    void setEnqueuePriority(@Nonnull DownloadRequestPriority downloadRequestPriority);

    boolean shouldAllowHttpRedirects();

    boolean shouldDisableCompression();

    boolean shouldPersistHttpRedirects();

    boolean shouldUncompressResponse();

    boolean shouldUseHttpResponseCache();
}
