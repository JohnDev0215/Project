package com.amazon.avod.acos;

import android.content.Context;
import android.os.StatFs;
import com.amazon.avod.acos.internal.GenericPlatformStorage;
import com.amazon.avod.acos.internal.PlatformStorage;
import com.amazon.avod.acos.internal.StructStatProxy;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.fileio.ExternalStorageStatusChangeListener;
import com.amazon.avod.fileio.ExternalStorageStatusChangeReceiver;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.avod.util.Preconditions2;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes2.dex */
public class StorageHelper {
    private static final long EXTERNAL_STORAGE_RETRY_DELAY_MS = TimeUnit.SECONDS.toMillis(1);
    private ExecutorService mClearTrashBinExecutor;
    private ScheduledExecutorService mDiscoverExternalStorageExecutor;

    @GuardedBy("mExternalStorageStateLock")
    private ExternalStoragePaths mExternalStoragePaths;
    private ExternalStorageStatusChangeReceiver mExternalStorageStatusChangeReceiver;

    @GuardedBy("mExternalStorageStateLock")
    private boolean mIsSDCardSlotPresent;
    private PlatformStorage mPlatformStorage;
    private SecondaryStorageUtils mSecondaryStorageUtils;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private final InitializationLatch mExternalStorageInitializationLatch = new InitializationLatch("StorageHelper:ExternalStorage");
    private final Object mExternalStorageStateLock = new Object();
    private final CopyOnWriteArraySet<ExternalStorageStatusChangeListener> mListeners = new CopyOnWriteArraySet<>();

    @GuardedBy("mExternalStorageStateLock")
    private Optional<DiscoverSdCardStatusTask> mDiscoverSdCardStatusTask = Optional.absent();

    public enum StorageLocation {
        INTERNAL_STORAGE,
        SD_CARD
    }

    private static class Holder {
        private static volatile StorageHelper sInstance = new StorageHelper();

        private Holder() {
        }
    }

    public static StorageHelper getInstance() {
        return Holder.sInstance;
    }

    public void initialize(@Nonnull Context context, @Nonnull SecondaryStorageUtils secondaryStorageUtils, boolean z) {
        Preconditions.checkNotNull(context, "context");
        this.mSecondaryStorageUtils = (SecondaryStorageUtils) Preconditions.checkNotNull(secondaryStorageUtils, "secondaryStorageUtils");
        this.mInitializationLatch.start(20L, TimeUnit.SECONDS);
        PlatformStorage platformStorageCreatePlatformStorage = GenericPlatformStorage.createPlatformStorage(context);
        this.mPlatformStorage = platformStorageCreatePlatformStorage;
        disableMediaScanning(platformStorageCreatePlatformStorage.getGeneralFileRootDir());
        synchronized (this.mExternalStorageStateLock) {
            this.mIsSDCardSlotPresent = this.mSecondaryStorageUtils.isSDCardSlotPresent();
            ExternalStoragePaths externalStoragePaths = new ExternalStoragePaths(z);
            this.mExternalStoragePaths = externalStoragePaths;
            if (!this.mIsSDCardSlotPresent) {
                externalStoragePaths.updateStateToUnavailable();
            }
            DLog.logf("StorageHelper: An SD card slot has %s", this.mIsSDCardSlotPresent ? "been detected" : "not been detected yet");
        }
        ExternalStorageStatusChangeReceiver externalStorageStatusChangeReceiver = new ExternalStorageStatusChangeReceiver();
        this.mExternalStorageStatusChangeReceiver = externalStorageStatusChangeReceiver;
        externalStorageStatusChangeReceiver.register(context);
        ScheduledExecutorBuilder scheduledExecutorBuilderWithFixedThreadPoolSize = ScheduledExecutorBuilder.newBuilderFor(this, "DiscoverExternalStorage").withFixedThreadPoolSize(1);
        Profiler.TraceLevel traceLevel = Profiler.TraceLevel.INFO;
        this.mDiscoverExternalStorageExecutor = scheduledExecutorBuilderWithFixedThreadPoolSize.withProfilerTraceLevel(traceLevel).build();
        this.mClearTrashBinExecutor = ExecutorBuilder.newBuilderFor(this, "clearTrashBin").withFixedThreadPoolSize(1).withProfilerTraceLevel(traceLevel).allowCoreThreadExpiry().build();
        this.mInitializationLatch.complete();
        clearTrashBin(new File(getInternalRootDir(), "delete"));
        synchronized (this.mExternalStorageStateLock) {
            this.mDiscoverSdCardStatusTask = Optional.of(new DiscoverSdCardStatusTask());
        }
    }

