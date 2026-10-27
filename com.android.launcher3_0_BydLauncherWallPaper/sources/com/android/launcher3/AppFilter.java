package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;
import com.android.launcher3.util.ResourceBasedOverride;

/* JADX INFO: loaded from: classes4.dex */
public class AppFilter implements ResourceBasedOverride {
    public boolean shouldShowApp(ComponentName app) {
        return true;
    }

    public static AppFilter newInstance(Context context) {
        return (AppFilter) ResourceBasedOverride.Overrides.getObject(AppFilter.class, context, R.string.app_filter_class);
    }
}
