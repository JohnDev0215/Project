package com.amazon.avod.content.backgrounddownload;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.event.ContentEventDownloadUpdate;
import com.amazon.avod.content.event.ContentEventDownloaded;
import com.amazon.avod.content.event.ContentEventStartDownloading;
import com.amazon.avod.content.event.ContentEventStopDownloading;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.singlefile.ByteRange;
import com.amazon.avod.content.smoothstream.storage.singlefile.JsonDownloadContentStatus;
import com.amazon.avod.content.smoothstream.storage.singlefile.RandomAccessFileBasedMediaFile;
import com.amazon.avod.content.smoothstream.storage.singlefile.SingleFileStoreUtils;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.playback.sampling.SampleType;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class CompositeDownloader implements DownloadTask {
    private final PlayableContent mContent;
    private final ContentManagementEventBus mContentManagementEventBus;
    private List<DownloadTask> mDownloadTasks;
    private boolean mSentDownloadedEvent = false;

    static DownloadTask newDownloadTask(List<SmoothStreamingURI> list, DownloadService downloadService, File file, ContentManagementEventBus contentManagementEventBus, ContentUrlSelector contentUrlSelector, PlayableContent playableContent, SingleFileDownloadConfig singleFileDownloadConfig, SmoothStreamingContentStore smoothStreamingContentStore, ContentSessionContext contentSessionContext) throws IOException {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
        JsonDownloadContentStatus jsonDownloadContentStatusFromFileOrNew = JsonDownloadContentStatus.fromFileOrNew(SingleFileStoreUtils.buildDownloadStatusFilePath(file));
        for (SmoothStreamingURI smoothStreamingURI : list) {
            String fileNameFromSSU = SingleFileStoreUtils.getFileNameFromSSU(smoothStreamingURI);
            String strBuildMediaFilePath = SingleFileStoreUtils.buildMediaFilePath(file, fileNameFromSSU);
            long end = ByteRange.fromSmoothStreamingURI(smoothStreamingURI).getEnd() + 1;
            RandomAccessFileBasedMediaFile randomAccessFileBasedMediaFileNewInstance = RandomAccessFileBasedMediaFile.newInstance(strBuildMediaFilePath, end, StorageHelper.getInstance());
            if (!jsonDownloadContentStatusFromFileOrNew.getFileNames().contains(fileNameFromSSU)) {
                jsonDownloadContentStatusFromFileOrNew.addMediaFileStatus(fileNameFromSSU, smoothStreamingURI.getQualityLevel().getBitrate(), end);
            }
            ArrayList arrayList = arrayListNewArrayListWithCapacity;
            arrayList.add(new SingleFileDownloader(downloadService, randomAccessFileBasedMediaFileNewInstance, jsonDownloadContentStatusFromFileOrNew.getMediaFileStatus(fileNameFromSSU), jsonDownloadContentStatusFromFileOrNew, contentManagementEventBus, playableContent, smoothStreamingURI, contentUrlSelector, smoothStreamingURI.isVideo() ? singleFileDownloadConfig.getVideoConcurrentRequests() : singleFileDownloadConfig.getAudioConcurrentRequests(), smoothStreamingContentStore, contentSessionContext, singleFileDownloadConfig.getMaxChunkSizeBytes(), singleFileDownloadConfig.getCheckPointStepBytes(), singleFileDownloadConfig.getDownloadRequestTimeout().getTotalNanoSeconds()));
            arrayListNewArrayListWithCapacity = arrayList;
            jsonDownloadContentStatusFromFileOrNew = jsonDownloadContentStatusFromFileOrNew;
        }
        CompositeDownloader compositeDownloader = new CompositeDownloader(arrayListNewArrayListWithCapacity, contentManagementEventBus, playableContent);
        contentManagementEventBus.registerEventBusHandler(compositeDownloader);
        return compositeDownloader;
    }

    CompositeDownloader(@Nonnull List<DownloadTask> list, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlayableContent playableContent) {
        this.mDownloadTasks = (List) Preconditions.checkNotNull(list, "downloadTasks");
        this.mContentManagementEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        this.mContent = (PlayableContent) Preconditions.checkNotNull(playableContent, "playableContent");
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public void start() {
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            it.next().start();
        }
        this.mContentManagementEventBus.postEvent(new ContentEventStartDownloading(this.mContent, ContentSessionType.DOWNLOAD, getTotalSizeBytes()));
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public void stop() {
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            it.next().stop();
        }
        this.mContentManagementEventBus.postEvent(new ContentEventStopDownloading(this.mContent, ContentSessionType.DOWNLOAD));
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public long getDownloadedSizeBytes() {
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        long downloadedSizeBytes = 0;
        while (it.hasNext()) {
            downloadedSizeBytes += it.next().getDownloadedSizeBytes();
        }
        return downloadedSizeBytes;
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public float getPlayableDownloadRatio() {
        float playableDownloadRatio = 0.0f;
        for (DownloadTask downloadTask : this.mDownloadTasks) {
            if (playableDownloadRatio < downloadTask.getPlayableDownloadRatio()) {
                playableDownloadRatio = downloadTask.getPlayableDownloadRatio();
            }
        }
        return playableDownloadRatio;
    }

    @Override // com.amazon.avod.content.backgrounddownload.DownloadTask
    public long getTotalSizeBytes() {
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        long totalSizeBytes = 0;
        while (it.hasNext()) {
            totalSizeBytes += it.next().getTotalSizeBytes();
        }
        return totalSizeBytes;
    }

    @Subscribe
    public void handleDownloadUpdateEvent(ContentEventDownloadUpdate contentEventDownloadUpdate) {
        if (this.mSentDownloadedEvent || getDownloadedSizeBytes() != getTotalSizeBytes()) {
            return;
        }
        ContentManagementEventBus contentManagementEventBus = this.mContentManagementEventBus;
        PlayableContent playableContent = this.mContent;
        ContentSessionType contentSessionType = ContentSessionType.DOWNLOAD;
        contentManagementEventBus.postEvent(new ContentEventDownloadUpdate(playableContent, contentSessionType, SampleType.AUDIO_SAMPLE));
        this.mContentManagementEventBus.postEvent(new ContentEventDownloadUpdate(this.mContent, contentSessionType, SampleType.VIDEO_SAMPLE));
        this.mContentManagementEventBus.postEvent(new ContentEventDownloaded(this.mContent, contentSessionType));
        this.mSentDownloadedEvent = true;
    }
}
