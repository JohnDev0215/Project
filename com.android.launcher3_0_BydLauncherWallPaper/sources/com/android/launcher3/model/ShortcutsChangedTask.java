package com.android.launcher3.model;

import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.os.UserHandle;
import com.android.launcher3.AllAppsList;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.util.MultiHashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class ShortcutsChangedTask extends BaseModelUpdateTask {
    private final String mPackageName;
    private final List<ShortcutInfo> mShortcuts;
    private final boolean mUpdateIdMap;
    private final UserHandle mUser;

    static /* synthetic */ ItemInfoWithIcon lambda$execute$0(WorkspaceItemInfo workspaceItemInfo) {
        return workspaceItemInfo;
    }

    public ShortcutsChangedTask(String packageName, List<ShortcutInfo> shortcuts, UserHandle user, boolean updateIdMap) {
        this.mPackageName = packageName;
        this.mShortcuts = shortcuts;
        this.mUser = user;
        this.mUpdateIdMap = updateIdMap;
    }

    @Override // com.android.launcher3.model.BaseModelUpdateTask
    public void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps) {
        Context context = app.getContext();
        DeepShortcutManager deepShortcutManager = DeepShortcutManager.getInstance(context);
        HashSet hashSet = new HashSet();
        MultiHashMap multiHashMap = new MultiHashMap();
        HashSet hashSet2 = new HashSet();
        for (ItemInfo itemInfo : dataModel.itemsIdMap) {
            if (itemInfo.itemType == 6) {
                WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) itemInfo;
                if (workspaceItemInfo.getIntent().getPackage().equals(this.mPackageName) && workspaceItemInfo.user.equals(this.mUser)) {
                    multiHashMap.addToList(ShortcutKey.fromItemInfo(workspaceItemInfo), workspaceItemInfo);
                    hashSet2.add(workspaceItemInfo.getDeepShortcutId());
                }
            }
        }
        ArrayList<WorkspaceItemInfo> arrayList = new ArrayList<>();
        if (!multiHashMap.isEmpty()) {
            for (ShortcutInfo shortcutInfo : deepShortcutManager.queryForFullDetails(this.mPackageName, new ArrayList(hashSet2), this.mUser)) {
                ShortcutKey shortcutKeyFromInfo = ShortcutKey.fromInfo(shortcutInfo);
                List<WorkspaceItemInfo> listRemove = multiHashMap.remove(shortcutKeyFromInfo);
                if (!shortcutInfo.isPinned()) {
                    hashSet.add(shortcutKeyFromInfo);
                } else {
                    for (final WorkspaceItemInfo workspaceItemInfo2 : listRemove) {
                        workspaceItemInfo2.updateFromDeepShortcutInfo(shortcutInfo, context);
                        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(context);
                        workspaceItemInfo2.applyFrom(launcherIconsObtain.createShortcutIcon(shortcutInfo, true, new Supplier() { // from class: com.android.launcher3.model.-$$Lambda$ShortcutsChangedTask$gCByPSC2x3eMmA6F9TdgWP3t_Lk
                            @Override // java.util.function.Supplier
                            public final Object get() {
                                return ShortcutsChangedTask.lambda$execute$0(workspaceItemInfo2);
                            }
                        }));
                        launcherIconsObtain.recycle();
                        arrayList.add(workspaceItemInfo2);
                    }
                }
            }
        }
        hashSet.addAll(multiHashMap.keySet());
        bindUpdatedWorkspaceItems(arrayList);
        if (!multiHashMap.isEmpty()) {
            deleteAndBindComponentsRemoved(ItemInfoMatcher.ofShortcutKeys(hashSet));
        }
        if (this.mUpdateIdMap) {
            dataModel.updateDeepShortcutCounts(this.mPackageName, this.mUser, this.mShortcuts);
            bindDeepShortcuts(dataModel);
        }
    }
}
