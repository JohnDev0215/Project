package com.google.zxing.pdf417.decoder;

/* JADX INFO: compiled from: Codeword.java */
/* JADX INFO: loaded from: classes.dex */
final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4459a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4460b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4461c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4462d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4463e = -1;

    d(int i, int i2, int i3, int i4) {
        this.f4459a = i;
        this.f4460b = i2;
        this.f4461c = i3;
        this.f4462d = i4;
    }

    int a() {
        return this.f4461c;
    }

    int b() {
        return this.f4460b;
    }

    int c() {
        return this.f4463e;
    }

    int d() {
        return this.f4459a;
    }

    int e() {
        return this.f4462d;
    }

    int f() {
        return this.f4460b - this.f4459a;
    }

    boolean g() {
        return h(this.f4463e);
    }

    boolean h(int i) {
        return i != -1 && this.f4461c == (i % 3) * 3;
    }

    void i(int i) {
        this.f4463e = i;
    }

    void j() {
        this.f4463e = ((this.f4462d / 30) * 3) + (this.f4461c / 3);
    }

    public String toString() {
        return this.f4463e + "|" + this.f4462d;
    }
}
