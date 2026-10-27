package okio.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;

/* JADX INFO: compiled from: -ByteString.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0007\u001a$\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\bH\u0002\u001a\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0003H\u0002\" \u0010\u0010\u001a\u00020\u000f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lokio/ByteString;", "Lokio/Buffer;", "buffer", "", "offset", "byteCount", "", "commonWrite", "", "c", "decodeHexDigit", "", "s", "codePointCount", "codePointIndexToCharIndex", "", "HEX_DIGIT_CHARS", "[C", "getHEX_DIGIT_CHARS", "()[C", "getHEX_DIGIT_CHARS$annotations", "()V", "okio"}, k = 2, mv = {1, 5, 1})
public final class _ByteStringKt {
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    public static final void commonWrite(ByteString byteString, Buffer buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.write(byteString.getData(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int decodeHexDigit(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (!('a' <= c && c <= 'f')) {
            c2 = 'A';
            if (!('A' <= c && c <= 'F')) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected hex digit: ", Character.valueOf(c)));
            }
        }
        return (c - c2) + 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0047 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0081 A[EDGE_INSN: B:261:0x0081->B:55:0x0081 BREAK  A[LOOP:1: B:31:0x0051->B:59:0x0088, LOOP_LABEL: LOOP:0: B:3:0x0008->B:289:0x0008], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:268:0x00da A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:276:0x016c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0217 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int codePointIndexToCharIndex(byte[] r19, int r20) {
        /*
            Method dump skipped, instruction units count: 556
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._ByteStringKt.codePointIndexToCharIndex(byte[], int):int");
    }
}
