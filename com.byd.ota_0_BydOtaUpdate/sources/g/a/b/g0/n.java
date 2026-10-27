package g.a.b.g0;

import g.a.b.a0.c0;
import g.a.b.j0.g1;
import g.a.b.j0.s0;
import g.a.b.t;

/* JADX INFO: loaded from: classes.dex */
public class n implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c0 f4825a;

    public n(int i2, int i3) {
        this.f4825a = new c0(i2, i3);
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) {
        return this.f4825a.g(bArr, i2);
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return "Skein-MAC-" + (this.f4825a.h() * 8) + "-" + (this.f4825a.i() * 8);
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4825a.i();
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) throws IllegalArgumentException {
        g1 g1VarA;
        if (iVar instanceof g1) {
            g1VarA = (g1) iVar;
        } else {
            if (!(iVar instanceof s0)) {
                throw new IllegalArgumentException("Invalid parameter passed to Skein MAC init - " + iVar.getClass().getName());
            }
            g1VarA = new g1.b().c(((s0) iVar).a()).a();
        }
        if (g1VarA.a() == null) {
            throw new IllegalArgumentException("Skein MAC requires a key parameter.");
        }
        this.f4825a.j(g1VarA);
    }

    @Override // g.a.b.t
    public void reset() {
        this.f4825a.n();
    }

    @Override // g.a.b.t
    public void update(byte b2) {
        this.f4825a.s(b2);
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) {
        this.f4825a.t(bArr, i2, i3);
    }
}
