package p;

import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public interface ko extends de2, ReadableByteChannel {
    String A0(Charset charset);

    wo C0();

    String M();

    int O();

    long P(ByteString byteString);

    boolean Q();

    boolean a(long j);

    long b0(ByteString byteString);

    fo d();

    long d0(io ioVar);

    long f0();

    String g0(long j);

    void p(fo foVar, long j);

    int p0(ar1 ar1Var);

    w02 peek();

    void q0(long j);

    int read(byte[] bArr);

    byte readByte();

    void readFully(byte[] bArr);

    int readInt();

    long readLong();

    short readShort();

    ByteString v(long j);

    boolean x0(long j, ByteString byteString);

    void y(long j);

    long y0();
}
