package com.airbnb.lottie.x;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f984a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f985b;

    public d() {
        this(1.0f, 1.0f);
    }

    public d(float f, float f2) {
        this.f984a = f;
        this.f985b = f2;
    }

    public boolean a(float f, float f2) {
        return this.f984a == f && this.f985b == f2;
    }

    public float b() {
        return this.f984a;
    }

    public float c() {
        return this.f985b;
    }

    public void d(float f, float f2) {
        this.f984a = f;
        this.f985b = f2;
    }

    public String toString() {
        return b() + "x" + c();
    }
}
