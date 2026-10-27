package com.byd.filemanager.bean;

/* JADX INFO: compiled from: Volume.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f3541a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected String f3542b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String f3543c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected boolean f3544d = true;

    public d(int i, String str, String str2) {
        this.f3541a = i;
        this.f3542b = str2;
        this.f3543c = str;
        com.byd.filemanager.k.b.b("Volume -- path:" + str);
    }

    public int a() {
        return this.f3541a;
    }

    public String b() {
        return this.f3542b;
    }

    public String c() {
        return this.f3543c;
    }

    public boolean d() {
        return this.f3544d;
    }

    public String toString() {
        return "Volume{id=" + this.f3541a + ", path='" + this.f3543c + "'}";
    }
}
