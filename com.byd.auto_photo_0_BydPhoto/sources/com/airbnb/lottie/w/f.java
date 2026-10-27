package com.airbnb.lottie.w;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f969a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f970b;

    public void a(float f) {
        float f2 = this.f969a + f;
        this.f969a = f2;
        int i = this.f970b + 1;
        this.f970b = i;
        if (i == Integer.MAX_VALUE) {
            this.f969a = f2 / 2.0f;
            this.f970b = i / 2;
        }
    }
}
