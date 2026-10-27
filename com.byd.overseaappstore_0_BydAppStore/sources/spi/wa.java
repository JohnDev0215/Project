package spi;

import java.io.EOFException;
import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class wa extends hc {
    public boolean b;

    @NotNull
    public final Function1<IOException, Unit> c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public wa(@NotNull rp delegate, @NotNull Function1<? super IOException, Unit> onException) {
        super(delegate);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(onException, "onException");
        this.c = onException;
    }

    @Override // spi.hc, spi.rp, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.b) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.b = true;
            this.c.invoke(e);
        }
    }

    @Override // spi.hc, spi.rp, java.io.Flushable
    public final void flush() {
        if (this.b) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.b = true;
            this.c.invoke(e);
        }
    }

    @Override // spi.hc, spi.rp
    public final void p(@NotNull g2 source, long j) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (this.b) {
            source.skip(j);
            return;
        }
        try {
            super.p(source, j);
        } catch (IOException e) {
            this.b = true;
            this.c.invoke(e);
        }
    }
}
