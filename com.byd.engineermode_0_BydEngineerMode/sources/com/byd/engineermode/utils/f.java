package com.byd.engineermode.utils;

import com.byd.engineermode.bean.State;

/* JADX INFO: compiled from: DiagnoseDeviceTestStatus.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private State f2511a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private State f2512b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private State f2513c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2514d;

    public f() {
        State state = State.UNPERFORMED;
        this.f2511a = state;
        this.f2512b = state;
        this.f2513c = state;
        this.f2514d = "--";
    }

    public State a() {
        return this.f2513c;
    }

    public State b() {
        return this.f2511a;
    }

    public String c() {
        return this.f2514d;
    }

    public void d(State state) {
        this.f2513c = state;
    }

    public void e(State state) {
        this.f2511a = state;
    }

    public void f(String str) {
        this.f2514d = str;
    }

    public String toString() {
        return "DiagnoseDeviceTestStatus{Test_SHORT_OUT=" + this.f2511a + ", Test_CUTTING_OUT=" + this.f2512b + ", Test_CONNECTION=" + this.f2513c + ", Test_SUPPLIER_NAME='" + this.f2514d + "'}";
    }
}
