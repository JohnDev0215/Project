package com.amazon.avod.playback.session;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackSession {
    @Nullable
    VideoCacheManager getCacheManager();

    @Nullable
    ContentSession getContentSession();

    @Nonnull
    PlaybackSessionContext getContext();

    @Nullable
    PlaybackDataSource getDataSource();

    @Nullable
    PVDownloadPlayerShim getDownload();

    @Nullable
    VideoPlaybackEngine getPlaybackEngine();

    @Nonnull
    PlaybackSessionResources getResources();

    void prepare(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nullable String str, @Nullable AudioVideoUrls audioVideoUrls);

    void restart(@Nonnull VideoSpecification videoSpecification, @Nonnull PlaybackRestartEvent playbackRestartEvent);

    void start(@Nonnull VideoRenderingSettings videoRenderingSettings);

    void terminate(boolean z, @Nullable MediaException mediaException);
}
