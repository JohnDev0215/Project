package okio;

import java.nio.channels.WritableByteChannel;

/* JADX INFO: loaded from: classes.dex */
public interface BufferedSink extends Sink, WritableByteChannel {
    BufferedSink D(String str);

    BufferedSink F(long j);

    BufferedSink H(int i2);

    BufferedSink J(int i2);

    Buffer b();

    BufferedSink f(byte[] bArr);

    @Override // okio.Sink, java.io.Flushable
    void flush();

    BufferedSink g(byte[] bArr, int i2, int i3);

    BufferedSink i(ByteString byteString);

    long m(Source source);

    BufferedSink n();

    BufferedSink o(long j);

    BufferedSink v(int i2);

    BufferedSink x(int i2);
}
