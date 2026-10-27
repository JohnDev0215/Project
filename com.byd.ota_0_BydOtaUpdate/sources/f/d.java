package f;

import java.io.IOException;
import okio.ByteString;

/* JADX INFO: compiled from: BufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends p {
    d A(int i2) throws IOException;

    c a();

    d f(byte[] bArr) throws IOException;

    @Override // f.p, java.io.Flushable
    void flush() throws IOException;

    d g(byte[] bArr, int i2, int i3) throws IOException;

    d i(ByteString byteString) throws IOException;

    long k(q qVar) throws IOException;

    d l() throws IOException;

    d m(long j) throws IOException;

    d q(int i2) throws IOException;

    d r(int i2) throws IOException;

    d w(String str) throws IOException;

    d y(long j) throws IOException;
}
