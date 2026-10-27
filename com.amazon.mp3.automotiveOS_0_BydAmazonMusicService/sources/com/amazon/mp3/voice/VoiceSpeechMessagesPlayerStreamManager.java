package com.amazon.mp3.voice;

import android.content.Context;
import android.os.Handler;
import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;

/* JADX INFO: loaded from: classes4.dex */
public class VoiceSpeechMessagesPlayerStreamManager {
    private final Context mContext;
    private String mStreamURL;
    private final int CLOSE_NOW_PLAYING_DELAY = 500;
    private final AudioPlayer.SimpleCallback mSimpleCallback = new AudioPlayer.SimpleCallback() { // from class: com.amazon.mp3.voice.VoiceSpeechMessagesPlayerStreamManager.1
        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackEnded() {
            new Handler().postDelayed(new Runnable() { // from class: com.amazon.mp3.voice.VoiceSpeechMessagesPlayerStreamManager.1.1
                @Override // java.lang.Runnable
                public void run() {
                    NowPlayingUtil.clearAndFinishNowPlaying(VoiceSpeechMessagesPlayerStreamManager.this.mContext, PlayStateMutationReason.ALEXA_PLAYBACK_ENDED);
                    VoiceSpeechMessagesPlayerStreamManager.this.reset();
                }
            }, 500L);
        }
    };

    public VoiceSpeechMessagesPlayerStreamManager(final Context context) {
        this.mContext = context;
    }

    public void setStreamURL(final String streamURL) {
        this.mStreamURL = streamURL;
    }

    public String getStreamURL() {
        return this.mStreamURL;
    }

    public void reset() {
        this.mStreamURL = null;
    }

    public AudioPlayer.SimpleCallback getSimpleCallback() {
        return this.mSimpleCallback;
    }
}
