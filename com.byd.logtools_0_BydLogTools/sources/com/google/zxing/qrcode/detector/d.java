package com.google.zxing.qrcode.detector;

import com.google.zxing.i;

/* JADX INFO: compiled from: FinderPattern.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f4525c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4526d;

    d(float f2, float f3, float f4) {
        this(f2, f3, f4, 1);
    }

    boolean f(float f2, float f3, float f4) {
        if (Math.abs(f3 - d()) > f2 || Math.abs(f4 - c()) > f2) {
            return false;
        }
        float fAbs = Math.abs(f2 - this.f4525c);
        return fAbs <= 1.0f || fAbs <= this.f4525c;
    }

    d g(float f2, float f3, float f4) {
        int i = this.f4526d;
        int i2 = i + 1;
        float fC = (i * c()) + f3;
        float f5 = i2;
        return new d(fC / f5, ((this.f4526d * d()) + f2) / f5, ((this.f4526d * this.f4525c) + f4) / f5, i2);
    }

    int h() {
        return this.f4526d;
    }

    public float i() {
        return this.f4525c;
    }

    private d(float f2, float f3, float f4, int i) {
        super(f2, f3);
        this.f4525c = f4;
        this.f4526d = i;
    }
}
