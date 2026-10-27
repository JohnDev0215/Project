package com.android.server.telecom.components;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.telecom.TelecomManager;

/* JADX INFO: loaded from: classes.dex */
public class AppUninstallBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Uri data;
        if (!"android.intent.action.PACKAGE_FULLY_REMOVED".equals(intent.getAction()) || (data = intent.getData()) == null) {
            return;
        }
        String schemeSpecificPart = data.getSchemeSpecificPart();
        handlePackageRemoved(context, schemeSpecificPart);
        handleUninstallOfCallScreeningService(context, schemeSpecificPart);
    }

    private void handlePackageRemoved(Context context, String str) {
        TelecomManager telecomManagerFrom = TelecomManager.from(context);
        if (telecomManagerFrom != null) {
            telecomManagerFrom.clearAccountsForPackage(str);
        }
    }

    private void handleUninstallOfCallScreeningService(Context context, String str) {
        String stringForUser = Settings.Secure.getStringForUser(context.getContentResolver(), "call_screening_default_component", -2);
        ComponentName componentNameUnflattenFromString = stringForUser != null ? ComponentName.unflattenFromString(stringForUser) : null;
        if (componentNameUnflattenFromString == null || !componentNameUnflattenFromString.getPackageName().equals(str)) {
            return;
        }
        Settings.Secure.putStringForUser(context.getContentResolver(), "call_screening_default_component", null, -2);
    }
}
