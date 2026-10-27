package com.amazon.ion.impl.bin.utf8;

import com.amazon.music.destination.parser.ParserUtil;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/* JADX INFO: loaded from: classes3.dex */
public class Utf8StringEncoder extends Poolable<Utf8StringEncoder> {
    final char[] charArray;
    final CharBuffer charBuffer;
    final CharsetEncoder utf8Encoder;
    final ByteBuffer utf8EncodingBuffer;

    @Override // com.amazon.ion.impl.bin.utf8.Poolable, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    Utf8StringEncoder(Pool<Utf8StringEncoder> pool) {
        super(pool);
        CharsetEncoder charsetEncoderNewEncoder = Charset.forName(ParserUtil.UTF_8).newEncoder();
        this.utf8Encoder = charsetEncoderNewEncoder;
        this.utf8EncodingBuffer = ByteBuffer.allocate((int) (charsetEncoderNewEncoder.maxBytesPerChar() * 4096.0f));
        char[] cArr = new char[4096];
        this.charArray = cArr;
        this.charBuffer = CharBuffer.wrap(cArr);
    }

    public Result encode(String str) {
        ByteBuffer byteBufferAllocate;
        CharBuffer charBufferWrap;
        if (str.length() > 4096) {
            byteBufferAllocate = ByteBuffer.allocate((int) (str.length() * this.utf8Encoder.maxBytesPerChar()));
            char[] cArr = new char[str.length()];
            str.getChars(0, str.length(), cArr, 0);
            charBufferWrap = CharBuffer.wrap(cArr);
        } else {
            byteBufferAllocate = this.utf8EncodingBuffer;
            byteBufferAllocate.clear();
            CharBuffer charBuffer = this.charBuffer;
            str.getChars(0, str.length(), this.charArray, 0);
            this.charBuffer.rewind();
            this.charBuffer.limit(str.length());
            charBufferWrap = charBuffer;
        }
        if (!this.utf8Encoder.encode(charBufferWrap, byteBufferAllocate, true).isUnderflow()) {
            throw new IllegalArgumentException("Could not encode string as UTF8 bytes: " + str);
        }
        byteBufferAllocate.flip();
        return new Result(byteBufferAllocate.remaining(), byteBufferAllocate.array());
    }

    public static class Result {
        private final byte[] buffer;
        private final int encodedLength;

        public Result(int i, byte[] bArr) {
            this.encodedLength = i;
            this.buffer = bArr;
        }

        public byte[] getBuffer() {
            return this.buffer;
        }

        public int getEncodedLength() {
            return this.encodedLength;
        }
    }
}
