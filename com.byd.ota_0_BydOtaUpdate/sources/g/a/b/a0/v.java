package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class v extends j {
    public v() {
    }

    public v(v vVar) {
        super(vVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        super.k((v) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new v(this);
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
        reset();
        return 48;
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "SHA-384";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 48;
    }

    @Override // g.a.b.a0.j, g.a.b.n
    public void reset() {
        super.reset();
        this.f4306f = -3766243637369397544L;
        this.f4307g = 7105036623409894663L;
        this.f4308h = -7973340178411365097L;
        this.f4309i = 1526699215303891257L;
        this.j = 7436329637833083697L;
        this.k = -8163818279084223215L;
        this.l = -2662702644619276377L;
        this.m = 5167115440072839076L;
    }
}
