package com.byd.engineermode.bean;

/* JADX INFO: compiled from: ItemBean.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2293a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2294b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2295c = true;

    public c(String str) {
        this.f2293a = str;
    }

    public String a() {
        String str = this.f2293a;
        return str != null ? str : "";
    }

    public boolean b() {
        return this.f2295c;
    }

    public String c() {
        String str = this.f2294b;
        return str != null ? str : "";
    }

    public void d(boolean z) {
        this.f2295c = z;
    }
}
