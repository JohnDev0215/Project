package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.graphics.Bitmap;
import android.view.View;
import androidx.core.app.e;
import com.blankj.utilcode.util.n;
import com.blankj.utilcode.util.w;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: compiled from: UtilsBridge.java */
/* JADX INFO: loaded from: classes.dex */
class y {
    private static void A(Runnable... runnableArr) {
        for (Runnable runnable : runnableArr) {
            ThreadUtils.k().execute(runnable);
        }
    }

    static void B(w.a aVar) {
        x.f2623a.s(aVar);
    }

    static void C(Runnable runnable) {
        ThreadUtils.q(runnable);
    }

    static void D(Runnable runnable, long j) {
        ThreadUtils.r(runnable, j);
    }

    static void E(Application application) {
        x.f2623a.w(application);
    }

    static Bitmap F(View view) {
        return k.a(view);
    }

    static boolean G(String str, InputStream inputStream) {
        return h.e(str, inputStream);
    }

    static void a(w.a aVar) {
        x.f2623a.d(aVar);
    }

    static String b(byte[] bArr) {
        return e.b(bArr);
    }

    static boolean c(File file) {
        return i.d(file);
    }

    static int d(float f) {
        return u.a(f);
    }

    static void e(Activity activity) {
        l.a(activity);
    }

    static String f(String str, Object... objArr) {
        return v.a(str, objArr);
    }

    static List<Activity> g() {
        return x.f2623a.i();
    }

    static int h() {
        return s.a();
    }

    static Application i() {
        return x.f2623a.m();
    }

    static String j() {
        return p.a();
    }

    static File k(String str) {
        return i.j(str);
    }

    static int l() {
        return d.a();
    }

    static Notification m(n.a aVar, w.b<e.c> bVar) {
        return n.a(aVar, bVar);
    }

    static r n() {
        return r.b("Utils");
    }

    static int o() {
        return d.b();
    }

    static String p(int i) {
        return v.b(i);
    }

    static void q(Application application) {
        x.f2623a.n(application);
    }

    static byte[] r(InputStream inputStream) {
        return e.e(inputStream);
    }

    static boolean s(Activity activity) {
        return a.a(activity);
    }

    static boolean t() {
        return x.f2623a.o();
    }

    static boolean u(File file) {
        return i.m(file);
    }

    static boolean v() {
        return o.a();
    }

    static boolean w() {
        return z.a();
    }

    static boolean x(String str) {
        return v.d(str);
    }

    static View y(int i) {
        return z.b(i);
    }

    static void z() {
        A(b.f());
    }
}
