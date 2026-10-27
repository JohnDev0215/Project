package com.google.flatbuffers;

import com.amazon.music.destination.parser.ParserUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/* JADX INFO: loaded from: classes6.dex */
public class Table {
    protected ByteBuffer bb;
    protected int bb_pos;
    private static final ThreadLocal<CharsetDecoder> UTF8_DECODER = new ThreadLocal<CharsetDecoder>() { // from class: com.google.flatbuffers.Table.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public CharsetDecoder initialValue() {
            return Charset.forName(ParserUtil.UTF_8).newDecoder();
        }
    };
    public static final ThreadLocal<Charset> UTF8_CHARSET = new ThreadLocal<Charset>() { // from class: com.google.flatbuffers.Table.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Charset initialValue() {
            return Charset.forName(ParserUtil.UTF_8);
        }
    };
    private static final ThreadLocal<CharBuffer> CHAR_BUFFER = new ThreadLocal<>();

    protected int __offset(int i) {
        int i2 = this.bb_pos;
        int i3 = i2 - this.bb.getInt(i2);
        if (i < this.bb.getShort(i3)) {
            return this.bb.getShort(i3 + i);
        }
        return 0;
    }

    protected int __indirect(int i) {
        return i + this.bb.getInt(i);
    }

    protected String __string(int i) {
        CharsetDecoder charsetDecoder = UTF8_DECODER.get();
        charsetDecoder.reset();
        int i2 = i + this.bb.getInt(i);
        ByteBuffer byteBufferOrder = this.bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int i3 = byteBufferOrder.getInt(i2);
        int i4 = i2 + 4;
        byteBufferOrder.position(i4);
        byteBufferOrder.limit(i4 + i3);
        int iMaxCharsPerByte = (int) (i3 * charsetDecoder.maxCharsPerByte());
        ThreadLocal<CharBuffer> threadLocal = CHAR_BUFFER;
        CharBuffer charBufferAllocate = threadLocal.get();
        if (charBufferAllocate == null || charBufferAllocate.capacity() < iMaxCharsPerByte) {
            charBufferAllocate = CharBuffer.allocate(iMaxCharsPerByte);
            threadLocal.set(charBufferAllocate);
        }
        charBufferAllocate.clear();
        try {
            CoderResult coderResultDecode = charsetDecoder.decode(byteBufferOrder, charBufferAllocate, true);
            if (!coderResultDecode.isUnderflow()) {
                coderResultDecode.throwException();
            }
            return charBufferAllocate.flip().toString();
        } catch (CharacterCodingException e) {
            throw new RuntimeException(e);
        }
    }

    protected int __vector_len(int i) {
        int i2 = i + this.bb_pos;
        return this.bb.getInt(i2 + this.bb.getInt(i2));
    }

    protected int __vector(int i) {
        int i2 = i + this.bb_pos;
        return i2 + this.bb.getInt(i2) + 4;
    }
}
