package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Options;
import okio.Segment;
import okio._JvmPlatformKt;
import okio._UtilKt;

/* JADX INFO: compiled from: -Buffer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a0\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0000\u001a\u0014\u0010\u000e\u001a\u00020\r*\u00020\n2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001a\u001e\u0010\u0012\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\bH\u0000\u001a\u0014\u0010\u0015\u001a\u00020\u0013*\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0000\" \u0010\u0016\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lokio/Segment;", "segment", "", "segmentPos", "", "bytes", "bytesOffset", "bytesLimit", "", "rangeEquals", "Lokio/Buffer;", "", "newline", "", "readUtf8Line", "Lokio/Options;", "options", "selectTruncated", "selectPrefix", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "commonReadAndWriteUnsafe", "HEX_DIGIT_BYTES", "[B", "getHEX_DIGIT_BYTES", "()[B", "getHEX_DIGIT_BYTES$annotations", "()V", "okio"}, k = 2, mv = {1, 5, 1})
public final class _BufferKt {
    private static final byte[] HEX_DIGIT_BYTES = _JvmPlatformKt.asUtf8ToByteArray("0123456789abcdef");

    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static final boolean rangeEquals(Segment segment, int i, byte[] bytes, int i2, int i3) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        int i4 = segment.limit;
        byte[] bArr = segment.data;
        while (i2 < i3) {
            if (i == i4) {
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                byte[] bArr2 = segment.data;
                bArr = bArr2;
                i = segment.pos;
                i4 = segment.limit;
            }
            if (bArr[i] != bytes[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public static final String readUtf8Line(Buffer buffer, long j) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (j > 0) {
            long j2 = j - 1;
            if (buffer.getByte(j2) == ((byte) 13)) {
                String utf8 = buffer.readUtf8(j2);
                buffer.skip(2L);
                return utf8;
            }
        }
        String utf82 = buffer.readUtf8(j);
        buffer.skip(1L);
        return utf82;
    }

    public static /* synthetic */ int selectPrefix$default(Buffer buffer, Options options, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return selectPrefix(buffer, options, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
    
        if (r19 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
    
        return -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
    
        return r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int selectPrefix(okio.Buffer r17, okio.Options r18, boolean r19) {
        /*
            r0 = r17
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "options"
            r2 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            okio.Segment r0 = r0.head
            r1 = -2
            r3 = -1
            if (r0 != 0) goto L19
            if (r19 == 0) goto L17
            goto L18
        L17:
            r1 = r3
        L18:
            return r1
        L19:
            byte[] r4 = r0.data
            int r5 = r0.pos
            int r6 = r0.limit
            int[] r2 = r18.getTrie()
            r7 = 0
            r9 = r0
            r10 = r3
            r8 = r7
        L27:
            int r11 = r8 + 1
            r8 = r2[r8]
            int r12 = r11 + 1
            r11 = r2[r11]
            if (r11 == r3) goto L32
            r10 = r11
        L32:
            if (r9 != 0) goto L35
            goto L62
        L35:
            r11 = 0
            if (r8 >= 0) goto L82
            int r8 = r8 * (-1)
            int r13 = r12 + r8
        L3c:
            int r8 = r5 + 1
            r5 = r4[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r14 = r12 + 1
            r12 = r2[r12]
            if (r5 == r12) goto L49
            return r10
        L49:
            if (r14 != r13) goto L4d
            r5 = 1
            goto L4e
        L4d:
            r5 = r7
        L4e:
            if (r8 != r6) goto L6f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            okio.Segment r4 = r9.next
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            int r6 = r4.pos
            byte[] r8 = r4.data
            int r9 = r4.limit
            if (r4 != r0) goto L69
            if (r5 != 0) goto L66
        L62:
            if (r19 == 0) goto L65
            return r1
        L65:
            return r10
        L66:
            r4 = r8
            r8 = r11
            goto L75
        L69:
            r16 = r8
            r8 = r4
            r4 = r16
            goto L75
        L6f:
            r16 = r9
            r9 = r6
            r6 = r8
            r8 = r16
        L75:
            if (r5 == 0) goto L7d
            r5 = r2[r14]
            r13 = r6
            r6 = r9
            r9 = r8
            goto La7
        L7d:
            r5 = r6
            r6 = r9
            r12 = r14
            r9 = r8
            goto L3c
        L82:
            int r13 = r5 + 1
            r5 = r4[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r14 = r12 + r8
        L8a:
            if (r12 != r14) goto L8d
            return r10
        L8d:
            r15 = r2[r12]
            if (r5 != r15) goto Lae
            int r12 = r12 + r8
            r5 = r2[r12]
            if (r13 != r6) goto La7
            okio.Segment r9 = r9.next
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            int r4 = r9.pos
            byte[] r6 = r9.data
            int r8 = r9.limit
            r13 = r4
            r4 = r6
            r6 = r8
            if (r9 != r0) goto La7
            r9 = r11
        La7:
            if (r5 < 0) goto Laa
            return r5
        Laa:
            int r8 = -r5
            r5 = r13
            goto L27
        Lae:
            int r12 = r12 + 1
            goto L8a
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._BufferKt.selectPrefix(okio.Buffer, okio.Options, boolean):int");
    }

    public static final Buffer.UnsafeCursor commonReadAndWriteUnsafe(Buffer buffer, Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        Buffer.UnsafeCursor unsafeCursorResolveDefaultParameter = _UtilKt.resolveDefaultParameter(unsafeCursor);
        if (!(unsafeCursorResolveDefaultParameter.buffer == null)) {
            throw new IllegalStateException("already attached to a buffer".toString());
        }
        unsafeCursorResolveDefaultParameter.buffer = buffer;
        unsafeCursorResolveDefaultParameter.readWrite = true;
        return unsafeCursorResolveDefaultParameter;
    }
}
