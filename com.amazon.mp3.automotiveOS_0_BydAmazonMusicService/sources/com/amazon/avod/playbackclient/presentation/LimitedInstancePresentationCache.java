package com.amazon.avod.playbackclient.presentation;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.UserWatchSessionIdManager;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.playbackclient.presentation.PrepareFailedException;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.ProfiledRunnable;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Uninterruptibles;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class LimitedInstancePresentationCache implements PresentationCache {
    private static final String TRACE_TAG = "LimitedInstancePresentationCache";
    private final ExecutorService mCacheToggleExecutor;
    private final LinkedHashMap<PresentationKey, PresentationBundle> mCachedPresentations;
    private final ExecutorService mExecutor;
    private final AtomicReference<Future<?>> mLastSubmittedPrepareVideoFuture;
    private int mMaxCacheSize;
    private final MediaSystem mMediaSystem;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final PlaybackConfig mPlaybackConfig;
    private final boolean mPreferContentIdForVideoSpecMatch;
    private final boolean mShouldDisableWhisperCacheDuringPreinit;
    private final long mSpecStartTimeMatchThresholdNanos;
    private final UserWatchSessionIdManager mUserWatchSessionIdManager;
    private final Supplier<VideoPresentationFactory> mVideoPresentationFactorySupplier;

    private void checkAbsoluteUrlIsPlayable(@Nonnull VideoSpecification videoSpecification) {
    }

    private static class PresentationKey {
        private final AudioFormat mAudioFormat;
        private final ImmutableList<String> mAudioTrackIds;
        private final ContentType mContentType;
        private final boolean mIsEmbedded;
        private final MediaQuality mMediaQuality;
        private final String mMimeType;
        private final String mTitleId;

        public PresentationKey(@Nonnull VideoSpecification videoSpecification) {
            Preconditions.checkNotNull(videoSpecification, "videoSpecification");
            this.mTitleId = videoSpecification.getTitleId();
            this.mMimeType = videoSpecification.getMimeType();
            this.mMediaQuality = videoSpecification.getMediaQuality();
            this.mContentType = videoSpecification.getContentType();
            this.mAudioFormat = videoSpecification.getAudioFormat();
            this.mAudioTrackIds = videoSpecification.getAudioTrackIds();
            this.mIsEmbedded = videoSpecification.isEmbedded();
        }

        public int hashCode() {
            return Objects.hashCode(this.mTitleId, this.mMimeType, this.mMediaQuality, this.mContentType, this.mAudioFormat, Boolean.valueOf(this.mIsEmbedded));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PresentationKey)) {
                return false;
            }
            PresentationKey presentationKey = (PresentationKey) obj;
            return Objects.equal(this.mTitleId, presentationKey.mTitleId) && Objects.equal(this.mMimeType, presentationKey.mMimeType) && Objects.equal(this.mMediaQuality, presentationKey.mMediaQuality) && Objects.equal(this.mContentType, presentationKey.mContentType) && Objects.equal(this.mAudioFormat, presentationKey.mAudioFormat) && this.mAudioTrackIds.containsAll(presentationKey.mAudioTrackIds) && Objects.equal(Boolean.valueOf(this.mIsEmbedded), Boolean.valueOf(presentationKey.mIsEmbedded));
        }

        public String toString() {
            return MoreObjects.toStringHelper(getClass()).add("titleId", this.mTitleId).add("mimeType", this.mMimeType).add("mediaQuality", this.mMediaQuality).add("contentType", this.mContentType).add(voMimeTypes.VOBASE_TYPE_AUDIO, this.mAudioFormat).add("audioTrackIds", this.mAudioTrackIds).add("isEmbedded", this.mIsEmbedded).toString();
        }
    }

    private static class PresentationBundle {
        private final CachedVideoPresentation mCachedPresentation;
        private boolean mCachedPresentationIsConsumed = false;
        private final VideoSpecification mCachedSpec;
        private final File mCachedStoragePath;

        public PresentationBundle(@Nonnull CachedVideoPresentation cachedVideoPresentation, @Nonnull VideoSpecification videoSpecification, @Nullable File file) {
            this.mCachedPresentation = (CachedVideoPresentation) Preconditions.checkNotNull(cachedVideoPresentation, "cachedPresentation");
            this.mCachedSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "cachedSpec");
            this.mCachedStoragePath = file;
        }
    }

    private static class SingletonHolder {
        public static final LimitedInstancePresentationCache INSTANCE = new LimitedInstancePresentationCache();

        private SingletonHolder() {
        }
    }

    public static LimitedInstancePresentationCache getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private LimitedInstancePresentationCache() {
        this(NetworkConnectionManager.getInstance(), Suppliers.memoize(new VideoPresentationFactorySupplier()), ExecutorBuilder.newBuilderFor(LimitedInstancePresentationCache.class, new String[0]).withFixedThreadPoolSize(1).build(), ExecutorBuilder.newBuilderFor(LimitedInstancePresentationCache.class, "WhisperCacheToggle").withFixedThreadPoolSize(1).build(), new AtomicReference(null), UserWatchSessionIdManager.getInstance(), MediaSystem.getInstance(), PlaybackConfig.getInstance(), PresentationCacheConfig.getInstance());
    }

    private LimitedInstancePresentationCache(@Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull Supplier<VideoPresentationFactory> supplier, @Nonnull ExecutorService executorService, @Nonnull ExecutorService executorService2, @Nonnull AtomicReference<Future<?>> atomicReference, @Nonnull UserWatchSessionIdManager userWatchSessionIdManager, @Nonnull MediaSystem mediaSystem, @Nonnull PlaybackConfig playbackConfig, @Nonnull PresentationCacheConfig presentationCacheConfig) {
        this.mMaxCacheSize = -1;
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mVideoPresentationFactorySupplier = (Supplier) Preconditions.checkNotNull(supplier, "videoPresentationFactorySupplier");
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
        this.mCacheToggleExecutor = (ExecutorService) Preconditions.checkNotNull(executorService2, "cacheToggleExecutor");
        this.mLastSubmittedPrepareVideoFuture = (AtomicReference) Preconditions.checkNotNull(atomicReference, "lastSubmittedPrepareVideoFuture");
        this.mMediaSystem = (MediaSystem) Preconditions.checkNotNull(mediaSystem, "mediaSystem");
        this.mUserWatchSessionIdManager = (UserWatchSessionIdManager) Preconditions.checkNotNull(userWatchSessionIdManager, "userWatchSessionIdManager");
        this.mPlaybackConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mCachedPresentations = new LinkedHashMap<>();
        Preconditions.checkNotNull(presentationCacheConfig, "presentationCacheConfig");
        this.mPreferContentIdForVideoSpecMatch = presentationCacheConfig.preferContentIdForVideoSpecMatch();
        this.mShouldDisableWhisperCacheDuringPreinit = presentationCacheConfig.shouldDisableWhisperCacheDuringPreinit();
        this.mSpecStartTimeMatchThresholdNanos = presentationCacheConfig.getSpecStartTimeMatchThreshold().getTotalNanoSeconds();
    }

    private void computeMaxCacheSize() {
        if (this.mMaxCacheSize != -1) {
            return;
        }
        int i = (this.mPlaybackConfig.isContinuousPlayPreinitEnabled() && this.mMediaSystem.getBaseDrmSystem().isMultiSessionDrmSupported()) ? 2 : 1;
        if (this.mPlaybackConfig.isEmbeddedPlaybackEnabled()) {
            i++;
        }
        this.mMaxCacheSize = i;
    }

    @Override // com.amazon.avod.playbackclient.presentation.PresentationCache
    public void preparePresentationAsync(@Nonnull final VideoSpecification videoSpecification, @Nonnull final VideoOptions videoOptions, @Nullable final File file, @Nullable final VideoPresentationEventListener videoPresentationEventListener) {
        Preconditions.checkNotNull(videoSpecification, "videoSpec");
        Preconditions.checkNotNull(videoOptions, "videoOptions");
        Future<?> andSet = this.mLastSubmittedPrepareVideoFuture.getAndSet(this.mExecutor.submit(new ProfiledRunnable(new Runnable() { // from class: com.amazon.avod.playbackclient.presentation.LimitedInstancePresentationCache.1PrepareVideoTask
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LimitedInstancePresentationCache.this.prepareVideo(file, videoSpecification, videoOptions, videoPresentationEventListener, true);
                } catch (PrepareFailedException e) {
                    Profiler.incrementCounter("PreinitFailure-" + e.getError().getErrorCode().getName());
                    DLog.exceptionf(e, "Failed to pre-init", new Object[0]);
                }
            }
        }, Profiler.TraceLevel.DEBUG, "%s:preparePresentation", TRACE_TAG)));
        if (andSet != null) {
            andSet.cancel(false);
        }
        toggleCaching(false);
    }

    private void toggleCaching(final boolean z) {
        if (this.mShouldDisableWhisperCacheDuringPreinit && this.mMediaSystem.isInitialized()) {
            this.mCacheToggleExecutor.submit(new Runnable() { // from class: com.amazon.avod.playbackclient.presentation.LimitedInstancePresentationCache$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$toggleCaching$0(z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toggleCaching$0(boolean z) {
        DLog.warnf("PresentationCache: toggling whispercache enabled: %s", Boolean.valueOf(z));
        this.mMediaSystem.getVideoCacheManager().setCachingIsEnabled(z);
    }

    @Override // com.amazon.avod.playbackclient.presentation.PresentationCache
    @Nonnull
    public CachedVideoPresentation getPresentation(@Nonnull final VideoSpecification videoSpecification, @Nonnull final VideoOptions videoOptions, @Nullable final File file, @Nullable final VideoPresentationEventListener videoPresentationEventListener) throws PrepareFailedException {
        Preconditions.checkNotNull(videoSpecification, "videoSpec");
        Preconditions.checkNotNull(videoOptions, "videoOptions");
        return getOrThrowPrepareFailed(this.mExecutor.submit(new Callable<CachedVideoPresentation>() { // from class: com.amazon.avod.playbackclient.presentation.LimitedInstancePresentationCache.1GetPresentationTask
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public CachedVideoPresentation call() throws PrepareFailedException {
                LimitedInstancePresentationCache.this.prepareVideo(file, videoSpecification, videoOptions, videoPresentationEventListener, false);
                PresentationBundle presentationFromCache = LimitedInstancePresentationCache.this.getPresentationFromCache(videoSpecification);
                Preconditions2.checkStateWeakly(!presentationFromCache.mCachedPresentationIsConsumed, "PresentationCache: CachedVideoPresentation %s being used more than once!", presentationFromCache.mCachedPresentation);
                if (presentationFromCache.mCachedPresentationIsConsumed) {
                    LimitedInstancePresentationCache.this.destroyPresentationInternal(videoSpecification, false);
                    return LimitedInstancePresentationCache.this.getPresentation(videoSpecification, videoOptions, file, null);
                }
                DLog.logf("PresentationCache: Marking presentation spec: %s and storagePath: %s as consumed and vending it.", presentationFromCache.mCachedSpec, presentationFromCache.mCachedStoragePath);
                presentationFromCache.mCachedPresentationIsConsumed = true;
                return presentationFromCache.mCachedPresentation;
            }
        }));
    }

    @Nonnull
    private CachedVideoPresentation getOrThrowPrepareFailed(@Nonnull Future<CachedVideoPresentation> future) throws PrepareFailedException {
        try {
            return (CachedVideoPresentation) Uninterruptibles.getUninterruptibly(future);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof PrepareFailedException) {
                throw ((PrepareFailedException) cause);
            }
            throw new RuntimeException("PresentationCache: Exception occurred when trying to get presentation", e.getCause());
        }
    }

    @Override // com.amazon.avod.playbackclient.presentation.PresentationCache
    public void destroyPresentationAsync(@Nonnull final VideoSpecification videoSpecification, final boolean z) {
        this.mExecutor.execute(new ProfiledRunnable(new Runnable() { // from class: com.amazon.avod.playbackclient.presentation.LimitedInstancePresentationCache.1DestroyPlaybackTask
            @Override // java.lang.Runnable
            public void run() {
                LimitedInstancePresentationCache.this.destroyPresentationInternal(videoSpecification, z);
            }
        }, Profiler.TraceLevel.DEBUG, "%s:destroyPlayback", TRACE_TAG));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareVideo(@Nullable File file, @Nonnull VideoSpecification videoSpecification, @Nonnull VideoOptions videoOptions, @Nullable VideoPresentationEventListener videoPresentationEventListener, boolean z) throws PrepareFailedException {
        String orCreate;
        Preconditions.checkNotNull(videoSpecification, "spec");
        Preconditions.checkNotNull(videoOptions, "options");
        computeMaxCacheSize();
        Preconditions.checkState(this.mMaxCacheSize != -1, "Max cache size cannot be unset.");
        checkPlayableState(videoSpecification, file);
        PresentationBundle presentationFromCache = getPresentationFromCache(videoSpecification);
        if (presentationFromCache != null) {
            boolean zEqualsIgnoringStartTimePreferContentId = this.mPreferContentIdForVideoSpecMatch ? presentationFromCache.mCachedSpec.equalsIgnoringStartTimePreferContentId(videoSpecification) : presentationFromCache.mCachedSpec.equalsIgnoringStartTime(videoSpecification);
            boolean zStartTimesMatchWithinThreshold = startTimesMatchWithinThreshold(presentationFromCache.mCachedSpec.getStartTime(), videoSpecification.getStartTime());
            boolean zIsErrored = presentationFromCache.mCachedPresentation.isErrored();
            boolean z2 = file == presentationFromCache.mCachedStoragePath;
            boolean z3 = presentationFromCache.mCachedPresentationIsConsumed;
            if (zEqualsIgnoringStartTimePreferContentId && zStartTimesMatchWithinThreshold && !zIsErrored && z2 && !z3) {
                DLog.logf("PresentationCache: Playback already prepared for: spec: %s and storagePath: %s", videoSpecification, file);
                return;
            } else if (!z || !z3) {
                DLog.warnf("PresentationCache: Destroying prepared presentation because of mismatch: isVideoSpecMatching: %s, startTimesMatchWithinThreshold: %s cachedStartTime: %s, requestedStartTime: %s, isCachedPresentationErrored: %s, isStoragePathMatching: %s, cachedStoragePath: %s, requestedStoragePath: %s, isCachedPresentationConsumed: %s, cachedSpec: %s, requestedSpec: %s", Boolean.valueOf(zEqualsIgnoringStartTimePreferContentId), Boolean.valueOf(zStartTimesMatchWithinThreshold), presentationFromCache.mCachedSpec.getStartTime(), videoSpecification.getStartTime(), Boolean.valueOf(zIsErrored), Boolean.valueOf(z2), presentationFromCache.mCachedStoragePath, file, Boolean.valueOf(z3), presentationFromCache.mCachedSpec, videoSpecification);
                destroyPresentationInternal(videoSpecification, false);
            }
        }
        if (this.mCachedPresentations.size() >= this.mMaxCacheSize && !evictOldestPresentation(false)) {
            if (z) {
                throwPrepareFailedCacheFull(videoSpecification, file);
            }
            if (!evictOldestPresentation(true)) {
                throwPrepareFailedCacheFull(videoSpecification, file);
            }
        }
        if (!this.mCachedPresentations.isEmpty()) {
            orCreate = this.mUserWatchSessionIdManager.getOrCreate(this.mCachedPresentations.values().iterator().next().mCachedSpec.getTitleId());
            this.mUserWatchSessionIdManager.set(videoSpecification.getTitleId(), orCreate);
        } else {
            orCreate = this.mUserWatchSessionIdManager.getOrCreate(videoSpecification.getTitleId());
        }
        DLog.logf("PresentationCache: prepareVideo: spec: %s, storagePath: %s, (userWatchSessionId = %s)", videoSpecification, file, orCreate);
        CachedVideoPresentation cachedVideoPresentationCreateCachedPresentation = createCachedPresentation(videoSpecification, file, videoOptions, this, videoPresentationEventListener, z);
        try {
            cachedVideoPresentationCreateCachedPresentation.getPresentation().prepareAsync();
            this.mCachedPresentations.put(new PresentationKey(videoSpecification), new PresentationBundle(cachedVideoPresentationCreateCachedPresentation, videoSpecification, file));
        } catch (MediaException e) {
            DLog.exceptionf(e, "PresentationCache: Exception preparing video presentation", new Object[0]);
            throw new PrepareFailedException(PrepareFailedException.Error.MEDIA_EXCEPTION);
        }
    }

    private boolean evictOldestPresentation(boolean z) {
        for (PresentationBundle presentationBundle : this.mCachedPresentations.values()) {
            if (z || !presentationBundle.mCachedPresentationIsConsumed) {
                DLog.warnf("PresentationCache: Destroying prepared presentation (consumed=%s) for spec: %s and storagePath: %s, because cache is full (size=%s).", Boolean.valueOf(presentationBundle.mCachedPresentationIsConsumed), presentationBundle.mCachedSpec, presentationBundle.mCachedStoragePath, Integer.valueOf(this.mCachedPresentations.size()));
                destroyPresentationInternal(presentationBundle.mCachedSpec, false);
                return true;
            }
        }
        return false;
    }

    private void throwPrepareFailedCacheFull(@Nonnull VideoSpecification videoSpecification, @Nullable File file) throws PrepareFailedException {
        DLog.warnf("PresentationCache: Failed to prepare video presentation for spec: %s and storagePath: %s, cache full, cache size %s.", videoSpecification, file, Integer.valueOf(this.mCachedPresentations.size()));
        throw new PrepareFailedException(PrepareFailedException.Error.PRESENTATION_CACHE_FULL);
    }

    @Override // com.amazon.avod.playbackclient.presentation.PresentationCache
    public void onPresentationPreparedOrErrored() {
        toggleCaching(true);
    }

    private boolean startTimesMatchWithinThreshold(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        return Math.abs(timeSpan.getTotalNanoSeconds() - timeSpan2.getTotalNanoSeconds()) <= this.mSpecStartTimeMatchThresholdNanos;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyPresentationInternal(@Nonnull VideoSpecification videoSpecification, boolean z) {
        PresentationBundle presentationFromCache = getPresentationFromCache(videoSpecification);
        if (presentationFromCache == null) {
            DLog.warnf("PresentationCache: DestroyPresentation np-op, no cached presentation found for %s.", videoSpecification);
            return;
        }
        DLog.logf("PresentationCache: Destroying playback for spec: %s, storagePath: %s", presentationFromCache.mCachedSpec, presentationFromCache.mCachedStoragePath);
        presentationFromCache.mCachedPresentation.getPresentation().terminate(z);
        this.mCachedPresentations.remove(new PresentationKey(videoSpecification));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public PresentationBundle getPresentationFromCache(@Nonnull VideoSpecification videoSpecification) {
        return this.mCachedPresentations.get(new PresentationKey(videoSpecification));
    }

    private void checkPlayableState(@Nonnull VideoSpecification videoSpecification, @Nullable File file) throws PrepareFailedException {
        if (Strings.isNullOrEmpty(videoSpecification.getUrl())) {
            checkAivTitleIdPlaybable(videoSpecification, file);
        } else {
            checkAbsoluteUrlIsPlayable(videoSpecification);
        }
    }

    private void checkAivTitleIdPlaybable(@Nonnull VideoSpecification videoSpecification, @Nullable File file) throws PrepareFailedException {
        if (file != null || this.mNetworkConnectionManager.hasDataConnection()) {
            return;
        }
        DLog.warnf("PresentationCache: Ignoring the call to prepare playback for: %s because there is no connection available.", videoSpecification);
        QALog.newQALog(PlaybackQAEvent.UNEXPECTED_PLAYBACK_FAILURE).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.ERROR_CODE, "No data connection").send();
        throw new PrepareFailedException(PrepareFailedException.Error.PLAYBACK_UNAVAILABLE_NO_DATA_CONNECTION);
    }

    private CachedVideoPresentation createCachedPresentation(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nonnull VideoOptions videoOptions, @Nullable PresentationCache presentationCache, @Nullable VideoPresentationEventListener videoPresentationEventListener, boolean z) {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.DEBUG, TRACE_TAG + ":newVideoPresentation");
        VideoPresentation videoPresentationNewVideoPresentation = this.mVideoPresentationFactorySupplier.get().newVideoPresentation(videoSpecification, file, videoOptions);
        Profiler.endTrace(traceKeyBeginTrace);
        if (videoPresentationEventListener != null) {
            videoPresentationNewVideoPresentation.setListener(videoPresentationEventListener);
        }
        return CachedVideoPresentation.from(videoPresentationNewVideoPresentation, presentationCache, z);
    }

    private static class VideoPresentationFactorySupplier implements Supplier<VideoPresentationFactory> {
        private final MediaSystem mMediaSystem;

        private VideoPresentationFactorySupplier() {
            this.mMediaSystem = MediaSystem.getInstance();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Supplier
        public VideoPresentationFactory get() {
            this.mMediaSystem.waitOnInitializationUninterruptibly();
            return this.mMediaSystem.getVideoPresentationFactory();
        }
    }
}
