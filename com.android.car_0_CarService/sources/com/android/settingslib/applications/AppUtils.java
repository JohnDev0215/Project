package com.android.settingslib.applications;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.usb.IUsbManager;
import android.net.Uri;
import android.os.Environment;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import com.android.settingslib.R;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.applications.instantapps.InstantAppDataProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AppUtils {
    private static final String TAG = "AppUtils";
    private static InstantAppDataProvider sInstantAppDataProvider = null;
    private static final Intent sBrowserIntent = new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse("http:"));

    public static CharSequence getLaunchByDefaultSummary(ApplicationsState.AppEntry appEntry, IUsbManager usbManager, PackageManager pm, Context context) {
        int i;
        String packageName = appEntry.info.packageName;
        boolean hasPreferred = hasPreferredActivities(pm, packageName) || hasUsbDefaults(usbManager, packageName);
        int status = pm.getIntentVerificationStatusAsUser(packageName, UserHandle.myUserId());
        boolean hasDomainURLsPreference = status != 0;
        if (hasPreferred || hasDomainURLsPreference) {
            i = R.string.launch_defaults_some;
        } else {
            i = R.string.launch_defaults_none;
        }
        return context.getString(i);
    }

    public static boolean hasUsbDefaults(IUsbManager usbManager, String packageName) {
        if (usbManager != null) {
            try {
                return usbManager.hasDefaults(packageName, UserHandle.myUserId());
            } catch (RemoteException e) {
                Log.e(TAG, "mUsbManager.hasDefaults", e);
                return false;
            }
        }
        return false;
    }

    public static boolean hasPreferredActivities(PackageManager pm, String packageName) {
        List<ComponentName> prefActList = new ArrayList<>();
        List<IntentFilter> intentList = new ArrayList<>();
        pm.getPreferredActivities(intentList, prefActList, packageName);
        Log.d(TAG, "Have " + prefActList.size() + " number of activities in preferred list");
        return prefActList.size() > 0;
    }

    public static boolean isInstant(ApplicationInfo info) {
        String[] searchTerms;
        InstantAppDataProvider instantAppDataProvider = sInstantAppDataProvider;
        if (instantAppDataProvider != null) {
            if (instantAppDataProvider.isInstantApp(info)) {
                return true;
            }
        } else if (info.isInstantApp()) {
            return true;
        }
        String propVal = SystemProperties.get("settingsdebug.instant.packages");
        if (propVal != null && !propVal.isEmpty() && info.packageName != null && (searchTerms = propVal.split(",")) != null) {
            for (String term : searchTerms) {
                if (info.packageName.contains(term)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static CharSequence getApplicationLabel(PackageManager packageManager, String packageName) {
        return com.android.settingslib.utils.applications.AppUtils.getApplicationLabel(packageManager, packageName);
    }

    public static boolean isHiddenSystemModule(Context context, String packageName) {
        return ApplicationsState.getInstance((Application) context.getApplicationContext()).isHiddenModule(packageName);
    }

    public static boolean isSystemModule(Context context, String packageName) {
        return ApplicationsState.getInstance((Application) context.getApplicationContext()).isSystemModule(packageName);
    }

    public static boolean isMainlineModule(PackageManager pm, String packageName) {
        try {
            pm.getModuleInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            try {
                PackageInfo pkg = pm.getPackageInfo(packageName, 0);
                return pkg.applicationInfo.sourceDir.startsWith(Environment.getApexDirectory().getAbsolutePath());
            } catch (PackageManager.NameNotFoundException e2) {
                return false;
            }
        }
    }

    public static String getAppContentDescription(Context context, String packageName, int userId) {
        return com.android.settingslib.utils.applications.AppUtils.getAppContentDescription(context, packageName, userId);
    }

    public static boolean isBrowserApp(Context context, String packageName, int userId) {
        sBrowserIntent.setPackage(packageName);
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivitiesAsUser(sBrowserIntent, 131072, userId);
        for (ResolveInfo info : list) {
            if (info.activityInfo != null && info.handleAllWebDataURI) {
                return true;
            }
        }
        return false;
    }
}
