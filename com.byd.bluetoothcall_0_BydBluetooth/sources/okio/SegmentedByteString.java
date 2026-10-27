package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class SegmentedByteString extends ByteString {
    final transient int[] directory;
    final transient byte[][] segments;

    SegmentedByteString(b bVar, int i) {
        super(null);
        l.b(bVar.f4372d, 0L, i);
        h hVar = bVar.f4371c;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            int i5 = hVar.f4383c;
            int i6 = hVar.f4382b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            hVar = hVar.f;
        }
        this.segments = new byte[i4][];
        this.directory = new int[i4 * 2];
        h hVar2 = bVar.f4371c;
        int i7 = 0;
        while (i2 < i) {
            byte[][] bArr = this.segments;
            bArr[i7] = hVar2.f4381a;
            int i8 = hVar2.f4383c;
            int i9 = hVar2.f4382b;
            i2 += i8 - i9;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.directory;
            iArr[i7] = i2;
            iArr[bArr.length + i7] = i9;
            hVar2.f4384d = true;
            i7++;
            hVar2 = hVar2.f;
        }
    }

    private int g(int i) {
        int iBinarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    private ByteString h() {
        return new ByteString(toByteArray());
    }

    private Object writeReplace() {
        return h();
    }

    @Override // okio.ByteString
    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // okio.ByteString
    public String base64() {
        return h().base64();
    }

    @Override // okio.ByteString
    public String base64Url() {
        return h().base64Url();
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
    public byte getByte(int i) {
        l.b(this.directory[this.segments.length - 1], i, 1L);
        int iG = g(i);
        int i2 = iG == 0 ? 0 : this.directory[iG - 1];
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[iG][(i - i2) + iArr[bArr.length + iG]];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int length = this.segments.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.segments[i2];
            int[] iArr = this.directory;
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
        this.hashCode = i3;
        return i3;
    }

    @Override // okio.ByteString
    public String hex() {
        return h().hex();
    }

    @Override // okio.ByteString
    public ByteString hmacSha1(ByteString byteString) {
        return h().hmacSha1(byteString);
    }

    @Override // okio.ByteString
    public ByteString hmacSha256(ByteString byteString) {
        return h().hmacSha256(byteString);
    }

    @Override // okio.ByteString
    public int indexOf(byte[] bArr, int i) {
        return h().indexOf(bArr, i);
    }

    @Override // okio.ByteString
    byte[] internalArray() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] bArr, int i) {
        return h().lastIndexOf(bArr, i);
    }

    @Override // okio.ByteString
    public ByteString md5() {
        return h().md5();
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int iG = g(i);
        while (i3 > 0) {
            int i4 = iG == 0 ? 0 : this.directory[iG - 1];
            int iMin = Math.min(i3, ((this.directory[iG] - i4) + i4) - i);
            int[] iArr = this.directory;
            byte[][] bArr = this.segments;
            if (!byteString.rangeEquals(i2, bArr[iG], (i - i4) + iArr[bArr.length + iG], iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iG++;
        }
        return true;
    }

    @Override // okio.ByteString
    public ByteString sha1() {
        return h().sha1();
    }

    @Override // okio.ByteString
    public ByteString sha256() {
        return h().sha256();
    }

    @Override // okio.ByteString
    public int size() {
        return this.directory[this.segments.length - 1];
    }

    @Override // okio.ByteString
    public String string(Charset charset) {
        return h().string(charset);
    }

    @Override // okio.ByteString
    public ByteString substring(int i) {
        return h().substring(i);
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return h().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString toAsciiUppercase() {
        return h().toAsciiUppercase();
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.directory;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.segments[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // okio.ByteString
    public String toString() {
        return h().toString();
    }

    @Override // okio.ByteString
    public String utf8() {
        return h().utf8();
    }

    @Override // okio.ByteString
    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.directory;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            outputStream.write(this.segments[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    @Override // okio.ByteString
    public ByteString substring(int i, int i2) {
        return h().substring(i, i2);
    }

    @Override // okio.ByteString
    void write(b bVar) {
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.directory;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            h hVar = new h(this.segments[i], i3, (i3 + i4) - i2, true, false);
            h hVar2 = bVar.f4371c;
            if (hVar2 == null) {
                hVar.g = hVar;
                hVar.f = hVar;
                bVar.f4371c = hVar;
            } else {
                hVar2.g.c(hVar);
            }
            i++;
            i2 = i4;
        }
        bVar.f4372d += (long) i2;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iG = g(i);
        while (i3 > 0) {
            int i4 = iG == 0 ? 0 : this.directory[iG - 1];
            int iMin = Math.min(i3, ((this.directory[iG] - i4) + i4) - i);
            int[] iArr = this.directory;
            byte[][] bArr2 = this.segments;
            if (!l.a(bArr2[iG], (i - i4) + iArr[bArr2.length + iG], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iG++;
        }
        return true;
    }
}
