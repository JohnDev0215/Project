package y;

import E0.AbstractC0156f;
import E0.InterfaceC0162l;
import d8.C1134u;

/* JADX INFO: renamed from: y.N, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2693N extends g0.n implements InterfaceC0162l, E0.f0 {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public G.Q f23855y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f23856z;

    @Override // g0.n
    public final void B0() {
        G.Q q4 = this.f23855y;
        if (q4 != null) {
            q4.b();
        }
        this.f23855y = null;
    }

    @Override // E0.f0
    public final void L() {
        A7.z zVar = new A7.z();
        AbstractC0156f.q(this, new C1134u(zVar, 5, this));
        G.Q q4 = (G.Q) zVar.l;
        if (this.f23856z) {
            G.Q q10 = this.f23855y;
            if (q10 != null) {
                q10.b();
            }
            if (q4 != null) {
                q4.a();
            } else {
                q4 = null;
            }
            this.f23855y = q4;
        }
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
