package okio;

import com.amazon.music.find.utils.PageUriUtils;
import com.google.firebase.perf.plugin.util.AsmString;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import okio.internal._BufferKt;

/* JADX INFO: compiled from: Buffer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001~B\u0007¢\u0006\u0004\b|\u0010}J\b\u0010\u0005\u001a\u00020\u0000H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0000H\u0016J\b\u0010\t\u001a\u00020\u0000H\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\u0001H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J \u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u0017\u001a\u00020\fJ\b\u0010\u0019\u001a\u00020\u0018H\u0016J\u0018\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\fH\u0087\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010\u001f\u001a\u00020\u001eH\u0016J\b\u0010!\u001a\u00020 H\u0016J\b\u0010\"\u001a\u00020\fH\u0016J\b\u0010#\u001a\u00020\u001eH\u0016J\b\u0010$\u001a\u00020 H\u0016J\b\u0010%\u001a\u00020\fH\u0016J\b\u0010&\u001a\u00020\fH\u0016J\b\u0010(\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020'2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010+\u001a\u00020 2\u0006\u0010*\u001a\u00020)H\u0016J\u0018\u0010-\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010/\u001a\u00020\f2\u0006\u0010,\u001a\u00020.H\u0016J\b\u00101\u001a\u000200H\u0016J\u0010\u00101\u001a\u0002002\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u00104\u001a\u0002002\u0006\u00103\u001a\u000202H\u0016J\u0018\u00104\u001a\u0002002\u0006\u0010\r\u001a\u00020\f2\u0006\u00103\u001a\u000202H\u0016J\b\u00105\u001a\u000200H\u0016J\u0010\u00105\u001a\u0002002\u0006\u00106\u001a\u00020\fH\u0016J\b\u00107\u001a\u00020 H\u0016J\b\u00109\u001a\u000208H\u0016J\u0010\u00109\u001a\u0002082\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010-\u001a\u00020\u000e2\u0006\u0010,\u001a\u000208H\u0016J \u0010:\u001a\u00020 2\u0006\u0010,\u001a\u0002082\u0006\u0010\u0015\u001a\u00020 2\u0006\u0010\r\u001a\u00020 H\u0016J\u0010\u0010:\u001a\u00020 2\u0006\u0010,\u001a\u00020;H\u0016J\u0006\u0010<\u001a\u00020\u000eJ\u0010\u0010=\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010?\u001a\u00020\u00002\u0006\u0010>\u001a\u00020'H\u0016J\u0010\u0010A\u001a\u00020\u00002\u0006\u0010@\u001a\u000200H\u0016J \u0010A\u001a\u00020\u00002\u0006\u0010@\u001a\u0002002\u0006\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020 H\u0016J\u0010\u0010E\u001a\u00020\u00002\u0006\u0010D\u001a\u00020 H\u0016J\u0018\u0010F\u001a\u00020\u00002\u0006\u0010@\u001a\u0002002\u0006\u00103\u001a\u000202H\u0016J(\u0010F\u001a\u00020\u00002\u0006\u0010@\u001a\u0002002\u0006\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020 2\u0006\u00103\u001a\u000202H\u0016J\u0010\u0010?\u001a\u00020\u00002\u0006\u0010G\u001a\u000208H\u0016J \u0010?\u001a\u00020\u00002\u0006\u0010G\u001a\u0002082\u0006\u0010\u0015\u001a\u00020 2\u0006\u0010\r\u001a\u00020 H\u0016J\u0010\u0010?\u001a\u00020 2\u0006\u0010G\u001a\u00020;H\u0016J\u0010\u0010I\u001a\u00020\f2\u0006\u0010G\u001a\u00020HH\u0016J\u0018\u0010?\u001a\u00020\u00002\u0006\u0010G\u001a\u00020H2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010K\u001a\u00020\u00002\u0006\u0010J\u001a\u00020 H\u0016J\u0010\u0010M\u001a\u00020\u00002\u0006\u0010L\u001a\u00020 H\u0016J\u0010\u0010O\u001a\u00020\u00002\u0006\u0010N\u001a\u00020 H\u0016J\u0010\u0010P\u001a\u00020\u00002\u0006\u0010N\u001a\u00020 H\u0016J\u0010\u0010R\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\fH\u0016J\u0010\u0010S\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\fH\u0016J\u0010\u0010T\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\fH\u0016J\u0017\u0010Y\u001a\u00020V2\u0006\u0010U\u001a\u00020 H\u0000¢\u0006\u0004\bW\u0010XJ\u0018\u0010?\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010:\u001a\u00020\f2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\\\u001a\u00020\f2\u0006\u0010J\u001a\u00020\u00182\u0006\u0010Z\u001a\u00020\f2\u0006\u0010[\u001a\u00020\fH\u0016J\u0010\u0010\\\u001a\u00020\f2\u0006\u0010]\u001a\u00020'H\u0016J\u0018\u0010\\\u001a\u00020\f2\u0006\u0010]\u001a\u00020'2\u0006\u0010Z\u001a\u00020\fH\u0016J\u0010\u0010_\u001a\u00020\f2\u0006\u0010^\u001a\u00020'H\u0016J\u0018\u0010_\u001a\u00020\f2\u0006\u0010^\u001a\u00020'2\u0006\u0010Z\u001a\u00020\fH\u0016J\u0018\u0010`\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010]\u001a\u00020'H\u0016J(\u0010`\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010]\u001a\u00020'2\u0006\u0010a\u001a\u00020 2\u0006\u0010\r\u001a\u00020 H\u0016J\b\u0010b\u001a\u00020\u000eH\u0016J\b\u0010c\u001a\u00020\nH\u0016J\b\u0010d\u001a\u00020\u000eH\u0016J\b\u0010f\u001a\u00020eH\u0016J\u0013\u0010i\u001a\u00020\n2\b\u0010h\u001a\u0004\u0018\u00010gH\u0096\u0002J\b\u0010j\u001a\u00020 H\u0016J\b\u0010k\u001a\u000200H\u0016J\u0006\u0010l\u001a\u00020\u0000J\b\u0010m\u001a\u00020\u0000H\u0016J\u0006\u0010n\u001a\u00020'J\u000e\u0010n\u001a\u00020'2\u0006\u0010\r\u001a\u00020 J\u0012\u0010q\u001a\u00020o2\b\b\u0002\u0010p\u001a\u00020oH\u0007R\u0018\u0010r\u001a\u0004\u0018\u00010V8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\br\u0010sR*\u0010u\u001a\u00020\f2\u0006\u0010t\u001a\u00020\f8G@@X\u0086\u000e¢\u0006\u0012\n\u0004\bu\u0010v\u001a\u0004\bu\u0010w\"\u0004\bx\u0010yR\u0014\u0010\u0005\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bz\u0010{¨\u0006\u007f"}, d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "buffer", "Ljava/io/OutputStream;", "outputStream", "emitCompleteSegments", "emit", "", "exhausted", "", "byteCount", "", "require", "request", "peek", "Ljava/io/InputStream;", "inputStream", "out", "offset", "copyTo", "completeSegmentByteCount", "", "readByte", "pos", "getByte", "(J)B", "get", "", "readShort", "", "readInt", "readLong", "readShortLe", "readIntLe", "readDecimalLong", "readHexadecimalUnsignedLong", "Lokio/ByteString;", "readByteString", "Lokio/Options;", "options", "select", "sink", "readFully", "Lokio/Sink;", "readAll", "", "readUtf8", "Ljava/nio/charset/Charset;", "charset", "readString", "readUtf8LineStrict", "limit", "readUtf8CodePoint", "", "readByteArray", "read", "Ljava/nio/ByteBuffer;", "clear", "skip", "byteString", "write", "string", "writeUtf8", "beginIndex", "endIndex", "codePoint", "writeUtf8CodePoint", "writeString", PageUriUtils.SOURCE_QUERY_PARAM, "Lokio/Source;", "writeAll", "b", "writeByte", "s", "writeShort", "i", "writeInt", "writeIntLe", "v", "writeLong", "writeDecimalLong", "writeHexadecimalUnsignedLong", "minimumCapacity", "Lokio/Segment;", "writableSegment$okio", "(I)Lokio/Segment;", "writableSegment", "fromIndex", "toIndex", "indexOf", "bytes", "targetBytes", "indexOfElement", "rangeEquals", "bytesOffset", "flush", "isOpen", "close", "Lokio/Timeout;", "timeout", "", "other", "equals", "hashCode", "toString", "copy", "clone", "snapshot", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readAndWriteUnsafe", "head", "Lokio/Segment;", "<set-?>", "size", "J", "()J", "setSize$okio", "(J)V", "getBuffer", "()Lokio/Buffer;", "<init>", "()V", "UnsafeCursor", "okio"}, k = 1, mv = {1, 5, 1})
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {

    @JvmField
    public Segment head;
    private long size;

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.BufferedSink
    public Buffer emit() {
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer getBuffer() {
        return this;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @JvmName(name = "size")
    /* JADX INFO: renamed from: size, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    public final void setSize$okio(long j) {
        this.size = j;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.Buffer.outputStream.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.OutputStream
            public void write(int b) {
                Buffer.this.writeByte(b);
            }

            @Override // java.io.OutputStream
            public void write(byte[] data, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(data, "data");
                Buffer.this.write(data, offset, byteCount);
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }
        };
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    public long indexOfElement(ByteString targetBytes, long fromIndex) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long size = 0;
        if (fromIndex >= 0) {
            Segment segment = this.head;
            if (segment == null) {
                return -1L;
            }
            if (getSize() - fromIndex < fromIndex) {
                size = getSize();
                while (size > fromIndex) {
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    size -= (long) (segment.limit - segment.pos);
                }
                if (targetBytes.size() == 2) {
                    byte b = targetBytes.getByte(0);
                    byte b2 = targetBytes.getByte(1);
                    while (size < getSize()) {
                        byte[] bArr = segment.data;
                        i = (int) ((((long) segment.pos) + fromIndex) - size);
                        int i3 = segment.limit;
                        while (i < i3) {
                            byte b3 = bArr[i];
                            if (b3 == b || b3 == b2) {
                                i2 = segment.pos;
                            } else {
                                i++;
                            }
                        }
                        size += (long) (segment.limit - segment.pos);
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        fromIndex = size;
                    }
                    return -1L;
                }
                byte[] bArrInternalArray$okio = targetBytes.internalArray$okio();
                while (size < getSize()) {
                    byte[] bArr2 = segment.data;
                    i = (int) ((((long) segment.pos) + fromIndex) - size);
                    int i4 = segment.limit;
                    while (i < i4) {
                        byte b4 = bArr2[i];
                        int length = bArrInternalArray$okio.length;
                        int i5 = 0;
                        while (i5 < length) {
                            byte b5 = bArrInternalArray$okio[i5];
                            i5++;
                            if (b4 == b5) {
                                i2 = segment.pos;
                            }
                        }
                        i++;
                    }
                    size += (long) (segment.limit - segment.pos);
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    fromIndex = size;
                }
                return -1L;
            }
            while (true) {
                long j = ((long) (segment.limit - segment.pos)) + size;
                if (j > fromIndex) {
                    break;
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                size = j;
            }
            if (targetBytes.size() == 2) {
                byte b6 = targetBytes.getByte(0);
                byte b7 = targetBytes.getByte(1);
                while (size < getSize()) {
                    byte[] bArr3 = segment.data;
                    i = (int) ((((long) segment.pos) + fromIndex) - size);
                    int i6 = segment.limit;
                    while (i < i6) {
                        byte b8 = bArr3[i];
                        if (b8 == b6 || b8 == b7) {
                            i2 = segment.pos;
                        } else {
                            i++;
                        }
                    }
                    size += (long) (segment.limit - segment.pos);
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    fromIndex = size;
                }
                return -1L;
            }
            byte[] bArrInternalArray$okio2 = targetBytes.internalArray$okio();
            while (size < getSize()) {
                byte[] bArr4 = segment.data;
                i = (int) ((((long) segment.pos) + fromIndex) - size);
                int i7 = segment.limit;
                while (i < i7) {
                    byte b9 = bArr4[i];
                    int length2 = bArrInternalArray$okio2.length;
                    int i8 = 0;
                    while (i8 < length2) {
                        byte b10 = bArrInternalArray$okio2[i8];
                        i8++;
                        if (b9 == b10) {
                            i2 = segment.pos;
                        }
                    }
                    i++;
                }
                size += (long) (segment.limit - segment.pos);
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                fromIndex = size;
            }
            return -1L;
            return ((long) (i - i2)) + size;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("fromIndex < 0: ", Long.valueOf(fromIndex)).toString());
    }

    @Override // okio.BufferedSource
    public void require(long byteCount) throws EOFException {
        if (this.size < byteCount) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public boolean request(long byteCount) {
        return this.size >= byteCount;
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.Buffer.inputStream.1
            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                if (Buffer.this.getSize() > 0) {
                    return Buffer.this.readByte() & 255;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(byte[] sink, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                return Buffer.this.read(sink, offset, byteCount);
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.getSize(), Integer.MAX_VALUE);
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    public static /* synthetic */ UnsafeCursor readAndWriteUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = _UtilKt.getDEFAULT__new_UnsafeCursor();
        }
        return buffer.readAndWriteUnsafe(unsafeCursor);
    }

    public final Buffer copyTo(Buffer out, long offset, long byteCount) {
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkOffsetAndCount(getSize(), offset, byteCount);
        if (byteCount != 0) {
            out.setSize$okio(out.getSize() + byteCount);
            Segment segment = this.head;
            while (true) {
                Intrinsics.checkNotNull(segment);
                int i = segment.limit;
                int i2 = segment.pos;
                if (offset < i - i2) {
                    break;
                }
                offset -= (long) (i - i2);
                segment = segment.next;
            }
            while (byteCount > 0) {
                Intrinsics.checkNotNull(segment);
                Segment segmentSharedCopy = segment.sharedCopy();
                int i3 = segmentSharedCopy.pos + ((int) offset);
                segmentSharedCopy.pos = i3;
                segmentSharedCopy.limit = Math.min(i3 + ((int) byteCount), segmentSharedCopy.limit);
                Segment segment2 = out.head;
                if (segment2 == null) {
                    segmentSharedCopy.prev = segmentSharedCopy;
                    segmentSharedCopy.next = segmentSharedCopy;
                    out.head = segmentSharedCopy;
                } else {
                    Intrinsics.checkNotNull(segment2);
                    Segment segment3 = segment2.prev;
                    Intrinsics.checkNotNull(segment3);
                    segment3.push(segmentSharedCopy);
                }
                byteCount -= (long) (segmentSharedCopy.limit - segmentSharedCopy.pos);
                segment = segment.next;
                offset = 0;
            }
        }
        return this;
    }

    public short readShortLe() throws EOFException {
        return _UtilKt.reverseBytes(readShort());
    }

    public int readIntLe() throws EOFException {
        return _UtilKt.reverseBytes(readInt());
    }

    public final long completeSegmentByteCount() {
        long size = getSize();
        if (size == 0) {
            return 0L;
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        int i = segment2.limit;
        if (i < 8192 && segment2.owner) {
            size -= (long) (i - segment2.pos);
        }
        return size;
    }

    @Override // okio.BufferedSource
    public byte readByte() throws EOFException {
        if (getSize() == 0) {
            throw new EOFException();
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i = segment.pos;
        int i2 = segment.limit;
        int i3 = i + 1;
        byte b = segment.data[i];
        setSize$okio(getSize() - 1);
        if (i3 == i2) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i3;
        }
        return b;
    }

    public String readUtf8() {
        return readString(this.size, Charsets.UTF_8);
    }

    public String readUtf8(long byteCount) throws EOFException {
        return readString(byteCount, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return readString(this.size, charset);
    }

    public String readString(long byteCount, Charset charset) throws EOFException {
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (!(byteCount >= 0 && byteCount <= 2147483647L)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(byteCount)).toString());
        }
        if (this.size < byteCount) {
            throw new EOFException();
        }
        if (byteCount == 0) {
            return "";
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i = segment.pos;
        if (((long) i) + byteCount > segment.limit) {
            return new String(readByteArray(byteCount), charset);
        }
        int i2 = (int) byteCount;
        String str = new String(segment.data, i, i2, charset);
        int i3 = segment.pos + i2;
        segment.pos = i3;
        this.size -= byteCount;
        if (i3 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return str;
    }

    @Override // okio.BufferedSource
    public short readShort() throws EOFException {
        if (getSize() < 2) {
            throw new EOFException();
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i = segment.pos;
        int i2 = segment.limit;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = segment.data;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        setSize$okio(getSize() - 2);
        if (i4 == i2) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i4;
        }
        return (short) i5;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public int readInt() throws EOFException {
        if (getSize() < 4) {
            throw new EOFException();
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i = segment.pos;
        int i2 = segment.limit;
        if (i2 - i < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = segment.data;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        setSize$okio(getSize() - 4);
        if (i8 == i2) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i8;
        }
        return i9;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int iMin = Math.min(sink.remaining(), segment.limit - segment.pos);
        sink.put(segment.data, segment.pos, iMin);
        int i = segment.pos + iMin;
        segment.pos = i;
        this.size -= (long) iMin;
        if (i == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return iMin;
    }

    @Override // okio.BufferedSource
    public long readLong() throws EOFException {
        if (getSize() < 8) {
            throw new EOFException();
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i = segment.pos;
        int i2 = segment.limit;
        if (i2 - i < 8) {
            return ((((long) readInt()) & 4294967295L) << 32) | (4294967295L & ((long) readInt()));
        }
        byte[] bArr = segment.data;
        int i3 = i + 1;
        long j = (((long) bArr[i]) & 255) << 56;
        int i4 = i3 + 1;
        int i5 = i4 + 1;
        long j2 = j | ((((long) bArr[i3]) & 255) << 48) | ((((long) bArr[i4]) & 255) << 40);
        int i6 = i5 + 1;
        int i7 = i6 + 1;
        long j3 = j2 | ((((long) bArr[i5]) & 255) << 32) | ((((long) bArr[i6]) & 255) << 24);
        int i8 = i7 + 1;
        long j4 = j3 | ((((long) bArr[i7]) & 255) << 16);
        int i9 = i8 + 1;
        long j5 = j4 | ((((long) bArr[i8]) & 255) << 8);
        int i10 = i9 + 1;
        long j6 = j5 | (((long) bArr[i9]) & 255);
        setSize$okio(getSize() - 8);
        if (i10 == i2) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i10;
        }
        return j6;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return writeUtf8(string, 0, string.length());
    }

    public Buffer writeString(String string, Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return writeString(string, 0, string.length(), charset);
    }

    public Buffer writeString(String string, int beginIndex, int endIndex, Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (!(beginIndex >= 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("beginIndex < 0: ", Integer.valueOf(beginIndex)).toString());
        }
        if (!(endIndex >= beginIndex)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + endIndex + " < " + beginIndex).toString());
        }
        if (!(endIndex <= string.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + endIndex + " > " + string.length()).toString());
        }
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            return writeUtf8(string, beginIndex, endIndex);
        }
        String strSubstring = string.substring(beginIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = strSubstring.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return write(bytes, 0, bytes.length);
    }

    @JvmName(name = "getByte")
    public final byte getByte(long pos) {
        _UtilKt.checkOffsetAndCount(getSize(), pos, 1L);
        Segment segment = this.head;
        if (segment == null) {
            Intrinsics.checkNotNull(null);
            throw null;
        }
        if (getSize() - pos < pos) {
            long size = getSize();
            while (size > pos) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size -= (long) (segment.limit - segment.pos);
            }
            Intrinsics.checkNotNull(segment);
            return segment.data[(int) ((((long) segment.pos) + pos) - size)];
        }
        long j = 0;
        while (true) {
            long j2 = ((long) (segment.limit - segment.pos)) + j;
            if (j2 <= pos) {
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j = j2;
            } else {
                Intrinsics.checkNotNull(segment);
                return segment.data[(int) ((((long) segment.pos) + pos) - j)];
            }
        }
    }

    public final void clear() throws EOFException {
        skip(getSize());
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        int iRemaining = source.remaining();
        int i = iRemaining;
        while (i > 0) {
            Segment segmentWritableSegment$okio = writableSegment$okio(1);
            int iMin = Math.min(i, 8192 - segmentWritableSegment$okio.limit);
            source.get(segmentWritableSegment$okio.data, segmentWritableSegment$okio.limit, iMin);
            i -= iMin;
            segmentWritableSegment$okio.limit += iMin;
        }
        this.size += (long) iRemaining;
        return iRemaining;
    }

    @Override // okio.BufferedSource
    public void skip(long byteCount) throws EOFException {
        while (byteCount > 0) {
            Segment segment = this.head;
            if (segment != null) {
                int iMin = (int) Math.min(byteCount, segment.limit - segment.pos);
                long j = iMin;
                setSize$okio(getSize() - j);
                byteCount -= j;
                int i = segment.pos + iMin;
                segment.pos = i;
                if (i == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(this, 0, byteString.size());
        return this;
    }

    public Buffer writeIntLe(int i) {
        return writeInt(_UtilKt.reverseBytes(i));
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long v) {
        if (v == 0) {
            return writeByte(48);
        }
        boolean z = false;
        int i = 1;
        if (v < 0) {
            v = -v;
            if (v < 0) {
                return writeUtf8("-9223372036854775808");
            }
            z = true;
        }
        if (v >= 100000000) {
            i = v < 1000000000000L ? v < 10000000000L ? v < 1000000000 ? 9 : 10 : v < 100000000000L ? 11 : 12 : v < 1000000000000000L ? v < 10000000000000L ? 13 : v < 100000000000000L ? 14 : 15 : v < 100000000000000000L ? v < 10000000000000000L ? 16 : 17 : v < 1000000000000000000L ? 18 : 19;
        } else if (v >= 10000) {
            i = v < 1000000 ? v < 100000 ? 5 : 6 : v < 10000000 ? 7 : 8;
        } else if (v >= 100) {
            i = v < 1000 ? 3 : 4;
        } else if (v >= 10) {
            i = 2;
        }
        if (z) {
            i++;
        }
        Segment segmentWritableSegment$okio = writableSegment$okio(i);
        byte[] bArr = segmentWritableSegment$okio.data;
        int i2 = segmentWritableSegment$okio.limit + i;
        while (v != 0) {
            long j = 10;
            i2--;
            bArr[i2] = _BufferKt.getHEX_DIGIT_BYTES()[(int) (v % j)];
            v /= j;
        }
        if (z) {
            bArr[i2 - 1] = (byte) 45;
        }
        segmentWritableSegment$okio.limit += i;
        setSize$okio(getSize() + ((long) i));
        return this;
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString bytes) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return indexOf(bytes, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString targetBytes) {
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        return indexOfElement(targetBytes, 0L);
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return rangeEquals(offset, bytes, 0, bytes.size());
    }

    @Override // okio.Source
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long v) {
        if (v == 0) {
            return writeByte(48);
        }
        long j = (v >>> 1) | v;
        long j2 = j | (j >>> 2);
        long j3 = j2 | (j2 >>> 4);
        long j4 = j3 | (j3 >>> 8);
        long j5 = j4 | (j4 >>> 16);
        long j6 = j5 | (j5 >>> 32);
        long j7 = j6 - ((j6 >>> 1) & 6148914691236517205L);
        long j8 = ((j7 >>> 2) & 3689348814741910323L) + (j7 & 3689348814741910323L);
        long j9 = ((j8 >>> 4) + j8) & 1085102592571150095L;
        long j10 = j9 + (j9 >>> 8);
        long j11 = j10 + (j10 >>> 16);
        int i = (int) ((((j11 & 63) + ((j11 >>> 32) & 63)) + ((long) 3)) / ((long) 4));
        Segment segmentWritableSegment$okio = writableSegment$okio(i);
        byte[] bArr = segmentWritableSegment$okio.data;
        int i2 = segmentWritableSegment$okio.limit;
        for (int i3 = (i2 + i) - 1; i3 >= i2; i3--) {
            bArr[i3] = _BufferKt.getHEX_DIGIT_BYTES()[(int) (15 & v)];
            v >>>= 4;
        }
        segmentWritableSegment$okio.limit += i;
        setSize$okio(getSize() + ((long) i));
        return this;
    }

    public final Segment writableSegment$okio(int minimumCapacity) {
        if (!(minimumCapacity >= 1 && minimumCapacity <= 8192)) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        Segment segment = this.head;
        if (segment == null) {
            Segment segmentTake = SegmentPool.take();
            this.head = segmentTake;
            segmentTake.prev = segmentTake;
            segmentTake.next = segmentTake;
            return segmentTake;
        }
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        return (segment2.limit + minimumCapacity > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return write(source, 0, source.length);
    }

    public String toString() {
        return snapshot().toString();
    }

    public Buffer clone() {
        return copy();
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] source, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = byteCount;
        _UtilKt.checkOffsetAndCount(source.length, offset, j);
        int i = byteCount + offset;
        while (offset < i) {
            Segment segmentWritableSegment$okio = writableSegment$okio(1);
            int iMin = Math.min(i - offset, 8192 - segmentWritableSegment$okio.limit);
            int i2 = offset + iMin;
            ArraysKt___ArraysJvmKt.copyInto(source, segmentWritableSegment$okio.data, segmentWritableSegment$okio.limit, offset, i2);
            segmentWritableSegment$okio.limit += iMin;
            offset = i2;
        }
        setSize$okio(getSize() + j);
        return this;
    }

    @JvmOverloads
    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return _BufferKt.commonReadAndWriteUnsafe(this, unsafeCursor);
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        return readByteArray(getSize());
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long byteCount) throws EOFException {
        if (!(byteCount >= 0 && byteCount <= 2147483647L)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(byteCount)).toString());
        }
        if (getSize() < byteCount) {
            throw new EOFException();
        }
        byte[] bArr = new byte[(int) byteCount];
        readFully(bArr);
        return bArr;
    }

    /* JADX INFO: compiled from: Buffer.kt */
    @Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001f\u0010 J\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\b\u0010\n\u001a\u00020\tH\u0016R\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R$\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001d¨\u0006!"}, d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "", "next", "", "offset", "seek", "newSize", "resizeBuffer", "", "close", "Lokio/Buffer;", "buffer", "Lokio/Buffer;", "", "readWrite", "Z", "Lokio/Segment;", "segment", "Lokio/Segment;", "getSegment$okio", "()Lokio/Segment;", "setSegment$okio", "(Lokio/Segment;)V", "J", "", "data", "[B", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "I", "end", "<init>", "()V", "okio"}, k = 1, mv = {1, 5, 1})
    public static final class UnsafeCursor implements Closeable {

        @JvmField
        public Buffer buffer;

        @JvmField
        public byte[] data;

        @JvmField
        public boolean readWrite;
        private Segment segment;

        @JvmField
        public long offset = -1;

        @JvmField
        public int start = -1;

        @JvmField
        public int end = -1;

        /* JADX INFO: renamed from: getSegment$okio, reason: from getter */
        public final Segment getSegment() {
            return this.segment;
        }

        public final void setSegment$okio(Segment segment) {
            this.segment = segment;
        }

        public final int next() {
            long j = this.offset;
            Buffer buffer = this.buffer;
            Intrinsics.checkNotNull(buffer);
            if (!(j != buffer.getSize())) {
                throw new IllegalStateException("no more bytes".toString());
            }
            long j2 = this.offset;
            return seek(j2 == -1 ? 0L : j2 + ((long) (this.end - this.start)));
        }

        public final int seek(long offset) {
            Segment segmentPush;
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            if (offset < -1 || offset > buffer.getSize()) {
                throw new ArrayIndexOutOfBoundsException("offset=" + offset + " > size=" + buffer.getSize());
            }
            if (offset == -1 || offset == buffer.getSize()) {
                setSegment$okio(null);
                this.offset = offset;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return -1;
            }
            long j = 0;
            long size = buffer.getSize();
            Segment segment = buffer.head;
            if (getSegment() != null) {
                long j2 = this.offset;
                int i = this.start;
                Segment segment2 = getSegment();
                Intrinsics.checkNotNull(segment2);
                long j3 = j2 - ((long) (i - segment2.pos));
                if (j3 > offset) {
                    segment = getSegment();
                    size = j3;
                    segmentPush = segment;
                } else {
                    segmentPush = getSegment();
                    j = j3;
                }
            } else {
                segmentPush = segment;
            }
            if (size - offset > offset - j) {
                while (true) {
                    Intrinsics.checkNotNull(segmentPush);
                    int i2 = segmentPush.limit;
                    int i3 = segmentPush.pos;
                    if (offset < ((long) (i2 - i3)) + j) {
                        break;
                    }
                    j += (long) (i2 - i3);
                    segmentPush = segmentPush.next;
                }
            } else {
                while (size > offset) {
                    Intrinsics.checkNotNull(segment);
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    size -= (long) (segment.limit - segment.pos);
                }
                j = size;
                segmentPush = segment;
            }
            if (this.readWrite) {
                Intrinsics.checkNotNull(segmentPush);
                if (segmentPush.shared) {
                    Segment segmentUnsharedCopy = segmentPush.unsharedCopy();
                    if (buffer.head == segmentPush) {
                        buffer.head = segmentUnsharedCopy;
                    }
                    segmentPush = segmentPush.push(segmentUnsharedCopy);
                    Segment segment3 = segmentPush.prev;
                    Intrinsics.checkNotNull(segment3);
                    segment3.pop();
                }
            }
            setSegment$okio(segmentPush);
            this.offset = offset;
            Intrinsics.checkNotNull(segmentPush);
            this.data = segmentPush.data;
            int i4 = segmentPush.pos + ((int) (offset - j));
            this.start = i4;
            int i5 = segmentPush.limit;
            this.end = i5;
            return i5 - i4;
        }

        public final long resizeBuffer(long newSize) {
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            if (!this.readWrite) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
            }
            long size = buffer.getSize();
            int i = 1;
            if (newSize <= size) {
                if (!(newSize >= 0)) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("newSize < 0: ", Long.valueOf(newSize)).toString());
                }
                long j = size - newSize;
                while (true) {
                    if (j <= 0) {
                        break;
                    }
                    Segment segment = buffer.head;
                    Intrinsics.checkNotNull(segment);
                    Segment segment2 = segment.prev;
                    Intrinsics.checkNotNull(segment2);
                    int i2 = segment2.limit;
                    long j2 = i2 - segment2.pos;
                    if (j2 <= j) {
                        buffer.head = segment2.pop();
                        SegmentPool.recycle(segment2);
                        j -= j2;
                    } else {
                        segment2.limit = i2 - ((int) j);
                        break;
                    }
                }
                setSegment$okio(null);
                this.offset = newSize;
                this.data = null;
                this.start = -1;
                this.end = -1;
            } else if (newSize > size) {
                long j3 = newSize - size;
                boolean z = true;
                while (j3 > 0) {
                    Segment segmentWritableSegment$okio = buffer.writableSegment$okio(i);
                    int iMin = (int) Math.min(j3, 8192 - segmentWritableSegment$okio.limit);
                    segmentWritableSegment$okio.limit += iMin;
                    j3 -= (long) iMin;
                    if (z) {
                        setSegment$okio(segmentWritableSegment$okio);
                        this.offset = size;
                        this.data = segmentWritableSegment$okio.data;
                        int i3 = segmentWritableSegment$okio.limit;
                        this.start = i3 - iMin;
                        this.end = i3;
                        z = false;
                    }
                    i = 1;
                }
            }
            buffer.setSize$okio(newSize);
            return size;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!(this.buffer != null)) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            this.buffer = null;
            setSegment$okio(null);
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] sink) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i = 0;
        while (i < sink.length) {
            int i2 = read(sink, i, sink.length - i);
            if (i2 == -1) {
                throw new EOFException();
            }
            i += i2;
        }
    }

    public int read(byte[] sink, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        _UtilKt.checkOffsetAndCount(sink.length, offset, byteCount);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int iMin = Math.min(byteCount, segment.limit - segment.pos);
        byte[] bArr = segment.data;
        int i = segment.pos;
        ArraysKt___ArraysJvmKt.copyInto(bArr, sink, offset, i, i + iMin);
        segment.pos += iMin;
        setSize$okio(getSize() - ((long) iMin));
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return iMin;
    }

    @Override // okio.BufferedSource
    public long readDecimalLong() throws EOFException {
        if (getSize() == 0) {
            throw new EOFException();
        }
        long j = -7;
        int i = 0;
        long j2 = 0;
        boolean z = false;
        boolean z2 = false;
        do {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            byte[] bArr = segment.data;
            int i2 = segment.pos;
            int i3 = segment.limit;
            while (i2 < i3) {
                byte b = bArr[i2];
                byte b2 = (byte) 48;
                if (b >= b2 && b <= ((byte) 57)) {
                    int i4 = b2 - b;
                    if (j2 < -922337203685477580L || (j2 == -922337203685477580L && i4 < j)) {
                        Buffer bufferWriteByte = new Buffer().writeDecimalLong(j2).writeByte((int) b);
                        if (!z) {
                            bufferWriteByte.readByte();
                        }
                        throw new NumberFormatException(Intrinsics.stringPlus("Number too large: ", bufferWriteByte.readUtf8()));
                    }
                    j2 = (j2 * 10) + ((long) i4);
                } else {
                    if (b != ((byte) 45) || i != 0) {
                        z2 = true;
                        break;
                    }
                    j--;
                    z = true;
                }
                i2++;
                i++;
            }
            if (i2 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i2;
            }
            if (z2) {
                break;
            }
        } while (this.head != null);
        setSize$okio(getSize() - ((long) i));
        if (i >= (z ? 2 : 1)) {
            return z ? j2 : -j2;
        }
        if (getSize() == 0) {
            throw new EOFException();
        }
        throw new NumberFormatException((z ? "Expected a digit" : "Expected a digit or '-'") + " but was 0x" + _UtilKt.toHexString(getByte(0L)));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0098 A[EDGE_INSN: B:43:0x0098->B:37:0x0098 BREAK  A[LOOP:0: B:5:0x000d->B:45:?], SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long readHexadecimalUnsignedLong() throws java.io.EOFException {
        /*
            r14 = this;
            long r0 = r14.getSize()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto La2
            r0 = 0
            r1 = r0
            r4 = r2
        Ld:
            okio.Segment r6 = r14.head
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L18:
            if (r8 >= r9) goto L84
            r10 = r7[r8]
            r11 = 48
            byte r11 = (byte) r11
            if (r10 < r11) goto L29
            r12 = 57
            byte r12 = (byte) r12
            if (r10 > r12) goto L29
            int r11 = r10 - r11
            goto L43
        L29:
            r11 = 97
            byte r11 = (byte) r11
            if (r10 < r11) goto L38
            r12 = 102(0x66, float:1.43E-43)
            byte r12 = (byte) r12
            if (r10 > r12) goto L38
        L33:
            int r11 = r10 - r11
            int r11 = r11 + 10
            goto L43
        L38:
            r11 = 65
            byte r11 = (byte) r11
            if (r10 < r11) goto L70
            r12 = 70
            byte r12 = (byte) r12
            if (r10 > r12) goto L70
            goto L33
        L43:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L53
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L18
        L53:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okio.Buffer r0 = r0.writeHexadecimalUnsignedLong(r4)
            okio.Buffer r0 = r0.writeByte(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r0 = r0.readUtf8()
            java.lang.String r2 = "Number too large: "
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r2, r0)
            r1.<init>(r0)
            throw r1
        L70:
            if (r0 == 0) goto L74
            r1 = 1
            goto L84
        L74:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = okio._UtilKt.toHexString(r10)
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r2, r1)
            r0.<init>(r1)
            throw r0
        L84:
            if (r8 != r9) goto L90
            okio.Segment r7 = r6.pop()
            r14.head = r7
            okio.SegmentPool.recycle(r6)
            goto L92
        L90:
            r6.pos = r8
        L92:
            if (r1 != 0) goto L98
            okio.Segment r6 = r14.head
            if (r6 != 0) goto Ld
        L98:
            long r1 = r14.getSize()
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.setSize$okio(r1)
            return r4
        La2:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        return readByteString(getSize());
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long byteCount) throws EOFException {
        if (!(byteCount >= 0 && byteCount <= 2147483647L)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(byteCount)).toString());
        }
        if (getSize() < byteCount) {
            throw new EOFException();
        }
        if (byteCount >= 4096) {
            ByteString byteStringSnapshot = snapshot((int) byteCount);
            skip(byteCount);
            return byteStringSnapshot;
        }
        return new ByteString(readByteArray(byteCount));
    }

    @Override // okio.BufferedSource
    public int select(Options options) throws EOFException {
        Intrinsics.checkNotNullParameter(options, "options");
        int iSelectPrefix$default = _BufferKt.selectPrefix$default(this, options, false, 2, null);
        if (iSelectPrefix$default == -1) {
            return -1;
        }
        skip(options.getByteStrings()[iSelectPrefix$default].size());
        return iSelectPrefix$default;
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer sink, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (getSize() < byteCount) {
            sink.write(this, getSize());
            throw new EOFException();
        }
        sink.write(this, byteCount);
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long size = getSize();
        if (size > 0) {
            sink.write(this, size);
        }
        return size;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long limit) throws EOFException {
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("limit < 0: ", Long.valueOf(limit)).toString());
        }
        long j = limit != Long.MAX_VALUE ? limit + 1 : Long.MAX_VALUE;
        byte b = (byte) 10;
        long jIndexOf = indexOf(b, 0L, j);
        if (jIndexOf != -1) {
            return _BufferKt.readUtf8Line(this, jIndexOf);
        }
        if (j < getSize() && getByte(j - 1) == ((byte) 13) && getByte(j) == b) {
            return _BufferKt.readUtf8Line(this, j);
        }
        Buffer buffer = new Buffer();
        copyTo(buffer, 0L, Math.min(32, getSize()));
        throw new EOFException("\\n not found: limit=" + Math.min(getSize(), limit) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
    }

    public int readUtf8CodePoint() throws EOFException {
        int i;
        int i2;
        int i3;
        if (getSize() == 0) {
            throw new EOFException();
        }
        byte b = getByte(0L);
        boolean z = false;
        if ((b & ByteCompanionObject.MIN_VALUE) == 0) {
            i = b & 127;
            i3 = 0;
            i2 = 1;
        } else if ((b & 224) == 192) {
            i = b & 31;
            i2 = 2;
            i3 = 128;
        } else if ((b & 240) == 224) {
            i = b & 15;
            i2 = 3;
            i3 = 2048;
        } else {
            if ((b & 248) != 240) {
                skip(1L);
                return 65533;
            }
            i = b & 7;
            i2 = 4;
            i3 = 65536;
        }
        long j = i2;
        if (getSize() < j) {
            throw new EOFException("size < " + i2 + ": " + getSize() + " (to read code point prefixed 0x" + _UtilKt.toHexString(b) + ')');
        }
        if (1 < i2) {
            int i4 = 1;
            while (true) {
                int i5 = i4 + 1;
                long j2 = i4;
                byte b2 = getByte(j2);
                if ((b2 & 192) != 128) {
                    skip(j2);
                    return 65533;
                }
                i = (i << 6) | (b2 & 63);
                if (i5 >= i2) {
                    break;
                }
                i4 = i5;
            }
        }
        skip(j);
        if (i > 1114111) {
            return 65533;
        }
        if (55296 <= i && i <= 57343) {
            z = true;
        }
        if (!z && i >= i3) {
            return i;
        }
        return 65533;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String string, int beginIndex, int endIndex) {
        char cCharAt;
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(beginIndex >= 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("beginIndex < 0: ", Integer.valueOf(beginIndex)).toString());
        }
        if (!(endIndex >= beginIndex)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + endIndex + " < " + beginIndex).toString());
        }
        if (!(endIndex <= string.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + endIndex + " > " + string.length()).toString());
        }
        while (beginIndex < endIndex) {
            char cCharAt2 = string.charAt(beginIndex);
            if (cCharAt2 < 128) {
                Segment segmentWritableSegment$okio = writableSegment$okio(1);
                byte[] bArr = segmentWritableSegment$okio.data;
                int i = segmentWritableSegment$okio.limit - beginIndex;
                int iMin = Math.min(endIndex, 8192 - i);
                int i2 = beginIndex + 1;
                bArr[beginIndex + i] = (byte) cCharAt2;
                while (true) {
                    beginIndex = i2;
                    if (beginIndex >= iMin || (cCharAt = string.charAt(beginIndex)) >= 128) {
                        break;
                    }
                    i2 = beginIndex + 1;
                    bArr[beginIndex + i] = (byte) cCharAt;
                }
                int i3 = segmentWritableSegment$okio.limit;
                int i4 = (i + beginIndex) - i3;
                segmentWritableSegment$okio.limit = i3 + i4;
                setSize$okio(getSize() + ((long) i4));
            } else {
                if (cCharAt2 < 2048) {
                    Segment segmentWritableSegment$okio2 = writableSegment$okio(2);
                    byte[] bArr2 = segmentWritableSegment$okio2.data;
                    int i5 = segmentWritableSegment$okio2.limit;
                    bArr2[i5] = (byte) ((cCharAt2 >> 6) | 192);
                    bArr2[i5 + 1] = (byte) ((cCharAt2 & '?') | 128);
                    segmentWritableSegment$okio2.limit = i5 + 2;
                    setSize$okio(getSize() + 2);
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    Segment segmentWritableSegment$okio3 = writableSegment$okio(3);
                    byte[] bArr3 = segmentWritableSegment$okio3.data;
                    int i6 = segmentWritableSegment$okio3.limit;
                    bArr3[i6] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i6 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | 128);
                    bArr3[i6 + 2] = (byte) ((cCharAt2 & '?') | 128);
                    segmentWritableSegment$okio3.limit = i6 + 3;
                    setSize$okio(getSize() + 3);
                } else {
                    int i7 = beginIndex + 1;
                    char cCharAt3 = i7 < endIndex ? string.charAt(i7) : (char) 0;
                    if (cCharAt2 <= 56319) {
                        if (56320 <= cCharAt3 && cCharAt3 <= 57343) {
                            int i8 = (((cCharAt2 & 1023) << 10) | (cCharAt3 & 1023)) + 65536;
                            Segment segmentWritableSegment$okio4 = writableSegment$okio(4);
                            byte[] bArr4 = segmentWritableSegment$okio4.data;
                            int i9 = segmentWritableSegment$okio4.limit;
                            bArr4[i9] = (byte) ((i8 >> 18) | 240);
                            bArr4[i9 + 1] = (byte) (((i8 >> 12) & 63) | 128);
                            bArr4[i9 + 2] = (byte) (((i8 >> 6) & 63) | 128);
                            bArr4[i9 + 3] = (byte) ((i8 & 63) | 128);
                            segmentWritableSegment$okio4.limit = i9 + 4;
                            setSize$okio(getSize() + 4);
                            beginIndex += 2;
                        }
                    }
                    writeByte(63);
                    beginIndex = i7;
                }
                beginIndex++;
            }
        }
        return this;
    }

    public Buffer writeUtf8CodePoint(int codePoint) {
        if (codePoint < 128) {
            writeByte(codePoint);
        } else if (codePoint < 2048) {
            Segment segmentWritableSegment$okio = writableSegment$okio(2);
            byte[] bArr = segmentWritableSegment$okio.data;
            int i = segmentWritableSegment$okio.limit;
            bArr[i] = (byte) ((codePoint >> 6) | 192);
            bArr[i + 1] = (byte) ((codePoint & 63) | 128);
            segmentWritableSegment$okio.limit = i + 2;
            setSize$okio(getSize() + 2);
        } else {
            boolean z = false;
            if (55296 <= codePoint && codePoint <= 57343) {
                z = true;
            }
            if (z) {
                writeByte(63);
            } else if (codePoint < 65536) {
                Segment segmentWritableSegment$okio2 = writableSegment$okio(3);
                byte[] bArr2 = segmentWritableSegment$okio2.data;
                int i2 = segmentWritableSegment$okio2.limit;
                bArr2[i2] = (byte) ((codePoint >> 12) | 224);
                bArr2[i2 + 1] = (byte) (((codePoint >> 6) & 63) | 128);
                bArr2[i2 + 2] = (byte) ((codePoint & 63) | 128);
                segmentWritableSegment$okio2.limit = i2 + 3;
                setSize$okio(getSize() + 3);
            } else if (codePoint <= 1114111) {
                Segment segmentWritableSegment$okio3 = writableSegment$okio(4);
                byte[] bArr3 = segmentWritableSegment$okio3.data;
                int i3 = segmentWritableSegment$okio3.limit;
                bArr3[i3] = (byte) ((codePoint >> 18) | 240);
                bArr3[i3 + 1] = (byte) (((codePoint >> 12) & 63) | 128);
                bArr3[i3 + 2] = (byte) (((codePoint >> 6) & 63) | 128);
                bArr3[i3 + 3] = (byte) ((codePoint & 63) | 128);
                segmentWritableSegment$okio3.limit = i3 + 4;
                setSize$okio(getSize() + 4);
            } else {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected code point: 0x", _UtilKt.toHexString(codePoint)));
            }
        }
        return this;
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long j2 = source.read(this, 8192L);
            if (j2 == -1) {
                return j;
            }
            j += j2;
        }
    }

    public Buffer write(Source source, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        while (byteCount > 0) {
            long j = source.read(this, byteCount);
            if (j == -1) {
                throw new EOFException();
            }
            byteCount -= j;
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int b) {
        Segment segmentWritableSegment$okio = writableSegment$okio(1);
        byte[] bArr = segmentWritableSegment$okio.data;
        int i = segmentWritableSegment$okio.limit;
        segmentWritableSegment$okio.limit = i + 1;
        bArr[i] = (byte) b;
        setSize$okio(getSize() + 1);
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int s) {
        Segment segmentWritableSegment$okio = writableSegment$okio(2);
        byte[] bArr = segmentWritableSegment$okio.data;
        int i = segmentWritableSegment$okio.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((s >>> 8) & 255);
        bArr[i2] = (byte) (s & 255);
        segmentWritableSegment$okio.limit = i2 + 1;
        setSize$okio(getSize() + 2);
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int i) {
        Segment segmentWritableSegment$okio = writableSegment$okio(4);
        byte[] bArr = segmentWritableSegment$okio.data;
        int i2 = segmentWritableSegment$okio.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        segmentWritableSegment$okio.limit = i5 + 1;
        setSize$okio(getSize() + 4);
        return this;
    }

    public Buffer writeLong(long v) {
        Segment segmentWritableSegment$okio = writableSegment$okio(8);
        byte[] bArr = segmentWritableSegment$okio.data;
        int i = segmentWritableSegment$okio.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((v >>> 56) & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((v >>> 48) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((v >>> 40) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((v >>> 32) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((v >>> 24) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((v >>> 16) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((v >>> 8) & 255);
        bArr[i8] = (byte) (v & 255);
        segmentWritableSegment$okio.limit = i8 + 1;
        setSize$okio(getSize() + 8);
        return this;
    }

    @Override // okio.Sink
    public void write(Buffer source, long byteCount) {
        Segment segment;
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(source != this)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        _UtilKt.checkOffsetAndCount(source.getSize(), 0L, byteCount);
        while (byteCount > 0) {
            Segment segment2 = source.head;
            Intrinsics.checkNotNull(segment2);
            int i = segment2.limit;
            Intrinsics.checkNotNull(source.head);
            if (byteCount < i - r2.pos) {
                Segment segment3 = this.head;
                if (segment3 != null) {
                    Intrinsics.checkNotNull(segment3);
                    segment = segment3.prev;
                } else {
                    segment = null;
                }
                if (segment != null && segment.owner) {
                    if ((((long) segment.limit) + byteCount) - ((long) (segment.shared ? 0 : segment.pos)) <= 8192) {
                        Segment segment4 = source.head;
                        Intrinsics.checkNotNull(segment4);
                        segment4.writeTo(segment, (int) byteCount);
                        source.setSize$okio(source.getSize() - byteCount);
                        setSize$okio(getSize() + byteCount);
                        return;
                    }
                }
                Segment segment5 = source.head;
                Intrinsics.checkNotNull(segment5);
                source.head = segment5.split((int) byteCount);
            }
            Segment segment6 = source.head;
            Intrinsics.checkNotNull(segment6);
            long j = segment6.limit - segment6.pos;
            source.head = segment6.pop();
            Segment segment7 = this.head;
            if (segment7 == null) {
                this.head = segment6;
                segment6.prev = segment6;
                segment6.next = segment6;
            } else {
                Intrinsics.checkNotNull(segment7);
                Segment segment8 = segment7.prev;
                Intrinsics.checkNotNull(segment8);
                segment8.push(segment6).compact();
            }
            source.setSize$okio(source.getSize() - j);
            setSize$okio(getSize() + j);
            byteCount -= j;
        }
    }

    @Override // okio.Source
    public long read(Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!(byteCount >= 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(byteCount)).toString());
        }
        if (getSize() == 0) {
            return -1L;
        }
        if (byteCount > getSize()) {
            byteCount = getSize();
        }
        sink.write(this, byteCount);
        return byteCount;
    }

    public long indexOf(byte b, long fromIndex, long toIndex) {
        Segment segment;
        int i;
        long size = 0;
        boolean z = false;
        if (0 <= fromIndex && fromIndex <= toIndex) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("size=" + getSize() + " fromIndex=" + fromIndex + " toIndex=" + toIndex).toString());
        }
        if (toIndex > getSize()) {
            toIndex = getSize();
        }
        long j = toIndex;
        if (fromIndex == j || (segment = this.head) == null) {
            return -1L;
        }
        if (getSize() - fromIndex < fromIndex) {
            size = getSize();
            while (size > fromIndex) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size -= (long) (segment.limit - segment.pos);
            }
            while (size < j) {
                byte[] bArr = segment.data;
                int iMin = (int) Math.min(segment.limit, (((long) segment.pos) + j) - size);
                i = (int) ((((long) segment.pos) + fromIndex) - size);
                while (i < iMin) {
                    if (bArr[i] != b) {
                        i++;
                    }
                }
                size += (long) (segment.limit - segment.pos);
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                fromIndex = size;
            }
            return -1L;
        }
        while (true) {
            long j2 = ((long) (segment.limit - segment.pos)) + size;
            if (j2 > fromIndex) {
                break;
            }
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            size = j2;
        }
        while (size < j) {
            byte[] bArr2 = segment.data;
            int iMin2 = (int) Math.min(segment.limit, (((long) segment.pos) + j) - size);
            i = (int) ((((long) segment.pos) + fromIndex) - size);
            while (i < iMin2) {
                if (bArr2[i] != b) {
                    i++;
                }
            }
            size += (long) (segment.limit - segment.pos);
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            fromIndex = size;
        }
        return -1L;
        return ((long) (i - segment.pos)) + size;
    }

    public long indexOf(ByteString bytes, long fromIndex) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (!(bytes.size() > 0)) {
            throw new IllegalArgumentException("bytes is empty".toString());
        }
        long j = 0;
        if (fromIndex >= 0) {
            Segment segment = this.head;
            if (segment != null) {
                if (getSize() - fromIndex < fromIndex) {
                    long size = getSize();
                    while (size > fromIndex) {
                        segment = segment.prev;
                        Intrinsics.checkNotNull(segment);
                        size -= (long) (segment.limit - segment.pos);
                    }
                    byte[] bArrInternalArray$okio = bytes.internalArray$okio();
                    byte b = bArrInternalArray$okio[0];
                    int size2 = bytes.size();
                    long size3 = (getSize() - ((long) size2)) + 1;
                    long j2 = size;
                    long j3 = fromIndex;
                    while (j2 < size3) {
                        byte[] bArr = segment.data;
                        int iMin = (int) Math.min(segment.limit, (((long) segment.pos) + size3) - j2);
                        int i = (int) ((((long) segment.pos) + j3) - j2);
                        if (i < iMin) {
                            while (true) {
                                int i2 = i + 1;
                                if (bArr[i] == b && _BufferKt.rangeEquals(segment, i2, bArrInternalArray$okio, 1, size2)) {
                                    return ((long) (i - segment.pos)) + j2;
                                }
                                if (i2 >= iMin) {
                                    break;
                                }
                                i = i2;
                            }
                        }
                        j2 += (long) (segment.limit - segment.pos);
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j3 = j2;
                    }
                } else {
                    while (true) {
                        long j4 = ((long) (segment.limit - segment.pos)) + j;
                        if (j4 > fromIndex) {
                            break;
                        }
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j = j4;
                    }
                    byte[] bArrInternalArray$okio2 = bytes.internalArray$okio();
                    byte b2 = bArrInternalArray$okio2[0];
                    int size4 = bytes.size();
                    long size5 = (getSize() - ((long) size4)) + 1;
                    long j5 = j;
                    long j6 = fromIndex;
                    while (j5 < size5) {
                        byte[] bArr2 = segment.data;
                        long j7 = size5;
                        int iMin2 = (int) Math.min(segment.limit, (((long) segment.pos) + size5) - j5);
                        int i3 = (int) ((((long) segment.pos) + j6) - j5);
                        if (i3 < iMin2) {
                            while (true) {
                                int i4 = i3 + 1;
                                if (bArr2[i3] == b2 && _BufferKt.rangeEquals(segment, i4, bArrInternalArray$okio2, 1, size4)) {
                                    return ((long) (i3 - segment.pos)) + j5;
                                }
                                if (i4 >= iMin2) {
                                    break;
                                }
                                i3 = i4;
                            }
                        }
                        j5 += (long) (segment.limit - segment.pos);
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j6 = j5;
                        size5 = j7;
                    }
                }
            }
            return -1L;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("fromIndex < 0: ", Long.valueOf(fromIndex)).toString());
    }

    public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (offset < 0 || bytesOffset < 0 || byteCount < 0 || getSize() - offset < byteCount || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        if (byteCount > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (getByte(((long) i) + offset) != bytes.getByte(i + bytesOffset)) {
                    return false;
                }
                if (i2 >= byteCount) {
                    break;
                }
                i = i2;
            }
        }
        return true;
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (!(other instanceof Buffer)) {
                return false;
            }
            Buffer buffer = (Buffer) other;
            if (getSize() != buffer.getSize()) {
                return false;
            }
            if (getSize() != 0) {
                Segment segment = this.head;
                Intrinsics.checkNotNull(segment);
                Segment segment2 = buffer.head;
                Intrinsics.checkNotNull(segment2);
                int i = segment.pos;
                int i2 = segment2.pos;
                long j = 0;
                while (j < getSize()) {
                    long jMin = Math.min(segment.limit - i, segment2.limit - i2);
                    if (0 < jMin) {
                        long j2 = 0;
                        while (true) {
                            j2++;
                            int i3 = i + 1;
                            int i4 = i2 + 1;
                            if (segment.data[i] != segment2.data[i2]) {
                                return false;
                            }
                            if (j2 >= jMin) {
                                i = i3;
                                i2 = i4;
                                break;
                            }
                            i = i3;
                            i2 = i4;
                        }
                    }
                    if (i == segment.limit) {
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        i = segment.pos;
                    }
                    if (i2 == segment2.limit) {
                        segment2 = segment2.next;
                        Intrinsics.checkNotNull(segment2);
                        i2 = segment2.pos;
                    }
                    j += jMin;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
        } while (segment != this.head);
        return i;
    }

    public final Buffer copy() {
        Buffer buffer = new Buffer();
        if (getSize() != 0) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            Segment segmentSharedCopy = segment.sharedCopy();
            buffer.head = segmentSharedCopy;
            segmentSharedCopy.prev = segmentSharedCopy;
            segmentSharedCopy.next = segmentSharedCopy;
            for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
                Segment segment3 = segmentSharedCopy.prev;
                Intrinsics.checkNotNull(segment3);
                Intrinsics.checkNotNull(segment2);
                segment3.push(segment2.sharedCopy());
            }
            buffer.setSize$okio(getSize());
        }
        return buffer;
    }

    public final ByteString snapshot() {
        if (!(getSize() <= 2147483647L)) {
            throw new IllegalStateException(Intrinsics.stringPlus("size > Int.MAX_VALUE: ", Long.valueOf(getSize())).toString());
        }
        return snapshot((int) getSize());
    }

    public final ByteString snapshot(int byteCount) {
        if (byteCount == 0) {
            return ByteString.EMPTY;
        }
        _UtilKt.checkOffsetAndCount(getSize(), 0L, byteCount);
        Segment segment = this.head;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < byteCount) {
            Intrinsics.checkNotNull(segment);
            int i4 = segment.limit;
            int i5 = segment.pos;
            if (i4 == i5) {
                throw new AssertionError("s.limit == s.pos");
            }
            i2 += i4 - i5;
            i3++;
            segment = segment.next;
        }
        byte[][] bArr = new byte[i3][];
        int[] iArr = new int[i3 * 2];
        Segment segment2 = this.head;
        int i6 = 0;
        while (i < byteCount) {
            Intrinsics.checkNotNull(segment2);
            bArr[i6] = segment2.data;
            i += segment2.limit - segment2.pos;
            iArr[i6] = Math.min(i, byteCount);
            iArr[i6 + i3] = segment2.pos;
            segment2.shared = true;
            i6++;
            segment2 = segment2.next;
        }
        return new SegmentedByteString(bArr, iArr);
    }
}
