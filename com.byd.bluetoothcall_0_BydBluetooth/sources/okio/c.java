package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: compiled from: BufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends Closeable, Flushable, WritableByteChannel {
    c b(byte[] bArr, int i, int i2) throws IOException;

    c c(String str, int i, int i2) throws IOException;

    c f(String str) throws IOException;

    c i(int i) throws IOException;
}
