package g;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import okio.ByteString;

/* JADX INFO: compiled from: BufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends o, WritableByteChannel {
    d A(String str) throws IOException;

    d C(long j) throws IOException;

    d E(int i2) throws IOException;

    c a();

    d c(byte[] bArr) throws IOException;

    d d(byte[] bArr, int i2, int i3) throws IOException;

    @Override // g.o, java.io.Flushable
    void flush() throws IOException;

    d h(ByteString byteString) throws IOException;

    long j(p pVar) throws IOException;

    d k() throws IOException;

    d l(long j) throws IOException;

    d q(int i2) throws IOException;

    d u(int i2) throws IOException;
}
