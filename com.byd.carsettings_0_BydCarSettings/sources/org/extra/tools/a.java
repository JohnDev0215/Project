package org.extra.tools;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3990a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Context f3991b;

    public static void a(String str) {
        try {
            f3991b = ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null)).getApplicationContext();
        } catch (Exception unused) {
        }
        b(f3991b, str);
    }

    private static void b(Context context, String str) {
        if (b(str) || a(context, str)) {
            return;
        }
        c(context, str);
    }

    private static boolean c(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                org.extra.relinker.c.a(context, str);
                return true;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            System.loadLibrary(str);
            return true;
        } catch (Throwable th) {
            Log.i(f3990a, "loadLibrary " + str + " fail! Error: " + th.getMessage());
            return false;
        }
    }

    private static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(context.getApplicationInfo().dataDir);
            sb.append("/lib");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            sb2.append(File.separator);
            sb2.append("lib");
            sb2.append(str);
            sb2.append(".so");
            System.load(sb2.toString());
            return true;
        } catch (Throwable th) {
            Log.i(f3990a, "load  fail! Error: " + th.getMessage());
            return false;
        }
    }
}
