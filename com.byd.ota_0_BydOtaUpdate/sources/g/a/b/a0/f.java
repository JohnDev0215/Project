package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public final class f extends e {
    public static final byte[] m = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    public f() {
        super(m);
    }

    public f(f fVar) {
        super(m);
        a(fVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new f(this);
    }

    @Override // g.a.b.a0.e, g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[64];
        super.doFinal(bArr2, 0);
        System.arraycopy(bArr2, 32, bArr, i2, 32);
        return 32;
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "GOST3411-2012-256";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 32;
    }
}
