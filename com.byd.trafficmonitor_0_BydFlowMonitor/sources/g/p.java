package g;

import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: compiled from: Source.java */
/* JADX INFO: loaded from: classes.dex */
public interface p extends Closeable {
    q b();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long m(c cVar, long j) throws IOException;
}
