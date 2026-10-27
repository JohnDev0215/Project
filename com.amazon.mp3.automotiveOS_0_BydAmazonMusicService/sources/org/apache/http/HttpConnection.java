package org.apache.http;

import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpConnection extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    int getSocketTimeout();

    boolean isOpen();

    boolean isStale();

    void setSocketTimeout(int i);

    void shutdown() throws IOException;
}
