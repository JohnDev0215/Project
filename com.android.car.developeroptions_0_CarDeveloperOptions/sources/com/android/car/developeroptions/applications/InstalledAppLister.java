package com.android.car.developeroptions.applications;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.UserManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class InstalledAppLister extends AppLister {
    public InstalledAppLister(PackageManager packageManager, UserManager userManager) {
        super(packageManager, userManager);
    }

    @Override // com.android.car.developeroptions.applications.AppLister
    protected boolean includeInCount(ApplicationInfo applicationInfo) {
        return InstalledAppCounter.includeInCount(1, this.mPm, applicationInfo);
    }
}
