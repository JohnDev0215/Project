package com.android.launcher3.popup;

import android.content.ComponentName;
import android.service.notification.StatusBarNotification;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.dot.DotInfo;
import com.android.launcher3.model.WidgetItem;
import com.android.launcher3.notification.NotificationKeyData;
import com.android.launcher3.notification.NotificationListener;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.widget.WidgetListRowEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes4.dex */
public class PopupDataProvider implements NotificationListener.NotificationsChangedListener {
    private static final boolean LOGD = false;
    private static final String TAG = "PopupDataProvider";
    private final Launcher mLauncher;
    private HashMap<ComponentKey, Integer> mDeepShortcutMap = new HashMap<>();
    private Map<PackageUserKey, DotInfo> mPackageUserToDotInfos = new HashMap();
    private ArrayList<WidgetListRowEntry> mAllWidgets = new ArrayList<>();
    private PopupDataChangeListener mChangeListener = PopupDataChangeListener.INSTANCE;

    public interface PopupDataChangeListener {
        public static final PopupDataChangeListener INSTANCE = new PopupDataChangeListener() { // from class: com.android.launcher3.popup.PopupDataProvider.PopupDataChangeListener.1
        };

        default void onNotificationDotsUpdated(Predicate<PackageUserKey> updatedDots) {
        }

        default void onWidgetsBound() {
        }

        default void trimNotifications(Map<PackageUserKey, DotInfo> updatedDots) {
        }
    }

    public PopupDataProvider(Launcher launcher) {
        this.mLauncher = launcher;
    }

    private void updateNotificationDots(Predicate<PackageUserKey> updatedDots) {
        this.mLauncher.updateNotificationDots(updatedDots);
        this.mChangeListener.onNotificationDotsUpdated(updatedDots);
    }