    public void waitOnInitializationUninterruptibly() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
    }

    public void initializeExternalStorage() {
        DiscoverSdCardStatusTask discoverSdCardStatusTaskOrNull;
        this.mExternalStorageInitializationLatch.start(30L, TimeUnit.SECONDS);
        this.mExternalStorageStatusChangeReceiver.addExternalStorageStatusChangeListener(new ExternalStorageListener());
        synchronized (this.mExternalStorageStateLock) {
            discoverSdCardStatusTaskOrNull = this.mDiscoverSdCardStatusTask.orNull();
        }
        if (discoverSdCardStatusTaskOrNull != null) {
            this.mDiscoverExternalStorageExecutor.execute(discoverSdCardStatusTaskOrNull);
        }
        this.mExternalStorageInitializationLatch.complete();
    }

    public void waitOnExternalStorageInitializationUninterruptibly() {
        this.mExternalStorageInitializationLatch.waitOnInitializationUninterruptibly();
    }

    public void disableMediaScanning(@Nullable File file) {
        this.mInitializationLatch.checkStarted();
        if (file == null) {
            return;
        }
        File file2 = new File(file, ".nomedia");
        boolean z = true;
        if (file2.exists()) {
            z = false;
        } else {
            try {
                if (!file2.createNewFile()) {
                    DLog.warnf("StorageHelper: Could not disable media scanner for: %s", DLog.maskString(file.getAbsolutePath()));
                    return;
                }
            } catch (IOException e) {
                DLog.warnf("StorageHelper: Could not disable media scanner for: %s (%s)", DLog.maskString(file.getAbsolutePath()), e.getMessage());
                return;
            }
        }
        if (z) {
            DLog.logf("StorageHelper: Disabled media scanner for: %s", DLog.maskString(file.getAbsolutePath()));
        } else {
            DLog.devf("StorageHelper: Media scanner was already disabled for: %s", DLog.maskString(file.getAbsolutePath()));
        }
    }

    public File getGeneralFileDir() {
        this.mInitializationLatch.checkInitialized();
        return this.mPlatformStorage.getGeneralFileDir();
    }

    public File getGlobalSharedDir() {
        this.mInitializationLatch.checkInitialized();
        return this.mPlatformStorage.getGlobalFileDir();
    }

    public void runIfClearedDataOnAppStartup(@Nonnull Runnable runnable) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(runnable, "runnable");
        this.mPlatformStorage.runIfClearedDataOnAppStartup(runnable);
    }

    @Nonnull
    public File getInternalDownloadDir() {
        this.mInitializationLatch.checkInitialized();
        return this.mPlatformStorage.getInternalDownloadDir();
    }

    @Nonnull
    public File getInternalPlaybackDownloadDir() {
        this.mInitializationLatch.checkInitialized();
        return this.mPlatformStorage.getInternalPlaybackDownloadDir();
    }

    @Nonnull
    public Optional<File> getExternalDownloadDir() {
        Optional<File> externalStorageDownloadPath;
        this.mInitializationLatch.checkInitialized();
        synchronized (this.mExternalStorageStateLock) {
            externalStorageDownloadPath = this.mExternalStoragePaths.getExternalStorageDownloadPath();
        }
        return externalStorageDownloadPath;
    }

    @Nonnull
    public Optional<File> getExternalPlaybackDownloadDir() {
        Optional<File> externalStoragePlaybackDownloadPath;
        this.mInitializationLatch.checkInitialized();
        synchronized (this.mExternalStorageStateLock) {
            externalStoragePlaybackDownloadPath = this.mExternalStoragePaths.getExternalStoragePlaybackDownloadPath();
        }
        return externalStoragePlaybackDownloadPath;
    }

    @Nonnull
    public Optional<File> getExternalStoragePathIfWritable() {
        Optional<File> externalStoragePathIfWritable;
        this.mInitializationLatch.checkInitialized();
        synchronized (this.mExternalStorageStateLock) {
            externalStoragePathIfWritable = this.mExternalStoragePaths.getExternalStoragePathIfWritable();
        }
        return externalStoragePathIfWritable;
    }

    @Nonnull
    public Optional<File> getExternalStoragePath() {
        Optional<File> externalStoragePath;
        this.mInitializationLatch.checkInitialized();
        synchronized (this.mExternalStorageStateLock) {
            externalStoragePath = this.mExternalStoragePaths.getExternalStoragePath();
        }
        return externalStoragePath;
    }

    public boolean isSDCardSlotPresent() {
        boolean z;
        this.mInitializationLatch.checkInitialized();
        synchronized (this.mExternalStorageStateLock) {
            z = this.mIsSDCardSlotPresent;
        }
        return z;
    }

    public boolean isExternalStorageStateKnown() {
        boolean z;
        this.mInitializationLatch.checkInitialized();
        synchronized (this.mExternalStorageStateLock) {
            z = this.mExternalStoragePaths.getState() != ExternalStoragePaths.ExternalStorageState.UNKNOWN;
        }
        return z;
    }

    public Optional<StorageLocation> inferStorageLocationForFile(File file) {
        Optional<File> externalStoragePathIfWritable;
        Optional<Long> deviceIdForFile = getDeviceIdForFile(file);
        if (!deviceIdForFile.isPresent()) {
            DLog.errorf("StorageHelper: %s failed accessing device id for file", DLog.maskString(file.getAbsolutePath()));
        }
        synchronized (this.mExternalStorageStateLock) {
            externalStoragePathIfWritable = this.mExternalStoragePaths.getExternalStoragePathIfWritable();
        }
        Optional<Long> deviceIdForFile2 = getDeviceIdForFile(getInternalRootDir());
        if (deviceIdForFile.or(0L).equals(deviceIdForFile2.or(0L))) {
            if (!deviceIdForFile2.isPresent()) {
                Profiler.incrementCounter("StructStat-AbsentForInternalDir");
            } else if (deviceIdForFile2.get().longValue() == 0) {
                Profiler.incrementCounter("StructStat-ZeroForInternalDir");
            }
            String strMaskString = DLog.maskString(file.getAbsolutePath());
            StorageLocation storageLocation = StorageLocation.INTERNAL_STORAGE;
            DLog.logf("StorageHelper: file %s on %s", strMaskString, storageLocation);
            return Optional.of(storageLocation);
        }
        if (!externalStoragePathIfWritable.isPresent()) {
            return Optional.absent();
        }
        if (deviceIdForFile.or(0L).equals(getDeviceIdForFile(externalStoragePathIfWritable.get()).or(0L))) {
            String strMaskString2 = DLog.maskString(file.getAbsolutePath());
            StorageLocation storageLocation2 = StorageLocation.SD_CARD;
            DLog.logf("StorageHelper: file %s on %s", strMaskString2, storageLocation2);
            return Optional.of(storageLocation2);
        }
        DLog.errorf("StorageHelper: %s doesn't exist on internal or external storage", DLog.maskString(file.getAbsolutePath()));
        return Optional.absent();
    }

    public static Optional<Long> getDeviceIdForFile(File file) {
        if (file == null) {
            return Optional.absent();
        }
        StructStatProxy.StructStat structStat = StructStatProxy.getStructStat(file.getAbsolutePath());
        return structStat == null ? Optional.absent() : Optional.of(Long.valueOf(structStat.st_dev));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTrashBin(@Nonnull File file) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(file, "trashBin");
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return;
        }
        this.mClearTrashBinExecutor.execute(new DeleteTask(ImmutableSet.copyOf(fileArrListFiles)));
    }

    private File getInternalRootDir() {
        return this.mPlatformStorage.getGeneralFileDir();
    }

    public long getAvailableStorageInBytes(@Nonnull File file) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(file, "file");
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (IllegalArgumentException unused) {
            DLog.warnf("StorageHelper: Someone has removed/unmounted the storage medium: %s", DLog.maskString(file.getAbsolutePath()));
            return 0L;
        }
    }

    public boolean requestStorage(@Nonnegative long j, @Nonnull File file) {
        this.mInitializationLatch.checkInitialized();
        Preconditions2.checkNonNegative(j, "Cannot request a negative amount of storage");
        Preconditions.checkNotNull(file, "location");
        Optional<StorageLocation> optionalInferStorageLocationForFile = inferStorageLocationForFile(file);
        return optionalInferStorageLocationForFile.isPresent() && this.mPlatformStorage.requestStorage(j, optionalInferStorageLocationForFile.get());
    }

    public void addExternalStorageStatusChangeListener(@Nonnull ExternalStorageStatusChangeListener externalStorageStatusChangeListener) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(externalStorageStatusChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mListeners.add(externalStorageStatusChangeListener);
    }

    private class ExternalStorageListener implements ExternalStorageStatusChangeListener {
        private ExternalStorageListener() {
        }

        @Override // com.amazon.avod.fileio.ExternalStorageStatusChangeListener
        public void onExternalStorageMounted() {
            DiscoverSdCardStatusTask discoverSdCardStatusTask;
            synchronized (StorageHelper.this.mExternalStorageStateLock) {
                if (StorageHelper.this.mDiscoverSdCardStatusTask.isPresent()) {
                    ((DiscoverSdCardStatusTask) StorageHelper.this.mDiscoverSdCardStatusTask.get()).cancel();
                }
                discoverSdCardStatusTask = StorageHelper.this.new DiscoverSdCardStatusTask(0);
                StorageHelper.this.mDiscoverSdCardStatusTask = Optional.of(discoverSdCardStatusTask);
            }
            StorageHelper.this.mDiscoverExternalStorageExecutor.execute(discoverSdCardStatusTask);
        }

        @Override // com.amazon.avod.fileio.ExternalStorageStatusChangeListener
        public void onExternalStorageUnmounted() {
            synchronized (StorageHelper.this.mExternalStorageStateLock) {
                StorageHelper.this.mExternalStoragePaths.updateStateToUnavailable();
                if (StorageHelper.this.mDiscoverSdCardStatusTask.isPresent()) {
                    ((DiscoverSdCardStatusTask) StorageHelper.this.mDiscoverSdCardStatusTask.get()).cancel();
                    StorageHelper.this.mDiscoverSdCardStatusTask = Optional.absent();
                }
            }
            Iterator it = StorageHelper.this.mListeners.iterator();
            while (it.hasNext()) {
                ((ExternalStorageStatusChangeListener) it.next()).onExternalStorageUnmounted();
            }
        }
    }

    final class DiscoverSdCardStatusTask implements Runnable {

        @GuardedBy("mExternalStorageStateLock")
        private boolean mCancelled;
        private final int mCurrentTry;
        private final boolean mShouldRetry;

        public DiscoverSdCardStatusTask() {
            this.mCancelled = false;
            this.mShouldRetry = false;
            this.mCurrentTry = 0;
        }

        public DiscoverSdCardStatusTask(int i) {
            this.mCancelled = false;
            this.mShouldRetry = true;
            this.mCurrentTry = i;
        }

        public void cancel() {
            this.mCancelled = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            ExternalStoragePaths.ExternalStorageState state;
            synchronized (StorageHelper.this.mExternalStorageStateLock) {
                if (this.mCancelled) {
                    return;
                }
                Optional<File> externalStoragePath = StorageHelper.this.mSecondaryStorageUtils.getExternalStoragePath();
                Optional<File> externalStoragePathIfWritable = StorageHelper.this.mSecondaryStorageUtils.getExternalStoragePathIfWritable();
                Optional<File> externalPathForUserDownload = StorageHelper.this.mSecondaryStorageUtils.getExternalPathForUserDownload();
                Optional<File> externalPathForPlaybackDownload = StorageHelper.this.mSecondaryStorageUtils.getExternalPathForPlaybackDownload();
                synchronized (StorageHelper.this.mExternalStorageStateLock) {
                    if (this.mCancelled) {
                        return;
                    }
                    StorageHelper.this.mExternalStoragePaths.update(externalStoragePath, externalStoragePathIfWritable, externalPathForUserDownload, externalPathForPlaybackDownload);
                    boolean zAreAllPathsPresent = StorageHelper.this.mExternalStoragePaths.areAllPathsPresent();
                    boolean zAreAllPathsAbsent = StorageHelper.this.mExternalStoragePaths.areAllPathsAbsent();
                    if (externalStoragePathIfWritable.isPresent()) {
                        StorageHelper.this.disableMediaScanning(externalStoragePathIfWritable.get());
                    }
                    if (externalPathForUserDownload.isPresent()) {
                        StorageHelper.this.disableMediaScanning(externalPathForUserDownload.get());
                    }
                    if (!zAreAllPathsAbsent) {
                        DLog.logf("StorageHelper: An SD card slot has been detected");
                        synchronized (StorageHelper.this.mExternalStorageStateLock) {
                            if (!this.mCancelled) {
                                StorageHelper.this.mIsSDCardSlotPresent = true;
                            }
                        }
                        StorageHelper.this.mSecondaryStorageUtils.onSDCardMounted();
                    }
                    if (this.mShouldRetry && !zAreAllPathsPresent && this.mCurrentTry < 10) {
                        scheduleNextRetry();
                        return;
                    }
                    if (!zAreAllPathsAbsent && !zAreAllPathsPresent && this.mCurrentTry < 10) {
                        scheduleNextRetry();
                        return;
                    }
                    if (zAreAllPathsAbsent || zAreAllPathsPresent) {
                        synchronized (StorageHelper.this.mExternalStorageStateLock) {
                            if (this.mCancelled) {
                                return;
                            }
                            synchronized (StorageHelper.this.mExternalStorageStateLock) {
                                state = StorageHelper.this.mExternalStoragePaths.getState();
                                if (!this.mCancelled) {
                                    StorageHelper.this.mDiscoverSdCardStatusTask = Optional.absent();
                                }
                            }
                            StorageHelper.this.notifySdCardStatus(state);
                            DLog.logf("StorageHelper: Updated SD card path: %s (%s)", DLog.maskString(StorageHelper.this.mExternalStoragePaths), externalStoragePathIfWritable.isPresent() ? "Writable" : "Read-Only");
                            if (externalStoragePathIfWritable.isPresent()) {
                                StorageHelper.this.clearTrashBin(new File(externalStoragePathIfWritable.get(), "delete"));
                                return;
                            }
                            return;
                        }
                    }
                    synchronized (StorageHelper.this.mExternalStorageStateLock) {
                        if (!this.mCancelled) {
                            StorageHelper.this.mDiscoverSdCardStatusTask = Optional.absent();
                        }
                    }
                    DLog.warnf("StorageHelper: Could not determine the External Storage Status after multiple retries");
                }
            }
        }

        private void scheduleNextRetry() {
            synchronized (StorageHelper.this.mExternalStorageStateLock) {
                if (this.mCancelled) {
                    return;
                }
                DiscoverSdCardStatusTask discoverSdCardStatusTask = StorageHelper.this.new DiscoverSdCardStatusTask(this.mCurrentTry + 1);
                StorageHelper.this.mDiscoverSdCardStatusTask = Optional.of(discoverSdCardStatusTask);
                StorageHelper.this.mDiscoverExternalStorageExecutor.schedule(discoverSdCardStatusTask, StorageHelper.EXTERNAL_STORAGE_RETRY_DELAY_MS, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySdCardStatus(@Nonnull ExternalStoragePaths.ExternalStorageState externalStorageState) {
        Preconditions.checkNotNull(externalStorageState, "externalStorageState");
        if (this.mIsSDCardSlotPresent) {
            if (externalStorageState == ExternalStoragePaths.ExternalStorageState.AVAILABLE) {
                Iterator<ExternalStorageStatusChangeListener> it = this.mListeners.iterator();
                while (it.hasNext()) {
                    it.next().onExternalStorageMounted();
                }
            } else if (externalStorageState == ExternalStoragePaths.ExternalStorageState.UNAVAILABLE) {
                Iterator<ExternalStorageStatusChangeListener> it2 = this.mListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onExternalStorageUnmounted();
                }
            }
        }
    }

    private static class DeleteTask implements Runnable {
        private final ImmutableSet<File> mFoldersToDelete;

        public DeleteTask(@Nonnull ImmutableSet<File> immutableSet) {
            this.mFoldersToDelete = (ImmutableSet) Preconditions.checkNotNull(immutableSet);
        }

        @Override // java.lang.Runnable
        public void run() {
            UnmodifiableIterator<File> it = this.mFoldersToDelete.iterator();
            while (it.hasNext()) {
                File next = it.next();
                if (next.exists()) {
                    DLog.logf("StorageHelper: Deleting folder %s", DLog.maskString(next));
                    TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "StorageHelper:Delete:%s", next.getName());
                    DiskUtils.deleteFile(next);
                    Profiler.endTrace(traceKeyBeginTrace);
                } else {
                    DLog.warnf("StorageHelper: %s doesn't exist.", DLog.maskString(next.getAbsolutePath()));
                }
            }
        }
    }

    static class ExternalStoragePaths {
        private Optional<File> mExternalStorageDownloadPath;
        private Optional<File> mExternalStoragePath;
        private Optional<File> mExternalStoragePathIfWritable;
        private Optional<File> mExternalStoragePlaybackDownloadPath;
        private ExternalStorageState mExternalStorageState = ExternalStorageState.UNKNOWN;
        private final boolean mIsForceSdCardFailureEnabled;

        public enum ExternalStorageState {
            UNKNOWN,
            UNAVAILABLE,
            AVAILABLE
        }

        ExternalStoragePaths(boolean z) {
            this.mIsForceSdCardFailureEnabled = z;
            resetPaths();
        }

        private void resetPaths() {
            this.mExternalStoragePath = Optional.absent();
            this.mExternalStoragePathIfWritable = Optional.absent();
            this.mExternalStorageDownloadPath = Optional.absent();
            this.mExternalStoragePlaybackDownloadPath = Optional.absent();
        }

        void update(@Nonnull Optional<File> optional, @Nonnull Optional<File> optional2, @Nonnull Optional<File> optional3, @Nonnull Optional<File> optional4) {
            this.mExternalStoragePath = (Optional) Preconditions.checkNotNull(optional, "externalStoragePath");
            this.mExternalStoragePathIfWritable = (Optional) Preconditions.checkNotNull(optional2, "externalStoragePathIfWritable");
            this.mExternalStorageDownloadPath = (Optional) Preconditions.checkNotNull(optional3, "externalStorageDownloadPath");
            this.mExternalStoragePlaybackDownloadPath = (Optional) Preconditions.checkNotNull(optional4, "externalStoragePlaybackDownloadPath");
            if (areAllPathsPresent()) {
                this.mExternalStorageState = ExternalStorageState.AVAILABLE;
            } else if (areAllPathsAbsent()) {
                this.mExternalStorageState = ExternalStorageState.UNAVAILABLE;
            } else {
                this.mExternalStorageState = ExternalStorageState.UNKNOWN;
            }
        }

        boolean areAllPathsPresent() {
            return this.mExternalStoragePath.isPresent() && this.mExternalStoragePathIfWritable.isPresent() && this.mExternalStorageDownloadPath.isPresent() && this.mExternalStoragePlaybackDownloadPath.isPresent() && !this.mIsForceSdCardFailureEnabled;
        }

        boolean areAllPathsAbsent() {
            return (this.mExternalStoragePath.isPresent() || this.mExternalStoragePathIfWritable.isPresent() || this.mExternalStorageDownloadPath.isPresent() || this.mExternalStoragePlaybackDownloadPath.isPresent() || this.mIsForceSdCardFailureEnabled) ? false : true;
        }

        void updateStateToUnavailable() {
            resetPaths();
            this.mExternalStorageState = ExternalStorageState.UNAVAILABLE;
        }

        @Nonnull
        ExternalStorageState getState() {
            return this.mExternalStorageState;
        }

        @Nonnull
        Optional<File> getExternalStoragePath() {
            return this.mExternalStoragePath;
        }

        @Nonnull
        Optional<File> getExternalStoragePathIfWritable() {
            return this.mExternalStoragePathIfWritable;
        }

        @Nonnull
        Optional<File> getExternalStorageDownloadPath() {
            return this.mExternalStorageDownloadPath;
        }

        @Nonnull
        Optional<File> getExternalStoragePlaybackDownloadPath() {
            return this.mExternalStoragePlaybackDownloadPath;
        }
    }
}
