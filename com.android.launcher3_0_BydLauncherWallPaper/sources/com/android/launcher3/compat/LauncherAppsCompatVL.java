package com.android.launcher3.compat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.ArrayMap;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.ShortcutConfigActivityInfo;
import com.android.launcher3.util.PackageUserKey;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppsCompatVL extends LauncherAppsCompat {
    private final ArrayMap<LauncherAppsCompat.OnAppsChangedCallbackCompat, WrappedCallback> mCallbacks = new ArrayMap<>();
    protected final Context mContext;
    protected final LauncherApps mLauncherApps;

    LauncherAppsCompatVL(Context context) {
        this.mContext = context;
        this.mLauncherApps = (LauncherApps) context.getSystemService(Context.LAUNCHER_APPS_SERVICE);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public List<LauncherActivityInfo> getActivityList(String packageName, UserHandle user) {
        return this.mLauncherApps.getActivityList(packageName, user);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public LauncherActivityInfo resolveActivity(Intent intent, UserHandle user) {
        return this.mLauncherApps.resolveActivity(intent, user);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public void startActivityForProfile(ComponentName component, UserHandle user, Rect sourceBounds, Bundle opts) {
        this.mLauncherApps.startMainActivity(component, user, sourceBounds, opts);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public ApplicationInfo getApplicationInfo(String packageName, int flags, UserHandle user) {
        boolean zEquals = Process.myUserHandle().equals(user);
        if (!zEquals && flags == 0) {
            List<LauncherActivityInfo> activityList = this.mLauncherApps.getActivityList(packageName, user);
            if (activityList.size() > 0) {
                return activityList.get(0).getApplicationInfo();
            }
            return null;
        }
        try {
            ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(packageName, flags);
            if (!zEquals || (applicationInfo.flags & 8388608) != 0) {
                if (applicationInfo.enabled) {
                    return applicationInfo;
                }
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public void showAppDetailsForProfile(ComponentName component, UserHandle user, Rect sourceBounds, Bundle opts) {
        this.mLauncherApps.startAppDetailsActivity(component, user, sourceBounds, opts);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public void addOnAppsChangedCallback(LauncherAppsCompat.OnAppsChangedCallbackCompat callback) {
        WrappedCallback wrappedCallback = new WrappedCallback(callback);
        synchronized (this.mCallbacks) {
            this.mCallbacks.put(callback, wrappedCallback);
        }
        this.mLauncherApps.registerCallback(wrappedCallback);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public void removeOnAppsChangedCallback(LauncherAppsCompat.OnAppsChangedCallbackCompat callback) {
        WrappedCallback wrappedCallbackRemove;
        synchronized (this.mCallbacks) {
            wrappedCallbackRemove = this.mCallbacks.remove(callback);
        }
        if (wrappedCallbackRemove != null) {
            this.mLauncherApps.unregisterCallback(wrappedCallbackRemove);
        }
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public boolean isPackageEnabledForProfile(String packageName, UserHandle user) {
        return this.mLauncherApps.isPackageEnabled(packageName, user);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public boolean isActivityEnabledForProfile(ComponentName component, UserHandle user) {
        return this.mLauncherApps.isActivityEnabled(component, user);
    }

    private static class WrappedCallback extends LauncherApps.Callback {
        private final LauncherAppsCompat.OnAppsChangedCallbackCompat mCallback;

        public WrappedCallback(LauncherAppsCompat.OnAppsChangedCallbackCompat callback) {
            this.mCallback = callback;
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageRemoved(String packageName, UserHandle user) {
            this.mCallback.onPackageRemoved(packageName, user);
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageAdded(String packageName, UserHandle user) {
            this.mCallback.onPackageAdded(packageName, user);
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackageChanged(String packageName, UserHandle user) {
            this.mCallback.onPackageChanged(packageName, user);
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackagesAvailable(String[] packageNames, UserHandle user, boolean replacing) {
            this.mCallback.onPackagesAvailable(packageNames, user, replacing);
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackagesUnavailable(String[] packageNames, UserHandle user, boolean replacing) {
            this.mCallback.onPackagesUnavailable(packageNames, user, replacing);
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackagesSuspended(String[] packageNames, UserHandle user) {
            this.mCallback.onPackagesSuspended(packageNames, user);
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onPackagesUnsuspended(String[] packageNames, UserHandle user) {
            this.mCallback.onPackagesUnsuspended(packageNames, user);
        }

        @Override // android.content.pm.LauncherApps.Callback
        public void onShortcutsChanged(String packageName, List<ShortcutInfo> shortcuts, UserHandle user) {
            this.mCallback.onShortcutsChanged(packageName, shortcuts, user);
        }
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public List<ShortcutConfigActivityInfo> getCustomShortcutActivityList(PackageUserKey packageUser) {
        ArrayList arrayList = new ArrayList();
        if (packageUser != null && !packageUser.mUser.equals(Process.myUserHandle())) {
            return arrayList;
        }
        for (ResolveInfo resolveInfo : this.mContext.getPackageManager().queryIntentActivities(new Intent(Intent.ACTION_CREATE_SHORTCUT), 0)) {
            if (packageUser == null || packageUser.mPackageName.equals(resolveInfo.activityInfo.packageName)) {
                arrayList.add(new ShortcutConfigActivityInfo.ShortcutConfigActivityInfoVL(resolveInfo.activityInfo));
            }
        }
        return arrayList;
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat
    public List<PackageInstaller.SessionInfo> getAllPackageInstallerSessions() {
        return this.mContext.getPackageManager().getPackageInstaller().getAllSessions();
    }
}
