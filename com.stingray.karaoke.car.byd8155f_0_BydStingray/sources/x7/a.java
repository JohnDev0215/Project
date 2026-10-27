package x7;

import A7.m;
import Q8.h;
import h6.e;
import io.sentry.instrumentation.file.g;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends e {
    public static File u(File file) {
        int length;
        File file2;
        int iJ0;
        File file3 = new File("image_cache");
        String path = file3.getPath();
        m.e(path, "getPath(...)");
        char c = File.separatorChar;
        int iJ02 = h.J0(path, c, 0, false, 4);
        if (iJ02 != 0) {
            length = (iJ02 <= 0 || path.charAt(iJ02 + (-1)) != ':') ? (iJ02 == -1 && h.F0(path, ':')) ? path.length() : 0 : iJ02 + 1;
        } else if (path.length() <= 1 || path.charAt(1) != c || (iJ0 = h.J0(path, c, 2, false, 4)) < 0) {
            length = 1;
        } else {
            int iJ03 = h.J0(path, c, iJ0 + 1, false, 4);
            length = iJ03 >= 0 ? iJ03 + 1 : path.length();
        }
        if (length > 0) {
            return file3;
        }
        String string = file.toString();
        m.e(string, "toString(...)");
        if ((string.length() == 0) || h.F0(string, c)) {
            file2 = new File(string + file3);
        } else {
            file2 = new File(string + c + file3);
        }
        return file2;
    }

    public static final void v(g gVar, String str, Charset charset) {
        m.f(str, "text");
        if (str.length() < 16384) {
            byte[] bytes = str.getBytes(charset);
            m.e(bytes, "getBytes(...)");
            gVar.write(bytes);
            return;
        }
        CharsetEncoder charsetEncoderNewEncoder = charset.newEncoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPLACE;
        CharsetEncoder charsetEncoderOnUnmappableCharacter = charsetEncoderNewEncoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
        CharBuffer charBufferAllocate = CharBuffer.allocate(8192);
        m.c(charsetEncoderOnUnmappableCharacter);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8192 * ((int) Math.ceil(charsetEncoderOnUnmappableCharacter.maxBytesPerChar())));
        m.e(byteBufferAllocate, "allocate(...)");
        int i7 = 0;
        int i10 = 0;
        while (i7 < str.length()) {
            int iMin = Math.min(8192 - i10, str.length() - i7);
            int i11 = i7 + iMin;
            char[] cArrArray = charBufferAllocate.array();
            m.e(cArrArray, "array(...)");
            str.getChars(i7, i11, cArrArray, i10);
            charBufferAllocate.limit(iMin + i10);
            i10 = 1;
            if (!charsetEncoderOnUnmappableCharacter.encode(charBufferAllocate, byteBufferAllocate, i11 == str.length()).isUnderflow()) {
                throw new IllegalStateException("Check failed.");
            }
            gVar.write(byteBufferAllocate.array(), 0, byteBufferAllocate.position());
            if (charBufferAllocate.position() != charBufferAllocate.limit()) {
                charBufferAllocate.put(0, charBufferAllocate.get());
            } else {
                i10 = 0;
            }
            charBufferAllocate.clear();
            byteBufferAllocate.clear();
            i7 = i11;
        }
    }
}
