package com.amazon.mp3.voice;

import android.content.Context;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.voice.AppSwipeOutListener;
import com.amazon.music.voice.PlaybackEventProcessor;
import com.amazon.music.voice.VoiceManagerSingleton;

/* JADX INFO: loaded from: classes4.dex */
public class VoicePlaybackEventReceiver implements AppSwipeOutListener {
    private static final String TAG = "VoicePlaybackEventReceiver";
    private PlaybackEventProcessor mEventProcessor;
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.voice.VoicePlaybackEventReceiver$$ExternalSyntheticLambda0
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public final void onPlayStateChanged() {
            this.f$0.lambda$new$0();
        }
    };
    private OnPlaybackEventListener mOnPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.voice.VoicePlaybackEventReceiver$$ExternalSyntheticLambda1
        @Override // com.amazon.music.media.playback.OnPlaybackEventListener
        public final void onPlaybackEvent(int i, ControlSource controlSource) {
            this.f$0.lambda$new$1(i, controlSource);
        }
    };
    private MediaItem mStartedMediaItem;

    public void attach(final PlaybackEventProcessor eventProcessor) {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.ALEXA);
        controller.addOnPlaybackEventListener(this.mOnPlaybackEventListener);
        controller.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        this.mEventProcessor = eventProcessor;
    }

    @Override // com.amazon.music.voice.AppSwipeOutListener
    public void onAppRestart(final Context context) {
        attach(VoiceManagerSingleton.getInstance().getPlaybackEventProcessor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.ALEXA);
        MediaItem currentMediaItem = controller.getCurrentMediaItem();
        if (PlayStatus.RENDERING.equals(controller.getPlayStatus()) && NowPlayingUtil.isAlexaPlaying() && this.mStartedMediaItem != currentMediaItem) {
            PlaybackEventProcessor playbackEventProcessor = this.mEventProcessor;
            if (playbackEventProcessor != null) {
                playbackEventProcessor.onPlaybackIdle();
                this.mEventProcessor.onPlaybackStarted();
            }
            this.mStartedMediaItem = currentMediaItem;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(int i, ControlSource controlSource) {
        String str = TAG;
        Log.debug(str, "Received playbackEvent: " + i);
        if (this.mEventProcessor == null || i == 17) {
            return;
        }
        if (!NowPlayingUtil.isAlexaPlaying()) {
            VoiceManagerSingleton.getInstance().clearQueue();
            this.mEventProcessor.onPlaybackIdle();
            return;
        }
        if (i != 1 && i != 2 && i != 3) {
            if (i != 18) {
                if (i == 24) {
                    Log.debug(str, "Received playbackEvent:Shuffle:Enable");
                    VoiceManagerSingleton.getInstance().issueShuffleCommand(true);
                    return;
                } else {
                    if (i != 25) {
                        switch (i) {
                            case 12:
                            case 15:
                                this.mEventProcessor.onPlaybackPaused();
                                break;
                            case 13:
                                this.mEventProcessor.onPlaybackIdle();
                                break;
                        }
                        return;
                    }
                    Log.debug(str, "Received playbackEvent:Shuffle:Disable");
                    VoiceManagerSingleton.getInstance().issueShuffleCommand(false);
                    return;
                }
            }
            this.mEventProcessor.onPlaybackStarted();
            return;
        }
        this.mEventProcessor.onPlaybackIdle();
    }
}
