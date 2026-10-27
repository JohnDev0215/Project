package com.amazon.avod.download;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.CuepointPlaylistInfo;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.userdownload.internal.PlaybackDownloadJob;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackDownloadListenerInternal {
    void onJobStarted(@Nonnull PlaybackDownloadJob playbackDownloadJob);

    void updateAudioTrackMetadataList(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull ImmutableList<AudioTrackMetadata> immutableList);

    void updateCuepointPlayList(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull CuepointPlaylistInfo cuepointPlaylistInfo);

    void updateDeletionProgress(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnegative int i);

    void updateDrmAssetId(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull String str);

    void updateFileSize(PlaybackDownloadJob playbackDownloadJob, long j);

    void updateProgress(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull SampleType sampleType, @Nonnull ContentSession contentSession, long j, long j2);

    void updateState(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull Downloadable.DownloadableState downloadableState, @Nonnull Optional<MediaErrorCode> optional);
}
