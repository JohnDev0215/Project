package com.amazon.music.media.playback.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MediaButtonReceiver extends BroadcastReceiver {
    private static final Logger logger = LoggerFactory.getLogger(MediaButtonReceiver.class.getSimpleName());
    private static final PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.EXTERNAL);

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Logger logger2;
        String strNullToEmpty = intent == null ? "" : StringUtils.nullToEmpty(intent.getAction());
        logger2 = logger;
        logger2.info("onReceive(" + intent + ", " + strNullToEmpty + ")");
        strNullToEmpty.hashCode();
        switch (strNullToEmpty) {
            case "action_removed_notification":
                playbackController.getPlayback().getPlaybackConfig();
                context.stopService(new Intent().setClass(context, PlaybackService.class));
                break;
            case "action_thumbs_up":
                playbackController.toggleThumbsUp();
                break;
            case "action_thumbs_down":
                playbackController.toggleThumbsDown();
                break;
            case "action_toggle_repeat_mode":
                playbackController.toggleRepeatMode();
                break;
            case "action_toggle_shuffle_mode":
                playbackController.setShuffled(!r6.isShuffled());
                break;
            case "android.intent.action.MEDIA_BUTTON":
                MediaSessionCompat mediaSession = playbackController.getPlayback().getMediaSession();
                KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
                if (mediaSession != null && keyEvent != null) {
                    mediaSession.getController().dispatchMediaButtonEvent(keyEvent);
                    break;
                }
                break;
            default:
                logger2.warn("Unknown Intent received");
                break;
        }
    }
}
