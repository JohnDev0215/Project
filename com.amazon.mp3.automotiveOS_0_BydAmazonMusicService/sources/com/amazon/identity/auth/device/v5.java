package com.amazon.identity.auth.device;

import android.os.Build;
import android.util.Log;
import com.amazon.identity.auth.device.utils.ReflectionHelper;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class v5 {
    public static final String b = "com.amazon.identity.auth.device.v5";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReflectionHelper f626a = new ReflectionHelper();

    public String a(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() > 31) {
            str = str.substring(0, 31);
        }
        ga.a(b);
        return (String) a("get", new Class[]{String.class}, str);
    }

    public final Object a(String str, Class[] clsArr, Object... objArr) {
        try {
            return this.f626a.a(str, "android.os.SystemProperties", clsArr, objArr);
        } catch (ReflectionHelper.CannotCallMethodException unused) {
            Log.e(ga.a(b), String.format(Locale.US, "Cannot call such method %s in SystemProperties in Android %d", str, Integer.valueOf(Build.VERSION.SDK_INT)));
            return null;
        }
    }
}
