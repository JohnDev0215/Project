package com.amazon.mp3.voice;

import android.content.Context;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCallback;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.voice.AlexaPlaystateProvider;
import com.amazon.music.voice.AppSwipeOutListener;

/* JADX INFO: loaded from: classes4.dex */
public class VoicePlaystateProvider implements AlexaPlaystateProvider, AppSwipeOutListener {
    private long mLastPosition = 0;
    private NowPlayingListener mNowPlayingListener = new NowPlayingCallback() { // from class: com.amazon.mp3.voice.VoicePlaystateProvider.1
        @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCallback, com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener
        public void onTrackChanged(final int position, final MusicTrack track) {
            VoicePlaystateProvider.this.mLastPosition = 0L;
        }
    };

    @Override // com.amazon.music.voice.AlexaPlaystateProvider
    public boolean isBuffering() {
        return false;
    }

    public void attach() {
        NowPlayingManager.getInstance().registerListener(this.mNowPlayingListener);
    }

    @Override // com.amazon.music.voice.AppSwipeOutListener
    public void onAppRestart(final Context context) {
        attach();
    }

    @Override // com.amazon.music.voice.AlexaPlaystateProvider
    public boolean isIdle() {
        return (NowPlayingUtil.isAlexaPlaying() && getPlaybackController().canPlay()) ? false : true;
    }

    @Override // com.amazon.music.voice.AlexaPlaystateProvider
    public boolean isPlaying() {
        return NowPlayingUtil.isAlexaPlaying() && getPlayStatus().willPlay();
    }

    @Override // com.amazon.music.voice.AlexaPlaystateProvider
    public boolean isPaused() {
        return NowPlayingUtil.isAlexaPlaying() && getPlayStatus() == PlayStatus.USER_PAUSED;
    }

    @Override // com.amazon.music.voice.AlexaPlaystateProvider
    public boolean isEnded() {
        return NowPlayingUtil.isAlexaPlaying() && !getPlaybackController().canPlay();
    }

    @Override // com.amazon.music.voice.AlexaPlaystateProvider
    public long getCurrentPosition() {
        if (!NowPlayingUtil.isAlexaPlaying()) {
            this.mLastPosition = 0L;
            return 0L;
        }
        long positionMillis = getPlaybackController().getPositionMillis();
        if (positionMillis < 0) {
            positionMillis = this.mLastPosition;
        }
        this.mLastPosition = positionMillis;
        return positionMillis;
    }

    @Override // com.amazon.music.voice.AlexaPlaystateProvider
    public long getDuration() {
        if (NowPlayingUtil.isAlexaPlaying()) {
            return getPlaybackController().getDurationMillis();
        }
        return 0L;
    }

    private PlayStatus getPlayStatus() {
        return getPlaybackController().getPlayStatus();
    }

    private PlaybackController getPlaybackController() {
        return PlaybackControllerProvider.getController(ControlSource.ALEXA);
    }
}
