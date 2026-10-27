package com.amazon.avod.userdownload;

import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackDownloadChangeListener {
    void onDownloadAvailabilityChanged(@Nonnull PlaybackDownload playbackDownload);

    void onDownloadProgressChanged(@Nonnull PlaybackDownload playbackDownload);

    void onDownloadStateChanged(@Nonnull PlaybackDownload playbackDownload);

    void onDownloadsAvailabilityChanged(@Nonnull ImmutableSet<PlaybackDownload> immutableSet);
}
