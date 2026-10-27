package n0;

import E0.InterfaceC0174y;
import T.C0741g;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: n0.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1885n extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2782k f19442y;

    public C1885n(InterfaceC2782k interfaceC2782k) {
        this.f19442y = interfaceC2782k;
    }

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        C0.V vB = j3.b(j10);
        return m10.m0(vB.l, vB.f871m, n7.v.l, new C0741g(vB, 18, this));
    }

    public final String toString() {
        return "BlockGraphicsLayerModifier(block=" + this.f19442y + ')';
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
