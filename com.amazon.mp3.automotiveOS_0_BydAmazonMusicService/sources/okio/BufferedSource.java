package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: BufferedSource.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0004\u001a\u00020\u0003H'J\b\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H&J\b\u0010\r\u001a\u00020\fH&J\b\u0010\u000f\u001a\u00020\u000eH&J\b\u0010\u0011\u001a\u00020\u0010H&J\b\u0010\u0012\u001a\u00020\u0007H&J\b\u0010\u0013\u001a\u00020\u0007H&J\b\u0010\u0014\u001a\u00020\u0007H&J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H&J\b\u0010\u0017\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0018H&J\b\u0010\u001c\u001a\u00020\u001bH&J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001bH&J\u0018\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010 \u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001fH&J\b\u0010\"\u001a\u00020!H&J\u0010\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u0007H&J\u0010\u0010&\u001a\u00020!2\u0006\u0010%\u001a\u00020$H&J\u0010\u0010(\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0016H&J\u0010\u0010*\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u0016H&J\u0018\u0010,\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0016H&J\b\u0010-\u001a\u00020\u0000H&J\b\u0010/\u001a\u00020.H&R\u0014\u0010\u0004\u001a\u00020\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b0\u00101ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00062À\u0006\u0001"}, d2 = {"Lokio/BufferedSource;", "Lokio/Source;", "Ljava/nio/channels/ReadableByteChannel;", "Lokio/Buffer;", "buffer", "", "exhausted", "", "byteCount", "", "require", "request", "", "readByte", "", "readShort", "", "readInt", "readLong", "readDecimalLong", "readHexadecimalUnsignedLong", "skip", "Lokio/ByteString;", "readByteString", "Lokio/Options;", "options", "select", "", "readByteArray", "sink", "readFully", "Lokio/Sink;", "readAll", "", "readUtf8LineStrict", "limit", "Ljava/nio/charset/Charset;", "charset", "readString", "bytes", "indexOf", "targetBytes", "indexOfElement", "offset", "rangeEquals", "peek", "Ljava/io/InputStream;", "inputStream", "getBuffer", "()Lokio/Buffer;", "okio"}, k = 1, mv = {1, 5, 1})
public interface BufferedSource extends Source, ReadableByteChannel {
    @Deprecated(level = DeprecationLevel.WARNING, message = "moved to val: use getBuffer() instead", replaceWith = @ReplaceWith(expression = "buffer", imports = {}))
    Buffer buffer();

    boolean exhausted() throws IOException;

    Buffer getBuffer();

    long indexOf(ByteString bytes) throws IOException;

    long indexOfElement(ByteString targetBytes) throws IOException;

    InputStream inputStream();

    BufferedSource peek();

    boolean rangeEquals(long offset, ByteString bytes) throws IOException;

    long readAll(Sink sink) throws IOException;

    byte readByte() throws IOException;

    byte[] readByteArray() throws IOException;

    byte[] readByteArray(long byteCount) throws IOException;

    ByteString readByteString() throws IOException;

    ByteString readByteString(long byteCount) throws IOException;

    long readDecimalLong() throws IOException;

    void readFully(Buffer sink, long byteCount) throws IOException;

    void readFully(byte[] sink) throws IOException;

    long readHexadecimalUnsignedLong() throws IOException;

    int readInt() throws IOException;

    long readLong() throws IOException;

    short readShort() throws IOException;

    String readString(Charset charset) throws IOException;

    String readUtf8LineStrict() throws IOException;

    String readUtf8LineStrict(long limit) throws IOException;

    boolean request(long byteCount) throws IOException;

    void require(long byteCount) throws IOException;

    int select(Options options) throws IOException;

    void skip(long byteCount) throws IOException;
}
