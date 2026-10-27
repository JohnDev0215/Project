package spi;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class hc implements rp {

    @NotNull
    public final rp a;

    public hc(@NotNull rp delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.a = delegate;
    }

    @Override // spi.rp, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.close();
    }

    @Override // spi.rp, java.io.Flushable
    public void flush() throws IOException {
        this.a.flush();
    }

    @Override // spi.rp
    public void p(@NotNull g2 source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        this.a.p(source, j);
    }

    @Override // spi.rp
    @NotNull
    public final dt timeout() {
        return this.a.timeout();
    }

    @NotNull
    public final String toString() {
        return getClass().getSimpleName() + '(' + this.a + ')';
    }
}
