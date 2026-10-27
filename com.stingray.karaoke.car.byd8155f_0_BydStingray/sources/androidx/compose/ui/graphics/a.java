package androidx.compose.ui.graphics;

import g0.o;
import n0.AbstractC1851A;
import n0.AbstractC1861K;
import n0.C1870U;
import n0.InterfaceC1866P;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final o a(o oVar, InterfaceC2782k interfaceC2782k) {
        return oVar.k(new BlockGraphicsLayerElement(interfaceC2782k));
    }

    public static o b(o oVar, float f10, float f11, float f12, float f13, float f14, long j3, InterfaceC1866P interfaceC1866P, boolean z6, int i7) {
        float f15 = (i7 & 1) != 0 ? 1.0f : f10;
        float f16 = (i7 & 2) != 0 ? 1.0f : f11;
        float f17 = (i7 & 4) != 0 ? 1.0f : f12;
        float f18 = (i7 & 32) != 0 ? 0.0f : f13;
        float f19 = (i7 & 256) != 0 ? 0.0f : f14;
        long j10 = (i7 & 1024) != 0 ? C1870U.f19422b : j3;
        InterfaceC1866P interfaceC1866P2 = (i7 & 2048) != 0 ? AbstractC1861K.f19381a : interfaceC1866P;
        boolean z10 = (i7 & 4096) != 0 ? false : z6;
        long j11 = AbstractC1851A.f19369a;
        return oVar.k(new GraphicsLayerElement(f15, f16, f17, 0.0f, 0.0f, f18, 0.0f, 0.0f, f19, 8.0f, j10, interfaceC1866P2, z10, j11, j11, 0));
    }
}
