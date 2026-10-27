package com.google.flatbuffers;

import com.amazon.music.destination.parser.ParserUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;

/* JADX INFO: loaded from: classes6.dex */
public class FlatBufferBuilder {
    static final Charset utf8charset = Charset.forName(ParserUtil.UTF_8);
    ByteBuffer bb;
    ByteBufferFactory bb_factory;
    ByteBuffer dst;
    CharsetEncoder encoder;
    boolean finished;
    boolean force_defaults;
    int minalign;
    boolean nested;
    int num_vtables;
    int object_start;
    int space;
    int vector_num_elems;
    int[] vtable;
    int vtable_in_use;
    int[] vtables;

    public interface ByteBufferFactory {
        ByteBuffer newByteBuffer(int i);

        default void releaseByteBuffer(ByteBuffer byteBuffer) {
        }
    }

    public FlatBufferBuilder(int i, ByteBufferFactory byteBufferFactory) {
        this.minalign = 1;
        this.vtable = null;
        this.vtable_in_use = 0;
        this.nested = false;
        this.finished = false;
        this.vtables = new int[16];
        this.num_vtables = 0;
        this.vector_num_elems = 0;
        this.force_defaults = false;
        this.encoder = utf8charset.newEncoder();
        i = i <= 0 ? 1 : i;
        this.space = i;
        this.bb_factory = byteBufferFactory;
        this.bb = byteBufferFactory.newByteBuffer(i);
    }

    public FlatBufferBuilder(int i) {
        this(i, new HeapByteBufferFactory());
    }

    public FlatBufferBuilder() {
        this(1024);
    }

    public static final class HeapByteBufferFactory implements ByteBufferFactory {
        @Override // com.google.flatbuffers.FlatBufferBuilder.ByteBufferFactory
        public ByteBuffer newByteBuffer(int i) {
            return ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
        }
    }

    static ByteBuffer growByteBuffer(ByteBuffer byteBuffer, ByteBufferFactory byteBufferFactory) {
        int iCapacity = byteBuffer.capacity();
        if (((-1073741824) & iCapacity) != 0) {
            throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
        }
        int i = iCapacity == 0 ? 1 : iCapacity << 1;
        byteBuffer.position(0);
        ByteBuffer byteBufferNewByteBuffer = byteBufferFactory.newByteBuffer(i);
        byteBufferNewByteBuffer.position(i - iCapacity);
        byteBufferNewByteBuffer.put(byteBuffer);
        return byteBufferNewByteBuffer;
    }

    public int offset() {
        return this.bb.capacity() - this.space;
    }

