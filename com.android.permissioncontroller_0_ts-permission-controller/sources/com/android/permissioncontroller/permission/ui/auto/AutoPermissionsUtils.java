package com.android.permissioncontroller.permission.ui.auto;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.Log;
import androidx.preference.Preference;
import com.android.permissioncontroller.permission.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class AutoPermissionsUtils {
    private static final String LOG_TAG = "AutoPermissionsUtils";

    private AutoPermissionsUtils() {
    }

    public static PackageInfo getPackageInfo(Activity activity, String packageName, UserHandle userHandle) {
        try {
            return activity.createPackageContextAsUser(packageName, 0, userHandle).getPackageManager().getPackageInfo(packageName, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(LOG_TAG, "No package:" + activity.getCallingPackage(), e);
            return null;
        }
    }

    public static Preference createHeaderPreference(Context context, ApplicationInfo appInfo) {
        Drawable icon = Utils.getBadgedIcon(context, appInfo);
        Preference preference = new Preference(context);
        preference.setIcon(icon);
        preference.setKey(appInfo.packageName);
        preference.setTitle(Utils.getFullAppLabel(appInfo, context));
        return preference;
    }
}
