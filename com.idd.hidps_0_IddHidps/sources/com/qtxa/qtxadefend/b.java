package com.qtxa.qtxadefend;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f111b;
    public static final boolean c;
    private static boolean d;

    static {
        f110a = new File("/data/idd/enable_log").exists() || new File("/data/qtxa/enable_log").exists();
        f111b = new File("/data/idd/enable_logi").exists() || new File("/data/qtxa/enable_logi").exists();
        c = new File("/data/idd/enable_rule").exists() || new File("/data/qtxa/enable_rule").exists();
        int i = Build.VERSION.SDK_INT;
        d = true;
    }

    public static boolean a() {
        return d;
    }

    public static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0.0";
        }
    }
}
