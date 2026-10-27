package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.utils.PackageUtils;

/* JADX INFO: loaded from: classes8.dex */
public class EncryptionUnawareConfirmationMixin {
    private static final String LOG_TAG = EncryptionUnawareConfirmationMixin.class.getSimpleName();

    public static CharSequence getConfirmationMessage(Role role, String packageName, Context context) {
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, context);
        if (applicationInfo == null) {
            Log.w(LOG_TAG, "Cannot get ApplicationInfo for application, package name: " + packageName);
            return null;
        }
        if (applicationInfo.isEncryptionAware()) {
            return null;
        }
        return context.getString(R.string.encryption_unaware_confirmation_message);
    }
}
