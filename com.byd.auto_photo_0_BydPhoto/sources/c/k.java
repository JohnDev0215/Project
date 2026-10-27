package c;

import java.io.Closeable;

/* JADX INFO: loaded from: classes2.dex */
public interface k extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    void close();

    long g(a aVar, long j);
}
