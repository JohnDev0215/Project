package okio;

import com.amazon.music.find.utils.PageUriUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: BufferedSink.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0004\u001a\u00020\u0003H'J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bH&J \u0010\u0007\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH&J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\rH&J\u0010\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010H&J \u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH&J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\nH&J\u0010\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\nH&J\u0010\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\nH&J\u0010\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u000eH&J\u0010\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u000eH&J\b\u0010\u001f\u001a\u00020\u001eH&J\b\u0010 \u001a\u00020\u0000H&J\b\u0010!\u001a\u00020\u0000H&J\b\u0010#\u001a\u00020\"H&R\u0014\u0010\u0004\u001a\u00020\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006&À\u0006\u0001"}, d2 = {"Lokio/BufferedSink;", "Lokio/Sink;", "Ljava/nio/channels/WritableByteChannel;", "Lokio/Buffer;", "buffer", "Lokio/ByteString;", "byteString", "write", "", PageUriUtils.SOURCE_QUERY_PARAM, "", "offset", "byteCount", "Lokio/Source;", "", "writeAll", "", "string", "writeUtf8", "beginIndex", "endIndex", "b", "writeByte", "s", "writeShort", "i", "writeInt", "v", "writeDecimalLong", "writeHexadecimalUnsignedLong", "", "flush", "emit", "emitCompleteSegments", "Ljava/io/OutputStream;", "outputStream", "getBuffer", "()Lokio/Buffer;", "okio"}, k = 1, mv = {1, 5, 1})
public interface BufferedSink extends Sink, WritableByteChannel {
    @Deprecated(level = DeprecationLevel.WARNING, message = "moved to val: use getBuffer() instead", replaceWith = @ReplaceWith(expression = "buffer", imports = {}))
    Buffer buffer();

    BufferedSink emit() throws IOException;

    BufferedSink emitCompleteSegments() throws IOException;

    @Override // okio.Sink, java.io.Flushable
    void flush() throws IOException;

    Buffer getBuffer();

    OutputStream outputStream();

    BufferedSink write(ByteString byteString) throws IOException;

    BufferedSink write(byte[] source) throws IOException;

    BufferedSink write(byte[] source, int offset, int byteCount) throws IOException;

    long writeAll(Source source) throws IOException;

    BufferedSink writeByte(int b) throws IOException;

    BufferedSink writeDecimalLong(long v) throws IOException;

    BufferedSink writeHexadecimalUnsignedLong(long v) throws IOException;

    BufferedSink writeInt(int i) throws IOException;

    BufferedSink writeShort(int s) throws IOException;

    BufferedSink writeUtf8(String string) throws IOException;

    BufferedSink writeUtf8(String string, int beginIndex, int endIndex) throws IOException;
}
