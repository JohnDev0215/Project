package spi;

import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class uf implements vr {
    public final InputStream a;
    public final dt b;

    public uf(@NotNull InputStream input, @NotNull dt timeout) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        this.a = input;
        this.b = timeout;
    }

    @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.a.close();
    }

    @Override // spi.vr
    public final long read(@NotNull g2 sink, long j) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (j == 0) {
            return 0L;
        }
        if (!(j >= 0)) {
            throw new IllegalArgumentException(d.d("byteCount < 0: ", j).toString());
        }
        try {
            this.b.f();
            vo voVarO = sink.O(1);
            int i = this.a.read(voVarO.a, voVarO.c, (int) Math.min(j, 8192 - voVarO.c));
            if (i != -1) {
                voVarO.c += i;
                long j2 = i;
                sink.b += j2;
                return j2;
            }
            if (voVarO.b != voVarO.c) {
                return -1L;
            }
            sink.a = voVarO.a();
            wo.b(voVarO);
            return -1L;
        } catch (AssertionError e) {
            if (b.f(e)) {
                throw new IOException(e);
            }
            throw e;
        }
    }

    @Override // spi.vr
    @NotNull
    public final dt timeout() {
        return this.b;
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("source(");
        sbK.append(this.a);
        sbK.append(')');
        return sbK.toString();
    }
}
