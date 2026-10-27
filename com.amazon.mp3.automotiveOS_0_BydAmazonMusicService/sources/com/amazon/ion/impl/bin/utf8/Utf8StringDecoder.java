package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.IonException;
import com.amazon.music.destination.parser.ParserUtil;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/* JADX INFO: loaded from: classes3.dex */
public class Utf8StringDecoder extends Poolable<Utf8StringDecoder> {
    private final CharBuffer reusableUtf8DecodingBuffer;
    private final CharsetDecoder utf8CharsetDecoder;
    private CharBuffer utf8DecodingBuffer;

    @Override // com.amazon.ion.impl.bin.utf8.Poolable, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    Utf8StringDecoder(Pool<Utf8StringDecoder> pool) {
        super(pool);
        this.reusableUtf8DecodingBuffer = CharBuffer.allocate(4096);
        this.utf8CharsetDecoder = Charset.forName(ParserUtil.UTF_8).newDecoder();
    }

    public void prepareDecode(int i) {
        this.utf8CharsetDecoder.reset();
        CharBuffer charBuffer = this.reusableUtf8DecodingBuffer;
        this.utf8DecodingBuffer = charBuffer;
        if (i > charBuffer.capacity()) {
            this.utf8DecodingBuffer = CharBuffer.allocate(i);
        }
    }

    public void partialDecode(ByteBuffer byteBuffer, boolean z) {
        CoderResult coderResultDecode = this.utf8CharsetDecoder.decode(byteBuffer, this.utf8DecodingBuffer, z);
        if (coderResultDecode.isError()) {
            throw new IonException("Illegal value encountered while validating UTF-8 data in input stream. " + coderResultDecode.toString());
        }
    }

    public String finishDecode() {
        this.utf8DecodingBuffer.flip();
        return this.utf8DecodingBuffer.toString();
    }

    public String decode(ByteBuffer byteBuffer, int i) {
        prepareDecode(i);
        this.utf8DecodingBuffer.position(0);
        CharBuffer charBuffer = this.utf8DecodingBuffer;
        charBuffer.limit(charBuffer.capacity());
        partialDecode(byteBuffer, true);
        return finishDecode();
    }
}
