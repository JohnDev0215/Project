package okio;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: compiled from: BufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends j, ReadableByteChannel {
    boolean a(long j) throws IOException;

    long d(ByteString byteString) throws IOException;

    long g(ByteString byteString) throws IOException;

    b h();

    int j(f fVar) throws IOException;
}
