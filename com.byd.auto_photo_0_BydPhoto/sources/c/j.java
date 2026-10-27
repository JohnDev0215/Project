package c;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
final class j extends d {
    final transient byte[][] f;
    final transient int[] g;

    j(a aVar, int i) {
        super(null);
        m.b(aVar.f627c, 0L, i);
        h hVar = aVar.f626b;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            int i5 = hVar.f641c;
            int i6 = hVar.f640b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            hVar = hVar.f;
        }
        this.f = new byte[i4][];
        this.g = new int[i4 * 2];
        h hVar2 = aVar.f626b;
        int i7 = 0;
        while (i2 < i) {
            byte[][] bArr = this.f;
            bArr[i7] = hVar2.f639a;
            int i8 = hVar2.f641c;
            int i9 = hVar2.f640b;
            i2 += i8 - i9;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.g;
            iArr[i7] = i2;
            iArr[bArr.length + i7] = i9;
            hVar2.d = true;
            i7++;
            hVar2 = hVar2.f;
        }
    }

    private int n(int i) {
        int iBinarySearch = Arrays.binarySearch(this.g, 0, this.f.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    private d p() {
        return new d(o());
    }

    @Override // c.d
    public byte d(int i) {
        m.b(this.g[this.f.length - 1], i, 1L);
        int iN = n(i);
        int i2 = iN == 0 ? 0 : this.g[iN - 1];
        int[] iArr = this.g;
        byte[][] bArr = this.f;
        return bArr[iN][(i - i2) + iArr[bArr.length + iN]];
    }

    @Override // c.d
    public String e() {
        return p().e();
    }

    @Override // c.d
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (dVar.j() == j() && h(0, dVar, 0, j())) {
                return true;
            }
        }
        return false;
    }

    @Override // c.d
    byte[] f() {
        return o();
    }

    @Override // c.d
    public boolean h(int i, d dVar, int i2, int i3) {
        if (i < 0 || i > j() - i3) {
            return false;
        }
        int iN = n(i);
        while (i3 > 0) {
            int i4 = iN == 0 ? 0 : this.g[iN - 1];
            int iMin = Math.min(i3, ((this.g[iN] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr = this.f;
            if (!dVar.i(i2, bArr[iN], (i - i4) + iArr[bArr.length + iN], iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iN++;
        }
        return true;
    }

    @Override // c.d
    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int length = this.f.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.f[i2];
            int[] iArr = this.g;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.d = i3;
        return i3;
    }

    @Override // c.d
    public boolean i(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > j() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iN = n(i);
        while (i3 > 0) {
            int i4 = iN == 0 ? 0 : this.g[iN - 1];
            int iMin = Math.min(i3, ((this.g[iN] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr2 = this.f;
            if (!m.a(bArr2[iN], (i - i4) + iArr[bArr2.length + iN], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iN++;
        }
        return true;
    }

    @Override // c.d
    public int j() {
        return this.g[this.f.length - 1];
    }

    @Override // c.d
    public d l(int i, int i2) {
        return p().l(i, i2);
    }

    @Override // c.d
    public String m() {
        return p().m();
    }

    public byte[] o() {
        int[] iArr = this.g;
        byte[][] bArr = this.f;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.g;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.f[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // c.d
    public String toString() {
        return p().toString();
    }
}
