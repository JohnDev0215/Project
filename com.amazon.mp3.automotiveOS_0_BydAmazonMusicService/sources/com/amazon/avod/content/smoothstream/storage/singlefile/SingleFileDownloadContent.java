package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SingleFileDownloadContent {
    private final DownloadContentStatus mDownloadContentStatus;
    private final Map<String, MediaFile> mFileNameMediaFileMap;

    public SingleFileDownloadContent(@Nonnull Map<String, MediaFile> map, @Nonnull DownloadContentStatus downloadContentStatus) {
        this.mFileNameMediaFileMap = (Map) Preconditions.checkNotNull(map, "fileNameMediaFileMap");
        this.mDownloadContentStatus = (DownloadContentStatus) Preconditions.checkNotNull(downloadContentStatus, "downloadContentStatus");
    }

    @Nonnull
    public Map<String, MediaFile> getFileNameMediaFileMap() {
        return this.mFileNameMediaFileMap;
    }

    @Nonnull
    public DownloadContentStatus getDownloadContentStatus() {
        return this.mDownloadContentStatus;
    }
}
