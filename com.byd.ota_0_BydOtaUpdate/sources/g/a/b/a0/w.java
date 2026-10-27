package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class w extends i {
    public w() {
        this(256);
    }

    public w(int i2) {
        super(k(i2));
    }

    public w(w wVar) {
        super(wVar);
    }

    public static int k(int i2) {
        if (i2 == 224 || i2 == 256 || i2 == 384 || i2 == 512) {
            return i2;
        }
        throw new IllegalArgumentException("'bitLength' " + i2 + " not supported for SHA-3");
    }

    @Override // g.a.b.a0.i, g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        f(2, 2);
        return super.doFinal(bArr, i2);
    }

    @Override // g.a.b.a0.i, g.a.b.n
    public String getAlgorithmName() {
        return "SHA3-" + this.f4299f;
    }
}
