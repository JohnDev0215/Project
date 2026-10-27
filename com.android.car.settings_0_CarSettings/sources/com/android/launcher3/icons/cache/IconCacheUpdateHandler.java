package com.android.launcher3.icons.cache;

import android.content.ComponentName;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.SparseBooleanArray;
import com.android.launcher3.icons.cache.BaseIconCache;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/* JADX INFO: loaded from: classes6.dex */
public class IconCacheUpdateHandler {
    private static final Object ICON_UPDATE_TOKEN = new Object();
    private static final boolean MODE_CLEAR_VALID_ITEMS = false;
    private static final boolean MODE_SET_INVALID_ITEMS = true;
    private static final String TAG = "IconCacheUpdateHandler";
    private final BaseIconCache mIconCache;
    private final ArrayMap<UserHandle, Set<String>> mPackagesToIgnore = new ArrayMap<>();
    private final SparseBooleanArray mItemsToDelete = new SparseBooleanArray();
    private boolean mFilterMode = true;
    private final HashMap<String, PackageInfo> mPkgInfoMap = new HashMap<>();

    public interface OnUpdateCallback {
        void onPackageIconsUpdated(HashSet<String> hashSet, UserHandle userHandle);
    }

    IconCacheUpdateHandler(BaseIconCache cache) {
        this.mIconCache = cache;
        this.mIconCache.mWorkerHandler.removeCallbacksAndMessages(ICON_UPDATE_TOKEN);
        createPackageInfoMap();
    }

    public void addPackagesToIgnore(UserHandle userHandle, String packageName) {
        Set<String> packages = this.mPackagesToIgnore.get(userHandle);
        if (packages == null) {
            packages = new HashSet();
            this.mPackagesToIgnore.put(userHandle, packages);
        }
        packages.add(packageName);
    }

    private void createPackageInfoMap() {
        PackageManager pm = this.mIconCache.mPackageManager;
        for (PackageInfo info : pm.getInstalledPackages(8192)) {
            this.mPkgInfoMap.put(info.packageName, info);
        }
    }

    public <T> void updateIcons(List<T> apps, CachingLogic<T> cachingLogic, OnUpdateCallback onUpdateCallback) throws Throwable {
        HashMap<UserHandle, HashMap<ComponentName, T>> userComponentMap = new HashMap<>();
        int count = apps.size();
        for (int i = 0; i < count; i++) {
            T app = apps.get(i);
            UserHandle userHandle = cachingLogic.getUser(app);
            HashMap<ComponentName, T> componentMap = userComponentMap.get(userHandle);
            if (componentMap == null) {
                componentMap = new HashMap<>();
                userComponentMap.put(userHandle, componentMap);
            }
            componentMap.put(cachingLogic.getComponent(app), app);
        }
        for (Map.Entry<UserHandle, HashMap<ComponentName, T>> entry : userComponentMap.entrySet()) {
            updateIconsPerUser(entry.getKey(), entry.getValue(), cachingLogic, onUpdateCallback);
        }
        this.mFilterMode = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:? A[Catch: SQLiteException -> 0x017f, SYNTHETIC, TRY_LEAVE, TryCatch #2 {SQLiteException -> 0x017f, blocks: (B:64:0x017e, B:63:0x017b, B:54:0x0169, B:59:0x0175), top: B:80:0x002b, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0175 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <T> void updateIconsPerUser(android.os.UserHandle r26, java.util.HashMap<android.content.ComponentName, T> r27, com.android.launcher3.icons.cache.CachingLogic<T> r28, com.android.launcher3.icons.cache.IconCacheUpdateHandler.OnUpdateCallback r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.cache.IconCacheUpdateHandler.updateIconsPerUser(android.os.UserHandle, java.util.HashMap, com.android.launcher3.icons.cache.CachingLogic, com.android.launcher3.icons.cache.IconCacheUpdateHandler$OnUpdateCallback):void");
    }

    public void finish() {
        int deleteCount = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(BaseIconCache.IconDB.COLUMN_ROWID);
        StringBuilder queryBuilder = sb.append(" IN (");
        int count = this.mItemsToDelete.size();
        for (int i = 0; i < count; i++) {
            if (this.mItemsToDelete.valueAt(i)) {
                if (deleteCount > 0) {
                    queryBuilder.append(", ");
                }
                queryBuilder.append(this.mItemsToDelete.keyAt(i));
                deleteCount++;
            }
        }
        queryBuilder.append(')');
        if (deleteCount > 0) {
            this.mIconCache.mIconDb.delete(queryBuilder.toString(), null);
        }
    }

    private class SerializedIconUpdateTask<T> implements Runnable {
        private final Stack<T> mAppsToAdd;
        private final Stack<T> mAppsToUpdate;
        private final CachingLogic<T> mCachingLogic;
        private final OnUpdateCallback mOnUpdateCallback;
        private final HashSet<String> mUpdatedPackages = new HashSet<>();
        private final UserHandle mUserHandle;
        private final long mUserSerial;

        SerializedIconUpdateTask(long userSerial, UserHandle userHandle, Stack<T> appsToAdd, Stack<T> appsToUpdate, CachingLogic<T> cachingLogic, OnUpdateCallback onUpdateCallback) {
            this.mUserHandle = userHandle;
            this.mUserSerial = userSerial;
            this.mAppsToAdd = appsToAdd;
            this.mAppsToUpdate = appsToUpdate;
            this.mCachingLogic = cachingLogic;
            this.mOnUpdateCallback = onUpdateCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mAppsToUpdate.isEmpty()) {
                T app = this.mAppsToUpdate.pop();
                String pkg = this.mCachingLogic.getComponent(app).getPackageName();
                IconCacheUpdateHandler.this.mIconCache.addIconToDBAndMemCache(app, this.mCachingLogic, (PackageInfo) IconCacheUpdateHandler.this.mPkgInfoMap.get(pkg), this.mUserSerial, true);
                this.mUpdatedPackages.add(pkg);
                if (this.mAppsToUpdate.isEmpty() && !this.mUpdatedPackages.isEmpty()) {
                    this.mOnUpdateCallback.onPackageIconsUpdated(this.mUpdatedPackages, this.mUserHandle);
                }
                scheduleNext();
                return;
            }
            if (!this.mAppsToAdd.isEmpty()) {
                T app2 = this.mAppsToAdd.pop();
                PackageInfo info = (PackageInfo) IconCacheUpdateHandler.this.mPkgInfoMap.get(this.mCachingLogic.getComponent(app2).getPackageName());
                if (info != null) {
                    IconCacheUpdateHandler.this.mIconCache.addIconToDBAndMemCache(app2, this.mCachingLogic, info, this.mUserSerial, false);
                }
                if (!this.mAppsToAdd.isEmpty()) {
                    scheduleNext();
                }
            }
        }

        public void scheduleNext() {
            IconCacheUpdateHandler.this.mIconCache.mWorkerHandler.postAtTime(this, IconCacheUpdateHandler.ICON_UPDATE_TOKEN, SystemClock.uptimeMillis() + 1);
        }
    }
}
