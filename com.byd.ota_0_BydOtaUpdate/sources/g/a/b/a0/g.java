package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class g extends e {
    public static final byte[] m = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public g() {
        super(m);
    }

    public g(g gVar) {
        super(m);
        a(gVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new g(this);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "GOST3411-2012-512";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 64;
    }
}
