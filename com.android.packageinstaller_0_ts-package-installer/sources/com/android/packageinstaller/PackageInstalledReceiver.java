package com.android.packageinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class PackageInstalledReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = PackageInstalledReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Uri packageUri;
        if (Settings.Global.getInt(context.getContentResolver(), "show_new_app_installed_notification_enabled", 0) == 0) {
            return;
        }
        String action = intent.getAction();
        if (!"android.intent.action.PACKAGE_ADDED".equals(action) || (packageUri = intent.getData()) == null) {
            return;
        }
        String packageName = packageUri.getSchemeSpecificPart();
        if (packageName == null) {
            Log.e(TAG, "No package name");
        } else {
            if (intent.getBooleanExtra("android.intent.extra.REPLACING", DEBUG)) {
                return;
            }
            String installer = context.getPackageManager().getInstallerPackageName(packageName);
            new PackageInstalledNotificationUtils(context, installer, packageName).postAppInstalledNotification();
        }
    }
}
