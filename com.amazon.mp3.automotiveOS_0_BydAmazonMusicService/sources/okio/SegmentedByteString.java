package okio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._SegmentedByteStringKt;

/* JADX INFO: compiled from: SegmentedByteString.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00140)\u0012\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b3\u00104J\b\u0010\u0002\u001a\u00020\u0001H\u0002J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0001H\u0016J\u0017\u0010\n\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0003H\u0010¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0010¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u000bH\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0015\u001a\u00020\u0014H\u0016J'\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0010¢\u0006\u0004\b\u001b\u0010\u001cJ(\u0010!\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0016J(\u0010!\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0016J\u000f\u0010$\u001a\u00020\u0014H\u0010¢\u0006\u0004\b\"\u0010#J\u0013\u0010&\u001a\u00020 2\b\u0010\u001e\u001a\u0004\u0018\u00010%H\u0096\u0002J\b\u0010'\u001a\u00020\u000bH\u0016J\b\u0010(\u001a\u00020\u0003H\u0016R \u0010*\u001a\b\u0012\u0004\u0012\u00020\u00140)8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001a\u0010/\u001a\u00020.8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102¨\u00065"}, d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", "toByteString", "", "base64", "hex", "toAsciiLowercase", "algorithm", "digest$okio", "(Ljava/lang/String;)Lokio/ByteString;", "digest", "", "pos", "", "internalGet$okio", "(I)B", "internalGet", "getSize$okio", "()I", "getSize", "", "toByteArray", "Lokio/Buffer;", "buffer", "offset", "byteCount", "", "write$okio", "(Lokio/Buffer;II)V", "write", "other", "otherOffset", "", "rangeEquals", "internalArray$okio", "()[B", "internalArray", "", "equals", "hashCode", "toString", "", "segments", "[[B", "getSegments$okio", "()[[B", "", "directory", "[I", "getDirectory$okio", "()[I", "<init>", "([[B[I)V", "okio"}, k = 1, mv = {1, 5, 1})
public final class SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    /* JADX INFO: renamed from: getSegments$okio, reason: from getter */
    public final byte[][] getSegments() {
        return this.segments;
    }

    /* JADX INFO: renamed from: getDirectory$okio, reason: from getter */
    public final int[] getDirectory() {
        return this.directory;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SegmentedByteString(byte[][] segments, int[] directory) {
        super(ByteString.EMPTY.getData());
        Intrinsics.checkNotNullParameter(segments, "segments");
        Intrinsics.checkNotNullParameter(directory, "directory");
        this.segments = segments;
        this.directory = directory;
    }

    @Override // okio.ByteString
    public String base64() {
        return toByteString().base64();
    }

    @Override // okio.ByteString
    public String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString digest$okio(String algorithm) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        int length = getSegments().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = getDirectory()[length + i];
            int i4 = getDirectory()[i];
            messageDigest.update(getSegments()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
        byte[] digestBytes = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(digestBytes, "digestBytes");
        return new ByteString(digestBytes);
    }

    @Override // okio.ByteString
    public void write$okio(Buffer buffer, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int i = offset + byteCount;
        int iSegment = _SegmentedByteStringKt.segment(this, offset);
        while (offset < i) {
            int i2 = iSegment == 0 ? 0 : getDirectory()[iSegment - 1];
            int i3 = getDirectory()[iSegment] - i2;
            int i4 = getDirectory()[getSegments().length + iSegment];
            int iMin = Math.min(i, i3 + i2) - offset;
            int i5 = i4 + (offset - i2);
            Segment segment = new Segment(getSegments()[iSegment], i5, i5 + iMin, true, false);
            Segment segment2 = buffer.head;
            if (segment2 == null) {
                segment.prev = segment;
                segment.next = segment;
                buffer.head = segment;
            } else {
                Intrinsics.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                Intrinsics.checkNotNull(segment3);
                segment3.push(segment);
            }
            offset += iMin;
            iSegment++;
        }
        buffer.setSize$okio(buffer.getSize() + ((long) byteCount));
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    public byte[] internalArray$okio() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public byte internalGet$okio(int pos) {
        _UtilKt.checkOffsetAndCount(getDirectory()[getSegments().length - 1], pos, 1L);
        int iSegment = _SegmentedByteStringKt.segment(this, pos);
        return getSegments()[iSegment][(pos - (iSegment == 0 ? 0 : getDirectory()[iSegment - 1])) + getDirectory()[getSegments().length + iSegment]];
    }

    @Override // okio.ByteString
    public String toString() {
        return toByteString().toString();
    }

    @Override // okio.ByteString
    public int getSize$okio() {
        return getDirectory()[getSegments().length - 1];
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        byte[] bArr = new byte[size()];
        int length = getSegments().length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory()[length + i];
            int i5 = getDirectory()[i];
            int i6 = i5 - i2;
            ArraysKt___ArraysJvmKt.copyInto(getSegments()[i], bArr, i3, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (offset < 0 || offset > size() - byteCount) {
            return false;
        }
        int i = byteCount + offset;
        int iSegment = _SegmentedByteStringKt.segment(this, offset);
        while (offset < i) {
            int i2 = iSegment == 0 ? 0 : getDirectory()[iSegment - 1];
            int i3 = getDirectory()[iSegment] - i2;
            int i4 = getDirectory()[getSegments().length + iSegment];
            int iMin = Math.min(i, i3 + i2) - offset;
            if (!other.rangeEquals(otherOffset, getSegments()[iSegment], i4 + (offset - i2), iMin)) {
                return false;
            }
            otherOffset += iMin;
            offset += iMin;
            iSegment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (offset < 0 || offset > size() - byteCount || otherOffset < 0 || otherOffset > other.length - byteCount) {
            return false;
        }
        int i = byteCount + offset;
        int iSegment = _SegmentedByteStringKt.segment(this, offset);
        while (offset < i) {
            int i2 = iSegment == 0 ? 0 : getDirectory()[iSegment - 1];
            int i3 = getDirectory()[iSegment] - i2;
            int i4 = getDirectory()[getSegments().length + iSegment];
            int iMin = Math.min(i, i3 + i2) - offset;
            if (!_UtilKt.arrayRangeEquals(getSegments()[iSegment], i4 + (offset - i2), other, otherOffset, iMin)) {
                return false;
            }
            otherOffset += iMin;
            offset += iMin;
            iSegment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ByteString) {
            ByteString byteString = (ByteString) other;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public int hashCode() {
        int hashCode = getHashCode();
        if (hashCode != 0) {
            return hashCode;
        }
        int length = getSegments().length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory()[length + i];
            int i5 = getDirectory()[i];
            byte[] bArr = getSegments()[i];
            int i6 = (i5 - i3) + i4;
            while (i4 < i6) {
                i2 = (i2 * 31) + bArr[i4];
                i4++;
            }
            i++;
            i3 = i5;
        }
        setHashCode$okio(i2);
        return i2;
    }
}
