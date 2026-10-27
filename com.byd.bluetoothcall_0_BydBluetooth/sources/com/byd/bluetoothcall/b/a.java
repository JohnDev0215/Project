package com.byd.bluetoothcall.b;

import java.util.Arrays;

/* JADX INFO: compiled from: Calllog_info.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3232a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3233b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String[] f3234c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String[] f3235d;

    public String a() {
        return this.f3232a;
    }

    public String b() {
        return this.f3233b;
    }

    public String[] c() {
        return this.f3234c;
    }

    public void d(String str) {
        this.f3232a = str;
    }

    public void e(String str) {
        this.f3233b = str;
    }

    public void f(String[] strArr) {
        this.f3235d = strArr;
    }

    public void g(String[] strArr) {
        this.f3234c = strArr;
    }

    public String toString() {
        return "Calllog_info{Name='" + this.f3232a + "', Numbers='" + this.f3233b + "', t9code=" + Arrays.toString(this.f3234c) + ", pycode=" + Arrays.toString(this.f3235d) + '}';
    }
}
