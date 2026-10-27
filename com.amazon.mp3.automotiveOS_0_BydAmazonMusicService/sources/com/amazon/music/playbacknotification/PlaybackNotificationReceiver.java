package com.amazon.music.playbacknotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackNotificationReceiver extends BroadcastReceiver {
    private static PlaybackNotificationInterface playbackNotificationInterface;
    private final Logger LOG = LoggerFactory.getLogger(PlaybackNotificationReceiver.class.getSimpleName());
    private final Context context;

    public PlaybackNotificationReceiver(Context context, PlaybackNotificationInterface playbackNotificationInterface2) {
        this.context = context;
        playbackNotificationInterface = playbackNotificationInterface2;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.amazon.music.playbacknotification.next");
        intentFilter.addAction("com.amazon.music.playbacknotification.pause");
        intentFilter.addAction("com.amazon.music.playbacknotification.play");
        intentFilter.addAction("com.amazon.music.playbacknotification.prev");
        intentFilter.addAction("com.amazon.music.playbacknotification.thumb_up");
        intentFilter.addAction("com.amazon.music.playbacknotification.thumb_down");
        intentFilter.addAction("com.amazon.music.playbacknotification.add");
        intentFilter.addAction("com.amazon.music.playbacknotification.stop_cast");
        context.registerReceiver(this, intentFilter);
    }

    public void setInterface(PlaybackNotificationInterface playbackNotificationInterface2) {
        playbackNotificationInterface = playbackNotificationInterface2;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        action = intent.getAction();
        this.LOG.debug("Received intent with action " + action);
        action.hashCode();
        switch (action) {
            case "com.amazon.music.playbacknotification.add":
                playbackNotificationInterface.addTrackToLibrary();
                break;
            case "com.amazon.music.playbacknotification.pause":
                playbackNotificationInterface.pause();
                break;
            case "com.amazon.music.playbacknotification.thumb_up":
                playbackNotificationInterface.thumbsDown();
                break;
            case "com.amazon.music.playbacknotification.next":
                playbackNotificationInterface.next();
                break;
            case "com.amazon.music.playbacknotification.play":
                playbackNotificationInterface.play();
                break;
            case "com.amazon.music.playbacknotification.prev":
                playbackNotificationInterface.prev();
                break;
            case "com.amazon.music.playbacknotification.stop_cast":
                break;
            case "com.amazon.music.playbacknotification.thumb_down":
                playbackNotificationInterface.thumbsUp();
                break;
            default:
                this.LOG.error("Unknown intent ignored. Action= " + action);
                break;
        }
    }
}
