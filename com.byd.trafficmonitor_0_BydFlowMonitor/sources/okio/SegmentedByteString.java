package okio;

import g.c;
import g.m;
import g.r;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class SegmentedByteString extends ByteString {
    public final transient int[] directory;
    public final transient byte[][] segments;

    public SegmentedByteString(c cVar, int i2) {
        super(null);
        r.b(cVar.f5471d, 0L, i2);
        m mVar = cVar.f5470c;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = mVar.f5498c;
            int i7 = mVar.f5497b;
            if (i6 == i7) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += i6 - i7;
            i5++;
            mVar = mVar.f5501f;
        }
        this.segments = new byte[i5][];
        this.directory = new int[i5 * 2];
        m mVar2 = cVar.f5470c;
        int i8 = 0;
        while (i3 < i2) {
            byte[][] bArr = this.segments;
            bArr[i8] = mVar2.f5496a;
            int i9 = mVar2.f5498c;
            int i10 = mVar2.f5497b;
            i3 += i9 - i10;
            if (i3 > i2) {
                i3 = i2;
            }
            int[] iArr = this.directory;
            iArr[i8] = i3;
            iArr[bArr.length + i8] = i10;
            mVar2.f5499d = true;
            i8++;
            mVar2 = mVar2.f5501f;
        }
    }

    private Object writeReplace() {
        return e();
    }

    @Override // okio.ByteString
    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // okio.ByteString
    public String base64() {
        return e().base64();
    }

    @Override // okio.ByteString
    public String base64Url() {
        return e().base64Url();
    }

    public final int d(int i2) {
        int iBinarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, i2 + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    public final ByteString e() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public byte getByte(int i2) {
        r.b(this.directory[this.segments.length - 1], i2, 1L);
        int iD = d(i2);
        int i3 = iD == 0 ? 0 : this.directory[iD - 1];
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[iD][(i2 - i3) + iArr[bArr.length + iD]];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i2 = this.hashCode;
        if (i2 != 0) {
            return i2;
        }
        int length = this.segments.length;
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        while (i3 < length) {
            byte[] bArr = this.segments[i3];
            int[] iArr = this.directory;
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
        this.hashCode = i4;
        return i4;
    }

    @Override // okio.ByteString
    public String hex() {
        return e().hex();
    }

    @Override // okio.ByteString
    public ByteString hmacSha1(ByteString byteString) {
        return e().hmacSha1(byteString);
    }

    @Override // okio.ByteString
    public ByteString hmacSha256(ByteString byteString) {
        return e().hmacSha256(byteString);
    }

    @Override // okio.ByteString
    public int indexOf(byte[] bArr, int i2) {
        return e().indexOf(bArr, i2);
    }

    @Override // okio.ByteString
    public byte[] internalArray() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] bArr, int i2) {
        return e().lastIndexOf(bArr, i2);
    }

    @Override // okio.ByteString
    public ByteString md5() {
        return e().md5();
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i2, ByteString byteString, int i3, int i4) {
        if (i2 < 0 || i2 > size() - i4) {
            return false;
        }
        int iD = d(i2);
        while (i4 > 0) {
            int i5 = iD == 0 ? 0 : this.directory[iD - 1];
            int iMin = Math.min(i4, ((this.directory[iD] - i5) + i5) - i2);
            int[] iArr = this.directory;
            byte[][] bArr = this.segments;
            if (!byteString.rangeEquals(i3, bArr[iD], (i2 - i5) + iArr[bArr.length + iD], iMin)) {
                return false;
            }
            i2 += iMin;
            i3 += iMin;
            i4 -= iMin;
            iD++;
        }
        return true;
    }

    @Override // okio.ByteString
    public ByteString sha1() {
        return e().sha1();
    }

    @Override // okio.ByteString
    public ByteString sha256() {
        return e().sha256();
    }

    @Override // okio.ByteString
    public int size() {
        return this.directory[this.segments.length - 1];
    }

    @Override // okio.ByteString
    public String string(Charset charset) {
        return e().string(charset);
    }

    @Override // okio.ByteString
    public ByteString substring(int i2) {
        return e().substring(i2);
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return e().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString toAsciiUppercase() {
        return e().toAsciiUppercase();
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] iArr2 = this.directory;
            int i4 = iArr2[length + i2];
            int i5 = iArr2[i2];
            System.arraycopy(this.segments[i2], i4, bArr2, i3, i5 - i3);
            i2++;
            i3 = i5;
        }
        return bArr2;
    }

    @Override // okio.ByteString
    public String toString() {
        return e().toString();
    }

    @Override // okio.ByteString
    public String utf8() {
        return e().utf8();
    }

    @Override // okio.ByteString
    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.segments.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] iArr = this.directory;
            int i4 = iArr[length + i2];
            int i5 = iArr[i2];
            outputStream.write(this.segments[i2], i4, i5 - i3);
            i2++;
            i3 = i5;
        }
    }

    @Override // okio.ByteString
    public ByteString substring(int i2, int i3) {
        return e().substring(i2, i3);
    }

    @Override // okio.ByteString
    public void write(c cVar) {
        int length = this.segments.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] iArr = this.directory;
            int i4 = iArr[length + i2];
            int i5 = iArr[i2];
            m mVar = new m(this.segments[i2], i4, (i4 + i5) - i3, true, false);
            m mVar2 = cVar.f5470c;
            if (mVar2 == null) {
                mVar.f5502g = mVar;
                mVar.f5501f = mVar;
                cVar.f5470c = mVar;
            } else {
                mVar2.f5502g.c(mVar);
            }
            i2++;
            i3 = i5;
        }
        cVar.f5471d += (long) i3;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i2, byte[] bArr, int i3, int i4) {
        if (i2 < 0 || i2 > size() - i4 || i3 < 0 || i3 > bArr.length - i4) {
            return false;
        }
        int iD = d(i2);
        while (i4 > 0) {
            int i5 = iD == 0 ? 0 : this.directory[iD - 1];
            int iMin = Math.min(i4, ((this.directory[iD] - i5) + i5) - i2);
            int[] iArr = this.directory;
            byte[][] bArr2 = this.segments;
            if (!r.a(bArr2[iD], (i2 - i5) + iArr[bArr2.length + iD], bArr, i3, iMin)) {
                return false;
            }
            i2 += iMin;
            i3 += iMin;
            i4 -= iMin;
            iD++;
        }
        return true;
    }
}
