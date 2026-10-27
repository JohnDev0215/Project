package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;

/* JADX INFO: loaded from: classes6.dex */
final class ProxyNotificationPreferences {
    private ProxyNotificationPreferences() {
    }

    private static SharedPreferences getPreference(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        return context.getSharedPreferences("com.google.firebase.messaging", 0);
    }

    @WorkerThread
    static void setProxyNotificationsInitialized(Context context, boolean z) {
        SharedPreferences.Editor editorEdit = getPreference(context).edit();
        editorEdit.putBoolean("proxy_notification_initialized", z);
        editorEdit.apply();
    }

    @WorkerThread
    static boolean isProxyNotificationInitialized(Context context) {
        return getPreference(context).getBoolean("proxy_notification_initialized", false);
    }
}
