package android.net;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/* JADX INFO: loaded from: classes2.dex */
public final class UriCodec {
    private static final char INVALID_INPUT_CHARACTER = 65533;

    private static int hexCharToValue(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        int i = 97;
        if ('a' > c || c > 'f') {
            i = 65;
            if ('A' > c || c > 'F') {
                return -1;
            }
        }
        return (c + '\n') - i;
    }

    private UriCodec() {
    }

    private static URISyntaxException unexpectedCharacterException(String str, String str2, char c, int i) {
        String str3;
        if (str2 == null) {
            str3 = "";
        } else {
            str3 = " in [" + str2 + "]";
        }
        return new URISyntaxException(str, "Unexpected character" + str3 + ": " + c, i);
    }

    private static char getNextCharacter(String str, int i, int i2, String str2) throws URISyntaxException {
        String str3;
        if (i >= i2) {
            if (str2 == null) {
                str3 = "";
            } else {
                str3 = " in [" + str2 + "]";
            }
            throw new URISyntaxException(str, "Unexpected end of string" + str3, i);
        }
        return str.charAt(i);
    }

    public static String decode(String str, boolean z, Charset charset, boolean z2) {
        StringBuilder sb = new StringBuilder(str.length());
        appendDecoded(sb, str, z, charset, z2);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x008c, code lost:
    
        r0.put(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void appendDecoded(java.lang.StringBuilder r9, java.lang.String r10, boolean r11, java.nio.charset.Charset r12, boolean r13) {
        /*
            java.nio.charset.CharsetDecoder r12 = r12.newDecoder()
            java.nio.charset.CodingErrorAction r0 = java.nio.charset.CodingErrorAction.REPLACE
            java.nio.charset.CharsetDecoder r12 = r12.onMalformedInput(r0)
            java.lang.String r0 = "�"
            java.nio.charset.CharsetDecoder r12 = r12.replaceWith(r0)
            java.nio.charset.CodingErrorAction r0 = java.nio.charset.CodingErrorAction.REPORT
            java.nio.charset.CharsetDecoder r12 = r12.onUnmappableCharacter(r0)
            int r0 = r10.length()
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)
            r1 = 0
            r2 = r1
        L21:
            int r3 = r10.length()
            if (r2 >= r3) goto L90
            char r3 = r10.charAt(r2)
            int r2 = r2 + 1
            r4 = 37
            if (r3 == r4) goto L47
            r4 = 43
            if (r3 == r4) goto L3c
            flushDecodingByteAccumulator(r9, r12, r0, r13)
            r9.append(r3)
            goto L21
        L3c:
            flushDecodingByteAccumulator(r9, r12, r0, r13)
            if (r11 == 0) goto L43
            r4 = 32
        L43:
            r9.append(r4)
            goto L21
        L47:
            r3 = r1
            r4 = r3
        L49:
            r5 = 2
            if (r3 >= r5) goto L8c
            r5 = 65533(0xfffd, float:9.1831E-41)
            int r6 = r10.length()     // Catch: java.net.URISyntaxException -> L7c
            r7 = 0
            char r6 = getNextCharacter(r10, r2, r6, r7)     // Catch: java.net.URISyntaxException -> L7c
            int r2 = r2 + 1
            int r8 = hexCharToValue(r6)
            if (r8 >= 0) goto L75
            if (r13 != 0) goto L69
            flushDecodingByteAccumulator(r9, r12, r0, r13)
            r9.append(r5)
            goto L8c
        L69:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            int r2 = r2 + (-1)
            java.net.URISyntaxException r10 = unexpectedCharacterException(r10, r7, r6, r2)
            r9.<init>(r10)
            throw r9
        L75:
            int r4 = r4 * 16
            int r4 = r4 + r8
            byte r4 = (byte) r4
            int r3 = r3 + 1
            goto L49
        L7c:
            r10 = move-exception
            if (r13 != 0) goto L86
            flushDecodingByteAccumulator(r9, r12, r0, r13)
            r9.append(r5)
            return
        L86:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r10)
            throw r9
        L8c:
            r0.put(r4)
            goto L21
        L90:
            flushDecodingByteAccumulator(r9, r12, r0, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.UriCodec.appendDecoded(java.lang.StringBuilder, java.lang.String, boolean, java.nio.charset.Charset, boolean):void");
    }

    private static void flushDecodingByteAccumulator(StringBuilder sb, CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.position() == 0) {
            return;
        }
        byteBuffer.flip();
        try {
            try {
                sb.append((CharSequence) charsetDecoder.decode(byteBuffer));
            } catch (CharacterCodingException e) {
                if (z) {
                    throw new IllegalArgumentException(e);
                }
                sb.append(INVALID_INPUT_CHARACTER);
            }
        } finally {
            byteBuffer.flip();
            byteBuffer.limit(byteBuffer.capacity());
        }
    }
}
