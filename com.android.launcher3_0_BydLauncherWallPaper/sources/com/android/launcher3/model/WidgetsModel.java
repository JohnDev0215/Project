package com.android.launcher3.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.UserHandle;
import com.android.launcher3.AppFilter;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.AlphabeticIndexCompat;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.ShortcutConfigActivityInfo;
import com.android.launcher3.icons.ComponentWithLabel;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.MultiHashMap;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.widget.WidgetItemComparator;
import com.android.launcher3.widget.WidgetListRowEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetsModel {
    private static final boolean DEBUG = false;
    private static final String TAG = "WidgetsModel";
    private AppFilter mAppFilter;
    private final MultiHashMap<PackageItemInfo, WidgetItem> mWidgetsList = new MultiHashMap<>();

    public synchronized ArrayList<WidgetListRowEntry> getWidgetsList(Context context) {
        ArrayList<WidgetListRowEntry> arrayList;
        arrayList = new ArrayList<>();
        AlphabeticIndexCompat alphabeticIndexCompat = new AlphabeticIndexCompat(context);
        WidgetItemComparator widgetItemComparator = new WidgetItemComparator();
        for (Map.Entry<PackageItemInfo, WidgetItem> entry : this.mWidgetsList.entrySet()) {
            WidgetListRowEntry widgetListRowEntry = new WidgetListRowEntry(entry.getKey(), (ArrayList) entry.getValue());
            widgetListRowEntry.titleSectionName = widgetListRowEntry.pkgItem.title == null ? "" : alphabeticIndexCompat.computeSectionName(widgetListRowEntry.pkgItem.title);
            Collections.sort(widgetListRowEntry.widgets, widgetItemComparator);
            arrayList.add(widgetListRowEntry);
        }
        return arrayList;
    }

    public List<ComponentWithLabel> update(LauncherAppState app, PackageUserKey packageUser) {
        Preconditions.assertWorkerThread();
        Context context = app.getContext();
        ArrayList<? extends ComponentKey> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        try {
            app.getInvariantDeviceProfile();
            PackageManager packageManager = app.getContext().getPackageManager();
            for (ShortcutConfigActivityInfo shortcutConfigActivityInfo : LauncherAppsCompat.getInstance(context).getCustomShortcutActivityList(packageUser)) {
                arrayList.add(new WidgetItem(shortcutConfigActivityInfo, app.getIconCache(), packageManager));
                arrayList2.add(shortcutConfigActivityInfo);
            }
            setWidgetsAndShortcuts(arrayList, app, packageUser);
        } catch (Exception e) {
            if (!Utilities.isBinderSizeError(e)) {
                throw e;
            }
        }
        app.getWidgetCache().removeObsoletePreviews(arrayList, packageUser);
        return arrayList2;
    }

    private synchronized void setWidgetsAndShortcuts(ArrayList<WidgetItem> rawWidgetsShortcuts, LauncherAppState app, PackageUserKey packageUser) {
        HashMap map = new HashMap();
        if (packageUser == null) {
            this.mWidgetsList.clear();
        } else {
            PackageItemInfo packageItemInfo = null;
            Iterator<PackageItemInfo> it = this.mWidgetsList.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PackageItemInfo next = it.next();
                if (next.packageName.equals(packageUser.mPackageName)) {
                    packageItemInfo = next;
                    break;
                }
            }
            if (packageItemInfo != null) {
                map.put(packageItemInfo.packageName, packageItemInfo);
                Iterator it2 = ((ArrayList) this.mWidgetsList.get(packageItemInfo)).iterator();
                while (it2.hasNext()) {
                    WidgetItem widgetItem = (WidgetItem) it2.next();
                    if (widgetItem.componentName.getPackageName().equals(packageUser.mPackageName) && widgetItem.user.equals(packageUser.mUser)) {
                        it2.remove();
                    }
                }
            }
        }
        InvariantDeviceProfile invariantDeviceProfile = app.getInvariantDeviceProfile();
        UserHandle userHandleMyUserHandle = Process.myUserHandle();
        for (WidgetItem widgetItem2 : rawWidgetsShortcuts) {
            if (widgetItem2.widgetInfo != null) {
                if ((widgetItem2.widgetInfo.getWidgetFeatures() & 2) == 0) {
                    int iMin = Math.min(widgetItem2.widgetInfo.spanX, widgetItem2.widgetInfo.minSpanX);
                    int iMin2 = Math.min(widgetItem2.widgetInfo.spanY, widgetItem2.widgetInfo.minSpanY);
                    if (iMin <= invariantDeviceProfile.numColumns && iMin2 <= invariantDeviceProfile.numRows) {
                    }
                }
            }
            if (this.mAppFilter == null) {
                this.mAppFilter = AppFilter.newInstance(app.getContext());
            }
            if (this.mAppFilter.shouldShowApp(widgetItem2.componentName)) {
                String packageName = widgetItem2.componentName.getPackageName();
                PackageItemInfo packageItemInfo2 = (PackageItemInfo) map.get(packageName);
                if (packageItemInfo2 == null) {
                    packageItemInfo2 = new PackageItemInfo(packageName);
                    packageItemInfo2.user = widgetItem2.user;
                    map.put(packageName, packageItemInfo2);
                } else if (!userHandleMyUserHandle.equals(packageItemInfo2.user)) {
                    packageItemInfo2.user = widgetItem2.user;
                }
                this.mWidgetsList.addToList(packageItemInfo2, widgetItem2);
            }
        }
        IconCache iconCache = app.getIconCache();
        Iterator it3 = map.values().iterator();
        while (it3.hasNext()) {
            iconCache.getTitleAndIconForApp((PackageItemInfo) it3.next(), true);
        }
    }

    public void onPackageIconsUpdated(Set<String> packageNames, UserHandle user, LauncherAppState app) {
        for (Map.Entry<PackageItemInfo, WidgetItem> entry : this.mWidgetsList.entrySet()) {
            if (packageNames.contains(entry.getKey().packageName)) {
                ArrayList arrayList = (ArrayList) entry.getValue();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    WidgetItem widgetItem = (WidgetItem) arrayList.get(i);
                    if (widgetItem.user.equals(user)) {
                        if (widgetItem.activityInfo != null) {
                            arrayList.set(i, new WidgetItem(widgetItem.activityInfo, app.getIconCache(), app.getContext().getPackageManager()));
                        } else {
                            arrayList.set(i, new WidgetItem(widgetItem.widgetInfo, app.getInvariantDeviceProfile(), app.getIconCache()));
                        }
                    }
                }
            }
        }
    }
}
