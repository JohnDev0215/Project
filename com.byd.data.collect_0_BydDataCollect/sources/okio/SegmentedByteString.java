package okio;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class SegmentedByteString extends ByteString {

    /* JADX INFO: renamed from: f */
    final transient byte[][] f5851f;

    /* JADX INFO: renamed from: g */
    final transient int[] f5852g;

    SegmentedByteString(Buffer buffer, int i2) {
        super(null);
        Util.b(buffer.f5771d, 0L, i2);
        Segment segment = buffer.f5770c;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = segment.f5844c;
            int i7 = segment.f5843b;
            if (i6 == i7) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += i6 - i7;
            i5++;
            segment = segment.f5847f;
        }
        this.f5851f = new byte[i5][];
        this.f5852g = new int[i5 * 2];
        Segment segment2 = buffer.f5770c;
        int i8 = 0;
        while (i3 < i2) {
            byte[][] bArr = this.f5851f;
            bArr[i8] = segment2.f5842a;
            int i9 = segment2.f5844c;
            int i10 = segment2.f5843b;
            i3 += i9 - i10;
            if (i3 > i2) {
                i3 = i2;
            }
            int[] iArr = this.f5852g;
            iArr[i8] = i3;
            iArr[bArr.length + i8] = i10;
            segment2.f5845d = true;
            i8++;
            segment2 = segment2.f5847f;
        }
    }

    private ByteString A() {
        return new ByteString(w());
    }

    private Object writeReplace() {
        return A();
    }

    private int z(int i2) {
        int iBinarySearch = Arrays.binarySearch(this.f5852g, 0, this.f5851f.length, i2 + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    @Override // okio.ByteString
    public String a() {
        return A().a();
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.s() == s() && n(0, byteString, 0, s())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i2 = this.f5782d;
        if (i2 != 0) {
            return i2;
        }
        int length = this.f5851f.length;
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        while (i3 < length) {
            byte[] bArr = this.f5851f[i3];
            int[] iArr = this.f5852g;
            int i6 = iArr[length + i3];
            int i7 = iArr[i3];
            int i8 = (i7 - i5) + i6;
            while (i6 < i8) {
                i4 = (i4 * 31) + bArr[i6];
                i6++;
            }
            i3++;
            i5 = i7;
        }
        this.f5782d = i4;
        return i4;
    }

    @Override // okio.ByteString
    public byte j(int i2) {
        Util.b(this.f5852g[this.f5851f.length - 1], i2, 1L);
        int iZ = z(i2);
        int i3 = iZ == 0 ? 0 : this.f5852g[iZ - 1];
        int[] iArr = this.f5852g;
        byte[][] bArr = this.f5851f;
        return bArr[iZ][(i2 - i3) + iArr[bArr.length + iZ]];
    }

    @Override // okio.ByteString
    public String k() {
        return A().k();
    }

    @Override // okio.ByteString
    public boolean n(int i2, ByteString byteString, int i3, int i4) {
        if (i2 < 0 || i2 > s() - i4) {
            return false;
        }
        int iZ = z(i2);
        while (i4 > 0) {
            int i5 = iZ == 0 ? 0 : this.f5852g[iZ - 1];
            int iMin = Math.min(i4, ((this.f5852g[iZ] - i5) + i5) - i2);
            int[] iArr = this.f5852g;
            byte[][] bArr = this.f5851f;
            if (!byteString.o(i3, bArr[iZ], (i2 - i5) + iArr[bArr.length + iZ], iMin)) {
                return false;
            }
            i2 += iMin;
            i3 += iMin;
            i4 -= iMin;
            iZ++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean o(int i2, byte[] bArr, int i3, int i4) {
        if (i2 < 0 || i2 > s() - i4 || i3 < 0 || i3 > bArr.length - i4) {
            return false;
        }
        int iZ = z(i2);
        while (i4 > 0) {
            int i5 = iZ == 0 ? 0 : this.f5852g[iZ - 1];
            int iMin = Math.min(i4, ((this.f5852g[iZ] - i5) + i5) - i2);
            int[] iArr = this.f5852g;
            byte[][] bArr2 = this.f5851f;
            if (!Util.a(bArr2[iZ], (i2 - i5) + iArr[bArr2.length + iZ], bArr, i3, iMin)) {
                return false;
            }
            i2 += iMin;
            i3 += iMin;
            i4 -= iMin;
            iZ++;
        }
        return true;
    }

    @Override // okio.ByteString
    public ByteString q() {
        return A().q();
    }

    @Override // okio.ByteString
    public ByteString r() {
        return A().r();
    }

    @Override // okio.ByteString
    public int s() {
        return this.f5852g[this.f5851f.length - 1];
    }

    @Override // okio.ByteString
    public String toString() {
        return A().toString();
    }

    @Override // okio.ByteString
    public ByteString u(int i2, int i3) {
        return A().u(i2, i3);
    }

    @Override // okio.ByteString
    public ByteString v() {
        return A().v();
    }

    @Override // okio.ByteString
    public byte[] w() {
        int[] iArr = this.f5852g;
        byte[][] bArr = this.f5851f;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] iArr2 = this.f5852g;
            int i4 = iArr2[length + i2];
            int i5 = iArr2[i2];
            System.arraycopy(this.f5851f[i2], i4, bArr2, i3, i5 - i3);
            i2++;
            i3 = i5;
        }
        return bArr2;
    }

    @Override // okio.ByteString
    public String x() {
        return A().x();
    }

    @Override // okio.ByteString
    void y(Buffer buffer) {
        int length = this.f5851f.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] iArr = this.f5852g;
            int i4 = iArr[length + i2];
            int i5 = iArr[i2];
            Segment segment = new Segment(this.f5851f[i2], i4, (i4 + i5) - i3, true, false);
            Segment segment2 = buffer.f5770c;
            if (segment2 == null) {
                segment.f5848g = segment;
                segment.f5847f = segment;
                buffer.f5770c = segment;
            } else {
                segment2.f5848g.c(segment);
            }
            i2++;
            i3 = i5;
        }
        buffer.f5771d += (long) i3;
    }
}
