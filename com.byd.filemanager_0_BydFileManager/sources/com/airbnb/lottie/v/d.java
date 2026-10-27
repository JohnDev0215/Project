package com.airbnb.lottie.v;

/* JADX INFO: compiled from: ScaleXY.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f2830a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f2831b;

    public d(float f, float f2) {
        this.f2830a = f;
        this.f2831b = f2;
    }

    public boolean a(float f, float f2) {
        return this.f2830a == f && this.f2831b == f2;
    }

    public float b() {
        return this.f2830a;
    }

    public float c() {
        return this.f2831b;
    }

    public void d(float f, float f2) {
        this.f2830a = f;
        this.f2831b = f2;
    }

    public String toString() {
        return b() + "x" + c();
    }

    public d() {
        this(1.0f, 1.0f);
    }
}
