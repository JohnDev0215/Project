package com.android.launcher3.util;

import android.content.ComponentName;
import android.os.UserHandle;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.shortcuts.ShortcutKey;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public interface ItemInfoMatcher {
    boolean matches(ItemInfo info, ComponentName cn);

    default HashSet<ItemInfo> filterItemInfos(Iterable<ItemInfo> infos) {
        LauncherAppWidgetInfo launcherAppWidgetInfo;
        ComponentName componentName;
        HashSet<ItemInfo> hashSet = new HashSet<>();
        for (ItemInfo itemInfo : infos) {
            if (itemInfo instanceof WorkspaceItemInfo) {
                ItemInfo itemInfo2 = (WorkspaceItemInfo) itemInfo;
                ComponentName targetComponent = itemInfo2.getTargetComponent();
                if (targetComponent != null && matches(itemInfo2, targetComponent)) {
                    hashSet.add(itemInfo2);
                }
            } else if (itemInfo instanceof FolderInfo) {
                for (ItemInfo itemInfo3 : ((FolderInfo) itemInfo).contents) {
                    ComponentName targetComponent2 = itemInfo3.getTargetComponent();
                    if (targetComponent2 != null && matches(itemInfo3, targetComponent2)) {
                        hashSet.add(itemInfo3);
                    }
                }
            } else if ((itemInfo instanceof LauncherAppWidgetInfo) && (componentName = (launcherAppWidgetInfo = (LauncherAppWidgetInfo) itemInfo).providerName) != null && matches(launcherAppWidgetInfo, componentName)) {
                hashSet.add(launcherAppWidgetInfo);
            }
        }
        return hashSet;
    }

    static /* synthetic */ boolean lambda$or$0(ItemInfoMatcher _this, ItemInfoMatcher itemInfoMatcher, ItemInfo itemInfo, ComponentName componentName) {
        return _this.matches(itemInfo, componentName) || itemInfoMatcher.matches(itemInfo, componentName);
    }

    default ItemInfoMatcher or(final ItemInfoMatcher matcher) {
        return new ItemInfoMatcher() { // from class: com.android.launcher3.util.-$$Lambda$ItemInfoMatcher$ajx7-u18qvghdWNHcdagG1ru5NU
            @Override // com.android.launcher3.util.ItemInfoMatcher
            public final boolean matches(ItemInfo itemInfo, ComponentName componentName) {
                return ItemInfoMatcher.lambda$or$0(this.f$0, matcher, itemInfo, componentName);
            }
        };
    }

    static /* synthetic */ boolean lambda$and$1(ItemInfoMatcher _this, ItemInfoMatcher itemInfoMatcher, ItemInfo itemInfo, ComponentName componentName) {
        return _this.matches(itemInfo, componentName) && itemInfoMatcher.matches(itemInfo, componentName);
    }

    default ItemInfoMatcher and(final ItemInfoMatcher matcher) {
        return new ItemInfoMatcher() { // from class: com.android.launcher3.util.-$$Lambda$ItemInfoMatcher$Ve8W8tX5t7J-QuqZs5P0L5E8ZIQ
            @Override // com.android.launcher3.util.ItemInfoMatcher
            public final boolean matches(ItemInfo itemInfo, ComponentName componentName) {
                return ItemInfoMatcher.lambda$and$1(this.f$0, matcher, itemInfo, componentName);
            }
        };
    }

    static /* synthetic */ boolean lambda$not$2(ItemInfoMatcher itemInfoMatcher, ItemInfo itemInfo, ComponentName componentName) {
        return !itemInfoMatcher.matches(itemInfo, componentName);
    }

    static ItemInfoMatcher not(final ItemInfoMatcher matcher) {
        return new ItemInfoMatcher() { // from class: com.android.launcher3.util.-$$Lambda$ItemInfoMatcher$4uIOxmG2I5T3FkGdKLkk5FeCEH0
            @Override // com.android.launcher3.util.ItemInfoMatcher
            public final boolean matches(ItemInfo itemInfo, ComponentName componentName) {
                return ItemInfoMatcher.lambda$not$2(this.f$0, itemInfo, componentName);
            }
        };
    }

    static ItemInfoMatcher ofUser(final UserHandle user) {
        return new ItemInfoMatcher() { // from class: com.android.launcher3.util.-$$Lambda$ItemInfoMatcher$MD7udUEiQJ225Yq9xWLBFSB1SDs
            @Override // com.android.launcher3.util.ItemInfoMatcher
            public final boolean matches(ItemInfo itemInfo, ComponentName componentName) {
                return itemInfo.user.equals(user);
            }
        };
    }

    static /* synthetic */ boolean lambda$ofComponents$4(HashSet hashSet, UserHandle userHandle, ItemInfo itemInfo, ComponentName componentName) {
        return hashSet.contains(componentName) && itemInfo.user.equals(userHandle);
    }

    static ItemInfoMatcher ofComponents(final HashSet<ComponentName> components, final UserHandle user) {
        return new ItemInfoMatcher() { // from class: com.android.launcher3.util.-$$Lambda$ItemInfoMatcher$ltMloLTAcm5WPvQGLnwgXudLjKQ
            @Override // com.android.launcher3.util.ItemInfoMatcher
            public final boolean matches(ItemInfo itemInfo, ComponentName componentName) {
                return ItemInfoMatcher.lambda$ofComponents$4(components, user, itemInfo, componentName);
            }
        };
    }

    static /* synthetic */ boolean lambda$ofPackages$5(HashSet hashSet, UserHandle userHandle, ItemInfo itemInfo, ComponentName componentName) {
        return hashSet.contains(componentName.getPackageName()) && itemInfo.user.equals(userHandle);
    }

    static ItemInfoMatcher ofPackages(final HashSet<String> packageNames, final UserHandle user) {
        return new ItemInfoMatcher() { // from class: com.android.launcher3.util.-$$Lambda$ItemInfoMatcher$_We7hHbrLJ18HojekNrf7fF1idM
            @Override // com.android.launcher3.util.ItemInfoMatcher
            public final boolean matches(ItemInfo itemInfo, ComponentName componentName) {
                return ItemInfoMatcher.lambda$ofPackages$5(packageNames, user, itemInfo, componentName);
            }
        };
    }

    static /* synthetic */ boolean lambda$ofShortcutKeys$6(HashSet hashSet, ItemInfo itemInfo, ComponentName componentName) {
        return itemInfo.itemType == 6 && hashSet.contains(ShortcutKey.fromItemInfo(itemInfo));
    }

    static ItemInfoMatcher ofShortcutKeys(final HashSet<ShortcutKey> keys) {
        return new ItemInfoMatcher() { // from class: com.android.launcher3.util.-$$Lambda$ItemInfoMatcher$NsigDGH0NEXHwsAGmQKGVYU9u_4
            @Override // com.android.launcher3.util.ItemInfoMatcher
            public final boolean matches(ItemInfo itemInfo, ComponentName componentName) {
                return ItemInfoMatcher.lambda$ofShortcutKeys$6(keys, itemInfo, componentName);
            }
        };
    }

    static ItemInfoMatcher ofItemIds(final IntSparseArrayMap<Boolean> ids, final Boolean matchDefault) {
        return new ItemInfoMatcher() { // from class: com.android.launcher3.util.-$$Lambda$ItemInfoMatcher$gTDKitMxqMe4ux7RavB7kLvDLak
            @Override // com.android.launcher3.util.ItemInfoMatcher
            public final boolean matches(ItemInfo itemInfo, ComponentName componentName) {
                return ((Boolean) ids.get(itemInfo.id, matchDefault)).booleanValue();
            }
        };
    }
}
