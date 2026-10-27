package com.airbnb.lottie.v;

/* JADX INFO: compiled from: ScaleXY.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f3210a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f3211b;

    public d(float f, float f2) {
        this.f3210a = f;
        this.f3211b = f2;
    }

    public boolean a(float f, float f2) {
        return this.f3210a == f && this.f3211b == f2;
    }

    public float b() {
        return this.f3210a;
    }

    public float c() {
        return this.f3211b;
    }

    public void d(float f, float f2) {
        this.f3210a = f;
        this.f3211b = f2;
    }

    public String toString() {
        return b() + "x" + c();
    }

    public d() {
        this(1.0f, 1.0f);
    }
}
