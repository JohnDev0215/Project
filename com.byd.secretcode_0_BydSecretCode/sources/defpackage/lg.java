package defpackage;

import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: AppInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class lg {
    public String a;
    public String b;
    public String c;
    public Drawable d;
    public int e;
    public String f;

    public String a() {
        return this.c;
    }

    public Drawable b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public void e(String str) {
        this.b = str;
    }

    public void f(String str) {
        this.c = str;
    }

    public void g(String str) {
        this.a = str;
    }

    public void h(Drawable drawable) {
        this.d = drawable;
    }

    public void i(int i) {
        this.e = i;
    }

    public void j(String str) {
        this.f = str;
    }

    public String toString() {
        return "AppInfo{mPkgName='" + this.a + "', mAppLauncherClassName='" + this.b + "', mAppName='" + this.c + "', mIcon=" + this.d + ", mVersionCode=" + this.e + ", mVersionName='" + this.f + "'}";
    }
}
