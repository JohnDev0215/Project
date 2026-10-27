package com.amazon.avod.content.downloading.streaming;

import com.amazon.avod.content.downloading.ContentAccessorBase;
import com.amazon.avod.content.downloading.DownloadAvailabilityController;
import com.amazon.avod.content.event.ContentEventBufferFullnessChanged;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.downloading.CompositeDownloadTask;
import com.amazon.avod.content.smoothstream.downloading.DownloadTaskFactory;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.downloadservice.DownloadService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class StreamingContentAccessor extends ContentAccessorBase {
    public StreamingContentAccessor(DownloadAvailabilityController downloadAvailabilityController, ContentManagementEventBus contentManagementEventBus, DownloadTaskFactory downloadTaskFactory, DownloadService downloadService, ContentUrlSelector contentUrlSelector) {
        super(downloadAvailabilityController, contentManagementEventBus, downloadTaskFactory, downloadService, contentUrlSelector);
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessorBase, com.amazon.avod.content.downloading.DownloadEventsListener
    public void onBufferSizeChange(long j, StreamIndex streamIndex, boolean z) {
        postEvent(new ContentEventBufferFullnessChanged(this.mSessionContext.getContent(), this.mSessionContext.getSessionType(), j, streamIndex));
        super.onBufferSizeChange(j, streamIndex, z);
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessorBase, com.amazon.avod.content.downloading.ContentAccessor
    public void restrictToQuality(@Nonnull QualityLevel qualityLevel, @Nonnull long j) {
        CompositeDownloadTask compositeDownloadTask = this.mDownloadTask;
        if (compositeDownloadTask != null) {
            compositeDownloadTask.restrictToQuality(qualityLevel, j);
        }
    }
}
