package spi;

import java.io.Closeable;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface vr extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long read(@NotNull g2 g2Var, long j) throws IOException;

    @NotNull
    dt timeout();
}
