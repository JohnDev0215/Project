package com.amazon.mp3.playback.service.streaming;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.library.activity.BaseAccountValidationWebView;
import com.amazon.mp3.library.activity.LibraryDeviceAuthorizationWebViewActivity;
import com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.util.ResourceUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingBlueMoonErrorHandler {
    private static final int NOTIFICATION_ID = UniqueCodeUtil.nextUniqueCode();
    private static AtomicBoolean sTriggered = new AtomicBoolean(false);

    public static boolean isTriggered() {
        return sTriggered.get();
    }

    public static void reset() {
        sTriggered.set(false);
    }

    public static void startIfTriggered(Context context) {
        if (isTriggered()) {
            reset();
            BlueMoonExceptionsUtil.processBlueMoonException(context);
        }
    }

    public static void showNotification(Context context, Intent intent) {
        Intent startIntent;
        int i;
        int i2;
        Bundle extras;
        if (BlueMoonExceptionsUtil.isBlueMoonException()) {
            boolean z = false;
            if (intent != null && (extras = intent.getExtras()) != null) {
                z = extras.getBoolean("com.amazon.mp3.util.FROM_PLAYBACK", false);
            }
            if (z) {
                Intent intent2 = new Intent("com.amazon.mp3.playerservicecommand.stop");
                intent2.putExtra("com.amazon.mp3.playstate_mutation_reason", PlayStateMutationReason.BLUEMOON_STREAMING_ERROR);
                context.sendBroadcast(intent2);
            }
            if (CirrusExceptions.AccountNotVerifiedException.hasBeenDetected()) {
                startIntent = BaseAccountValidationWebView.getStartIntent(context);
                i = R.string.dmusic_streaming_account_not_verified_exception_notification_title;
                i2 = R.string.dmusic_streaming_account_not_verified_exception_notification_message;
            } else if (CirrusExceptions.DeviceException.hasBeenDetected()) {
                startIntent = LibraryDeviceAuthorizationWebViewActivity.getStartIntent(context);
                i = R.string.dmusic_streaming_device_exception_notification_title;
                i2 = R.string.dmusic_streaming_device_exception_notification_message;
            } else if (CirrusExceptions.DeviceNotAuthorizedException.hasBeenDetected()) {
                startIntent = LibraryDeviceAuthorizationWebViewActivity.getStartIntent(context);
                i = R.string.dmusic_streaming_device_not_authorizing_exception_notification_title;
                i2 = R.string.dmusic_streaming_device_not_authorizing_exception_notification_message;
            } else {
                throw new IllegalArgumentException("Showing notification with no error to show");
            }
            Intent intent3 = startIntent;
            intent3.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
            intent3.addFlags(537001984);
            PendingIntent activity = PendingIntentUtil.INSTANCE.getActivity(context, 0, intent3, 134217728, true);
            String string = context.getString(i);
            Notification.Builder when = new Notification.Builder(context).setContentIntent(activity).setTicker(string).setContentTitle(string).setContentText(context.getString(i2)).setSmallIcon(ResourceUtil.getWarningNotificationIconId()).setAutoCancel(true).setWhen(System.currentTimeMillis());
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            BlueMoonExceptionsUtil.disableBlueMoonException();
            notificationManager.notify(NOTIFICATION_ID, when.getNotification());
        }
    }

    public static void hideNotification(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancel(NOTIFICATION_ID);
    }
}
