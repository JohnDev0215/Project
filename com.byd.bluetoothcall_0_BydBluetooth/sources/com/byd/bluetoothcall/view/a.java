package com.byd.bluetoothcall.view;

import com.byd.bluetoothcall.f.f;

/* JADX INFO: compiled from: BtCall.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f3287a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3288b = "unknown";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3289c = "unknown";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3290d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3291e = 0;
    private boolean f = false;
    private String g = "";

    public String a() {
        return this.f3288b;
    }

    public String b() {
        return this.f3289c;
    }

    public int c() {
        return this.f3290d;
    }

    public int d() {
        return this.f3291e;
    }

    public boolean e() {
        return this.f;
    }

    public void f(String str) {
        this.g = str;
    }

    public void g(long j) {
        this.f3287a = j;
    }

    public void h(boolean z) {
        this.f = z;
    }

    public void i(String str) {
        this.f3288b = str;
    }

    public void j(String str) {
        this.f3289c = str;
    }

    public void k(int i) {
        this.f3290d = i;
    }

    public void l(int i) {
        this.f3291e = i;
    }

    public String toString() {
        return "BtCall{id=" + this.f3287a + ", name='" + f.d(this.f3288b) + "', number='" + f.d(this.f3289c) + "', state=" + this.f3290d + ", time=" + this.f3291e + ", idle=" + this.f + ", date='" + this.g + "'}";
    }
}
