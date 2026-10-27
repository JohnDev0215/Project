package com.amazon.avod.content.image;

import android.content.Context;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.image.BaseImageDownloader;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.downloadservice.BlockingDownloadAdapter;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import java.io.File;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SerialImageDownloader extends BaseImageDownloader {
    public SerialImageDownloader(@Nonnull Context context, @Nonnull ContentSessionContext contentSessionContext, @Nonnull StreamIndex streamIndex, @Nonnull File file, @Nonnull ImageDownloaderReporter imageDownloaderReporter) {
        super(context, contentSessionContext, streamIndex, file, imageDownloaderReporter);
    }

    @Override // com.amazon.avod.content.smoothstream.ImageDownloader
    public void start() {
        synchronized (this.mMutex) {
            if (this.mIsRunning) {
                return;
            }
            if (validateImageStream()) {
                this.mIsRunning = true;
                long mediaTimeWindowEndNanos = this.mSessionContext.getState().getMediaTimeWindowEndNanos();
                String periodIdWithTimestampNanos = this.mImageStreamIndex.getPeriodIdWithTimestampNanos(mediaTimeWindowEndNanos);
                ImageQualityLevelInterface[] imageQualityLevelInterfaceArrFilterImageQualities = ImageDownloadUtil.filterImageQualities(this.mImageStreamIndex.getSortedImageQualityLevels(periodIdWithTimestampNanos), this.mImageHorizontalResolution);
                this.mImageDownloaderReporter.reportQualitySelected(imageQualityLevelInterfaceArrFilterImageQualities[0].getThumbnailWidthInPixels(), ImageDownloadStrategy.SERIAL);
                if (this.mTrickplayManifest == null) {
                    this.mTrickplayManifest = new LiveTrickplayManifest(imageQualityLevelInterfaceArrFilterImageQualities[0].getThumbnailWidthInPixels(), imageQualityLevelInterfaceArrFilterImageQualities[0].getThumbnailHeightInPixels());
                }
                this.mExecutor = ScheduledExecutorBuilder.newBuilderFor(this, new String[0]).withFixedThreadPoolSize(DownloadDirection.values().length).build();
                StringBuilder sb = new StringBuilder();
                ImageDownloadAttribute imageDownloadAttribute = new ImageDownloadAttribute(DownloadDirection.FORWARD, this.mFinestImageDownloadGranularityInSeconds);
                ImageQualityLevelInterface imageQualityLevelInterface = imageQualityLevelInterfaceArrFilterImageQualities[0];
                BaseImageDownloader.ImageDownloadLoop imageDownloadLoop = new BaseImageDownloader.ImageDownloadLoop(this, imageQualityLevelInterface, new BlockingDownloadAdapter(this.mDownloadService), imageDownloadAttribute, imageQualityLevelInterface.getChunkIndexFromNanos(mediaTimeWindowEndNanos), periodIdWithTimestampNanos, imageQualityLevelInterface.getThumbnailIndexFromNanos(mediaTimeWindowEndNanos));
                this.mDownloadLoopMap.put(imageDownloadAttribute, imageDownloadLoop);
                this.mDownloadFutureList.add(this.mExecutor.submit(imageDownloadLoop));
                sb.append(imageDownloadAttribute);
                sb.append(";");
                ImageQualityLevelInterface imageQualityLevelInterfaceSelectQualityLevel = ImageDownloadUtil.selectQualityLevel(imageQualityLevelInterfaceArrFilterImageQualities, this.mFinestImageDownloadGranularityInSeconds);
                long jAbs = Math.abs(this.mSessionContext.getState().getMediaTimeWindowEndNanos() - this.mSessionContext.getState().getMediaTimeWindowStartNanos());
                if (jAbs < this.mDurationThresholdForCurrentTierMultipass.getTotalNanoSeconds() + imageQualityLevelInterfaceSelectQualityLevel.getImageDurationNanos()) {
                    DLog.logf("SerialImageDownloader fallback to the smallest tile because not enough content currently for this image duration. CurrentDurationNanos:%d, ImageDurationNanos:%d", Long.valueOf(jAbs), Long.valueOf(imageQualityLevelInterfaceSelectQualityLevel.getImageDurationNanos()));
                    imageQualityLevelInterfaceSelectQualityLevel = imageQualityLevelInterfaceArrFilterImageQualities[0];
                }
                ImageQualityLevelInterface imageQualityLevelInterface2 = imageQualityLevelInterfaceSelectQualityLevel;
                ImageDownloadAttribute imageDownloadAttribute2 = new ImageDownloadAttribute(DownloadDirection.BACKWARD, this.mFinestImageDownloadGranularityInSeconds);
                BaseImageDownloader.ImageDownloadLoop imageDownloadLoop2 = new BaseImageDownloader.ImageDownloadLoop(this, imageQualityLevelInterface2, new BlockingDownloadAdapter(this.mDownloadService), imageDownloadAttribute2, imageQualityLevelInterface2.getChunkIndexFromNanos(mediaTimeWindowEndNanos), periodIdWithTimestampNanos, imageQualityLevelInterface2.getThumbnailIndexFromNanos(mediaTimeWindowEndNanos));
                this.mLastBackwardDownloadLoop = imageDownloadLoop2;
                this.mDownloadLoopMap.put(imageDownloadAttribute2, imageDownloadLoop2);
                this.mDownloadFutureList.add(this.mExecutor.submit(this.mLastBackwardDownloadLoop));
                sb.append(imageDownloadAttribute2);
                DLog.logf("SerialImageDownloader started with download attributes: %s", sb);
                this.mImageDownloaderReporter.reportStarted(sb.toString());
                addListener(this.mImageDownloaderReporter);
                if (!this.mDownloadTrackerStopwatch.isRunning()) {
                    this.mDownloadTrackerStopwatch.start();
                }
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.ImageDownloader
    public ImageDownloadStrategy getImageDownloaderStrategy() {
        return ImageDownloadStrategy.SERIAL;
    }
}
