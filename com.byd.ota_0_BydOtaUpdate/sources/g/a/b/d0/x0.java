package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class x0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static short[] f4645a = {163, 215, 9, 131, 248, 72, 246, 244, 179, 33, 21, 120, 153, 177, 175, 249, 231, 45, 77, 138, 206, 76, 202, 46, 82, 149, 217, 30, 78, 56, 68, 40, 10, 223, 2, 160, 23, 241, 96, 104, 18, 183, 122, 195, 233, 250, 61, 83, 150, 132, 107, 186, 242, 99, 154, 25, 124, 174, 229, 245, 247, 22, 106, 162, 57, 182, 123, 15, 193, 147, 129, 27, 238, 180, 26, 234, 208, 145, 47, 184, 85, 185, 218, 133, 63, 65, 191, 224, 90, 88, 128, 95, 102, 11, 216, 144, 53, 213, 192, 167, 51, 6, 101, 105, 69, 0, 148, 86, 109, 152, 155, 118, 151, 252, 178, 194, 176, 254, 219, 32, 225, 235, 214, 228, 221, 71, 74, 29, 66, 237, 158, 110, 73, 60, 205, 67, 39, 210, 7, 212, 222, 199, 103, 24, 137, 203, 48, 31, 141, 198, 143, 170, 200, 116, 220, 201, 93, 92, 49, 164, 112, 136, 97, 44, 159, 13, 43, 135, 80, 130, 84, 100, 38, 125, 3, 64, 52, 75, 28, 115, 209, 196, 253, 59, 204, 251, 127, 171, 230, 62, 91, 165, 173, 4, 35, 156, 20, 81, 34, 240, 41, 121, 113, 126, 255, 140, 14, 226, 12, 239, 188, 114, 117, 111, 55, 161, 236, 211, 142, 98, 139, 134, 16, 232, 8, 119, 17, 190, 146, 79, 36, 197, 50, 54, 157, 207, 243, 166, 187, 172, 94, 108, 169, 19, 87, 37, 181, 227, 189, 168, 58, 1, 5, 89, 42, 70};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f4646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f4647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f4648d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4649e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4650f;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.f4647c == null) {
            throw new IllegalStateException("SKIPJACK engine not initialised");
        }
        if (i2 + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.f4650f) {
            d(bArr, i2, bArr2, i3);
            return 8;
        }
        c(bArr, i2, bArr2, i3);
        return 8;
    }

    @Override // g.a.b.e
    public int b() {
        return 8;
    }

    public int c(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = (bArr[i2 + 0] << 8) + (bArr[i2 + 1] & 255);
        int i5 = (bArr[i2 + 2] << 8) + (bArr[i2 + 3] & 255);
        int i6 = (bArr[i2 + 4] << 8) + (bArr[i2 + 5] & 255);
        int i7 = (bArr[i2 + 6] << 8) + (bArr[i2 + 7] & 255);
        int i8 = 31;
        for (int i9 = 0; i9 < 2; i9++) {
            int i10 = 0;
            while (i10 < 8) {
                int iF = f(i8, i5);
                int i11 = (i6 ^ iF) ^ (i8 + 1);
                i8--;
                i10++;
                int i12 = i7;
                i7 = i4;
                i4 = iF;
                i5 = i11;
                i6 = i12;
            }
            int i13 = 0;
            while (i13 < 8) {
                int i14 = (i4 ^ i5) ^ (i8 + 1);
                int iF2 = f(i8, i5);
                i8--;
                i13++;
                int i15 = i7;
                i7 = i14;
                i4 = iF2;
                i5 = i6;
                i6 = i15;
            }
        }
        bArr2[i3 + 0] = (byte) (i4 >> 8);
        bArr2[i3 + 1] = (byte) i4;
        bArr2[i3 + 2] = (byte) (i5 >> 8);
        bArr2[i3 + 3] = (byte) i5;
        bArr2[i3 + 4] = (byte) (i6 >> 8);
        bArr2[i3 + 5] = (byte) i6;
        bArr2[i3 + 6] = (byte) (i7 >> 8);
        bArr2[i3 + 7] = (byte) i7;
        return 8;
    }

    public int d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = (bArr[i2 + 0] << 8) + (bArr[i2 + 1] & 255);
        int i5 = (bArr[i2 + 2] << 8) + (bArr[i2 + 3] & 255);
        int i6 = (bArr[i2 + 4] << 8) + (bArr[i2 + 5] & 255);
        int i7 = (bArr[i2 + 6] << 8) + (bArr[i2 + 7] & 255);
        int i8 = 0;
        for (int i9 = 0; i9 < 2; i9++) {
            int i10 = 0;
            while (i10 < 8) {
                int iE = e(i8, i4);
                i8++;
                i10++;
                int i11 = i5;
                i5 = iE;
                i4 = (i7 ^ iE) ^ i8;
                i7 = i6;
                i6 = i11;
            }
            int i12 = 0;
            while (i12 < 8) {
                int i13 = i8 + 1;
                int i14 = (i5 ^ i4) ^ i13;
                int iE2 = e(i8, i4);
                i12++;
                i8 = i13;
                i5 = iE2;
                i4 = i7;
                i7 = i6;
                i6 = i14;
            }
        }
        bArr2[i3 + 0] = (byte) (i4 >> 8);
        bArr2[i3 + 1] = (byte) i4;
        bArr2[i3 + 2] = (byte) (i5 >> 8);
        bArr2[i3 + 3] = (byte) i5;
        bArr2[i3 + 4] = (byte) (i6 >> 8);
        bArr2[i3 + 5] = (byte) i6;
        bArr2[i3 + 6] = (byte) (i7 >> 8);
        bArr2[i3 + 7] = (byte) i7;
        return 8;
    }

    public final int e(int i2, int i3) {
        int i4 = (i3 >> 8) & 255;
        int i5 = i3 & 255;
        short[] sArr = f4645a;
        int i6 = i4 ^ sArr[this.f4646b[i2] ^ i5];
        int i7 = i5 ^ sArr[this.f4647c[i2] ^ i6];
        int i8 = i6 ^ sArr[this.f4648d[i2] ^ i7];
        return (i8 << 8) + (sArr[this.f4649e[i2] ^ i8] ^ i7);
    }

    public final int f(int i2, int i3) {
        int i4 = i3 & 255;
        int i5 = (i3 >> 8) & 255;
        short[] sArr = f4645a;
        int i6 = i4 ^ sArr[this.f4649e[i2] ^ i5];
        int i7 = i5 ^ sArr[this.f4648d[i2] ^ i6];
        int i8 = i6 ^ sArr[this.f4647c[i2] ^ i7];
        return ((sArr[this.f4646b[i2] ^ i8] ^ i7) << 8) + i8;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "SKIPJACK";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (!(iVar instanceof g.a.b.j0.s0)) {
            throw new IllegalArgumentException("invalid parameter passed to SKIPJACK init - " + iVar.getClass().getName());
        }
        byte[] bArrA = ((g.a.b.j0.s0) iVar).a();
        this.f4650f = z;
        this.f4646b = new int[32];
        this.f4647c = new int[32];
        this.f4648d = new int[32];
        this.f4649e = new int[32];
        for (int i2 = 0; i2 < 32; i2++) {
            int i3 = i2 * 4;
            this.f4646b[i2] = bArrA[i3 % 10] & 255;
            this.f4647c[i2] = bArrA[(i3 + 1) % 10] & 255;
            this.f4648d[i2] = bArrA[(i3 + 2) % 10] & 255;
            this.f4649e[i2] = bArrA[(i3 + 3) % 10] & 255;
        }
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
