package com.android.launcher3.popup;

import android.content.ComponentName;
import android.content.pm.ShortcutInfo;
import android.os.Handler;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.notification.NotificationInfo;
import com.android.launcher3.notification.NotificationKeyData;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.DeepShortcutView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PopupPopulator {
    public static final int MAX_SHORTCUTS = 4;
    public static final int MAX_SHORTCUTS_IF_NOTIFICATIONS = 2;
    static final int NUM_DYNAMIC = 2;
    private static final Comparator<ShortcutInfo> SHORTCUT_RANK_COMPARATOR = new Comparator<ShortcutInfo>() { // from class: com.android.launcher3.popup.PopupPopulator.1
        @Override // java.util.Comparator
        public int compare(ShortcutInfo a, ShortcutInfo b) {
            if (a.isDeclaredInManifest() && !b.isDeclaredInManifest()) {
                return -1;
            }
            if (a.isDeclaredInManifest() || !b.isDeclaredInManifest()) {
                return Integer.compare(a.getRank(), b.getRank());
            }
            return 1;
        }
    };

    public static List<ShortcutInfo> sortAndFilterShortcuts(List<ShortcutInfo> shortcuts, String shortcutIdToRemoveFirst) {
        if (shortcutIdToRemoveFirst != null) {
            Iterator<ShortcutInfo> it = shortcuts.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().getId().equals(shortcutIdToRemoveFirst)) {
                    it.remove();
                    break;
                }
            }
        }
        Collections.sort(shortcuts, SHORTCUT_RANK_COMPARATOR);
        if (shortcuts.size() <= 4) {
            return shortcuts;
        }
        ArrayList arrayList = new ArrayList(4);
        int size = shortcuts.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ShortcutInfo shortcutInfo = shortcuts.get(i2);
            int size2 = arrayList.size();
            if (size2 < 4) {
                arrayList.add(shortcutInfo);
                if (shortcutInfo.isDynamic()) {
                    i++;
                }
            } else if (shortcutInfo.isDynamic() && i < 2) {
                i++;
                arrayList.remove(size2 - i);
                arrayList.add(shortcutInfo);
            }
        }
        return arrayList;
    }

    public static Runnable createUpdateRunnable(final Launcher launcher, final ItemInfo originalInfo, final Handler uiHandler, final PopupContainerWithArrow container, final List<DeepShortcutView> shortcutViews, final List<NotificationKeyData> notificationKeys) {
        final ComponentName targetComponent = originalInfo.getTargetComponent();
        final UserHandle userHandle = originalInfo.user;
        return new Runnable() { // from class: com.android.launcher3.popup.-$$Lambda$PopupPopulator$cmIzQNw7m9owWnzPSgUAFVgvDhc
            @Override // java.lang.Runnable
            public final void run() {
                PopupPopulator.lambda$createUpdateRunnable$2(notificationKeys, launcher, uiHandler, container, targetComponent, userHandle, shortcutViews);
            }
        };
    }

    static /* synthetic */ void lambda$createUpdateRunnable$2(List list, Launcher launcher, Handler handler, final PopupContainerWithArrow popupContainerWithArrow, ComponentName componentName, UserHandle userHandle, List list2) {
        if (!list.isEmpty()) {
            List<StatusBarNotification> statusBarNotificationsForKeys = launcher.getPopupDataProvider().getStatusBarNotificationsForKeys(list);
            final ArrayList arrayList = new ArrayList(statusBarNotificationsForKeys.size());
            for (int i = 0; i < statusBarNotificationsForKeys.size(); i++) {
                arrayList.add(new NotificationInfo(launcher, statusBarNotificationsForKeys.get(i)));
            }
            handler.post(new Runnable() { // from class: com.android.launcher3.popup.-$$Lambda$PopupPopulator$X5ShG6e2BivBjh-zz_q3eQTLxrQ
                @Override // java.lang.Runnable
                public final void run() {
                    popupContainerWithArrow.applyNotificationInfos(arrayList);
                }
            });
        }
        List<ShortcutInfo> listSortAndFilterShortcuts = sortAndFilterShortcuts(DeepShortcutManager.getInstance(launcher).queryForShortcutsContainer(componentName, userHandle), list.isEmpty() ? null : ((NotificationKeyData) list.get(0)).shortcutId);
        for (int i2 = 0; i2 < listSortAndFilterShortcuts.size() && i2 < list2.size(); i2++) {
            final ShortcutInfo shortcutInfo = listSortAndFilterShortcuts.get(i2);
            final WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo(shortcutInfo, launcher);
            LauncherIcons launcherIconsObtain = LauncherIcons.obtain(launcher);
            workspaceItemInfo.applyFrom(launcherIconsObtain.createShortcutIcon(shortcutInfo, false));
            launcherIconsObtain.recycle();
            workspaceItemInfo.rank = i2;
            final DeepShortcutView deepShortcutView = (DeepShortcutView) list2.get(i2);
            handler.post(new Runnable() { // from class: com.android.launcher3.popup.-$$Lambda$PopupPopulator$kkLQXr3H4ETovjfZaJtDiSjoCwo
                @Override // java.lang.Runnable
                public final void run() {
                    deepShortcutView.applyShortcutInfo(workspaceItemInfo, shortcutInfo, popupContainerWithArrow);
                }
            });
        }
    }
}
