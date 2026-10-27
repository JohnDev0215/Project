package com.amazon.avod.content.image;

import android.content.Context;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.image.BaseImageDownloader;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.downloadservice.BlockingDownloadAdapter;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MultipassImageDownloader extends BaseImageDownloader {
    private CompoundCallable mOrderedDownloadLoops;

    public MultipassImageDownloader(@Nonnull Context context, @Nonnull ContentSessionContext contentSessionContext, @Nonnull StreamIndex streamIndex, @Nonnull File file, @Nonnull ImageDownloaderReporter imageDownloaderReporter) {
        super(context, contentSessionContext, streamIndex, file, imageDownloaderReporter);
    }

    @Override // com.amazon.avod.content.smoothstream.ImageDownloader
    public void start() {
        ImageQualityLevelInterface[] imageQualityLevelInterfaceArr;
        long j;
        synchronized (this.mMutex) {
            if (this.mIsRunning) {
                return;
            }
            if (validateImageStream()) {
                this.mIsRunning = true;
                long mediaTimeWindowEndNanos = this.mSessionContext.getState().getMediaTimeWindowEndNanos();
                String periodIdWithTimestampNanos = this.mImageStreamIndex.getPeriodIdWithTimestampNanos(mediaTimeWindowEndNanos);
                ImageQualityLevelInterface[] imageQualityLevelInterfaceArrFilterImageQualities = ImageDownloadUtil.filterImageQualities(this.mImageStreamIndex.getSortedImageQualityLevels(periodIdWithTimestampNanos), this.mImageHorizontalResolution);
                this.mImageDownloaderReporter.reportQualitySelected(imageQualityLevelInterfaceArrFilterImageQualities[0].getThumbnailWidthInPixels(), ImageDownloadStrategy.MULTIPASS);
                if (this.mTrickplayManifest == null) {
                    this.mTrickplayManifest = new LiveTrickplayManifest(imageQualityLevelInterfaceArrFilterImageQualities[0].getThumbnailWidthInPixels(), imageQualityLevelInterfaceArrFilterImageQualities[0].getThumbnailHeightInPixels());
                }
                this.mExecutor = ScheduledExecutorBuilder.newBuilderFor(this, new String[0]).withFixedThreadPoolSize(DownloadDirection.values().length).build();
                StringBuilder sb = new StringBuilder();
                ImageDownloadAttribute imageDownloadAttribute = new ImageDownloadAttribute(DownloadDirection.FORWARD, this.mFinestImageDownloadGranularityInSeconds);
                ImageQualityLevelInterface imageQualityLevelInterface = imageQualityLevelInterfaceArrFilterImageQualities[0];
                long j2 = mediaTimeWindowEndNanos;
                BaseImageDownloader.ImageDownloadLoop imageDownloadLoop = new BaseImageDownloader.ImageDownloadLoop(this, imageQualityLevelInterface, new BlockingDownloadAdapter(this.mDownloadService), imageDownloadAttribute, imageQualityLevelInterface.getChunkIndexFromNanos(mediaTimeWindowEndNanos), periodIdWithTimestampNanos, imageQualityLevelInterface.getThumbnailIndexFromNanos(mediaTimeWindowEndNanos));
                this.mDownloadLoopMap.put(imageDownloadAttribute, imageDownloadLoop);
                this.mDownloadFutureList.add(this.mExecutor.submit(imageDownloadLoop));
                sb.append(imageDownloadAttribute);
                sb.append(";");
                long jAbs = Math.abs(this.mSessionContext.getState().getMediaTimeWindowEndNanos() - this.mSessionContext.getState().getMediaTimeWindowStartNanos());
                ArrayList arrayListNewArrayList = Lists.newArrayList();
                int i = 0;
                while (i < this.mSortedImageDownloadGranularitiesInSeconds.size()) {
                    int iIntValue = this.mSortedImageDownloadGranularitiesInSeconds.get(i).intValue();
                    ImageQualityLevelInterface imageQualityLevelInterfaceSelectQualityLevel = ImageDownloadUtil.selectQualityLevel(imageQualityLevelInterfaceArrFilterImageQualities, iIntValue);
                    if (jAbs < this.mDurationThresholdForCurrentTierMultipass.getTotalNanoSeconds() + imageQualityLevelInterfaceSelectQualityLevel.getImageDurationNanos()) {
                        DLog.logf("MultipassImageDownloader skipping this multipass loop because not enough content currently for this image duration. CurrentDurationNanos:%d, ImageDurationNanos:%d", Long.valueOf(jAbs), Long.valueOf(imageQualityLevelInterfaceSelectQualityLevel.getImageDurationNanos()));
                        j = j2;
                        imageQualityLevelInterfaceArr = imageQualityLevelInterfaceArrFilterImageQualities;
                    } else {
                        long j3 = j2;
                        long chunkIndexFromNanos = imageQualityLevelInterfaceSelectQualityLevel.getChunkIndexFromNanos(j3);
                        int thumbnailIndexFromNanos = imageQualityLevelInterfaceSelectQualityLevel.getThumbnailIndexFromNanos(j3);
                        ImageDownloadAttribute imageDownloadAttribute2 = new ImageDownloadAttribute(DownloadDirection.BACKWARD, iIntValue);
                        imageQualityLevelInterfaceArr = imageQualityLevelInterfaceArrFilterImageQualities;
                        j = j3;
                        BaseImageDownloader.ImageDownloadLoop imageDownloadLoop2 = new BaseImageDownloader.ImageDownloadLoop(this, imageQualityLevelInterfaceSelectQualityLevel, new BlockingDownloadAdapter(this.mDownloadService), imageDownloadAttribute2, chunkIndexFromNanos, periodIdWithTimestampNanos, thumbnailIndexFromNanos);
                        arrayListNewArrayList.add(imageDownloadLoop2);
                        this.mDownloadLoopMap.put(imageDownloadAttribute2, imageDownloadLoop2);
                        sb.append(imageDownloadAttribute2);
                        sb.append(";");
                        if (i == this.mSortedImageDownloadGranularitiesInSeconds.size() - 1) {
                            this.mLastBackwardDownloadLoop = imageDownloadLoop2;
                        }
                    }
                    i++;
                    imageQualityLevelInterfaceArrFilterImageQualities = imageQualityLevelInterfaceArr;
                    j2 = j;
                }
                if (!arrayListNewArrayList.isEmpty()) {
                    CompoundCallable compoundCallable = new CompoundCallable(arrayListNewArrayList);
                    this.mOrderedDownloadLoops = compoundCallable;
                    this.mDownloadFutureList.add(this.mExecutor.submit(compoundCallable));
                } else {
                    this.mImageDownloaderReporter.reportError("No backwards multipass loop can be scheduled");
                }
                DLog.logf("MultipassImageDownloader started with download attributes: %s", sb);
                this.mImageDownloaderReporter.reportStarted(sb.toString());
                addListener(this.mImageDownloaderReporter);
                if (!this.mDownloadTrackerStopwatch.isRunning()) {
                    this.mDownloadTrackerStopwatch.start();
                }
            }
        }
    }

    private static class CompoundCallable implements Callable<Void> {
        private final List<Callable<Void>> mCallables;
        private boolean mIsCancelled;
        private final Object mMutex;

        private CompoundCallable(@Nonnull List<Callable<Void>> list) {
            this.mMutex = new Object();
            this.mIsCancelled = false;
            this.mCallables = (List) Preconditions.checkNotNull(list, "orderedCallables");
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            for (Callable<Void> callable : this.mCallables) {
                synchronized (this.mMutex) {
                    if (this.mIsCancelled) {
                        return null;
                    }
                }
                callable.call();
            }
            return null;
        }

        public void cancelPendingCallables() {
            synchronized (this.mMutex) {
                this.mIsCancelled = true;
            }
        }
    }

    @Override // com.amazon.avod.content.image.BaseImageDownloader
    protected void trimIfMaxSizeExceeded() {
        if (getImageCacheSizeKB() <= this.mMaxImageCacheSizeKB) {
            return;
        }
        this.mOrderedDownloadLoops.cancelPendingCallables();
        super.trimIfMaxSizeExceeded();
    }

    @Override // com.amazon.avod.content.smoothstream.ImageDownloader
    public ImageDownloadStrategy getImageDownloaderStrategy() {
        return ImageDownloadStrategy.MULTIPASS;
    }
}
