package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.os.UserHandle;
import android.util.Log;
import com.amazon.android.os.MultipleProfileHelper;
import com.amazon.identity.auth.device.utils.ReflectionHelper;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class m4 {
    public static final Class<?> c;
    public static final String d;
    public static final Integer e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final UserHandle f428a;
    public final int b;

    static {
        Class<?> cls;
        Integer num = null;
        try {
            cls = Class.forName("android.os.UserHandle");
        } catch (ClassNotFoundException unused) {
            ga.a(d, "Cannot find class %s", "android.os.UserHandle");
            cls = null;
        }
        c = cls;
        String name = m4.class.getName();
        d = name;
        if (cls == null) {
            ga.a(name);
        } else {
            try {
                num = (Integer) new ReflectionHelper().a(cls, (Object) null, "USER_OWNER");
            } catch (ReflectionHelper.CannotCallMethodException e2) {
                ga.b(d, "Cannot get USER_OWNER static field. Error: %s", e2.getMessage());
            }
        }
        e = num;
    }

    @SuppressLint({"NewApi"})
    public m4(int i, int i2, Object obj) {
        this.b = i;
        this.f428a = (UserHandle) obj;
    }

    public static int a() {
        if (qe.c()) {
            return MultipleProfileHelper.getCallingProfileId();
        }
        if (c == null) {
            return 0;
        }
        try {
            return ((Integer) new ReflectionHelper().a("getCallingUserId", "android.os.UserHandle", new Class[0], new Object[0])).intValue();
        } catch (ReflectionHelper.CannotCallMethodException e2) {
            ga.b(d, "Cannot get getCallingUserId static field. Error: %s", e2.getMessage());
            return 0;
        }
    }

    public static int b() {
        if (qe.c()) {
            return MultipleProfileHelper.myProfileId();
        }
        if (c == null) {
            return 0;
        }
        try {
            return ((Integer) new ReflectionHelper().a("myUserId", "android.os.UserHandle", new Class[0], new Object[0])).intValue();
        } catch (ReflectionHelper.CannotCallMethodException e2) {
            ga.b(d, "Cannot get myUserId static field. Error: %s", e2.getMessage());
            return 0;
        }
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof m4) && this.b == ((m4) obj).b;
    }

    public int hashCode() {
        return this.b + 31;
    }

    public static m4 a(Object obj) {
        if (obj != null && "android.content.pm.UserInfo".equals(obj.getClass().getName())) {
            try {
                ReflectionHelper reflectionHelper = new ReflectionHelper();
                return new m4(((Integer) reflectionHelper.a(obj.getClass(), obj, "id")).intValue(), ((Integer) reflectionHelper.a(obj.getClass(), obj, "flags")).intValue(), reflectionHelper.a("getUserHandle", obj.getClass(), obj, new Class[0], new Object[0]));
            } catch (ReflectionHelper.CannotCallMethodException e2) {
                Log.e(ga.a(d), "Cannot construct Android User from User Info", e2);
                return null;
            }
        }
        Log.e(ga.a(d), "Given Object is not a valid UserInfo class");
        return null;
    }
}
