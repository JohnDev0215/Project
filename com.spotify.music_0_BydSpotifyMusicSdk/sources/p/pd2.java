package p;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: loaded from: classes.dex */
public interface pd2 extends Closeable, Flushable {
    void Y(fo foVar, long j);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    tl2 e();

    void flush();
}
