package com.amazon.music.inappmessaging.ui.notification;

import android.R;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.amazon.music.inappmessaging.ui.notification.FullscreenNotificationDialog;
import com.amazon.music.inappmessaging.ui.notification.HtmlNotificationDialog;
import com.amazon.music.inappmessaging.ui.notification.NotificationDialog;

/* JADX INFO: loaded from: classes4.dex */
public final class Notifications {
    static final String KEY = "com.amazon.music.messaging.NOTIFICATION";

    public static void show(final FragmentManager manager, final FullscreenNotificationDialog dialog, final FullscreenNotificationDialog.Notification notification) {
        Bundle arguments = dialog.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putSerializable(KEY, notification);
        dialog.setStyle(0, R.style.Theme.DeviceDefault.NoActionBar);
        dialog.setArguments(arguments);
        dialog.show(manager, "NotificationFullScreenDialog");
    }

    public static void show(final FragmentManager manager, final NotificationDialog dialog, final NotificationDialog.Notification notification) {
        Bundle arguments = dialog.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putSerializable(KEY, notification);
        dialog.setArguments(arguments);
        dialog.show(manager, "NotificationDialog");
    }

    public static void show(final FragmentManager manager, final HtmlNotificationDialog dialog, final HtmlNotificationDialog.Notification notification) {
        Bundle arguments = dialog.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putSerializable(KEY, notification);
        dialog.setArguments(arguments);
        dialog.show(manager, "HtmlNotificationDialog");
    }
}
