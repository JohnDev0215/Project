package spi;

import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class s0 implements vr {
    public final /* synthetic */ q0 a;
    public final /* synthetic */ vr b;

    public s0(q0 q0Var, vr vrVar) {
        this.a = q0Var;
        this.b = vrVar;
    }

    @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        q0 q0Var = this.a;
        q0Var.h();
        try {
            this.b.close();
            Unit unit = Unit.INSTANCE;
            if (q0Var.i()) {
                throw q0Var.j(null);
            }
        } catch (IOException e) {
            if (!q0Var.i()) {
                throw e;
            }
            throw q0Var.j(e);
        } finally {
            q0Var.i();
        }
    }

    @Override // spi.vr
    public final long read(@NotNull g2 sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        q0 q0Var = this.a;
        q0Var.h();
        try {
            long j2 = this.b.read(sink, j);
            if (q0Var.i()) {
                throw q0Var.j(null);
            }
            return j2;
        } catch (IOException e) {
            if (q0Var.i()) {
                throw q0Var.j(e);
            }
            throw e;
        } finally {
            q0Var.i();
        }
    }

    @Override // spi.vr
    public final dt timeout() {
        return this.a;
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("AsyncTimeout.source(");
        sbK.append(this.b);
        sbK.append(')');
        return sbK.toString();
    }
}
