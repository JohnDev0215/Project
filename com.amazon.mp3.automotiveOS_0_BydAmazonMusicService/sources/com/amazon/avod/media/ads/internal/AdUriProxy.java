package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.downloadservice.DownloadListener;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.uriproxy.EnhancedURI;
import com.amazon.avod.media.framework.uriproxy.PersistentUriProxy;
import com.amazon.avod.media.framework.uriproxy.UriProxy;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.android.AndroidVideoPlayerV2Config;
import com.amazon.avod.playback.event.playback.AdAcquisitionDownloadedEvent;
import com.amazon.avod.playback.event.playback.AdAcquisitionErroredEvent;
import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdUriProxy {
    private final AndroidVideoPlayerV2Cache mAndroidVideoPlayerV2Cache;
    private final AdsConfig mConfig;
    private final boolean mIsAndroidVideoPlayerV2CacheEnabled;
    private final PersistentUriProxy mPersistentUriProxy;

    @Nonnull
    private QOSCommunicationService mQOSCommunicationService;
    private final UriProxy mSessionUriProxy;

    public AdUriProxy(DownloadService downloadService, FileSystem fileSystem, ExecutorService executorService, @Nonnull QOSCommunicationService qOSCommunicationService, @Nonnull AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache) {
        long totalNanoSeconds = AdsConfig.getInstance().getPreloadDownloadTimeout().getTotalNanoSeconds();
        PriorityTier priorityTier = PriorityTier.AUXILIARY_REQUIRED;
        this.mSessionUriProxy = new UriProxy(downloadService, fileSystem, executorService, "adSessionCache", totalNanoSeconds, priorityTier);
        boolean zIsAndroidVideoPlayerV2CacheEnabled = AndroidVideoPlayerV2Config.getInstance().isAndroidVideoPlayerV2CacheEnabled();
        this.mIsAndroidVideoPlayerV2CacheEnabled = zIsAndroidVideoPlayerV2CacheEnabled;
        this.mPersistentUriProxy = new PersistentUriProxy(downloadService, fileSystem, executorService, "adPersistentCache", AdsConfig.getInstance().getPreloadDownloadTimeout().getTotalNanoSeconds(), (zIsAndroidVideoPlayerV2CacheEnabled || AdsConfig.getInstance().isSSAIEnabled()) ? 0L : AdsConfig.getInstance().getCacheMaxSizeBytes(), priorityTier);
        this.mConfig = AdsConfig.getInstance();
        this.mQOSCommunicationService = (QOSCommunicationService) Preconditions.checkNotNull(qOSCommunicationService, "qosCommunicationService");
        this.mAndroidVideoPlayerV2Cache = (AndroidVideoPlayerV2Cache) Preconditions.checkNotNull(androidVideoPlayerV2Cache, "exoPlayerCache");
    }

    public URI lookup(@Nullable EnhancedURI enhancedURI, boolean z) {
        if (enhancedURI == null) {
            return null;
        }
        URI uriLookup = this.mSessionUriProxy.lookup(enhancedURI);
        return (uriLookup == null && this.mConfig.getPrimePreRollCacheEnabled() && z) ? this.mPersistentUriProxy.lookup(enhancedURI) : uriLookup;
    }

    public boolean contains(EnhancedURI enhancedURI) {
        return this.mSessionUriProxy.contains(enhancedURI) || (this.mConfig.getPrimePreRollCacheEnabled() && this.mPersistentUriProxy.contains(enhancedURI));
    }

    public void load(EnhancedURI enhancedURI, TimeSpan timeSpan, int i, boolean z, String str) {
        PriorityTier priorityTier = timeSpan.isZero() ? PriorityTier.PRIMARY : PriorityTier.AUXILIARY_REQUIRED;
        if (this.mConfig.getPrimePreRollCacheEnabled() && z) {
            this.mPersistentUriProxy.load(enhancedURI, timeSpan.getTotalNanoSeconds(), i, z, priorityTier, new AdUriProxyDownloadListener(str));
        } else {
            this.mSessionUriProxy.load(enhancedURI, timeSpan.getTotalNanoSeconds(), i, z, priorityTier, new AdUriProxyDownloadListener(str));
        }
    }

    public void delayLoad(EnhancedURI enhancedURI, int i, boolean z, String str) {
        if (!this.mConfig.getPrimePreRollCacheEnabled() || !z || enhancedURI == null || i <= 0) {
            return;
        }
        this.mPersistentUriProxy.load(enhancedURI, Long.MAX_VALUE, i, z, PriorityTier.BACKGROUND, new AdUriProxyDownloadListener(str));
    }

    public void sync() {
        if (this.mConfig.getPrimePreRollCacheEnabled()) {
            this.mPersistentUriProxy.sync();
        }
    }

    public void cleanup() {
        if (this.mConfig.getPrimePreRollCacheEnabled()) {
            this.mPersistentUriProxy.cleanup();
        }
        this.mSessionUriProxy.cleanup();
        if (this.mIsAndroidVideoPlayerV2CacheEnabled) {
            this.mAndroidVideoPlayerV2Cache.cancelPendingRequests();
        }
    }

    public void cancel(EnhancedURI enhancedURI) {
        if (this.mConfig.getPrimePreRollCacheEnabled()) {
            this.mPersistentUriProxy.cancel(enhancedURI);
        }
        this.mSessionUriProxy.cancel(enhancedURI);
    }

    class AdUriProxyDownloadListener implements DownloadListener {
        private final String mConsumptionId;

        @Override // com.amazon.avod.media.downloadservice.DownloadListener
        public void onCancel(DownloadRequest downloadRequest) {
        }

        @Override // com.amazon.avod.media.downloadservice.DownloadListener
        public void onDownloadProgress(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics, long j) {
        }

        public AdUriProxyDownloadListener(String str) {
            this.mConsumptionId = str;
        }

        @Override // com.amazon.avod.media.downloadservice.DownloadListener
        public void onSuccess(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics) {
            AdUriProxy.this.mQOSCommunicationService.getEventTransport().postEvent(new AdAcquisitionDownloadedEvent(downloadRequest.getUrl(), downloadStatistics, this.mConsumptionId));
        }

        @Override // com.amazon.avod.media.downloadservice.DownloadListener
        public void onFailure(DownloadRequest downloadRequest, Exception exc, DownloadStatistics downloadStatistics) {
            AdUriProxy.this.mQOSCommunicationService.getEventTransport().postEvent(new AdAcquisitionErroredEvent(downloadRequest.getUrl(), exc, downloadStatistics, this.mConsumptionId));
        }
    }
}
