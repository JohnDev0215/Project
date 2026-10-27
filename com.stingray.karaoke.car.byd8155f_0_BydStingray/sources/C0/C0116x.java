package C0;

import E0.InterfaceC0174y;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: C0.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0116x extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2786o f920y;

    @Override // E0.InterfaceC0174y
    public final L g(M m10, J j3, long j10) {
        return (L) this.f920y.g(m10, j3, new a1.a(j10));
    }

    public final String toString() {
        return "LayoutModifierImpl(measureBlock=" + this.f920y + ')';
    }
}
