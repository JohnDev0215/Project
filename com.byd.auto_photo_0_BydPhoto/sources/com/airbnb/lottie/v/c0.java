package com.airbnb.lottie.v;

import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
public class c0 implements j0<com.airbnb.lottie.x.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c0 f915a = new c0();

    private c0() {
    }

    @Override // com.airbnb.lottie.v.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.x.d a(com.airbnb.lottie.v.k0.c cVar, float f) {
        boolean z = cVar.w() == c.b.BEGIN_ARRAY;
        if (z) {
            cVar.k();
        }
        float fR = (float) cVar.r();
        float fR2 = (float) cVar.r();
        while (cVar.p()) {
            cVar.A();
        }
        if (z) {
            cVar.m();
        }
        return new com.airbnb.lottie.x.d((fR / 100.0f) * f, (fR2 / 100.0f) * f);
    }
}
