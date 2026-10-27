package com.amazon.avod.userdownload.internal;

import android.content.Context;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.download.DownloadLicenseManager;
import com.amazon.avod.download.PlaybackDownloadExecutorFactory;
import com.amazon.avod.drm.db.DrmPersistence;
import com.amazon.avod.drm.db.DrmPersistenceInfo;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.error.DrmErrorCode;
import com.amazon.avod.media.framework.error.LicenseQueryException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.metrics.aloysius.AloysiusDownloadEventReporter;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.userdownload.DownloadQoSReporter;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.PauseToken;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadChangeListener;
import com.amazon.avod.userdownload.PlaybackDownloadErrorConverter;
import com.amazon.avod.userdownload.PlaybackDownloadEventReporter;
import com.amazon.avod.userdownload.PlaybackDownloadRequest;
import com.amazon.avod.userdownload.UserDownloadLocation;
import com.amazon.avod.userdownload.filter.PlaybackDownloadFilter;
import com.amazon.avod.userdownload.internal.PlaybackDownloadLicenseHelper;
import com.amazon.avod.userdownload.reporting.DeletionCause;
import com.amazon.avod.userdownload.reporting.LicenseOperationCause;
import com.amazon.avod.userdownload.reporting.MakeActiveCause;
import com.amazon.avod.userdownload.reporting.PauseCause;
import com.amazon.avod.userdownload.reporting.QueueCause;
import com.amazon.avod.userdownload.reporting.RetryCause;
import com.amazon.avod.userdownload.rights.RightsManager;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadManager implements DrmPersistence, PVDownloadManagerPlayerShim {
    private final Context mContext;
    private final PlaybackDownloadEventReporter mDownloadEventReporter;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private final InitializationLatch mInitializationLatchMedia = new InitializationLatch(String.format(Locale.US, "%s:%s", getClass().getSimpleName(), "Media"));
    private final PlaybackDownloadLicenseHelper.Factory mLicenseHelperFactory;
    private final PlaybackDownloadExecutor mPlaybackDownloadExecutor;
    private final PlaybackDownloadNotifier mPlaybackDownloadNotifier;
    private final PlaybackDownloadPersistence mPlaybackDownloadPersistence;
    private final RightsManager mRightsManager;
    private final PlaybackDownloadSharedComponents mSharedComponents;
    private PlaybackDownloadLicenseHelper mUserDownloadLicenseHelper;

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void makeActive(@Nonnull PVDownloadPlayerShim pVDownloadPlayerShim, @Nonnull MakeActiveCause makeActiveCause) {
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void resume(@Nonnull PauseToken pauseToken) {
    }

    public PlaybackDownloadManager(@Nonnull PlaybackDownloadSharedComponents playbackDownloadSharedComponents, @Nonnull PlaybackDownloadPersistence playbackDownloadPersistence, @Nonnull PlaybackDownloadExecutor playbackDownloadExecutor, @Nonnull RightsManager rightsManager, @Nonnull PlaybackDownloadLicenseHelper.Factory factory, @Nonnull Context context) {
        PlaybackDownloadSharedComponents playbackDownloadSharedComponents2 = (PlaybackDownloadSharedComponents) Preconditions.checkNotNull(playbackDownloadSharedComponents, "sharedComponents");
        this.mSharedComponents = playbackDownloadSharedComponents2;
        this.mPlaybackDownloadPersistence = (PlaybackDownloadPersistence) Preconditions.checkNotNull(playbackDownloadPersistence, "PlaybackDownloadPersistence");
        this.mDownloadEventReporter = playbackDownloadSharedComponents2.getPlaybackDownloadEventReporter();
        this.mPlaybackDownloadNotifier = playbackDownloadSharedComponents2.getPlaybackDownloadNotifier();
        this.mPlaybackDownloadExecutor = (PlaybackDownloadExecutor) Preconditions.checkNotNull(playbackDownloadExecutor, "baseDownloadExecutor");
        this.mRightsManager = (RightsManager) Preconditions.checkNotNull(rightsManager, "rightsManager");
        this.mLicenseHelperFactory = (PlaybackDownloadLicenseHelper.Factory) Preconditions.checkNotNull(factory, "licenseHelperFactory");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
    }

    public void initialize() throws InitializationException {
        this.mInitializationLatch.start(120L, TimeUnit.SECONDS);
        this.mPlaybackDownloadPersistence.initialize(this.mContext, this.mSharedComponents);
        this.mPlaybackDownloadExecutor.initialize();
        this.mInitializationLatch.complete();
    }

    public void initializeWithMediaComponents(@Nonnull EventReporterFactory eventReporterFactory, @Nonnull DownloadLicenseManager downloadLicenseManager, @Nonnull PlaybackDownloadExecutorFactory playbackDownloadExecutorFactory, @Nonnull ImmutableList<Provider<? extends ContentFetcherPlugin>> immutableList, @Nonnull AloysiusDownloadEventReporter aloysiusDownloadEventReporter) throws InitializationException {
        this.mInitializationLatchMedia.start(120L, TimeUnit.SECONDS);
        this.mSharedComponents.getPlaybackDownloadEventReporter().initialize(new DownloadQoSReporter(eventReporterFactory.newStandaloneEventReporter(null), DownloadQoSReporter.DownloadQoSEventType.PLAYER_SDK_DOWNLOAD_EVENT));
        PlaybackDownloadLicenseHelper playbackDownloadLicenseHelperCreateHelper = this.mLicenseHelperFactory.createHelper(downloadLicenseManager, this.mRightsManager);
        this.mUserDownloadLicenseHelper = playbackDownloadLicenseHelperCreateHelper;
        this.mPlaybackDownloadExecutor.initializeWithMediaComponents(playbackDownloadExecutorFactory, immutableList, playbackDownloadLicenseHelperCreateHelper);
        this.mInitializationLatchMedia.complete();
        this.mPlaybackDownloadExecutor.refreshCurrentTask();
        this.mDownloadEventReporter.setAloysiusDownloadReporter(aloysiusDownloadEventReporter);
    }

    public void waitOnMediaInitializationUninterruptibly() {
        this.mInitializationLatchMedia.waitOnInitializationUninterruptibly();
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void registerStreamingDownloadEventListener(@Nonnull PVDownloadPlayerShim pVDownloadPlayerShim, @Nonnull ContentSession contentSession) {
        Preconditions.checkNotNull(pVDownloadPlayerShim, "download");
        Preconditions.checkNotNull(contentSession, "session");
        this.mPlaybackDownloadExecutor.registerStreamingDownloadEventListener((PlaybackDownload) pVDownloadPlayerShim, contentSession);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    @Nonnull
    public Optional<PVDownloadPlayerShim> getOfflineDownloadIfPresent(VideoSpecification videoSpecification, Map<String, String> map) {
        if (videoSpecification == null || videoSpecification.isTrailer() || videoSpecification.isLiveStream()) {
            return Optional.absent();
        }
        return Optional.fromNullable(getDownloadForAsin(videoSpecification.getTitleId(), PlaybackDownloadFilter.acceptAll()).orNull());
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    @Nonnull
    public Optional<PVDownloadPlayerShim> getOfflineDownloadIfPresent(String str, Map<String, String> map) {
        return Optional.fromNullable(getDownloadForAsin(str, PlaybackDownloadFilter.acceptAll()).orNull());
    }

    @Nonnull
    public PlaybackDownload queue(@Nonnull PlaybackDownloadRequest playbackDownloadRequest, @Nonnull QueueCause queueCause) throws PlaybackDownloadRequest.IllegalPlaybackDownloadRequestException, PlaybackDownloadRequest.DuplicatePlaybackDownloadRequestException {
        Preconditions.checkNotNull(playbackDownloadRequest, "request");
        Preconditions.checkNotNull(queueCause, "cause");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:queue", getClass().getSimpleName());
        try {
            return this.mPlaybackDownloadExecutor.queue(playbackDownloadRequest, queueCause);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nonnull
    public void makeActiveSDK(@Nonnull PVDownloadPlayerShim pVDownloadPlayerShim, @Nonnull MakeActiveCause makeActiveCause) {
        Preconditions.checkNotNull(pVDownloadPlayerShim, "download");
        Preconditions.checkNotNull(makeActiveCause, "cause");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:makeActive", getClass().getSimpleName());
        try {
            this.mPlaybackDownloadExecutor.makeActive((PlaybackDownload) pVDownloadPlayerShim, makeActiveCause);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nonnull
    public Optional<PlaybackDownload> delete(@Nonnull PlaybackDownload playbackDownload, @Nonnull DeletionCause deletionCause) {
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(deletionCause, "cause");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:delete", getClass().getSimpleName());
        try {
            return this.mPlaybackDownloadExecutor.delete(playbackDownload, deletionCause);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nonnull
    public Optional<PlaybackDownload> retry(@Nonnull PlaybackDownload playbackDownload, @Nonnull RetryCause retryCause) {
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(retryCause, "cause");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:retry", getClass().getSimpleName());
        try {
            return this.mPlaybackDownloadExecutor.retry(playbackDownload, retryCause);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nonnull
    public ImmutableSet<PlaybackDownload> getAllDownloadsForAllUsers() {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.DEBUG, "%s:getAllDownloadsForAllUsers", getClass().getSimpleName());
        try {
            return this.mPlaybackDownloadPersistence.getAllDownloads();
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nonnull
    public ImmutableSet<PlaybackDownload> getDownloads(@Nonnull PlaybackDownloadFilter playbackDownloadFilter) {
        Preconditions.checkNotNull(playbackDownloadFilter, PageUriUtils.FILTER_QUERY_PARAM);
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.DEBUG, "%s:getAllDownloads:%s", getClass().getSimpleName(), playbackDownloadFilter.getClass().getSimpleName());
        try {
            return this.mPlaybackDownloadPersistence.getAllDownloads(playbackDownloadFilter);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nonnull
    public Optional<PlaybackDownload> getDownloadForAsin(@Nonnull String str, @Nonnull PlaybackDownloadFilter playbackDownloadFilter) {
        Preconditions.checkNotNull(str, "asin");
        Preconditions.checkNotNull(playbackDownloadFilter, PageUriUtils.FILTER_QUERY_PARAM);
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.DEBUG, "%s:getDownloadForAsin:filter", getClass().getSimpleName());
        try {
            return Iterables.tryFind(this.mPlaybackDownloadPersistence.getAllDownloads(str), playbackDownloadFilter);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    public void addDownloadChangeListener(@Nonnull PlaybackDownloadChangeListener playbackDownloadChangeListener) {
        Preconditions.checkNotNull(playbackDownloadChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:addDownloadChangeListener", getClass().getSimpleName());
        try {
            this.mPlaybackDownloadNotifier.addListener(playbackDownloadChangeListener);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    public void pauseSDK(@Nonnull PlaybackDownload playbackDownload) {
        Preconditions.checkNotNull(playbackDownload, "download");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:pauseSDK", getClass().getSimpleName());
        try {
            this.mPlaybackDownloadExecutor.pausePlaybackSDK(playbackDownload);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    public void resumeSDK(@Nonnull PlaybackDownload playbackDownload) {
        Preconditions.checkNotNull(playbackDownload, "download");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:resumeSDK", getClass().getSimpleName());
        try {
            this.mPlaybackDownloadExecutor.resumePlaybackSDK(playbackDownload);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    @Nonnull
    public PauseToken pause(@Nonnull PauseCause pauseCause) {
        return new PauseToken(new Object(), pauseCause);
    }

    @Nonnull
    public ImmutableSet<PauseCause> getDownloadWaitingCauses() {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:getDownloadWaitingCause", getClass().getSimpleName());
        try {
            return this.mPlaybackDownloadExecutor.getPauseStatus();
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void onPlaybackLicenseError(@Nonnull PVDownloadPlayerShim pVDownloadPlayerShim, @Nonnull LicenseQueryException licenseQueryException, @Nonnull LicenseOperationCause licenseOperationCause) {
        Preconditions.checkNotNull(pVDownloadPlayerShim, "download");
        Preconditions.checkNotNull(licenseQueryException, "ex");
        Preconditions.checkNotNull(licenseOperationCause, "cause");
        this.mPlaybackDownloadExecutor.handleLicenseError((PlaybackDownload) pVDownloadPlayerShim, licenseQueryException.getErrorCode());
        if (licenseQueryException.getErrorCode() == DrmErrorCode.OFFLINE_LICENSE_MISSING || PlaybackDownloadErrorConverter.getBaseLicenseExpiryCodes().contains(licenseQueryException.getErrorCode())) {
            try {
                this.mPlaybackDownloadExecutor.syncLicenseState((PlaybackDownload) pVDownloadPlayerShim, licenseOperationCause);
            } catch (LicenseQueryException e) {
                DLog.warnf("DWNLD_SDK Sync with store failed: %s", e);
            }
        }
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void consumeRightIfNeeded(@Nonnull PVDownloadPlayerShim pVDownloadPlayerShim) {
        waitOnMediaInitializationUninterruptibly();
        this.mUserDownloadLicenseHelper.consumeRightIfNeeded((PlaybackDownload) pVDownloadPlayerShim);
    }

    @Override // com.amazon.avod.drm.db.DrmPersistence
    public void upsertToDrmPersistence(@Nonnull String str, @Nonnull DrmRecord drmRecord) {
        this.mPlaybackDownloadExecutor.upsertToDrmPersistence(str, drmRecord);
    }

    @Override // com.amazon.avod.drm.db.DrmPersistence
    @Nonnull
    public Set<DrmPersistenceInfo> getLicenseRecordsFromDrmPersistence(String str) {
        ImmutableSet<DrmPersistenceInfo> fromDrmPersistence = this.mPlaybackDownloadExecutor.getFromDrmPersistence(str);
        DLog.devf("DWNLD_SDK DrmPersistence.getLicenseRecordsFromDrmPersistence(%s) returning %d entries: %s", str, Integer.valueOf(fromDrmPersistence.size()), fromDrmPersistence);
        return fromDrmPersistence;
    }

    @Override // com.amazon.avod.drm.db.DrmPersistence
    @Nonnull
    public Set<DrmPersistenceInfo> getAllRecords() {
        ImmutableSet<DrmPersistenceInfo> allRecords = this.mPlaybackDownloadExecutor.getAllRecords();
        DLog.devf("DWNLD_SDK DrmPersistence.getAllRecords() returning %d entries: %s", Integer.valueOf(allRecords.size()), allRecords);
        return allRecords;
    }

    public UserDownloadLocation getPreferredDownloadLocation() {
        return this.mSharedComponents.getLocationConfig().getPreferredDownloadLocation();
    }
}
