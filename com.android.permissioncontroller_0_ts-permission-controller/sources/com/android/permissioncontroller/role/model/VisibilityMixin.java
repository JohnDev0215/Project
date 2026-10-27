package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.android.permissioncontroller.permission.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class VisibilityMixin {
    private static final String LOG_TAG = VisibilityMixin.class.getSimpleName();

    private VisibilityMixin() {
    }

    public static boolean isVisible(String resourceName, Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(resourceName, "bool", Utils.OS_PKG);
        if (resourceId == 0) {
            Log.w(LOG_TAG, "Cannot find resource for visibility: " + resourceName);
            return true;
        }
        try {
            return resources.getBoolean(resourceId);
        } catch (Resources.NotFoundException e) {
            Log.w(LOG_TAG, "Cannot get resource for visibility: " + resourceName, e);
            return true;
        }
    }
}
