package com.android.launcher3.compat;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInstaller;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class PackageInstallerCompat {
    public static final int STATUS_FAILED = 2;
    public static final int STATUS_INSTALLED = 0;
    public static final int STATUS_INSTALLING = 1;
    private static PackageInstallerCompat sInstance;
    private static final Object sInstanceLock = new Object();

    public abstract List<PackageInstaller.SessionInfo> getAllVerifiedSessions();

    public abstract void onStop();

    public abstract HashMap<String, PackageInstaller.SessionInfo> updateAndGetActiveSessionCache();

    public static PackageInstallerCompat getInstance(Context context) {
        PackageInstallerCompat packageInstallerCompat;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new PackageInstallerCompatVL(context);
            }
            packageInstallerCompat = sInstance;
        }
        return packageInstallerCompat;
    }

    public static final class PackageInstallInfo {
        public final ComponentName componentName;
        public final String packageName;
        public final int progress;
        public final int state;

        private PackageInstallInfo(PackageInstaller.SessionInfo info) {
            this.state = 1;
            String appPackageName = info.getAppPackageName();
            this.packageName = appPackageName;
            this.componentName = new ComponentName(appPackageName, "");
            this.progress = (int) (info.getProgress() * 100.0f);
        }

        public PackageInstallInfo(String packageName, int state, int progress) {
            this.state = state;
            this.packageName = packageName;
            this.componentName = new ComponentName(packageName, "");
            this.progress = progress;
        }

        public static PackageInstallInfo fromInstallingState(PackageInstaller.SessionInfo info) {
            return new PackageInstallInfo(info);
        }

        public static PackageInstallInfo fromState(int state, String packageName) {
            return new PackageInstallInfo(packageName, state, 0);
        }
    }
}
