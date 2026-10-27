package com.android.textclassifier.notification;

import android.app.Notification;
import android.app.RemoteInput;
import android.service.notification.StatusBarNotification;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class NotificationUtils {
    static boolean isMessaging(StatusBarNotification statusBarNotification) {
        return isCategory(statusBarNotification, "msg") || isPublicVersionCategory(statusBarNotification, "msg") || hasStyle(statusBarNotification, Notification.MessagingStyle.class);
    }

    static boolean hasInlineReply(StatusBarNotification statusBarNotification) {
        Notification.Action[] actionArr = statusBarNotification.getNotification().actions;
        if (actionArr == null) {
            return false;
        }
        for (Notification.Action action : actionArr) {
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (remoteInputs != null) {
                for (RemoteInput remoteInput : remoteInputs) {
                    if (remoteInput.getAllowFreeFormInput()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasStyle(StatusBarNotification statusBarNotification, Class<? extends Notification.Style> cls) {
        return cls.getName().equals(statusBarNotification.getNotification().extras.getString("android.template"));
    }

    private static boolean isCategory(StatusBarNotification statusBarNotification, String str) {
        return Objects.equals(statusBarNotification.getNotification().category, str);
    }

    private static boolean isPublicVersionCategory(StatusBarNotification statusBarNotification, String str) {
        Notification notification = statusBarNotification.getNotification().publicVersion;
        return notification != null && isCategoryInternal(notification, str);
    }

    private static boolean isCategoryInternal(Notification notification, String str) {
        return Objects.equals(notification.category, str);
    }
}
