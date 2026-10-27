package com.amazon.avod.media.downloadservice.internal;

import com.amazon.avod.media.downloadservice.DownloadListener;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.threading.Tickers;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class CancellableThrottledMeteringInputStream extends CancellableMeteringInputStream {
    private final InputStream mInputStream;
    private final Stopwatch mStopwatch;
    private long mTemporaryBytesProcessed;
    private final long mTimeThresholdMillis;

    public CancellableThrottledMeteringInputStream(@Nonnull DownloadListener downloadListener, @Nonnull DownloadRequest downloadRequest, @Nonnull DownloadStatistics.DownloadStatisticsBuilder downloadStatisticsBuilder, @Nonnull InputStream inputStream, long j, long j2) {
        this(downloadListener, downloadRequest, downloadStatisticsBuilder, inputStream, j, j2, Tickers.androidTicker());
    }

    CancellableThrottledMeteringInputStream(@Nonnull DownloadListener downloadListener, @Nonnull DownloadRequest downloadRequest, @Nonnull DownloadStatistics.DownloadStatisticsBuilder downloadStatisticsBuilder, @Nonnull InputStream inputStream, long j, long j2, Ticker ticker) {
        super(downloadListener, downloadRequest, downloadStatisticsBuilder, inputStream, j);
        this.mTemporaryBytesProcessed = 0L;
        this.mInputStream = (InputStream) Preconditions.checkNotNull(inputStream, "inputStream");
        Preconditions.checkArgument(j > 0, "inputStreamLength");
        Preconditions.checkArgument(j2 > 0, "timeThresholdMillis");
        this.mTimeThresholdMillis = j2;
        this.mStopwatch = Stopwatch.createUnstarted(ticker);
    }

    @Override // com.amazon.avod.media.downloadservice.internal.CancellableMeteringInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.mIsCancelled) {
            throw new IOException("Calling read operation after the stream has been cancelled.");
        }
        int i3 = this.mInputStream.read(bArr, i, i2);
        if (i3 != -1) {
            long j = i3;
            this.mBytesProcessed += j;
            this.mTemporaryBytesProcessed += j;
        }
        if (!this.mStopwatch.isRunning()) {
            this.mDownloadStatisticsBuilder.onBytesProcessed(i3);
            this.mDownloadListener.onDownloadProgress(this.mDownloadRequest, this.mDownloadStatisticsBuilder.build(), this.mInputStreamLength);
            this.mTemporaryBytesProcessed = 0L;
            this.mStopwatch.start();
        } else if (this.mStopwatch.elapsed(TimeUnit.MILLISECONDS) > this.mTimeThresholdMillis || i3 == -1 || this.mInputStreamLength == this.mBytesProcessed) {
            this.mDownloadStatisticsBuilder.onBytesProcessed(this.mTemporaryBytesProcessed);
            this.mDownloadListener.onDownloadProgress(this.mDownloadRequest, this.mDownloadStatisticsBuilder.build(), this.mInputStreamLength);
            this.mTemporaryBytesProcessed = 0L;
            this.mStopwatch.reset().start();
        }
        return i3;
    }
}
