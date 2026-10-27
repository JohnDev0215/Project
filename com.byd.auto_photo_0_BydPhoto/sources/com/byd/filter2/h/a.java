package com.byd.filter2.h;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2828b;

    public a(int i) {
        this(i, null);
    }

    public a(int i, String str) {
        this.f2827a = i;
        this.f2828b = str;
    }

    public String a() {
        return this.f2828b;
    }

    public int b() {
        return this.f2827a;
    }

    public boolean c() {
        return this.f2827a == 1;
    }

    public String toString() {
        return "ExportState{state=" + this.f2827a + ", exportPath='" + this.f2828b + "'}";
    }
}
