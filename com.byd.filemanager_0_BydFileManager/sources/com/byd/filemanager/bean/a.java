package com.byd.filemanager.bean;

import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: AppInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3530a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Drawable f3531b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3532c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3533d;

    public String a() {
        return this.f3533d;
    }

    public Drawable b() {
        return this.f3531b;
    }

    public String c() {
        return this.f3530a;
    }

    public String d() {
        return this.f3532c;
    }

    public void e(String str) {
        this.f3533d = str;
    }

    public void f(Drawable drawable) {
        this.f3531b = drawable;
    }

    public void g(String str) {
        this.f3530a = str;
    }

    public void h(String str) {
        this.f3532c = str;
    }

    public String toString() {
        return "AppInfo{name='" + this.f3530a + "', packageName='" + this.f3532c + "', className='" + this.f3533d + "'}";
    }
}
