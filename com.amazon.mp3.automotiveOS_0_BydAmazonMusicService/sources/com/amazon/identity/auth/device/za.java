package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.PackageManager;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class za {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f706a = "com.amazon.identity.auth.device.za";

    public static Integer a(Context context, String str) {
        try {
            return Integer.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            String str2 = f706a;
            String.format("%s cannot be found because it is not installed", str);
            ga.a(str2);
            return null;
        }
    }
}
