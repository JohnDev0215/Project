package com.byd.util;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static void a(Application application) {
        Resources resources = application.getResources();
        boolean z = false;
        boolean z2 = resources.getInteger(R.integer.boot_start) == 1;
        boolean z3 = resources.getInteger(R.integer.activity_main_enable) == 1;
        boolean z4 = resources.getInteger(R.integer.activity_video_enable) == 1;
        boolean z5 = resources.getBoolean(R.bool.support_crop);
        if ("852".equals(e.n())) {
            z4 = false;
            z3 = false;
        } else {
            z = z2;
        }
        b(application, z, z3, z5, z4);
    }

    public static void b(Application application, boolean z, boolean z2, boolean z3, boolean z4) {
        PackageManager packageManager = application.getPackageManager();
        String str = application.getApplicationInfo().packageName;
        d(new ComponentName(str, "com.byd.auto_photo.BootReceiver"), z, packageManager);
        d(new ComponentName(str, "com.byd.auto_photo.MainActivity"), z2, packageManager);
        d(new ComponentName(str, "com.byd.auto_photo.CropActivity"), z3, packageManager);
        d(new ComponentName(str, "com.byd.auto_photo.VideoPlayActivity"), z4, packageManager);
    }

    public static boolean c(Context context) {
        return "852".equals(e.n());
    }

    public static void d(ComponentName componentName, boolean z, PackageManager packageManager) {
        StringBuilder sb;
        String str;
        if (z) {
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
            sb = new StringBuilder();
            str = "photo enable : ";
        } else {
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
            sb = new StringBuilder();
            str = "photo disable :";
        }
        sb.append(str);
        sb.append(componentName.getClassName());
        w.c(sb.toString());
    }
}
