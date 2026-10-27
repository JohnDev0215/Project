package g;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import okio.ByteString;

/* JADX INFO: compiled from: BufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends p, ReadableByteChannel {
    int B() throws IOException;

    boolean D() throws IOException;

    long F(byte b2) throws IOException;

    byte[] G(long j) throws IOException;

    boolean H(long j, ByteString byteString) throws IOException;

    long J() throws IOException;

    String L(Charset charset) throws IOException;

    InputStream M();

    byte N() throws IOException;

    @Deprecated
    c a();

    short i() throws IOException;

    ByteString n(long j) throws IOException;

    void o(long j) throws IOException;

    short p() throws IOException;

    int t() throws IOException;

    String x() throws IOException;

    void y(long j) throws IOException;
}
