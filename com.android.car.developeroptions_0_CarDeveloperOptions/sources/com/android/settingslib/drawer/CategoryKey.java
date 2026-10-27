package com.android.settingslib.drawer;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class CategoryKey {
    public static final Map<String, String> KEY_COMPAT_MAP;

    static {
        HashMap map = new HashMap();
        KEY_COMPAT_MAP = map;
        map.put("com.android.settings.category.wireless", "com.android.settings.category.ia.wireless");
        KEY_COMPAT_MAP.put("com.android.settings.category.device", "com.android.settings.category.ia.system");
        KEY_COMPAT_MAP.put("com.android.settings.category.personal", "com.android.settings.category.ia.system");
        KEY_COMPAT_MAP.put("com.android.settings.category.system", "com.android.settings.category.ia.system");
    }
}
