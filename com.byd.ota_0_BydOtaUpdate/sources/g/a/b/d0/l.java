package g.a.b.d0;

/* JADX INFO: loaded from: classes.dex */
public class l extends t0 {
    @Override // g.a.b.d0.t0
    public void a() {
        int[] iArr = this.f4605f;
        int i2 = iArr[12] + 1;
        iArr[12] = i2;
        if (i2 == 0) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
    }

    @Override // g.a.b.d0.t0
    public void b(byte[] bArr) {
        m.k(this.f4603d, this.f4605f, this.f4606g);
        g.a.f.f.h(this.f4606g, bArr, 0);
    }

    @Override // g.a.b.d0.t0
    public int c() {
        return 12;
    }

    @Override // g.a.b.d0.t0
    public void f() {
        this.f4605f[12] = 0;
    }

    @Override // g.a.b.d0.t0, g.a.b.w
    public String getAlgorithmName() {
        return "ChaCha7539-" + this.f4603d;
    }

    @Override // g.a.b.d0.t0
    public void j(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 256 bit key");
            }
            e(bArr.length, this.f4605f, 0);
            g.a.f.f.l(bArr, 0, this.f4605f, 4, 8);
        }
        g.a.f.f.l(bArr2, 0, this.f4605f, 13, 3);
    }
}
