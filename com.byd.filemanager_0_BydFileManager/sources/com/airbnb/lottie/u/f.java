package com.airbnb.lottie.u;

/* JADX INFO: compiled from: MeanCalculator.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f2809a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2810b;

    public void a(float f) {
        float f2 = this.f2809a + f;
        this.f2809a = f2;
        int i = this.f2810b + 1;
        this.f2810b = i;
        if (i == Integer.MAX_VALUE) {
            this.f2809a = f2 / 2.0f;
            this.f2810b = i / 2;
        }
    }
}
