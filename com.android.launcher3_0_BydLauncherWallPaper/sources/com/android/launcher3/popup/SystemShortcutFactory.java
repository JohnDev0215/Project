package com.android.launcher3.popup;

import android.content.Context;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.popup.shortcut.Uninstall;
import com.android.launcher3.util.MainThreadInitializedObject;
import com.android.launcher3.util.ResourceBasedOverride;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SystemShortcutFactory implements ResourceBasedOverride {
    public static final MainThreadInitializedObject<SystemShortcutFactory> INSTANCE = new MainThreadInitializedObject<>(new MainThreadInitializedObject.ObjectProvider() { // from class: com.android.launcher3.popup.-$$Lambda$SystemShortcutFactory$E9QQlLu-lbdYFMgyd-B8ZtLOnCo
        @Override // com.android.launcher3.util.MainThreadInitializedObject.ObjectProvider
        public final Object get(Context context) {
            return SystemShortcutFactory.lambda$static$0(context);
        }
    });
    private final SystemShortcut[] mAllShortcuts;

    static /* synthetic */ SystemShortcutFactory lambda$static$0(Context context) {
        return (SystemShortcutFactory) ResourceBasedOverride.Overrides.getObject(SystemShortcutFactory.class, context, R.string.system_shortcut_factory_class);
    }

    public SystemShortcutFactory() {
        this(new Uninstall());
    }

    protected SystemShortcutFactory(SystemShortcut... shortcuts) {
        this.mAllShortcuts = shortcuts;
    }

    public List<SystemShortcut> getEnabledShortcuts(Launcher launcher, ItemInfo info) {
        ArrayList arrayList = new ArrayList();
        for (SystemShortcut systemShortcut : this.mAllShortcuts) {
            if (systemShortcut.getOnClickListener(launcher, info) != null) {
                arrayList.add(systemShortcut);
            }
        }
        return arrayList;
    }
}
