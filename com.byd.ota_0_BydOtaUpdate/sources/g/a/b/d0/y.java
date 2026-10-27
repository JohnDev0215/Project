package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class y implements g.a.b.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4652b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4653c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f4654d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4655e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4656f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4657g = 2;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4658h = false;

    public final byte a() {
        if (this.f4657g > 1) {
            f();
            this.f4657g = 0;
        }
        byte[] bArr = this.f4653c;
        int i2 = this.f4657g;
        this.f4657g = i2 + 1;
        return bArr[i2];
    }

    public final int b() {
        int[] iArr = this.f4655e;
        int i2 = (iArr[0] >>> 1) | (iArr[1] << 15);
        int i3 = (iArr[0] >>> 2) | (iArr[1] << 14);
        int i4 = (iArr[0] >>> 4) | (iArr[1] << 12);
        int i5 = (iArr[0] >>> 10) | (iArr[1] << 6);
        int i6 = (iArr[1] >>> 15) | (iArr[2] << 1);
        int i7 = (iArr[2] >>> 11) | (iArr[3] << 5);
        int i8 = (iArr[3] >>> 8) | (iArr[4] << 8);
        int i9 = (iArr[4] << 1) | (iArr[3] >>> 15);
        int[] iArr2 = this.f4654d;
        int i10 = (iArr2[0] >>> 3) | (iArr2[1] << 13);
        int i11 = (iArr2[1] >>> 9) | (iArr2[2] << 7);
        int i12 = (iArr2[3] << 2) | (iArr2[2] >>> 14);
        int i13 = iArr2[4];
        int i14 = i12 & i13;
        int i15 = ((((i11 ^ i9) ^ (i10 & i13)) ^ i14) ^ (i13 & i9)) ^ ((i10 & i11) & i12);
        int i16 = i10 & i12;
        return (((((((((i9 & i14) ^ (((i16 & i9) ^ ((i13 & i16) ^ i15)) ^ ((i11 & i12) & i9))) ^ i2) ^ i3) ^ i4) ^ i5) ^ i6) ^ i7) ^ i8) & 65535;
    }

    public final int c() {
        int[] iArr = this.f4654d;
        int i2 = iArr[0];
        int i3 = (iArr[0] >>> 13) | (iArr[1] << 3);
        int i4 = (iArr[1] >>> 7) | (iArr[2] << 9);
        int i5 = (iArr[2] >>> 6) | (iArr[3] << 10);
        int i6 = (iArr[3] >>> 3) | (iArr[4] << 13);
        return (((iArr[4] << 2) | (iArr[3] >>> 14)) ^ ((((i3 ^ i2) ^ i4) ^ i5) ^ i6)) & 65535;
    }

    public final int d() {
        int[] iArr = this.f4655e;
        int i2 = iArr[0];
        int i3 = (iArr[0] >>> 9) | (iArr[1] << 7);
        int i4 = (iArr[0] >>> 14) | (iArr[1] << 2);
        int i5 = (iArr[0] >>> 15) | (iArr[1] << 1);
        int i6 = (iArr[1] >>> 5) | (iArr[2] << 11);
        int i7 = (iArr[1] >>> 12) | (iArr[2] << 4);
        int i8 = (iArr[2] >>> 1) | (iArr[3] << 15);
        int i9 = (iArr[2] >>> 5) | (iArr[3] << 11);
        int i10 = (iArr[2] >>> 13) | (iArr[3] << 3);
        int i11 = (iArr[3] >>> 4) | (iArr[4] << 12);
        int i12 = (iArr[3] >>> 12) | (iArr[4] << 4);
        int i13 = (iArr[3] >>> 14) | (iArr[4] << 2);
        int i14 = (iArr[4] << 1) | (iArr[3] >>> 15);
        int i15 = i14 & i12;
        int i16 = (((i2 ^ (((((((((i13 ^ i12) ^ i11) ^ i10) ^ i9) ^ i8) ^ i7) ^ i6) ^ i4) ^ i3)) ^ i15) ^ (i9 & i8)) ^ (i5 & i3);
        int i17 = i12 & i11;
        int i18 = i8 & i7 & i6;
        return (((((((((i14 & i10) & i7) & i3) ^ ((i16 ^ (i17 & i10)) ^ i18)) ^ ((i17 & i9) & i8)) ^ ((i15 & i6) & i5)) ^ (((i15 & i11) & i10) & i9)) ^ ((i5 & i18) & i3)) ^ (((((i11 & i10) & i9) & i8) & i7) & i6)) & 65535;
    }

    public final void e() {
        for (int i2 = 0; i2 < 10; i2++) {
            this.f4656f = b();
            this.f4655e = h(this.f4655e, (d() ^ this.f4654d[0]) ^ this.f4656f);
            this.f4654d = h(this.f4654d, c() ^ this.f4656f);
        }
        this.f4658h = true;
    }

    public final void f() {
        int iB = b();
        this.f4656f = iB;
        byte[] bArr = this.f4653c;
        bArr[0] = (byte) iB;
        bArr[1] = (byte) (iB >> 8);
        this.f4655e = h(this.f4655e, d() ^ this.f4654d[0]);
        this.f4654d = h(this.f4654d, c());
    }

    public final void g(byte[] bArr, byte[] bArr2) {
        bArr2[8] = -1;
        bArr2[9] = -1;
        this.f4651a = bArr;
        this.f4652b = bArr2;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.f4655e;
            if (i2 >= iArr.length) {
                return;
            }
            byte[] bArr3 = this.f4651a;
            int i4 = i3 + 1;
            iArr[i2] = ((bArr3[i3] & 255) | (bArr3[i4] << 8)) & 65535;
            int[] iArr2 = this.f4654d;
            byte[] bArr4 = this.f4652b;
            iArr2[i2] = ((bArr4[i3] & 255) | (bArr4[i4] << 8)) & 65535;
            i3 += 2;
            i2++;
        }
    }

    @Override // g.a.b.w
    public String getAlgorithmName() {
        return "Grain v1";
    }

    public final int[] h(int[] iArr, int i2) {
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = i2;
        return iArr;
    }

    @Override // g.a.b.w
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof g.a.b.j0.w0)) {
            throw new IllegalArgumentException("Grain v1 Init parameters must include an IV");
        }
        g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
        byte[] bArrA = w0Var.a();
        if (bArrA == null || bArrA.length != 8) {
            throw new IllegalArgumentException("Grain v1 requires exactly 8 bytes of IV");
        }
        if (!(w0Var.b() instanceof g.a.b.j0.s0)) {
            throw new IllegalArgumentException("Grain v1 Init parameters must include a key");
        }
        g.a.b.j0.s0 s0Var = (g.a.b.j0.s0) w0Var.b();
        this.f4652b = new byte[s0Var.a().length];
        this.f4651a = new byte[s0Var.a().length];
        this.f4654d = new int[5];
        this.f4655e = new int[5];
        this.f4653c = new byte[2];
        System.arraycopy(bArrA, 0, this.f4652b, 0, bArrA.length);
        System.arraycopy(s0Var.a(), 0, this.f4651a, 0, s0Var.a().length);
        reset();
    }

    @Override // g.a.b.w
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException {
        if (!this.f4658h) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i4 + i5] = (byte) (bArr[i2 + i5] ^ a());
        }
        return i3;
    }

    @Override // g.a.b.w
    public void reset() {
        this.f4657g = 2;
        g(this.f4651a, this.f4652b);
        e();
    }
}
