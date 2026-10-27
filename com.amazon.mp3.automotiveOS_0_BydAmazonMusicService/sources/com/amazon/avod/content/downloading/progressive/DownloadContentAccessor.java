package com.amazon.avod.content.downloading.progressive;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.ContentAccessorBase;
import com.amazon.avod.content.downloading.DefaultDownloadAvailabilityController;
import com.amazon.avod.content.event.ContentEventDownloaded;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.downloading.DownloadTaskFactory;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadContentAccessor extends ContentAccessorBase {
    private static final ImmutableSet<ContentException.ContentError> RETRIABLE_ERRORS = ImmutableSet.of(ContentException.ContentError.CDN_ERROR, ContentException.ContentError.NETWORK_ERROR, ContentException.ContentError.SERVICE_ERROR, ContentException.ContentError.UNKNOWN_ERROR, ContentException.ContentError.DATA_CONNECTION_UNAVAILABLE_ERROR, ContentException.ContentError.INVALID_CONTENT_LENGTH, new ContentException.ContentError[0]);
    private final SmoothStreamingPlaybackConfig mConfig;
    private final FileSystem mFileSystem;

    public DownloadContentAccessor(ContentManagementEventBus contentManagementEventBus, DownloadTaskFactory downloadTaskFactory, FileSystem fileSystem, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, DownloadService downloadService, ContentUrlSelector contentUrlSelector) {
        super(new DefaultDownloadAvailabilityController(), contentManagementEventBus, downloadTaskFactory, downloadService, contentUrlSelector);
        this.mConfig = smoothStreamingPlaybackConfig;
        this.mFileSystem = fileSystem;
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessorBase, com.amazon.avod.content.downloading.ContentAccessor
    public void start(ContentSessionContext contentSessionContext) throws ContentException {
        super.start(contentSessionContext);
        ensureNecessarySpaceToCompleteDownload();
    }

    protected void ensureNecessarySpaceToCompleteDownload() throws ContentException {
        long remainingNeededSizeInBytes = getRemainingNeededSizeInBytes();
        if (remainingNeededSizeInBytes == 0) {
            DLog.logf("Content fully available on disk.");
            return;
        }
        DataUnit dataUnit = DataUnit.BYTES;
        DLog.logf("Estimated remaining content size on disk is %s MB", Float.valueOf(dataUnit.toMegaBytes(remainingNeededSizeInBytes)));
        long remainingDiskSpaceInBytes = this.mFileSystem.getRemainingDiskSpaceInBytes(this.mSessionContext.getStoragePath());
        long bytes = DataUnit.MEGABYTES.toBytes(this.mConfig.getDiskSpaceOverheadMegs());
        long j = remainingNeededSizeInBytes + bytes;
        float f = bytes;
        DLog.logf("Free disk space is %s MB, disk space overhead is %s MB", Float.valueOf(dataUnit.toMegaBytes(remainingDiskSpaceInBytes)), Float.valueOf(dataUnit.toMegaBytes(f)));
        if (remainingDiskSpaceInBytes < j) {
            String str = String.format(Locale.US, "Disk is too full before starting download for %s (Disk space remaining : %s MB)", this.mSessionContext.getContent().getTitleId(), Float.valueOf(dataUnit.toMegaBytes(f)));
            DLog.logf(str);
            if (this.mFileSystem.requestStorage(j, this.mSessionContext.getStoragePath())) {
                DLog.logf("Platform has indicated storage will be made available by OS, starting download %s despite lack of space", this.mSessionContext.getContent().getTitleId());
            } else {
                ContentException contentException = new ContentException(ContentException.ContentError.DISK_FULL, str);
                super.onDownloadError(null, null, contentException, 0, null);
                throw contentException;
            }
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessorBase, com.amazon.avod.content.downloading.DownloadEventsListener
    public void onBufferFilled() {
        if (this.mDownloadTask.isFullyDownloadedFromNanos(0L)) {
            postEvent(new ContentEventDownloaded(this.mSessionContext.getContent(), this.mSessionContext.getSessionType()));
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessorBase, com.amazon.avod.content.downloading.DownloadEventsListener
    public void onDownloadError(ContentUrl contentUrl, SmoothStreamingURI smoothStreamingURI, ContentException contentException, int i, @Nullable Map<String, List<String>> map) {
        ContentException.ContentError errorCode = contentException.getErrorCode();
        boolean zContains = RETRIABLE_ERRORS.contains(errorCode);
        int numberOfRetriesBeforeEndingProgressiveDownload = this.mConfig.getNumberOfRetriesBeforeEndingProgressiveDownload();
        boolean z = i <= numberOfRetriesBeforeEndingProgressiveDownload;
        if ((zContains && z) || contentException.getErrorCode() == ContentException.ContentError.INVALID_CONTENT_LENGTH) {
            super.onDownloadError(contentUrl, smoothStreamingURI, contentException, i, map);
        } else {
            if (this.mSessionContext.isDownload() && contentException.getErrorCode() == ContentException.ContentError.DATA_CONNECTION_UNAVAILABLE_ERROR) {
                return;
            }
            DLog.logf("Messaging fatal error: %s (isRetriable = %s, tryCount = %d/%d)", errorCode, Boolean.valueOf(zContains), Integer.valueOf(i), Integer.valueOf(numberOfRetriesBeforeEndingProgressiveDownload));
            super.postEvent(new FatalContentEventError(this.mSessionContext.getContent(), this.mSessionContext.getSessionType(), contentException, null, null, map, getConsumptionId(), this.mSessionContext.getEffectiveSessionType()));
        }
    }
}
