package p;

import java.nio.channels.WritableByteChannel;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public interface io extends pd2, WritableByteChannel {
    io B();

    io C(int i);

    io I(int i);

    long L(de2 de2Var);

    io R(int i);

    io V(byte[] bArr);

    io a0(ByteString byteString);

    io c0();

    fo d();

    @Override // p.pd2, java.io.Flushable
    void flush();

    io l(byte[] bArr, int i, int i2);

    io r(String str, int i, int i2);

    io s(long j);

    io s0(String str);

    io t0(long j);
}
