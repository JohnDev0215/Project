package okio;

import com.amazon.music.find.utils.PageUriUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: okio.RealBufferedSink, reason: from toString */
/* JADX INFO: compiled from: RealBufferedSink.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010,\u001a\u00020+¢\u0006\u0004\b6\u00107J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u000bH\u0016J \u0010\r\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0011H\u0016J \u0010\b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0016J\u0010\u0010\b\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0014H\u0016J\u0010\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\u0010\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u000eH\u0016J\u0010\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u0010\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010 \u001a\u00020\u0001H\u0016J\b\u0010!\u001a\u00020\u0001H\u0016J\b\u0010#\u001a\u00020\"H\u0016J\b\u0010$\u001a\u00020\u0007H\u0016J\b\u0010&\u001a\u00020%H\u0016J\b\u0010'\u001a\u00020\u0007H\u0016J\b\u0010)\u001a\u00020(H\u0016J\b\u0010*\u001a\u00020\u000bH\u0016R\u0014\u0010,\u001a\u00020+8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020%8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u001b\u0010\u0003\u001a\u00020\u00028Ö\u0002X\u0096\u0004¢\u0006\f\u0012\u0004\b4\u00105\u001a\u0004\b2\u00103¨\u00068"}, d2 = {"Lokio/RealBufferedSink;", "Lokio/BufferedSink;", "Lokio/Buffer;", "buffer", PageUriUtils.SOURCE_QUERY_PARAM, "", "byteCount", "", "write", "Lokio/ByteString;", "byteString", "", "string", "writeUtf8", "", "beginIndex", "endIndex", "", "offset", "Ljava/nio/ByteBuffer;", "Lokio/Source;", "writeAll", "b", "writeByte", "s", "writeShort", "i", "writeInt", "writeIntLe", "v", "writeDecimalLong", "writeHexadecimalUnsignedLong", "emitCompleteSegments", "emit", "Ljava/io/OutputStream;", "outputStream", "flush", "", "isOpen", "close", "Lokio/Timeout;", "timeout", "toString", "Lokio/Sink;", "sink", "Lokio/Sink;", "bufferField", "Lokio/Buffer;", "closed", "Z", "getBuffer", "()Lokio/Buffer;", "getBuffer$annotations", "()V", "<init>", "(Lokio/Sink;)V", "okio"}, k = 1, mv = {1, 5, 1})
public final class buffer implements BufferedSink {

    @JvmField
    public final Buffer bufferField;

    @JvmField
    public boolean closed;

    @JvmField
    public final Sink sink;

    @Override // okio.Sink
    public void write(Buffer source, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.write(source, byteCount);
        emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink write(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.write(byteString);
        return emitCompleteSegments();
    }

    public buffer(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.sink = sink;
        this.bufferField = new Buffer();
    }

    @Override // okio.BufferedSink
    public Buffer getBuffer() {
        return this.bufferField;
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long j2 = source.read(this.bufferField, 8192L);
            if (j2 == -1) {
                return j;
            }
            j += j2;
            emitCompleteSegments();
        }
    }

    @Override // okio.BufferedSink
    /* JADX INFO: renamed from: buffer, reason: from getter */
    public Buffer getBufferField() {
        return this.bufferField;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.writeUtf8(string);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String string, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.writeUtf8(string, beginIndex, endIndex);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.write(source);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] source, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.write(source, offset, byteCount);
        return emitCompleteSegments();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        int iWrite = this.bufferField.write(source);
        emitCompleteSegments();
        return iWrite;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.RealBufferedSink.outputStream.1
            @Override // java.io.OutputStream
            public void write(int b) throws IOException {
                buffer bufferVar = buffer.this;
                if (bufferVar.closed) {
                    throw new IOException("closed");
                }
                bufferVar.bufferField.writeByte((int) ((byte) b));
                buffer.this.emitCompleteSegments();
            }

            @Override // java.io.OutputStream
            public void write(byte[] data, int offset, int byteCount) throws IOException {
                Intrinsics.checkNotNullParameter(data, "data");
                buffer bufferVar = buffer.this;
                if (bufferVar.closed) {
                    throw new IOException("closed");
                }
                bufferVar.bufferField.write(data, offset, byteCount);
                buffer.this.emitCompleteSegments();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                buffer bufferVar = buffer.this;
                if (bufferVar.closed) {
                    return;
                }
                bufferVar.flush();
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws Throwable {
                buffer.this.close();
            }

            public String toString() {
                return buffer.this + ".outputStream()";
            }
        };
    }

    @Override // okio.BufferedSink
    public BufferedSink writeByte(int b) {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.writeByte(b);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShort(int s) {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.writeShort(s);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeInt(int i) {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.writeInt(i);
        return emitCompleteSegments();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    public BufferedSink writeIntLe(int i) {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.writeIntLe(i);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeDecimalLong(long v) {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.writeDecimalLong(v);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink writeHexadecimalUnsignedLong(long v) {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        this.bufferField.writeHexadecimalUnsignedLong(v);
        return emitCompleteSegments();
    }

    @Override // okio.BufferedSink
    public BufferedSink emitCompleteSegments() throws IOException {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        long jCompleteSegmentByteCount = this.bufferField.completeSegmentByteCount();
        if (jCompleteSegmentByteCount > 0) {
            this.sink.write(this.bufferField, jCompleteSegmentByteCount);
        }
        return this;
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() throws IOException {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        long size = this.bufferField.getSize();
        if (size > 0) {
            this.sink.write(this.bufferField, size);
        }
        return this;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (this.bufferField.getSize() > 0) {
            Sink sink = this.sink;
            Buffer buffer = this.bufferField;
            sink.write(buffer, buffer.getSize());
        }
        this.sink.flush();
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        Throwable th = null;
        try {
            if (this.bufferField.getSize() > 0) {
                Sink sink = this.sink;
                Buffer buffer = this.bufferField;
                sink.write(buffer, buffer.getSize());
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.sink.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.closed = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // okio.Sink
    /* JADX INFO: renamed from: timeout */
    public Timeout getTimeout() {
        return this.sink.getTimeout();
    }

    public String toString() {
        return "buffer(" + this.sink + ')';
    }
}
