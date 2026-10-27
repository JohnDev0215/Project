package i7;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: compiled from: BufferedSink.java */
/* JADX INFO: loaded from: classes2.dex */
public interface c extends Closeable, Flushable, WritableByteChannel {
    c H(int i8) throws IOException;

    c b(byte[] bArr, int i8, int i9) throws IOException;

    c f(String str, int i8, int i9) throws IOException;

    c f0(String str) throws IOException;
}
