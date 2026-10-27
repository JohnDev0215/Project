package okio;

import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.Buffer;
import okio.internal._ByteStringKt;

/* JADX INFO: compiled from: -Util.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001a \u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0006*\u00020\u0006H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\bH\u0000\u001a0\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\bH\u0000\u001a\f\u0010\u0012\u001a\u00020\u0011*\u00020\u0010H\u0000\u001a\f\u0010\u0012\u001a\u00020\u0011*\u00020\bH\u0000\u001a\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0000\u001a\u0014\u0010\u0015\u001a\u00020\b*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0000\" \u0010\u0018\u001a\u00020\u00138\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b\"\u001a\u0010\u001e\u001a\u00020\b8\u0000X\u0080D¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"", "size", "offset", "byteCount", "", "checkOffsetAndCount", "", "reverseBytes", "", "", "a", "aOffset", "b", "bOffset", "", "arrayRangeEquals", "", "", "toHexString", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "resolveDefaultParameter", "Lokio/ByteString;", "position", "DEFAULT__new_UnsafeCursor", "Lokio/Buffer$UnsafeCursor;", "getDEFAULT__new_UnsafeCursor", "()Lokio/Buffer$UnsafeCursor;", "getDEFAULT__new_UnsafeCursor$annotations", "()V", "DEFAULT__ByteString_size", "I", "getDEFAULT__ByteString_size", "()I", "okio"}, k = 2, mv = {1, 5, 1})
public final class _UtilKt {
    private static final Buffer.UnsafeCursor DEFAULT__new_UnsafeCursor = new Buffer.UnsafeCursor();
    private static final int DEFAULT__ByteString_size = -1234567890;

    public static final int reverseBytes(int i) {
        return ((i & 255) << 24) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    public static final short reverseBytes(short s) {
        int i = s & UShort.MAX_VALUE;
        return (short) (((i & 255) << 8) | ((65280 & i) >>> 8));
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException("size=" + j + " offset=" + j2 + " byteCount=" + j3);
        }
    }

    public static final boolean arrayRangeEquals(byte[] a2, int i, byte[] b, int i2, int i3) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (i3 <= 0) {
            return true;
        }
        int i4 = 0;
        while (true) {
            int i5 = i4 + 1;
            if (a2[i4 + i] != b[i4 + i2]) {
                return false;
            }
            if (i5 >= i3) {
                return true;
            }
            i4 = i5;
        }
    }

    public static final String toHexString(byte b) {
        return StringsKt__StringsJVMKt.concatToString(new char[]{_ByteStringKt.getHEX_DIGIT_CHARS()[(b >> 4) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[b & 15]});
    }

    public static final String toHexString(int i) {
        if (i == 0) {
            return "0";
        }
        int i2 = 0;
        char[] cArr = {_ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 28) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 24) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 20) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 16) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 12) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 8) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 4) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[i & 15]};
        while (i2 < 8 && cArr[i2] == '0') {
            i2++;
        }
        return StringsKt__StringsJVMKt.concatToString(cArr, i2, 8);
    }

    public static final Buffer.UnsafeCursor getDEFAULT__new_UnsafeCursor() {
        return DEFAULT__new_UnsafeCursor;
    }

    public static final Buffer.UnsafeCursor resolveDefaultParameter(Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return unsafeCursor == DEFAULT__new_UnsafeCursor ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    public static final int getDEFAULT__ByteString_size() {
        return DEFAULT__ByteString_size;
    }

    public static final int resolveDefaultParameter(ByteString byteString, int i) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return i == DEFAULT__ByteString_size ? byteString.size() : i;
    }
}
