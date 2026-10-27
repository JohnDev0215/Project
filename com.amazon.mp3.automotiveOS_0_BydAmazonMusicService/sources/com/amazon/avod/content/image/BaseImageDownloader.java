package com.amazon.avod.content.image;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.ImageDownloader;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.fileio.DiskIOException;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.BlockingDownloadAdapter;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService;
import com.amazon.avod.media.framework.storage.DiskStorage;
import com.amazon.avod.media.framework.storage.FileLockManager;
import com.amazon.avod.media.framework.util.RollingMedian;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseImageDownloader implements ImageDownloader {
    private static final int ESTIMATED_IMAGE_SIZE_BYTES = (int) DataUnit.KILOBITS.toBytes(10.0f);
    protected final Context mAppContext;
    private int mCancelledCount;
    protected final ImageDownloaderConfig mConfig;
    private final ContentUrlSelector mContentUrlSelector;
    protected final DiskStorage mDiskStorage;
    private int mDownloadCount;
    protected final List<Future<Void>> mDownloadFutureList;
    protected final Map<ImageDownloadAttribute, ImageDownloadLoop> mDownloadLoopMap;
    protected final PrioritizingDownloadService mDownloadService;
    protected final Stopwatch mDownloadTrackerStopwatch;
    protected final TimeSpan mDurationThresholdForCurrentTierMultipass;
    protected ScheduledExecutorService mExecutor;
    private int mFailedCount;
    protected final int mFinestImageDownloadGranularityInSeconds;
    protected final TimeSpan mImageDownloadRetryDelay;
    protected final TimeSpan mImageDownloadTickInterval;
    protected final TimeSpan mImageDownloadTimeout;
    private final List<ImageDownloader.ImageDownloaderListener> mImageDownloaderListeners;
    protected final ImageDownloaderReporter mImageDownloaderReporter;
    protected final ImageFileManager mImageFileManager;
    protected final int mImageHorizontalResolution;
    protected final ImageStreamIndexInterface mImageStreamIndex;
    protected final Set<String> mInProgressDownloads;
    protected boolean mIsCancelled;
    private final boolean mIsMultiPeriodSupported;
    private volatile boolean mIsReadyForConsumption;
    protected boolean mIsRunning;
    private boolean mIsTrimmingInProgress;
    protected ImageDownloadLoop mLastBackwardDownloadLoop;
    private final long mManifestAvailabilityStartTimeMillis;
    protected final long mMaxImageCacheSizeKB;
    private final RollingMedian mMedianDownloadLatencyNanos;
    private final RollingMedian mMedianDownloadSizeBytes;
    private final RollingMedian mMedianDownloadTimeNanos;
    protected final float mMinPercentageRequiredToShowThumbnails;
    protected final Object mMutex;
    protected final File mRootDir;
    private Boolean mSavedIsReady;
    private TimeSpan mSavedTimeToReady;
    protected final ContentSessionContext mSessionContext;
    protected final List<Integer> mSortedImageDownloadGranularitiesInSeconds;
    protected TrickplayManifest mTrickplayManifest;

    public BaseImageDownloader(@Nonnull Context context, @Nonnull ContentSessionContext contentSessionContext, @Nonnull StreamIndex streamIndex, @Nonnull File file, @Nonnull ImageDownloaderReporter imageDownloaderReporter) {
        this(context, contentSessionContext, streamIndex, file, imageDownloaderReporter, new FileLockManager(), new DiskStorage(), ImageDownloaderConfig.getInstance(), PrioritizingDownloadService.getInstance(), new HashSet(), new HashMap(), Lists.newArrayList(), new RollingMedian(), new RollingMedian(), new RollingMedian(), Lists.newArrayList());
    }

    @VisibleForTesting
    BaseImageDownloader(@Nonnull Context context, @Nonnull ContentSessionContext contentSessionContext, @Nonnull StreamIndex streamIndex, @Nonnull File file, @Nonnull ImageDownloaderReporter imageDownloaderReporter, @Nonnull FileLockManager fileLockManager, @Nonnull DiskStorage diskStorage, @Nonnull ImageDownloaderConfig imageDownloaderConfig, @Nonnull PrioritizingDownloadService prioritizingDownloadService, @Nonnull Set<String> set, @Nonnull Map<ImageDownloadAttribute, ImageDownloadLoop> map, @Nonnull List<Future<Void>> list, @Nonnull RollingMedian rollingMedian, @Nonnull RollingMedian rollingMedian2, @Nonnull RollingMedian rollingMedian3, @Nonnull List<ImageDownloader.ImageDownloaderListener> list2) {
        this.mMutex = new Object();
        this.mDownloadTrackerStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());
        this.mIsCancelled = false;
        this.mIsReadyForConsumption = false;
        this.mIsTrimmingInProgress = false;
        Context context2 = (Context) Preconditions.checkNotNull(context, "appContext");
        this.mAppContext = context2;
        ContentSessionContext contentSessionContext2 = (ContentSessionContext) Preconditions.checkNotNull(contentSessionContext, "sessionContext");
        this.mSessionContext = contentSessionContext2;
        this.mManifestAvailabilityStartTimeMillis = contentSessionContext.getManifest().getAvailabilityStartTimeMillis();
        ImageStreamIndexInterface imageStreamIndexInterface = (ImageStreamIndexInterface) CastUtils.castTo(Preconditions.checkNotNull(streamIndex, "imageStreamIndex"), ImageStreamIndexInterface.class);
        this.mImageStreamIndex = imageStreamIndexInterface;
        if (imageStreamIndexInterface == null) {
            throw new IllegalArgumentException("Given stream is not a image stream");
        }
        this.mRootDir = (File) Preconditions.checkNotNull(file, "rootDir");
        this.mImageDownloaderReporter = (ImageDownloaderReporter) Preconditions.checkNotNull(imageDownloaderReporter, "imageDownloaderReporter");
        this.mDiskStorage = (DiskStorage) Preconditions.checkNotNull(diskStorage, "diskStorage");
        this.mImageFileManager = new ImageFileManager(fileLockManager, diskStorage, file);
        ImageDownloaderConfig imageDownloaderConfig2 = (ImageDownloaderConfig) Preconditions.checkNotNull(imageDownloaderConfig, "imageDownloaderConfig");
        this.mConfig = imageDownloaderConfig2;
        this.mDownloadService = (PrioritizingDownloadService) Preconditions.checkNotNull(prioritizingDownloadService, "prioritizingDownloadService");
        this.mInProgressDownloads = (Set) Preconditions.checkNotNull(set, "inProgressDownloads");
        this.mDownloadLoopMap = (Map) Preconditions.checkNotNull(map, "downloadLoopMap");
        this.mDownloadFutureList = (List) Preconditions.checkNotNull(list, "downloadFutureList");
        this.mImageDownloadTimeout = imageDownloaderConfig2.getImageDownloadTimeout();
        this.mImageDownloadRetryDelay = imageDownloaderConfig2.getImageDownloadRetryDelay();
        this.mImageDownloadTickInterval = imageDownloaderConfig2.getImageDownloadTickInterval();
        this.mMaxImageCacheSizeKB = (long) DataUnit.MEGABYTES.toKiloBytes(imageDownloaderConfig2.getMaxImageCacheSizeMB());
        this.mImageHorizontalResolution = imageDownloaderConfig2.getImageHorizontalResolution(context2);
        this.mMinPercentageRequiredToShowThumbnails = imageDownloaderConfig2.getMinPercentageRequiredToShowThumbnails();
        List<Integer> sortedImageDownloadGranularitiesInSeconds = imageDownloaderConfig2.getSortedImageDownloadGranularitiesInSeconds();
        this.mSortedImageDownloadGranularitiesInSeconds = sortedImageDownloadGranularitiesInSeconds;
        this.mFinestImageDownloadGranularityInSeconds = sortedImageDownloadGranularitiesInSeconds.get(sortedImageDownloadGranularitiesInSeconds.size() - 1).intValue();
        this.mIsMultiPeriodSupported = imageDownloaderConfig2.isMultiPeriodSupported();
        this.mDurationThresholdForCurrentTierMultipass = imageDownloaderConfig2.getDurationThresholdForCurrentTierMultipass();
        this.mMedianDownloadSizeBytes = (RollingMedian) Preconditions.checkNotNull(rollingMedian, "medianDownloadSizeBytes");
        this.mMedianDownloadTimeNanos = (RollingMedian) Preconditions.checkNotNull(rollingMedian2, "medianDownloadTimeNanos");
        this.mMedianDownloadLatencyNanos = (RollingMedian) Preconditions.checkNotNull(rollingMedian3, "medianDownloadLatencyNanos");
        this.mImageDownloaderListeners = (List) Preconditions.checkNotNull(list2, "imageDownloaderListeners");
        this.mContentUrlSelector = (ContentUrlSelector) Preconditions.checkNotNull(contentSessionContext2.getContentUrlSelector(), "contentUrlSelector");
    }

    protected boolean validateImageStream() {
        synchronized (this.mMutex) {
            if (this.mIsCancelled) {
                return false;
            }
            if (this.mImageStreamIndex.getNumPeriods() > 1) {
                DLog.logf("ImageDownloader attempting download trickplay images on a multiperiod manifest");
                if (!this.mIsMultiPeriodSupported) {
                    cancelDownload(LiveTrickplayCancellationCause.MULTIPERIOD_MANIFEST);
                    return false;
                }
            }
            for (String str : this.mImageStreamIndex.getAllPeriodIds()) {
                ImageQualityLevelInterface[] sortedImageQualityLevels = this.mImageStreamIndex.getSortedImageQualityLevels(str);
                if (sortedImageQualityLevels.length == 0) {
                    DLog.warnf("periodId: %s no image quality levels - stopping", str);
                    cancelDownload(LiveTrickplayCancellationCause.NO_QUALITY_LEVELS);
                    return false;
                }
                if (((int) TimeUnit.NANOSECONDS.toSeconds(ImageDownloadUtil.selectQualityLevel(sortedImageQualityLevels, this.mFinestImageDownloadGranularityInSeconds).getThumbnailDurationNanos())) > this.mFinestImageDownloadGranularityInSeconds) {
                    DLog.warnf("periodId: %s no finest image quality level - stopping", str);
                    cancelDownload(LiveTrickplayCancellationCause.NO_FINEST_QUALITY_LEVEL);
                    return false;
                }
                for (ImageQualityLevelInterface imageQualityLevelInterface : sortedImageQualityLevels) {
                    if (!validateImageQualityLevel(imageQualityLevelInterface)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    protected boolean validateImageQualityLevel(@Nonnull ImageQualityLevelInterface imageQualityLevelInterface) {
        Preconditions.checkNotNull(imageQualityLevelInterface, "imageQualityLevel");
        if (this.mIsCancelled) {
            return false;
        }
        if (!imageQualityLevelInterface.isInitialized()) {
            DLog.warnf("ImageQuality level is malformed, cancelling trickplay download");
            cancelDownload(LiveTrickplayCancellationCause.QUALITY_LEVEL_UNINITIALIZED);
            return false;
        }
        try {
            if (Strings.isNullOrEmpty(imageQualityLevelInterface.getImageMediaUrl(0))) {
                DLog.warnf("Null or empty base image url, cancelling trickplay download");
                cancelDownload(LiveTrickplayCancellationCause.NULL_OR_EMPTY_URL);
                return false;
            }
            if (imageQualityLevelInterface.getImageDurationNanos() != 0) {
                return true;
            }
            DLog.warnf("Image duration is 0, cancelling trickplay download");
            cancelDownload(LiveTrickplayCancellationCause.ZERO_IMAGE_DURATION);
            return false;
        } catch (IllegalArgumentException e) {
            DLog.exceptionf(e, "Catching IllegalArgumentException while trying to extract image url: ", new Object[0]);
            cancelDownload(LiveTrickplayCancellationCause.NULL_OR_EMPTY_URL);
            return false;
        }
    }

    private void cancelDownload(@Nonnull LiveTrickplayCancellationCause liveTrickplayCancellationCause) {
        synchronized (this.mMutex) {
            this.mImageDownloaderReporter.reportCancelled(liveTrickplayCancellationCause);
            this.mIsCancelled = true;
            notifyListeners(false, new TimeSpan(this.mDownloadTrackerStopwatch));
        }
    }

    @Override // com.amazon.avod.content.smoothstream.ImageDownloader
    public void stop() {
        synchronized (this.mMutex) {
            if (this.mIsRunning) {
                this.mIsRunning = false;
                Iterator<ImageDownloadLoop> it = this.mDownloadLoopMap.values().iterator();
                while (it.hasNext()) {
                    it.next().stopLoop();
                }
                this.mDownloadLoopMap.clear();
                Iterator<Future<Void>> it2 = this.mDownloadFutureList.iterator();
                while (it2.hasNext()) {
                    it2.next().cancel(true);
                }
                this.mDownloadFutureList.clear();
                this.mImageDownloaderListeners.clear();
                this.mExecutor.shutdownNow();
                DLog.logf("%s stopped.", getClass().getSimpleName());
            }
        }
    }

    protected long getImageCacheSizeKB() {
        return (long) DataUnit.BYTES.toKiloBytes(DiskUtils.getSizeRecursiveInBytes(this.mRootDir));
    }

    @Override // com.amazon.avod.content.smoothstream.ImageDownloader
    public void dispose() {
        synchronized (this.mMutex) {
            Preconditions.checkState(!this.mIsRunning, "Can't call dispose() when running!");
            try {
                this.mDiskStorage.delete(this.mRootDir.getAbsolutePath());
            } catch (DiskIOException e) {
                DLog.warnf("%s failed to delete downloaded content at: %s, error: %s", getClass().getSimpleName(), this.mRootDir.getAbsolutePath(), e);
            }
            int kiloBits = ((long) this.mMedianDownloadTimeNanos.getSum()) == 0 ? 0 : (int) DataUnit.BITS.toKiloBits(DownloadStatistics.getBitRateSeconds((long) this.mMedianDownloadSizeBytes.getSum(), r1));
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            this.mImageDownloaderReporter.reportDisposed(this.mDownloadCount, this.mFailedCount, this.mCancelledCount, kiloBits, timeUnit.toMillis((long) this.mMedianDownloadTimeNanos.getMedian()), timeUnit.toMillis((long) this.mMedianDownloadLatencyNanos.getMedian()), getImageDownloaderStrategy());
            this.mDownloadCount = 0;
            this.mFailedCount = 0;
            this.mCancelledCount = 0;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.ImageDownloader
    @Nonnull
    public TrickplayManifest getTrickplayManifest() {
        TrickplayManifest trickplayManifest;
        synchronized (this.mMutex) {
            trickplayManifest = this.mTrickplayManifest;
        }
        return trickplayManifest;
    }

    public void addListener(@Nonnull ImageDownloader.ImageDownloaderListener imageDownloaderListener) {
        synchronized (this.mImageDownloaderListeners) {
            this.mImageDownloaderListeners.add((ImageDownloader.ImageDownloaderListener) Preconditions.checkNotNull(imageDownloaderListener, "imageDownloaderListener"));
            if (this.mSavedTimeToReady != null) {
                imageDownloaderListener.onStatusUpdated(this.mSavedIsReady.booleanValue(), this.mSavedTimeToReady, getImageDownloaderStrategy());
            }
        }
    }

    public void notifyListeners(boolean z, @Nonnull TimeSpan timeSpan) {
        synchronized (this.mImageDownloaderListeners) {
            this.mSavedIsReady = Boolean.valueOf(z);
            this.mSavedTimeToReady = (TimeSpan) Preconditions.checkNotNull(timeSpan, "timeToReady");
            Iterator<ImageDownloader.ImageDownloaderListener> it = this.mImageDownloaderListeners.iterator();
            while (it.hasNext()) {
                it.next().onStatusUpdated(z, timeSpan, getImageDownloaderStrategy());
            }
        }
    }

    protected long getThumbnailTimecodeMillis(long j, int i, @Nonnull ImageQualityLevelInterface imageQualityLevelInterface) {
        Preconditions.checkNotNull(imageQualityLevelInterface, "qualityLevel");
        return this.mManifestAvailabilityStartTimeMillis + TimeUnit.NANOSECONDS.toMillis(imageQualityLevelInterface.getThumbnailTimeInNanos(j, i));
    }

    protected boolean isAfterWindowEnd(long j, @Nonnull ImageQualityLevelInterface imageQualityLevelInterface) {
        Preconditions.checkNotNull(imageQualityLevelInterface, "qualityLevel");
        return j > (TimeUnit.NANOSECONDS.toMillis(this.mSessionContext.getState().getMediaTimeWindowEndNanos()) + this.mManifestAvailabilityStartTimeMillis) + TimeUnit.SECONDS.toMillis((long) imageQualityLevelInterface.getAvailabilityTimeOffsetSeconds());
    }

    protected boolean isBeforeWindowStart(long j) {
        return j < TimeUnit.NANOSECONDS.toMillis(this.mSessionContext.getState().getMediaTimeWindowStartNanos()) + this.mManifestAvailabilityStartTimeMillis;
    }

    protected boolean isChunkAvailable(long j, @Nonnull ImageQualityLevelInterface imageQualityLevelInterface) {
        Preconditions.checkNotNull(imageQualityLevelInterface, "imageQualityLevel");
        return !isAfterWindowEnd(getThumbnailTimecodeMillis(j, 0, imageQualityLevelInterface) + TimeUnit.NANOSECONDS.toMillis(imageQualityLevelInterface.getImageDurationNanos()), imageQualityLevelInterface);
    }

    protected class ImageDownloadLoop implements Callable<Void> {
        private final BlockingDownloadAdapter mBlockingDownloadAdapter;
        private long mChunkIndex;
        private final HashSet<String> mDownloadedImageUrls;
        private int mEstimatedSizeBytes;
        private final ImageDownloadAttribute mImageDownloadAttribute;
        private ImageQualityLevelInterface mImageQualityLevel;
        private volatile boolean mIsRunning;
        private int mLoopCancelledCount;
        private int mLoopDownloadCount;
        private long mLoopDownloadLatencyNanos;
        private long mLoopDownloadSizeBytes;
        private long mLoopDownloadTimeNanos;
        private int mLoopFailedCount;
        private String mPeriodId;
        private int mRetryCount;
        private final ImageDownloadRetryPolicy mRetryPolicy;
        private int mThumbnailIndex;

        protected ImageDownloadLoop(@Nonnull BaseImageDownloader baseImageDownloader, @Nonnull ImageQualityLevelInterface imageQualityLevelInterface, @Nonnull BlockingDownloadAdapter blockingDownloadAdapter, ImageDownloadAttribute imageDownloadAttribute, @Nonnull long j, String str, int i) {
            this(imageQualityLevelInterface, blockingDownloadAdapter, imageDownloadAttribute, j, str, i, new ImageDownloadRetryPolicy(imageDownloadAttribute, baseImageDownloader.mImageDownloaderReporter));
        }

        private ImageDownloadLoop(@Nonnull ImageQualityLevelInterface imageQualityLevelInterface, @Nonnull BlockingDownloadAdapter blockingDownloadAdapter, @Nonnull ImageDownloadAttribute imageDownloadAttribute, long j, @Nonnull String str, int i, @Nonnull ImageDownloadRetryPolicy imageDownloadRetryPolicy) {
            this.mDownloadedImageUrls = Sets.newHashSet();
            this.mEstimatedSizeBytes = BaseImageDownloader.ESTIMATED_IMAGE_SIZE_BYTES;
            this.mIsRunning = true;
            this.mImageQualityLevel = (ImageQualityLevelInterface) Preconditions.checkNotNull(imageQualityLevelInterface, "startImageQualityLevel");
            this.mBlockingDownloadAdapter = (BlockingDownloadAdapter) Preconditions.checkNotNull(blockingDownloadAdapter, "blockingDownloadAdapter");
            this.mImageDownloadAttribute = (ImageDownloadAttribute) Preconditions.checkNotNull(imageDownloadAttribute);
            this.mChunkIndex = j;
            this.mPeriodId = (String) Preconditions.checkNotNull(str, "startPeriod");
            this.mThumbnailIndex = i;
            this.mRetryPolicy = (ImageDownloadRetryPolicy) Preconditions.checkNotNull(imageDownloadRetryPolicy, "retryPolicy");
        }

        public void stopLoop() {
            if (this.mIsRunning) {
                this.mIsRunning = false;
                this.mBlockingDownloadAdapter.cancel();
                ImageDownloadAttribute imageDownloadAttribute = this.mImageDownloadAttribute;
                Integer numValueOf = Integer.valueOf(this.mLoopDownloadCount);
                Integer numValueOf2 = Integer.valueOf(this.mLoopFailedCount);
                Integer numValueOf3 = Integer.valueOf(this.mLoopCancelledCount);
                Long lValueOf = Long.valueOf((long) DataUnit.BYTES.toKiloBytes(this.mLoopDownloadSizeBytes));
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                DLog.logf("ImageDownloader %s stopped aggregate stats totalDownloads: %s totalFailed: %s totalCancelled: %s totalDownloadSizeKB: %s totalDownloadTimeMillis: %s totalDownloadLatencyMillis: %s", imageDownloadAttribute, numValueOf, numValueOf2, numValueOf3, lValueOf, Long.valueOf(timeUnit.toMillis(this.mLoopDownloadTimeNanos)), Long.valueOf(timeUnit.toMillis(this.mLoopDownloadLatencyNanos)));
                BaseImageDownloader.this.mDownloadCount += this.mLoopDownloadCount;
                BaseImageDownloader.this.mFailedCount += this.mLoopFailedCount;
                BaseImageDownloader.this.mCancelledCount += this.mLoopCancelledCount;
            }
        }

        public void doubleGranularity() {
            this.mImageDownloadAttribute.doubleGranularityFactor();
            DLog.logf("ImageDownloader %s doubled download granularity.", this.mImageDownloadAttribute);
        }

        /* JADX WARN: Code restructure failed: missing block: B:93:0x035a, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0035, code lost:
        
            r26.this$0.stop();
            r26.this$0.dispose();
         */
        /* JADX WARN: Removed duplicated region for block: B:114:0x0195 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:115:0x0181 A[SYNTHETIC] */
        @Override // java.util.concurrent.Callable
        @javax.annotation.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Void call() {
            /*
                Method dump skipped, instruction units count: 859
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.image.BaseImageDownloader.ImageDownloadLoop.call():java.lang.Void");
        }

        private void advanceToNextChunk() {
            while (advanceToNextThumbnail()) {
            }
            this.mRetryCount = 0;
            delay(BaseImageDownloader.this.mImageDownloadTickInterval);
        }

        private boolean advanceToNextThumbnail() {
            int downloadGranularityInThumbnails = this.mImageDownloadAttribute.getDownloadGranularityInThumbnails((int) TimeUnit.NANOSECONDS.toSeconds(this.mImageQualityLevel.getThumbnailDurationNanos()));
            int totalThumbnailPerChunk = this.mImageQualityLevel.getTotalThumbnailPerChunk();
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$image$DownloadDirection[this.mImageDownloadAttribute.getDirection().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException(String.format(Locale.ENGLISH, "Unknown download direction: %s", this.mImageDownloadAttribute.getDirection()));
                }
                downloadGranularityInThumbnails = -downloadGranularityInThumbnails;
            }
            int i2 = this.mThumbnailIndex;
            long j = this.mChunkIndex;
            int i3 = i2 + downloadGranularityInThumbnails;
            int i4 = i3 % totalThumbnailPerChunk;
            this.mThumbnailIndex = i4;
            if (i4 < 0) {
                i4 += totalThumbnailPerChunk;
            }
            this.mThumbnailIndex = i4;
            long jFloor = ((long) Math.floor(((double) i3) / ((double) totalThumbnailPerChunk))) + j;
            this.mChunkIndex = jFloor;
            DLog.devf("ImageDownloader %s advanceToNextThumbnail periodId: %s, chunkIndex: %s, thumbnailIndex: %s", this.mImageDownloadAttribute, this.mPeriodId, Long.valueOf(jFloor), Integer.valueOf(this.mThumbnailIndex));
            return !updatePeriod() && j == this.mChunkIndex;
        }

        private boolean updatePeriod() {
            long thumbnailTimeInNanos = this.mImageQualityLevel.getThumbnailTimeInNanos(this.mChunkIndex, this.mThumbnailIndex);
            String periodIdWithTimestampNanos = BaseImageDownloader.this.mImageStreamIndex.getPeriodIdWithTimestampNanos(thumbnailTimeInNanos);
            if (periodIdWithTimestampNanos == null) {
                DLog.logf("ImageDownloader %s went beyond the start of the first period", this.mImageDownloadAttribute);
                return true;
            }
            if (periodIdWithTimestampNanos.equals(this.mPeriodId)) {
                return false;
            }
            DLog.logf("ImageDownloader %s before updating period, timestamp: %s periodId: %s chunkIndex: %s thumbnailIndex: %s ", this.mImageDownloadAttribute, Long.valueOf(thumbnailTimeInNanos), this.mPeriodId, Long.valueOf(this.mChunkIndex), Integer.valueOf(this.mThumbnailIndex));
            this.mPeriodId = periodIdWithTimestampNanos;
            ImageQualityLevelInterface[] imageQualityLevelInterfaceArrFilterImageQualities = ImageDownloadUtil.filterImageQualities(BaseImageDownloader.this.mImageStreamIndex.getSortedImageQualityLevels(periodIdWithTimestampNanos), BaseImageDownloader.this.mImageHorizontalResolution);
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$image$DownloadDirection[this.mImageDownloadAttribute.getDirection().ordinal()];
            if (i == 1) {
                this.mImageQualityLevel = imageQualityLevelInterfaceArrFilterImageQualities[0];
            } else if (i == 2) {
                this.mImageQualityLevel = ImageDownloadUtil.selectQualityLevel(imageQualityLevelInterfaceArrFilterImageQualities, this.mImageDownloadAttribute.getDownloadGranularitySeconds());
            } else {
                throw new IllegalStateException(String.format(Locale.ENGLISH, "Unknown download direction: %s", this.mImageDownloadAttribute.getDirection()));
            }
            this.mChunkIndex = this.mImageQualityLevel.getChunkIndexFromNanos(thumbnailTimeInNanos);
            this.mThumbnailIndex = this.mImageQualityLevel.getThumbnailIndexFromNanos(thumbnailTimeInNanos);
            DLog.logf("ImageDownloader after updating period, timestamp: %s periodId: %s chunkIndex: %s thumbnailIndex: %s ", this.mImageDownloadAttribute, Long.valueOf(thumbnailTimeInNanos), this.mPeriodId, Long.valueOf(this.mChunkIndex), Integer.valueOf(this.mThumbnailIndex));
            return true;
        }

        private void delay(@Nonnull TimeSpan timeSpan) {
            if (timeSpan.isZero()) {
                return;
            }
            try {
                Thread.sleep(timeSpan.getTotalMilliseconds());
            } catch (InterruptedException e) {
                DLog.warnf("ImageDownloader %s interrupted during sleep, the downloader is likely being stopped, note: %s, periodId: %s chunkIndex: %s", this.mImageDownloadAttribute, e, this.mPeriodId, Long.valueOf(this.mChunkIndex));
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.image.BaseImageDownloader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$image$DownloadDirection;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$downloadservice$BlockingDownloadAdapter$BlockingDownloadResult$ResultType;

        static {
            int[] iArr = new int[BlockingDownloadAdapter.BlockingDownloadResult.ResultType.values().length];
            $SwitchMap$com$amazon$avod$media$downloadservice$BlockingDownloadAdapter$BlockingDownloadResult$ResultType = iArr;
            try {
                iArr[BlockingDownloadAdapter.BlockingDownloadResult.ResultType.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$downloadservice$BlockingDownloadAdapter$BlockingDownloadResult$ResultType[BlockingDownloadAdapter.BlockingDownloadResult.ResultType.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$downloadservice$BlockingDownloadAdapter$BlockingDownloadResult$ResultType[BlockingDownloadAdapter.BlockingDownloadResult.ResultType.CANCELLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[DownloadDirection.values().length];
            $SwitchMap$com$amazon$avod$content$image$DownloadDirection = iArr2;
            try {
                iArr2[DownloadDirection.FORWARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$image$DownloadDirection[DownloadDirection.BACKWARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    protected void trimIfMaxSizeExceeded() {
        if (getImageCacheSizeKB() <= this.mMaxImageCacheSizeKB) {
            return;
        }
        synchronized (this.mMutex) {
            if (this.mIsTrimmingInProgress) {
                return;
            }
            this.mIsTrimmingInProgress = true;
            Iterator<ImageDownloadLoop> it = this.mDownloadLoopMap.values().iterator();
            while (it.hasNext()) {
                it.next().doubleGranularity();
            }
            Long[] lArr = (Long[]) this.mTrickplayManifest.getTimecodeToFilepathMap().keySet().toArray(new Long[0]);
            int i = 0;
            for (int i2 = 0; i2 < lArr.length; i2 += 2) {
                if (!this.mIsRunning) {
                    return;
                }
                long jLongValue = lArr[i2].longValue();
                try {
                    Map.Entry<Long, String> closest = this.mTrickplayManifest.getClosest(jLongValue);
                    if (closest != null) {
                        this.mImageFileManager.deleteFile(new File(closest.getValue()));
                        this.mTrickplayManifest.delete(jLongValue);
                        i++;
                        DLog.warnf("ImageDownloader trimmed image timecodeMillis: %s, totalImageCacheSizeKb: %s", Long.valueOf(jLongValue), Long.valueOf(getImageCacheSizeKB()));
                    }
                } catch (ContentException e) {
                    DLog.warnf("ImageDownloader failed to delete image at timecodeMillis: %s, error: %s", Long.valueOf(jLongValue), e);
                }
            }
            String str = String.format(Locale.ENGLISH, "maxImageCacheSizeKB: %s postTrimImageCacheSizeKB: %s deleteCount: %s ", Long.valueOf(this.mMaxImageCacheSizeKB), Long.valueOf(getImageCacheSizeKB()), Integer.valueOf(i));
            DLog.warnf("ImageDownloader trimming complete: %s", str);
            this.mImageDownloaderReporter.reportTrimmed(str);
            this.mIsTrimmingInProgress = false;
        }
    }
}
