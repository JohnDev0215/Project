package okio;

import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    byte[] A();

    void B(long j);

    int E();

    Buffer G();

    boolean I();

    long K(byte b2);

    byte[] L(long j);

    long M();

    String N(Charset charset);

    String O();

    byte P();

    int Q(Options options);

    @Deprecated
    Buffer b();

    boolean c(long j);

    void k(byte[] bArr);

    short l();

    ByteString q(long j);

    String r(long j);

    void s(long j);

    long t(Sink sink);

    short u();

    int w();

    String z();
}
