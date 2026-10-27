package com.amazon.avod.media.contentcache.internal;

import android.content.Context;
import android.net.http.HttpResponseCache;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.contentcache.CacheRequest;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.contentcache.db.DBCachedContentPersistence;
import com.amazon.avod.media.contentcache.internal.CachedContentPersistence;
import com.amazon.avod.media.contentcache.internal.admittance.AdmittancePolicy;
import com.amazon.avod.media.contentcache.internal.admittance.AdmittancePolicyResult;
import com.amazon.avod.media.contentcache.internal.admittance.BackgroundCacheRequestAdmittancePolicy;
import com.amazon.avod.media.contentcache.internal.admittance.BooleanAndAdmittancePolicy;
import com.amazon.avod.media.contentcache.internal.admittance.DiskSpaceMonitoringAdmittancePolicy;
import com.amazon.avod.media.contentcache.internal.admittance.ForegroundApplicationAdmittancePolicy;
import com.amazon.avod.media.contentcache.internal.admittance.LiveCacheAdmittancePolicy;
import com.amazon.avod.media.contentcache.internal.admittance.RecordCountingAdmittancePolicy;
import com.amazon.avod.media.contentcache.internal.executor.DownloadExecutor;
import com.amazon.avod.media.contentcache.internal.executor.DownloadQueue;
import com.amazon.avod.media.contentcache.internal.queueing.DefaultQueueingPolicy;
import com.amazon.avod.media.contentcache.internal.queueing.QueueingPolicy;
import com.amazon.avod.media.contentcache.internal.retry.AttemptBasedRetryPolicy;
import com.amazon.avod.media.contentcache.internal.retry.RetryPolicy;
import com.amazon.avod.media.downloadservice.internal.DownloadServiceConfig;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.perf.DownloadMarkers;
import com.amazon.avod.perf.PlaybackExtras;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.perf.config.PlaybackPerfConfig;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheEviction;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheStatus;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import com.amazon.avod.qos.reporter.internal.StandaloneEventReporter;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.base.Ticker;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PrioritizingVideoCacheManager implements VideoCacheManager, DownloadQueue {
    private final AdmittancePolicy mAdmittancePolicy;
    private final Context mAppContext;
    private final BaseDrmSystem mBaseDrmSystem;
    private final AtomicBoolean mCacheDelayedTaskSet;
    private final CacheConfig mConfig;
    private final ContentStore mContentStore;
    private final SimpleDateFormat mDateFormat;
    final Runnable mDelayCacheExecutionRunnable;
    private final DownloadExecutor mDownloadExecutor;
    private final ScheduledExecutorService mExecutor;
    private final boolean mIsEPrivacyConsentValidationEnabled;
    private final boolean mIsRecordExpiryValidationEnabled;
    private final JitterCacheConfig mJitterCacheConfig;
    private final PlaybackNativeLibrariesLoader mLibrariesLoader;
    private final LiveCache mLiveCache;
    final Runnable mLiveExpiredRecordCheckRunnable;
    private final ReentrantReadWriteLock mLock;
    private final CachedContentPersistence mPersistence;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final QueueingPolicy mQueueingPolicy;
    private final Random mRandom;
    private TraceKey mReadLockTraceKey;
    private final RetryPolicy mRetryPolicy;
    private final boolean mShareOfflineKeyId;
    private final boolean mShouldAllowEvictionDuringPlayback;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;
    private final StandaloneEventReporter mStandaloneEventReporter;
    private final Stopwatch mStopwatch;
    final Runnable mVodExpiredRecordCheckRunnable;
    private TraceKey mWriteLockTraceKey;

    public PrioritizingVideoCacheManager(BaseDrmSystem baseDrmSystem, Context context, ContentSessionFactory contentSessionFactory, FileSystem fileSystem, ContentStore contentStore, QoSEventManagerFactory qoSEventManagerFactory, ContentUrlFetcher contentUrlFetcher, QosEventReporterFactory qosEventReporterFactory, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, RendererSchemeController rendererSchemeController, ClientCacheConfig clientCacheConfig) {
        this(contentStore, fileSystem, baseDrmSystem, contentSessionFactory, qoSEventManagerFactory, contentUrlFetcher, rendererSchemeController, CacheConfig.INSTANCE, JitterCacheConfig.INSTANCE, new DBCachedContentPersistence(context), context, qosEventReporterFactory.newStandaloneEventReporter(String.format(Locale.US, "CacheEviction%s", UUID.randomUUID().toString())), playbackNativeLibrariesLoader, LiveCache.getInstance(), clientCacheConfig);
    }

    private PrioritizingVideoCacheManager(ContentStore contentStore, FileSystem fileSystem, BaseDrmSystem baseDrmSystem, ContentSessionFactory contentSessionFactory, QoSEventManagerFactory qoSEventManagerFactory, ContentUrlFetcher contentUrlFetcher, RendererSchemeController rendererSchemeController, CacheConfig cacheConfig, JitterCacheConfig jitterCacheConfig, CachedContentPersistence cachedContentPersistence, Context context, StandaloneEventReporter standaloneEventReporter, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull LiveCache liveCache, ClientCacheConfig clientCacheConfig) {
        DownloadExecutor downloadExecutor = new DownloadExecutor(baseDrmSystem, contentStore, contentSessionFactory, "AIV.Player.VideoCacheManager_DownloadExecutor", qoSEventManagerFactory, contentUrlFetcher, rendererSchemeController, liveCache);
        DefaultQueueingPolicy defaultQueueingPolicy = new DefaultQueueingPolicy(cacheConfig);
        BooleanAndAdmittancePolicy booleanAndAdmittancePolicy = new BooleanAndAdmittancePolicy(ImmutableList.of((LiveCacheAdmittancePolicy) new ForegroundApplicationAdmittancePolicy(context), (LiveCacheAdmittancePolicy) new RecordCountingAdmittancePolicy(cacheConfig, cachedContentPersistence), (LiveCacheAdmittancePolicy) new DiskSpaceMonitoringAdmittancePolicy(cacheConfig, cachedContentPersistence, fileSystem, clientCacheConfig), (LiveCacheAdmittancePolicy) new BackgroundCacheRequestAdmittancePolicy(jitterCacheConfig), new LiveCacheAdmittancePolicy(liveCache)));
        AttemptBasedRetryPolicy attemptBasedRetryPolicy = new AttemptBasedRetryPolicy(cacheConfig);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilder("AIV.Player.VideoCacheManager_CacheExpiryExecutor", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();
        Random random = new Random();
        Ticker tickerAndroidTicker = Tickers.androidTicker();
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;
        DownloadServiceConfig downloadServiceConfig = DownloadServiceConfig.INSTANCE;
        this(downloadExecutor, cachedContentPersistence, cacheConfig, jitterCacheConfig, contentStore, defaultQueueingPolicy, booleanAndAdmittancePolicy, attemptBasedRetryPolicy, scheduledThreadPoolExecutorBuild, standaloneEventReporter, playbackNativeLibrariesLoader, random, tickerAndroidTicker, liveCache, smoothStreamingPlaybackConfig, downloadServiceConfig.isHttpResponseCacheEnabled(), downloadServiceConfig.getHttpResponseCacheSizeMB(), downloadServiceConfig.isOkHttpForDownloadServiceEnabled(), context, PlaybackPmetMetricReporter.getInstance(), baseDrmSystem);
    }

    PrioritizingVideoCacheManager(DownloadExecutor downloadExecutor, CachedContentPersistence cachedContentPersistence, CacheConfig cacheConfig, JitterCacheConfig jitterCacheConfig, ContentStore contentStore, QueueingPolicy queueingPolicy, AdmittancePolicy admittancePolicy, RetryPolicy retryPolicy, ScheduledExecutorService scheduledExecutorService, StandaloneEventReporter standaloneEventReporter, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, Random random, @Nonnull Ticker ticker, @Nonnull LiveCache liveCache, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, boolean z, int i, boolean z2, @Nonnull Context context, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nonnull BaseDrmSystem baseDrmSystem) {
        this.mLock = new ReentrantReadWriteLock();
        this.mDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.mCacheDelayedTaskSet = new AtomicBoolean(false);
        this.mWriteLockTraceKey = null;
        this.mReadLockTraceKey = null;
        Runnable runnable = new Runnable() { // from class: com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager.1
            @Override // java.lang.Runnable
            public void run() {
                PrioritizingVideoCacheManager.this.checkForExpiredRecords(ContentSessionType.CONTENT_CACHE);
                PrioritizingVideoCacheManager.this.mExecutor.schedule(this, PrioritizingVideoCacheManager.this.mConfig.getExpirationCheckFrequency().getTotalMilliseconds(), TimeUnit.MILLISECONDS);
            }
        };
        this.mVodExpiredRecordCheckRunnable = runnable;
        Runnable runnable2 = new Runnable() { // from class: com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager.2
            @Override // java.lang.Runnable
            public void run() {
                PrioritizingVideoCacheManager.this.checkForExpiredRecords(ContentSessionType.LIVE_CACHE);
                PrioritizingVideoCacheManager.this.mExecutor.schedule(this, PrioritizingVideoCacheManager.this.mConfig.getLiveExpirationCheckFrequency().getTotalMilliseconds(), TimeUnit.MILLISECONDS);
            }
        };
        this.mLiveExpiredRecordCheckRunnable = runnable2;
        this.mDelayCacheExecutionRunnable = new Runnable() { // from class: com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager.3
            @Override // java.lang.Runnable
            public void run() {
                PrioritizingVideoCacheManager.this.mDownloadExecutor.refreshCurrentTask();
                PrioritizingVideoCacheManager.this.mCacheDelayedTaskSet.set(false);
            }
        };
        this.mPersistence = cachedContentPersistence;
        this.mQueueingPolicy = queueingPolicy;
        this.mAdmittancePolicy = admittancePolicy;
        this.mRetryPolicy = retryPolicy;
        this.mConfig = cacheConfig;
        this.mJitterCacheConfig = jitterCacheConfig;
        this.mContentStore = contentStore;
        this.mStandaloneEventReporter = standaloneEventReporter;
        this.mExecutor = scheduledExecutorService;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        scheduledExecutorService.schedule(runnable, 0L, timeUnit);
        if (cacheConfig.isLiveCachePeriodicExpiryCheckEnabled()) {
            scheduledExecutorService.schedule(runnable2, 0L, timeUnit);
        }
        this.mIsRecordExpiryValidationEnabled = cacheConfig.isRecordExpiryValidationEnabled();
        this.mIsEPrivacyConsentValidationEnabled = cacheConfig.isEPrivacyConsentValidationEnabled();
        this.mShareOfflineKeyId = cacheConfig.getShareOfflineKeyId();
        this.mShouldAllowEvictionDuringPlayback = cacheConfig.shouldAllowEvictionDuringPlayback();
        this.mLibrariesLoader = playbackNativeLibrariesLoader;
        this.mRandom = random;
        this.mStopwatch = Stopwatch.createUnstarted(ticker);
        this.mLiveCache = (LiveCache) Preconditions.checkNotNull(liveCache, "liveCache");
        this.mSmoothStreamingPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
        this.mAppContext = (Context) Preconditions.checkNotNull(context, "appContext");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mBaseDrmSystem = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "baseDrmSystem");
        DownloadExecutor downloadExecutor2 = (DownloadExecutor) Preconditions.checkNotNull(downloadExecutor, "downloadExecutor");
        this.mDownloadExecutor = downloadExecutor2;
        downloadExecutor2.start(this);
        if (!z || z2) {
            return;
        }
        installHttpResponseCache(i);
    }

    private void installHttpResponseCache(int i) {
        try {
            File file = new File(this.mAppContext.getCacheDir(), "DownloadServiceHttpResponseCache");
            long bytes = DataUnit.MEGABYTES.toBytes(i);
            HttpResponseCache.install(file, bytes);
            DLog.logf("PrioritizingVideoCacheManager HttpResponseCache successfully installed at: %s maxSizeBytes: %s", file, Long.valueOf(bytes));
        } catch (IOException e) {
            DLog.warnf("PrioritizingVideoCacheManager HttpResponseCache installation failed: %s", e);
        }
    }

    @Override // com.amazon.avod.media.contentcache.VideoCacheManager
    public void setCachingIsEnabled(boolean z) {
        acquireWriteLock();
        try {
            if (this.mLibrariesLoader.waitForInitialization()) {
                this.mDownloadExecutor.setDownloadIsEnabled(z);
            }
        } finally {
            releaseWriteLock();
        }
    }

    @Override // com.amazon.avod.media.contentcache.VideoCacheManager
    public void clearCache() {
        tryReportCacheMetrics(true);
        acquireWriteLock();
        try {
            DLog.logf("Evicting all records and content from the cache.");
            Iterator<WritableCacheRecord> it = this.mPersistence.getAllRecords().iterator();
            while (it.hasNext()) {
                this.mPersistence.deleteRecord(it.next());
            }
            this.mDownloadExecutor.refreshCurrentTask();
            this.mContentStore.deleteAllContent(ContentSessionType.CONTENT_CACHE, null);
        } finally {
            releaseWriteLock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x007c, code lost:
    
        if (validateEPrivacyConsent(r5, r9) == false) goto L21;
     */
    @Override // com.amazon.avod.media.contentcache.VideoCacheManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.avod.media.contentcache.CacheRecord queryFor(@javax.annotation.Nonnull com.amazon.avod.media.playback.VideoSpecification r9) {
        /*
            r8 = this;
            java.lang.String r0 = "VideoSpecification"
            com.google.common.base.Preconditions.checkNotNull(r9, r0)
            com.amazon.avod.perf.Profiler$TraceLevel r0 = com.amazon.avod.perf.Profiler.TraceLevel.CRITICAL
            java.lang.String r1 = "PrioritizingVideoCacheManager.queryFor"
            com.amazon.avod.perf.TraceKey r0 = com.amazon.avod.perf.Profiler.beginTrace(r0, r1)
            com.google.common.collect.ImmutableList r1 = r9.getAudioTrackIds()
            int r1 = r1.size()
            r2 = 1
            if (r1 <= r2) goto L22
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r2 = 0
            r1[r2] = r9
            java.lang.String r2 = "Trimming %s to one track."
            com.amazon.avod.util.DLog.warnf(r2, r1)
        L22:
            com.amazon.avod.media.playback.VideoSpecification r9 = r9.trimToFirstAudioTrack()
            r8.acquireReadLock()
            com.amazon.avod.media.contentcache.internal.CachedContentPersistence r1 = r8.mPersistence     // Catch: java.lang.Throwable -> L86
            java.util.List r1 = r1.getRecordsIgnoringStartTime(r9)     // Catch: java.lang.Throwable -> L86
            com.amazon.avod.media.TimeSpan r2 = r9.getStartTime()     // Catch: java.lang.Throwable -> L86
            java.util.Iterator r3 = r1.iterator()     // Catch: java.lang.Throwable -> L86
            r4 = 0
            r5 = r4
        L39:
            boolean r6 = r3.hasNext()     // Catch: java.lang.Throwable -> L86
            if (r6 == 0) goto L57
            java.lang.Object r6 = r3.next()     // Catch: java.lang.Throwable -> L86
            com.amazon.avod.media.contentcache.internal.WritableCacheRecord r6 = (com.amazon.avod.media.contentcache.internal.WritableCacheRecord) r6     // Catch: java.lang.Throwable -> L86
            boolean r7 = r6.beginsAtTimestamp(r2)     // Catch: java.lang.Throwable -> L86
            if (r7 == 0) goto L4d
            r5 = r6
            goto L57
        L4d:
            boolean r7 = r6.coversTimestamp(r2)     // Catch: java.lang.Throwable -> L86
            if (r7 != 0) goto L55
            if (r5 != 0) goto L39
        L55:
            r5 = r6
            goto L39
        L57:
            if (r5 == 0) goto L7f
            java.lang.String r2 = "PrioritizingVideoCacheManager: Successfully fetched best match cache record - %s"
            com.amazon.avod.util.DLog.devf(r2, r5)     // Catch: java.lang.Throwable -> L86
            boolean r2 = r8.mShareOfflineKeyId     // Catch: java.lang.Throwable -> L86
            resolveRecordUrlAndOfflineKeyId(r5, r1, r2)     // Catch: java.lang.Throwable -> L86
            boolean r1 = r8.mIsRecordExpiryValidationEnabled     // Catch: java.lang.Throwable -> L86
            if (r1 == 0) goto L74
            boolean r1 = r8.validateRecordExpiry(r5)     // Catch: java.lang.Throwable -> L86
            if (r1 == 0) goto L74
        L6d:
            r8.releaseReadLock()
            com.amazon.avod.perf.Profiler.endTrace(r0)
            return r4
        L74:
            boolean r1 = r8.mIsEPrivacyConsentValidationEnabled     // Catch: java.lang.Throwable -> L86
            if (r1 == 0) goto L7f
            boolean r9 = r8.validateEPrivacyConsent(r5, r9)     // Catch: java.lang.Throwable -> L86
            if (r9 != 0) goto L7f
            goto L6d
        L7f:
            r8.releaseReadLock()
            com.amazon.avod.perf.Profiler.endTrace(r0)
            return r5
        L86:
            r9 = move-exception
            r8.releaseReadLock()
            com.amazon.avod.perf.Profiler.endTrace(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager.queryFor(com.amazon.avod.media.playback.VideoSpecification):com.amazon.avod.media.contentcache.CacheRecord");
    }

    boolean validateEPrivacyConsent(@Nonnull WritableCacheRecord writableCacheRecord, @Nonnull VideoSpecification videoSpecification) {
        Preconditions.checkNotNull(writableCacheRecord, "record");
        Preconditions.checkNotNull(writableCacheRecord, "spec");
        EPrivacyConsentData ePrivacyConsent = videoSpecification.getEPrivacyConsent();
        EPrivacyConsentData ePrivacyConsent2 = writableCacheRecord.getSpecification().getEPrivacyConsent();
        return (ePrivacyConsent.getEPrivacyConsentAVL() != null ? ePrivacyConsent.getEPrivacyConsentAVL() : "").equals(ePrivacyConsent2.getEPrivacyConsentAVL() != null ? ePrivacyConsent2.getEPrivacyConsentAVL() : "") && (ePrivacyConsent.getEPrivacyConsentGVL() != null ? ePrivacyConsent.getEPrivacyConsentGVL() : "").equals(ePrivacyConsent2.getEPrivacyConsentGVL() != null ? ePrivacyConsent2.getEPrivacyConsentGVL() : "");
    }

    boolean validateRecordExpiry(@Nonnull final WritableCacheRecord writableCacheRecord) {
        final TimeSpan timeToLive;
        Preconditions.checkNotNull(writableCacheRecord, "record");
        Date downloadTime = writableCacheRecord.getDownloadTime();
        boolean z = (writableCacheRecord.getAudioVideoUrls() == null || Strings.isNullOrEmpty(writableCacheRecord.getAudioVideoUrls().getAuxCacheKey()) || !this.mConfig.shouldUseAuxCacheTTL()) ? false : true;
        if (downloadTime != null) {
            if (writableCacheRecord.getSessionType() == ContentSessionType.LIVE_CACHE) {
                timeToLive = this.mConfig.getLiveTimeToLive();
            } else if (z) {
                timeToLive = this.mConfig.getTimeToLiveForAuxillaryEnabledContents();
            } else {
                timeToLive = this.mConfig.getTimeToLive();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            String str = this.mDateFormat.format(calendar.getTime());
            if (downloadTime.getTime() < calendar.getTimeInMillis() - timeToLive.getTotalMilliseconds()) {
                final String str2 = String.format(Locale.US, "EncodeId: %s, Download time: %s, Current time: %s", writableCacheRecord.getAudioVideoUrls() != null ? String.valueOf(writableCacheRecord.getAudioVideoUrls().getEncodeId()) : "null", this.mDateFormat.format(writableCacheRecord.getDownloadTime()), str);
                this.mExecutor.schedule(new Runnable() { // from class: com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager.4
                    @Override // java.lang.Runnable
                    public void run() {
                        DLog.logf("TTL %s reached. Record getting evicted %s", timeToLive, str2);
                        PrioritizingVideoCacheManager.this.evict(writableCacheRecord.getSpecification(), WhisperCacheMetrics$CacheEviction.TTL_EXPIRE, str2);
                    }
                }, 0L, TimeUnit.MILLISECONDS);
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.avod.media.contentcache.VideoCacheManager
    public void cacheTitleList(ImmutableList<CacheRequest> immutableList) {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "PrioritizingVideoCacheManager.cacheTitleList");
        ImmutableList.Builder builder = new ImmutableList.Builder();
        UnmodifiableIterator<CacheRequest> it = immutableList.iterator();
        while (it.hasNext()) {
            builder.add(it.next().trimToFirstAudioTrack());
        }
        ImmutableList immutableListBuild = builder.build();
        acquireWriteLock();
        try {
            HashSet hashSetNewHashSet = Sets.newHashSet();
            ImmutableList.Builder builder2 = ImmutableList.builder();
            UnmodifiableIterator it2 = immutableListBuild.iterator();
            while (it2.hasNext()) {
                WritableCacheRecord writableCacheRecordPrepareRecordForRequest = prepareRecordForRequest((CacheRequest) it2.next());
                builder2.add(writableCacheRecordPrepareRecordForRequest);
                hashSetNewHashSet.add(writableCacheRecordPrepareRecordForRequest.getSpecification());
            }
            for (WritableCacheRecord writableCacheRecord : this.mPersistence.getAllRecords()) {
                if (!hashSetNewHashSet.contains(writableCacheRecord.getSpecification())) {
                    Downloadable.DownloadableState downloadableState = writableCacheRecord.getDownloadableState();
                    Downloadable.DownloadableState downloadableState2 = Downloadable.DownloadableState.TOMBSTONED;
                    if (downloadableState != downloadableState2 && writableCacheRecord.getDownloadableState() != Downloadable.DownloadableState.DELETE_IMMEDIATELY) {
                        logRecordUpdate(writableCacheRecord, downloadableState2);
                        writableCacheRecord.setState(downloadableState2);
                        builder2.add(writableCacheRecord);
                    }
                }
            }
            ImmutableList immutableListBuild2 = builder2.build();
            if (!immutableListBuild2.isEmpty()) {
                this.mPersistence.putRecordBatch(immutableListBuild2.reverse());
            }
            releaseWriteLock();
            if (!immutableListBuild2.isEmpty()) {
                this.mDownloadExecutor.refreshCurrentTask();
            }
            Profiler.endTrace(traceKeyBeginTrace);
        } catch (Throwable th) {
            releaseWriteLock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.contentcache.VideoCacheManager
    public void evict(@Nonnull VideoSpecification videoSpecification, @Nonnull WhisperCacheMetrics$CacheEviction whisperCacheMetrics$CacheEviction, @Nullable String str) {
        ContentSession contentSessionPopFromCache;
        Preconditions.checkNotNull(videoSpecification, "multiSpec");
        Preconditions.checkNotNull(whisperCacheMetrics$CacheEviction, "evictionReason");
        VideoSpecification videoSpecificationTrimToFirstAudioTrack = videoSpecification.trimToFirstAudioTrack();
        acquireWriteLock();
        try {
            Date date = null;
            for (WritableCacheRecord writableCacheRecord : this.mPersistence.getRecordsIgnoringStartTime(videoSpecificationTrimToFirstAudioTrack)) {
                if (writableCacheRecord.getSessionType() == ContentSessionType.LIVE_CACHE && (contentSessionPopFromCache = this.mLiveCache.popFromCache(writableCacheRecord.getSpecification().getTitleId())) != null) {
                    DLog.logf("LiveCache terminating content session as the record is being evicted %s", writableCacheRecord);
                    contentSessionPopFromCache.end(true);
                }
                Date downloadTime = writableCacheRecord.getDownloadTime();
                if (date == null || (downloadTime != null && date.after(downloadTime))) {
                    date = downloadTime;
                }
                writableCacheRecord.setState(Downloadable.DownloadableState.DELETE_IMMEDIATELY);
                this.mPersistence.putRecord(writableCacheRecord);
            }
            releaseWriteLock();
            this.mDownloadExecutor.refreshCurrentTask();
            MetricsBuilder metricsBuilderCreateMetricsBuilder = this.mStandaloneEventReporter.createMetricsBuilder();
            metricsBuilderCreateMetricsBuilder.asin(videoSpecification.getTitleId()).eventSubtype(whisperCacheMetrics$CacheEviction.getQosString()).note(str);
            this.mStandaloneEventReporter.reportMetric(QoSMetric.CacheStatus.getEventName().name(), metricsBuilderCreateMetricsBuilder);
            this.mPlaybackPmetMetricReporter.reportCacheEvictionTimerMetric(whisperCacheMetrics$CacheEviction, date != null ? TimeUnit.MILLISECONDS.toMinutes(Math.abs(new Date().getTime() - date.getTime())) : 0L);
        } catch (Throwable th) {
            releaseWriteLock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.contentcache.VideoCacheManager
    public void cacheTitle(CacheRequest cacheRequest) {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "PrioritizingVideoCacheManager.cacheTitle");
        CacheRequest cacheRequestTrimToFirstAudioTrack = cacheRequest.trimToFirstAudioTrack();
        acquireWriteLock();
        try {
            WritableCacheRecord writableCacheRecordPrepareRecordForRequest = prepareRecordForRequest(cacheRequestTrimToFirstAudioTrack);
            triggerPerfLogging(writableCacheRecordPrepareRecordForRequest);
            this.mPersistence.putRecord(writableCacheRecordPrepareRecordForRequest);
            releaseWriteLock();
            this.mDownloadExecutor.refreshCurrentTask();
            Profiler.endTrace(traceKeyBeginTrace);
        } catch (Throwable th) {
            releaseWriteLock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.contentcache.VideoCacheManager
    @Nonnull
    public LiveCache getLiveCache() {
        return this.mLiveCache;
    }

    private void acquireWriteLock() {
        this.mLock.writeLock().lock();
        this.mWriteLockTraceKey = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "PrioritizingVideoCacheManager.writeLock");
    }

    private void acquireReadLock() {
        this.mLock.readLock().lock();
        this.mReadLockTraceKey = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "PrioritizingVideoCacheManager.readLock");
    }

    private void releaseWriteLock() {
        TraceKey traceKey = this.mWriteLockTraceKey;
        if (traceKey != null) {
            Profiler.endTrace(traceKey);
            this.mWriteLockTraceKey = null;
        }
        this.mLock.writeLock().unlock();
    }

    private void releaseReadLock() {
        TraceKey traceKey = this.mReadLockTraceKey;
        if (traceKey != null) {
            Profiler.endTrace(traceKey);
            this.mReadLockTraceKey = null;
        }
        this.mLock.readLock().unlock();
    }

    @Nonnull
    private WritableCacheRecord prepareRecordForRequest(CacheRequest cacheRequest) {
        WritableCacheRecord recordForSpec;
        TimeSpan durationToCache;
        VideoSpecification videoSpecification = cacheRequest.getVideoSpecification();
        Downloadable.DownloadableState downloadableState = this.mQueueingPolicy.canQueue(cacheRequest) ? Downloadable.DownloadableState.QUEUED : Downloadable.DownloadableState.NOT_DOWNLOADABLE;
        TimeSpan timeSpan = null;
        boolean z = false;
        if (ContentType.isLive(videoSpecification.getContentType())) {
            List<WritableCacheRecord> recordsIgnoringStartTime = this.mPersistence.getRecordsIgnoringStartTime(videoSpecification);
            recordForSpec = recordsIgnoringStartTime.isEmpty() ? null : recordsIgnoringStartTime.get(0);
        } else {
            recordForSpec = this.mPersistence.getRecordForSpec(videoSpecification);
        }
        if (recordForSpec != null) {
            boolean z2 = !recordForSpec.isLicensable() && cacheRequest.isEntitledToWatch();
            Downloadable.DownloadableState downloadableState2 = recordForSpec.getDownloadableState();
            Downloadable.DownloadableState downloadableState3 = Downloadable.DownloadableState.COMPLETED;
            if (downloadableState2 != downloadableState3 || (recordForSpec.getSessionType() == ContentSessionType.LIVE_CACHE && this.mConfig.shouldCacheLiveContentSessions())) {
                z = true;
            }
            if (!z2 && !z) {
                downloadableState = downloadableState3;
            }
            recordForSpec.setIsLicensable(cacheRequest.isEntitledToWatch());
            String source = cacheRequest.getSource();
            recordForSpec.setLatestSource(source);
            recordForSpec.addSource(source);
            recordForSpec.setPriority(cacheRequest.getPriority());
            recordForSpec.setDeferrable(cacheRequest.isDeferrable());
            if (recordForSpec.getDownloadableState() != Downloadable.DownloadableState.DELETE_IMMEDIATELY) {
                recordForSpec.setState(downloadableState);
            }
            recordForSpec.setPlaybackEnvelope(videoSpecification.getPlaybackEnvelope());
            return recordForSpec;
        }
        if (videoSpecification.isLiveStream()) {
            durationToCache = TimeSpan.ZERO;
            timeSpan = TimeSpan.MAX_VALUE;
        } else {
            durationToCache = this.mConfig.getDurationToCache();
        }
        VideoSpecification.Builder duration = VideoSpecification.newBuilder(videoSpecification).setDuration(durationToCache);
        if (timeSpan != null) {
            duration.setStartTime(timeSpan);
        }
        MediaQuality mediaQuality = videoSpecification.getMediaQuality();
        if (!videoSpecification.isLiveStream() && mediaQuality.compareTo(this.mConfig.getQualityToCache()) >= 0) {
            mediaQuality = this.mConfig.getQualityToCache();
        }
        if (mediaQuality == MediaQuality.HIGHEST) {
            mediaQuality = MediaQuality.HIGH;
        }
        duration.setMediaQuality(mediaQuality);
        VideoSpecification videoSpecificationBuild = duration.build();
        DLog.logf("Creating new cache record for %s", videoSpecificationBuild);
        WritableCacheRecord writableCacheRecord = new WritableCacheRecord(videoSpecificationBuild, cacheRequest.getVideoOptions().getSessionContext());
        writableCacheRecord.setInitialSource(cacheRequest.getSource());
        writableCacheRecord.addSource(cacheRequest.getSource());
        writableCacheRecord.setIsLicensable(cacheRequest.isEntitledToWatch());
        writableCacheRecord.setLatestSource(cacheRequest.getSource());
        writableCacheRecord.setPriority(cacheRequest.getPriority());
        writableCacheRecord.setState(downloadableState);
        writableCacheRecord.setDeferrable(cacheRequest.isDeferrable());
        writableCacheRecord.setPlaybackEnvelope(videoSpecificationBuild.getPlaybackEnvelope());
        return writableCacheRecord;
    }

    @Override // com.amazon.avod.media.contentcache.internal.executor.DownloadQueue
    public void updateItem(@Nonnull Downloadable downloadable, Downloadable.DownloadableState downloadableState, @Nullable ContentSession contentSession, @Nonnull Optional<String> optional, @Nullable TimeSpan timeSpan, long j) {
        long kiloBytes;
        Preconditions.checkNotNull(downloadable, "item");
        Preconditions.checkNotNull(optional, "optionalOfflineKeyId");
        tryReportCacheMetrics(false);
        acquireWriteLock();
        try {
            WritableCacheRecord recordForSpec = this.mPersistence.getRecordForSpec(downloadable.getSpecification());
            if (recordForSpec == null) {
                return;
            }
            if (downloadableState == Downloadable.DownloadableState.DELETED) {
                logRecordUpdate(recordForSpec, downloadableState);
                deleteCacheRecord(recordForSpec);
            } else if (recordForSpec.getDownloadableState() != Downloadable.DownloadableState.TOMBSTONED) {
                if (contentSession != null) {
                    if (recordForSpec.getAudioVideoUrls() == null) {
                        recordForSpec.setDownloadTime(new Date());
                    }
                    recordForSpec.setAudioVideoUrls(contentSession.getContext().getAudioVideoUrls());
                    recordForSpec.setEndTime(TimeSpan.max(recordForSpec.getSpecification().getStartTime(), contentSession.getLastAvailableTime()));
                    if (contentSession.getContext().getSessionType() == ContentSessionType.LIVE_CACHE) {
                        kiloBytes = 0;
                    } else if (this.mConfig.shouldUseCacheDirectoryFileSize()) {
                        kiloBytes = j + recordForSpec.getFilesizeKB();
                    } else {
                        kiloBytes = (long) DataUnit.BYTES.toKiloBytes(contentSession.getTotalNeededSizeInBytes() - contentSession.getRemainingNeededSizeInBytes());
                    }
                    recordForSpec.setFilesizeKb(kiloBytes);
                }
                if (optional.isPresent()) {
                    recordForSpec.setOfflineKeyId(optional.get());
                }
                if (timeSpan != null && this.mSmoothStreamingPlaybackConfig.shouldGetDurationToCacheFromHeuristics()) {
                    recordForSpec.setSpecification(VideoSpecification.newBuilder(recordForSpec.getSpecification()).setDuration(timeSpan).build());
                }
                logRecordUpdate(recordForSpec, downloadableState);
                recordForSpec.setState(downloadableState);
                if (downloadableState == Downloadable.DownloadableState.COMPLETED) {
                    triggerPerfLogging(recordForSpec);
                }
                this.mPersistence.putRecord(recordForSpec);
            }
        } finally {
            releaseWriteLock();
        }
    }

    private void deleteCacheRecord(WritableCacheRecord writableCacheRecord) {
        Preconditions.checkState(this.mLock.isWriteLockedByCurrentThread());
        for (WritableCacheRecord writableCacheRecord2 : this.mPersistence.getRecordsIgnoringStartTime(writableCacheRecord.getSpecification())) {
            if (writableCacheRecord2.getDownloadableState() == Downloadable.DownloadableState.TOMBSTONED || writableCacheRecord2.getDownloadableState() == Downloadable.DownloadableState.DELETE_IMMEDIATELY) {
                this.mPersistence.deleteRecord(writableCacheRecord2);
            } else {
                writableCacheRecord2.setState(Downloadable.DownloadableState.QUEUED);
                writableCacheRecord2.setEndTime(writableCacheRecord2.getSpecification().getStartTime());
                writableCacheRecord2.setFilesizeKb(0L);
                this.mPersistence.putRecord(writableCacheRecord2);
            }
        }
    }

    @Override // com.amazon.avod.media.contentcache.internal.executor.DownloadQueue
    @Nullable
    public Downloadable getNextItem() {
        WritableCacheRecord recordByState;
        acquireWriteLock();
        try {
            if ((this.mShouldAllowEvictionDuringPlayback || !this.mBaseDrmSystem.isPlaybackActive()) && (recordByState = this.mPersistence.getRecordByState(Downloadable.DownloadableState.DELETE_IMMEDIATELY, CachedContentPersistence.PriorityOrder.MAX)) != null) {
                return recordByState;
            }
            WritableCacheRecord recordByState2 = this.mPersistence.getRecordByState(Downloadable.DownloadableState.QUEUED, CachedContentPersistence.PriorityOrder.MAX);
            if (recordByState2 == null) {
                return findErrored();
            }
            int i = AnonymousClass5.$SwitchMap$com$amazon$avod$media$contentcache$internal$admittance$AdmittancePolicyResult[this.mAdmittancePolicy.canAdmit(recordByState2).ordinal()];
            if (i == 1) {
                return (recordByState2.getAudioVideoUrls() == null || recordByState2.getOfflineKeyId() == null) ? resolveRecordUrlAndOfflineKeyId(recordByState2, this.mPersistence.getRecordsIgnoringStartTime(recordByState2.getSpecification()), this.mShareOfflineKeyId) : recordByState2;
            }
            if (i != 2 && i != 3) {
                if (i != 4) {
                    return null;
                }
                scheduleDeferredExecution();
                return null;
            }
            WritableCacheRecord writableCacheRecordFindTombstonedRecord = findTombstonedRecord();
            if (writableCacheRecordFindTombstonedRecord == null) {
                return findRecordsToEvict(recordByState2);
            }
            if (writableCacheRecordFindTombstonedRecord.getDownloadTime() != null) {
                this.mPlaybackPmetMetricReporter.reportCacheEvictionTimerMetric(WhisperCacheMetrics$CacheEviction.CACHE_FULL, TimeUnit.MILLISECONDS.toMinutes(new Date().getTime() - writableCacheRecordFindTombstonedRecord.getDownloadTime().getTime()));
            }
            return writableCacheRecordFindTombstonedRecord;
        } finally {
            releaseWriteLock();
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$contentcache$internal$admittance$AdmittancePolicyResult;

        static {
            int[] iArr = new int[AdmittancePolicyResult.values().length];
            $SwitchMap$com$amazon$avod$media$contentcache$internal$admittance$AdmittancePolicyResult = iArr;
            try {
                iArr[AdmittancePolicyResult.ALLOWED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$contentcache$internal$admittance$AdmittancePolicyResult[AdmittancePolicyResult.DENIED_CACHE_FULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$contentcache$internal$admittance$AdmittancePolicyResult[AdmittancePolicyResult.DENIED_DISK_FULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$contentcache$internal$admittance$AdmittancePolicyResult[AdmittancePolicyResult.DENIED_DEFERRED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$contentcache$internal$admittance$AdmittancePolicyResult[AdmittancePolicyResult.DENIED_CACHING_NOT_PERMITTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private Downloadable findErrored() {
        DLog.logf("PrioritizingVideoCacheManager - Empty queue.");
        WritableCacheRecord recordByState = this.mPersistence.getRecordByState(Downloadable.DownloadableState.ERRORED, CachedContentPersistence.PriorityOrder.MAX);
        if (recordByState == null) {
            return null;
        }
        if (this.mRetryPolicy.canRetry(recordByState)) {
            recordByState.incrementAttemptCount();
            recordByState.setState(Downloadable.DownloadableState.QUEUED);
            this.mPersistence.putRecord(recordByState);
        } else {
            updateItem(recordByState, Downloadable.DownloadableState.NOT_DOWNLOADABLE, null, Optional.absent(), null, 0L);
        }
        return getNextItem();
    }

    @Nullable
    private WritableCacheRecord findTombstonedRecord() {
        return this.mPersistence.getRecordByState(Downloadable.DownloadableState.TOMBSTONED, CachedContentPersistence.PriorityOrder.MIN);
    }

    @Nullable
    private Downloadable findRecordsToEvict(WritableCacheRecord writableCacheRecord) {
        WritableCacheRecord recordByState = this.mPersistence.getRecordByState(Downloadable.DownloadableState.COMPLETED, CachedContentPersistence.PriorityOrder.MIN);
        if (recordByState == null || writableCacheRecord.getPriority() < recordByState.getPriority()) {
            return null;
        }
        updateItem(recordByState, Downloadable.DownloadableState.TOMBSTONED, null, Optional.fromNullable(recordByState.getOfflineKeyId()), null, 0L);
        return getNextItem();
    }

    private void scheduleDeferredExecution() {
        if (this.mCacheDelayedTaskSet.compareAndSet(false, true)) {
            int iNextInt = this.mRandom.nextInt(this.mJitterCacheConfig.getMaxDelayDurationForBackgroundCacheRequestsInMinutes().getTotalMinutes());
            long j = iNextInt;
            this.mJitterCacheConfig.setNextScheduledExecution(TimeSpan.add(TimeSpan.now(), TimeSpan.fromMinutes(j)));
            this.mJitterCacheConfig.setBackgroundCacheRequestCount(0);
            DLog.logf("Deferring this cache request to avoid heavy loading on the server side. Next execution in: %s minutes", Integer.valueOf(iNextInt));
            this.mExecutor.schedule(this.mDelayCacheExecutionRunnable, j, TimeUnit.MINUTES);
        }
    }

    private static WritableCacheRecord resolveRecordUrlAndOfflineKeyId(WritableCacheRecord writableCacheRecord, List<WritableCacheRecord> list, boolean z) {
        if (writableCacheRecord.getAudioVideoUrls() == null) {
            Iterator<WritableCacheRecord> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WritableCacheRecord next = it.next();
                if (next.getAudioVideoUrls() != null) {
                    writableCacheRecord.setAudioVideoUrls(next.getAudioVideoUrls());
                    writableCacheRecord.setDownloadTime(next.getDownloadTime());
                    break;
                }
            }
        }
        if (z && writableCacheRecord.getOfflineKeyId() == null) {
            Iterator<WritableCacheRecord> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                WritableCacheRecord next2 = it2.next();
                if (next2.getOfflineKeyId() != null) {
                    writableCacheRecord.setOfflineKeyId(next2.getOfflineKeyId());
                    break;
                }
            }
        }
        return writableCacheRecord;
    }

    protected void checkForExpiredRecords(@Nonnull ContentSessionType contentSessionType) {
        TimeSpan liveTimeToLive;
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String str = this.mDateFormat.format(calendar.getTime());
        if (contentSessionType == ContentSessionType.CONTENT_CACHE) {
            liveTimeToLive = this.mConfig.getTimeToLive();
        } else {
            liveTimeToLive = this.mConfig.getLiveTimeToLive();
        }
        calendar.setTimeInMillis(calendar.getTimeInMillis() - liveTimeToLive.getTotalMilliseconds());
        for (WritableCacheRecord writableCacheRecord : this.mPersistence.getAllRecordsOfTypeDownloadedBefore(calendar.getTime(), contentSessionType)) {
            String str2 = String.format(Locale.US, "encodeId: %s, Download time: %s, Current time: %s", writableCacheRecord.getAudioVideoUrls() != null ? String.valueOf(writableCacheRecord.getAudioVideoUrls().getEncodeId()) : "null", this.mDateFormat.format(writableCacheRecord.getDownloadTime()), str);
            DLog.logf("TTL %s reached. Record getting evicted %s", liveTimeToLive, str2);
            evict(writableCacheRecord.getSpecification(), WhisperCacheMetrics$CacheEviction.TTL_EXPIRE, str2);
        }
        purgeExpiredSsaiRecords(contentSessionType);
    }

    private void purgeExpiredSsaiRecords(@Nonnull ContentSessionType contentSessionType) {
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        if (contentSessionType != ContentSessionType.CONTENT_CACHE) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String str = this.mDateFormat.format(calendar.getTime());
        TimeSpan timeToLiveForAuxillaryEnabledContents = this.mConfig.getTimeToLiveForAuxillaryEnabledContents();
        calendar.setTimeInMillis(calendar.getTimeInMillis() - timeToLiveForAuxillaryEnabledContents.getTotalMilliseconds());
        for (WritableCacheRecord writableCacheRecord : this.mPersistence.getAllRecordsOfTypeDownloadedBefore(calendar.getTime(), contentSessionType)) {
            if (writableCacheRecord.getAudioVideoUrls() != null && !Strings.isNullOrEmpty(writableCacheRecord.getAudioVideoUrls().getAuxCacheKey())) {
                String str2 = String.format(Locale.US, "EncodeId: %s, Download time: %s, Current time: %s", String.valueOf(writableCacheRecord.getAudioVideoUrls().getEncodeId()), this.mDateFormat.format(writableCacheRecord.getDownloadTime()), str);
                DLog.logf("TTL %s reached. Record getting evicted %s", timeToLiveForAuxillaryEnabledContents, str2);
                evict(writableCacheRecord.getSpecification(), WhisperCacheMetrics$CacheEviction.TTL_EXPIRE, str2);
            }
        }
    }

    private void tryReportCacheMetrics(boolean z) {
        synchronized (this.mStopwatch) {
            if (!this.mStopwatch.isRunning()) {
                this.mStopwatch.start();
            } else if (!z && this.mStopwatch.elapsed(TimeUnit.SECONDS) < this.mConfig.getTimeIntervalToReportCacheMetrics().getTotalSeconds()) {
                return;
            }
            int totalRecords = this.mPersistence.getTotalRecords();
            long totalCacheFilesizeKb = this.mPersistence.getTotalCacheFilesizeKb();
            this.mStandaloneEventReporter.reportMetric(QoSMetric.CacheStatus.getEventName().name(), this.mStandaloneEventReporter.createMetricsBuilder().eventSubtype("CacheMetricsReport").note(String.format(Locale.US, "Number_of_titles : %d, Cache_file_size_in_kB : %d%n", Integer.valueOf(totalRecords), Long.valueOf(totalCacheFilesizeKb))));
            this.mPlaybackPmetMetricReporter.reportCacheStatusTimerMetric(WhisperCacheMetrics$CacheStatus.RECORD_COUNT, totalRecords);
            this.mPlaybackPmetMetricReporter.reportCacheStatusTimerMetric(WhisperCacheMetrics$CacheStatus.SIZE_ON_DISK, (long) DataUnit.KILOBYTES.toMegaBytes(totalCacheFilesizeKb));
            this.mStopwatch.reset().start();
        }
    }

    private void logRecordUpdate(WritableCacheRecord writableCacheRecord, Downloadable.DownloadableState downloadableState) {
        DLog.logf("Updating %s from %s to %s (tries: %d/%d)", writableCacheRecord.getSpecification(), writableCacheRecord.getDownloadableState(), downloadableState, Integer.valueOf(writableCacheRecord.getNumberOfAttempts()), Integer.valueOf(this.mConfig.getMaxNumberOfTries()));
    }

    private void triggerPerfLogging(WritableCacheRecord writableCacheRecord) {
        if (PlaybackPerfConfig.getInstance().isPlaybackPerfEnabled() && writableCacheRecord.getDownloadableState() == Downloadable.DownloadableState.COMPLETED && writableCacheRecord.getCacheState() == CacheRecord.RecordState.FULLY_CACHED) {
            Profiler.trigger(DownloadMarkers.PLAYBACK_PERF_WRITABLE_CACHE_RECORD_STATE, PlaybackExtras.newPlaybackTTFFExtra(String.format(Locale.US, "ASIN: %s | DownloadableState: %s | CachedState: %s | StartTime: %s", writableCacheRecord.getSpecification().getTitleId(), writableCacheRecord.getDownloadableState(), writableCacheRecord.getCacheState(), writableCacheRecord.getSpecification().getStartTime())));
        }
    }
}
