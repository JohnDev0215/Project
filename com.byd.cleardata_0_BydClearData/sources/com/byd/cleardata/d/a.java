package com.byd.cleardata.d;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2580a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Drawable f2581b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2582c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2583d;

    public String a() {
        return this.f2583d;
    }

    public Drawable b() {
        return this.f2581b;
    }

    public String c() {
        return this.f2580a;
    }

    public String d() {
        return this.f2582c;
    }

    public void e(String str) {
        this.f2583d = str;
    }

    public void f(Drawable drawable) {
        this.f2581b = drawable;
    }

    public void g(String str) {
        this.f2580a = str;
    }

    public void h(String str) {
        this.f2582c = str;
    }

    public String toString() {
        return "AppInfo{name='" + this.f2580a + "', packageName='" + this.f2582c + "', className='" + this.f2583d + "'}";
    }
}
