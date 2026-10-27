package com.android.server.telecom;

import android.content.pm.PackageManager;
import android.telecom.Log;

/* JADX INFO: loaded from: classes.dex */
public interface AppLabelProxy {
    public static final String LOG_TAG = "AppLabelProxy";

    CharSequence getAppLabel(String str);

    public static class Util {
        public static CharSequence getAppLabel(PackageManager packageManager, String str) {
            try {
                CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0));
                Log.i(AppLabelProxy.LOG_TAG, "package %s: name is %s", new Object[]{str, applicationLabel});
                return applicationLabel;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w(AppLabelProxy.LOG_TAG, "Could not determine app label. Package name is %s", new Object[]{str});
                return null;
            }
        }
    }
}
