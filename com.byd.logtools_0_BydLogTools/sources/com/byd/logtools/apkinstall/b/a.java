package com.byd.logtools.apkinstall.b;

/* JADX INFO: compiled from: ApkInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3048a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3049b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3050c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3051d;

    public a(String str, String str2) {
        this.f3048a = str;
        this.f3049b = str2;
    }

    public String a() {
        return this.f3048a;
    }

    public String b() {
        return this.f3049b;
    }

    public int c() {
        return this.f3051d;
    }

    public boolean d() {
        return this.f3050c;
    }

    public void e(int i) {
        this.f3051d = i;
    }

    public void f(boolean z) {
        this.f3050c = z;
    }

    public String toString() {
        return "ApkInfo{fileName='" + this.f3048a + "', isSelected=" + this.f3050c + ", installState=" + this.f3051d + '}';
    }
}