    public void pad(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer byteBuffer = this.bb;
            int i3 = this.space - 1;
            this.space = i3;
            byteBuffer.put(i3, (byte) 0);
        }
    }

    public void prep(int i, int i2) {
        if (i > this.minalign) {
            this.minalign = i;
        }
        int i3 = ((~((this.bb.capacity() - this.space) + i2)) + 1) & (i - 1);
        while (this.space < i3 + i + i2) {
            int iCapacity = this.bb.capacity();
            ByteBuffer byteBuffer = this.bb;
            ByteBuffer byteBufferGrowByteBuffer = growByteBuffer(byteBuffer, this.bb_factory);
            this.bb = byteBufferGrowByteBuffer;
            if (byteBuffer != byteBufferGrowByteBuffer) {
                this.bb_factory.releaseByteBuffer(byteBuffer);
            }
            this.space += this.bb.capacity() - iCapacity;
        }
        pad(i3);
    }

    public void putBoolean(boolean z) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 1;
        this.space = i;
        byteBuffer.put(i, z ? (byte) 1 : (byte) 0);
    }

    public void putByte(byte b) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 1;
        this.space = i;
        byteBuffer.put(i, b);
    }

    public void putShort(short s) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 2;
        this.space = i;
        byteBuffer.putShort(i, s);
    }

    public void putInt(int i) {
        ByteBuffer byteBuffer = this.bb;
        int i2 = this.space - 4;
        this.space = i2;
        byteBuffer.putInt(i2, i);
    }

    public void putLong(long j) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 8;
        this.space = i;
        byteBuffer.putLong(i, j);
    }

    public void addBoolean(boolean z) {
        prep(1, 0);
        putBoolean(z);
    }

    public void addByte(byte b) {
        prep(1, 0);
        putByte(b);
    }

    public void addShort(short s) {
        prep(2, 0);
        putShort(s);
    }

    public void addInt(int i) {
        prep(4, 0);
        putInt(i);
    }

    public void addLong(long j) {
        prep(8, 0);
        putLong(j);
    }

    public void addOffset(int i) {
        prep(4, 0);
        putInt((offset() - i) + 4);
    }

    public void startVector(int i, int i2, int i3) {
        notNested();
        this.vector_num_elems = i2;
        int i4 = i * i2;
        prep(4, i4);
        prep(i3, i4);
        this.nested = true;
    }

    public int endVector() {
        if (!this.nested) {
            throw new AssertionError("FlatBuffers: endVector called without startVector");
        }
        this.nested = false;
        putInt(this.vector_num_elems);
        return offset();
    }

    public int createString(CharSequence charSequence) {
        int length = (int) (charSequence.length() * this.encoder.maxBytesPerChar());
        ByteBuffer byteBuffer = this.dst;
        if (byteBuffer == null || byteBuffer.capacity() < length) {
            this.dst = ByteBuffer.allocate(Math.max(128, length));
        }
        this.dst.clear();
        CoderResult coderResultEncode = this.encoder.encode(charSequence instanceof CharBuffer ? (CharBuffer) charSequence : CharBuffer.wrap(charSequence), this.dst, true);
        if (coderResultEncode.isError()) {
            try {
                coderResultEncode.throwException();
            } catch (CharacterCodingException e) {
                throw new Error(e);
            }
        }
        this.dst.flip();
        return createString(this.dst);
    }

    public int createString(ByteBuffer byteBuffer) {
        int iRemaining = byteBuffer.remaining();
        addByte((byte) 0);
        startVector(1, iRemaining, 1);
        ByteBuffer byteBuffer2 = this.bb;
        int i = this.space - iRemaining;
        this.space = i;
        byteBuffer2.position(i);
        this.bb.put(byteBuffer);
        return endVector();
    }

    public void finished() {
        if (!this.finished) {
            throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
        }
    }

    public void notNested() {
        if (this.nested) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    public void startObject(int i) {
        notNested();
        int[] iArr = this.vtable;
        if (iArr == null || iArr.length < i) {
            this.vtable = new int[i];
        }
        this.vtable_in_use = i;
        Arrays.fill(this.vtable, 0, i, 0);
        this.nested = true;
        this.object_start = offset();
    }

    public void addBoolean(int i, boolean z, boolean z2) {
        if (this.force_defaults || z != z2) {
            addBoolean(z);
            slot(i);
        }
    }

    public void addLong(int i, long j, long j2) {
        if (this.force_defaults || j != j2) {
            addLong(j);
            slot(i);
        }
    }

    public void addOffset(int i, int i2, int i3) {
        if (this.force_defaults || i2 != i3) {
            addOffset(i2);
            slot(i);
        }
    }

    public void slot(int i) {
        this.vtable[i] = offset();
    }

    public int endObject() {
        int i;
        if (this.vtable == null || !this.nested) {
            throw new AssertionError("FlatBuffers: endObject called without startObject");
        }
        addInt(0);
        int iOffset = offset();
        int i2 = this.vtable_in_use - 1;
        while (i2 >= 0 && this.vtable[i2] == 0) {
            i2--;
        }
        int i3 = i2 + 1;
        while (i2 >= 0) {
            int i4 = this.vtable[i2];
            addShort((short) (i4 != 0 ? iOffset - i4 : 0));
            i2--;
        }
        addShort((short) (iOffset - this.object_start));
        addShort((short) ((i3 + 2) * 2));
        int i5 = 0;
        loop2: while (true) {
            if (i5 >= this.num_vtables) {
                i = 0;
                break;
            }
            int iCapacity = this.bb.capacity() - this.vtables[i5];
            int i6 = this.space;
            short s = this.bb.getShort(iCapacity);
            if (s == this.bb.getShort(i6)) {
                for (int i7 = 2; i7 < s; i7 += 2) {
                    if (this.bb.getShort(iCapacity + i7) != this.bb.getShort(i6 + i7)) {
                        break;
                    }
                }
                i = this.vtables[i5];
                break loop2;
            }
            i5++;
        }
        if (i != 0) {
            int iCapacity2 = this.bb.capacity() - iOffset;
            this.space = iCapacity2;
            this.bb.putInt(iCapacity2, i - iOffset);
        } else {
            int i8 = this.num_vtables;
            int[] iArr = this.vtables;
            if (i8 == iArr.length) {
                this.vtables = Arrays.copyOf(iArr, i8 * 2);
            }
            int[] iArr2 = this.vtables;
            int i9 = this.num_vtables;
            this.num_vtables = i9 + 1;
            iArr2[i9] = offset();
            ByteBuffer byteBuffer = this.bb;
            byteBuffer.putInt(byteBuffer.capacity() - iOffset, offset() - iOffset);
        }
        this.nested = false;
        return iOffset;
    }

    protected void finish(int i, boolean z) {
        prep(this.minalign, (z ? 4 : 0) + 4);
        addOffset(i);
        if (z) {
            addInt(this.bb.capacity() - this.space);
        }
        this.bb.position(this.space);
        this.finished = true;
    }

    public void finish(int i) {
        finish(i, false);
    }

    public ByteBuffer dataBuffer() {
        finished();
        return this.bb;
    }
}
