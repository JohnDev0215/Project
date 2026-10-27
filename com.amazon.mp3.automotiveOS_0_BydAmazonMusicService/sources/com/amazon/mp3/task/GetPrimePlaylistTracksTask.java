package com.amazon.mp3.task;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.external.api.uri.QueryPlaylistByAsin;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPlaybackComponent;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.handler.PlayWithContentUriPlaybackUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes4.dex */
public class GetPrimePlaylistTracksTask extends AsyncTask<Void, Void, Integer> {
    private Context mContext;
    private boolean mIsVideoPlayback;
    private MediaCollectionId mMediaCollectionId;
    private PlaybackPageType mPageType;
    private ControlSource mPlaybackControlSource;
    private PlaybackListener mPlaybackListener;
    private String mPlaylistAsin;
    private boolean mShuffleState;
    private final long mStartTime;
    private String mStartingTrackAsin;
    private int mStartingTrackPosition;
    private Task mTask;

    public enum Task {
        NONE,
        ADD_TO_PLAYQUEUE,
        PLAY_NEXT,
        PLAYBACK,
        PLAYBACK_FULLSCREEN
    }

    public static GetPrimePlaylistTracksTask createPlaybackTask(Context context, String playlistAsin, String startingTrackAsin, PlaybackPageType pageType, PlaybackListener playbackListener) {
        GetPrimePlaylistTracksTask getPrimePlaylistTracksTaskCreatePlaybackTask = createPlaybackTask(context, playlistAsin, startingTrackAsin, pageType, Task.PLAYBACK);
        getPrimePlaylistTracksTaskCreatePlaybackTask.mPlaybackListener = playbackListener;
        return getPrimePlaylistTracksTaskCreatePlaybackTask;
    }

    public static GetPrimePlaylistTracksTask createPlaybackTask(Context context, String playlistAsin, PlaybackPageType pageType, final boolean isVideoPlayback) {
        GetPrimePlaylistTracksTask getPrimePlaylistTracksTask = new GetPrimePlaylistTracksTask(context, playlistAsin, Task.PLAYBACK_FULLSCREEN, isVideoPlayback);
        getPrimePlaylistTracksTask.setPlaybackPageType(pageType);
        return getPrimePlaylistTracksTask;
    }

    public static GetPrimePlaylistTracksTask createPlaybackTask(Context context, String playlistAsin, String startingTrackAsin, PlaybackPageType pageType, Task playbackTask) {
        GetPrimePlaylistTracksTask getPrimePlaylistTracksTask = new GetPrimePlaylistTracksTask(context, playlistAsin, startingTrackAsin, playbackTask, false);
        getPrimePlaylistTracksTask.setPlaybackPageType(pageType);
        return getPrimePlaylistTracksTask;
    }

    public static GetPrimePlaylistTracksTask createPlaybackTask(Context context, String playlistAsin, int startIndex, PlaybackPageType pageType, Task playbackTask) {
        GetPrimePlaylistTracksTask getPrimePlaylistTracksTask = new GetPrimePlaylistTracksTask(context, playlistAsin, startIndex, playbackTask, false);
        getPrimePlaylistTracksTask.setPlaybackPageType(pageType);
        return getPrimePlaylistTracksTask;
    }

    public GetPrimePlaylistTracksTask(Context context, String playlistAsin, String startingTrackAsin, Task task, final boolean isVideoPlayback) {
        this.mPlaybackControlSource = ControlSource.APP_UI;
        this.mShuffleState = false;
        this.mStartTime = Clock.now();
        this.mContext = context;
        this.mPlaylistAsin = playlistAsin;
        this.mStartingTrackAsin = startingTrackAsin;
        this.mStartingTrackPosition = 0;
        this.mTask = task;
        this.mIsVideoPlayback = isVideoPlayback;
    }

    public GetPrimePlaylistTracksTask(Context context, String playlistAsin, int startIndex, Task task, final boolean isVideoPlayback) {
        this.mPlaybackControlSource = ControlSource.APP_UI;
        this.mShuffleState = false;
        this.mStartTime = Clock.now();
        this.mContext = context;
        this.mPlaylistAsin = playlistAsin;
        this.mStartingTrackAsin = null;
        this.mStartingTrackPosition = startIndex;
        this.mTask = task;
        this.mIsVideoPlayback = isVideoPlayback;
    }

    public GetPrimePlaylistTracksTask(Context context, String playlistAsin, Task task, final boolean isVideoPlayback) {
        this(context, playlistAsin, (String) null, task, isVideoPlayback);
    }

    public void setPlaybackPageType(PlaybackPageType pageType) {
        this.mPageType = pageType;
    }

    public void withShuffleState(final boolean state) {
        this.mShuffleState = state;
    }

    public void withMediaCollectionId(final MediaCollectionId mediaCollectionId) {
        this.mMediaCollectionId = mediaCollectionId;
    }

