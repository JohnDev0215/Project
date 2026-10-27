package org.chromium.content.browser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import cerence.ark.assistant.core.dcc.DccConstant;
import org.chromium.base.Log;

/* JADX INFO: loaded from: classes3.dex */
public class PepperPluginManager {
    private static final String DESCRIPTION = "description";
    private static final String FILENAME = "filename";
    private static final String MIMETYPE = "mimetype";
    private static final String NAME = "name";
    public static final String PEPPER_PLUGIN_ACTION = "org.chromium.intent.PEPPERPLUGIN";
    public static final String PEPPER_PLUGIN_ROOT = "/system/lib/pepperplugin/";
    private static final String TAG = "cr.PepperPluginManager";
    private static final String VERSION = "version";

    private static String getPluginDescription(Bundle bundle) {
        String string;
        String string2 = bundle.getString(FILENAME);
        if (string2 == null || string2.isEmpty() || (string = bundle.getString(MIMETYPE)) == null || string.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(PEPPER_PLUGIN_ROOT);
        sb.append(string2);
        String string3 = bundle.getString("name");
        String string4 = bundle.getString(DESCRIPTION);
        String string5 = bundle.getString("version");
        if (string3 != null && !string3.isEmpty()) {
            sb.append(DccConstant.DCC_SEPARATOR);
            sb.append(string3);
            if (string4 != null && !string4.isEmpty()) {
                sb.append(DccConstant.DCC_SEPARATOR);
                sb.append(string4);
                if (string5 != null && !string5.isEmpty()) {
                    sb.append(DccConstant.DCC_SEPARATOR);
                    sb.append(string5);
                }
            }
        }
        sb.append(';');
        sb.append(string);
        return sb.toString();
    }

    public static String getPlugins(Context context) {
        StringBuilder sb = new StringBuilder();
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent(PEPPER_PLUGIN_ACTION), 132)) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo == null || serviceInfo.metaData == null || serviceInfo.packageName == null) {
                Log.e(TAG, "Can't get service information from %s", resolveInfo);
            } else {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(serviceInfo.packageName, 0);
                    if (packageInfo != null && (packageInfo.applicationInfo.flags & 1) != 0) {
                        Log.i(TAG, "The given plugin package is preloaded: %s", serviceInfo.packageName);
                        String pluginDescription = getPluginDescription(serviceInfo.metaData);
                        if (pluginDescription != null) {
                            if (sb.length() > 0) {
                                sb.append(',');
                            }
                            sb.append(pluginDescription);
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.e(TAG, "Can't find plugin: %s", serviceInfo.packageName);
                }
            }
        }
        return sb.toString();
    }
}
