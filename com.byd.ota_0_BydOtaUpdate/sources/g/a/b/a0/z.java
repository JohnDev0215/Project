package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class z extends i implements g.a.b.y {
    public z(int i2) {
        super(k(i2));
    }

    public static int k(int i2) {
        if (i2 == 128 || i2 == 256) {
            return i2;
        }
        throw new IllegalArgumentException("'bitLength' " + i2 + " not supported for SHAKE");
    }

    @Override // g.a.b.y
    public int c(byte[] bArr, int i2, int i3) {
        int iL = l(bArr, i2, i3);
        reset();
        return iL;
    }

    @Override // g.a.b.a0.i, g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        return c(bArr, i2, getDigestSize());
    }

    @Override // g.a.b.a0.i, g.a.b.n
    public String getAlgorithmName() {
        return "SHAKE" + this.f4299f;
    }

    public int l(byte[] bArr, int i2, int i3) {
        if (!this.f4300g) {
            f(15, 4);
        }
        j(bArr, i2, ((long) i3) * 8);
        return i3;
    }
}
