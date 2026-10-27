package g0;

import C0.J;
import C0.L;
import C0.M;
import C0.V;
import E0.InterfaceC0174y;
import T.C0741g;
import h2.AbstractC1347d;
import n7.v;

/* JADX INFO: loaded from: classes.dex */
public final class s extends n implements InterfaceC0174y {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f14701y;

    @Override // E0.InterfaceC0174y
    public final L g(M m10, J j3, long j10) {
        V vB = j3.b(j10);
        return m10.m0(vB.l, vB.f871m, v.l, new C0741g(vB, 12, this));
    }

    public final String toString() {
        return AbstractC1347d.h(new StringBuilder("ZIndexModifier(zIndex="), this.f14701y, ')');
    }
}
