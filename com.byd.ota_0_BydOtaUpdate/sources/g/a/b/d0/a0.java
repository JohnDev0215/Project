package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class a0 implements g.a.b.w {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4421f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f4416a = new int[1024];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f4417b = new int[1024];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4418c = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4422g = new byte[4];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4423h = 0;

    public static int c(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    public final byte a() {
        if (this.f4423h == 0) {
            int iD = d();
            byte[] bArr = this.f4422g;
            bArr[0] = (byte) (iD & 255);
            int i2 = iD >> 8;
            bArr[1] = (byte) (i2 & 255);
            int i3 = i2 >> 8;
            bArr[2] = (byte) (i3 & 255);
            bArr[3] = (byte) ((i3 >> 8) & 255);
        }
        byte[] bArr2 = this.f4422g;
        int i4 = this.f4423h;
        byte b2 = bArr2[i4];
        this.f4423h = 3 & (i4 + 1);
        return b2;
    }

    public final void b() {
        byte[] bArr = this.f4419d;
        if (bArr.length != 32 && bArr.length != 16) {
            throw new IllegalArgumentException("The key must be 128/256 bits long");
        }
        if (this.f4420e.length < 16) {
            throw new IllegalArgumentException("The IV must be at least 128 bits long");
        }
        if (bArr.length != 32) {
            byte[] bArr2 = new byte[32];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            byte[] bArr3 = this.f4419d;
            System.arraycopy(bArr3, 0, bArr2, 16, bArr3.length);
            this.f4419d = bArr2;
        }
        byte[] bArr4 = this.f4420e;
        if (bArr4.length < 32) {
            byte[] bArr5 = new byte[32];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
            byte[] bArr6 = this.f4420e;
            System.arraycopy(bArr6, 0, bArr5, bArr6.length, 32 - bArr6.length);
            this.f4420e = bArr5;
        }
        this.f4423h = 0;
        this.f4418c = 0;
        int[] iArr = new int[2560];
        for (int i2 = 0; i2 < 32; i2++) {
            int i3 = i2 >> 2;
            iArr[i3] = iArr[i3] | ((this.f4419d[i2] & 255) << ((i2 & 3) * 8));
        }
        for (int i4 = 0; i4 < 32; i4++) {
            int i5 = (i4 >> 2) + 8;
            iArr[i5] = iArr[i5] | ((this.f4420e[i4] & 255) << ((i4 & 3) * 8));
        }
        for (int i6 = 16; i6 < 2560; i6++) {
            int i7 = iArr[i6 - 2];
            int i8 = iArr[i6 - 15];
            iArr[i6] = ((i7 >>> 10) ^ (c(i7, 17) ^ c(i7, 19))) + iArr[i6 - 7] + ((i8 >>> 3) ^ (c(i8, 7) ^ c(i8, 18))) + iArr[i6 - 16] + i6;
        }
        System.arraycopy(iArr, 512, this.f4416a, 0, 1024);
        System.arraycopy(iArr, 1536, this.f4417b, 0, 1024);
        for (int i9 = 0; i9 < 4096; i9++) {
            d();
        }
        this.f4418c = 0;
    }

    public final int d() {
        int i2;
        int i3;
        int i4 = this.f4418c;
        int i5 = i4 & 1023;
        if (i4 < 1024) {
            int[] iArr = this.f4416a;
            int i6 = iArr[(i5 - 3) & 1023];
            int i7 = iArr[(i5 - 1023) & 1023];
            int i8 = iArr[i5];
            int iC = iArr[(i5 - 10) & 1023] + (c(i7, 23) ^ c(i6, 10));
            int[] iArr2 = this.f4417b;
            iArr[i5] = i8 + iC + iArr2[(i6 ^ i7) & 1023];
            int[] iArr3 = this.f4416a;
            int i9 = iArr3[(i5 - 12) & 1023];
            i2 = iArr2[i9 & 255] + iArr2[((i9 >> 8) & 255) + 256] + iArr2[((i9 >> 16) & 255) + 512] + iArr2[((i9 >> 24) & 255) + 768];
            i3 = iArr3[i5];
        } else {
            int[] iArr4 = this.f4417b;
            int i10 = iArr4[(i5 - 3) & 1023];
            int i11 = iArr4[(i5 - 1023) & 1023];
            int i12 = iArr4[i5];
            int iC2 = iArr4[(i5 - 10) & 1023] + (c(i11, 23) ^ c(i10, 10));
            int[] iArr5 = this.f4416a;
            iArr4[i5] = i12 + iC2 + iArr5[(i10 ^ i11) & 1023];
            int[] iArr6 = this.f4417b;
            int i13 = iArr6[(i5 - 12) & 1023];
            i2 = iArr5[i13 & 255] + iArr5[((i13 >> 8) & 255) + 256] + iArr5[((i13 >> 16) & 255) + 512] + iArr5[((i13 >> 24) & 255) + 768];
            i3 = iArr6[i5];
        }
        int i14 = i3 ^ i2;
        this.f4418c = (this.f4418c + 1) & 2047;
        return i14;
    }

    @Override // g.a.b.w
    public String getAlgorithmName() {
        return "HC-256";
    }

    @Override // g.a.b.w
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.i iVarB;
        if (iVar instanceof g.a.b.j0.w0) {
            g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
            this.f4420e = w0Var.a();
            iVarB = w0Var.b();
        } else {
            this.f4420e = new byte[0];
            iVarB = iVar;
        }
        if (iVarB instanceof g.a.b.j0.s0) {
            this.f4419d = ((g.a.b.j0.s0) iVarB).a();
            b();
            this.f4421f = true;
        } else {
            throw new IllegalArgumentException("Invalid parameter passed to HC256 init - " + iVar.getClass().getName());
        }
    }

    @Override // g.a.b.w
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException {
        if (!this.f4421f) {
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
        b();
    }
}
