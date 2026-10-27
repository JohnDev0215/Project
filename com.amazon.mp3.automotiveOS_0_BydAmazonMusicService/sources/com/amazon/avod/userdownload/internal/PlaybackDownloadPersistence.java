package com.amazon.avod.userdownload.internal;

import android.content.Context;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.core.PlayerInitializationErrorCode;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.ProfiledLock;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackDownloadPersistence {
    private static final ImmutableSet<UserDownloadState> ADD_ALLOWED_STATES;
    private static final ImmutableSet<UserDownloadState> REMOVE_ALLOWED_STATES;
    public static final ImmutableSet<UserDownloadState> UPDATE_ALLOWED_STATES;
    private final PlaybackDownloadDiskPersistence mDiskPersistance;

    @GuardedBy("mLock")
    private PlaybackDownloadMemoryCache mDownloadsMemoryCache;
    private final InitializationLatch mInitializationLatch;
    private final ProfiledLock mLock;

    @GuardedBy("mLock")
    private final ListeningExecutorService mPersistanceExecutor;
    private final StorageHelper mStorageHelper;

    static {
        ImmutableSet immutableSetCopyOf = ImmutableSet.copyOf(UserDownloadState.values());
        UserDownloadState userDownloadState = UserDownloadState.DELETED;
        UserDownloadState userDownloadState2 = UserDownloadState.QUEUEING;
        UPDATE_ALLOWED_STATES = ImmutableSet.copyOf((Collection) Sets.difference(immutableSetCopyOf, ImmutableSet.of(userDownloadState, userDownloadState2)));
        ADD_ALLOWED_STATES = ImmutableSet.of(userDownloadState2);
        REMOVE_ALLOWED_STATES = ImmutableSet.of(userDownloadState);
    }

    public PlaybackDownloadPersistence(@Nonnull StorageHelper storageHelper, @Nonnull PlaybackDownloadDiskPersistence playbackDownloadDiskPersistence) {
        this(storageHelper, playbackDownloadDiskPersistence, MoreExecutors.listeningDecorator(ExecutorBuilder.newBuilderFor(PlaybackDownloadPersistence.class, new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build()));
    }

    public PlaybackDownloadPersistence(@Nonnull StorageHelper storageHelper, @Nonnull PlaybackDownloadDiskPersistence playbackDownloadDiskPersistence, @Nonnull ListeningExecutorService listeningExecutorService) {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mLock = new ProfiledLock(this, ProfiledLock.ThreadUsage.ANY);
        this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
        this.mDiskPersistance = (PlaybackDownloadDiskPersistence) Preconditions.checkNotNull(playbackDownloadDiskPersistence, "diskPersistance");
        this.mPersistanceExecutor = (ListeningExecutorService) Preconditions.checkNotNull(listeningExecutorService, "persistanceExecutor");
    }

    public void initialize(@Nonnull Context context, @Nonnull PlaybackDownloadSharedComponents playbackDownloadSharedComponents) throws InitializationException {
        Preconditions.checkNotNull(context, "context");
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        try {
            this.mDiskPersistance.initialize(context, playbackDownloadSharedComponents);
            PlaybackDownloadMemoryCache all = this.mDiskPersistance.readAll();
            ProfiledLock.Key keyLock = this.mLock.lock("initialize");
            try {
                this.mDownloadsMemoryCache = all;
                this.mLock.unlock(keyLock);
                this.mInitializationLatch.complete();
            } catch (Throwable th) {
                this.mLock.unlock(keyLock);
                throw th;
            }
        } catch (RuntimeException e) {
            throw new InitializationException(PlayerInitializationErrorCode.DOWNLOAD_PERSISTENCE_NOT_READABLE, "Unable to read downloads from persistence store", e);
        }
    }

    public boolean contains(@Nonnull PlaybackDownload playbackDownload) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "userDownload");
        ProfiledLock.Key keyLock = this.mLock.lock("contains");
        try {
            return this.mDownloadsMemoryCache.getIfPresent(playbackDownload).isPresent();
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    @Nonnull
    public ImmutableSet<PlaybackDownload> getAllDownloads() {
        this.mInitializationLatch.checkInitialized();
        ProfiledLock.Key keyLock = this.mLock.lock("getAllDownloads");
        try {
            return this.mDownloadsMemoryCache.getAllDownloads();
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    public ImmutableSet<PlaybackDownload> getAllDownloads(@Nonnull Predicate<PlaybackDownload> predicate) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(predicate, PageUriUtils.FILTER_QUERY_PARAM);
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.DEBUG, "%s:getAllDownloads:%s", getClass().getSimpleName(), predicate.getClass().getSimpleName());
        try {
            return ImmutableSet.copyOf((Collection) Sets.filter(getAllDownloads(), predicate));
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nonnull
    public ImmutableSet<PlaybackDownload> getAllDownloads(@Nonnull String str) {
        this.mInitializationLatch.checkInitialized();
        ProfiledLock.Key keyLock = this.mLock.lock("getAllDownloads:asin");
        try {
            ImmutableSet.Builder builder = ImmutableSet.builder();
            UnmodifiableIterator<PlaybackDownload> it = getAllDownloads().iterator();
            while (it.hasNext()) {
                PlaybackDownload next = it.next();
                if (isAliasTitleIdentifier(str, next.getAsin())) {
                    builder.add(next);
                }
            }
            return builder.build();
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    public boolean isAliasTitleIdentifier(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "titleId1");
        Preconditions.checkNotNull(str2, "titleId2");
        return str.equals(str2);
    }

    public ListenableFuture<PlaybackDownload> add(@Nonnull PlaybackDownload playbackDownload) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        UserDownloadState state = playbackDownload.getState();
        Preconditions.checkState(ADD_ALLOWED_STATES.contains(state), "Cannot add a download record with state %s (%s)", state, playbackDownload);
        ProfiledLock.Key keyLock = this.mLock.lock("add");
        try {
            Preconditions.checkState(!contains(playbackDownload), "Cannot add an item that is already present in persistence: %s", playbackDownload);
            this.mDownloadsMemoryCache.addAndResolve(playbackDownload);
            return this.mPersistanceExecutor.submit((Callable) new InsertDiskPersistence(playbackDownload));
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    public Optional<PlaybackDownload> update(@Nonnull PlaybackDownload playbackDownload) {
        return updateInternal(playbackDownload, null);
    }

    private Optional<PlaybackDownload> updateInternal(@Nonnull PlaybackDownload playbackDownload, @Nullable PlaybackDownload playbackDownload2) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        UserDownloadState state = playbackDownload.getState();
        if (!UPDATE_ALLOWED_STATES.contains(state)) {
            DLog.errorf("Cannot update a download record with state %s (%s)", state, playbackDownload);
            return Optional.absent();
        }
        ProfiledLock.Key keyLock = this.mLock.lock("update");
        try {
            if (playbackDownload2 == null) {
                Preconditions.checkState(contains(playbackDownload), "Cannot update an item that is not already in persistence: %s", playbackDownload);
                this.mDownloadsMemoryCache.update(playbackDownload);
            } else {
                this.mDownloadsMemoryCache.updateWithDifferentDownloadKey(playbackDownload, playbackDownload2);
            }
            this.mPersistanceExecutor.submit((Callable) new UpdateDiskPersistence(playbackDownload));
            this.mLock.unlock(keyLock);
            return Optional.of(playbackDownload);
        } catch (Throwable th) {
            this.mLock.unlock(keyLock);
            throw th;
        }
    }

    public void updateDrmRecord(@Nonnull String str, @Nonnull DrmRecord drmRecord) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "null or empty drmAsset id");
        Preconditions.checkNotNull(drmRecord, "newDrmRecord");
        this.mInitializationLatch.checkInitialized();
        ProfiledLock.Key keyLock = this.mLock.lock("updateDrmRecord");
        try {
            ImmutableSet.Builder builder = new ImmutableSet.Builder();
            UnmodifiableIterator<PlaybackDownload> it = getAllDownloads().iterator();
            while (it.hasNext()) {
                PlaybackDownload next = it.next();
                Optional<String> drmAssetId = next.getDrmAssetId();
                if (drmAssetId.isPresent() && drmAssetId.get().equals(str)) {
                    builder.add(updateCachedDownloadDrmRecordLocked(next, drmRecord));
                }
            }
            ImmutableSet immutableSetBuild = builder.build();
            if (!immutableSetBuild.isEmpty()) {
                this.mPersistanceExecutor.submit((Runnable) new UpdateDrmRecordRunnable(ImmutableSet.copyOf((Collection) immutableSetBuild), str, drmRecord));
            }
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    private PlaybackDownload updateCachedDownloadDrmRecordLocked(@Nonnull PlaybackDownload playbackDownload, @Nonnull DrmRecord drmRecord) {
        DLog.devf("DWNLD_SDK Updating DRM record for download %s from %s to %s", playbackDownload, playbackDownload.getDrmRecord().orNull(), drmRecord);
        PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(playbackDownload).setDrmRecord(Optional.of(drmRecord)).build();
        this.mDownloadsMemoryCache.update(playbackDownloadBuild);
        return playbackDownloadBuild;
    }

    public ListenableFuture<PlaybackDownload> remove(PlaybackDownload playbackDownload) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        UserDownloadState state = playbackDownload.getState();
        Preconditions.checkState(REMOVE_ALLOWED_STATES.contains(state), "Cannot remove a download record with state %s (%s)", state, playbackDownload);
        ProfiledLock.Key keyLock = this.mLock.lock("remove");
        try {
            Preconditions.checkState(this.mDownloadsMemoryCache.getIfPresent(playbackDownload).isPresent(), "Cannot remove an item that is not already in persistence: %s", playbackDownload);
            this.mDownloadsMemoryCache.remove(playbackDownload);
            return this.mPersistanceExecutor.submit((Callable) new DeleteDiskPersistence(playbackDownload));
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    private final class InsertDiskPersistence implements Callable<PlaybackDownload> {
        private final PlaybackDownload mDownload;

        InsertDiskPersistence(@Nonnull PlaybackDownload playbackDownload) {
            this.mDownload = (PlaybackDownload) Preconditions.checkNotNull(playbackDownload, "download");
            Preconditions.checkState(playbackDownload.getState() == UserDownloadState.QUEUEING, "Records for new content may not be written from the %s state (%s)", playbackDownload.getState(), playbackDownload);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public PlaybackDownload call() throws Exception {
            PlaybackDownloadPersistence.this.mDiskPersistance.upsert(this.mDownload);
            return this.mDownload;
        }
    }

    private final class UpdateDiskPersistence implements Callable<PlaybackDownload> {
        private final PlaybackDownload mDownload;

        UpdateDiskPersistence(@Nonnull PlaybackDownload playbackDownload) {
            this.mDownload = playbackDownload;
            Preconditions.checkState(playbackDownload.getState() != UserDownloadState.DELETED, "Records for deleted content should never be written to disk persistence (%s)", playbackDownload);
            Preconditions.checkState(playbackDownload.getState() != UserDownloadState.QUEUEING, "Records for new records must be added before an update can be applied (%s)", playbackDownload);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public PlaybackDownload call() throws Exception {
            PlaybackDownloadPersistence.this.mDiskPersistance.upsert(this.mDownload);
            return this.mDownload;
        }
    }

    private final class DeleteDiskPersistence implements Callable<PlaybackDownload> {
        private final PlaybackDownload mUserDownload;

        DeleteDiskPersistence(@Nonnull PlaybackDownload playbackDownload) {
            this.mUserDownload = playbackDownload;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public PlaybackDownload call() throws Exception {
            PlaybackDownloadPersistence.this.mDiskPersistance.delete(this.mUserDownload);
            return this.mUserDownload;
        }
    }

    private final class UpdateDrmRecordRunnable implements Runnable {
        private final ImmutableSet<PlaybackDownload> mDownloads;
        private final String mDrmAssetId;
        private final DrmRecord mDrmRecord;

        UpdateDrmRecordRunnable(@Nonnull ImmutableSet<PlaybackDownload> immutableSet, @Nonnull String str, @Nonnull DrmRecord drmRecord) {
            this.mDownloads = (ImmutableSet) Preconditions.checkNotNull(immutableSet, "download");
            this.mDrmAssetId = (String) Preconditions.checkNotNull(str, "drmAssetId");
            this.mDrmRecord = (DrmRecord) Preconditions.checkNotNull(drmRecord, "drmRecord");
        }

        @Override // java.lang.Runnable
        public void run() {
            PlaybackDownloadPersistence.this.mDiskPersistance.updateDrmRecord(this.mDownloads, this.mDrmAssetId, this.mDrmRecord);
        }
    }
}
