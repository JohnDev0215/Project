package com.amazon.avod.content.image;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ImageDownloadRetryPolicy {
    private final ImageDownloaderConfig mConfig;
    private final ImageDownloadAttribute mImageDownloadAttribute;
    private final int mImageDownloadMaxRetryCount;
    private final ImageDownloaderReporter mImageDownloaderReporter;

    public ImageDownloadRetryPolicy(@Nonnull ImageDownloadAttribute imageDownloadAttribute, @Nonnull ImageDownloaderReporter imageDownloaderReporter) {
        this(imageDownloadAttribute, imageDownloaderReporter, ImageDownloaderConfig.getInstance());
    }

    ImageDownloadRetryPolicy(@Nonnull ImageDownloadAttribute imageDownloadAttribute, @Nonnull ImageDownloaderReporter imageDownloaderReporter, @Nonnull ImageDownloaderConfig imageDownloaderConfig) {
        int backwardImageDownloadMaxRetryCount;
        ImageDownloadAttribute imageDownloadAttribute2 = (ImageDownloadAttribute) Preconditions.checkNotNull(imageDownloadAttribute, "imageDownloadAttribute");
        this.mImageDownloadAttribute = imageDownloadAttribute2;
        this.mImageDownloaderReporter = (ImageDownloaderReporter) Preconditions.checkNotNull(imageDownloaderReporter, "imageDownloaderReporter");
        ImageDownloaderConfig imageDownloaderConfig2 = (ImageDownloaderConfig) Preconditions.checkNotNull(imageDownloaderConfig, "imageDownloaderConfig");
        this.mConfig = imageDownloaderConfig2;
        if (imageDownloadAttribute2.getDirection() == DownloadDirection.FORWARD) {
            backwardImageDownloadMaxRetryCount = imageDownloaderConfig2.getForwardImageDownloadMaxRetryCount();
        } else {
            backwardImageDownloadMaxRetryCount = imageDownloaderConfig2.getBackwardImageDownloadMaxRetryCount();
        }
        this.mImageDownloadMaxRetryCount = backwardImageDownloadMaxRetryCount;
    }

    public boolean shouldRetryWithDownloadFailure(@Nonnull String str, @Nullable Exception exc, int i, int i2) {
        if (i <= this.mImageDownloadMaxRetryCount) {
            DLog.warnf("ImageDownloader %s download failed for: %s, failure cause: %s, retry count: %s, chunkIndex: %s retrying after a delay", this.mImageDownloadAttribute, str, exc, Integer.valueOf(i), Integer.valueOf(i2));
            return true;
        }
        String str2 = String.format(Locale.ENGLISH, "ImageDownloader %s download failed: %s, cause: %s, maxRetryCount hit: %s, chunkIndex: %s ", this.mImageDownloadAttribute, str, exc, Integer.valueOf(i), Integer.valueOf(i2));
        DLog.warnf(str2);
        this.mImageDownloaderReporter.reportError(str2);
        return false;
    }
}
