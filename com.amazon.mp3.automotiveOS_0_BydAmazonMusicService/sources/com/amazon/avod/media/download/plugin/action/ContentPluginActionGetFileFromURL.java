package com.amazon.avod.media.download.plugin.action;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.plugin.DownloadData;
import com.amazon.avod.media.downloadservice.BasicDownloadRequest;
import com.amazon.avod.media.downloadservice.BlockingDownloadAdapter;
import com.amazon.avod.media.downloadservice.DownloadRequestPriority;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.media.downloadservice.SaveCallback;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.GenericMediaException;
import com.amazon.avod.media.framework.storage.DiskStorage;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ContentPluginActionGetFileFromURL extends ContentFetcherPluginActionBase {
    private final BlockingDownloadAdapter mBlockingDownloadAdapter;
    private final DiskStorage mDiskStorage;

    public ContentPluginActionGetFileFromURL(@Nonnull DownloadService downloadService) {
        this(new BlockingDownloadAdapter(downloadService), new DiskStorage());
    }

    ContentPluginActionGetFileFromURL(@Nonnull BlockingDownloadAdapter blockingDownloadAdapter, @Nonnull DiskStorage diskStorage) {
        this.mBlockingDownloadAdapter = blockingDownloadAdapter;
        this.mDiskStorage = diskStorage;
    }

    @Nullable
    @Deprecated
    protected File downloadFromURL(File file, String str, TimeSpan timeSpan, int i) {
        return downloadFileDataFromURL(file, str, timeSpan, i, false, PriorityTier.AUXILIARY).getDownloadData();
    }

    @Nonnull
    protected DownloadData<File> downloadFileDataFromURL(File file, String str, TimeSpan timeSpan, int i, PriorityTier priorityTier) {
        return downloadFileDataFromURL(file, str, timeSpan, i, false, priorityTier);
    }

    @Nonnull
    protected DownloadData<File> downloadFileDataFromURL(@Nonnull File file, String str, TimeSpan timeSpan, int i, boolean z, PriorityTier priorityTier) {
        Preconditions.checkNotNull(file, "localPath");
        BlockingDownloadAdapter.BlockingDownloadResult blockingDownloadResultPerformDownload = performDownload(new PluginFileSaveCallback(file), str, timeSpan, i, z, priorityTier);
        if (blockingDownloadResultPerformDownload.isSuccess()) {
            DLog.logf("Completed the download of %s for %s", getPluginContext().getTitleId(), this);
            return new DownloadData<>(file, getDownloadTime(blockingDownloadResultPerformDownload));
        }
        DLog.errorf("Failed to download %s for %s due to %s", getPluginContext().getTitleId(), this, blockingDownloadResultPerformDownload.getException());
        return new DownloadData<>(null, getDownloadTime(blockingDownloadResultPerformDownload));
    }

    private long getDownloadTime(BlockingDownloadAdapter.BlockingDownloadResult blockingDownloadResult) {
        DownloadStatistics statistics = blockingDownloadResult.getStatistics();
        if (statistics != null) {
            return TimeUnit.MILLISECONDS.convert(statistics.getTotalTimeInNanos(), TimeUnit.NANOSECONDS);
        }
        return -1L;
    }

    private BlockingDownloadAdapter.BlockingDownloadResult performDownload(SaveCallback saveCallback, String str, TimeSpan timeSpan, int i, boolean z, PriorityTier priorityTier) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(timeSpan);
        Preconditions.checkState(i > 0);
        return this.mBlockingDownloadAdapter.submitRequestAndBlock(new BasicDownloadRequest(str, saveCallback, this.mBlockingDownloadAdapter, timeSpan.getTotalNanoSeconds(), new DownloadRequestPriority(priorityTier, i, 0L), z, true), false);
    }

    protected void saveDownload(File file, InputStream inputStream, int i) throws Throwable {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(inputStream);
        try {
            this.mDiskStorage.putInputStream(file.getAbsolutePath(), inputStream, i);
        } catch (IOException e) {
            throw GenericMediaException.wrapIfNeeded(e, StandardErrorCode.DISK_IO_ERROR);
        }
    }

    class PluginFileSaveCallback implements SaveCallback {
        private final File mSavePath;

        public PluginFileSaveCallback(File file) {
            this.mSavePath = file;
        }

        @Override // com.amazon.avod.media.downloadservice.SaveCallback
        public void save(InputStream inputStream, int i) throws Throwable {
            ContentPluginActionGetFileFromURL.this.saveDownload(this.mSavePath, inputStream, i);
        }
    }
}
