package spi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class z2 implements vr {
    public boolean a;
    public final /* synthetic */ l2 b;
    public final /* synthetic */ a3 c;
    public final /* synthetic */ k2 d;

    public z2(l2 l2Var, a3 a3Var, k2 k2Var) {
        this.b = l2Var;
        this.c = a3Var;
        this.d = k2Var;
    }

    @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!this.a) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (!uu.g(this)) {
                this.a = true;
                this.c.a();
            }
        }
        this.b.close();
    }

    @Override // spi.vr
    public final long read(@NotNull g2 sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            long j2 = this.b.read(sink, j);
            if (j2 != -1) {
                sink.E(this.d.a(), sink.b - j2, j2);
                this.d.j();
                return j2;
            }
            if (!this.a) {
                this.a = true;
                this.d.close();
            }
            return -1L;
        } catch (IOException e) {
            if (!this.a) {
                this.a = true;
                this.c.a();
            }
            throw e;
        }
    }

    @Override // spi.vr
    @NotNull
    public final dt timeout() {
        return this.b.timeout();
    }
}
