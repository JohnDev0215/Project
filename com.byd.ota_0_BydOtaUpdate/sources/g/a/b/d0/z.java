package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class z implements g.a.b.w {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4668d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4669e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4670f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f4665a = new int[512];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f4666b = new int[512];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4667c = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4671g = new byte[4];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4672h = 0;

    public static int a(int i2, int i3) {
        return k(i2 - i3);
    }

    public static int b(int i2) {
        return (i2 >>> 3) ^ (m(i2, 7) ^ m(i2, 18));
    }

    public static int c(int i2) {
        return (i2 >>> 10) ^ (m(i2, 17) ^ m(i2, 19));
    }

    public static int j(int i2) {
        return i2 & 1023;
    }

    public static int k(int i2) {
        return i2 & 511;
    }

    public static int l(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public static int m(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    public final int d(int i2, int i3, int i4) {
        return (m(i2, 10) ^ m(i4, 23)) + m(i3, 8);
    }

    public final int e(int i2, int i3, int i4) {
        return (l(i2, 10) ^ l(i4, 23)) + l(i3, 8);
    }

    public final byte f() {
        if (this.f4672h == 0) {
            int iN = n();
            byte[] bArr = this.f4671g;
            bArr[0] = (byte) (iN & 255);
            int i2 = iN >> 8;
            bArr[1] = (byte) (i2 & 255);
            int i3 = i2 >> 8;
            bArr[2] = (byte) (i3 & 255);
            bArr[3] = (byte) ((i3 >> 8) & 255);
        }
        byte[] bArr2 = this.f4671g;
        int i4 = this.f4672h;
        byte b2 = bArr2[i4];
        this.f4672h = 3 & (i4 + 1);
        return b2;
    }

    public final int g(int i2) {
        int[] iArr = this.f4666b;
        return iArr[i2 & 255] + iArr[((i2 >> 16) & 255) + 256];
    }

    @Override // g.a.b.w
    public String getAlgorithmName() {
        return "HC-128";
    }

    public final int h(int i2) {
        int[] iArr = this.f4665a;
        return iArr[i2 & 255] + iArr[((i2 >> 16) & 255) + 256];
    }

    public final void i() {
        if (this.f4668d.length != 16) {
            throw new IllegalArgumentException("The key must be 128 bits long");
        }
        this.f4672h = 0;
        this.f4667c = 0;
        int[] iArr = new int[1280];
        for (int i2 = 0; i2 < 16; i2++) {
            int i3 = i2 >> 2;
            iArr[i3] = ((this.f4668d[i2] & 255) << ((i2 & 3) * 8)) | iArr[i3];
        }
        System.arraycopy(iArr, 0, iArr, 4, 4);
        int i4 = 0;
        while (true) {
            byte[] bArr = this.f4669e;
            if (i4 >= bArr.length || i4 >= 16) {
                break;
            }
            int i5 = (i4 >> 2) + 8;
            iArr[i5] = ((bArr[i4] & 255) << ((i4 & 3) * 8)) | iArr[i5];
            i4++;
        }
        System.arraycopy(iArr, 8, iArr, 12, 4);
        for (int i6 = 16; i6 < 1280; i6++) {
            iArr[i6] = c(iArr[i6 - 2]) + iArr[i6 - 7] + b(iArr[i6 - 15]) + iArr[i6 - 16] + i6;
        }
        System.arraycopy(iArr, 256, this.f4665a, 0, 512);
        System.arraycopy(iArr, 768, this.f4666b, 0, 512);
        for (int i7 = 0; i7 < 512; i7++) {
            this.f4665a[i7] = n();
        }
        for (int i8 = 0; i8 < 512; i8++) {
            this.f4666b[i8] = n();
        }
        this.f4667c = 0;
    }

    @Override // g.a.b.w
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.i iVarB;
        if (iVar instanceof g.a.b.j0.w0) {
            g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
            this.f4669e = w0Var.a();
            iVarB = w0Var.b();
        } else {
            this.f4669e = new byte[0];
            iVarB = iVar;
        }
        if (iVarB instanceof g.a.b.j0.s0) {
            this.f4668d = ((g.a.b.j0.s0) iVarB).a();
            i();
            this.f4670f = true;
        } else {
            throw new IllegalArgumentException("Invalid parameter passed to HC128 init - " + iVar.getClass().getName());
        }
    }

    public final int n() {
        int iH;
        int i2;
        int iK = k(this.f4667c);
        if (this.f4667c < 512) {
            int[] iArr = this.f4665a;
            iArr[iK] = iArr[iK] + d(iArr[a(iK, 3)], this.f4665a[a(iK, 10)], this.f4665a[a(iK, 511)]);
            iH = g(this.f4665a[a(iK, 12)]);
            i2 = this.f4665a[iK];
        } else {
            int[] iArr2 = this.f4666b;
            iArr2[iK] = iArr2[iK] + e(iArr2[a(iK, 3)], this.f4666b[a(iK, 10)], this.f4666b[a(iK, 511)]);
            iH = h(this.f4666b[a(iK, 12)]);
            i2 = this.f4666b[iK];
        }
        int i3 = i2 ^ iH;
        this.f4667c = j(this.f4667c + 1);
        return i3;
    }

    @Override // g.a.b.w
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException {
        if (!this.f4670f) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i4 + i5] = (byte) (bArr[i2 + i5] ^ f());
        }
        return i3;
    }

    @Override // g.a.b.w
    public void reset() {
        i();
    }
}
