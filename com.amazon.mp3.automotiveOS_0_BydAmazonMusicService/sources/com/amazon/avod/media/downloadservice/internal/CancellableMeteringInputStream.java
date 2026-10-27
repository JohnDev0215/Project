package com.amazon.avod.media.downloadservice.internal;

import com.amazon.avod.media.downloadservice.DownloadListener;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.google.common.base.Preconditions;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class CancellableMeteringInputStream extends FilterInputStream {
    protected long mBytesProcessed;
    protected final DownloadListener mDownloadListener;
    protected final DownloadRequest mDownloadRequest;
    protected final DownloadStatistics.DownloadStatisticsBuilder mDownloadStatisticsBuilder;
    protected final long mInputStreamLength;
    protected volatile boolean mIsCancelled;

    public CancellableMeteringInputStream(@Nonnull DownloadListener downloadListener, @Nonnull DownloadRequest downloadRequest, @Nonnull DownloadStatistics.DownloadStatisticsBuilder downloadStatisticsBuilder, @Nonnull InputStream inputStream, long j) {
        super((InputStream) Preconditions.checkNotNull(inputStream, "inputStream"));
        this.mBytesProcessed = 0L;
        this.mIsCancelled = false;
        this.mDownloadListener = (DownloadListener) Preconditions.checkNotNull(downloadListener, "downloadListener");
        this.mDownloadRequest = (DownloadRequest) Preconditions.checkNotNull(downloadRequest, "downloadRequest");
        this.mDownloadStatisticsBuilder = (DownloadStatistics.DownloadStatisticsBuilder) Preconditions.checkNotNull(downloadStatisticsBuilder, "downloadStatisticsBuilder");
        this.mInputStreamLength = j;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.mIsCancelled) {
            throw new IOException("Calling read operation after the stream has been cancelled.");
        }
        int i = super.read();
        this.mBytesProcessed++;
        this.mDownloadStatisticsBuilder.onBytesProcessed(1L);
        this.mDownloadListener.onDownloadProgress(this.mDownloadRequest, this.mDownloadStatisticsBuilder.build(), this.mInputStreamLength);
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.mIsCancelled) {
            throw new IOException("Calling read operation after the stream has been cancelled.");
        }
        int i3 = super.read(bArr, i, i2);
        if (i3 == -1) {
            return -1;
        }
        long j = i3;
        this.mBytesProcessed += j;
        this.mDownloadStatisticsBuilder.onBytesProcessed(j);
        this.mDownloadListener.onDownloadProgress(this.mDownloadRequest, this.mDownloadStatisticsBuilder.build(), this.mInputStreamLength);
        return i3;
    }

    public boolean isCancelled() {
        return this.mIsCancelled;
    }

    public void cancel() {
        this.mIsCancelled = true;
    }
}
