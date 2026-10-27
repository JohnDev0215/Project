package com.amazon.avod.media.downloadservice;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class BlockingDownloadAdapter implements DownloadListener {
    private final DownloadService mDownloadScheduler;
    private final Object mDownloadResultMonitor = new Object();
    private DownloadRequest mDownloadRequest = null;
    private BlockingDownloadResult mDownloadResult = null;
    private boolean mIsCancelled = false;

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onDownloadProgress(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics, long j) {
    }

    public static class BlockingDownloadResult {
        private final Exception mException;
        private final DownloadStatistics mStatistics;
        private final ResultType mType;

        public enum ResultType {
            SUCCESS,
            FAILURE,
            CANCELLED
        }

        public BlockingDownloadResult(ResultType resultType, DownloadStatistics downloadStatistics, Exception exc) {
            this.mType = resultType;
            this.mStatistics = downloadStatistics;
            this.mException = exc;
        }

        public ResultType getType() {
            return this.mType;
        }

        public boolean isSuccess() {
            return this.mType == ResultType.SUCCESS;
        }

        public boolean isFailure() {
            return this.mType == ResultType.FAILURE;
        }

        @Nullable
        public DownloadStatistics getStatistics() {
            return this.mStatistics;
        }

        public Exception getException() {
            Preconditions.checkState(isFailure(), "can only get exception of failed operation!");
            return this.mException;
        }
    }

    public BlockingDownloadAdapter(DownloadService downloadService) {
        Preconditions.checkNotNull(downloadService);
        this.mDownloadScheduler = downloadService;
    }

    public void cancel() {
        DownloadRequest downloadRequest;
        synchronized (this.mDownloadResultMonitor) {
            this.mIsCancelled = true;
            downloadRequest = this.mDownloadRequest;
        }
        if (downloadRequest != null) {
            DLog.devf("Attempting to cancel request for %s", downloadRequest.getUrl());
            this.mDownloadScheduler.cancelDownloadRequest(downloadRequest);
        }
    }

    @Nonnull
    public BlockingDownloadResult submitRequestAndBlock(@Nonnull DownloadRequest downloadRequest, boolean z) {
        BlockingDownloadResult blockingDownloadResult;
        synchronized (this.mDownloadResultMonitor) {
            this.mDownloadResult = null;
            if (this.mIsCancelled) {
                setDownloadResult(BlockingDownloadResult.ResultType.CANCELLED, null, null);
            } else {
                DLog.devf("Submitting request for %s", downloadRequest.getUrl());
                this.mDownloadScheduler.submitDownloadRequest(downloadRequest, z);
                this.mDownloadRequest = downloadRequest;
                while (this.mDownloadResult == null) {
                    try {
                        this.mDownloadResultMonitor.wait();
                    } catch (InterruptedException e) {
                        DLog.warnf("Downloader was interrupted - probably shutting down");
                        setDownloadResult(BlockingDownloadResult.ResultType.FAILURE, e, null);
                    }
                }
            }
            blockingDownloadResult = this.mDownloadResult;
        }
        return blockingDownloadResult;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onSuccess(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics) {
        setDownloadResult(BlockingDownloadResult.ResultType.SUCCESS, null, downloadStatistics);
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onFailure(DownloadRequest downloadRequest, Exception exc, DownloadStatistics downloadStatistics) {
        setDownloadResult(BlockingDownloadResult.ResultType.FAILURE, exc, downloadStatistics);
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onCancel(DownloadRequest downloadRequest) {
        setDownloadResult(BlockingDownloadResult.ResultType.CANCELLED, null, null);
    }

    private void setDownloadResult(BlockingDownloadResult.ResultType resultType, Exception exc, DownloadStatistics downloadStatistics) {
        synchronized (this.mDownloadResultMonitor) {
            if (this.mDownloadResult == null) {
                this.mDownloadResult = new BlockingDownloadResult(resultType, downloadStatistics, exc);
                this.mDownloadResultMonitor.notifyAll();
            }
        }
    }
}
