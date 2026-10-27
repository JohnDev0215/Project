package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import java.util.UUID;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class o5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f474a = UUID.randomUUID().toString().replace("-", "");
    public static final String b = o5.class.getSimpleName();

    public abstract String a();

    public abstract String a(y5 y5Var);

    public abstract long b();

    public abstract String c();

    public abstract String d();

    public String e() {
        String strD;
        try {
            strD = d();
        } catch (Exception e) {
            Log.e(ga.a(b), "Exception when trying to get DSN", e);
            strD = null;
        }
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        Log.e(ga.a(b), "Cannot get DSN, use randomly generated: " + f474a);
        return f474a;
    }

    public abstract String f();

    public kd g() {
        return new kd(Long.toString(b()));
    }

    public abstract boolean h();
}
