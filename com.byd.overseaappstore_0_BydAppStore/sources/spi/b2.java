package spi;

import java.io.EOFException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class b2 implements rp {
    @Override // spi.rp, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // spi.rp, java.io.Flushable
    public final void flush() {
    }

    @Override // spi.rp
    public final void p(@NotNull g2 source, long j) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "source");
        source.skip(j);
    }

    @Override // spi.rp
    @NotNull
    public final dt timeout() {
        return dt.d;
    }
}
