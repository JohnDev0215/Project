package com.airbnb.lottie.v;

import android.graphics.Color;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
public class f implements j0<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f920a = new f();

    private f() {
    }

    @Override // com.airbnb.lottie.v.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(com.airbnb.lottie.v.k0.c cVar, float f) {
        boolean z = cVar.w() == c.b.BEGIN_ARRAY;
        if (z) {
            cVar.k();
        }
        double dR = cVar.r();
        double dR2 = cVar.r();
        double dR3 = cVar.r();
        double dR4 = cVar.r();
        if (z) {
            cVar.m();
        }
        if (dR <= 1.0d && dR2 <= 1.0d && dR3 <= 1.0d && dR4 <= 1.0d) {
            dR *= 255.0d;
            dR2 *= 255.0d;
            dR3 *= 255.0d;
            dR4 *= 255.0d;
        }
        return Integer.valueOf(Color.argb((int) dR4, (int) dR, (int) dR2, (int) dR3));
    }
}
