package com.amazon.identity.frc.FrcCookiesManager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/* JADX INFO: compiled from: PackageUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {
    private static final String TAG = "com.amazon.identity.frc.FrcCookiesManager.b";

    private b() {
    }

    public static Integer a(Context context, String str) {
        try {
            return Integer.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, String.format("Could not find package %s", str), e);
            return null;
        }
    }
}
