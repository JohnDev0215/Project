package com.amazon.mp3.voice;

import android.content.Context;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.voice.AlexaPlaybackController;

/* JADX INFO: loaded from: classes4.dex */
public class VoicePlaybackController implements AlexaPlaybackController {
    private static final String TAG = "VoicePlaybackController";
    private final Context mContext;

    public VoicePlaybackController(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.music.voice.AlexaPlaybackController
    public void resume() {
        Log.debug(TAG, "Resume");
        if (NowPlayingUtil.isAlexaPlaying()) {
            ControlSource controlSource = ControlSource.ALEXA;
            if (PlaybackControllerProvider.getController(controlSource).getPlayStatus().equals(PlayStatus.USER_PAUSED)) {
                PlaybackControllerProvider.getController(controlSource).play();
            }
        }
    }

    @Override // com.amazon.music.voice.AlexaPlaybackController
    public void pause() {
        if (NowPlayingUtil.isAlexaPlaying()) {
            PlaybackControllerProvider.getController(ControlSource.ALEXA).pause();
        }
    }

    @Override // com.amazon.music.voice.AlexaPlaybackController
    public void stop() {
        Log.debug(TAG, "Stop");
        if (NowPlayingUtil.isAlexaPlaying()) {
            PlaybackControllerProvider.getController(ControlSource.ALEXA).pause();
        }
    }

    @Override // com.amazon.music.voice.AlexaPlaybackController
    public void seek(final long positionInMillis) {
        Log.debug(TAG, "seek position in millisec: " + positionInMillis);
        if (!NowPlayingUtil.isAlexaPlaying() || positionInMillis <= 0) {
            return;
        }
        ControlSource controlSource = ControlSource.ALEXA;
        PlaybackControllerProvider.getController(controlSource).seek(positionInMillis, 0);
        if (PlaybackControllerProvider.getController(controlSource).getPlayStatus().equals(PlayStatus.USER_PAUSED)) {
            PlaybackControllerProvider.getController(controlSource).play();
        }
    }
}
