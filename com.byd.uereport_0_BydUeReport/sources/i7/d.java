package i7;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import okio.ByteString;

/* JADX INFO: compiled from: BufferedSource.java */
/* JADX INFO: loaded from: classes2.dex */
public interface d extends j, ReadableByteChannel {
    long D(ByteString byteString) throws IOException;

    b G();

    long Q(ByteString byteString) throws IOException;

    boolean p(long j8) throws IOException;

    int p0(f fVar) throws IOException;
}
