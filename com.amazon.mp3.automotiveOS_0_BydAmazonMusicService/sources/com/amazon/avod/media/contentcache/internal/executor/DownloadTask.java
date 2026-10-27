package com.amazon.avod.media.contentcache.internal.executor;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionEventListener;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.ClosePlayerEvent;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.internal.CacheConfig;
import com.amazon.avod.media.contentcache.internal.LiveCache;
import com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask;
import com.amazon.avod.media.drm.DrmLicenseAcquirer;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class DownloadTask implements DownloadExecutorTask, ContentSessionEventListener {
    private ContentSession mContentSession;
    private final ContentSessionFactory mContentSessionFactory;
    private final Stopwatch mDownloadTimer;
    private TimeSpan mDurationCached;
    private boolean mHasHandledClosePlayerEvent;
    private final Downloadable mItem;
    private final DrmLicenseAcquirer mLicenseAcquirer;
    private final LiveCache mLiveCache;
    private final Object mMonitor;
    private String mOfflineKeyId;
    private final PlaybackEnvelopeValidator mPlaybackEnvelopeValidator;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final QoSEventManagerFactory mQoSEventManagerFactory;
    private final RendererSchemeController mRendererSchemeController;
    private DownloadExecutorTask.Result mResult;
    private final boolean mShouldCacheLiveContentSessions;
    private boolean mShouldUseAuxCacheTTL;
    private final boolean mShouldUseCacheDirectoryFileSize;
    private long mStartingSizeInKiloBytes;
    private final ContentUrlFetcher mUrlFetcher;

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onAuxiliaryDownloadEligibilityStateChanged(boolean z) {
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onContentFragmentDownloaded(@Nonnull SampleType sampleType, long j, long j2) {
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onContentMetadataAcquired(@Nonnull String str) {
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onDownloadStart(long j) {
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onSessionEnding() {
    }

    public DownloadTask(@Nonnull BaseDrmSystem baseDrmSystem, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull Downloadable downloadable, @Nonnull QoSEventManagerFactory qoSEventManagerFactory, @Nonnull ContentUrlFetcher contentUrlFetcher, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull LiveCache liveCache) {
        DrmLicenseAcquirer drmLicenseAcquirer = new DrmLicenseAcquirer(baseDrmSystem);
        CacheConfig cacheConfig = CacheConfig.INSTANCE;
        this(contentSessionFactory, downloadable, drmLicenseAcquirer, qoSEventManagerFactory, contentUrlFetcher, rendererSchemeController, liveCache, cacheConfig.shouldCacheLiveContentSessions(), cacheConfig.shouldUseCacheDirectoryFileSize(), cacheConfig.shouldUseAuxCacheTTL(), PlaybackPmetMetricReporter.getInstance(), MediaSystemSharedDependencies.getInstance().getPlaybackEnvelopeValidator());
    }

    DownloadTask(@Nonnull ContentSessionFactory contentSessionFactory, @Nonnull Downloadable downloadable, @Nonnull DrmLicenseAcquirer drmLicenseAcquirer, @Nonnull QoSEventManagerFactory qoSEventManagerFactory, @Nonnull ContentUrlFetcher contentUrlFetcher, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull LiveCache liveCache, boolean z, boolean z2, boolean z3, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nonnull PlaybackEnvelopeValidator playbackEnvelopeValidator) {
        this.mMonitor = new Object();
        this.mOfflineKeyId = null;
        this.mDurationCached = null;
        this.mStartingSizeInKiloBytes = 0L;
        this.mHasHandledClosePlayerEvent = false;
        this.mShouldUseAuxCacheTTL = false;
        this.mContentSessionFactory = (ContentSessionFactory) Preconditions.checkNotNull(contentSessionFactory, "sessionFactory");
        this.mItem = (Downloadable) Preconditions.checkNotNull(downloadable, "workItem");
        this.mLicenseAcquirer = (DrmLicenseAcquirer) Preconditions.checkNotNull(drmLicenseAcquirer, "licenseAcquirer");
        this.mQoSEventManagerFactory = (QoSEventManagerFactory) Preconditions.checkNotNull(qoSEventManagerFactory, "qosEventManagerFactory");
        this.mUrlFetcher = (ContentUrlFetcher) Preconditions.checkNotNull(contentUrlFetcher, "urlFetcher");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mLiveCache = (LiveCache) Preconditions.checkNotNull(liveCache, "liveCache");
        this.mShouldCacheLiveContentSessions = z;
        this.mShouldUseCacheDirectoryFileSize = z2;
        this.mShouldUseAuxCacheTTL = z3;
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mDownloadTimer = Stopwatch.createStarted();
        this.mPlaybackEnvelopeValidator = (PlaybackEnvelopeValidator) Preconditions.checkNotNull(playbackEnvelopeValidator, "playbackEnvelopeValidator");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0257 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0272 A[Catch: all -> 0x02bd, TryCatch #4 {, blocks: (B:4:0x0005, B:6:0x002f, B:8:0x003e, B:10:0x0046, B:13:0x0054, B:15:0x0064, B:27:0x00b1, B:29:0x00c6, B:31:0x00ca, B:32:0x00d1, B:34:0x00db, B:36:0x00e5, B:38:0x00f2, B:39:0x00f5, B:41:0x00fb, B:43:0x010f, B:45:0x0141, B:47:0x0145, B:49:0x0159, B:50:0x0173, B:52:0x0175, B:54:0x017f, B:56:0x0187, B:58:0x0197, B:60:0x019b, B:61:0x01b5, B:63:0x01b7, B:64:0x01df, B:66:0x01ec, B:68:0x0208, B:72:0x021f, B:71:0x0219, B:73:0x022b, B:75:0x0233, B:82:0x0253, B:84:0x0257, B:87:0x0263, B:89:0x0272, B:90:0x0277, B:91:0x0296, B:86:0x025d, B:78:0x023b, B:79:0x024c, B:81:0x024e, B:42:0x010a, B:17:0x006d, B:19:0x0073, B:22:0x0082, B:23:0x008e, B:25:0x00ab, B:97:0x029c, B:99:0x02a5, B:102:0x02ae, B:104:0x02b3, B:105:0x02bb, B:103:0x02b1, B:12:0x004d, B:7:0x0038), top: B:118:0x0005, inners: #1 }] */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v4, types: [boolean, int] */
    @Override // com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask
    @javax.annotation.Nonnull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask.Result execute() {
        /*
            Method dump skipped, instruction units count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.contentcache.internal.executor.DownloadTask.execute():com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask$Result");
    }

    private long getCacheDirectoryFileInKiloBytes() {
        if (this.mShouldUseCacheDirectoryFileSize) {
            return (long) DataUnit.BYTES.toKiloBytes(DiskUtils.getSizeRecursiveInBytes(this.mItem.getStoragePath()));
        }
        return 0L;
    }

    @Subscribe
    public void handleClosePlayerEvent(@Nonnull ClosePlayerEvent closePlayerEvent) {
        Preconditions.checkNotNull(closePlayerEvent, "closePlayerEvent");
        if (this.mHasHandledClosePlayerEvent) {
            return;
        }
        this.mHasHandledClosePlayerEvent = true;
        cleanupCachedLiveContentSession();
    }

    private boolean shouldEndCachedContentSession() {
        if (this.mItem.getSessionType() != ContentSessionType.LIVE_CACHE || !this.mShouldCacheLiveContentSessions) {
            return true;
        }
        DLog.logf("Not terminating content session for live cache title %s", this.mItem.getSpecification().getTitleId());
        return false;
    }

    @Override // com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask
    public void cancel() {
        setResult(Downloadable.DownloadableState.QUEUED, true);
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onFatalError(@Nonnull ContentException contentException) {
        setResult(Downloadable.DownloadableState.ERRORED, true);
        cleanupCachedLiveContentSession();
    }

    private void cleanupCachedLiveContentSession() {
        ContentSession contentSessionPopFromCache;
        if (this.mItem.getSessionType() == ContentSessionType.LIVE_CACHE && this.mShouldCacheLiveContentSessions && (contentSessionPopFromCache = this.mLiveCache.popFromCache(this.mItem.getSpecification().getTitleId())) != null) {
            contentSessionPopFromCache.getEventDispatcher().unregisterEventBusHandler(this);
            DLog.logf("Shutting down LIVE_CACHE ContentSession for title: %s", this.mItem.getSpecification().getTitleId());
            contentSessionPopFromCache.end(true);
        }
    }

    @Override // com.amazon.avod.content.ContentSessionEventListener
    public void onDownloadComplete() {
        setResult(Downloadable.DownloadableState.COMPLETED, true);
    }

    private void setResult(Downloadable.DownloadableState downloadableState, boolean z) {
        synchronized (this.mMonitor) {
            if (this.mResult == null) {
                this.mResult = new DownloadExecutorTask.Result(downloadableState, z ? this.mContentSession : null, Optional.fromNullable(this.mOfflineKeyId), this.mDurationCached, Math.max(getCacheDirectoryFileInKiloBytes() - this.mStartingSizeInKiloBytes, 0L));
            }
            this.mMonitor.notifyAll();
        }
    }
}
