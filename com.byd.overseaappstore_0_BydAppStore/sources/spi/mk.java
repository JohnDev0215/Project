package spi;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class mk implements rp {
    public final OutputStream a;
    public final dt b;

    public mk(@NotNull OutputStream out, @NotNull dt timeout) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        this.a = out;
        this.b = timeout;
    }

    @Override // spi.rp, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.a.close();
    }

    @Override // spi.rp, java.io.Flushable
    public final void flush() throws IOException {
        this.a.flush();
    }

    @Override // spi.rp
    public final void p(@NotNull g2 source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        c.d(source.b, 0L, j);
        while (j > 0) {
            this.b.f();
            vo voVar = source.a;
            Intrinsics.checkNotNull(voVar);
            int iMin = (int) Math.min(j, voVar.c - voVar.b);
            this.a.write(voVar.a, voVar.b, iMin);
            int i = voVar.b + iMin;
            voVar.b = i;
            long j2 = iMin;
            j -= j2;
            source.b -= j2;
            if (i == voVar.c) {
                source.a = voVar.a();
                wo.b(voVar);
            }
        }
    }

    @Override // spi.rp
    @NotNull
    public final dt timeout() {
        return this.b;
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("sink(");
        sbK.append(this.a);
        sbK.append(')');
        return sbK.toString();
    }
}
