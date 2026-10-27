package com.amazon.avod.content.backgrounddownload;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.downloading.progressive.SerializableStreamStatus;
import com.amazon.avod.content.event.ContentEventDownloadUpdate;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.downloading.SmoothStreamingDownloadExceptionUtils;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.singlefile.ByteRange;
import com.amazon.avod.content.smoothstream.storage.singlefile.ByteRangeUtils;
import com.amazon.avod.content.smoothstream.storage.singlefile.DownloadContentStatus;
import com.amazon.avod.content.smoothstream.storage.singlefile.MediaFile;
import com.amazon.avod.content.smoothstream.storage.singlefile.MediaFileStatus;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.DownloadType;
import com.amazon.avod.media.downloadservice.BasicDownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadListener;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadRequestPriority;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class SingleFileDownloader implements DownloadTask, DownloadListener {
    private final long mCheckPointStepBytes;
    private final ContentManagementEventBus mContentManagementEventBus;
    private final SmoothStreamingContentStore mContentStore;
    private final ContentUrlSelector mContentUrlSelector;
    private final ContentSessionContext mContext;
    private final DownloadContentStatus mDownloadContentStatus;
    private final DownloadService mDownloadService;
    private final long mDownloadTimeoutNanos;
    private ConcurrentMap<DownloadRequest, DownloadRequestStatus> mDownloadingRequests;
    private final SmoothStreamingURI mLastUri;
    private final long mMaxChunkSizeBytes;
    private final int mMaxDownloadRetries;
    private final MediaFile mMediaFile;
    private final MediaFileStatus mMediaFileStatus;
    private final int mNumberOfConcurrentDownloadRequests;
    private final PlayableContent mPlayableContent;
    private final SingleFileDownloadConfig mSingleFileDownloadConfig;
    private boolean mStarted;
    private final SmoothStreamingDownloadExceptionUtils mExceptionUtils = new SmoothStreamingDownloadExceptionUtils();
    private final Object mSubmitRequestMutex = new Object();
    private final Queue<DownloadRequest> mDownloadRequests = Queues.newConcurrentLinkedQueue();

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onCancel(DownloadRequest downloadRequest) {
    }

    SingleFileDownloader(@Nonnull DownloadService downloadService, @Nonnull MediaFile mediaFile, @Nonnull MediaFileStatus mediaFileStatus, @Nonnull DownloadContentStatus downloadContentStatus, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlayableContent playableContent, @Nonnull SmoothStreamingURI smoothStreamingURI, @Nonnull ContentUrlSelector contentUrlSelector, int i, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull ContentSessionContext contentSessionContext, long j, long j2, long j3) {
        this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService");
        this.mMediaFile = (MediaFile) Preconditions.checkNotNull(mediaFile, "mediaFile");
        this.mDownloadContentStatus = (DownloadContentStatus) Preconditions.checkNotNull(downloadContentStatus, "downloadContentStatus");
        this.mContentManagementEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        this.mMediaFileStatus = (MediaFileStatus) Preconditions.checkNotNull(mediaFileStatus, "mediaFileStatus");
        this.mPlayableContent = (PlayableContent) Preconditions.checkNotNull(playableContent, "playableContent");
        this.mLastUri = (SmoothStreamingURI) Preconditions.checkNotNull(smoothStreamingURI, "lastUri");
        this.mContentUrlSelector = (ContentUrlSelector) Preconditions.checkNotNull(contentUrlSelector, "contentUrlSelector");
        this.mNumberOfConcurrentDownloadRequests = i;
        this.mContentStore = (SmoothStreamingContentStore) Preconditions.checkNotNull(smoothStreamingContentStore, "contentStore");
        this.mContext = (ContentSessionContext) Preconditions.checkNotNull(contentSessionContext, "context");
        this.mMaxChunkSizeBytes = j;
        this.mCheckPointStepBytes = j2;
        this.mDownloadTimeoutNanos = j3;
        SingleFileDownloadConfig singleFileDownloadConfig = SingleFileDownloadConfig.getInstance();
        this.mSingleFileDownloadConfig = singleFileDownloadConfig;
        this.mMaxDownloadRetries = singleFileDownloadConfig.getMaxDownloadRetries();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onSuccess(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics) {
        DownloadRequestStatus downloadRequestStatusRemove = this.mDownloadingRequests.remove(downloadRequest);
        if (downloadRequestStatusRemove == null) {
            DLog.warnf("Request was canceled before");
            return;
        }
        this.mContentUrlSelector.notifyDownloadSuccess(downloadStatistics);
        saveProgress(downloadRequestStatusRemove.getByteRange());
        submitDownloadRequest();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onFailure(DownloadRequest downloadRequest, Exception exc, DownloadStatistics downloadStatistics) {
        DownloadRequestStatus downloadRequestStatus = this.mDownloadingRequests.get(downloadRequest);
        if (downloadRequestStatus == null) {
            DLog.warnf("Request was canceled before");
            return;
        }
        ContentException contentExceptionTranslateExceptionForFragment = this.mExceptionUtils.translateExceptionForFragment(exc, null, downloadStatistics);
        if (this.mSingleFileDownloadConfig.getFasterSdCardDownload() && ContentException.ContentError.DISK_FULL == contentExceptionTranslateExceptionForFragment.getErrorCode()) {
            ContentManagementEventBus contentManagementEventBus = this.mContentManagementEventBus;
            PlayableContent playableContent = this.mPlayableContent;
            ContentSessionType contentSessionType = ContentSessionType.DOWNLOAD;
            contentManagementEventBus.postEvent(new FatalContentEventError(playableContent, contentSessionType, contentExceptionTranslateExceptionForFragment, getConsumptionId(), contentSessionType));
            return;
        }
        if (contentExceptionTranslateExceptionForFragment.getErrorCode() == ContentException.ContentError.CDN_ERROR && downloadRequestStatus.getTryCount() > this.mMaxDownloadRetries) {
            ContentManagementEventBus contentManagementEventBus2 = this.mContentManagementEventBus;
            PlayableContent playableContent2 = this.mPlayableContent;
            ContentSessionType contentSessionType2 = ContentSessionType.DOWNLOAD;
            contentManagementEventBus2.postEvent(new FatalContentEventError(playableContent2, contentSessionType2, contentExceptionTranslateExceptionForFragment, getConsumptionId(), contentSessionType2));
            return;
        }
        this.mContentManagementEventBus.postEvent(new RetriableContentEventError(this.mPlayableContent, ContentSessionType.DOWNLOAD, contentExceptionTranslateExceptionForFragment, downloadRequestStatus.getTryCount()));
        downloadRequestStatus.setTryCount(downloadRequestStatus.getTryCount() + 1);
        this.mContentUrlSelector.notifyDownloadFailure(contentExceptionTranslateExceptionForFragment, this.mContentUrlSelector.getCurrentContentUrl(), downloadRequestStatus.getTryCount(), 0L, false, null, downloadStatistics, DownloadType.FRAGMENT);
        retryDownloadRequest(downloadRequest);
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onDownloadProgress(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics, long j) {
        DownloadRequestStatus downloadRequestStatus = this.mDownloadingRequests.get(downloadRequest);
        if (downloadRequestStatus == null) {
            DLog.warnf("Request was canceled before");
            return;
        }
        downloadRequestStatus.setDownloadedBytes(downloadStatistics.getBytesProcessed());
        if (downloadRequestStatus.getDownloadedBytes() - downloadRequestStatus.getDownloadedBytesCheckPoint() > this.mCheckPointStepBytes) {
            saveProgress(new ByteRange(downloadRequestStatus.getByteRange().getStart() + downloadRequestStatus.getDownloadedBytesCheckPoint(), (downloadRequestStatus.getByteRange().getStart() + downloadRequestStatus.getDownloadedBytes()) - 1));
            downloadRequestStatus.setDownloadedBytesCheckPoint(downloadRequestStatus.getDownloadedBytes());
        }
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public void start() {
        buildDownloadRequests();
        this.mStarted = true;
        if (getDownloadedSizeBytes() == getTotalSizeBytes()) {
            this.mContentManagementEventBus.postEvent(new ContentEventDownloadUpdate(this.mPlayableContent, ContentSessionType.DOWNLOAD, this.mLastUri.isAudio() ? SampleType.AUDIO_SAMPLE : SampleType.VIDEO_SAMPLE));
        }
        submitDownloadRequest();
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public void stop() {
        if (this.mStarted) {
            synchronized (this.mSubmitRequestMutex) {
                this.mStarted = false;
            }
            Iterator<DownloadRequest> it = this.mDownloadingRequests.keySet().iterator();
            while (it.hasNext()) {
                this.mDownloadService.cancelDownloadRequest(it.next());
            }
            this.mDownloadingRequests.clear();
            if (getDownloadedSizeBytes() == getTotalSizeBytes()) {
                saveFullyDownloadedStatFile();
            }
            try {
                this.mMediaFile.close();
            } catch (IOException e) {
                DLog.warnf(e.getMessage());
            }
        }
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public long getDownloadedSizeBytes() {
        return this.mMediaFileStatus.getDownloadedSizeBytes();
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public float getPlayableDownloadRatio() {
        float fileSizeBytes = this.mMediaFileStatus.getFileSizeBytes();
        return fileSizeBytes == 0.0f ? fileSizeBytes : this.mMediaFileStatus.getFirstDownloadedChunkSizeInBytes() / fileSizeBytes;
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public long getTotalSizeBytes() {
        return this.mMediaFileStatus.getFileSizeBytes();
    }

    private void buildDownloadRequests() {
        for (ByteRange byteRange : ByteRangeUtils.getUnDownloadedByteRanges(this.mMediaFileStatus.getSortedDownloadedByteRanges(), this.mMediaFileStatus.getFileSizeBytes(), this.mMaxChunkSizeBytes)) {
            ImmutableMap immutableMapOf = ImmutableMap.of("Range", String.format(Locale.US, "bytes=%s-%s", Long.valueOf(byteRange.getStart()), Long.valueOf(byteRange.getEnd())));
            this.mDownloadRequests.offer(new BasicDownloadRequest(this.mLastUri.getAbsoluteUrl(this.mContentUrlSelector.getCurrentContentUrl().getUrl()), immutableMapOf, new SingleFileDownloader$$ExternalSyntheticLambda0(new SingleFileSaveCallback(this.mMediaFile, byteRange)), this, this.mDownloadTimeoutNanos, new DownloadRequestPriority(PriorityTier.BACKGROUND, (int) byteRange.getSizeBytes(), 0L), true, true, false, true, false));
        }
        this.mDownloadingRequests = Maps.newConcurrentMap();
    }

    private void submitDownloadRequest() {
        synchronized (this.mSubmitRequestMutex) {
            if (!this.mStarted) {
                DLog.warnf("Downloader was stopped.");
                return;
            }
            while (this.mDownloadingRequests.size() < this.mNumberOfConcurrentDownloadRequests && !this.mDownloadRequests.isEmpty()) {
                DownloadRequest downloadRequestPoll = this.mDownloadRequests.poll();
                DownloadRequestStatus downloadRequestStatus = new DownloadRequestStatus(ByteRange.fromDownloadRequest(downloadRequestPoll));
                this.mDownloadService.submitDownloadRequest(downloadRequestPoll, true);
                this.mDownloadingRequests.put(downloadRequestPoll, downloadRequestStatus);
            }
        }
    }

    private void retryDownloadRequest(DownloadRequest downloadRequest) {
        DownloadRequestStatus downloadRequestStatus = this.mDownloadingRequests.get(downloadRequest);
        if (downloadRequestStatus == null) {
            DLog.warnf("Request was canceled before");
            return;
        }
        BasicDownloadRequest basicDownloadRequest = new BasicDownloadRequest(this.mLastUri.getAbsoluteUrl(this.mContentUrlSelector.getCurrentContentUrl().getUrl()), downloadRequest.getHeaders(), downloadRequest.getDownloadCallback(), downloadRequest.getListener(), downloadRequest.getTimeoutInNanos(), downloadRequest.getPriority(), downloadRequest.shouldDisableCompression(), downloadRequest.shouldUncompressResponse(), downloadRequest.shouldUseHttpResponseCache(), downloadRequest.shouldAllowHttpRedirects(), downloadRequest.shouldPersistHttpRedirects());
        synchronized (this.mSubmitRequestMutex) {
            if (!this.mStarted) {
                DLog.warnf("Downloader was stopped.");
                return;
            }
            this.mDownloadService.submitDownloadRequest(basicDownloadRequest, true);
            this.mDownloadingRequests.put(basicDownloadRequest, downloadRequestStatus);
            this.mDownloadingRequests.remove(downloadRequest);
        }
    }

    private void saveProgress(ByteRange byteRange) {
        synchronized (this.mDownloadContentStatus) {
            this.mMediaFileStatus.markDownloaded(byteRange);
            try {
                this.mDownloadContentStatus.save();
            } catch (IOException e) {
                DLog.warnf("Failed to save progress: %s", e.getMessage());
            }
        }
        this.mContentManagementEventBus.postEvent(new ContentEventDownloadUpdate(this.mPlayableContent, ContentSessionType.DOWNLOAD, this.mLastUri.isAudio() ? SampleType.AUDIO_SAMPLE : SampleType.VIDEO_SAMPLE));
        if (getDownloadedSizeBytes() == getTotalSizeBytes()) {
            saveFullyDownloadedStatFile();
        }
    }

    private void saveFullyDownloadedStatFile() {
        synchronized (this.mContentStore) {
            try {
                byte[] bArrSerialize = new SerializableStreamStatus(this.mLastUri.getStream().getNumChunks(), this.mLastUri.getChunkIndex()).serialize();
                this.mContentStore.storeStatFile(this.mContext, this.mLastUri.getStream(), new ByteArrayInputStream(bArrSerialize), bArrSerialize.length);
            } catch (ContentException unused) {
                DLog.warnf("(non-fatal) Failed to checkpoint streamState for stream %s...", this.mLastUri.getStream().getType());
            }
        }
    }

    @Nullable
    private String getConsumptionId() {
        ContentUrlSelector contentUrlSelector = this.mContentUrlSelector;
        if (contentUrlSelector != null) {
            return contentUrlSelector.getCurrentContentUrl().getSessionId();
        }
        return null;
    }
}
