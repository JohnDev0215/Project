package com.android.launcher3.compat;

import android.content.Context;
import android.content.pm.PackageInstaller;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppsCompatVQ extends LauncherAppsCompatVO {
    LauncherAppsCompatVQ(Context context) {
        super(context);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompatVL, com.android.launcher3.compat.LauncherAppsCompat
    public List<PackageInstaller.SessionInfo> getAllPackageInstallerSessions() {
        return this.mLauncherApps.getAllPackageInstallerSessions();
    }
}
