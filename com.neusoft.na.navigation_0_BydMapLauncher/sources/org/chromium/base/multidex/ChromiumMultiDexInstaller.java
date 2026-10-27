package org.chromium.base.multidex;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import androidx.multidex.MultiDex;
import java.lang.reflect.InvocationTargetException;
import org.chromium.base.BuildConfig;
import org.chromium.base.Log;

/* JADX INFO: loaded from: classes3.dex */
public class ChromiumMultiDexInstaller {
    private static final String IGNORE_MULTIDEX_KEY = ".ignore_multidex";
    private static final String TAG = "base_multidex";

    public static void install(Context context) {
        if (BuildConfig.isMultidexEnabled()) {
            MultiDex.install(context);
            Log.i(TAG, "Completed multidex installation.", new Object[0]);
        }
    }

    private static String getProcessName(Context context) {
        try {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.processName;
                }
            }
            return null;
        } catch (SecurityException unused) {
            return null;
        }
    }

    private static boolean shouldInstallMultiDex(Context context) {
        try {
            Object objInvoke = Process.class.getMethod("isIsolated", new Class[0]).invoke(null, new Object[0]);
            if (objInvoke != null && (objInvoke instanceof Boolean)) {
                if (((Boolean) objInvoke).booleanValue()) {
                    return false;
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
        }
        if (getProcessName(context) == null) {
            return true;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                return !applicationInfo.metaData.getBoolean(r1 + IGNORE_MULTIDEX_KEY, false);
            }
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        return true;
    }
}
