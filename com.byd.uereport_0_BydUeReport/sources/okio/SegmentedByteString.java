package okio;

import i7.b;
import i7.h;
import i7.l;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class SegmentedByteString extends ByteString {
    public final transient int[] directory;
    public final transient byte[][] segments;

    public SegmentedByteString(b bVar, int i8) {
        super(null);
        l.b(bVar.f5513f, 0L, i8);
        h hVar = bVar.f5512e;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i8) {
            int i12 = hVar.f5524c;
            int i13 = hVar.f5523b;
            if (i12 == i13) {
                throw new AssertionError("s.limit == s.pos");
            }
            i10 += i12 - i13;
            i11++;
            hVar = hVar.f5527f;
        }
        this.segments = new byte[i11][];
        this.directory = new int[i11 * 2];
        h hVar2 = bVar.f5512e;
        int i14 = 0;
        while (i9 < i8) {
            byte[][] bArr = this.segments;
            bArr[i14] = hVar2.f5522a;
            int i15 = hVar2.f5524c;
            int i16 = hVar2.f5523b;
            i9 += i15 - i16;
            if (i9 > i8) {
                i9 = i8;
            }
            int[] iArr = this.directory;
            iArr[i14] = i9;
            iArr[bArr.length + i14] = i16;
            hVar2.f5525d = true;
            i14++;
            hVar2 = hVar2.f5527f;
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

    public final int d(int i8) {
        int iBinarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, i8 + 1);
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
    public byte getByte(int i8) {
        l.b(this.directory[this.segments.length - 1], i8, 1L);
        int iD = d(i8);
        int i9 = iD == 0 ? 0 : this.directory[iD - 1];
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[iD][(i8 - i9) + iArr[bArr.length + iD]];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i8 = this.hashCode;
        if (i8 != 0) {
            return i8;
        }
        int length = this.segments.length;
        int i9 = 0;
        int i10 = 1;
        int i11 = 0;
        while (i9 < length) {
            byte[] bArr = this.segments[i9];
            int[] iArr = this.directory;
            int i12 = iArr[length + i9];
            int i13 = iArr[i9];
            int i14 = (i13 - i11) + i12;
            while (i12 < i14) {
                i10 = (i10 * 31) + bArr[i12];
                i12++;
            }
            i9++;
            i11 = i13;
        }
        this.hashCode = i10;
        return i10;
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
    public int indexOf(byte[] bArr, int i8) {
        return e().indexOf(bArr, i8);
    }

    @Override // okio.ByteString
    public byte[] internalArray() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] bArr, int i8) {
        return e().lastIndexOf(bArr, i8);
    }

    @Override // okio.ByteString
    public ByteString md5() {
        return e().md5();
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i8, ByteString byteString, int i9, int i10) {
        if (i8 < 0 || i8 > size() - i10) {
            return false;
        }
        int iD = d(i8);
        while (i10 > 0) {
            int i11 = iD == 0 ? 0 : this.directory[iD - 1];
            int iMin = Math.min(i10, ((this.directory[iD] - i11) + i11) - i8);
            int[] iArr = this.directory;
            byte[][] bArr = this.segments;
            if (!byteString.rangeEquals(i9, bArr[iD], (i8 - i11) + iArr[bArr.length + iD], iMin)) {
                return false;
            }
            i8 += iMin;
            i9 += iMin;
            i10 -= iMin;
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
    public ByteString substring(int i8) {
        return e().substring(i8);
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
        int i8 = 0;
        int i9 = 0;
        while (i8 < length) {
            int[] iArr2 = this.directory;
            int i10 = iArr2[length + i8];
            int i11 = iArr2[i8];
            System.arraycopy(this.segments[i8], i10, bArr2, i9, i11 - i9);
            i8++;
            i9 = i11;
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
        int i8 = 0;
        int i9 = 0;
        while (i8 < length) {
            int[] iArr = this.directory;
            int i10 = iArr[length + i8];
            int i11 = iArr[i8];
            outputStream.write(this.segments[i8], i10, i11 - i9);
            i8++;
            i9 = i11;
        }
    }

    @Override // okio.ByteString
    public ByteString substring(int i8, int i9) {
        return e().substring(i8, i9);
    }

    @Override // okio.ByteString
    public void write(b bVar) {
        int length = this.segments.length;
        int i8 = 0;
        int i9 = 0;
        while (i8 < length) {
            int[] iArr = this.directory;
            int i10 = iArr[length + i8];
            int i11 = iArr[i8];
            h hVar = new h(this.segments[i8], i10, (i10 + i11) - i9, true, false);
            h hVar2 = bVar.f5512e;
            if (hVar2 == null) {
                hVar.f5528g = hVar;
                hVar.f5527f = hVar;
                bVar.f5512e = hVar;
            } else {
                hVar2.f5528g.c(hVar);
            }
            i8++;
            i9 = i11;
        }
        bVar.f5513f += (long) i9;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i8, byte[] bArr, int i9, int i10) {
        if (i8 < 0 || i8 > size() - i10 || i9 < 0 || i9 > bArr.length - i10) {
            return false;
        }
        int iD = d(i8);
        while (i10 > 0) {
            int i11 = iD == 0 ? 0 : this.directory[iD - 1];
            int iMin = Math.min(i10, ((this.directory[iD] - i11) + i11) - i8);
            int[] iArr = this.directory;
            byte[][] bArr2 = this.segments;
            if (!l.a(bArr2[iD], (i8 - i11) + iArr[bArr2.length + iD], bArr, i9, iMin)) {
                return false;
            }
            i8 += iMin;
            i9 += iMin;
            i10 -= iMin;
            iD++;
        }
        return true;
    }
}
