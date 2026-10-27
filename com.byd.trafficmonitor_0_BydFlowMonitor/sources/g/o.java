package g;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* JADX INFO: compiled from: Sink.java */
/* JADX INFO: loaded from: classes.dex */
public interface o extends Closeable, Flushable {
    q b();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    void flush() throws IOException;

    void g(c cVar, long j) throws IOException;
}
