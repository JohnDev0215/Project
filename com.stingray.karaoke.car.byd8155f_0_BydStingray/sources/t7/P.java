package T7;

import F8.AbstractC0287w;
import Q7.InterfaceC0666t;
import o8.C2061f;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class P extends Q {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final m7.n f8821x;

    public P(InterfaceC0666t interfaceC0666t, Q q4, int i7, R7.h hVar, C2061f c2061f, AbstractC0287w abstractC0287w, boolean z6, boolean z10, boolean z11, AbstractC0287w abstractC0287w2, Q7.N n10, InterfaceC2772a interfaceC2772a) {
        super(interfaceC0666t, q4, i7, hVar, c2061f, abstractC0287w, z6, z10, z11, abstractC0287w2, n10);
        this.f8821x = h6.r.o(interfaceC2772a);
    }

    @Override // T7.Q
    public final Q c1(O7.g gVar, C2061f c2061f, int i7) {
        R7.h hVarK = k();
        A7.m.e(hVarK, "annotations");
        AbstractC0287w abstractC0287wB = b();
        A7.m.e(abstractC0287wB, "type");
        boolean zD1 = d1();
        Q7.O o = Q7.N.c;
        K7.K k10 = new K7.K(this, 21);
        return new P(gVar, null, i7, hVarK, c2061f, abstractC0287wB, zD1, this.f8824t, this.f8825u, this.f8826v, o, k10);
    }
}
