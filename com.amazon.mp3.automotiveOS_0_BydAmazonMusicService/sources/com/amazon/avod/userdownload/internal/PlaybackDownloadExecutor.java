package com.amazon.avod.userdownload.internal;

import android.content.Context;
import android.util.Pair;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionEventListener;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.CuepointPlaylistInfo;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.download.PlaybackDownloadExecutorFactory;
import com.amazon.avod.download.PlaybackDownloadListenerInternal;
import com.amazon.avod.download.PlaybackQueueDownloadExecutor;
import com.amazon.avod.drm.db.DrmPersistenceInfo;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.fileio.ExternalStorageStatusChangeListener;
import com.amazon.avod.media.download.error.DownloadErrorCode;
import com.amazon.avod.media.download.error.DownloadExecutionErrorCode;
import com.amazon.avod.media.download.internal.PlaybackDownloadQueueConfig;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.ServiceErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.LicenseQueryException;
import com.amazon.avod.metrics.aloysius.AloysiusDownloadEventReporter;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.ProfiledLock;
import com.amazon.avod.userdownload.PauseToken;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadEventReporter;
import com.amazon.avod.userdownload.PlaybackDownloadLocationConfig;
import com.amazon.avod.userdownload.PlaybackDownloadRequest;
import com.amazon.avod.userdownload.ProgressMilestone;
import com.amazon.avod.userdownload.UserDownloadLocation;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.filter.PlaybackDownloadFilter;
import com.amazon.avod.userdownload.reporting.Cause;
import com.amazon.avod.userdownload.reporting.DeletionCause;
import com.amazon.avod.userdownload.reporting.DrmRecordChangeType;
import com.amazon.avod.userdownload.reporting.InternalErrorCause;
import com.amazon.avod.userdownload.reporting.LicenseOperationCause;
import com.amazon.avod.userdownload.reporting.MakeActiveCause;
import com.amazon.avod.userdownload.reporting.MarkAsErroredCause;
import com.amazon.avod.userdownload.reporting.PauseCause;
import com.amazon.avod.userdownload.reporting.QueueCause;
import com.amazon.avod.userdownload.reporting.RedownloadCause;
import com.amazon.avod.userdownload.reporting.RetryCause;
import com.amazon.avod.userdownload.reporting.RightsOperationCause;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.avod.util.Preconditions2;
import com.amazon.avod.util.Throwables2;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadExecutor implements PlaybackDownloadListenerInternal {
    private static final ImmutableSet<MediaErrorCode> ALLOWED_ERROR_CODE_OVERRIDES;
    private static final ImmutableMultimap<UserDownloadState, UserDownloadState> ALLOWED_STATE_TRANSITIONS;
    private static final ImmutableSet<MediaErrorCode> DISK_FULL_ERRORS;
    private static final ImmutableMap<Downloadable.DownloadableState, UserDownloadState> DOWNLOADABLE_STATE_TRANSITIONS;
    private static final ImmutableSet<UserDownloadState> IDLABLE_INCOMPLETE_STATES;
    private static final ImmutableSet<UserDownloadState> IDLE_INCOMPLETE_STATES;
    private static final ImmutableSet<UserDownloadState> WATCHABLE_DOWNLOAD_STATES;
    private final PlaybackDownloadQueueConfig mConfig;
    private final Context mContext;

    @GuardedBy("mExecutorLock")
    private PlaybackQueueDownloadExecutor mDownloadExecutor;

    @GuardedBy("mExecutorLock")
    private final PlaybackDownloadNotifier mDownloadNotifier;

    @GuardedBy("mExecutorLock")
    private final PlaybackDownloadPersistence mDownloadPersistance;

    @GuardedBy("mExecutorLock")
    private final PlaybackDownloadQueueManager mDownloadQueueManager;

    @GuardedBy("mExecutorLock")
    private final PauseTokenVendor mPauseTokenVendor;
    private final PlaybackDownloadEventReporter mPlaybackDownloadEventReporter;

    @GuardedBy("mExecutorLock")
    private final PlaybackDownloadLocationConfig mPlaybackDownloadLocationConfig;
    private final StorageHelper mStorageHelper;
    private PlaybackDownloadLicenseHelper mUserDownloadLicenseHelper;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private final InitializationLatch mInitializationLatchMedia = new InitializationLatch(String.format(Locale.US, "%s:%s", getClass().getSimpleName(), "Media"));
    private final ProfiledLock mExecutorLock = new ProfiledLock(this, ProfiledLock.ThreadUsage.ANY);

    @GuardedBy("mExecutorLock")
    private final Map<PlaybackDownload, PlaybackDownload> mDownloadsPendingChange = Maps.newHashMap();

    @GuardedBy("mExecutorLock")
    private Optional<PauseToken> mStorageStateUnknownPauseToken = Optional.absent();

    @GuardedBy("mExecutorLock")
    private Optional<PauseToken> mMediaSystemPauseToken = Optional.absent();

    @GuardedBy("mExecutorLock")
    private ExecutorService mReleaseRightsExecutor = ExecutorBuilder.newBuilderFor(this, new String[0]).withFixedThreadPoolSize(1).allowCoreThreadExpiry().build();

    static {
        ImmutableMultimap.Builder builder = new ImmutableMultimap.Builder();
        UserDownloadState userDownloadState = UserDownloadState.QUEUEING;
        UserDownloadState userDownloadState2 = UserDownloadState.QUEUED;
        ImmutableMultimap.Builder builderPut = builder.put(userDownloadState, userDownloadState2);
        UserDownloadState userDownloadState3 = UserDownloadState.WAITING;
        ImmutableMultimap.Builder builderPut2 = builderPut.put(userDownloadState, userDownloadState3);
        UserDownloadState userDownloadState4 = UserDownloadState.PAUSED;
        ImmutableMultimap.Builder builderPut3 = builderPut2.put(userDownloadState, userDownloadState4);
        UserDownloadState userDownloadState5 = UserDownloadState.DELETE_REQUESTED;
        UserDownloadState userDownloadState6 = UserDownloadState.DELETING;
        ImmutableMultimap.Builder builderPut4 = builderPut3.put(userDownloadState5, userDownloadState6);
        UserDownloadState userDownloadState7 = UserDownloadState.DELETED;
        ImmutableMultimap.Builder builderPut5 = builderPut4.put(userDownloadState6, userDownloadState7).put(userDownloadState, userDownloadState7).put(userDownloadState2, userDownloadState7);
        UserDownloadState userDownloadState8 = UserDownloadState.DOWNLOADING;
        ImmutableMultimap.Builder builderPut6 = builderPut5.put(userDownloadState8, userDownloadState7);
        UserDownloadState userDownloadState9 = UserDownloadState.ERROR;
        ImmutableMultimap.Builder builderPut7 = builderPut6.put(userDownloadState9, userDownloadState7);
        UserDownloadState userDownloadState10 = UserDownloadState.DOWNLOADED;
        ALLOWED_STATE_TRANSITIONS = builderPut7.put(userDownloadState10, userDownloadState7).put(userDownloadState5, userDownloadState7).put(userDownloadState4, userDownloadState7).put(userDownloadState3, userDownloadState7).put(userDownloadState2, userDownloadState5).put(userDownloadState3, userDownloadState5).put(userDownloadState4, userDownloadState5).put(userDownloadState8, userDownloadState5).put(userDownloadState9, userDownloadState5).put(userDownloadState10, userDownloadState5).put(userDownloadState2, userDownloadState8).put(userDownloadState3, userDownloadState8).put(userDownloadState9, userDownloadState8).put(userDownloadState8, userDownloadState10).put(userDownloadState8, userDownloadState2).put(userDownloadState8, userDownloadState4).put(userDownloadState8, userDownloadState9).put(userDownloadState8, userDownloadState3).put(userDownloadState9, userDownloadState2).put(userDownloadState9, userDownloadState4).put(userDownloadState9, userDownloadState3).put(userDownloadState10, userDownloadState9).put(userDownloadState2, userDownloadState9).put(userDownloadState3, userDownloadState9).put(userDownloadState4, userDownloadState9).put(userDownloadState2, userDownloadState4).put(userDownloadState3, userDownloadState4).put(userDownloadState2, userDownloadState3).put(userDownloadState4, userDownloadState3).put(userDownloadState3, userDownloadState2).put(userDownloadState4, userDownloadState2).build();
        ALLOWED_ERROR_CODE_OVERRIDES = ImmutableSet.builder().add(StandardErrorCode.MEDIA_EJECTED).add(ServiceErrorCode.DOWNLOAD_NOT_OWNED).add(DownloadErrorCode.AV_MARKETPLACE_CHANGE).build();
        DOWNLOADABLE_STATE_TRANSITIONS = (ImmutableMap) Preconditions2.checkFullKeyMapping(Downloadable.DownloadableState.class, ImmutableMap.builder().put(Downloadable.DownloadableState.NOT_DOWNLOADABLE, userDownloadState6).put(Downloadable.DownloadableState.TOMBSTONED, userDownloadState6).put(Downloadable.DownloadableState.DELETE_IMMEDIATELY, userDownloadState6).put(Downloadable.DownloadableState.QUEUED, userDownloadState4).put(Downloadable.DownloadableState.ERRORED, userDownloadState9).put(Downloadable.DownloadableState.COMPLETED, userDownloadState10).put(Downloadable.DownloadableState.DELETED, userDownloadState7).build());
        WATCHABLE_DOWNLOAD_STATES = ImmutableSet.of(userDownloadState10, userDownloadState2, userDownloadState3, userDownloadState4, userDownloadState8);
        IDLE_INCOMPLETE_STATES = UserDownloadState.IDLE_WORK_IN_PROGRESS_STATES;
        IDLABLE_INCOMPLETE_STATES = UserDownloadState.WORK_NEEDED_STATES;
        DISK_FULL_ERRORS = ImmutableSet.of((DownloadErrorCode) StandardErrorCode.DISK_FULL, DownloadErrorCode.DISK_FULL, DownloadErrorCode.EXTERNAL_DISK_FULL, DownloadErrorCode.INTERNAL_DISK_FULL);
    }

    public PlaybackDownloadExecutor(@Nonnull PlaybackDownloadPersistence playbackDownloadPersistence, @Nonnull PlaybackDownloadNotifier playbackDownloadNotifier, @Nonnull PlaybackDownloadQueueManager playbackDownloadQueueManager, @Nonnull PlaybackDownloadEventReporter playbackDownloadEventReporter, @Nonnull PlaybackDownloadLocationConfig playbackDownloadLocationConfig, @Nonnull PauseTokenVendor pauseTokenVendor, @Nonnull PlaybackDownloadQueueConfig playbackDownloadQueueConfig, @Nonnull StorageHelper storageHelper, @Nonnull Context context) {
        this.mDownloadPersistance = (PlaybackDownloadPersistence) Preconditions.checkNotNull(playbackDownloadPersistence, "downloadPersistance");
        this.mDownloadNotifier = (PlaybackDownloadNotifier) Preconditions.checkNotNull(playbackDownloadNotifier, "downloadNotifier");
        this.mDownloadQueueManager = (PlaybackDownloadQueueManager) Preconditions.checkNotNull(playbackDownloadQueueManager, "downloadQueueManager");
        this.mPlaybackDownloadEventReporter = (PlaybackDownloadEventReporter) Preconditions.checkNotNull(playbackDownloadEventReporter, "playbackDownloadEventReporter");
        this.mPlaybackDownloadLocationConfig = (PlaybackDownloadLocationConfig) Preconditions.checkNotNull(playbackDownloadLocationConfig, "downloadLocationConfig");
        this.mPauseTokenVendor = (PauseTokenVendor) Preconditions.checkNotNull(pauseTokenVendor, "pauseTokenVendor");
        this.mConfig = (PlaybackDownloadQueueConfig) Preconditions.checkNotNull(playbackDownloadQueueConfig, "downloadQueueConfig");
        this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
    }

    public void initialize() {
        this.mInitializationLatch.start(120L, TimeUnit.SECONDS);
        this.mDownloadQueueManager.initialize(this.mContext);
        updateDownloadsWithPreferredDownloadLocation();
        checkInternalDownloadAvailabilityDuringInitialization();
        this.mInitializationLatch.complete();
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("pauseExecutionUntilMediaSystemInitializes");
        try {
            this.mMediaSystemPauseToken = Optional.of(pause(PauseCause.EXTERNAL_STORAGE_STATE_UNKNOWN));
            this.mExecutorLock.unlock(keyLock);
            if (!this.mStorageHelper.isExternalStorageStateKnown()) {
                onExternalStorageStateUnknown();
            }
            this.mStorageHelper.addExternalStorageStatusChangeListener(new OnExternalStorageMountListener());
            if (this.mStorageHelper.isExternalStorageStateKnown()) {
                if (this.mStorageHelper.getExternalDownloadDir().isPresent()) {
                    onExternalStorageAvailable();
                } else {
                    onExternalStorageUnavailable();
                }
            }
        } catch (Throwable th) {
            this.mExecutorLock.unlock(keyLock);
            throw th;
        }
    }

    public void initializeWithMediaComponents(@Nonnull PlaybackDownloadExecutorFactory playbackDownloadExecutorFactory, @Nonnull ImmutableList<Provider<? extends ContentFetcherPlugin>> immutableList, @Nonnull PlaybackDownloadLicenseHelper playbackDownloadLicenseHelper) {
        Preconditions.checkNotNull(immutableList, "pluginProviders");
        this.mInitializationLatch.checkInitialized();
        this.mInitializationLatchMedia.start(120L, TimeUnit.SECONDS);
        this.mUserDownloadLicenseHelper = (PlaybackDownloadLicenseHelper) Preconditions.checkNotNull(playbackDownloadLicenseHelper, "userDownloadLicenseHelper");
        this.mDownloadExecutor = playbackDownloadExecutorFactory.newDownloadExecutor(getClass().getSimpleName(), this.mDownloadQueueManager, this, immutableList);
        this.mInitializationLatchMedia.complete();
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("PostInitializeWithMediaComponents");
        try {
            if (this.mMediaSystemPauseToken.isPresent()) {
                resume(this.mMediaSystemPauseToken.get());
                this.mMediaSystemPauseToken = Optional.absent();
            }
            this.mReleaseRightsExecutor = MoreExecutors.newDirectExecutorService();
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    protected PauseToken pause(@Nonnull PauseCause pauseCause) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(pauseCause, "cause");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("pause");
        try {
            PauseToken pauseTokenRequestToken = this.mPauseTokenVendor.requestToken(pauseCause);
            DLog.logf("DWNLD_SDK Acquired pause token: %s", pauseTokenRequestToken);
            updateIdleDownloadsBasedOnQueueStatus(pauseCause);
            refreshCurrentTask();
            return pauseTokenRequestToken;
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    public void pausePlaybackSDK(@Nonnull PlaybackDownload playbackDownload) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("pausePlaybackSDK");
        try {
            DLog.logf("DWNLD_SDK pausePlaybackSDK %s", playbackDownload);
            Optional<PlaybackDownload> optionalPauseDownloadSDK = pauseDownloadSDK(playbackDownload);
            if (optionalPauseDownloadSDK.isPresent()) {
                this.mDownloadPersistance.update(optionalPauseDownloadSDK.get());
            }
            refreshCurrentTask();
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    protected void resume(@Nonnull PauseToken pauseToken) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(pauseToken, "token");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("resume");
        try {
            DLog.logf("DWNLD_SDK resume, releasing pause token: %s", pauseToken);
            this.mPauseTokenVendor.releaseToken(pauseToken);
            updateIdleDownloadsBasedOnQueueStatus(pauseToken.getCause().getResumeCause());
            refreshCurrentTask();
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    public void resumePlaybackSDK(@Nonnull PlaybackDownload playbackDownload) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("resumePlaybackSDK");
        try {
            DLog.logf("DWNLD_SDK resumePlaybackSDK %s", playbackDownload);
            if (IDLE_INCOMPLETE_STATES.contains(playbackDownload.getState())) {
                Optional<PlaybackDownload> optionalAttemptTransitionToIdleState = attemptTransitionToIdleState(playbackDownload);
                if (optionalAttemptTransitionToIdleState.isPresent()) {
                    this.mDownloadPersistance.update(optionalAttemptTransitionToIdleState.get());
                }
                refreshCurrentTask();
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private void updateIdleDownloadsBasedOnQueueStatus(@Nonnull Cause cause) {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateIdleDownloadsBasedOnQueueStatus");
        try {
            UnmodifiableIterator<PlaybackDownload> it = this.mDownloadPersistance.getAllDownloads().iterator();
            while (it.hasNext()) {
                PlaybackDownload next = it.next();
                if (IDLE_INCOMPLETE_STATES.contains(next.getState())) {
                    Optional<PlaybackDownload> optionalAttemptTransitionToIdleState = attemptTransitionToIdleState(next);
                    if (optionalAttemptTransitionToIdleState.isPresent()) {
                        this.mPlaybackDownloadEventReporter.reportOperationWithCause(optionalAttemptTransitionToIdleState.get(), cause);
                        this.mDownloadPersistance.update(optionalAttemptTransitionToIdleState.get());
                    } else if (!getPauseStatus().isEmpty()) {
                        this.mPlaybackDownloadEventReporter.reportOperationWithCause(next, cause);
                    }
                }
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private Optional<PlaybackDownload> pauseDownloadSDK(@Nonnull PlaybackDownload playbackDownload) {
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            DLog.warnf("DWNLD_SDK Cannot perform state transition from %s to idle for %s because it isn't in memory anymore", playbackDownload.getState(), playbackDownload);
            this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownload, InternalErrorCause.ILLEGAL_STATE_TRANSITION);
            return Optional.absent();
        }
        PlaybackDownload playbackDownload2 = latestVersion.get();
        UserDownloadState state = playbackDownload2.getState();
        if (!IDLABLE_INCOMPLETE_STATES.contains(state)) {
            return Optional.absent();
        }
        UserDownloadState userDownloadState = UserDownloadState.PAUSED;
        if (state != userDownloadState) {
            return attemptStateTransition(playbackDownload2, userDownloadState, Optional.absent());
        }
        return Optional.absent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Optional<PlaybackDownload> attemptTransitionToIdleState(@Nonnull PlaybackDownload playbackDownload) {
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            DLog.warnf("DWNLD_SDK Cannot perform state transition from %s to idle for %s because it isn't in memory anymore", playbackDownload.getState(), playbackDownload);
            this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownload, InternalErrorCause.ILLEGAL_STATE_TRANSITION);
            return Optional.absent();
        }
        PlaybackDownload playbackDownload2 = latestVersion.get();
        UserDownloadState state = playbackDownload2.getState();
        if (!IDLABLE_INCOMPLETE_STATES.contains(state)) {
            return Optional.absent();
        }
        if (!(!this.mPauseTokenVendor.hasAnyVendedTokens())) {
            UserDownloadState userDownloadState = UserDownloadState.WAITING;
            if (state != userDownloadState) {
                return attemptStateTransition(playbackDownload2, userDownloadState, Optional.absent());
            }
            return Optional.absent();
        }
        UserDownloadState userDownloadState2 = UserDownloadState.QUEUED;
        if (state != userDownloadState2) {
            return attemptStateTransition(playbackDownload2, userDownloadState2, Optional.absent());
        }
        return Optional.absent();
    }

    private Optional<PlaybackDownload> transitionToDeletedState(@Nonnull PlaybackDownload playbackDownload) {
        Preconditions.checkNotNull(playbackDownload, "download");
        Optional<PlaybackDownload> optionalAttemptStateTransition = attemptStateTransition(playbackDownload, UserDownloadState.DELETED, Optional.absent());
        if (!optionalAttemptStateTransition.isPresent()) {
            DLog.warnf("DWNLD_SDK Cannot transition download state to DELETED");
            return Optional.absent();
        }
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("transitionToDeletedState");
        try {
            deleteDownloadRecord(optionalAttemptStateTransition.get());
            PlaybackDownload playbackDownloadNotifyStateUpdate = notifyStateUpdate(optionalAttemptStateTransition.get());
            retryDiskFullErrorsIfNeeded(playbackDownload);
            refreshCurrentTask();
            return Optional.of(playbackDownloadNotifyStateUpdate);
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    @Nonnull
    private PlaybackDownload queue(@Nonnull PlaybackDownloadRequest playbackDownloadRequest, @Nonnull QueueCause queueCause, int i) throws PlaybackDownloadRequest.IllegalPlaybackDownloadRequestException, PlaybackDownloadRequest.DuplicatePlaybackDownloadRequestException {
        PlaybackDownload playbackDownloadNotifyStateUpdate;
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownloadRequest, "request");
        Preconditions.checkNotNull(queueCause, "cause");
        DLog.logf("DWNLD_SDK queue");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("queue");
        try {
            PlaybackDownload playbackDownloadConvertToPlaybackDownload = playbackDownloadRequest.convertToPlaybackDownload();
            Optional<PlaybackDownload> optionalTryFind = Iterables.tryFind(this.mDownloadPersistance.getAllDownloads(playbackDownloadConvertToPlaybackDownload.getAsin()), PlaybackDownloadFilter.newOwnerIdFilter(playbackDownloadConvertToPlaybackDownload.getSessionContext()));
            boolean zPrepareToRedownloadIfNecessary = prepareToRedownloadIfNecessary(playbackDownloadConvertToPlaybackDownload, optionalTryFind);
            if (optionalTryFind.isPresent() && !zPrepareToRedownloadIfNecessary) {
                DLog.logf("DWNLD_SDK %s was not queued because it already exists and shouldn't be redownloaded", optionalTryFind.get());
                playbackDownloadNotifyStateUpdate = optionalTryFind.get();
            } else {
                PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(playbackDownloadConvertToPlaybackDownload).setState(UserDownloadState.QUEUEING).setQueuePosition(getQueuePosition(playbackDownloadConvertToPlaybackDownload) + i).build();
                ListenableFuture<PlaybackDownload> listenableFutureAdd = this.mDownloadPersistance.add(playbackDownloadBuild);
                DLog.logf("DWNLD_SDK Started database insertion for download: %s", playbackDownloadConvertToPlaybackDownload);
                this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownloadConvertToPlaybackDownload, queueCause);
                playbackDownloadNotifyStateUpdate = notifyStateUpdate(playbackDownloadBuild);
                Futures.addCallback(listenableFutureAdd, new DownloadQueueingToQueuedCallback(playbackDownloadNotifyStateUpdate), MoreExecutors.newDirectExecutorService());
            }
            return playbackDownloadNotifyStateUpdate;
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    @Nonnull
    public PlaybackDownload queue(@Nonnull PlaybackDownloadRequest playbackDownloadRequest, @Nonnull QueueCause queueCause) throws PlaybackDownloadRequest.IllegalPlaybackDownloadRequestException, PlaybackDownloadRequest.DuplicatePlaybackDownloadRequestException {
        return queue(playbackDownloadRequest, queueCause, 0);
    }

    private boolean prepareToRedownloadIfNecessary(@Nonnull PlaybackDownload playbackDownload, @Nonnull Optional<PlaybackDownload> optional) throws PlaybackDownloadRequest.DuplicatePlaybackDownloadRequestException {
        this.mExecutorLock.checkLocked("prepareToRedownloadIfNecessary");
        if (!optional.isPresent()) {
            return true;
        }
        Optional<PlaybackDownload> optionalAttemptStateTransition = attemptStateTransition(optional.get(), UserDownloadState.DELETED, Optional.absent());
        if (!optionalAttemptStateTransition.isPresent()) {
            return false;
        }
        deleteDownloadRecord(optionalAttemptStateTransition.get());
        this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownload, RedownloadCause.UNAVAILABLE_DOWNLOAD_QUEUED_TO_NEW_STORAGE_MEDIUM);
        return true;
    }

    private int getQueuePosition(@Nonnull PlaybackDownload playbackDownload) {
        this.mExecutorLock.checkLocked("getQueuePosition");
        UnmodifiableIterator<PlaybackDownload> it = this.mDownloadQueueManager.getDownloadQueueSnapshot().iterator();
        int iMax = 0;
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().getQueuePosition() + 1);
        }
        return iMax;
    }

    private Optional<PlaybackDownload> deleteWithErrorCode(@Nonnull PlaybackDownload playbackDownload, @Nonnull MediaErrorCode mediaErrorCode) {
        this.mInitializationLatch.checkInitialized();
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("deleteWithErrorCode");
        try {
            Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
            boolean zIsPresent = latestVersion.isPresent();
            Preconditions2.checkStateWeakly(zIsPresent, "Cannot re-download the PlaybackDownload: %s", playbackDownload);
            if (!zIsPresent) {
                return Optional.absent();
            }
            PlaybackDownload playbackDownload2 = latestVersion.get();
            File relativePathFromTitleId = this.mPlaybackDownloadLocationConfig.getRelativePathFromTitleId(playbackDownload2.getAsin());
            Pair<UserDownloadLocation, File> bestAvailableAbsolutePath = this.mPlaybackDownloadLocationConfig.getBestAvailableAbsolutePath(playbackDownload2.getUserDownloadLocation(), relativePathFromTitleId);
            Optional<PlaybackDownload> optionalRedownloadWithNewConfig = redownloadWithNewConfig(playbackDownload2, recreateWithNewConfig(playbackDownload2).setState(UserDownloadState.ERROR).setPersistedErrorCode(mediaErrorCode).setDrmAssetId(Optional.absent()).setDrmRecord(Optional.absent()).setStoragePath((UserDownloadLocation) bestAvailableAbsolutePath.first, (File) bestAvailableAbsolutePath.second, Optional.of(relativePathFromTitleId)).build());
            if (optionalRedownloadWithNewConfig.isPresent()) {
                this.mPlaybackDownloadEventReporter.reportDrmRecordRemoved(playbackDownload2, optionalRedownloadWithNewConfig.get());
            }
            return optionalRedownloadWithNewConfig;
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    @Nonnull
    private Optional<PlaybackDownload> redownloadWithNewConfig(@Nonnull PlaybackDownload playbackDownload, @Nonnull PlaybackDownload playbackDownload2) {
        Optional<PlaybackDownload> optionalAttemptStateTransition = attemptStateTransition(playbackDownload, UserDownloadState.DELETE_REQUESTED, Optional.absent());
        if (!optionalAttemptStateTransition.isPresent()) {
            refreshCurrentTask();
            return Optional.absent();
        }
        PlaybackDownload playbackDownload3 = optionalAttemptStateTransition.get();
        this.mDownloadsPendingChange.put(playbackDownload3, playbackDownload2);
        this.mDownloadPersistance.update(playbackDownload3);
        PlaybackDownload playbackDownloadNotifyStateUpdate = notifyStateUpdate(playbackDownload3);
        refreshCurrentTask();
        return Optional.of(playbackDownloadNotifyStateUpdate);
    }

    @Nonnull
    private PlaybackDownload.Builder recreateWithNewConfig(@Nonnull PlaybackDownload playbackDownload) {
        return PlaybackDownload.newBuilder(playbackDownload).setState(UserDownloadState.QUEUED).setPersistedErrorCode(Optional.absent()).setUrl(Optional.absent()).setFileSizeBytes(0L).setSessionId(Optional.absent()).setActualRuntimeinMS(Optional.absent());
    }

    @Nonnull
    public Optional<PlaybackDownload> delete(@Nonnull PlaybackDownload playbackDownload, @Nonnull DeletionCause deletionCause) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(deletionCause, "cause");
        DLog.logf("DWNLD_SDK delete");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("delete");
        try {
            Optional<PlaybackDownload> optionalDeleteLocked = deleteLocked(playbackDownload, deletionCause);
            if (optionalDeleteLocked.isPresent()) {
                this.mPlaybackDownloadEventReporter.reportOperationWithCause(optionalDeleteLocked.get(), deletionCause);
            }
            return optionalDeleteLocked;
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private Optional<PlaybackDownload> deleteLocked(@Nonnull PlaybackDownload playbackDownload, @Nonnull DeletionCause deletionCause) {
        this.mExecutorLock.checkLocked("deleteLocked");
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            return Optional.absent();
        }
        PlaybackDownload playbackDownload2 = latestVersion.get();
        if (IDLE_INCOMPLETE_STATES.contains(playbackDownload2.getState()) && playbackDownload2.getPercentage() == 0.0f) {
            return transitionToDeletedState(playbackDownload2);
        }
        Optional<PlaybackDownload> optionalAttemptStateTransition = attemptStateTransition(playbackDownload2, UserDownloadState.DELETE_REQUESTED, Optional.absent());
        if (optionalAttemptStateTransition.isPresent()) {
            PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(optionalAttemptStateTransition.get()).updateProgress(ProgressMilestone.NOT_READY_TO_WATCH, 0.0f).build();
            this.mDownloadPersistance.update(playbackDownloadBuild);
            PlaybackDownload playbackDownloadNotifyStateUpdate = notifyStateUpdate(playbackDownloadBuild);
            refreshCurrentTask();
            return Optional.of(playbackDownloadNotifyStateUpdate);
        }
        return Optional.absent();
    }

    @Override // com.amazon.avod.download.PlaybackDownloadListenerInternal
    public void updateProgress(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull SampleType sampleType, @Nonnull ContentSession contentSession, long j, long j2) {
        if (sampleType != SampleType.VIDEO_SAMPLE) {
            return;
        }
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateProgress");
        try {
            if (this.mDownloadQueueManager.isJobInQueue(playbackDownloadJob)) {
                updateDownloadProgress(playbackDownloadJob.getDownload(), contentSession, j, j2);
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadProgress(@Nonnull PlaybackDownload playbackDownload, @Nonnull ContentSession contentSession, long j, long j2) {
        this.mExecutorLock.checkLocked("updateDownloadProgress");
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownload, InternalErrorCause.UNEXPECTED_DOWNLOAD_PROGRESS);
            return;
        }
        PlaybackDownload playbackDownload2 = latestVersion.get();
        if (!playbackDownload2.getErrorCode().equals(Optional.of(StandardErrorCode.MEDIA_EJECTED)) || this.mStorageHelper.getExternalDownloadDir().isPresent()) {
            if (playbackDownload2.getState() != UserDownloadState.DOWNLOADING) {
                Preconditions2.failWeakly("Received unexpected progress update for download %s that is in state %s", playbackDownload2, playbackDownload2.getState());
                return;
            }
            DLog.devf("DWNLD_SDK fragment downloaded for %s, Start time: %s nanoseconds end time %s nanoseconds", playbackDownload2, Long.valueOf(j), Long.valueOf(j2));
            ContentSessionContext context = contentSession.getContext();
            ContentUrl contentUrl = context.getContentUrl();
            PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(playbackDownload2).updateProgress(contentSession.getReadyToWatch().canStartWatchingFromTimeInNanos(0L).isReadyToWatch() ? ProgressMilestone.READY_TO_WATCH : ProgressMilestone.NOT_READY_TO_WATCH, (TimeUnit.NANOSECONDS.toSeconds(contentSession.getDownloadedTimeAfterPositionInNanos(0L)) * 100.0f) / context.getDuration().getTotalSeconds()).setUrl(Optional.of(contentUrl.getUrl())).setSessionId(contentUrl.getSessionId()).setActualRuntimeinMS(Optional.of(Long.valueOf(context.getDuration().getTotalMilliseconds()))).setFileSizeBytes(contentSession.getTotalNeededSizeInBytes()).build();
            this.mPlaybackDownloadEventReporter.reportDownload(AloysiusDownloadEventReporter.Action.Update, playbackDownloadBuild);
            boolean z = !playbackDownload2.isReadyToWatch() && playbackDownloadBuild.isReadyToWatch();
            if (z) {
                DLog.logf("DWNLD_SDK Download just became ready to watch: %s", playbackDownloadBuild);
                this.mPlaybackDownloadEventReporter.reportDownloadReadyToWatch(playbackDownloadBuild);
            }
            if (playbackDownload2.getPercentage() == 0.0f || z || ((int) playbackDownloadBuild.getPercentage()) != ((int) playbackDownload2.getPercentage())) {
                this.mDownloadPersistance.update(playbackDownloadBuild);
                this.mDownloadNotifier.notifyProgressUpdate(playbackDownloadBuild);
            }
        }
    }

    @Override // com.amazon.avod.download.PlaybackDownloadListenerInternal
    public void updateDeletionProgress(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnegative int i) {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateDeletionProgress");
        try {
            if (this.mDownloadQueueManager.isJobInQueue(playbackDownloadJob)) {
                Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownloadJob.getDownload());
                if (!latestVersion.isPresent()) {
                    this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownloadJob.getDownload(), InternalErrorCause.UNEXPECTED_DELETION_PROGRESS);
                } else {
                    PlaybackDownload playbackDownload = latestVersion.get();
                    Preconditions2.checkStateWeakly(playbackDownload.getState() == UserDownloadState.DELETING, "Received unexpected deletion progress update for download %s that is in state %s", playbackDownload, playbackDownload.getState());
                    PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(playbackDownload).updateProgress(ProgressMilestone.NOT_READY_TO_WATCH, i).build();
                    this.mDownloadPersistance.update(playbackDownloadBuild);
                    this.mDownloadNotifier.notifyProgressUpdate(playbackDownloadBuild);
                }
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    @Override // com.amazon.avod.download.PlaybackDownloadListenerInternal
    public void onJobStarted(@Nonnull PlaybackDownloadJob playbackDownloadJob) {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("onDownloadJobStarted");
        try {
            if (this.mDownloadQueueManager.isJobInQueue(playbackDownloadJob)) {
                if (playbackDownloadJob.getDownloadableState() == Downloadable.DownloadableState.QUEUED) {
                    PlaybackDownload download = playbackDownloadJob.getDownload();
                    if (download.getUserDownloadLocation() == UserDownloadLocation.SD_CARD && !this.mStorageHelper.getExternalDownloadDir().isPresent()) {
                        markAsErrored(download, StandardErrorCode.MEDIA_EJECTED, MarkAsErroredCause.MEDIA_EJECTED);
                    } else {
                        Optional<PlaybackDownload> optionalAttemptStateTransition = attemptStateTransition(download, UserDownloadState.DOWNLOADING, Optional.absent());
                        if (optionalAttemptStateTransition.isPresent()) {
                            PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(optionalAttemptStateTransition.get()).build();
                            this.mDownloadPersistance.update(playbackDownloadBuild);
                            notifyStateUpdate(playbackDownloadBuild);
                        }
                    }
                } else if (playbackDownloadJob.getDownloadableState() == Downloadable.DownloadableState.DELETE_IMMEDIATELY) {
                    Optional<PlaybackDownload> optionalAttemptStateTransition2 = attemptStateTransition(playbackDownloadJob.getDownload(), UserDownloadState.DELETING, Optional.absent());
                    if (optionalAttemptStateTransition2.isPresent()) {
                        this.mDownloadPersistance.update(optionalAttemptStateTransition2.get());
                        notifyStateUpdate(optionalAttemptStateTransition2.get());
                    }
                }
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    @Override // com.amazon.avod.download.PlaybackDownloadListenerInternal
    public void updateState(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull Downloadable.DownloadableState downloadableState, @Nonnull Optional<MediaErrorCode> optional) {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateState");
        try {
            if (this.mDownloadQueueManager.isJobInQueue(playbackDownloadJob)) {
                updateDownloadState(playbackDownloadJob.getDownload(), downloadableState, optional);
            }
        } finally {
            refreshCurrentTask();
            this.mExecutorLock.unlock(keyLock);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadState(@Nonnull PlaybackDownload playbackDownload, @Nonnull Downloadable.DownloadableState downloadableState, @Nonnull Optional<MediaErrorCode> optional) {
        UserDownloadState state;
        Optional<PlaybackDownload> optionalAttemptTransitionToIdleState;
        Optional<PlaybackDownload> optionalAbsent;
        this.mExecutorLock.checkLocked("updateDownloadState");
        if (!playbackDownload.getErrorCode().equals(Optional.of(StandardErrorCode.MEDIA_EJECTED)) || this.mStorageHelper.getExternalDownloadDir().isPresent()) {
            boolean z = downloadableState == Downloadable.DownloadableState.DELETED;
            PlaybackDownload playbackDownload2 = this.mDownloadsPendingChange.get(playbackDownload);
            if (z && (playbackDownload2 != null)) {
                this.mDownloadsPendingChange.remove(playbackDownload);
                this.mDownloadPersistance.update(playbackDownload2);
                state = playbackDownload2.getState();
                optional = playbackDownload2.getErrorCode();
                notifyStateUpdate(playbackDownload2);
            } else {
                state = DOWNLOADABLE_STATE_TRANSITIONS.get(downloadableState);
            }
            UserDownloadState userDownloadState = UserDownloadState.ERROR;
            if (state == userDownloadState) {
                Preconditions2.checkStateWeakly(optional.isPresent(), "Download has hit an error without explicit error code: %s", playbackDownload);
                StandardErrorCode standardErrorCode = StandardErrorCode.DOWNLOAD_UNKNOWN_ERROR;
                optionalAttemptTransitionToIdleState = handleLicenseError(playbackDownload, optional.or(standardErrorCode));
                if (!optionalAttemptTransitionToIdleState.isPresent()) {
                    optionalAttemptTransitionToIdleState = attemptStateTransition(playbackDownload, userDownloadState, Optional.of(optional.or(standardErrorCode)));
                }
            } else if (state == UserDownloadState.DOWNLOADED) {
                Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
                if (latestVersion.isPresent()) {
                    PlaybackDownload playbackDownload3 = latestVersion.get();
                    DownloadExecutionErrorCode downloadExecutionErrorCodeCheckRefreshLicensePrerequisites = PlaybackDownloadLicenseHelper.checkRefreshLicensePrerequisites(playbackDownload3);
                    Optional<MediaErrorCode> optionalAbsent2 = Optional.absent();
                    if (!playbackDownload3.isReadyToWatch()) {
                        optionalAbsent2 = Optional.of(DownloadExecutionErrorCode.NOT_READY_TO_WATCH);
                    } else if (downloadExecutionErrorCodeCheckRefreshLicensePrerequisites != null) {
                        optionalAbsent2 = Optional.of(downloadExecutionErrorCodeCheckRefreshLicensePrerequisites);
                    } else if (playbackDownload3.getDrmRecord().get().getErrorCode() != null) {
                        optionalAbsent2 = Optional.of(playbackDownload3.getDrmRecord().get().getErrorCode());
                    }
                    if (optionalAbsent2.isPresent()) {
                        state = userDownloadState;
                    }
                    optionalAbsent = attemptStateTransition(playbackDownload3, state, optionalAbsent2);
                } else {
                    optionalAbsent = Optional.absent();
                }
                optionalAttemptTransitionToIdleState = optionalAbsent;
            } else if (state == UserDownloadState.QUEUED || state == UserDownloadState.WAITING) {
                optionalAttemptTransitionToIdleState = attemptTransitionToIdleState(playbackDownload);
            } else {
                optionalAttemptTransitionToIdleState = attemptStateTransition(playbackDownload, state, Optional.absent());
            }
            if (optionalAttemptTransitionToIdleState.isPresent()) {
                PlaybackDownload playbackDownload4 = optionalAttemptTransitionToIdleState.get();
                if (playbackDownload4.getState() == UserDownloadState.DELETED) {
                    deleteDownloadRecord(playbackDownload4);
                    retryDiskFullErrorsIfNeeded(playbackDownload);
                } else {
                    this.mDownloadPersistance.update(playbackDownload4);
                }
                notifyStateUpdate(playbackDownload4);
            }
        }
    }

    private void retryDiskFullErrorsIfNeeded(@Nonnull PlaybackDownload playbackDownload) {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("retryDiskFullErrorsIfNeeded");
        try {
            if (!this.mDownloadQueueManager.getNextDeletion().isPresent() && playbackDownload.getFileSizeBytes() != 0) {
                UnmodifiableIterator<PlaybackDownload> it = this.mDownloadPersistance.getAllDownloads().iterator();
                while (it.hasNext()) {
                    PlaybackDownload next = it.next();
                    if (next.getState() == UserDownloadState.ERROR && DISK_FULL_ERRORS.contains(next.getErrorCode().orNull())) {
                        retryIfPossible(next);
                    }
                }
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private Optional<PlaybackDownload> retryIfPossible(@Nonnull PlaybackDownload playbackDownload) {
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!(latestVersion.isPresent() && latestVersion.get().getState() == UserDownloadState.ERROR)) {
            Preconditions2.failWeakly("Cannot retry a download that is not errored: %s", playbackDownload);
            return Optional.absent();
        }
        PlaybackDownload playbackDownload2 = latestVersion.get();
        if (playbackDownload2.getErrorCode().equals(Optional.of(StandardErrorCode.MEDIA_EJECTED)) && !this.mStorageHelper.getExternalDownloadDir().isPresent()) {
            return Optional.absent();
        }
        Optional<PlaybackDownload> optionalAttemptTransitionToIdleState = attemptTransitionToIdleState(playbackDownload2);
        if (!optionalAttemptTransitionToIdleState.isPresent()) {
            return Optional.absent();
        }
        PlaybackDownload playbackDownload3 = optionalAttemptTransitionToIdleState.get();
        PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(switchDownloadLocationIfApplicable(playbackDownload3).or(playbackDownload3)).build();
        this.mDownloadPersistance.update(playbackDownloadBuild);
        PlaybackDownload playbackDownloadNotifyStateUpdate = notifyStateUpdate(playbackDownloadBuild);
        refreshCurrentTask();
        return Optional.of(playbackDownloadNotifyStateUpdate);
    }

    private ListenableFuture<PlaybackDownload> deleteDownloadRecord(@Nonnull final PlaybackDownload playbackDownload) {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("deleteDownloadRecord");
        try {
            final ImmutableSet<PlaybackDownload> allDownloads = this.mDownloadPersistance.getAllDownloads();
            this.mReleaseRightsExecutor.execute(new Runnable() { // from class: com.amazon.avod.userdownload.internal.PlaybackDownloadExecutor.1ReleaseRights
                @Override // java.lang.Runnable
                public void run() {
                    PlaybackDownloadExecutor.this.mInitializationLatchMedia.waitOnInitializationUninterruptibly();
                    PlaybackDownloadExecutor.this.mUserDownloadLicenseHelper.releaseRights(playbackDownload, allDownloads, LicenseOperationCause.LICENSE_REMOVED_DOWNLOAD_DELETED, RightsOperationCause.RIGHTS_RELEASED_DOWNLOAD_DELETED);
                }
            });
            return this.mDownloadPersistance.remove(playbackDownload);
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private void onExternalStorageStateUnknown() {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("onExternalStorageUnavailable");
        try {
            DLog.logf("DWNLD_SDK External storage state unknown during initialization");
            if (!this.mStorageStateUnknownPauseToken.isPresent()) {
                this.mStorageStateUnknownPauseToken = Optional.of(pause(PauseCause.EXTERNAL_STORAGE_STATE_UNKNOWN));
            }
            reportSDCardAvailability();
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onExternalStorageUnavailable() {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("onExternalStorageUnavailable");
        try {
            updateDownloadsWithPreferredDownloadLocation();
            ImmutableSet.Builder builder = ImmutableSet.builder();
            UnmodifiableIterator<PlaybackDownload> it = this.mDownloadPersistance.getAllDownloads().iterator();
            while (it.hasNext()) {
                PlaybackDownload next = it.next();
                if (next.getUserDownloadLocation() == UserDownloadLocation.SD_CARD && next.getPercentage() > 0.0f) {
                    if (BaseDownloadQueueFactory.DOWNLOAD_STATES.contains(next.getState())) {
                        markAsErrored(next, StandardErrorCode.MEDIA_EJECTED, MarkAsErroredCause.MEDIA_EJECTED);
                    } else {
                        DLog.logf("DWNLD_SDK Download availability is changed due to external storage change: %s", next);
                        builder.add(next);
                    }
                }
            }
            if (this.mStorageStateUnknownPauseToken.isPresent()) {
                resume(this.mStorageStateUnknownPauseToken.get());
                this.mStorageStateUnknownPauseToken = Optional.absent();
            }
            refreshCurrentTask();
            this.mDownloadNotifier.notifyDownloadAvailabilityChanged(builder.build());
            reportSDCardAvailability();
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onExternalStorageAvailable() {
        ImmutableSet<String> allAvailableDownloadKeys = getAllAvailableDownloadKeys();
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("onExternalStorageAvailable");
        try {
            updateDownloadsWithPreferredDownloadLocation();
            ImmutableSet.Builder builder = ImmutableSet.builder();
            UnmodifiableIterator<PlaybackDownload> it = this.mDownloadPersistance.getAllDownloads().iterator();
            while (it.hasNext()) {
                PlaybackDownload next = it.next();
                UserDownloadLocation userDownloadLocation = next.getUserDownloadLocation();
                UserDownloadLocation userDownloadLocation2 = UserDownloadLocation.SD_CARD;
                if (!(userDownloadLocation == userDownloadLocation2 && allAvailableDownloadKeys.contains(next.getOpaqueDownloadKey()))) {
                    if (next.getUserDownloadLocation() == userDownloadLocation2 && next.getPercentage() > 0.0f && BaseDownloadQueueFactory.DOWNLOAD_STATES.contains(next.getState())) {
                        markAsErrored(next, StandardErrorCode.MEDIA_EJECTED, MarkAsErroredCause.MEDIA_EJECTED);
                    }
                } else if (next.getState() == UserDownloadState.DOWNLOADED) {
                    DLog.logf("DWNLD_SDK Download availability is changed due to external storage change: %s", next);
                    builder.add(next);
                } else if (next.getErrorCode().equals(Optional.of(StandardErrorCode.MEDIA_EJECTED))) {
                    Optional<PlaybackDownload> optionalAttemptTransitionToIdleState = attemptTransitionToIdleState(next);
                    if (!optionalAttemptTransitionToIdleState.isPresent()) {
                        Preconditions2.checkStateWeakly(optionalAttemptTransitionToIdleState.isPresent(), "Did not receive a valid download after state transition for download : %s", next);
                    } else {
                        this.mDownloadPersistance.update(optionalAttemptTransitionToIdleState.get());
                        notifyStateUpdate(optionalAttemptTransitionToIdleState.get());
                    }
                }
            }
            if (this.mStorageStateUnknownPauseToken.isPresent()) {
                resume(this.mStorageStateUnknownPauseToken.get());
                this.mStorageStateUnknownPauseToken = Optional.absent();
            }
            refreshCurrentTask();
            this.mDownloadNotifier.notifyDownloadAvailabilityChanged(builder.build());
            reportSDCardAvailability();
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private void reportSDCardAvailability() {
        this.mExecutorLock.checkLocked("reportSDCardAvailability");
        if (this.mStorageHelper.isSDCardSlotPresent()) {
            ImmutableSet<PlaybackDownload> allDownloads = this.mDownloadPersistance.getAllDownloads(new PlaybackDownloadFilter() { // from class: com.amazon.avod.userdownload.internal.PlaybackDownloadExecutor.1SDCardDownloadFilter
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.amazon.avod.userdownload.filter.PlaybackDownloadFilter, com.google.common.base.Predicate
                public boolean apply(@Nonnull PlaybackDownload playbackDownload) {
                    return playbackDownload.getUserDownloadLocation() == UserDownloadLocation.SD_CARD && playbackDownload.getPercentage() > 0.0f;
                }
            });
            if (allDownloads.isEmpty()) {
                return;
            }
            if (!this.mStorageHelper.isExternalStorageStateKnown()) {
                Profiler.incrementCounter("Download:SDCardState:NotKnown");
                return;
            }
            if (this.mStorageHelper.getExternalStoragePathIfWritable().isPresent()) {
                Profiler.incrementCounter("Download:SDCardState:Writable");
                return;
            }
            if (this.mStorageHelper.getExternalStoragePath().isPresent()) {
                Profiler.incrementCounter("Download:SDCardState:ReadOnly");
                UnmodifiableIterator<PlaybackDownload> it = allDownloads.iterator();
                while (it.hasNext()) {
                    this.mPlaybackDownloadEventReporter.reportOperationWithCause(it.next(), InternalErrorCause.SD_CARD_READ_ONLY);
                }
                return;
            }
            Profiler.incrementCounter("Download:SDCardState:NotMounted");
        }
    }

    Optional<PlaybackDownload> getLatestVersion(@Nonnull PlaybackDownload playbackDownload) {
        Optional<PlaybackDownload> optionalTryFind = Iterables.tryFind(this.mDownloadPersistance.getAllDownloads(playbackDownload.getAsin()), PlaybackDownloadFilter.newOwnerIdFilter(playbackDownload.getSessionContext()));
        Preconditions2.checkStateWeakly(optionalTryFind.isPresent(), "Attempting an update for a download that is not in persistence: %s", playbackDownload);
        return optionalTryFind;
    }

    @Nonnull
    private Optional<PlaybackDownload> attemptStateTransition(@Nonnull PlaybackDownload playbackDownload, @Nonnull UserDownloadState userDownloadState, @Nonnull Optional<MediaErrorCode> optional) {
        Preconditions.checkNotNull(playbackDownload, "downloadToUpdate");
        Preconditions.checkNotNull(userDownloadState, "targetState");
        Preconditions.checkNotNull(optional, "mediaErrorCode");
        UserDownloadState userDownloadState2 = UserDownloadState.ERROR;
        boolean z = userDownloadState == userDownloadState2;
        Preconditions.checkState(z == optional.isPresent(), "%s transition must%s include explicit error code", z ? "Error state" : "Non-error state", z ? "" : " not");
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            DLog.warnf("DWNLD_SDK Cannot perform state transition from %s to %s for %s because it isn't in memory anymore", playbackDownload.getState(), userDownloadState, playbackDownload);
            this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownload, InternalErrorCause.ILLEGAL_STATE_TRANSITION);
            return Optional.absent();
        }
        PlaybackDownload playbackDownload2 = latestVersion.get();
        UserDownloadState state = playbackDownload2.getState();
        boolean z2 = z && state == userDownloadState && ALLOWED_ERROR_CODE_OVERRIDES.contains(optional.orNull());
        if (state == userDownloadState && !z2) {
            Object[] objArr = new Object[5];
            objArr[0] = playbackDownload2;
            objArr[1] = state;
            objArr[2] = playbackDownload2.getErrorCode().isPresent() ? String.format(Locale.US, " (%s)", playbackDownload2.getErrorCode().get()) : "";
            objArr[3] = userDownloadState;
            objArr[4] = optional.isPresent() ? String.format(Locale.US, " (%s)", optional.get()) : "";
            DLog.warnf("DWNLD_SDK Cannot perform reflexive state transition for %s (%s%s --> %s%s)", objArr);
            return latestVersion;
        }
        boolean z3 = ALLOWED_STATE_TRANSITIONS.containsEntry(state, userDownloadState) || z2;
        Preconditions2.checkStateWeakly(z3, "Cannot perform illegal state transition for %s from %s to %s", playbackDownload2, state, userDownloadState);
        if (!z3) {
            this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownload2, InternalErrorCause.ILLEGAL_STATE_TRANSITION);
            return Optional.absent();
        }
        PlaybackDownload.Builder state2 = PlaybackDownload.newBuilder(playbackDownload2).setState(userDownloadState);
        if (userDownloadState == userDownloadState2) {
            state2.setPersistedErrorCode(optional);
        } else if (!UserDownloadState.WORK_NEEDED_STATES.contains(userDownloadState)) {
            state2.setPersistedErrorCode(Optional.absent());
        }
        if (userDownloadState == UserDownloadState.DOWNLOADED) {
            state2.updateProgress(ProgressMilestone.COMPLETED, 100.0f);
        }
        PlaybackDownload playbackDownloadBuild = state2.build();
        DLog.logf("DWNLD_SDK Transitioning download %s from state %s to %s", playbackDownload2, state, optional.isPresent() ? String.format(Locale.US, "%s (%s)", userDownloadState, optional) : userDownloadState);
        QALog.newQALog(PlaybackQAEvent.DOWNLOAD_STATE_TRANSITION).addMetric(PlaybackQAMetric.OLD_STATE, state).addMetric(PlaybackQAMetric.NEW_STATE, userDownloadState).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.TITLE_ID, playbackDownload2.getAsin()).send();
        this.mPlaybackDownloadEventReporter.reportDownloadStateTransition(playbackDownload2, playbackDownloadBuild);
        return Optional.of(PlaybackDownload.newBuilder(playbackDownloadBuild).build());
    }

    private class DownloadQueueingToQueuedCallback implements FutureCallback<PlaybackDownload> {
        private final PlaybackDownload mQueueingDownload;

        public DownloadQueueingToQueuedCallback(@Nonnull PlaybackDownload playbackDownload) {
            this.mQueueingDownload = playbackDownload;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onSuccess(PlaybackDownload playbackDownload) {
            if (playbackDownload != null) {
                DLog.logf("DWNLD_SDK %s was persisted successfully", playbackDownload);
                ProfiledLock.Key keyLock = PlaybackDownloadExecutor.this.mExecutorLock.lock("DownloadQueueingToQueuedCallback:onSuccess");
                try {
                    Optional optionalAttemptTransitionToIdleState = PlaybackDownloadExecutor.this.attemptTransitionToIdleState(playbackDownload);
                    if (optionalAttemptTransitionToIdleState.isPresent()) {
                        PlaybackDownloadExecutor.this.mDownloadPersistance.update((PlaybackDownload) optionalAttemptTransitionToIdleState.get());
                        PlaybackDownloadExecutor.this.notifyStateUpdate((PlaybackDownload) optionalAttemptTransitionToIdleState.get());
                    }
                    PlaybackDownloadExecutor.this.refreshCurrentTask();
                } finally {
                    PlaybackDownloadExecutor.this.mExecutorLock.unlock(keyLock);
                }
            }
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(@Nonnull Throwable th) {
            Throwables2.propagateIfWeakMode("Download:QueueingTransitionFailed", th);
        }
    }

    @Nonnull
    public Optional<PlaybackDownload> markAsErrored(@Nonnull PlaybackDownload playbackDownload, @Nonnull MediaErrorCode mediaErrorCode, @Nonnull MarkAsErroredCause markAsErroredCause) {
        Optional<PlaybackDownload> optionalMarkWithErrorCode;
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(mediaErrorCode, "mediaErrorCode");
        Preconditions.checkNotNull(markAsErroredCause, "cause");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("markAsErrored");
        try {
            if (this.mConfig.getMarkErroredMustDeleteDownload().contains(mediaErrorCode.getName())) {
                optionalMarkWithErrorCode = deleteWithErrorCode(playbackDownload, mediaErrorCode);
            } else {
                optionalMarkWithErrorCode = markWithErrorCode(playbackDownload, mediaErrorCode);
            }
            if (optionalMarkWithErrorCode.isPresent()) {
                this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownload, markAsErroredCause);
            }
            return optionalMarkWithErrorCode;
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private Optional<PlaybackDownload> markWithErrorCode(@Nonnull PlaybackDownload playbackDownload, @Nonnull MediaErrorCode mediaErrorCode) {
        Optional<PlaybackDownload> optionalOf;
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(mediaErrorCode, "mediaErrorCode");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("markWithErrorCode");
        try {
            Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
            if (!latestVersion.isPresent()) {
                DLog.logf("DWNLD_SDK Not marking download : %s as errored since it's not present", playbackDownload);
                optionalOf = Optional.absent();
            } else {
                PlaybackDownload playbackDownload2 = latestVersion.get();
                if (!(mediaErrorCode != playbackDownload2.getErrorCode().orNull())) {
                    DLog.logf("DWNLD_SDK Not marking download : %s as errored since it's already errored with the same code (%s)", playbackDownload, mediaErrorCode);
                    optionalOf = Optional.absent();
                } else {
                    Optional<PlaybackDownload> optionalAttemptStateTransition = attemptStateTransition(playbackDownload2, UserDownloadState.ERROR, Optional.of(mediaErrorCode));
                    boolean zIsPresent = optionalAttemptStateTransition.isPresent();
                    Preconditions2.checkStateWeakly(zIsPresent, "Did not receive a valid download after state transition for download : %s", latestVersion.get());
                    if (!zIsPresent) {
                        optionalOf = Optional.absent();
                    } else {
                        PlaybackDownload playbackDownload3 = optionalAttemptStateTransition.get();
                        DLog.logf("DWNLD_SDK Marked following download as errored download: %s, errorCode: %s, previous download status: %s", playbackDownload3, mediaErrorCode, playbackDownload.getState());
                        this.mDownloadPersistance.update(playbackDownload3);
                        PlaybackDownload playbackDownloadNotifyStateUpdate = notifyStateUpdate(playbackDownload3);
                        refreshCurrentTask();
                        optionalOf = Optional.of(playbackDownloadNotifyStateUpdate);
                    }
                }
            }
            return optionalOf;
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    public boolean syncLicenseState(@Nonnull PlaybackDownload playbackDownload, @Nonnull LicenseOperationCause licenseOperationCause) throws LicenseQueryException {
        this.mInitializationLatchMedia.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(licenseOperationCause, "cause");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("syncLicenseState");
        try {
            PlaybackDownload playbackDownloadOrNull = getLatestVersion(playbackDownload).orNull();
            if (playbackDownloadOrNull == null) {
                DLog.logf("DWNLD_SDK syncLicenseState(%s) no-op - download not found. Download: %s", licenseOperationCause, playbackDownload);
                return false;
            }
            if (!playbackDownloadOrNull.isReadyToWatch()) {
                DLog.logf("DWNLD_SDK syncLicenseState(%s) no-op - not ready to watch. Download: %s", licenseOperationCause, playbackDownload);
                return false;
            }
            DownloadExecutionErrorCode downloadExecutionErrorCodeCheckRefreshLicensePrerequisites = PlaybackDownloadLicenseHelper.checkRefreshLicensePrerequisites(playbackDownloadOrNull);
            if (downloadExecutionErrorCodeCheckRefreshLicensePrerequisites != null) {
                PlaybackDownload playbackDownloadOrNull2 = markAsErrored(playbackDownloadOrNull, downloadExecutionErrorCodeCheckRefreshLicensePrerequisites, MarkAsErroredCause.APP_MARK_ERRORED_SYNC_WITH_DRM_STORE).orNull();
                if (playbackDownloadOrNull2 != null && !Objects.equal(playbackDownloadOrNull2.getErrorCode(), playbackDownloadOrNull.getErrorCode())) {
                    this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownloadOrNull2, licenseOperationCause);
                    return true;
                }
                return false;
            }
            this.mExecutorLock.unlock(keyLock);
            DLog.logf("DWNLD_SDK syncLicenseState(%s) syncing with DRM store. Download: %s", licenseOperationCause, playbackDownloadOrNull);
            this.mExecutorLock.checkNotLocked("PlaybackDownloadLicenseHelper#getLicenseState()");
            this.mUserDownloadLicenseHelper.getLicenseState(playbackDownloadOrNull.getDrmAssetId().get());
            keyLock = this.mExecutorLock.lock("syncLicenseState-report");
            try {
                PlaybackDownload playbackDownloadOrNull3 = getLatestVersion(playbackDownload).orNull();
                if (playbackDownloadOrNull3 == null || DrmRecordChangeType.determineChangeType(playbackDownloadOrNull.getDrmRecord().orNull(), playbackDownloadOrNull3.getDrmRecord().orNull()) == null) {
                    return false;
                }
                this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownloadOrNull3, licenseOperationCause);
                return true;
            } finally {
            }
        } finally {
        }
    }

    public void upsertToDrmPersistence(@Nonnull String str, @Nonnull DrmRecord drmRecord) {
        Preconditions.checkNotNull(str, "drmAssetId");
        Preconditions.checkNotNull(drmRecord, "newDrmRecord");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("upsertToDrmPersistence");
        try {
            ImmutableSet<PlaybackDownload> immutableSetCopyOf = ImmutableSet.copyOf(Iterables.filter(this.mDownloadPersistance.getAllDownloads(), PlaybackDownloadFilter.newDrmAssetIdFilter(str)));
            if (immutableSetCopyOf.isEmpty()) {
                return;
            }
            this.mDownloadPersistance.updateDrmRecord(str, drmRecord);
            UnmodifiableIterator<PlaybackDownload> it = immutableSetCopyOf.iterator();
            while (it.hasNext()) {
                PlaybackDownload next = it.next();
                PlaybackDownload playbackDownloadOrNull = getLatestVersion(next).orNull();
                if (playbackDownloadOrNull == null) {
                    Preconditions2.failWeakly("Unexpected state after updating the DrmRecord inside lock - updated download for %s not found.", next);
                } else if (playbackDownloadOrNull.getDrmRecord().orNull() != drmRecord) {
                    Preconditions2.failWeakly("Unexpected state after updating the DrmRecord inside lock - record doesn't match. Download: %s, expected: %s, actual: %s", playbackDownloadOrNull, drmRecord, playbackDownloadOrNull.getDrmRecord().orNull());
                } else {
                    DrmRecord drmRecordOrNull = next.getDrmRecord().orNull();
                    DrmRecordChangeType drmRecordChangeTypeDetermineChangeType = DrmRecordChangeType.determineChangeType(drmRecordOrNull, drmRecord);
                    if (drmRecordChangeTypeDetermineChangeType == null) {
                        DLog.logf("DWNLD_SDK upsertToDrmPersistence() updated record from %s to %s, no major changes. Download: %s", drmRecordOrNull, drmRecord, playbackDownloadOrNull);
                    } else {
                        DLog.logf("DWNLD_SDK upsertToDrmPersistence() change type %s when updating record from %s to %s. Download: %s", drmRecordChangeTypeDetermineChangeType, drmRecordOrNull, drmRecord, playbackDownloadOrNull);
                    }
                    if (drmRecordChangeTypeDetermineChangeType != null) {
                        this.mPlaybackDownloadEventReporter.reportDrmStateChange(drmRecordChangeTypeDetermineChangeType, next, playbackDownloadOrNull, immutableSetCopyOf);
                    }
                    if (drmRecord.getErrorCode() == null) {
                        if (drmRecordOrNull == null || drmRecordOrNull.getErrorCode() != null) {
                            if (playbackDownloadOrNull.getState() == UserDownloadState.ERROR) {
                                retryIfPossible(PlaybackDownload.newBuilder(playbackDownloadOrNull).build());
                            }
                        }
                    } else {
                        markAsErrored(playbackDownloadOrNull, drmRecord.getErrorCode(), MarkAsErroredCause.APP_MARK_ERRORED_LICENSE_LOST);
                    }
                }
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    @Override // com.amazon.avod.download.PlaybackDownloadListenerInternal
    public void updateDrmAssetId(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull String str) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownloadJob, "downloadable");
        Preconditions.checkNotNull(str, "drmAssetId");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateDrmAssetId");
        try {
            updateDownloadDrmAssetId(playbackDownloadJob.getDownload(), str);
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadDrmAssetId(PlaybackDownload playbackDownload, String str) {
        this.mExecutorLock.checkLocked("updateDownloadDrmAssetId");
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            DLog.logf("DWNLD_SDK Not updating DRM asset id for download : %s since it is not present", playbackDownload);
        } else {
            this.mDownloadPersistance.update(PlaybackDownload.newBuilder(latestVersion.get()).setDrmAssetId(Optional.of(str)).build());
        }
    }

    public Optional<PlaybackDownload> handleLicenseError(@Nonnull PlaybackDownload playbackDownload, @Nonnull MediaErrorCode mediaErrorCode) {
        Optional<PlaybackDownload> optionalReleaseRightsAndDisableDownload;
        if (mediaErrorCode != ServiceErrorCode.DOWNLOAD_NOT_OWNED) {
            return Optional.absent();
        }
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("handleLicenseError");
        try {
            PlaybackDownload playbackDownloadOrNull = getLatestVersion(playbackDownload).orNull();
            if (playbackDownloadOrNull == null) {
                DLog.logf("DWNLD_SDK handleLicenseError() no-op - updated download not found; download: %s", playbackDownload);
                optionalReleaseRightsAndDisableDownload = Optional.absent();
            } else if (this.mConfig.shouldDeleteOnDownloadNotOwned()) {
                DLog.logf("DWNLD_SDK handleLicenseError() - received error %s with instruction to delete download: %s", mediaErrorCode, playbackDownloadOrNull);
                optionalReleaseRightsAndDisableDownload = delete(playbackDownloadOrNull, DeletionCause.PRS_DELETE);
            } else {
                DLog.logf("DWNLD_SDK handleLicenseError() - received error %s with instruction to render download unplayable: %s", mediaErrorCode, playbackDownloadOrNull);
                optionalReleaseRightsAndDisableDownload = releaseRightsAndDisableDownload(playbackDownloadOrNull, LicenseOperationCause.LICENSE_REMOVED_PRS_DIRECTIVE, RightsOperationCause.RIGHTS_RELEASED_PRS_DIRECTIVE, mediaErrorCode, MarkAsErroredCause.PRS_RENDER_UNPLAYABLE);
            }
            return optionalReleaseRightsAndDisableDownload;
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private Optional<PlaybackDownload> releaseRightsAndDisableDownload(@Nonnull PlaybackDownload playbackDownload, @Nonnull LicenseOperationCause licenseOperationCause, @Nonnull RightsOperationCause rightsOperationCause, @Nonnull MediaErrorCode mediaErrorCode, @Nonnull MarkAsErroredCause markAsErroredCause) {
        this.mUserDownloadLicenseHelper.releaseRights(playbackDownload, this.mDownloadPersistance.getAllDownloads(), licenseOperationCause, rightsOperationCause);
        PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(playbackDownload).setDrmAssetId(Optional.absent()).setDrmRecord(Optional.absent()).build();
        this.mDownloadPersistance.update(playbackDownloadBuild);
        this.mPlaybackDownloadEventReporter.reportDrmRecordRemoved(playbackDownload, playbackDownloadBuild);
        return markAsErrored(playbackDownloadBuild, mediaErrorCode, markAsErroredCause);
    }

    @Override // com.amazon.avod.download.PlaybackDownloadListenerInternal
    public void updateAudioTrackMetadataList(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull ImmutableList<AudioTrackMetadata> immutableList) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownloadJob, "downloadable");
        Preconditions.checkNotNull(immutableList, "audioTrackMetadataList");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateAudioTrackMetadataList");
        try {
            updateDownloadAudioTrackMetadataList(playbackDownloadJob.getDownload(), immutableList);
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private void updateDownloadAudioTrackMetadataList(@Nonnull PlaybackDownload playbackDownload, @Nonnull ImmutableList<AudioTrackMetadata> immutableList) {
        this.mExecutorLock.checkLocked("updateDownloadAudioTrackMetadataList");
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            DLog.logf("DWNLD_SDK Not updating AudioTrackMetadata for download : %s since it is not present", playbackDownload);
        } else {
            this.mDownloadPersistance.update(PlaybackDownload.newBuilder(latestVersion.get()).setAudioTrackMetadataList(immutableList).build());
        }
    }

    @Override // com.amazon.avod.download.PlaybackDownloadListenerInternal
    public void updateCuepointPlayList(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull CuepointPlaylistInfo cuepointPlaylistInfo) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownloadJob, "downloadable");
        Preconditions.checkNotNull(cuepointPlaylistInfo, "cuepointPlaylistInfo");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateCuepointPlaylistInfo");
        try {
            updateDownloadCuepointPlaylistInfo(playbackDownloadJob.getDownload(), cuepointPlaylistInfo);
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    private void updateDownloadCuepointPlaylistInfo(@Nonnull PlaybackDownload playbackDownload, @Nonnull CuepointPlaylistInfo cuepointPlaylistInfo) {
        this.mExecutorLock.checkLocked("updateCuepointPlaylistInfo");
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            DLog.logf("ServerInsertedPlaybackSession DWNLD Not updating CuepointPlaylistInfo for download : %s since it is not present", playbackDownload);
        } else {
            this.mDownloadPersistance.update(PlaybackDownload.newBuilder(latestVersion.get()).setCuepointPlaylistInfo(cuepointPlaylistInfo).build());
        }
    }

    @Override // com.amazon.avod.download.PlaybackDownloadListenerInternal
    public void updateFileSize(PlaybackDownloadJob playbackDownloadJob, long j) {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateFileSize");
        try {
            updateDownloadFileSize(playbackDownloadJob.getDownload(), j);
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadFileSize(PlaybackDownload playbackDownload, long j) {
        this.mExecutorLock.checkLocked("updateDownloadFileSize");
        Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
        if (!latestVersion.isPresent()) {
            DLog.logf("DWNLD_SDK Not updating fileSize for download : %s since it is not present", playbackDownload);
        } else {
            this.mDownloadPersistance.update(PlaybackDownload.newBuilder(latestVersion.get()).setFileSizeBytes(j).build());
        }
    }

    public ImmutableSet<DrmPersistenceInfo> getAllRecords() {
        this.mInitializationLatchMedia.waitOnInitializationUninterruptibly();
        return this.mUserDownloadLicenseHelper.convertToDrmPersistenceInfo(this.mDownloadPersistance.getAllDownloads());
    }

    public ImmutableSet<DrmPersistenceInfo> getFromDrmPersistence(@Nonnull String str) {
        this.mInitializationLatchMedia.waitOnInitializationUninterruptibly();
        return this.mUserDownloadLicenseHelper.convertToDrmPersistenceInfo(this.mDownloadPersistance.getAllDownloads(), str);
    }

    private void checkInternalDownloadAvailabilityDuringInitialization() {
        this.mInitializationLatch.checkStarted();
        Preconditions.checkState(!this.mInitializationLatch.isInitialized(), "Internal download availability check can only be invoked during initialization");
        if (!this.mConfig.shouldCheckInternalDownloadAvailability()) {
            DLog.logf("DWNLD_SDK Internal download availability check is disabled");
            return;
        }
        UnmodifiableIterator<PlaybackDownload> it = this.mDownloadPersistance.getAllDownloads().iterator();
        while (it.hasNext()) {
            PlaybackDownload next = it.next();
            if (next.getUserDownloadLocation() == UserDownloadLocation.INTERNAL_STORAGE && WATCHABLE_DOWNLOAD_STATES.contains(next.getState()) && next.isReadyToWatch() && !next.getStoragePath().exists()) {
                Optional<PlaybackDownload> optionalAttemptStateTransition = attemptStateTransition(next, UserDownloadState.ERROR, Optional.of(StandardErrorCode.FILE_MISSING));
                DLog.errorf("P55110778 PlaybackDownloadExecutor %s", next.getStoragePath().getAbsolutePath());
                boolean zIsPresent = optionalAttemptStateTransition.isPresent();
                Preconditions2.checkStateWeakly(zIsPresent, "AIVMCCORE-2849: Failed to mark download errored: %s", next);
                if (zIsPresent) {
                    PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(optionalAttemptStateTransition.get()).updateProgress(ProgressMilestone.NOT_READY_TO_WATCH, 0.0f).setFileSizeBytes(0L).build();
                    Profiler.incrementCounter("AIVMCCORE-2849:DownloadContentMissingOnDisk-DownloadCount");
                    DLog.logf("DWNLD_SDK AIVMCCORE-2849: Marked download as errored due to content missing on disk. Download: %s", playbackDownloadBuild);
                    this.mDownloadPersistance.update(playbackDownloadBuild);
                }
            }
        }
    }

    public void updateDownloadsWithPreferredDownloadLocation() {
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("updateDownloadsWithPreferredDownloadLocation");
        try {
            DLog.logf("DWNLD_SDK PlaybackDownloadManager updateDownloadsWithPreferredDownloadLocation %s", this.mPlaybackDownloadLocationConfig.getPreferredDownloadLocation());
            UnmodifiableIterator<PlaybackDownload> it = this.mDownloadPersistance.getAllDownloads().iterator();
            while (it.hasNext()) {
                Optional<PlaybackDownload> optionalSwitchDownloadLocationIfApplicable = switchDownloadLocationIfApplicable(it.next());
                if (optionalSwitchDownloadLocationIfApplicable.isPresent()) {
                    this.mDownloadPersistance.update(optionalSwitchDownloadLocationIfApplicable.get());
                }
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    @Nonnull
    private Optional<PlaybackDownload> switchDownloadLocationIfApplicable(@Nonnull PlaybackDownload playbackDownload) {
        if (!IDLE_INCOMPLETE_STATES.contains(playbackDownload.getState()) || playbackDownload.getPercentage() != 0.0f) {
            return Optional.absent();
        }
        UserDownloadLocation preferredDownloadLocation = this.mPlaybackDownloadLocationConfig.getPreferredDownloadLocation();
        if (preferredDownloadLocation == playbackDownload.getUserDownloadLocation()) {
            return Optional.absent();
        }
        File relativePathFromTitleId = this.mPlaybackDownloadLocationConfig.getRelativePathFromTitleId(playbackDownload.getAsin());
        Pair<UserDownloadLocation, File> bestAvailableAbsolutePath = this.mPlaybackDownloadLocationConfig.getBestAvailableAbsolutePath(preferredDownloadLocation, relativePathFromTitleId);
        return Optional.of(PlaybackDownload.newBuilder(playbackDownload).setStoragePath((UserDownloadLocation) bestAvailableAbsolutePath.first, (File) bestAvailableAbsolutePath.second, Optional.of(relativePathFromTitleId)).build());
    }

    private ImmutableSet<String> getAllAvailableDownloadKeys() {
        this.mExecutorLock.checkNotLocked("getAllAvailableDownloadKeys");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.DEBUG, "%s:CheckDownloadAvailability", getClass().getSimpleName());
        try {
            if (this.mStorageHelper.getExternalStoragePath().orNull() == null) {
                return ImmutableSet.of();
            }
            ImmutableSet.Builder builder = ImmutableSet.builder();
            UnmodifiableIterator<PlaybackDownload> it = this.mDownloadPersistance.getAllDownloads().iterator();
            while (it.hasNext()) {
                PlaybackDownload next = it.next();
                Optional<File> absoluteStoragePathIfAvailable = this.mPlaybackDownloadLocationConfig.getAbsoluteStoragePathIfAvailable(next.getUserDownloadLocation(), next.getRelativeStoragePath().orNull());
                if (next.getUserDownloadLocation() == UserDownloadLocation.SD_CARD && absoluteStoragePathIfAvailable.or(next.getStoragePath()).exists()) {
                    builder.add(next.getOpaqueDownloadKey());
                }
            }
            return builder.build();
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlaybackDownload notifyStateUpdate(@Nonnull PlaybackDownload playbackDownload) {
        this.mExecutorLock.checkLocked("notifyStateUpdate");
        this.mDownloadNotifier.notifyStateUpdate(playbackDownload);
        this.mDownloadPersistance.update(playbackDownload);
        return playbackDownload;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean attemptStateTransitionAndPersistDownload(PlaybackDownload playbackDownload, UserDownloadState userDownloadState) {
        this.mExecutorLock.checkLocked("persistDownload");
        Optional<PlaybackDownload> optionalAttemptStateTransition = attemptStateTransition(playbackDownload, userDownloadState, Optional.absent());
        if (!optionalAttemptStateTransition.isPresent()) {
            return false;
        }
        persistDownload(optionalAttemptStateTransition.get());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void persistDownload(@Nonnull PlaybackDownload playbackDownload) {
        this.mExecutorLock.checkLocked("persistDownload");
        this.mDownloadPersistance.update(playbackDownload);
        notifyStateUpdate(playbackDownload);
    }

    ProfiledLock.Key lock(String str) {
        return this.mExecutorLock.lock(str);
    }

    void checkLocked() {
        this.mExecutorLock.checkLocked("checkLocked");
    }

    void unlock(ProfiledLock.Key key) {
        this.mExecutorLock.unlock(key);
    }

    private class OnExternalStorageMountListener implements ExternalStorageStatusChangeListener {
        private OnExternalStorageMountListener() {
        }

        @Override // com.amazon.avod.fileio.ExternalStorageStatusChangeListener
        public void onExternalStorageMounted() {
            DLog.logf("DWNLD_SDK External storage changed: mounted");
            PlaybackDownloadExecutor.this.onExternalStorageAvailable();
        }

        @Override // com.amazon.avod.fileio.ExternalStorageStatusChangeListener
        public void onExternalStorageUnmounted() {
            DLog.logf("DWNLD_SDK External storage changed: unmounted");
            PlaybackDownloadExecutor.this.onExternalStorageUnavailable();
        }
    }

    public void registerStreamingDownloadEventListener(@Nonnull PlaybackDownload playbackDownload, @Nonnull ContentSession contentSession) {
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(contentSession, "session");
        registerListener(playbackDownload, contentSession);
    }

    private void registerListener(@Nonnull PlaybackDownload playbackDownload, @Nonnull ContentSession contentSession) {
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(contentSession, "session");
        if (playbackDownload.getState() != UserDownloadState.DOWNLOADED) {
            contentSession.setListener(new StreamingDownloadEventListener(this, playbackDownload, contentSession));
        }
    }

    public void refreshCurrentTask() {
        this.mInitializationLatch.checkInitialized();
        if (this.mInitializationLatchMedia.isInitialized()) {
            ProfiledLock.Key keyLock = this.mExecutorLock.lock("refreshCurrentTask");
            try {
                this.mDownloadExecutor.refreshCurrentTask();
            } finally {
                this.mExecutorLock.unlock(keyLock);
            }
        }
    }

    public ImmutableSet<PauseCause> getPauseStatus() {
        this.mInitializationLatch.checkInitialized();
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("getPauseStatus");
        try {
            return this.mPauseTokenVendor.getVendedTokenCauses();
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    public void makeActive(@Nonnull PlaybackDownload playbackDownload, @Nonnull MakeActiveCause makeActiveCause) {
        this.mInitializationLatch.checkInitialized();
        DLog.logf("DWNLD_SDK makeActive");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("makeActive");
        try {
            Optional<PlaybackDownload> latestVersion = getLatestVersion(playbackDownload);
            if (latestVersion.isPresent()) {
                PlaybackDownload playbackDownloadPromoteDownloadToFrontOfQueue = this.mDownloadQueueManager.promoteDownloadToFrontOfQueue(latestVersion.get(), this.mDownloadQueueManager.getDownloadQueueSnapshot());
                refreshCurrentTask();
                DLog.logf("DWNLD_SDK Moved download %s to front of queue", playbackDownloadPromoteDownloadToFrontOfQueue);
                this.mPlaybackDownloadEventReporter.reportOperationWithCause(playbackDownloadPromoteDownloadToFrontOfQueue, makeActiveCause);
            }
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    @Nonnull
    public Optional<PlaybackDownload> retry(@Nonnull PlaybackDownload playbackDownload, @Nonnull RetryCause retryCause) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(retryCause, "cause");
        ProfiledLock.Key keyLock = this.mExecutorLock.lock("retry");
        try {
            DLog.logf("DWNLD_SDK retry download: %s", playbackDownload);
            Optional<PlaybackDownload> optionalRetryIfPossible = retryIfPossible(playbackDownload);
            if (optionalRetryIfPossible.isPresent()) {
                this.mPlaybackDownloadEventReporter.reportOperationWithCause(optionalRetryIfPossible.get(), retryCause);
            }
            return optionalRetryIfPossible;
        } finally {
            this.mExecutorLock.unlock(keyLock);
        }
    }

    static class StreamingDownloadEventListener implements ContentSessionEventListener {
        private final ContentSession mContentSession;
        private final PlaybackDownloadExecutor mPlaybackDownloadExecutor;
        private final PlaybackDownload mUserDownload;

        @Override // com.amazon.avod.content.ContentSessionEventListener
        public void onAuxiliaryDownloadEligibilityStateChanged(boolean z) {
        }

        @Override // com.amazon.avod.content.ContentSessionEventListener
        public void onFatalError(@Nonnull ContentException contentException) {
        }

        public StreamingDownloadEventListener(@Nonnull PlaybackDownloadExecutor playbackDownloadExecutor, @Nonnull PlaybackDownload playbackDownload, @Nonnull ContentSession contentSession) {
            this.mPlaybackDownloadExecutor = (PlaybackDownloadExecutor) Preconditions.checkNotNull(playbackDownloadExecutor, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.mUserDownload = (PlaybackDownload) Preconditions.checkNotNull(playbackDownload, "download");
            this.mContentSession = (ContentSession) Preconditions.checkNotNull(contentSession, "session");
        }

        @Override // com.amazon.avod.content.ContentSessionEventListener
        public void onSessionEnding() {
            ProfiledLock.Key keyLock = this.mPlaybackDownloadExecutor.lock("StreamingDownloadEventListener:onSessionEnding");
            try {
                Optional<PlaybackDownload> latestVersion = this.mPlaybackDownloadExecutor.getLatestVersion(this.mUserDownload);
                if (latestVersion.isPresent() && latestVersion.get().getState() == UserDownloadState.DOWNLOADING) {
                    this.mPlaybackDownloadExecutor.persistDownload((PlaybackDownload) this.mPlaybackDownloadExecutor.attemptTransitionToIdleState(latestVersion.get()).get());
                }
            } finally {
                this.mPlaybackDownloadExecutor.unlock(keyLock);
            }
        }

        @Override // com.amazon.avod.content.ContentSessionEventListener
        public void onContentFragmentDownloaded(@Nonnull SampleType sampleType, long j, long j2) {
            ProfiledLock.Key keyLock = this.mPlaybackDownloadExecutor.lock("StreamingDownloadEventListener:onContentFragmentDownloaded");
            try {
                Optional<PlaybackDownload> latestVersion = this.mPlaybackDownloadExecutor.getLatestVersion(this.mUserDownload);
                if (latestVersion.isPresent()) {
                    UserDownloadState state = latestVersion.get().getState();
                    if ((!(state == UserDownloadState.WAITING || state == UserDownloadState.PAUSED) || shouldUpdate()) && updateDownloadStateIfNecessary(UserDownloadState.DOWNLOADING, Optional.absent())) {
                        this.mPlaybackDownloadExecutor.updateDownloadProgress(this.mUserDownload, this.mContentSession, j, j2);
                    }
                }
            } finally {
                this.mPlaybackDownloadExecutor.unlock(keyLock);
            }
        }

        @Override // com.amazon.avod.content.ContentSessionEventListener
        public void onContentMetadataAcquired(@Nonnull String str) {
            ProfiledLock.Key keyLock = this.mPlaybackDownloadExecutor.lock("StreamingDownloadEventListener:onContentMetadataAcquired");
            try {
                this.mPlaybackDownloadExecutor.updateDownloadDrmAssetId(this.mUserDownload, str);
            } finally {
                this.mPlaybackDownloadExecutor.unlock(keyLock);
            }
        }

        @Override // com.amazon.avod.content.ContentSessionEventListener
        public void onDownloadComplete() {
            long sizeRecursiveInBytes = DiskUtils.getSizeRecursiveInBytes(this.mUserDownload.getStoragePath());
            ProfiledLock.Key keyLock = this.mPlaybackDownloadExecutor.lock("StreamingDownloadEventListener:onDownloadComplete");
            try {
                this.mPlaybackDownloadExecutor.updateDownloadFileSize(this.mUserDownload, sizeRecursiveInBytes);
                this.mPlaybackDownloadExecutor.updateDownloadState(this.mUserDownload, Downloadable.DownloadableState.COMPLETED, Optional.absent());
            } finally {
                this.mPlaybackDownloadExecutor.unlock(keyLock);
                this.mContentSession.setListener(null);
            }
        }

        @Override // com.amazon.avod.content.ContentSessionEventListener
        public void onDownloadStart(long j) {
            if (this.mContentSession.getContext().getSessionType() != ContentSessionType.PROGRESSIVE_PLAYBACK) {
                this.mContentSession.setListener(null);
                return;
            }
            ProfiledLock.Key keyLock = this.mPlaybackDownloadExecutor.lock("StreamingDownloadEventListener:onDownloadStart");
            try {
                Optional<PlaybackDownload> latestVersion = this.mPlaybackDownloadExecutor.getLatestVersion(this.mUserDownload);
                if (latestVersion.isPresent()) {
                    if (!(latestVersion.get().getState() == UserDownloadState.WAITING) || shouldUpdate()) {
                        updateDownloadStateIfNecessary(UserDownloadState.DOWNLOADING, Optional.absent());
                        this.mPlaybackDownloadExecutor.updateDownloadFileSize(this.mUserDownload, j);
                    }
                }
            } finally {
                this.mPlaybackDownloadExecutor.unlock(keyLock);
            }
        }

        private boolean updateDownloadStateIfNecessary(UserDownloadState userDownloadState, Optional<UserDownloadState> optional) {
            this.mPlaybackDownloadExecutor.checkLocked();
            Optional<PlaybackDownload> latestVersion = this.mPlaybackDownloadExecutor.getLatestVersion(this.mUserDownload);
            if (!latestVersion.isPresent()) {
                return false;
            }
            UserDownloadState state = latestVersion.get().getState();
            if (optional.isPresent() && optional.get() != state) {
                return false;
            }
            if (state == userDownloadState) {
                return true;
            }
            return this.mPlaybackDownloadExecutor.attemptStateTransitionAndPersistDownload(latestVersion.get(), userDownloadState);
        }

        private boolean shouldUpdate() {
            return (this.mPlaybackDownloadExecutor.getPauseStatus().size() <= 1) && updateDownloadStateIfNecessary(UserDownloadState.QUEUED, Optional.absent());
        }
    }
}
