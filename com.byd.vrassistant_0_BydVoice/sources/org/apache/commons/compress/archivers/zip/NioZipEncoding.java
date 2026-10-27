package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import okio.Utf8;

/* JADX INFO: loaded from: classes4.dex */
class NioZipEncoding implements ZipEncoding, CharsetAccessor {
    private final Charset charset;
    private final boolean useReplacement;
    private static final byte[] REPLACEMENT_BYTES = {Utf8.REPLACEMENT_BYTE};
    private static final char REPLACEMENT = '?';
    private static final String REPLACEMENT_STRING = String.valueOf(REPLACEMENT);
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    NioZipEncoding(Charset charset, boolean z) {
        this.charset = charset;
        this.useReplacement = z;
    }

    @Override // org.apache.commons.compress.archivers.zip.CharsetAccessor
    public Charset getCharset() {
        return this.charset;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public boolean canEncode(String str) {
        return newEncoder().canEncode(str);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public ByteBuffer encode(String str) {
        CharsetEncoder charsetEncoderNewEncoder = newEncoder();
        CharBuffer charBufferWrap = CharBuffer.wrap(str);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(estimateInitialBufferSize(charsetEncoderNewEncoder, charBufferWrap.remaining()));
        CharBuffer charBufferAllocate = null;
        while (charBufferWrap.hasRemaining()) {
            CoderResult coderResultEncode = charsetEncoderNewEncoder.encode(charBufferWrap, byteBufferAllocate, false);
            if (coderResultEncode.isUnmappable() || coderResultEncode.isMalformed()) {
                if (estimateIncrementalEncodingSize(charsetEncoderNewEncoder, coderResultEncode.length() * 6) > byteBufferAllocate.remaining()) {
                    int i = 0;
                    for (int iPosition = charBufferWrap.position(); iPosition < charBufferWrap.limit(); iPosition++) {
                        i += !charsetEncoderNewEncoder.canEncode(charBufferWrap.get(iPosition)) ? 6 : 1;
                    }
                    byteBufferAllocate = ZipEncodingHelper.growBufferBy(byteBufferAllocate, estimateIncrementalEncodingSize(charsetEncoderNewEncoder, i) - byteBufferAllocate.remaining());
                }
                if (charBufferAllocate == null) {
                    charBufferAllocate = CharBuffer.allocate(6);
                }
                for (int i2 = 0; i2 < coderResultEncode.length(); i2++) {
                    byteBufferAllocate = encodeFully(charsetEncoderNewEncoder, encodeSurrogate(charBufferAllocate, charBufferWrap.get()), byteBufferAllocate);
                }
            } else if (coderResultEncode.isOverflow()) {
                byteBufferAllocate = ZipEncodingHelper.growBufferBy(byteBufferAllocate, estimateIncrementalEncodingSize(charsetEncoderNewEncoder, charBufferWrap.remaining()));
            } else if (coderResultEncode.isUnderflow() || coderResultEncode.isError()) {
                break;
            }
        }
        charsetEncoderNewEncoder.encode(charBufferWrap, byteBufferAllocate, true);
        byteBufferAllocate.limit(byteBufferAllocate.position());
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public String decode(byte[] bArr) throws IOException {
        return newDecoder().decode(ByteBuffer.wrap(bArr)).toString();
    }

    private static ByteBuffer encodeFully(CharsetEncoder charsetEncoder, CharBuffer charBuffer, ByteBuffer byteBuffer) {
        while (charBuffer.hasRemaining()) {
            if (charsetEncoder.encode(charBuffer, byteBuffer, false).isOverflow()) {
                byteBuffer = ZipEncodingHelper.growBufferBy(byteBuffer, estimateIncrementalEncodingSize(charsetEncoder, charBuffer.remaining()));
            }
        }
        return byteBuffer;
    }

    private static CharBuffer encodeSurrogate(CharBuffer charBuffer, char c) {
        charBuffer.position(0).limit(6);
        charBuffer.put('%');
        charBuffer.put('U');
        char[] cArr = HEX_CHARS;
        charBuffer.put(cArr[(c >> '\f') & 15]);
        charBuffer.put(cArr[(c >> '\b') & 15]);
        charBuffer.put(cArr[(c >> 4) & 15]);
        charBuffer.put(cArr[c & 15]);
        charBuffer.flip();
        return charBuffer;
    }

    private CharsetEncoder newEncoder() {
        if (this.useReplacement) {
            return this.charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).replaceWith(REPLACEMENT_BYTES);
        }
        return this.charset.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
    }

    private CharsetDecoder newDecoder() {
        if (!this.useReplacement) {
            return this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        }
        return this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).replaceWith(REPLACEMENT_STRING);
    }

    private static int estimateInitialBufferSize(CharsetEncoder charsetEncoder, int i) {
        return (int) Math.ceil(charsetEncoder.maxBytesPerChar() + ((i - 1) * charsetEncoder.averageBytesPerChar()));
    }

    private static int estimateIncrementalEncodingSize(CharsetEncoder charsetEncoder, int i) {
        return (int) Math.ceil(i * charsetEncoder.averageBytesPerChar());
    }
}
