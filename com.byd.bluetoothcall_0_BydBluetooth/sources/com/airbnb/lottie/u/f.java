package com.airbnb.lottie.u;

/* JADX INFO: compiled from: MeanCalculator.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f3189a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3190b;

    public void a(float f) {
        float f2 = this.f3189a + f;
        this.f3189a = f2;
        int i = this.f3190b + 1;
        this.f3190b = i;
        if (i == Integer.MAX_VALUE) {
            this.f3189a = f2 / 2.0f;
            this.f3190b = i / 2;
        }
    }
}
