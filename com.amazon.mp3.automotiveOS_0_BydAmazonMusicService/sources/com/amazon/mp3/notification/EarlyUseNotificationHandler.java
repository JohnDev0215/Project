package com.amazon.mp3.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.notification.NotificationEarlyUseMetricsUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.ResourceUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.push.activity.NotificationOpenHandlerActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EarlyUseNotificationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/notification/EarlyUseNotificationHandler;", "", "Landroid/content/Context;", "context", "", "createNotificationChannel", "Landroidx/work/Data;", "data", "startNotification", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class EarlyUseNotificationHandler {
    public static final EarlyUseNotificationHandler INSTANCE = new EarlyUseNotificationHandler();

    private EarlyUseNotificationHandler() {
    }

    public final void startNotification(Context context, Data data) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        String string = data.getString("deeplink");
        String string2 = data.getString(Splash.PARAMS_CAMPAIGN_ID);
        NotificationEarlyUseMetricsUtil.INSTANCE.trackEvent(NotificationEarlyUseMetricsUtil.EventType.NOTIFICATION_SENT);
        createNotificationChannel(context);
        Intent intent = new Intent(context, (Class<?>) NotificationOpenHandlerActivity.class);
        intent.setFlags(268468224);
        intent.putExtra(Splash.PARAMS_CAMPAIGN_ID, string2);
        intent.putExtra("deeplink", string);
        intent.setAction("com.amazon.mp3.push.intent");
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(context, "AmazonMusicEarlyUseNotification").setSmallIcon(ResourceUtil.getPlayingNotificationIconId()).setContentTitle(data.getString("notification_title")).setContentText(data.getString("notification_body")).setPriority(1).setContentIntent(PendingIntentUtil.getActivity$default(PendingIntentUtil.INSTANCE, context, 0, intent, 1073741824, false, 16, null)).setAutoCancel(true);
        Intrinsics.checkNotNullExpressionValue(autoCancel, "Builder(context, CHANNEL…     .setAutoCancel(true)");
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(context)");
        notificationManagerCompatFrom.notify(0, autoCancel.build());
    }

    private final void createNotificationChannel(Context context) {
        if (PlatformUtil.isVersionOrGreater(26)) {
            NotificationChannel notificationChannel = new NotificationChannel("AmazonMusicEarlyUseNotification", "Amazon Music", 4);
            notificationChannel.setShowBadge(true);
            notificationChannel.setDescription("This is Amazon Music Early Use Notification channel");
            notificationChannel.setLockscreenVisibility(0);
            ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }
}
