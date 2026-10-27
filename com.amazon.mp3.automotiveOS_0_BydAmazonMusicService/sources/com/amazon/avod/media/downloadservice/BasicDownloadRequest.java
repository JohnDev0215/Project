package com.amazon.avod.media.downloadservice;

import com.amazon.avod.content.backgrounddownload.SingleFileDownloader$$ExternalSyntheticLambda0;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BasicDownloadRequest implements DownloadRequest {
    private final DownloadCallback mDownloadCallback;
    private final DownloadListener mDownloadListener;
    private final String mDownloadUrl;
    private DownloadRequestPriority mEnqueuePriority;
    private final Map<String, String> mHeaders;
    private final DownloadRequestPriority mPriority;
    private final boolean mShouldAllowHttpRedirects;
    private final boolean mShouldDisableCompression;
    private final boolean mShouldPersistHttpRedirects;
    private final boolean mShouldUncompressResponse;
    private final boolean mShouldUseHttpResponseCache;
    private final long mTimeoutInNanos;

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public long getExpectedDownloadSize() {
        return -1L;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public int getStatsTag() {
        return 268435456;
    }

    public BasicDownloadRequest(String str, SaveCallback saveCallback, DownloadListener downloadListener, long j, DownloadRequestPriority downloadRequestPriority) {
        this(str, saveCallback, downloadListener, j, downloadRequestPriority, true, false);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BasicDownloadRequest(String str, SaveCallback saveCallback, DownloadListener downloadListener, long j, DownloadRequestPriority downloadRequestPriority, boolean z, boolean z2) {
        Map mapEmptyMap = Collections.emptyMap();
        saveCallback.getClass();
        this(str, mapEmptyMap, new SingleFileDownloader$$ExternalSyntheticLambda0(saveCallback), downloadListener, j, downloadRequestPriority, z, true, z2, true, false);
    }

    public BasicDownloadRequest(String str, Map<String, String> map, DownloadCallback downloadCallback, DownloadListener downloadListener, long j, DownloadRequestPriority downloadRequestPriority, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.mDownloadUrl = str;
        this.mHeaders = map;
        this.mDownloadCallback = downloadCallback;
        this.mDownloadListener = downloadListener;
        this.mTimeoutInNanos = j;
        this.mPriority = downloadRequestPriority;
        this.mShouldDisableCompression = z;
        this.mShouldUncompressResponse = z2;
        this.mShouldUseHttpResponseCache = z3;
        this.mShouldAllowHttpRedirects = z4;
        this.mShouldPersistHttpRedirects = z5;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public String getUrl() {
        return this.mDownloadUrl;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public DownloadCallback getDownloadCallback() {
        return this.mDownloadCallback;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public DownloadListener getListener() {
        return this.mDownloadListener;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public long getTimeoutInNanos() {
        return this.mTimeoutInNanos;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public DownloadRequestPriority getPriority() {
        return this.mPriority;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public DownloadRequestPriority getEnqueuePriority() {
        DownloadRequestPriority downloadRequestPriority = this.mEnqueuePriority;
        return downloadRequestPriority != null ? downloadRequestPriority : this.mPriority;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public void setEnqueuePriority(@Nonnull DownloadRequestPriority downloadRequestPriority) {
        this.mEnqueuePriority = (DownloadRequestPriority) Preconditions.checkNotNull(downloadRequestPriority, "enqueuePriority");
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldDisableCompression() {
        return this.mShouldDisableCompression;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldUncompressResponse() {
        return this.mShouldUncompressResponse;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldUseHttpResponseCache() {
        return this.mShouldUseHttpResponseCache;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldAllowHttpRedirects() {
        return this.mShouldAllowHttpRedirects;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldPersistHttpRedirects() {
        return this.mShouldPersistHttpRedirects;
    }
}
