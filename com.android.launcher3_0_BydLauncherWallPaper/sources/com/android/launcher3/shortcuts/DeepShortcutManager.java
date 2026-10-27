package com.android.launcher3.shortcuts;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.WorkspaceItemInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class DeepShortcutManager {
    private static final int FLAG_GET_ALL = 11;
    private static final String TAG = "DeepShortcutManager";
    private static DeepShortcutManager sInstance;
    private static final Object sInstanceLock = new Object();
    private final LauncherApps mLauncherApps;
    private boolean mWasLastCallSuccess;

    public static DeepShortcutManager getInstance(Context context) {
        DeepShortcutManager deepShortcutManager;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new DeepShortcutManager(context.getApplicationContext());
            }
            deepShortcutManager = sInstance;
        }
        return deepShortcutManager;
    }

    private DeepShortcutManager(Context context) {
        this.mLauncherApps = (LauncherApps) context.getSystemService(Context.LAUNCHER_APPS_SERVICE);
    }

    public static boolean supportsShortcuts(ItemInfo info) {
        return (info.itemType != 0 || info.isDisabled() || ((info instanceof WorkspaceItemInfo) && ((WorkspaceItemInfo) info).hasPromiseIconUi())) ? false : true;
    }

    public boolean wasLastCallSuccess() {
        return this.mWasLastCallSuccess;
    }

    public List<ShortcutInfo> queryForFullDetails(String packageName, List<String> shortcutIds, UserHandle user) {
        return query(11, packageName, null, shortcutIds, user);
    }

    public List<ShortcutInfo> queryForShortcutsContainer(ComponentName activity, UserHandle user) {
        return query(9, activity.getPackageName(), activity, null, user);
    }

    public void unpinShortcut(final ShortcutKey key) {
        String packageName = key.componentName.getPackageName();
        String id = key.getId();
        UserHandle userHandle = key.user;
        List<String> listExtractIds = extractIds(queryForPinnedShortcuts(packageName, userHandle));
        listExtractIds.remove(id);
        try {
            this.mLauncherApps.pinShortcuts(packageName, listExtractIds, userHandle);
            this.mWasLastCallSuccess = true;
        } catch (IllegalStateException | SecurityException e) {
            Log.w(TAG, "Failed to unpin shortcut", e);
            this.mWasLastCallSuccess = false;
        }
    }

    public void pinShortcut(final ShortcutKey key) {
        String packageName = key.componentName.getPackageName();
        String id = key.getId();
        UserHandle userHandle = key.user;
        List<String> listExtractIds = extractIds(queryForPinnedShortcuts(packageName, userHandle));
        listExtractIds.add(id);
        try {
            this.mLauncherApps.pinShortcuts(packageName, listExtractIds, userHandle);
            this.mWasLastCallSuccess = true;
        } catch (IllegalStateException | SecurityException e) {
            Log.w(TAG, "Failed to pin shortcut", e);
            this.mWasLastCallSuccess = false;
        }
    }

    public void startShortcut(String packageName, String id, Rect sourceBounds, Bundle startActivityOptions, UserHandle user) {
        try {
            this.mLauncherApps.startShortcut(packageName, id, sourceBounds, startActivityOptions, user);
            this.mWasLastCallSuccess = true;
        } catch (IllegalStateException | SecurityException e) {
            Log.e(TAG, "Failed to start shortcut", e);
            this.mWasLastCallSuccess = false;
        }
    }

    public Drawable getShortcutIconDrawable(ShortcutInfo shortcutInfo, int density) {
        try {
            Drawable shortcutIconDrawable = this.mLauncherApps.getShortcutIconDrawable(shortcutInfo, density);
            this.mWasLastCallSuccess = true;
            return shortcutIconDrawable;
        } catch (IllegalStateException | SecurityException e) {
            Log.e(TAG, "Failed to get shortcut icon", e);
            this.mWasLastCallSuccess = false;
            return null;
        }
    }

    public List<ShortcutInfo> queryForPinnedShortcuts(String packageName, UserHandle user) {
        return queryForPinnedShortcuts(packageName, null, user);
    }

    public List<ShortcutInfo> queryForPinnedShortcuts(String packageName, List<String> shortcutIds, UserHandle user) {
        return query(2, packageName, null, shortcutIds, user);
    }

    public List<ShortcutInfo> queryForAllShortcuts(UserHandle user) {
        return query(11, null, null, null, user);
    }

    private List<String> extractIds(List<ShortcutInfo> shortcuts) {
        ArrayList arrayList = new ArrayList(shortcuts.size());
        Iterator<ShortcutInfo> it = shortcuts.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getId());
        }
        return arrayList;
    }

    private List<ShortcutInfo> query(int flags, String packageName, ComponentName activity, List<String> shortcutIds, UserHandle user) {
        LauncherApps.ShortcutQuery shortcutQuery = new LauncherApps.ShortcutQuery();
        shortcutQuery.setQueryFlags(flags);
        if (packageName != null) {
            shortcutQuery.setPackage(packageName);
            shortcutQuery.setActivity(activity);
            shortcutQuery.setShortcutIds(shortcutIds);
        }
        List<ShortcutInfo> shortcuts = null;
        try {
            shortcuts = this.mLauncherApps.getShortcuts(shortcutQuery, user);
            this.mWasLastCallSuccess = true;
        } catch (IllegalStateException | SecurityException e) {
            Log.e(TAG, "Failed to query for shortcuts", e);
            this.mWasLastCallSuccess = false;
        }
        return shortcuts == null ? Collections.EMPTY_LIST : shortcuts;
    }

    public boolean hasHostPermission() {
        try {
            return this.mLauncherApps.hasShortcutHostPermission();
        } catch (IllegalStateException | SecurityException e) {
            Log.e(TAG, "Failed to make shortcut manager call", e);
            return false;
        }
    }
}
