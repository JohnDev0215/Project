package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.util.FlagOp;
import com.android.launcher3.util.ItemInfoMatcher;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsList {
    public static final int DEFAULT_APPLICATIONS_NUMBER = 42;
    private static final String TAG = "AllAppsList";
    private AppFilter mAppFilter;
    private IconCache mIconCache;
    public final ArrayList<AppInfo> data = new ArrayList<>(42);
    public ArrayList<AppInfo> added = new ArrayList<>(42);
    public ArrayList<AppInfo> removed = new ArrayList<>();
    public ArrayList<AppInfo> modified = new ArrayList<>();

    public AllAppsList(IconCache iconCache, AppFilter appFilter) {
        this.mIconCache = iconCache;
        this.mAppFilter = appFilter;
    }

    public void add(AppInfo info, LauncherActivityInfo activityInfo) {
        if (this.mAppFilter.shouldShowApp(info.componentName) && findAppInfo(info.componentName, info.user) == null) {
            this.mIconCache.getTitleAndIcon(info, activityInfo, true);
            this.data.add(info);
            this.added.add(info);
        }
    }

    public void addPromiseApp(Context context, PackageInstallerCompat.PackageInstallInfo installInfo) {
        if (LauncherAppsCompat.getInstance(context).getApplicationInfo(installInfo.packageName, 0, Process.myUserHandle()) == null) {
            PromiseAppInfo promiseAppInfo = new PromiseAppInfo(installInfo);
            this.mIconCache.getTitleAndIcon(promiseAppInfo, promiseAppInfo.usingLowResIcon());
            this.data.add(promiseAppInfo);
            this.added.add(promiseAppInfo);
        }
    }

    public void removePromiseApp(AppInfo appInfo) {
        this.data.remove(appInfo);
    }

    public void clear() {
        this.data.clear();
        this.added.clear();
        this.removed.clear();
        this.modified.clear();
    }

    public int size() {
        return this.data.size();
    }

    public AppInfo get(int index) {
        return this.data.get(index);
    }

    public void addPackage(Context context, String packageName, UserHandle user) {
        for (LauncherActivityInfo launcherActivityInfo : LauncherAppsCompat.getInstance(context).getActivityList(packageName, user)) {
            add(new AppInfo(context, launcherActivityInfo, user), launcherActivityInfo);
        }
    }

    public void removePackage(String packageName, UserHandle user) {
        ArrayList<AppInfo> arrayList = this.data;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            AppInfo appInfo = arrayList.get(size);
            if (appInfo.user.equals(user) && packageName.equals(appInfo.componentName.getPackageName())) {
                this.removed.add(appInfo);
                arrayList.remove(size);
            }
        }
    }

    public void updateDisabledFlags(ItemInfoMatcher matcher, FlagOp op) {
        ArrayList<AppInfo> arrayList = this.data;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            AppInfo appInfo = arrayList.get(size);
            if (matcher.matches(appInfo, appInfo.componentName)) {
                appInfo.runtimeStatusFlags = op.apply(appInfo.runtimeStatusFlags);
                this.modified.add(appInfo);
            }
        }
    }

    public void updateIconsAndLabels(HashSet<String> packages, UserHandle user, ArrayList<AppInfo> outUpdates) {
        for (AppInfo appInfo : this.data) {
            if (appInfo.user.equals(user) && packages.contains(appInfo.componentName.getPackageName())) {
                this.mIconCache.updateTitleAndIcon(appInfo);
                outUpdates.add(appInfo);
            }
        }
    }

    public void updatePackage(Context context, String packageName, UserHandle user) {
        List<LauncherActivityInfo> activityList = LauncherAppsCompat.getInstance(context).getActivityList(packageName, user);
        if (activityList.size() > 0) {
            for (int size = this.data.size() - 1; size >= 0; size--) {
                AppInfo appInfo = this.data.get(size);
                if (user.equals(appInfo.user) && packageName.equals(appInfo.componentName.getPackageName()) && !findActivity(activityList, appInfo.componentName)) {
                    Log.w(TAG, "Changing shortcut target due to app component name change.");
                    this.removed.add(appInfo);
                    this.data.remove(size);
                }
            }
            for (LauncherActivityInfo launcherActivityInfo : activityList) {
                AppInfo appInfoFindAppInfo = findAppInfo(launcherActivityInfo.getComponentName(), user);
                if (appInfoFindAppInfo == null) {
                    add(new AppInfo(context, launcherActivityInfo, user), launcherActivityInfo);
                } else {
                    this.mIconCache.getTitleAndIcon(appInfoFindAppInfo, launcherActivityInfo, true);
                    this.modified.add(appInfoFindAppInfo);
                }
            }
            return;
        }
        for (int size2 = this.data.size() - 1; size2 >= 0; size2--) {
            AppInfo appInfo2 = this.data.get(size2);
            if (user.equals(appInfo2.user) && packageName.equals(appInfo2.componentName.getPackageName())) {
                this.removed.add(appInfo2);
                this.mIconCache.remove(appInfo2.componentName, user);
                this.data.remove(size2);
            }
        }
    }

    private static boolean findActivity(List<LauncherActivityInfo> apps, ComponentName component) {
        Iterator<LauncherActivityInfo> it = apps.iterator();
        while (it.hasNext()) {
            if (it.next().getComponentName().equals(component)) {
                return true;
            }
        }
        return false;
    }

    private AppInfo findAppInfo(ComponentName componentName, UserHandle user) {
        for (AppInfo appInfo : this.data) {
            if (componentName.equals(appInfo.componentName) && user.equals(appInfo.user)) {
                return appInfo;
            }
        }
        return null;
    }
}
