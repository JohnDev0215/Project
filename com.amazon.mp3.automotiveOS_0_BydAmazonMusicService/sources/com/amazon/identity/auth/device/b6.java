package com.amazon.identity.auth.device;

import android.app.ActivityManager;
import android.util.Log;
import com.amazon.android.os.MultipleProfileHelper;
import com.amazon.identity.auth.device.utils.ReflectionHelper;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class b6 {
    public static final String e = "com.amazon.identity.auth.device.b6";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReflectionHelper f239a = new ReflectionHelper();
    public final t5 b;
    public final Object c;
    public final i5 d;

    public b6(t5 t5Var) {
        this.b = t5Var;
        this.c = t5Var.getSystemService("user");
        this.d = (i5) t5Var.getSystemService("sso_platform");
    }

    public final void a() {
        if (qe.b(this.d.f382a) && t9.f(this.b)) {
            return;
        }
        Log.e(ga.a(e), "checkIsUserManagerSupportedOnThisPlatform failed. IMP is going to crash. This issue is tracked in SSO-150 and FWK-10172");
        throw new IllegalStateException("This platform does not support UserManager");
    }

    public m4 b() {
        a();
        Class<?> cls = m4.c;
        int iIntValue = 0;
        if (qe.c()) {
            iIntValue = MultipleProfileHelper.getForegroundProfileId();
        } else {
            try {
                iIntValue = ((Integer) new ReflectionHelper().a("getCurrentUser", ActivityManager.class, null, new Class[0], new Object[0])).intValue();
            } catch (ReflectionHelper.CannotCallMethodException unused) {
                ga.a(m4.d);
            }
        }
        return a(iIntValue);
    }

    public m4 a(int i) {
        try {
            ReflectionHelper reflectionHelper = this.f239a;
            Object obj = this.c;
            Class[] clsArr = {Integer.TYPE};
            Object[] objArr = {Integer.valueOf(i)};
            reflectionHelper.getClass();
            return m4.a(reflectionHelper.a("getUserInfo", obj.getClass(), obj, clsArr, objArr));
        } catch (ReflectionHelper.CannotCallMethodException e2) {
            Log.e(ga.a(e), "Cannot get user info for my user id", e2);
            return null;
        }
    }
}
