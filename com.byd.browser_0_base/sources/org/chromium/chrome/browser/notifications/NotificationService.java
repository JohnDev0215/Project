package org.chromium.chrome.browser.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.BundleUtils;
import org.chromium.chrome.browser.base.SplitCompatIntentService;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationService extends SplitCompatIntentService {

    public static class Receiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            ((BroadcastReceiver) BundleUtils.e(context, "org.chromium.chrome.browser.notifications.NotificationServiceImpl$Receiver")).onReceive(context, intent);
        }
    }

    public NotificationService() {
        super("org.chromium.chrome.browser.notifications.NotificationServiceImpl", "NotificationService");
    }
}
