package g.a.b.a0;

import g.a.b.j0.g1;

/* JADX INFO: loaded from: classes.dex */
public class b0 implements g.a.b.p, g.a.f.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c0 f4226a;

    public b0(int i2, int i3) {
        this.f4226a = new c0(i2, i3);
        d(null);
    }

    public b0(b0 b0Var) {
        this.f4226a = new c0(b0Var.f4226a);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        this.f4226a.a(((b0) eVar).f4226a);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new b0(this);
    }

    public void d(g1 g1Var) {
        this.f4226a.j(g1Var);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        return this.f4226a.g(bArr, i2);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "Skein-" + (this.f4226a.h() * 8) + "-" + (this.f4226a.i() * 8);
    }

    @Override // g.a.b.p
    public int getByteLength() {
        return this.f4226a.h();
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return this.f4226a.i();
    }

    @Override // g.a.b.n
    public void reset() {
        this.f4226a.n();
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        this.f4226a.s(b2);
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        this.f4226a.t(bArr, i2, i3);
    }
}
