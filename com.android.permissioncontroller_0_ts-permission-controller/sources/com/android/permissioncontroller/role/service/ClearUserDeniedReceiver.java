package com.android.permissioncontroller.role.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.permissioncontroller.role.model.UserDeniedManager;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class ClearUserDeniedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!Objects.equals(action, "android.intent.action.PACKAGE_DATA_CLEARED") && !Objects.equals(action, "android.intent.action.PACKAGE_FULLY_REMOVED")) {
            return;
        }
        String packageName = intent.getData().getSchemeSpecificPart();
        UserDeniedManager.getInstance(context).clearPackageDenied(packageName);
    }
}
