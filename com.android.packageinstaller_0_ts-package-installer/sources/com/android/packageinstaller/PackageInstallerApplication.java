package com.android.packageinstaller;

import android.app.Application;
import android.content.pm.PackageItemInfo;

/* JADX INFO: loaded from: classes2.dex */
public class PackageInstallerApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        PackageItemInfo.forceSafeLabels();
    }
}
