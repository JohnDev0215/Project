package com.android.launcher3;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.graphics.drawable.Drawable;
import com.android.launcher3.util.ResourceBasedOverride;

/* JADX INFO: loaded from: classes4.dex */
public class IconProvider implements ResourceBasedOverride {
    public String getSystemStateForPackage(String systemState, String packageName) {
        return systemState;
    }

    public static IconProvider newInstance(Context context) {
        return (IconProvider) ResourceBasedOverride.Overrides.getObject(IconProvider.class, context, R.string.icon_provider_class);
    }

    public Drawable getIcon(LauncherActivityInfo info, int iconDpi, boolean flattenDrawable) {
        return info.getIcon(iconDpi);
    }
}
