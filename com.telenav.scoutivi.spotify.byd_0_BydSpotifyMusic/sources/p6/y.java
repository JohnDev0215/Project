package p6;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: compiled from: Sink.kt */
/* JADX INFO: loaded from: classes.dex */
public interface y extends Closeable, Flushable {
    b0 c();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    void l(e eVar, long j7);
}
