package com.android.launcher3.compat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.ShortcutInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.UserHandle;
import com.android.launcher3.Utilities;
import com.android.launcher3.util.PackageUserKey;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LauncherAppsCompat {
    private static LauncherAppsCompat sInstance;
    private static final Object sInstanceLock = new Object();

    public interface OnAppsChangedCallbackCompat {
        void onPackageAdded(String packageName, UserHandle user);

        void onPackageChanged(String packageName, UserHandle user);

        void onPackageRemoved(String packageName, UserHandle user);

        void onPackagesAvailable(String[] packageNames, UserHandle user, boolean replacing);

        void onPackagesSuspended(String[] packageNames, UserHandle user);

        void onPackagesUnavailable(String[] packageNames, UserHandle user, boolean replacing);

        void onPackagesUnsuspended(String[] packageNames, UserHandle user);

        void onShortcutsChanged(String packageName, List<ShortcutInfo> shortcuts, UserHandle user);
    }

    public abstract void addOnAppsChangedCallback(OnAppsChangedCallbackCompat listener);

    public abstract List<LauncherActivityInfo> getActivityList(String packageName, UserHandle user);

    public abstract List<PackageInstaller.SessionInfo> getAllPackageInstallerSessions();

    public abstract ApplicationInfo getApplicationInfo(String packageName, int flags, UserHandle user);

    public abstract List<ShortcutConfigActivityInfo> getCustomShortcutActivityList(PackageUserKey packageUser);

    public abstract boolean isActivityEnabledForProfile(ComponentName component, UserHandle user);

    public abstract boolean isPackageEnabledForProfile(String packageName, UserHandle user);

    public abstract void removeOnAppsChangedCallback(OnAppsChangedCallbackCompat listener);

    public abstract LauncherActivityInfo resolveActivity(Intent intent, UserHandle user);

    public abstract void showAppDetailsForProfile(ComponentName component, UserHandle user, Rect sourceBounds, Bundle opts);

    public abstract void startActivityForProfile(ComponentName component, UserHandle user, Rect sourceBounds, Bundle opts);

    protected LauncherAppsCompat() {
    }

    public static LauncherAppsCompat getInstance(Context context) {
        LauncherAppsCompat launcherAppsCompat;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                if (Utilities.ATLEAST_Q) {
                    sInstance = new LauncherAppsCompatVQ(context.getApplicationContext());
                } else if (Utilities.ATLEAST_OREO) {
                    sInstance = new LauncherAppsCompatVO(context.getApplicationContext());
                } else {
                    sInstance = new LauncherAppsCompatVL(context.getApplicationContext());
                }
            }
            launcherAppsCompat = sInstance;
        }
        return launcherAppsCompat;
    }
}
