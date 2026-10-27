package g.a.e.d.a;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class a extends g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[][] f5700c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5701d;

    public a(int i2, int[][] iArr) {
        if (iArr[0].length != ((i2 + 31) >> 5)) {
            throw new ArithmeticException("Int array does not match given number of columns.");
        }
        this.f5712b = i2;
        this.f5711a = iArr.length;
        this.f5701d = iArr[0].length;
        int i3 = i2 & 31;
        int i4 = i3 == 0 ? -1 : (1 << i3) - 1;
        for (int i5 = 0; i5 < this.f5711a; i5++) {
            int[] iArr2 = iArr[i5];
            int i6 = this.f5701d - 1;
            iArr2[i6] = iArr2[i6] & i4;
        }
        this.f5700c = iArr;
    }

    public a(a aVar) {
        this.f5712b = aVar.a();
        this.f5711a = aVar.b();
        this.f5701d = aVar.f5701d;
        this.f5700c = new int[aVar.f5700c.length][];
        int i2 = 0;
        while (true) {
            int[][] iArr = this.f5700c;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = d.a(aVar.f5700c[i2]);
            i2++;
        }
    }

    public a(byte[] bArr) {
        if (bArr.length < 9) {
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        }
        this.f5711a = f.e(bArr, 0);
        int iE = f.e(bArr, 4);
        this.f5712b = iE;
        int i2 = this.f5711a;
        int i3 = ((iE + 7) >>> 3) * i2;
        if (i2 > 0) {
            int i4 = 8;
            if (i3 == bArr.length - 8) {
                int i5 = (iE + 31) >>> 5;
                this.f5701d = i5;
                this.f5700c = (int[][]) Array.newInstance((Class<?>) int.class, i2, i5);
                int i6 = this.f5712b;
                int i7 = i6 >> 5;
                int i8 = i6 & 31;
                for (int i9 = 0; i9 < this.f5711a; i9++) {
                    int i10 = 0;
                    while (i10 < i7) {
                        this.f5700c[i9][i10] = f.e(bArr, i4);
                        i10++;
                        i4 += 4;
                    }
                    int i11 = 0;
                    while (i11 < i8) {
                        int[] iArr = this.f5700c[i9];
                        iArr[i7] = ((bArr[i4] & 255) << i11) ^ iArr[i7];
                        i11 += 8;
                        i4++;
                    }
                }
                return;
            }
        }
        throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
    }

    public byte[] c() {
        int i2 = (this.f5712b + 7) >>> 3;
        int i3 = this.f5711a;
        int i4 = 8;
        byte[] bArr = new byte[(i2 * i3) + 8];
        f.a(i3, bArr, 0);
        f.a(this.f5712b, bArr, 4);
        int i5 = this.f5712b;
        int i6 = i5 >>> 5;
        int i7 = i5 & 31;
        for (int i8 = 0; i8 < this.f5711a; i8++) {
            int i9 = 0;
            while (i9 < i6) {
                f.a(this.f5700c[i8][i9], bArr, i4);
                i9++;
                i4 += 4;
            }
            int i10 = 0;
            while (i10 < i7) {
                bArr[i4] = (byte) ((this.f5700c[i8][i6] >>> i10) & 255);
                i10 += 8;
                i4++;
            }
        }
        return bArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f5711a != aVar.f5711a || this.f5712b != aVar.f5712b || this.f5701d != aVar.f5701d) {
            return false;
        }
        for (int i2 = 0; i2 < this.f5711a; i2++) {
            if (!d.b(this.f5700c[i2], aVar.f5700c[i2])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int iHashCode = (((this.f5711a * 31) + this.f5712b) * 31) + this.f5701d;
        for (int i2 = 0; i2 < this.f5711a; i2++) {
            iHashCode = (iHashCode * 31) + this.f5700c[i2].hashCode();
        }
        return iHashCode;
    }

    public String toString() {
        int i2 = this.f5712b & 31;
        int i3 = this.f5701d;
        if (i2 != 0) {
            i3--;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < this.f5711a; i4++) {
            stringBuffer.append(i4 + ": ");
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = this.f5700c[i4][i5];
                for (int i7 = 0; i7 < 32; i7++) {
                    if (((i6 >>> i7) & 1) == 0) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append('1');
                    }
                }
                stringBuffer.append(' ');
            }
            int i8 = this.f5700c[i4][this.f5701d - 1];
            for (int i9 = 0; i9 < i2; i9++) {
                if (((i8 >>> i9) & 1) == 0) {
                    stringBuffer.append('0');
                } else {
                    stringBuffer.append('1');
                }
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }
}
