package com.amazon.mp3.voice;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.view.player.EnabledViewState;
import com.amazon.mp3.view.player.NowPlayingViewConfig;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.voice.AppSwipeOutListener;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.music.voice.playbackstate.MediaMetadata;
import com.amazon.music.voice.playbackstate.MediaOperation;
import com.amazon.music.voice.playbackstate.MediaOperationSupported;
import com.amazon.music.voice.playbackstate.PlayerStateProvider;
import com.amazon.podcast.Podcast;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MusicPlayerStateProvider implements PlayerStateProvider, AppSwipeOutListener {
    private static final String TAG = "MusicPlayerStateProvider";
    private AudioPlayer.Callback mAudioPlayerCallback;
    private Context mContext;
    OnPlaybackEventListener mOnPlaybackEventListener;
    private PlaybackController mPlaybackController;

    @Override // com.amazon.music.voice.playbackstate.PlayerStateProvider
    public int getTrackPositionMs() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(int i, ControlSource controlSource) {
        if (NowPlayingUtil.isAlexaPlaying() || !ConnectivityUtil.hasAnyInternetConnection(this.mContext) || this.mAudioPlayerCallback == null) {
            return;
        }
        if (getPlayerState() == PlayerStateProvider.PlayerState.PLAYING || getPlayerState() == PlayerStateProvider.PlayerState.BUFFER_UNDERRUN || getPlayerState() == PlayerStateProvider.PlayerState.PAUSED) {
            this.mAudioPlayerCallback.onPlaybackStarted();
        }
    }

    public MusicPlayerStateProvider(final Context context) {
        this(context, PlaybackControllerProvider.getController(ControlSource.APP_UI));
    }

    @VisibleForTesting
    MusicPlayerStateProvider(final Context context, final PlaybackController playbackController) {
        OnPlaybackEventListener onPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.voice.MusicPlayerStateProvider$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                this.f$0.lambda$new$0(i, controlSource);
            }
        };
        this.mOnPlaybackEventListener = onPlaybackEventListener;
        this.mContext = context;
        this.mPlaybackController = playbackController;
        playbackController.addOnPlaybackEventListener(onPlaybackEventListener);
    }

    @Override // com.amazon.music.voice.AppSwipeOutListener
    public void onAppRestart(final Context context) {
        this.mPlaybackController.addOnPlaybackEventListener(this.mOnPlaybackEventListener);
    }

    @Override // com.amazon.music.voice.playbackstate.PlayerStateProvider
    public void add(AudioPlayer.Callback audioPlayerCallback) {
        this.mAudioPlayerCallback = audioPlayerCallback;
    }

    @Override // com.amazon.music.voice.playbackstate.PlayerStateProvider
    public MediaMetadata getMedia() {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        int currentIndex = this.mPlaybackController.getCurrentIndex();
        if (currentMediaItem == null) {
            return null;
        }
        Log.debug(TAG, "track: " + currentMediaItem.toString());
        return new MediaMetadata(currentMediaItem.getArtistName(), "TRACK", new MediaOperationSupported() { // from class: com.amazon.mp3.voice.MusicPlayerStateProvider$$ExternalSyntheticLambda1
            @Override // com.amazon.music.voice.playbackstate.MediaOperationSupported
            public final boolean isSupported(MediaOperation mediaOperation) {
                return this.f$0.isSupported(mediaOperation);
            }
        }, currentMediaItem.getDurationMillis(), currentMediaItem.getAlbumName(), currentMediaItem.getName(), currentIndex, MusicEMPTrackIdProvider.INSTANCE.getTrackId(currentMediaItem));
    }

    @Override // com.amazon.music.voice.playbackstate.PlayerStateProvider
    public PlayerStateProvider.PlayerState getPlayerState() {
        if (!this.mPlaybackController.canPlay()) {
            return PlayerStateProvider.PlayerState.STOPPED;
        }
        PlayStatus playStatus = this.mPlaybackController.getPlayStatus();
        if (playStatus == PlayStatus.RENDERING) {
            return PlayerStateProvider.PlayerState.PLAYING;
        }
        if (isAlexaListening() && playStatus == PlayStatus.SYSTEM_PAUSED) {
            return PlayerStateProvider.PlayerState.PLAYING;
        }
        if (playStatus == PlayStatus.USER_PAUSED || playStatus == PlayStatus.SYSTEM_PAUSED || playStatus == PlayStatus.SWITCHING_PLAYERS) {
            return PlayerStateProvider.PlayerState.PAUSED;
        }
        if (playStatus == PlayStatus.LOADING || playStatus == PlayStatus.PREPARING) {
            return PlayerStateProvider.PlayerState.BUFFER_UNDERRUN;
        }
        if (playStatus == PlayStatus.SEEKING || playStatus == PlayStatus.BUFFERING) {
            return PlayerStateProvider.PlayerState.BUFFER_UNDERRUN;
        }
        return PlayerStateProvider.PlayerState.FINISHED;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    @Override // com.amazon.music.voice.playbackstate.PlayerStateProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getPlaybackSessionId() {
        /*
            r4 = this;
            com.amazon.music.media.playback.MediaCollectionInfo r0 = r4.getMediaCollectionInfoForCurrentMediaItem()
            if (r0 == 0) goto L1b
            com.amazon.music.media.playback.MediaCollectionId$Type r1 = com.amazon.music.media.playback.MediaCollectionId.Type.ALEXA_PLAYBACK_SESSION_ID
            com.amazon.music.media.playback.MediaCollectionId r1 = r0.getId(r1)
            if (r1 != 0) goto L14
            com.amazon.music.media.playback.MediaCollectionId$Type r1 = com.amazon.music.media.playback.MediaCollectionId.Type.PLAYBACK_SESSION_ID
            com.amazon.music.media.playback.MediaCollectionId r1 = r0.getId(r1)
        L14:
            if (r1 == 0) goto L1b
            java.lang.String r0 = r1.getId()
            goto L1c
        L1b:
            r0 = 0
        L1c:
            java.lang.String r1 = com.amazon.mp3.voice.MusicPlayerStateProvider.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Playback Session ID - "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.amazon.mp3.util.Log.debug(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.voice.MusicPlayerStateProvider.getPlaybackSessionId():java.lang.String");
    }

    @Override // com.amazon.music.voice.playbackstate.PlayerStateProvider
    public PlayerStateProvider.ShuffleMode getShuffleMode() {
        if (this.mPlaybackController.isShuffled()) {
            return PlayerStateProvider.ShuffleMode.SHUFFLED;
        }
        return PlayerStateProvider.ShuffleMode.NOT_SHUFFLED;
    }

    @Override // com.amazon.music.voice.playbackstate.PlayerStateProvider
    public PlayerStateProvider.RepeatMode getRepeatMode() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$RepeatMode[this.mPlaybackController.getRepeatMode().ordinal()];
        if (i == 1) {
            return PlayerStateProvider.RepeatMode.REPEATED;
        }
        if (i == 2) {
            return PlayerStateProvider.RepeatMode.ONE_REPEATED;
        }
        return PlayerStateProvider.RepeatMode.NOT_REPEATED;
    }

    @Override // com.amazon.music.voice.playbackstate.PlayerStateProvider
    public PlayerStateProvider.FavoriteState getFavoriteState() {
        int currentItemRating = this.mPlaybackController.getCurrentItemRating();
        if (currentItemRating == 1) {
            return PlayerStateProvider.FavoriteState.FAVORITED;
        }
        if (currentItemRating == 2) {
            return PlayerStateProvider.FavoriteState.UNFAVORITED;
        }
        return PlayerStateProvider.FavoriteState.NOT_RATED;
    }

    private MediaCollectionInfo getMediaCollectionInfoForCurrentMediaItem() {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            Log.error(TAG, "Unable to get Media Collection Info for Current MediaItem");
            return null;
        }
        List<MediaCollectionInfo> collections = currentMediaItem.getCollections();
        if (collections == null || collections.isEmpty()) {
            Log.error(TAG, "MediaCollections is empty for current MediaItem - " + currentMediaItem.getName());
            return null;
        }
        return collections.get(0);
    }

    @VisibleForTesting
    public boolean isAlexaListening() {
        if (VoiceManagerSingleton.isInitialized()) {
            return VoiceManagerSingleton.getInstance().isAlexaListening();
        }
        return false;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.voice.MusicPlayerStateProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$RepeatMode;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation;

        static {
            int[] iArr = new int[MediaOperation.values().length];
            $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation = iArr;
            try {
                iArr[MediaOperation.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.PREVIOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.ADJUST_SEEK_POSITION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.SET_SEEK_POSITION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.START_OVER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.ENABLE_REPEAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.DISABLE_REPEAT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.ENABLE_REPEAT_ONE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.ENABLE_SHUFFLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.DISABLE_SHUFFLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.NEXT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.FAVORITE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.UN_FAVORITE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.FAST_FORWARD.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[MediaOperation.REWIND.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            int[] iArr2 = new int[RepeatMode.values().length];
            $SwitchMap$com$amazon$music$media$playback$RepeatMode = iArr2;
            try {
                iArr2[RepeatMode.REPEAT_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_ONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    @VisibleForTesting
    boolean isSupported(final MediaOperation mediaOperation) {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$voice$playbackstate$MediaOperation[mediaOperation.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return true;
            case 4:
                if (isPodcastPlaying()) {
                    return true;
                }
                return currentMediaItem != null && this.mPlaybackController.canSkipPrevious();
            case 5:
            case 6:
                if (isPodcastPlaying()) {
                    return true;
                }
                return currentMediaItem != null && this.mPlaybackController.canSeek();
            case 7:
                if (isPodcastPlaying()) {
                    return true;
                }
                return currentMediaItem != null && this.mPlaybackController.canRestartItem();
            case 8:
            case 9:
            case 10:
                return NowPlayingViewConfig.fromMediaItem(currentMediaItem).getShowRepeat() == EnabledViewState.ENABLED;
            case 11:
            case 12:
                return this.mPlaybackController.canShuffle();
            case 13:
                if (isPodcastPlaying()) {
                    return true;
                }
                return currentMediaItem != null && this.mPlaybackController.canSkipNext();
            case 14:
            case 15:
                return currentMediaItem != null && this.mPlaybackController.canRateCurrentItem();
            case 16:
            case 17:
                return isPodcastPlaying();
            default:
                return false;
        }
    }

    private boolean isPodcastPlaying() {
        return (Podcast.getPlayback() == null || Podcast.getPlayback().getPlaybackState() == 0) ? false : true;
    }
}
