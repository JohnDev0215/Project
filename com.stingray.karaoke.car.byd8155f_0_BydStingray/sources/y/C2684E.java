package y;

import E0.InterfaceC0165o;
import S8.AbstractC0727y;
import n0.C1891t;
import p0.C2071b;

/* JADX INFO: renamed from: y.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2684E extends g0.n implements InterfaceC0165o {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f23832A;
    public boolean B;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C.k f23833y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f23834z;

    public C2684E(C.k kVar) {
        this.f23833y = kVar;
    }

    @Override // E0.InterfaceC0165o
    public final void a(E0.H h10) {
        h10.a();
        boolean z6 = this.f23834z;
        C2071b c2071b = h10.l;
        if (z6) {
            h10.c0(C1891t.b(0.3f, C1891t.f19445b), 0L, (58 & 4) != 0 ? p0.d.s0(h10.e(), 0L) : c2071b.e(), (58 & 8) != 0 ? 1.0f : 0.0f, p0.g.f20641a, (58 & 32) != 0 ? null : null, (58 & 64) != 0 ? 3 : 0);
        } else if (this.f23832A || this.B) {
            h10.c0(C1891t.b(0.1f, C1891t.f19445b), 0L, (58 & 4) != 0 ? p0.d.s0(h10.e(), 0L) : c2071b.e(), (58 & 8) != 0 ? 1.0f : 0.0f, p0.g.f20641a, (58 & 32) != 0 ? null : null, (58 & 64) != 0 ? 3 : 0);
        }
    }

    @Override // g0.n
    public final void z0() {
        AbstractC0727y.w(v0(), null, null, new C2683D(this, null), 3);
    }
}
