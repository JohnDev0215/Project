package com.android.launcher3.icons.cache;

import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.SparseBooleanArray;
import com.android.launcher3.icons.cache.BaseIconCache;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/* JADX INFO: loaded from: classes4.dex */
public class IconCacheUpdateHandler {
    private static final Object ICON_UPDATE_TOKEN = new Object();
    private static final boolean MODE_CLEAR_VALID_ITEMS = false;
    private static final boolean MODE_SET_INVALID_ITEMS = true;
    private static final String TAG = "IconCacheUpdateHandler";
    private final BaseIconCache mIconCache;
    private final HashMap<UserHandle, Set<String>> mPackagesToIgnore = new HashMap<>();
    private final SparseBooleanArray mItemsToDelete = new SparseBooleanArray();
    private boolean mFilterMode = true;
    private final HashMap<String, PackageInfo> mPkgInfoMap = new HashMap<>();

    public interface OnUpdateCallback {
        void onPackageIconsUpdated(HashSet<String> updatedPackages, UserHandle user);
    }

    IconCacheUpdateHandler(BaseIconCache cache) {
        this.mIconCache = cache;
        cache.mWorkerHandler.removeCallbacksAndMessages(ICON_UPDATE_TOKEN);
        createPackageInfoMap();
    }

    public void setPackagesToIgnore(UserHandle userHandle, Set<String> packages) {
        this.mPackagesToIgnore.put(userHandle, packages);
    }

    private void createPackageInfoMap() {
        for (PackageInfo packageInfo : this.mIconCache.mPackageManager.getInstalledPackages(8192)) {
            this.mPkgInfoMap.put(packageInfo.packageName, packageInfo);
        }
    }

    public <T> void updateIcons(List<T> apps, CachingLogic<T> cachingLogic, OnUpdateCallback onUpdateCallback) throws Throwable {
        HashMap map = new HashMap();
        int size = apps.size();
        for (int i = 0; i < size; i++) {
            T t = apps.get(i);
            UserHandle user = cachingLogic.getUser(t);
            HashMap map2 = (HashMap) map.get(user);
            if (map2 == null) {
                map2 = new HashMap();
                map.put(user, map2);
            }
            map2.put(cachingLogic.getComponent(t), t);
        }
        for (Map.Entry entry : map.entrySet()) {
            updateIconsPerUser((UserHandle) entry.getKey(), (HashMap) entry.getValue(), cachingLogic, onUpdateCallback);
        }
        this.mFilterMode = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <T> void updateIconsPerUser(android.os.UserHandle r26, java.util.HashMap<android.content.ComponentName, T> r27, com.android.launcher3.icons.cache.CachingLogic<T> r28, com.android.launcher3.icons.cache.IconCacheUpdateHandler.OnUpdateCallback r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.cache.IconCacheUpdateHandler.updateIconsPerUser(android.os.UserHandle, java.util.HashMap, com.android.launcher3.icons.cache.CachingLogic, com.android.launcher3.icons.cache.IconCacheUpdateHandler$OnUpdateCallback):void");
    }

    public void finish() {
        StringBuilder sbAppend = new StringBuilder().append(BaseIconCache.IconDB.COLUMN_ROWID).append(" IN (");
        int size = this.mItemsToDelete.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mItemsToDelete.valueAt(i2)) {
                if (i > 0) {
                    sbAppend.append(", ");
                }
                sbAppend.append(this.mItemsToDelete.keyAt(i2));
                i++;
            }
        }
        sbAppend.append(')');
        if (i > 0) {
            this.mIconCache.mIconDb.delete(sbAppend.toString(), null);
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
                T tPop = this.mAppsToUpdate.pop();
                String packageName = this.mCachingLogic.getComponent(tPop).getPackageName();
                IconCacheUpdateHandler.this.mIconCache.addIconToDBAndMemCache(tPop, this.mCachingLogic, (PackageInfo) IconCacheUpdateHandler.this.mPkgInfoMap.get(packageName), this.mUserSerial, true);
                this.mUpdatedPackages.add(packageName);
                if (this.mAppsToUpdate.isEmpty() && !this.mUpdatedPackages.isEmpty()) {
                    this.mOnUpdateCallback.onPackageIconsUpdated(this.mUpdatedPackages, this.mUserHandle);
                }
                scheduleNext();
                return;
            }
            if (this.mAppsToAdd.isEmpty()) {
                return;
            }
            T tPop2 = this.mAppsToAdd.pop();
            PackageInfo packageInfo = (PackageInfo) IconCacheUpdateHandler.this.mPkgInfoMap.get(this.mCachingLogic.getComponent(tPop2).getPackageName());
            if (packageInfo != null) {
                IconCacheUpdateHandler.this.mIconCache.addIconToDBAndMemCache(tPop2, this.mCachingLogic, packageInfo, this.mUserSerial, false);
            }
            if (this.mAppsToAdd.isEmpty()) {
                return;
            }
            scheduleNext();
        }

        public void scheduleNext() {
            IconCacheUpdateHandler.this.mIconCache.mWorkerHandler.postAtTime(this, IconCacheUpdateHandler.ICON_UPDATE_TOKEN, SystemClock.uptimeMillis() + 1);
        }
    }
}