    @Override // com.android.launcher3.notification.NotificationListener.NotificationsChangedListener
    public void onNotificationPosted(final PackageUserKey postedPackageUserKey, NotificationKeyData notificationKey, boolean shouldBeFilteredOut) {
        boolean zAddOrUpdateNotificationKey;
        DotInfo dotInfo = this.mPackageUserToDotInfos.get(postedPackageUserKey);
        if (dotInfo != null) {
            if (shouldBeFilteredOut) {
                zAddOrUpdateNotificationKey = dotInfo.removeNotificationKey(notificationKey);
            } else {
                zAddOrUpdateNotificationKey = dotInfo.addOrUpdateNotificationKey(notificationKey);
            }
            if (dotInfo.getNotificationKeys().size() == 0) {
                this.mPackageUserToDotInfos.remove(postedPackageUserKey);
            }
        } else if (shouldBeFilteredOut) {
            zAddOrUpdateNotificationKey = false;
        } else {
            DotInfo dotInfo2 = new DotInfo();
            dotInfo2.addOrUpdateNotificationKey(notificationKey);
            this.mPackageUserToDotInfos.put(postedPackageUserKey, dotInfo2);
            zAddOrUpdateNotificationKey = true;
        }
        if (zAddOrUpdateNotificationKey) {
            updateNotificationDots(new Predicate() { // from class: com.android.launcher3.popup.-$$Lambda$PopupDataProvider$vcDsgXE7ufcnc-V1NO29-9errcc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return postedPackageUserKey.equals((PackageUserKey) obj);
                }
            });
        }
    }

    @Override // com.android.launcher3.notification.NotificationListener.NotificationsChangedListener
    public void onNotificationRemoved(final PackageUserKey removedPackageUserKey, NotificationKeyData notificationKey) {
        DotInfo dotInfo = this.mPackageUserToDotInfos.get(removedPackageUserKey);
        if (dotInfo == null || !dotInfo.removeNotificationKey(notificationKey)) {
            return;
        }
        if (dotInfo.getNotificationKeys().size() == 0) {
            this.mPackageUserToDotInfos.remove(removedPackageUserKey);
        }
        updateNotificationDots(new Predicate() { // from class: com.android.launcher3.popup.-$$Lambda$PopupDataProvider$AmRmVlNXjViD5Kf5mSvzWLW8M0A
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return removedPackageUserKey.equals((PackageUserKey) obj);
            }
        });
        trimNotifications(this.mPackageUserToDotInfos);
    }

    @Override // com.android.launcher3.notification.NotificationListener.NotificationsChangedListener
    public void onNotificationFullRefresh(List<StatusBarNotification> activeNotifications) {
        if (activeNotifications == null) {
            return;
        }
        final HashMap map = new HashMap(this.mPackageUserToDotInfos);
        this.mPackageUserToDotInfos.clear();
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            PackageUserKey packageUserKeyFromNotification = PackageUserKey.fromNotification(statusBarNotification);
            DotInfo dotInfo = this.mPackageUserToDotInfos.get(packageUserKeyFromNotification);
            if (dotInfo == null) {
                dotInfo = new DotInfo();
                this.mPackageUserToDotInfos.put(packageUserKeyFromNotification, dotInfo);
            }
            dotInfo.addOrUpdateNotificationKey(NotificationKeyData.fromNotification(statusBarNotification));
        }
        for (PackageUserKey packageUserKey : this.mPackageUserToDotInfos.keySet()) {
            DotInfo dotInfo2 = (DotInfo) map.get(packageUserKey);
            DotInfo dotInfo3 = this.mPackageUserToDotInfos.get(packageUserKey);
            if (dotInfo2 == null) {
                map.put(packageUserKey, dotInfo3);
            } else {
                map.remove(packageUserKey);
            }
        }
        if (!map.isEmpty()) {
            Objects.requireNonNull(map);
            updateNotificationDots(new Predicate() { // from class: com.android.launcher3.popup.-$$Lambda$PopupDataProvider$mukw2rAzWP0ewIRycnNAlAHM-Rg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return map.containsKey((PackageUserKey) obj);
                }
            });
        }
        trimNotifications(map);
    }

    private void trimNotifications(Map<PackageUserKey, DotInfo> updatedDots) {
        this.mChangeListener.trimNotifications(updatedDots);
    }

    public void setDeepShortcutMap(HashMap<ComponentKey, Integer> deepShortcutMapCopy) {
        this.mDeepShortcutMap = deepShortcutMapCopy;
    }

    public int getShortcutCountForItem(ItemInfo info) {
        ComponentName targetComponent;
        Integer num;
        if (!DeepShortcutManager.supportsShortcuts(info) || (targetComponent = info.getTargetComponent()) == null || (num = this.mDeepShortcutMap.get(new ComponentKey(targetComponent, info.user))) == null) {
            return 0;
        }
        return num.intValue();
    }

    public DotInfo getDotInfoForItem(ItemInfo info) {
        if (DeepShortcutManager.supportsShortcuts(info)) {
            return this.mPackageUserToDotInfos.get(PackageUserKey.fromItemInfo(info));
        }
        return null;
    }

    public List<NotificationKeyData> getNotificationKeysForItem(ItemInfo info) {
        DotInfo dotInfoForItem = getDotInfoForItem(info);
        return dotInfoForItem == null ? Collections.EMPTY_LIST : dotInfoForItem.getNotificationKeys();
    }

    public List<StatusBarNotification> getStatusBarNotificationsForKeys(List<NotificationKeyData> notificationKeys) {
        NotificationListener instanceIfConnected = NotificationListener.getInstanceIfConnected();
        if (instanceIfConnected == null) {
            return Collections.EMPTY_LIST;
        }
        return instanceIfConnected.getNotificationsForKeys(notificationKeys);
    }

    public void cancelNotification(String notificationKey) {
        NotificationListener instanceIfConnected = NotificationListener.getInstanceIfConnected();
        if (instanceIfConnected == null) {
            return;
        }
        instanceIfConnected.cancelNotificationFromLauncher(notificationKey);
    }

    public void setAllWidgets(ArrayList<WidgetListRowEntry> allWidgets) {
        this.mAllWidgets = allWidgets;
        this.mChangeListener.onWidgetsBound();
    }

    public void setChangeListener(PopupDataChangeListener listener) {
        if (listener == null) {
            listener = PopupDataChangeListener.INSTANCE;
        }
        this.mChangeListener = listener;
    }

    public ArrayList<WidgetListRowEntry> getAllWidgets() {
        return this.mAllWidgets;
    }

    public List<WidgetItem> getWidgetsForPackageUser(PackageUserKey packageUserKey) {
        for (WidgetListRowEntry widgetListRowEntry : this.mAllWidgets) {
            if (widgetListRowEntry.pkgItem.packageName.equals(packageUserKey.mPackageName)) {
                ArrayList arrayList = new ArrayList(widgetListRowEntry.widgets);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (!((WidgetItem) it.next()).user.equals(packageUserKey.mUser)) {
                        it.remove();
                    }
                }
                if (arrayList.isEmpty()) {
                    return null;
                }
                return arrayList;
            }
        }
        return null;
    }
}
