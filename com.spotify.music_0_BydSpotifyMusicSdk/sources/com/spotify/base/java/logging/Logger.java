package com.spotify.base.java.logging;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p.hc1;
import p.nc1;
import p.qc1;
import p.rc1;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public final class Logger {
    public static List a = Collections.emptyList();
    public static nc1 b = new zb0();
    public static qc1 c = new zb0();

    public static void a(String str, Object... objArr) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            f(((hc1) it.next()).c(), str, objArr);
        }
    }

    public static void b(String str, Object... objArr) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            f(((hc1) it.next()).f(), str, objArr);
        }
    }

    public static void c(Throwable th, String str, Object... objArr) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            ((hc1) it.next()).f().f(c.b(), d(str, objArr), th);
        }
    }

    public static void core(String str) {
        core(false, str);
    }

    public static String d(String str, Object... objArr) {
        return objArr.length == 0 ? b.g(str) : b.d(str, objArr);
    }

    public static void e(String str, Object... objArr) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            f(((hc1) it.next()).a(), str, objArr);
        }
    }

    public static void f(rc1 rc1Var, String str, Object... objArr) {
        rc1Var.c(c.b(), d(str, objArr));
    }

    public static void g(String str, Object... objArr) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            f(((hc1) it.next()).b(), str, objArr);
        }
    }

    public static void core(boolean z, String str) {
        for (hc1 hc1Var : a) {
            (z ? hc1Var.c() : hc1Var.a()).c(Thread.currentThread().getName() + "@core", d(str, new Object[0]));
        }
    }

    public static void core(int i, String str, int i2, String str2) {
        core(i, false, str, i2, str2);
    }

    public static void core(int i, boolean z, String str, int i2, String str2) {
        rc1 rc1VarD;
        for (hc1 hc1Var : a) {
            if (z) {
                rc1VarD = hc1Var.c();
            } else if (i == 0 || i == 1) {
                rc1VarD = hc1Var.d();
            } else if (i == 2) {
                rc1VarD = hc1Var.f();
            } else if (i == 3) {
                rc1VarD = hc1Var.b();
            } else if (i != 4) {
                rc1VarD = hc1Var.e();
            } else {
                rc1VarD = hc1Var.a();
            }
            rc1VarD.c(Thread.currentThread().getName() + '@' + str + ':' + i2, d(str2, new Object[0]));
        }
    }
}
