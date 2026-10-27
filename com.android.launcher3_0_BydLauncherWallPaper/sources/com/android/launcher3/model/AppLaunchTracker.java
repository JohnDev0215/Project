package com.android.launcher3.model;

import android.content.ComponentName;
import android.content.Context;
import android.os.UserHandle;
import com.android.launcher3.R;
import com.android.launcher3.util.MainThreadInitializedObject;
import com.android.launcher3.util.ResourceBasedOverride;

/* JADX INFO: loaded from: classes4.dex */
public class AppLaunchTracker implements ResourceBasedOverride {
    public static final String CONTAINER_DEFAULT = Integer.toString(1);
    public static final String CONTAINER_ALL_APPS = Integer.toString(4);
    public static final String CONTAINER_PREDICTIONS = Integer.toString(7);
    public static final String CONTAINER_SEARCH = Integer.toString(8);
    public static final MainThreadInitializedObject<AppLaunchTracker> INSTANCE = new MainThreadInitializedObject<>(new MainThreadInitializedObject.ObjectProvider() { // from class: com.android.launcher3.model.-$$Lambda$AppLaunchTracker$UrgSvqmnaec8nq5gS6ZYq5zQPX8
        @Override // com.android.launcher3.util.MainThreadInitializedObject.ObjectProvider
        public final Object get(Context context) {
            return AppLaunchTracker.lambda$static$0(context);
        }
    });

    public void onReturnedToHome() {
    }

    public void onStartApp(ComponentName componentName, UserHandle user, String container) {
    }

    public void onStartShortcut(String packageName, String shortcutId, UserHandle user, String container) {
    }

    static /* synthetic */ AppLaunchTracker lambda$static$0(Context context) {
        return (AppLaunchTracker) ResourceBasedOverride.Overrides.getObject(AppLaunchTracker.class, context, R.string.app_launch_tracker_class);
    }
}
