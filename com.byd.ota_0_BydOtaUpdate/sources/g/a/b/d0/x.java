package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class x implements g.a.b.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4637a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4638b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4639c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f4640d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4641e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4642f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4643g = 4;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4644h = false;

    public final byte a() {
        if (this.f4643g > 3) {
            f();
            this.f4643g = 0;
        }
        byte[] bArr = this.f4639c;
        int i2 = this.f4643g;
        this.f4643g = i2 + 1;
        return bArr[i2];
    }

    public final int b() {
        int[] iArr = this.f4641e;
        int i2 = (iArr[0] >>> 2) | (iArr[1] << 30);
        int i3 = (iArr[0] >>> 12) | (iArr[1] << 20);
        int i4 = (iArr[0] >>> 15) | (iArr[1] << 17);
        int i5 = (iArr[1] >>> 4) | (iArr[2] << 28);
        int i6 = (iArr[1] >>> 13) | (iArr[2] << 19);
        int i7 = iArr[2];
        int i8 = (iArr[2] >>> 9) | (iArr[3] << 23);
        int i9 = (iArr[2] >>> 25) | (iArr[3] << 7);
        int i10 = (iArr[3] << 1) | (iArr[2] >>> 31);
        int[] iArr2 = this.f4640d;
        int i11 = (iArr2[0] >>> 8) | (iArr2[1] << 24);
        int i12 = (iArr2[0] >>> 13) | (iArr2[1] << 19);
        int i13 = (iArr2[0] >>> 20) | (iArr2[1] << 12);
        int i14 = (iArr2[1] >>> 10) | (iArr2[2] << 22);
        int i15 = (iArr2[1] >>> 28) | (iArr2[2] << 4);
        int i16 = (iArr2[2] >>> 15) | (iArr2[3] << 17);
        return ((((((((((i10 & i3) & ((iArr2[2] >>> 31) | (iArr2[3] << 1))) ^ ((((i12 & i13) ^ (i3 & i11)) ^ (i10 & i14)) ^ (i15 & i16))) ^ ((iArr2[2] >>> 29) | (iArr2[3] << 3))) ^ i2) ^ i4) ^ i5) ^ i6) ^ i7) ^ i8) ^ i9;
    }

    public final int c() {
        int[] iArr = this.f4640d;
        int i2 = iArr[0];
        int i3 = (iArr[0] >>> 7) | (iArr[1] << 25);
        int i4 = (iArr[1] >>> 6) | (iArr[2] << 26);
        int i5 = (iArr[2] >>> 6) | (iArr[3] << 26);
        return iArr[3] ^ ((((i3 ^ i2) ^ i4) ^ i5) ^ ((iArr[2] >>> 17) | (iArr[3] << 15)));
    }

    public final int d() {
        int[] iArr = this.f4641e;
        int i2 = iArr[0];
        int i3 = (iArr[0] >>> 3) | (iArr[1] << 29);
        int i4 = (iArr[0] >>> 11) | (iArr[1] << 21);
        int i5 = (iArr[0] >>> 13) | (iArr[1] << 19);
        int i6 = (iArr[0] >>> 17) | (iArr[1] << 15);
        int i7 = (iArr[0] >>> 18) | (iArr[1] << 14);
        int i8 = (iArr[0] >>> 26) | (iArr[1] << 6);
        int i9 = (iArr[0] >>> 27) | (iArr[1] << 5);
        int i10 = (iArr[1] >>> 8) | (iArr[2] << 24);
        int i11 = (iArr[1] >>> 16) | (iArr[2] << 16);
        int i12 = (iArr[1] >>> 24) | (iArr[2] << 8);
        int i13 = (iArr[1] >>> 27) | (iArr[2] << 5);
        int i14 = (iArr[1] >>> 29) | (iArr[2] << 3);
        int i15 = (iArr[2] >>> 1) | (iArr[3] << 31);
        int i16 = (iArr[2] >>> 3) | (iArr[3] << 29);
        int i17 = (iArr[2] >>> 4) | (iArr[3] << 28);
        int i18 = (iArr[2] >>> 20) | (iArr[3] << 12);
        return (((((((iArr[3] ^ (((i2 ^ i8) ^ i12) ^ ((iArr[2] >>> 27) | (iArr[3] << 5)))) ^ (i3 & i16)) ^ (i4 & i5)) ^ (i6 & i7)) ^ (i9 & i13)) ^ (i10 & i11)) ^ (i14 & i15)) ^ (i17 & i18);
    }

    public final void e() {
        for (int i2 = 0; i2 < 8; i2++) {
            this.f4642f = b();
            this.f4641e = h(this.f4641e, (d() ^ this.f4640d[0]) ^ this.f4642f);
            this.f4640d = h(this.f4640d, c() ^ this.f4642f);
        }
        this.f4644h = true;
    }

    public final void f() {
        int iB = b();
        this.f4642f = iB;
        byte[] bArr = this.f4639c;
        bArr[0] = (byte) iB;
        bArr[1] = (byte) (iB >> 8);
        bArr[2] = (byte) (iB >> 16);
        bArr[3] = (byte) (iB >> 24);
        this.f4641e = h(this.f4641e, d() ^ this.f4640d[0]);
        this.f4640d = h(this.f4640d, c());
    }

    public final void g(byte[] bArr, byte[] bArr2) {
        bArr2[12] = -1;
        bArr2[13] = -1;
        bArr2[14] = -1;
        bArr2[15] = -1;
        this.f4637a = bArr;
        this.f4638b = bArr2;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.f4641e;
            if (i2 >= iArr.length) {
                return;
            }
            byte[] bArr3 = this.f4637a;
            int i4 = i3 + 3;
            int i5 = i3 + 2;
            int i6 = i3 + 1;
            iArr[i2] = (bArr3[i3] & 255) | (bArr3[i4] << 24) | ((bArr3[i5] << 16) & 16711680) | ((bArr3[i6] << 8) & 65280);
            int[] iArr2 = this.f4640d;
            byte[] bArr4 = this.f4638b;
            iArr2[i2] = (bArr4[i3] & 255) | (bArr4[i4] << 24) | ((bArr4[i5] << 16) & 16711680) | ((bArr4[i6] << 8) & 65280);
            i3 += 4;
            i2++;
        }
    }

    @Override // g.a.b.w
    public String getAlgorithmName() {
        return "Grain-128";
    }

    public final int[] h(int[] iArr, int i2) {
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = i2;
        return iArr;
    }

    @Override // g.a.b.w
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof g.a.b.j0.w0)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include an IV");
        }
        g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
        byte[] bArrA = w0Var.a();
        if (bArrA == null || bArrA.length != 12) {
            throw new IllegalArgumentException("Grain-128  requires exactly 12 bytes of IV");
        }
        if (!(w0Var.b() instanceof g.a.b.j0.s0)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include a key");
        }
        g.a.b.j0.s0 s0Var = (g.a.b.j0.s0) w0Var.b();
        this.f4638b = new byte[s0Var.a().length];
        this.f4637a = new byte[s0Var.a().length];
        this.f4640d = new int[4];
        this.f4641e = new int[4];
        this.f4639c = new byte[4];
        System.arraycopy(bArrA, 0, this.f4638b, 0, bArrA.length);
        System.arraycopy(s0Var.a(), 0, this.f4637a, 0, s0Var.a().length);
        reset();
    }

    @Override // g.a.b.w
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException {
        if (!this.f4644h) {
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
        this.f4643g = 4;
        g(this.f4637a, this.f4638b);
        e();
    }
}
