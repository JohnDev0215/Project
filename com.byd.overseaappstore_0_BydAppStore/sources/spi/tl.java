package spi;

import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class tl implements k2 {

    @JvmField
    @NotNull
    public final g2 a;

    @JvmField
    public boolean b;

    @JvmField
    @NotNull
    public final rp c;

    public tl(@NotNull rp sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.c = sink;
        this.a = new g2();
    }

    @Override // spi.k2
    @NotNull
    public final g2 a() {
        return this.a;
    }

    @Override // spi.k2
    @NotNull
    public final k2 c(int i) throws IOException {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.W(i);
        j();
        return this;
    }

    @Override // spi.rp, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        if (this.b) {
            return;
        }
        Throwable th = null;
        try {
            g2 g2Var = this.a;
            long j = g2Var.b;
            if (j > 0) {
                this.c.p(g2Var, j);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.c.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.b = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // spi.k2
    @NotNull
    public final k2 d(int i) throws IOException {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.V(i);
        j();
        return this;
    }

    @Override // spi.k2, spi.rp, java.io.Flushable
    public final void flush() throws IOException {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        g2 g2Var = this.a;
        long j = g2Var.b;
        if (j > 0) {
            this.c.p(g2Var, j);
        }
        this.c.flush();
    }

    @Override // spi.k2
    @NotNull
    public final k2 i(int i) throws IOException {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.S(i);
        j();
        return this;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.b;
    }

    @Override // spi.k2
    @NotNull
    public final k2 j() throws IOException {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        long jD = this.a.D();
        if (jD > 0) {
            this.c.p(this.a, jD);
        }
        return this;
    }

    @Override // spi.k2
    @NotNull
    public final k2 l(@NotNull String string) throws IOException {
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.Y(string);
        j();
        return this;
    }

    @Override // spi.k2
    @NotNull
    public final k2 n(@NotNull byte[] source, int i, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.R(source, i, i2);
        j();
        return this;
    }

    @Override // spi.k2
    @NotNull
    public final k2 o(long j) throws IOException {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.o(j);
        j();
        return this;
    }

    @Override // spi.rp
    public final void p(@NotNull g2 source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.p(source, j);
        j();
    }

    @Override // spi.k2
    @NotNull
    public final k2 t(@NotNull byte[] source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.Q(source);
        j();
        return this;
    }

    @Override // spi.rp
    @NotNull
    public final dt timeout() {
        return this.c.timeout();
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("buffer(");
        sbK.append(this.c);
        sbK.append(')');
        return sbK.toString();
    }

    @Override // spi.k2
    @NotNull
    public final k2 u(@NotNull ByteString byteString) throws IOException {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.P(byteString);
        j();
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(@NotNull ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        int iWrite = this.a.write(source);
        j();
        return iWrite;
    }

    @Override // spi.k2
    public final long x(@NotNull vr source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long j2 = ((uf) source).read(this.a, 8192);
            if (j2 == -1) {
                return j;
            }
            j += j2;
            j();
        }
    }

    @Override // spi.k2
    @NotNull
    public final k2 y(long j) throws IOException {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        this.a.y(j);
        j();
        return this;
    }
}
