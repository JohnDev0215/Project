package spi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface l2 extends vr, ReadableByteChannel {
    @NotNull
    InputStream A();

    @NotNull
    g2 a();

    @NotNull
    ByteString b(long j) throws IOException;

    @NotNull
    byte[] f() throws IOException;

    long g(@NotNull rp rpVar) throws IOException;

    boolean h() throws IOException;

    @NotNull
    String k(long j) throws IOException;

    @NotNull
    String m(@NotNull Charset charset) throws IOException;

    @NotNull
    ByteString q() throws IOException;

    @NotNull
    String r() throws IOException;

    byte readByte() throws IOException;

    int readInt() throws IOException;

    short readShort() throws IOException;

    boolean request(long j) throws IOException;

    @NotNull
    byte[] s(long j) throws IOException;

    void skip(long j) throws IOException;

    int v(@NotNull kk kkVar) throws IOException;

    void w(long j) throws IOException;

    long z() throws IOException;
}
