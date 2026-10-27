package spi;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ln;

/* JADX INFO: loaded from: classes2.dex */
public final class y8 {
    public boolean a;

    @NotNull
    public final wl b;

    @NotNull
    public final vl c;

    @NotNull
    public final u8 d;

    @NotNull
    public final a9 e;
    public final z8 f;

    public final class a extends hc {
        public boolean b;
        public long c;
        public boolean d;
        public final long e;
        public final /* synthetic */ y8 f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(@NotNull y8 y8Var, rp delegate, long j) {
            super(delegate);
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.f = y8Var;
            this.e = j;
        }

        @Override // spi.hc, spi.rp, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            if (this.d) {
                return;
            }
            this.d = true;
            long j = this.e;
            if (j != -1 && this.c != j) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                e(null);
            } catch (IOException e) {
                throw e(e);
            }
        }

        public final <E extends IOException> E e(E e) {
            if (this.b) {
                return e;
            }
            this.b = true;
            return (E) this.f.a(false, true, e);
        }

        @Override // spi.hc, spi.rp, java.io.Flushable
        public final void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e) {
                throw e(e);
            }
        }

        @Override // spi.hc, spi.rp
        public final void p(@NotNull g2 source, long j) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (!(!this.d)) {
                throw new IllegalStateException("closed".toString());
            }
            long j2 = this.e;
            if (j2 == -1 || this.c + j <= j2) {
                try {
                    super.p(source, j);
                    this.c += j;
                    return;
                } catch (IOException e) {
                    throw e(e);
                }
            }
            StringBuilder sbK = e.k("expected ");
            sbK.append(this.e);
            sbK.append(" bytes but received ");
            sbK.append(this.c + j);
            throw new ProtocolException(sbK.toString());
        }
    }

    public final class b extends ic {
        public long a;
        public boolean b;
        public boolean c;
        public boolean d;
        public final long e;
        public final /* synthetic */ y8 f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(@NotNull y8 y8Var, vr delegate, long j) {
            super(delegate);
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.f = y8Var;
            this.e = j;
            this.b = true;
            if (j == 0) {
                e(null);
            }
        }

        @Override // spi.ic, spi.vr, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            if (this.d) {
                return;
            }
            this.d = true;
            try {
                super.close();
                e(null);
            } catch (IOException e) {
                throw e(e);
            }
        }

        public final <E extends IOException> E e(E e) {
            if (this.c) {
                return e;
            }
            this.c = true;
            if (e == null && this.b) {
                this.b = false;
                y8 y8Var = this.f;
                u8 u8Var = y8Var.d;
                vl call = y8Var.c;
                Objects.requireNonNull(u8Var);
                Intrinsics.checkNotNullParameter(call, "call");
            }
            return (E) this.f.a(true, false, e);
        }

        @Override // spi.ic, spi.vr
        public final long read(@NotNull g2 sink, long j) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (!(!this.d)) {
                throw new IllegalStateException("closed".toString());
            }
            try {
                long j2 = delegate().read(sink, j);
                if (this.b) {
                    this.b = false;
                    y8 y8Var = this.f;
                    u8 u8Var = y8Var.d;
                    vl call = y8Var.c;
                    Objects.requireNonNull(u8Var);
                    Intrinsics.checkNotNullParameter(call, "call");
                }
                if (j2 == -1) {
                    e(null);
                    return -1L;
                }
                long j3 = this.a + j2;
                long j4 = this.e;
                if (j4 != -1 && j3 > j4) {
                    throw new ProtocolException("expected " + this.e + " bytes but received " + j3);
                }
                this.a = j3;
                if (j3 == j4) {
                    e(null);
                }
                return j2;
            } catch (IOException e) {
                throw e(e);
            }
        }
    }

    public y8(@NotNull vl call, @NotNull u8 eventListener, @NotNull a9 finder, @NotNull z8 codec) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(finder, "finder");
        Intrinsics.checkNotNullParameter(codec, "codec");
        this.c = call;
        this.d = eventListener;
        this.e = finder;
        this.f = codec;
        this.b = codec.d();
    }

    public final IOException a(boolean z, boolean z2, IOException iOException) {
        if (iOException != null) {
            e(iOException);
        }
        if (z2) {
            u8 u8Var = this.d;
            vl call = this.c;
            if (iOException != null) {
                u8Var.b(call, iOException);
            } else {
                Objects.requireNonNull(u8Var);
                Intrinsics.checkNotNullParameter(call, "call");
            }
        }
        if (z) {
            if (iOException != null) {
                this.d.c(this.c, iOException);
            } else {
                u8 u8Var2 = this.d;
                vl call2 = this.c;
                Objects.requireNonNull(u8Var2);
                Intrinsics.checkNotNullParameter(call2, "call");
            }
        }
        return this.c.h(this, z2, z, iOException);
    }

    @NotNull
    public final rp b(@NotNull im request, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        this.a = z;
        mm mmVar = request.e;
        Intrinsics.checkNotNull(mmVar);
        long jContentLength = mmVar.contentLength();
        u8 u8Var = this.d;
        vl call = this.c;
        Objects.requireNonNull(u8Var);
        Intrinsics.checkNotNullParameter(call, "call");
        return new a(this, this.f.f(request, jContentLength), jContentLength);
    }

    @Nullable
    public final ln.a c(boolean z) throws IOException {
        try {
            ln.a aVarC = this.f.c(z);
            if (aVarC != null) {
                Intrinsics.checkNotNullParameter(this, "deferredTrailers");
                aVarC.m = this;
            }
            return aVarC;
        } catch (IOException e) {
            this.d.c(this.c, e);
            e(e);
            throw e;
        }
    }

    public final void d() {
        u8 u8Var = this.d;
        vl call = this.c;
        Objects.requireNonNull(u8Var);
        Intrinsics.checkNotNullParameter(call, "call");
    }

    public final void e(IOException iOException) {
        this.e.c(iOException);
        wl wlVarD = this.f.d();
        vl call = this.c;
        synchronized (wlVarD) {
            Intrinsics.checkNotNullParameter(call, "call");
            if (iOException instanceof StreamResetException) {
                if (((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
                    int i = wlVarD.m + 1;
                    wlVarD.m = i;
                    if (i > 1) {
                        wlVarD.i = true;
                        wlVarD.k++;
                    }
                } else if (((StreamResetException) iOException).errorCode != ErrorCode.CANCEL || !call.m) {
                    wlVarD.i = true;
                    wlVarD.k++;
                }
            } else if (!wlVarD.j() || (iOException instanceof ConnectionShutdownException)) {
                wlVarD.i = true;
                if (wlVarD.l == 0) {
                    wlVarD.d(call.p, wlVarD.q, iOException);
                    wlVarD.k++;
                }
            }
        }
    }
}
