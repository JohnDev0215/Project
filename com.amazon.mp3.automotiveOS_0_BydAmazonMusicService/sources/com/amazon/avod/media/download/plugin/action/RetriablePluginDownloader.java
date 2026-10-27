package com.amazon.avod.media.download.plugin.action;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.plugin.DownloadData;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.ExponentialBackoff;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RetriablePluginDownloader extends ContentPluginActionGetFileFromURL {
    private final ExponentialBackoff mExponentialBackoff;
    private final RetriablePluginDownloaderConfig mRetriablePluginDownloaderConfig;

    public RetriablePluginDownloader(@Nonnull DownloadService downloadService) {
        this(downloadService, RetriablePluginDownloaderConfig.getInstance());
    }

    RetriablePluginDownloader(@Nonnull DownloadService downloadService, @Nonnull RetriablePluginDownloaderConfig retriablePluginDownloaderConfig) {
        super(downloadService);
        RetriablePluginDownloaderConfig retriablePluginDownloaderConfig2 = (RetriablePluginDownloaderConfig) Preconditions.checkNotNull(retriablePluginDownloaderConfig, "retriablePluginDownloaderConfig");
        this.mRetriablePluginDownloaderConfig = retriablePluginDownloaderConfig2;
        this.mExponentialBackoff = new ExponentialBackoff(retriablePluginDownloaderConfig2.getMinBackOffMillis().getTotalMilliseconds(), retriablePluginDownloaderConfig2.getMaxBackOffMillis().getTotalMilliseconds(), retriablePluginDownloaderConfig2.getBackOffGrowthFactor());
    }

    @Nonnull
    protected DownloadData<File> downloadFileDataFromURL(@Nonnull File file, @Nonnull String str, @Nonnull TimeSpan timeSpan, int i) {
        long totalMilliseconds;
        Preconditions.checkNotNull(file, "localPath");
        Preconditions.checkNotNull(str, "urlString");
        Preconditions.checkNotNull(timeSpan, "timeout");
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Ticker.systemTicker());
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i2 < this.mRetriablePluginDownloaderConfig.getNumRetries()) {
                DownloadData<File> downloadDataDownloadFileDataFromURL = downloadFileDataFromURL(file, str, timeSpan, i, PriorityTier.AUXILIARY_REQUIRED);
                if (downloadDataDownloadFileDataFromURL.getDownloadData() != null) {
                    if (stopwatchCreateStarted.isRunning()) {
                        stopwatchCreateStarted.stop();
                    }
                    this.mExponentialBackoff.reset();
                    DLog.logf("RetriablePluginDownloader returns success, attempts:%d, elapsedTimeMillis %d", Integer.valueOf(i3), Long.valueOf(stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS)));
                    return downloadDataDownloadFileDataFromURL;
                }
                try {
                    if (this.mRetriablePluginDownloaderConfig.isExponentialBackoffEnabled()) {
                        totalMilliseconds = this.mExponentialBackoff.nextBackoffMillis();
                    } else {
                        totalMilliseconds = this.mRetriablePluginDownloaderConfig.getMinBackOffMillis().getTotalMilliseconds();
                    }
                    Thread.sleep(totalMilliseconds);
                } catch (InterruptedException e) {
                    DLog.exceptionf(e, "interrupted during exponential backoff, continuing with next attempt", new Object[0]);
                }
                DLog.warnf("RetriablePluginDownloader attempting download again, retry attempt:%d", Integer.valueOf(i3));
                i2 = i3;
            } else {
                if (stopwatchCreateStarted.isRunning()) {
                    stopwatchCreateStarted.stop();
                }
                this.mExponentialBackoff.reset();
                return new DownloadData<>(null, stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS));
            }
        }
    }
}
