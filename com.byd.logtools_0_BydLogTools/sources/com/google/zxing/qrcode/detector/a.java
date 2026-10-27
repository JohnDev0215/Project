package com.google.zxing.qrcode.detector;

import com.google.zxing.i;

/* JADX INFO: compiled from: AlignmentPattern.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f4514c;

    a(float f2, float f3, float f4) {
        super(f2, f3);
        this.f4514c = f4;
    }

    boolean f(float f2, float f3, float f4) {
        if (Math.abs(f3 - d()) > f2 || Math.abs(f4 - c()) > f2) {
            return false;
        }
        float fAbs = Math.abs(f2 - this.f4514c);
        return fAbs <= 1.0f || fAbs <= this.f4514c;
    }

    a g(float f2, float f3, float f4) {
        return new a((c() + f3) / 2.0f, (d() + f2) / 2.0f, (this.f4514c + f4) / 2.0f);
    }
}
