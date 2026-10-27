package spi;

import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class h2 extends OutputStream {
    public final /* synthetic */ g2 a;

    public h2(g2 g2Var) {
        this.a = g2Var;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() {
    }

    @NotNull
    public final String toString() {
        return this.a + ".outputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        this.a.S(i);
    }

    @Override // java.io.OutputStream
    public final void write(@NotNull byte[] data, int i, int i2) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.a.R(data, i, i2);
    }
}
