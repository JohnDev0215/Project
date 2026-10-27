package com.amazon.avod.media.ads.internal;

import android.content.Context;
import android.net.Uri;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.uriproxy.EnhancedURI;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.android.AndroidVideoPlayerV2Config;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.event.playback.AdAcquisitionDownloadedEvent;
import com.amazon.avod.playback.event.playback.AdAcquisitionErroredEvent;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class AndroidVideoPlayerV2Cache {
    private final AndroidVideoPlayerV2Config mConfig;
    private final Context mContext;
    private ScheduledExecutorService mExecutor;
    private boolean mIsInitialized;
    private final QOSCommunicationService mQOSCommunicationService;
    private final SimpleCache mSimpleCache;
    private final boolean mUseEnhancedUriAsCacheKey;

    public AndroidVideoPlayerV2Cache(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this.mIsInitialized = false;
        Context appContext = mediaSystemSharedContext.getAppContext();
        this.mContext = appContext;
        this.mQOSCommunicationService = qOSCommunicationService;
        AndroidVideoPlayerV2Config androidVideoPlayerV2Config = AndroidVideoPlayerV2Config.getInstance();
        this.mConfig = androidVideoPlayerV2Config;
        this.mUseEnhancedUriAsCacheKey = androidVideoPlayerV2Config.shouldUseEnhancedUriAsCacheKey();
        createExecutor();
        this.mSimpleCache = new SimpleCache(new File(appContext.getCacheDir(), "AdsCacheV2"), new LeastRecentlyUsedCacheEvictor(DataUnit.MEGABYTES.toBytes(androidVideoPlayerV2Config.getAndroidVideoPlayerV2CacheSizeMb())));
        if (androidVideoPlayerV2Config.isAndroidVideoPlayerV2CacheEnabled()) {
            this.mIsInitialized = true;
        } else {
            clearCache();
        }
    }

    private synchronized void clearCache() {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AndroidVideoPlayerV2Cache$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$clearCache$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clearCache$0() {
        try {
            DLog.warnf("AndroidVideoPlayerV2Cache deleting all cached content.");
            Iterator<String> it = this.mSimpleCache.getKeys().iterator();
            while (it.hasNext()) {
                CacheUtil.remove(this.mSimpleCache, it.next());
            }
        } catch (Exception e) {
            DLog.errorf("AndroidVideoPlayerV2Cache unexpected error clearing cache %s", e);
        }
    }

    @Nullable
    public synchronized SimpleCache getSimpleCache() {
        if (!this.mIsInitialized) {
            DLog.warnf("AndroidVideoPlayerV2Cache cannot call getSimpleCache() before initialize, returning null");
            return null;
        }
        return this.mSimpleCache;
    }

    public synchronized void cache(@Nonnull final EnhancedURI enhancedURI, @Nullable final String str) {
        Preconditions.checkNotNull(enhancedURI, "enhancedURI");
        if (!this.mIsInitialized) {
            DLog.warnf("AndroidVideoPlayerV2Cache cannot call cache() before initialize, returning early");
            return;
        }
        DLog.logf("AndroidVideoPlayerV2Cache caching uri: %s", enhancedURI);
        final DataSpec dataSpec = new DataSpec(generateUri(enhancedURI), 0L, -1L, generateCacheKey(enhancedURI));
        Context context = this.mContext;
        final DefaultDataSource defaultDataSourceCreateDataSource = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "AmazonVideo")).createDataSource();
        final CacheUtil.CachingCounters cachingCounters = new CacheUtil.CachingCounters();
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AndroidVideoPlayerV2Cache$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$cache$1(dataSpec, defaultDataSourceCreateDataSource, cachingCounters, enhancedURI, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$cache$1(DataSpec dataSpec, DataSource dataSource, CacheUtil.CachingCounters cachingCounters, EnhancedURI enhancedURI, String str) {
        try {
            CacheUtil.cache(dataSpec, this.mSimpleCache, dataSource, cachingCounters);
            DLog.logf("AndroidVideoPlayerV2Cache caching successful for uri: %s", enhancedURI);
            this.mQOSCommunicationService.getEventTransport().postEvent(new AdAcquisitionDownloadedEvent(enhancedURI.getURI().toString(), DownloadStatistics.newBuilder().onBytesProcessed(cachingCounters.newlyCachedBytes).build(), str));
        } catch (InterruptedException e) {
            DLog.warnf("AndroidVideoPlayerV2Cache caching interrupted for uri: %s cause: %s", enhancedURI, e);
        } catch (Exception e2) {
            DLog.warnf("AndroidVideoPlayerV2Cache caching failed for uri: %s cause: %s", enhancedURI, e2);
            this.mQOSCommunicationService.getEventTransport().postEvent(new AdAcquisitionErroredEvent(enhancedURI.getURI().toString(), e2, null, str));
        }
    }

    @Nonnull
    public synchronized CacheStatus getCacheStatus(@Nullable EnhancedURI enhancedURI) {
        CacheStatus cacheStatus;
        if (!this.mIsInitialized) {
            DLog.warnf("AndroidVideoPlayerV2Cache cannot call getCacheStatus() before initialize, returning UNCACHED");
            return CacheStatus.UNCACHED;
        }
        if (enhancedURI == null) {
            CacheStatus cacheStatus2 = CacheStatus.UNCACHED;
            DLog.logf("AndroidVideoPlayerV2Cache uri: null cacheStatus: %s", cacheStatus2);
            return cacheStatus2;
        }
        DataSpec dataSpec = new DataSpec(generateUri(enhancedURI), 0L, -1L, generateCacheKey(enhancedURI));
        CacheUtil.CachingCounters cachingCounters = new CacheUtil.CachingCounters();
        CacheUtil.getCached(dataSpec, this.mSimpleCache, cachingCounters);
        float f = (cachingCounters.totalCachedBytes() / cachingCounters.contentLength) * 100.0f;
        if (cachingCounters.contentLength == cachingCounters.totalCachedBytes()) {
            cacheStatus = CacheStatus.FULLY_CACHED;
        } else if (cachingCounters.totalCachedBytes() == 0) {
            cacheStatus = CacheStatus.UNCACHED;
        } else if (f > 100.0f) {
            String str = String.format(Locale.US, "Percent cached %d/%d (totalCachedBytes/contentLength): %.2f is greater than 100", Long.valueOf(cachingCounters.totalCachedBytes()), Long.valueOf(cachingCounters.contentLength), Float.valueOf(f));
            DLog.warnf(str);
            this.mQOSCommunicationService.getEventTransport().postEvent(new MetricsBuilder().eventType(QOSEventName.Information.toString()).eventSubtype("CachedPercentMismatch").errorSeverity(4).note(str));
            cacheStatus = CacheStatus.FULLY_CACHED;
        } else {
            cacheStatus = new CacheStatus(f);
        }
        DataUnit dataUnit = DataUnit.BYTES;
        DLog.logf("AndroidVideoPlayerV2Cache uri: %s cacheStatus: %s cached/total: %.2f/%.2fMB percentCached: %.2f", enhancedURI, cacheStatus, Float.valueOf(dataUnit.toMegaBytes(cachingCounters.totalCachedBytes())), Float.valueOf(dataUnit.toMegaBytes(cachingCounters.contentLength >= 0 ? cachingCounters.contentLength : 0.0f)), Float.valueOf(cacheStatus.getPercentCached()));
        return cacheStatus;
    }

    public synchronized void cancelPendingRequests() {
        if (!this.mIsInitialized) {
            DLog.warnf("AndroidVideoPlayerV2Cache cannot call cancelPendingRequests() before initialize, returning early");
        } else {
            this.mExecutor.shutdownNow();
            createExecutor();
        }
    }

    @Nonnull
    private Uri generateUri(@Nonnull EnhancedURI enhancedURI) {
        return Uri.parse(enhancedURI.getURI().toString());
    }

    @Nonnull
    private String generateCacheKey(@Nonnull EnhancedURI enhancedURI) {
        if (this.mUseEnhancedUriAsCacheKey) {
            return enhancedURI.getIdentifier();
        }
        return enhancedURI.getURI().toString();
    }

    private synchronized void createExecutor() {
        this.mExecutor = ScheduledExecutorBuilder.newBuilder("AndroidVideoPlayerV2Cache", new String[0]).withFixedThreadPoolSize(this.mConfig.getAndroidVideoPlayerV2MaxConcurrentRequests()).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();
    }
}
