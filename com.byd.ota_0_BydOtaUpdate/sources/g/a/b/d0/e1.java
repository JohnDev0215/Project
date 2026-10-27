package g.a.b.d0;

/* JADX INFO: loaded from: classes.dex */
public class e1 extends t0 {
    @Override // g.a.b.d0.t0
    public int c() {
        return 24;
    }

    @Override // g.a.b.d0.t0, g.a.b.w
    public String getAlgorithmName() {
        return "XSalsa20";
    }

    @Override // g.a.b.d0.t0
    public void j(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new IllegalArgumentException(getAlgorithmName() + " doesn't support re-init with null key");
        }
        if (bArr.length != 32) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires a 256 bit key");
        }
        super.j(bArr, bArr2);
        g.a.f.f.l(bArr2, 8, this.f4605f, 8, 2);
        int[] iArr = this.f4605f;
        int[] iArr2 = new int[iArr.length];
        t0.i(20, iArr, iArr2);
        int[] iArr3 = this.f4605f;
        iArr3[1] = iArr2[0] - iArr3[0];
        iArr3[2] = iArr2[5] - iArr3[5];
        iArr3[3] = iArr2[10] - iArr3[10];
        iArr3[4] = iArr2[15] - iArr3[15];
        iArr3[11] = iArr2[6] - iArr3[6];
        iArr3[12] = iArr2[7] - iArr3[7];
        iArr3[13] = iArr2[8] - iArr3[8];
        iArr3[14] = iArr2[9] - iArr3[9];
        g.a.f.f.l(bArr2, 16, iArr3, 6, 2);
    }
}
