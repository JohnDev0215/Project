package com.android.launcher3.shortcuts;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.os.UserHandle;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.util.ComponentKey;

/* JADX INFO: loaded from: classes4.dex */
public class ShortcutKey extends ComponentKey {
    public static final String EXTRA_SHORTCUT_ID = "shortcut_id";
    private static final String INTENT_CATEGORY = "com.android.launcher3.DEEP_SHORTCUT";

    public ShortcutKey(String packageName, UserHandle user, String id) {
        super(new ComponentName(packageName, id), user);
    }

    public ShortcutKey(ComponentName componentName, UserHandle user) {
        super(componentName, user);
    }

    public String getId() {
        return this.componentName.getClassName();
    }

    public static ShortcutKey fromInfo(ShortcutInfo shortcutInfo) {
        return new ShortcutKey(shortcutInfo.getPackage(), shortcutInfo.getUserHandle(), shortcutInfo.getId());
    }

    public static ShortcutKey fromIntent(Intent intent, UserHandle user) {
        return new ShortcutKey(intent.getPackage(), user, intent.getStringExtra(EXTRA_SHORTCUT_ID));
    }

    public static ShortcutKey fromItemInfo(ItemInfo info) {
        return fromIntent(info.getIntent(), info.user);
    }

    public static Intent makeIntent(ShortcutInfo si) {
        return new Intent(Intent.ACTION_MAIN).addCategory(INTENT_CATEGORY).setComponent(si.getActivity()).setPackage(si.getPackage()).setFlags(270532608).putExtra(EXTRA_SHORTCUT_ID, si.getId());
    }
}
