package com.amazon.avod.media.downloadservice.internal;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.executors.TimeoutTask;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.resources.SurgingBufferPool;
import com.amazon.avod.util.DataUnit;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
abstract class DownloadCallable implements TimeoutTask.TimeoutCallable<Void> {
    protected static final int GZIP_INPUT_STREAM_BUFFER_SIZE_BYTES = (int) DataUnit.MEGABYTES.toBytes(2.0f);
    protected final boolean mAreRequestTimeoutsStrictlyEnforced;
    protected final DownloadCompletedListener mCompletedListener;
    protected final DownloadRequest mDownloadRequest;
    protected final boolean mFailOnContentLengthMismatch;
    protected final TimeSpan mHttpResponseCacheMaxAge;
    protected final boolean mIsHttpResponseCacheEnabled;
    protected final NetworkConnectionManager mNetworkConnectionManager;
    protected final String mPingUrl;
    protected final boolean mShouldPingServerAfterDownloadFailure;
    protected final boolean mShouldUseThrottledMeteringInputStream;
    protected final boolean mSocketTaggingEnabled;
    protected final DownloadStatistics.DownloadStatisticsBuilder mStatisticsBuilder;
    protected final SurgingBufferPool mSurgingBufferPool;
    protected final TimeSpan mThrottledMeteringInputStreamTimeThreshold;
    protected final Ticker mTicker;
    protected volatile boolean mIsCancelled = false;
    protected volatile CancellableMeteringInputStream mCancellableStream = null;
    private AtomicBoolean mIsDownloadCompletedListenerCalled = new AtomicBoolean(false);

    DownloadCallable(@Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull DownloadCompletedListener downloadCompletedListener, @Nonnull DownloadRequest downloadRequest, @Nonnull DownloadStatistics.DownloadStatisticsBuilder downloadStatisticsBuilder, @Nonnull Ticker ticker, @Nonnull SurgingBufferPool surgingBufferPool, boolean z, boolean z2, @Nonnull TimeSpan timeSpan, boolean z3, boolean z4, @Nonnull String str, boolean z5, @Nonnull TimeSpan timeSpan2, boolean z6) {
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mCompletedListener = (DownloadCompletedListener) Preconditions.checkNotNull(downloadCompletedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mDownloadRequest = (DownloadRequest) Preconditions.checkNotNull(downloadRequest, "request");
        this.mStatisticsBuilder = (DownloadStatistics.DownloadStatisticsBuilder) Preconditions.checkNotNull(downloadStatisticsBuilder, "statisticsBuilder");
        this.mTicker = (Ticker) Preconditions.checkNotNull(ticker, "ticker");
        this.mSurgingBufferPool = (SurgingBufferPool) Preconditions.checkNotNull(surgingBufferPool, "surgingBufferPool");
        this.mSocketTaggingEnabled = z;
        this.mShouldUseThrottledMeteringInputStream = z2;
        this.mThrottledMeteringInputStreamTimeThreshold = (TimeSpan) Preconditions.checkNotNull(timeSpan, "throttledMeteringInputStreamTimeThreshold");
        this.mFailOnContentLengthMismatch = z3;
        this.mShouldPingServerAfterDownloadFailure = z4;
        this.mPingUrl = (String) Preconditions.checkNotNull(str, "pingUrl");
        this.mIsHttpResponseCacheEnabled = z5;
        this.mHttpResponseCacheMaxAge = (TimeSpan) Preconditions.checkNotNull(timeSpan2, "httpResponseCacheMaxAge");
        this.mAreRequestTimeoutsStrictlyEnforced = z6;
    }

    @Override // com.amazon.avod.media.framework.executors.TimeoutTask
    public long getTimeoutInNanos() {
        return this.mDownloadRequest.getTimeoutInNanos();
    }

    @Override // com.amazon.avod.media.framework.executors.TimeoutTask
    public void onCancel() {
        CancellableMeteringInputStream cancellableMeteringInputStream = this.mCancellableStream;
        if (cancellableMeteringInputStream != null) {
            cancellableMeteringInputStream.cancel();
        }
        this.mIsCancelled = true;
        if (this.mAreRequestTimeoutsStrictlyEnforced) {
            notifyDownloadComplete();
        }
    }

    @Override // com.amazon.avod.media.framework.executors.TimeoutTask
    public void postRun() {
        notifyDownloadComplete();
    }

    private void notifyDownloadComplete() {
        if (this.mIsDownloadCompletedListenerCalled.compareAndSet(false, true)) {
            this.mCompletedListener.onDownloadComplete(this.mDownloadRequest, this.mStatisticsBuilder.build());
        }
    }

    int readFully(@Nonnull GrowableBuffer growableBuffer) throws IOException {
        byte[] buffer = growableBuffer.getBuffer();
        int i = 0;
        int i2 = 0;
        while (i != -1) {
            i2 += i;
            if (i2 >= buffer.length) {
                growableBuffer.doubleSize();
                buffer = growableBuffer.getBuffer();
            }
            if (this.mCancellableStream.isCancelled()) {
                throw new IOException("Cancelled while reading from stream!");
            }
            i = this.mCancellableStream.read(buffer, i2, buffer.length - i2);
        }
        return i2;
    }
}
