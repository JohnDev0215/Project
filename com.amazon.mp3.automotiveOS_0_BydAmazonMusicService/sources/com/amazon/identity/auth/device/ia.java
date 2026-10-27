package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ia {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f384a = "com.amazon.identity.auth.device.ia";

    public static Boolean a(Context context, String str, String str2, boolean z) {
        Bundle bundleA = a(context, str);
        return bundleA == null ? Boolean.valueOf(z) : Boolean.valueOf(bundleA.getBoolean(str2, z));
    }

    public static String a(Context context, String str, String str2) {
        Bundle bundleA = a(context, str);
        if (bundleA == null) {
            return null;
        }
        return bundleA.getString(str2);
    }

    public static Bundle a(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo == null) {
                Log.w(ga.a(f384a), "Could not get application meta data for installed package.");
                return null;
            }
            return applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(ga.a(f384a), "Could not get meta data for the application", e);
            return null;
        }
    }
}
