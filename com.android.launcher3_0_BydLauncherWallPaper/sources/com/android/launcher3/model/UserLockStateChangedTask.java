package com.android.launcher3.model;

import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.os.UserHandle;
import com.android.launcher3.AllAppsList;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.ItemInfoMatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class UserLockStateChangedTask extends BaseModelUpdateTask {
    private final UserHandle mUser;

    static /* synthetic */ ItemInfoWithIcon lambda$execute$0(WorkspaceItemInfo workspaceItemInfo) {
        return workspaceItemInfo;
    }

    public UserLockStateChangedTask(UserHandle user) {
        this.mUser = user;
    }

    @Override // com.android.launcher3.model.BaseModelUpdateTask
    public void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps) {
        Context context = app.getContext();
        boolean zIsUserUnlocked = UserManagerCompat.getInstance(context).isUserUnlocked(this.mUser);
        DeepShortcutManager deepShortcutManager = DeepShortcutManager.getInstance(context);
        HashMap map = new HashMap();
        if (zIsUserUnlocked) {
            List<ShortcutInfo> listQueryForPinnedShortcuts = deepShortcutManager.queryForPinnedShortcuts(null, this.mUser);
            if (deepShortcutManager.wasLastCallSuccess()) {
                for (ShortcutInfo shortcutInfo : listQueryForPinnedShortcuts) {
                    map.put(ShortcutKey.fromInfo(shortcutInfo), shortcutInfo);
                }
            } else {
                zIsUserUnlocked = false;
            }
        }
        ArrayList<WorkspaceItemInfo> arrayList = new ArrayList<>();
        HashSet hashSet = new HashSet();
        for (ItemInfo itemInfo : dataModel.itemsIdMap) {
            if (itemInfo.itemType == 6 && this.mUser.equals(itemInfo.user)) {
                final WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) itemInfo;
                if (zIsUserUnlocked) {
                    ShortcutKey shortcutKeyFromItemInfo = ShortcutKey.fromItemInfo(workspaceItemInfo);
                    ShortcutInfo shortcutInfo2 = (ShortcutInfo) map.get(shortcutKeyFromItemInfo);
                    if (shortcutInfo2 == null) {
                        hashSet.add(shortcutKeyFromItemInfo);
                    } else {
                        workspaceItemInfo.runtimeStatusFlags &= -33;
                        workspaceItemInfo.updateFromDeepShortcutInfo(shortcutInfo2, context);
                        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(context);
                        workspaceItemInfo.applyFrom(launcherIconsObtain.createShortcutIcon(shortcutInfo2, true, new Supplier() { // from class: com.android.launcher3.model.-$$Lambda$UserLockStateChangedTask$_F79O3iJIDWkGfZCIL7N2nPHD0c
                            @Override // java.util.function.Supplier
                            public final Object get() {
                                return UserLockStateChangedTask.lambda$execute$0(workspaceItemInfo);
                            }
                        }));
                        launcherIconsObtain.recycle();
                    }
                } else {
                    workspaceItemInfo.runtimeStatusFlags |= 32;
                }
                arrayList.add(workspaceItemInfo);
            }
        }
        bindUpdatedWorkspaceItems(arrayList);
        if (!hashSet.isEmpty()) {
            deleteAndBindComponentsRemoved(ItemInfoMatcher.ofShortcutKeys(hashSet));
        }
        Iterator<ComponentKey> it = dataModel.deepShortcutMap.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().user.equals(this.mUser)) {
                it.remove();
            }
        }
        if (zIsUserUnlocked) {
            UserHandle userHandle = this.mUser;
            dataModel.updateDeepShortcutCounts(null, userHandle, deepShortcutManager.queryForAllShortcuts(userHandle));
        }
        bindDeepShortcuts(dataModel);
    }
}
