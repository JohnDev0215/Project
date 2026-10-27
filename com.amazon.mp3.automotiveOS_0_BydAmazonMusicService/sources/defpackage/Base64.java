package defpackage;

import com.fasterxml.jackson.core.JsonPointer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: Base64.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u000e\u000fB\t\b\u0002¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"LBase64;", "", "LBase64$Encoder;", "encoder", "LBase64$Encoder;", "getEncoder", "()LBase64$Encoder;", "LBase64$Decoder;", "decoder", "LBase64$Decoder;", "getDecoder", "()LBase64$Decoder;", "<init>", "()V", "Decoder", "Encoder", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class Base64 {
    public static final Base64 INSTANCE = new Base64();
    private static final Encoder encoder = new Encoder(null, -1, true);
    private static final Decoder decoder = new Decoder();

    private Base64() {
    }

    public final Encoder getEncoder() {
        return encoder;
    }

    /* JADX INFO: compiled from: Base64.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B!\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003J(\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0003H\u0002J0\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"LBase64$Encoder;", "", "newline", "", "linemax", "", "doPadding", "", "([BIZ)V", "encode", "src", "encode0", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "end", "dst", "encodeBlock", "", "sp", "sl", "dp", "outLength", "srclen", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Encoder {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final char[] toBase64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', JsonPointer.SEPARATOR};
        private static final char[] toBase64URL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        private final boolean doPadding;
        private final int linemax;
        private final byte[] newline;

        public Encoder(byte[] bArr, int i, boolean z) {
            this.newline = bArr;
            this.linemax = i;
            this.doPadding = z;
        }

        private final int outLength(int srclen) {
            int i;
            if (this.doPadding) {
                i = ((srclen + 2) / 3) * 4;
            } else {
                int i2 = srclen % 3;
                i = ((srclen / 3) * 4) + (i2 == 0 ? 0 : i2 + 1);
            }
            int i3 = this.linemax;
            if (i3 <= 0) {
                return i;
            }
            int i4 = (i - 1) / i3;
            byte[] bArr = this.newline;
            Intrinsics.checkNotNull(bArr);
            return i + (i4 * bArr.length);
        }

        public final byte[] encode(byte[] src) {
            Intrinsics.checkNotNullParameter(src, "src");
            int iOutLength = outLength(src.length);
            byte[] bArr = new byte[iOutLength];
            int iEncode0 = encode0(src, 0, src.length, bArr);
            if (iEncode0 == iOutLength) {
                return bArr;
            }
            byte[] bArrCopyOf = Arrays.copyOf(bArr, iEncode0);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, newSize)");
            return bArrCopyOf;
        }

        private final void encodeBlock(byte[] src, int sp, int sl, byte[] dst, int dp) {
            while (sp < sl) {
                int i = sp + 1;
                int i2 = i + 1;
                int i3 = ((src[sp] & 255) << 16) | ((src[i] & 255) << 8);
                int i4 = i2 + 1;
                int i5 = i3 | (src[i2] & 255);
                int i6 = dp + 1;
                char[] cArr = toBase64;
                dst[dp] = (byte) cArr[(i5 >>> 18) & 63];
                int i7 = i6 + 1;
                dst[i6] = (byte) cArr[(i5 >>> 12) & 63];
                int i8 = i7 + 1;
                dst[i7] = (byte) cArr[(i5 >>> 6) & 63];
                dp = i8 + 1;
                dst[i8] = (byte) cArr[i5 & 63];
                sp = i4;
            }
        }

        private final int encode0(byte[] src, int off, int end, byte[] dst) {
            char[] cArr = toBase64;
            int i = ((end - off) / 3) * 3;
            int i2 = off + i;
            int i3 = this.linemax;
            if (i3 > 0 && i > (i3 / 4) * 3) {
                i = (i3 / 4) * 3;
            }
            int i4 = i;
            int i5 = off;
            int i6 = 0;
            while (i5 < i2) {
                int iMin = Math.min(i5 + i4, i2);
                encodeBlock(src, i5, iMin, dst, i6);
                int i7 = ((iMin - i5) / 3) * 4;
                i6 += i7;
                if (i7 == this.linemax && iMin < end) {
                    byte[] bArr = this.newline;
                    Intrinsics.checkNotNull(bArr);
                    int length = bArr.length;
                    int i8 = 0;
                    while (i8 < length) {
                        dst[i6] = bArr[i8];
                        i8++;
                        i6++;
                    }
                }
                i5 = iMin;
            }
            if (i5 >= end) {
                return i6;
            }
            int i9 = i5 + 1;
            int i10 = src[i5] & 255;
            int i11 = i6 + 1;
            dst[i6] = (byte) cArr[i10 >> 2];
            if (i9 == end) {
                int i12 = i11 + 1;
                dst[i11] = (byte) cArr[(i10 << 4) & 63];
                if (!this.doPadding) {
                    return i12;
                }
                int i13 = i12 + 1;
                dst[i12] = 61;
                int i14 = i13 + 1;
                dst[i13] = 61;
                return i14;
            }
            int i15 = src[i9] & 255;
            int i16 = i11 + 1;
            dst[i11] = (byte) cArr[((i10 << 4) & 63) | (i15 >> 4)];
            int i17 = i16 + 1;
            dst[i16] = (byte) cArr[(i15 << 2) & 63];
            if (!this.doPadding) {
                return i17;
            }
            int i18 = i17 + 1;
            dst[i17] = 61;
            return i18;
        }

        /* JADX INFO: compiled from: Base64.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"LBase64$Encoder$Companion;", "", "()V", "toBase64", "", "getToBase64", "()[C", "toBase64URL", "getToBase64URL$MusicPlatformCore_release", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final char[] getToBase64() {
                return Encoder.toBase64;
            }

            public final char[] getToBase64URL$MusicPlatformCore_release() {
                return Encoder.toBase64URL;
            }
        }
    }

    /* JADX INFO: compiled from: Base64.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"LBase64$Decoder;", "", "<init>", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class Decoder {
        private static final int[] fromBase64;
        private static final int[] fromBase64URL = new int[256];

        static {
            int[] iArr = new int[256];
            fromBase64 = iArr;
            ArraysKt___ArraysJvmKt.fill$default(iArr, -1, 0, 0, 6, (Object) null);
            int length = Encoder.INSTANCE.getToBase64().length;
            for (int i = 0; i < length; i++) {
                fromBase64[Encoder.INSTANCE.getToBase64()[i]] = i;
            }
            fromBase64[61] = -2;
            ArraysKt___ArraysJvmKt.fill$default(fromBase64URL, -1, 0, 0, 6, (Object) null);
            int length2 = Encoder.INSTANCE.getToBase64URL$MusicPlatformCore_release().length;
            for (int i2 = 0; i2 < length2; i2++) {
                fromBase64URL[Encoder.INSTANCE.getToBase64URL$MusicPlatformCore_release()[i2]] = i2;
            }
            fromBase64URL[61] = -2;
        }
    }
}
