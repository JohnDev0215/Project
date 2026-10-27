package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class la {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f421a = "com.amazon.identity.auth.device.la";

    public static Long a(Context context, String str) {
        try {
            return Long.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(ga.a(f421a), String.format("Could not find package. Error message : %s", e.getMessage()));
            return null;
        } catch (Exception e2) {
            Log.e(ga.a(f421a), String.format("Could not get version code for package. Error Message : %s", e2.getMessage()));
            return null;
        }
    }

    public static boolean a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                if (applicationInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static boolean a(Context context) {
        String str = f421a;
        ga.a(str);
        if (qe.m(context) && !qe.a(t5.a(context))) {
            ArrayList arrayList = (ArrayList) MAPApplicationInformationQueryer.a(context).c();
            boolean z = arrayList.isEmpty() || ((j5) arrayList.get(0)).e();
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[2];
            objArr[0] = context.getPackageName();
            objArr[1] = z ? "" : "not";
            String.format(locale, "Current package %s should %s generate device data.", objArr);
            ga.a(str);
            return z;
        }
        String.format("Running in 1P, or isolation mode. Package %s should generate device data.", context.getPackageName());
        ga.a(str);
        return true;
    }
}