    public void withPlaybackControlSource(final ControlSource source) {
        this.mPlaybackControlSource = source;
    }

    @Override // android.os.AsyncTask
    public Integer doInBackground(Void... params) {
        try {
            CatalogPlaylist catalogPlaylistFetchPrimePlaylist = PrimePlaylistUtil.fetchPrimePlaylist(new PrimePlaylistDatabaseManager(this.mContext), 0, this.mPlaylistAsin);
            if (!catalogPlaylistFetchPrimePlaylist.getAsin().equals(this.mPlaylistAsin)) {
                PlaybackListener playbackListener = this.mPlaybackListener;
                if (playbackListener != null) {
                    playbackListener.onPlaybackFailure(PlaybackError.ASIN_DISCREPANCY, null);
                }
                this.mPlaylistAsin = catalogPlaylistFetchPrimePlaylist.getAsin();
            }
            if (this.mStartingTrackAsin != null) {
                QueryPlaylistByAsin queryPlaylistByAsin = new QueryPlaylistByAsin(this.mContext);
                int iFindTrackIndexInCollectionByAsin = queryPlaylistByAsin.findTrackIndexInCollectionByAsin(queryPlaylistByAsin.convertCollectionUriToTracksUri(MediaProvider.PrimePlaylists.getContentUri("cirrus", this.mPlaylistAsin)), this.mStartingTrackAsin);
                this.mStartingTrackPosition = iFindTrackIndexInCollectionByAsin;
                if (iFindTrackIndexInCollectionByAsin < 0) {
                    this.mStartingTrackPosition = 0;
                }
            }
            return 1;
        } catch (Exception e) {
            PlaybackListener playbackListener2 = this.mPlaybackListener;
            if (playbackListener2 != null) {
                playbackListener2.onPlaybackFailure(PlaybackError.FETCH_CONTENT_ERROR, e);
            }
            return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Integer integer) {
        if (integer.intValue() == 1) {
            Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", this.mPlaylistAsin);
            boolean z = AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled() && AmazonApplication.getCapabilities().isCloudQueueEnabledForAOM(this.mPlaybackControlSource);
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$task$GetPrimePlaylistTracksTask$Task[this.mTask.ordinal()];
            if (i == 1 || i == 2) {
                if (z) {
                    playThroughCloudQueue(contentUri, this.mTask);
                    return;
                } else {
                    playThroughNonCloudQueue(contentUri, this.mTask);
                    return;
                }
            }
            if (i == 3) {
                NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, false);
            } else {
                if (i != 4) {
                    return;
                }
                NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, true);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.task.GetPrimePlaylistTracksTask$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$task$GetPrimePlaylistTracksTask$Task;

        static {
            int[] iArr = new int[Task.values().length];
            $SwitchMap$com$amazon$mp3$task$GetPrimePlaylistTracksTask$Task = iArr;
            try {
                iArr[Task.PLAYBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$task$GetPrimePlaylistTracksTask$Task[Task.PLAYBACK_FULLSCREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$task$GetPrimePlaylistTracksTask$Task[Task.ADD_TO_PLAYQUEUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$task$GetPrimePlaylistTracksTask$Task[Task.PLAY_NEXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void playThroughCloudQueue(final Uri contentUri, final Task task) {
        boolean z = task == Task.PLAYBACK_FULLSCREEN;
        if (this.mStartingTrackAsin != null) {
            ContextMenuPlaybackComponent.startPlayback(this.mContext, contentUri, this.mStartingTrackPosition, null, null, this.mPageType, this.mStartTime, z, this.mIsVideoPlayback, contentUri, this.mPlaybackControlSource, this.mPlaybackListener);
        } else {
            ContextMenuPlaybackComponent.startPlayback(this.mContext, contentUri, this.mStartingTrackPosition, null, null, this.mPageType, this.mStartTime, z, this.mShuffleState, this.mIsVideoPlayback, contentUri, this.mPlaybackControlSource, this.mPlaybackListener);
        }
    }

    private void playThroughNonCloudQueue(final Uri contentUri, final Task task) {
        boolean z = task == Task.PLAYBACK_FULLSCREEN;
        if (this.mStartingTrackAsin != null) {
            PlayWithContentUriPlaybackUtil.INSTANCE.startPlayback(this.mContext, this.mPlaybackControlSource, contentUri, this.mStartingTrackPosition, null, null, this.mMediaCollectionId, this.mPageType, this.mStartTime, z, this.mShuffleState);
        } else {
            PlayWithContentUriPlaybackUtil.INSTANCE.startPlayback(this.mContext, this.mPlaybackControlSource, contentUri, this.mStartingTrackPosition, "order_num", null, this.mMediaCollectionId, this.mPageType, this.mStartTime, z, this.mShuffleState);
        }
    }
}
