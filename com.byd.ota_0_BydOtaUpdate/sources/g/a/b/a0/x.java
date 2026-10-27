package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class x extends j {
    public x() {
    }

    public x(x xVar) {
        super(xVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        k((x) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new x(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        l();
        g.a.f.f.q(this.f4306f, bArr, i2);
        g.a.f.f.q(this.f4307g, bArr, i2 + 8);
        g.a.f.f.q(this.f4308h, bArr, i2 + 16);
        g.a.f.f.q(this.f4309i, bArr, i2 + 24);
        g.a.f.f.q(this.j, bArr, i2 + 32);
        g.a.f.f.q(this.k, bArr, i2 + 40);
        g.a.f.f.q(this.l, bArr, i2 + 48);
        g.a.f.f.q(this.m, bArr, i2 + 56);
        reset();
        return 64;
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "SHA-512";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 64;
    }

    @Override // g.a.b.a0.j, g.a.b.n
    public void reset() {
        super.reset();
        this.f4306f = 7640891576956012808L;
        this.f4307g = -4942790177534073029L;
        this.f4308h = 4354685564936845355L;
        this.f4309i = -6534734903238641935L;
        this.j = 5840696475078001361L;
        this.k = -7276294671716946913L;
        this.l = 2270897969802886507L;
        this.m = 6620516959819538809L;
    }
}
