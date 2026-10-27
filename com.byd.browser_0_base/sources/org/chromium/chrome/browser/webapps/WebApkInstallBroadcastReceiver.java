package org.chromium.chrome.browser.webapps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class WebApkInstallBroadcastReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    public WebApkInstallBroadcastReceiver() {
        new WebApkInstallCoordinatorBridge();
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        WebApkInstallService.cancelNotification(intent.getStringExtra("WebApkInstallNotification.notification_id"));
        if ("WebApkInstallNotification.open".equals(intent.getAction())) {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(intent.getStringExtra("WebApkInstallNotification.start_url")));
            intent2.setPackage(context.getPackageName());
            intent2.setFlags(268435456);
            intent2.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
            context.startActivity(intent2);
        }
    }
}
