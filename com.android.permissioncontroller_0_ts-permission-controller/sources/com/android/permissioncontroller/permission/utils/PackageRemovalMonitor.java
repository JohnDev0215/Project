package com.android.permissioncontroller.permission.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes8.dex */
public abstract class PackageRemovalMonitor extends BroadcastReceiver {
    private final Context mContext;
    private final String mPackageName;

    protected abstract void onPackageRemoved();

    public PackageRemovalMonitor(Context context, String packageName) {
        this.mContext = context;
        this.mPackageName = packageName;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction()) && this.mPackageName.equals(intent.getData().getSchemeSpecificPart())) {
            onPackageRemoved();
        }
    }

    public void register() {
        IntentFilter packageRemovedFilter = new IntentFilter();
        packageRemovedFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        packageRemovedFilter.addDataScheme("package");
        this.mContext.registerReceiver(this, packageRemovedFilter);
    }

    public void unregister() {
        this.mContext.unregisterReceiver(this);
    }
}
