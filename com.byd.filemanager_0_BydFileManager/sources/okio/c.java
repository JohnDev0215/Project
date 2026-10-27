package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: compiled from: BufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends Closeable, Flushable, WritableByteChannel {
    c b(String str, int i, int i2) throws IOException;

    c e(String str) throws IOException;

    c h(int i) throws IOException;
}
