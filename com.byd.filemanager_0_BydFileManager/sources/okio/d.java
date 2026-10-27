package okio;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: compiled from: BufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends j, ReadableByteChannel {
    boolean a(long j) throws IOException;

    long c(ByteString byteString) throws IOException;

    long f(ByteString byteString) throws IOException;

    b g();

    int i(f fVar) throws IOException;
}
