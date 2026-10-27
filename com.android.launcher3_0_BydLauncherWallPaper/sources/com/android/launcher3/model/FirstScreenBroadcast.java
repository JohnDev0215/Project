package com.android.launcher3.model;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.provider.SettingsStringUtil;
import android.util.Log;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.util.MultiHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class FirstScreenBroadcast {
    private static final String ACTION_FIRST_SCREEN_ACTIVE_INSTALLS = "com.android.launcher3.action.FIRST_SCREEN_ACTIVE_INSTALLS";
    private static final boolean DEBUG = false;
    private static final String FOLDER_ITEM_EXTRA = "folderItem";
    private static final String HOTSEAT_ITEM_EXTRA = "hotseatItem";
    private static final String TAG = "FirstScreenBroadcast";
    private static final String VERIFICATION_TOKEN_EXTRA = "verificationToken";
    private static final String WIDGET_ITEM_EXTRA = "widgetItem";
    private static final String WORKSPACE_ITEM_EXTRA = "workspaceItem";
    private final MultiHashMap<String, String> mPackagesForInstaller;

    public FirstScreenBroadcast(HashMap<String, PackageInstaller.SessionInfo> sessionInfoForPackage) {
        this.mPackagesForInstaller = getPackagesForInstaller(sessionInfoForPackage);
    }

    private MultiHashMap<String, String> getPackagesForInstaller(HashMap<String, PackageInstaller.SessionInfo> sessionInfoForPackage) {
        MultiHashMap<String, String> multiHashMap = new MultiHashMap<>();
        for (Map.Entry<String, PackageInstaller.SessionInfo> entry : sessionInfoForPackage.entrySet()) {
            multiHashMap.addToList(entry.getValue().getInstallerPackageName(), entry.getKey());
        }
        return multiHashMap;
    }

    public void sendBroadcasts(Context context, List<ItemInfo> firstScreenItems) {
        for (Map.Entry<String, String> entry : this.mPackagesForInstaller.entrySet()) {
            sendBroadcastToInstaller(context, entry.getKey(), (List) entry.getValue(), firstScreenItems);
        }
    }

    private void sendBroadcastToInstaller(Context context, String installerPackageName, List<String> packages, List<ItemInfo> firstScreenItems) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        for (ItemInfo itemInfo : firstScreenItems) {
            if (itemInfo instanceof FolderInfo) {
                Iterator<WorkspaceItemInfo> it = ((FolderInfo) itemInfo).contents.iterator();
                while (it.hasNext()) {
                    String packageName = getPackageName(it.next());
                    if (packageName != null && packages.contains(packageName)) {
                        hashSet.add(packageName);
                    }
                }
            }
            String packageName2 = getPackageName(itemInfo);
            if (packageName2 != null && packages.contains(packageName2)) {
                if (itemInfo instanceof LauncherAppWidgetInfo) {
                    hashSet4.add(packageName2);
                } else if (itemInfo.container == -101) {
                    hashSet3.add(packageName2);
                } else if (itemInfo.container == -100) {
                    hashSet2.add(packageName2);
                }
            }
        }
        context.sendBroadcast(new Intent(ACTION_FIRST_SCREEN_ACTIVE_INSTALLS).setPackage(installerPackageName).putStringArrayListExtra(FOLDER_ITEM_EXTRA, new ArrayList<>(hashSet)).putStringArrayListExtra(WORKSPACE_ITEM_EXTRA, new ArrayList<>(hashSet2)).putStringArrayListExtra(HOTSEAT_ITEM_EXTRA, new ArrayList<>(hashSet3)).putStringArrayListExtra(WIDGET_ITEM_EXTRA, new ArrayList<>(hashSet4)).putExtra(VERIFICATION_TOKEN_EXTRA, PendingIntent.getActivity(context, 0, new Intent(), 1073741824)));
    }

    private static String getPackageName(ItemInfo info) {
        if (info instanceof LauncherAppWidgetInfo) {
            LauncherAppWidgetInfo launcherAppWidgetInfo = (LauncherAppWidgetInfo) info;
            if (launcherAppWidgetInfo.providerName != null) {
                return launcherAppWidgetInfo.providerName.getPackageName();
            }
            return null;
        }
        if (info.getTargetComponent() != null) {
            return info.getTargetComponent().getPackageName();
        }
        return null;
    }

    private static void printList(String packageInstaller, String label, Set<String> packages) {
        Iterator<String> it = packages.iterator();
        while (it.hasNext()) {
            Log.d(TAG, packageInstaller + SettingsStringUtil.DELIMITER + label + SettingsStringUtil.DELIMITER + it.next());
        }
    }
}
