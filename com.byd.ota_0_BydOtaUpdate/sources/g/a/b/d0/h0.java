package g.a.b.d0;

/* JADX INFO: loaded from: classes.dex */
public class h0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4500a = 12;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f4501b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4502c;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        return this.f4502c ? e(bArr, i2, bArr2, i3) : d(bArr, i2, bArr2, i3);
    }

    @Override // g.a.b.e
    public int b() {
        return 8;
    }

    public final int c(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iC = c(bArr, i2);
        int iC2 = c(bArr, i2 + 4);
        for (int i4 = this.f4500a; i4 >= 1; i4--) {
            int i5 = i4 * 2;
            iC2 = g(iC2 - this.f4501b[i5 + 1], iC) ^ iC;
            iC = g(iC - this.f4501b[i5], iC2) ^ iC2;
        }
        i(iC - this.f4501b[0], bArr2, i3);
        i(iC2 - this.f4501b[1], bArr2, i3 + 4);
        return 8;
    }

    public final int e(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iC = c(bArr, i2) + this.f4501b[0];
        int iC2 = c(bArr, i2 + 4) + this.f4501b[1];
        for (int i4 = 1; i4 <= this.f4500a; i4++) {
            int i5 = i4 * 2;
            iC = f(iC ^ iC2, iC2) + this.f4501b[i5];
            iC2 = f(iC2 ^ iC, iC) + this.f4501b[i5 + 1];
        }
        i(iC, bArr2, i3);
        i(iC2, bArr2, i3 + 4);
        return 8;
    }

    public final int f(int i2, int i3) {
        int i4 = i3 & 31;
        return (i2 >>> (32 - i4)) | (i2 << i4);
    }

    public final int g(int i2, int i3) {
        int i4 = i3 & 31;
        return (i2 << (32 - i4)) | (i2 >>> i4);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "RC5-32";
    }

    public final void h(byte[] bArr) {
        int[] iArr;
        int length = (bArr.length + 3) / 4;
        int[] iArr2 = new int[length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            int i3 = i2 / 4;
            iArr2[i3] = iArr2[i3] + ((bArr[i2] & 255) << ((i2 % 4) * 8));
        }
        int[] iArr3 = new int[(this.f4500a + 1) * 2];
        this.f4501b = iArr3;
        iArr3[0] = -1209970333;
        int i4 = 1;
        while (true) {
            iArr = this.f4501b;
            if (i4 >= iArr.length) {
                break;
            }
            iArr[i4] = iArr[i4 - 1] - 1640531527;
            i4++;
        }
        int length2 = length > iArr.length ? length * 3 : iArr.length * 3;
        int length3 = 0;
        int iF = 0;
        int iF2 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length2; i6++) {
            int[] iArr4 = this.f4501b;
            iF = f(iArr4[length3] + iF + iF2, 3);
            iArr4[length3] = iF;
            iF2 = f(iArr2[i5] + iF + iF2, iF2 + iF);
            iArr2[i5] = iF2;
            length3 = (length3 + 1) % this.f4501b.length;
            i5 = (i5 + 1) % length;
        }
    }

    public final void i(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 3] = (byte) (i2 >> 24);
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.b1) {
            g.a.b.j0.b1 b1Var = (g.a.b.j0.b1) iVar;
            this.f4500a = b1Var.b();
            h(b1Var.a());
        } else {
            if (!(iVar instanceof g.a.b.j0.s0)) {
                throw new IllegalArgumentException("invalid parameter passed to RC532 init - " + iVar.getClass().getName());
            }
            h(((g.a.b.j0.s0) iVar).a());
        }
        this.f4502c = z;
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
