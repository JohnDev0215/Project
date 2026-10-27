package defpackage;

import android.app.NotificationChannel;
import android.os.Build;

/* JADX INFO: compiled from: NotificationChannelCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class io1 {
    public final int a;

    public io1(NotificationChannel notificationChannel) {
        String id = notificationChannel.getId();
        int importance = notificationChannel.getImportance();
        id.getClass();
        this.a = importance;
        notificationChannel.getName();
        notificationChannel.getDescription();
        notificationChannel.getGroup();
        notificationChannel.canShowBadge();
        notificationChannel.getSound();
        notificationChannel.getAudioAttributes();
        notificationChannel.shouldShowLights();
        notificationChannel.getLightColor();
        notificationChannel.shouldVibrate();
        notificationChannel.getVibrationPattern();
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            notificationChannel.getParentChannelId();
            notificationChannel.getConversationId();
        }
        notificationChannel.canBypassDnd();
        notificationChannel.getLockscreenVisibility();
        notificationChannel.canBubble();
        if (i >= 30) {
            notificationChannel.isImportantConversation();
        }
    }
}
