package com.amazon.avod.playbackclient.perf;

import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.perf.Extra;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackExtras {

    public static class PlaybackDataSourceExtra extends Extra {
        private final PlaybackDataSource mDataSource;

        private PlaybackDataSourceExtra(@Nonnull PlaybackDataSource playbackDataSource) {
            super(playbackDataSource.name());
            this.mDataSource = (PlaybackDataSource) Preconditions.checkNotNull(playbackDataSource, "DataSource");
        }
    }

    public static PlaybackDataSourceExtra newPlaybackDataSourceExtra(@Nonnull PlaybackDataSource playbackDataSource) {
        return new PlaybackDataSourceExtra(playbackDataSource);
    }
}
