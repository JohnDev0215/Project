package com.amazon.avod.content.downloading.caching;

import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.progressive.DownloadContentAccessor;
import com.amazon.avod.content.event.ContentEventDownloaded;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.downloading.DownloadTaskFactory;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.util.DLog;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class CacheContentAccessor extends DownloadContentAccessor {
    @Override // com.amazon.avod.content.downloading.progressive.DownloadContentAccessor
    protected void ensureNecessarySpaceToCompleteDownload() {
    }

    public CacheContentAccessor(ContentManagementEventBus contentManagementEventBus, DownloadTaskFactory downloadTaskFactory, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, FileSystem fileSystem, DownloadService downloadService, ContentUrlSelector contentUrlSelector) {
        super(contentManagementEventBus, downloadTaskFactory, fileSystem, smoothStreamingPlaybackConfig, downloadService, contentUrlSelector);
    }

    @Override // com.amazon.avod.content.downloading.progressive.DownloadContentAccessor, com.amazon.avod.content.downloading.ContentAccessorBase, com.amazon.avod.content.downloading.DownloadEventsListener
    public void onBufferFilled() {
        if (isFullyDownloadedFromNanos(this.mSessionContext.getSpecification().getStartTime().getTotalNanoSeconds())) {
            postEvent(new ContentEventDownloaded(this.mSessionContext.getContent(), this.mSessionContext.getSessionType()));
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessorBase, com.amazon.avod.content.LocalContentState
    public boolean isFullyDownloadedFromNanos(long j) {
        if (super.isFullyDownloadedFromNanos(j)) {
            return true;
        }
        long downloadedTimeAfterPositionInNanos = getDownloadedTimeAfterPositionInNanos(j);
        long totalNanoSeconds = this.mSessionContext.getDuration().getTotalNanoSeconds();
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        DLog.devf("Downloaded time is %d s, necessary time is %d s", Long.valueOf(timeUnit.toSeconds(downloadedTimeAfterPositionInNanos)), Long.valueOf(timeUnit.toSeconds(totalNanoSeconds)));
        return totalNanoSeconds <= downloadedTimeAfterPositionInNanos;
    }
}
