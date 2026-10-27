package com.amazon.mp3.service.metrics;

import android.content.Context;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class InstallSource {
    private static final String TAG = "InstallSource";

    public static String determineSource(final Context context) {
        String str;
        String installerPackage = getInstallerPackage(context);
        if (isAppPreloaded(context)) {
            String carrierName = ConnectivityUtil.getCarrierName();
            if (EnvironmentCompat.MEDIA_UNKNOWN.equals(carrierName)) {
                str = "PRELOAD-" + carrierName.toUpperCase(Locale.US);
            } else {
                str = "PRELOAD-" + Build.MANUFACTURER.toUpperCase(Locale.US);
            }
            installerPackage = str;
        } else if (isAmazonAppstoreApp()) {
            installerPackage = "AMAZON";
        } else if (installerPackage.length() == 0) {
            installerPackage = "";
        }
        Log.debug(TAG, "Installation source: %s", installerPackage);
        return installerPackage;
    }

    private static String getInstallerPackage(final Context context) {
        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        return installerPackageName == null ? "" : installerPackageName;
    }

    private static boolean isAmazonAppstoreApp() {
        try {
            Class.forName("com.amazon.venezia.command.Command");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean isAppPreloaded(final Context context) {
        int i = context.getApplicationInfo().flags;
        return ((i & 1) == 1) || ((i & 128) == 128);
    }
}
