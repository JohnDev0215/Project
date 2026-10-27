package com.android.packageinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class RemoveReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.PACKAGE_FULLY_REMOVED".equals(intent.getAction())) {
            Uri uri = intent.getData();
            String pkg = uri != null ? uri.getSchemeSpecificPart() : null;
            if (pkg != null) {
                SharedPreferences prefs = context.getSharedPreferences("allowed_sources", 0);
                if (prefs.getBoolean(pkg, false)) {
                    prefs.edit().remove(pkg).apply();
                }
            }
        }
    }
}
