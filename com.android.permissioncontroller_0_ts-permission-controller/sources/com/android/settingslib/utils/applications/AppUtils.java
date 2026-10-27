package com.android.settingslib.utils.applications;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.UserManager;
import android.util.Log;
import com.android.settingslib.utils.R;

/* JADX INFO: loaded from: classes8.dex */
public class AppUtils {
    private static final String TAG = AppUtils.class.getSimpleName();

    public static CharSequence getApplicationLabel(PackageManager packageManager, String packageName) {
        try {
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 4194816);
            return appInfo.loadLabel(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Unable to find info for package: " + packageName);
            return null;
        }
    }

    public static String getAppContentDescription(Context context, String packageName, int userId) {
        CharSequence appLabel = getApplicationLabel(context.getPackageManager(), packageName);
        return ((UserManager) context.getSystemService(UserManager.class)).isManagedProfile(userId) ? context.getString(R.string.accessibility_work_profile_app_description, appLabel) : appLabel.toString();
    }
}
