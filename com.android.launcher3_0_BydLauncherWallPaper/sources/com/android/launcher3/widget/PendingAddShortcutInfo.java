package com.android.launcher3.widget;

import com.android.launcher3.PendingAddItemInfo;
import com.android.launcher3.compat.ShortcutConfigActivityInfo;

/* JADX INFO: loaded from: classes4.dex */
public class PendingAddShortcutInfo extends PendingAddItemInfo {
    public ShortcutConfigActivityInfo activityInfo;

    public PendingAddShortcutInfo(ShortcutConfigActivityInfo activityInfo) {
        this.activityInfo = activityInfo;
        this.componentName = activityInfo.getComponent();
        this.user = activityInfo.getUser();
        this.itemType = activityInfo.getItemType();
    }
}
