package com.amazon.music.playbacknotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackNotificationManager {
    private static final Logger LOG = LoggerFactory.getLogger(PlaybackNotificationManager.class.getSimpleName());
    private static PlaybackNotificationInterface playbackNotificationInterface;
    private static PlaybackNotificationReceiver playbackNotificationReceiver;
    private final Context context;
    private final NotificationManager notificationManager;

    public PlaybackNotificationManager(Context context, PlaybackNotificationInterface playbackNotificationInterface2) {
        this.context = context;
        playbackNotificationInterface = playbackNotificationInterface2;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        this.notificationManager = notificationManager;
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("android_music_playback_notification", "Playback Controls", 2);
            notificationChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        PlaybackNotificationReceiver playbackNotificationReceiver2 = playbackNotificationReceiver;
        if (playbackNotificationReceiver2 == null) {
            playbackNotificationReceiver = new PlaybackNotificationReceiver(context, playbackNotificationInterface2);
        } else {
            playbackNotificationReceiver2.setInterface(playbackNotificationInterface2);
        }
        removeNotification();
    }

    public void removeNotification() {
        if (!playbackNotificationInterface.nowPlayingHasSongs() && !playbackNotificationInterface.hasNext()) {
            LOG.debug("Stop Foreground");
            playbackNotificationInterface.stopForeground();
        }
        this.notificationManager.cancel(1738);
        quitLooper();
    }

    private synchronized void quitLooper() {
        if (Looper.myLooper() != null && Looper.myLooper() != Looper.getMainLooper()) {
            Looper.myLooper().quit();
        }
    }
}
