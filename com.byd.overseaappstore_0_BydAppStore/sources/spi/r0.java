package spi;

import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class r0 implements rp {
    public final /* synthetic */ q0 a;
    public final /* synthetic */ rp b;

    public r0(q0 q0Var, rp rpVar) {
        this.a = q0Var;
        this.b = rpVar;
    }

    @Override // spi.rp, java.io.Closeable, java.lang.AutoCloseable
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

    @Override // spi.rp, java.io.Flushable
    public final void flush() throws IOException {
        q0 q0Var = this.a;
        q0Var.h();
        try {
            this.b.flush();
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

    @Override // spi.rp
    public final void p(@NotNull g2 source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        c.d(source.b, 0L, j);
        while (true) {
            long j2 = 0;
            if (j <= 0) {
                return;
            }
            vo voVar = source.a;
            while (true) {
                Intrinsics.checkNotNull(voVar);
                if (j2 >= 65536) {
                    break;
                }
                j2 += (long) (voVar.c - voVar.b);
                if (j2 >= j) {
                    j2 = j;
                    break;
                }
                voVar = voVar.f;
            }
            q0 q0Var = this.a;
            q0Var.h();
            try {
                this.b.p(source, j2);
                Unit unit = Unit.INSTANCE;
                if (q0Var.i()) {
                    throw q0Var.j(null);
                }
                j -= j2;
            } catch (IOException e) {
                if (!q0Var.i()) {
                    throw e;
                }
                throw q0Var.j(e);
            } finally {
                q0Var.i();
            }
        }
    }

    @Override // spi.rp
    public final dt timeout() {
        return this.a;
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("AsyncTimeout.sink(");
        sbK.append(this.b);
        sbK.append(')');
        return sbK.toString();
    }
}
