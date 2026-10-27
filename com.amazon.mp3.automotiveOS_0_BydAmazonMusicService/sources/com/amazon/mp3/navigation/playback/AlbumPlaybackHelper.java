package com.amazon.mp3.navigation.playback;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.layout.music.model.AlbumHint;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumPlaybackHelper {
    private final Context mContext;

    public AlbumPlaybackHelper(final Context context) {
        this.mContext = context;
    }

    public void play(@Nullable final AlbumHint hint, final String albumAsin, final boolean showNowPlaying, final ControlSource controlSource, final boolean isUserInitiated) {
        play(hint, albumAsin, showNowPlaying, (String) null);
    }

    public void play(@Nullable final AlbumHint hint, final String albumAsin, final boolean showNowPlaying, final String startingTrackAsin) {
        PrimeCollectionTask.Task task;
        if (showNowPlaying) {
            task = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
        } else {
            task = PrimeCollectionTask.Task.PLAYBACK;
        }
        Context context = this.mContext;
        PrimeCollectionTask.createPlaybackTask(context, albumAsin, startingTrackAsin, PlaybackPageType.ALBUM_DETAIL, task, new QueryAlbumByAsin(context), (PrimeCollectionTask.OnUriReadyCallback) null).execute();
    }

    public void play(@Nullable final AlbumHint hint, final String albumAsin, final PrimeCollectionTask.Task playbackTask, final String startingTrackAsin) {
        Context context = this.mContext;
        PrimeCollectionTask.createPlaybackTask(context, albumAsin, startingTrackAsin, PlaybackPageType.ALBUM_DETAIL, playbackTask, new QueryAlbumByAsin(context), (PrimeCollectionTask.OnUriReadyCallback) null).execute();
    }
}
