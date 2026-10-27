package spi;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class cf {
    public long a;
    public long b;
    public long c;
    public long d;
    public final ArrayDeque<ke> e;
    public boolean f;

    @NotNull
    public final c g;

    @NotNull
    public final b h;

    @NotNull
    public final d i;

    @NotNull
    public final d j;

    @Nullable
    public ErrorCode k;

    @Nullable
    public IOException l;
    public final int m;

    @NotNull
    public final re n;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final class b implements rp {
        public final g2 a;
        public boolean b;
        public boolean c;

        public b(boolean z) {
            this.c = z;
            this.a = new g2();
        }

        public /* synthetic */ b(cf cfVar, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z);
        }

        @Override // spi.rp, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            cf cfVar = cf.this;
            byte[] bArr = uu.a;
            synchronized (cfVar) {
                if (this.b) {
                    return;
                }
                boolean z = cf.this.f() == null;
                Unit unit = Unit.INSTANCE;
                cf cfVar2 = cf.this;
                if (!cfVar2.h.c) {
                    if (this.a.b > 0) {
                        while (this.a.b > 0) {
                            e(true);
                        }
                    } else if (z) {
                        cfVar2.n.H(cfVar2.m, true, null, 0L);
                    }
                }
                synchronized (cf.this) {
                    this.b = true;
                    Unit unit2 = Unit.INSTANCE;
                }
                cf.this.n.flush();
                cf.this.a();
            }
        }

        public final void e(boolean z) throws IOException {
            long jMin;
            cf cfVar;
            boolean z2;
            synchronized (cf.this) {
                cf.this.j.h();
                while (true) {
                    try {
                        cf cfVar2 = cf.this;
                        if (cfVar2.c < cfVar2.d || this.c || this.b || cfVar2.f() != null) {
                            break;
                        } else {
                            cf.this.k();
                        }
                    } finally {
                    }
                }
                cf.this.j.l();
                cf.this.b();
                cf cfVar3 = cf.this;
                jMin = Math.min(cfVar3.d - cfVar3.c, this.a.b);
                cfVar = cf.this;
                cfVar.c += jMin;
                z2 = z && jMin == this.a.b;
                Unit unit = Unit.INSTANCE;
            }
            cfVar.j.h();
            try {
                cf cfVar4 = cf.this;
                cfVar4.n.H(cfVar4.m, z2, this.a, jMin);
            } finally {
            }
        }

        @Override // spi.rp, java.io.Flushable
        public final void flush() throws IOException {
            cf cfVar = cf.this;
            byte[] bArr = uu.a;
            synchronized (cfVar) {
                cf.this.b();
                Unit unit = Unit.INSTANCE;
            }
            while (this.a.b > 0) {
                e(false);
                cf.this.n.flush();
            }
        }

        @Override // spi.rp
        public final void p(@NotNull g2 source, long j) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            byte[] bArr = uu.a;
            this.a.p(source, j);
            while (this.a.b >= PlaybackStateCompat.ACTION_PREPARE) {
                e(false);
            }
        }

        @Override // spi.rp
        @NotNull
        public final dt timeout() {
            return cf.this.j;
        }
    }

    public final class c implements vr {

        @NotNull
        public final g2 a = new g2();

        @NotNull
        public final g2 b = new g2();
        public boolean c;
        public final long d;
        public boolean e;

        public c(long j, boolean z) {
            this.d = j;
            this.e = z;
        }

        @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            long j;
            synchronized (cf.this) {
                this.c = true;
                g2 g2Var = this.b;
                j = g2Var.b;
                g2Var.B();
                cf cfVar = cf.this;
                if (cfVar == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                }
                cfVar.notifyAll();
                Unit unit = Unit.INSTANCE;
            }
            if (j > 0) {
                e(j);
            }
            cf.this.a();
        }

        public final void e(long j) {
            cf cfVar = cf.this;
            byte[] bArr = uu.a;
            cfVar.n.G(j);
        }

        @Override // spi.vr
        public final long read(@NotNull g2 sink, long j) throws Throwable {
            long j2;
            boolean z;
            long j3;
            Intrinsics.checkNotNullParameter(sink, "sink");
            long j4 = 0;
            if (!(j >= 0)) {
                throw new IllegalArgumentException(spi.d.d("byteCount < 0: ", j).toString());
            }
            while (true) {
                Throwable streamResetException = null;
                synchronized (cf.this) {
                    cf.this.i.h();
                    try {
                        if (cf.this.f() != null && (streamResetException = cf.this.l) == null) {
                            ErrorCode errorCodeF = cf.this.f();
                            Intrinsics.checkNotNull(errorCodeF);
                            streamResetException = new StreamResetException(errorCodeF);
                        }
                        if (this.c) {
                            throw new IOException("stream closed");
                        }
                        g2 g2Var = this.b;
                        long j5 = g2Var.b;
                        if (j5 > j4) {
                            j2 = g2Var.read(sink, Math.min(j, j5));
                            cf cfVar = cf.this;
                            long j6 = cfVar.a + j2;
                            cfVar.a = j6;
                            long j7 = j6 - cfVar.b;
                            if (streamResetException == null && j7 >= cfVar.n.r.a() / 2) {
                                cf cfVar2 = cf.this;
                                cfVar2.n.K(cfVar2.m, j7);
                                cf cfVar3 = cf.this;
                                cfVar3.b = cfVar3.a;
                            }
                        } else if (this.e || streamResetException != null) {
                            j2 = -1;
                        } else {
                            cf.this.k();
                            z = true;
                            j3 = -1;
                            cf.this.i.l();
                            Unit unit = Unit.INSTANCE;
                        }
                        long j8 = j2;
                        z = false;
                        j3 = j8;
                        cf.this.i.l();
                        Unit unit2 = Unit.INSTANCE;
                    } catch (Throwable th) {
                        cf.this.i.l();
                        throw th;
                    }
                }
                if (!z) {
                    if (j3 != -1) {
                        e(j3);
                        return j3;
                    }
                    if (streamResetException == null) {
                        return -1L;
                    }
                    Intrinsics.checkNotNull(streamResetException);
                    throw streamResetException;
                }
                j4 = 0;
            }
        }

        @Override // spi.vr
        @NotNull
        public final dt timeout() {
            return cf.this.i;
        }
    }

    public final class d extends q0 {
        public d() {
        }

        @Override // spi.q0
        @NotNull
        public final IOException j(@Nullable IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // spi.q0
        public final void k() {
            cf.this.e(ErrorCode.CANCEL);
            re reVar = cf.this.n;
            synchronized (reVar) {
                long j = reVar.p;
                long j2 = reVar.o;
                if (j < j2) {
                    return;
                }
                reVar.o = j2 + 1;
                reVar.q = System.nanoTime() + ((long) 1000000000);
                Unit unit = Unit.INSTANCE;
                vs vsVar = reVar.i;
                String strF = hi.f(new StringBuilder(), reVar.d, " ping");
                vsVar.c(new ze(strF, true, strF, true, reVar), 0L);
            }
        }

        public final void l() throws IOException {
            if (i()) {
                throw new SocketTimeoutException("timeout");
            }
        }
    }

    static {
        new a(null);
    }

    public cf(int i, @NotNull re connection, boolean z, boolean z2, @Nullable ke keVar) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        this.m = i;
        this.n = connection;
        this.d = connection.s.a();
        ArrayDeque<ke> arrayDeque = new ArrayDeque<>();
        this.e = arrayDeque;
        this.g = new c(connection.r.a(), z2);
        this.h = new b(z);
        this.i = new d();
        this.j = new d();
        if (keVar == null) {
            if (!h()) {
                throw new IllegalStateException("remotely-initiated streams should have headers".toString());
            }
        } else {
            if (!(!h())) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet".toString());
            }
            arrayDeque.add(keVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() throws java.io.IOException {
        /*
            r3 = this;
            byte[] r0 = spi.uu.a
            monitor-enter(r3)
            spi.cf$c r0 = r3.g     // Catch: java.lang.Throwable -> L34
            boolean r1 = r0.e     // Catch: java.lang.Throwable -> L34
            if (r1 != 0) goto L19
            boolean r0 = r0.c     // Catch: java.lang.Throwable -> L34
            if (r0 == 0) goto L19
            spi.cf$b r0 = r3.h     // Catch: java.lang.Throwable -> L34
            boolean r1 = r0.c     // Catch: java.lang.Throwable -> L34
            if (r1 != 0) goto L17
            boolean r0 = r0.b     // Catch: java.lang.Throwable -> L34
            if (r0 == 0) goto L19
        L17:
            r0 = 1
            goto L1a
        L19:
            r0 = 0
        L1a:
            boolean r1 = r3.i()     // Catch: java.lang.Throwable -> L34
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L34
            monitor-exit(r3)
            if (r0 == 0) goto L2a
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.CANCEL
            r1 = 0
            r3.c(r0, r1)
            goto L33
        L2a:
            if (r1 != 0) goto L33
            spi.re r0 = r3.n
            int r1 = r3.m
            r0.E(r1)
        L33:
            return
        L34:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.cf.a():void");
    }

    public final void b() throws IOException {
        b bVar = this.h;
        if (bVar.b) {
            throw new IOException("stream closed");
        }
        if (bVar.c) {
            throw new IOException("stream finished");
        }
        if (this.k != null) {
            IOException iOException = this.l;
            if (iOException != null) {
                throw iOException;
            }
            ErrorCode errorCode = this.k;
            Intrinsics.checkNotNull(errorCode);
            throw new StreamResetException(errorCode);
        }
    }

    public final void c(@NotNull ErrorCode statusCode, @Nullable IOException iOException) throws IOException {
        Intrinsics.checkNotNullParameter(statusCode, "rstStatusCode");
        if (d(statusCode, iOException)) {
            re reVar = this.n;
            int i = this.m;
            Objects.requireNonNull(reVar);
            Intrinsics.checkNotNullParameter(statusCode, "statusCode");
            reVar.y.G(i, statusCode);
        }
    }

    public final boolean d(ErrorCode errorCode, IOException iOException) {
        byte[] bArr = uu.a;
        synchronized (this) {
            if (this.k != null) {
                return false;
            }
            if (this.g.e && this.h.c) {
                return false;
            }
            this.k = errorCode;
            this.l = iOException;
            notifyAll();
            Unit unit = Unit.INSTANCE;
            this.n.E(this.m);
            return true;
        }
    }

    public final void e(@NotNull ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (d(errorCode, null)) {
            this.n.J(this.m, errorCode);
        }
    }

    @Nullable
    public final synchronized ErrorCode f() {
        return this.k;
    }

    @NotNull
    public final rp g() {
        synchronized (this) {
            if (!(this.f || h())) {
                throw new IllegalStateException("reply before requesting the sink".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        return this.h;
    }

    public final boolean h() {
        return this.n.a == ((this.m & 1) == 1);
    }

    public final synchronized boolean i() {
        if (this.k != null) {
            return false;
        }
        c cVar = this.g;
        if (cVar.e || cVar.c) {
            b bVar = this.h;
            if (bVar.c || bVar.b) {
                if (this.f) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void j(@NotNull ke headers, boolean z) {
        boolean zI;
        Intrinsics.checkNotNullParameter(headers, "headers");
        byte[] bArr = uu.a;
        synchronized (this) {
            if (this.f && z) {
                Objects.requireNonNull(this.g);
            } else {
                this.f = true;
                this.e.add(headers);
            }
            if (z) {
                this.g.e = true;
            }
            zI = i();
            notifyAll();
            Unit unit = Unit.INSTANCE;
        }
        if (zI) {
            return;
        }
        this.n.E(this.m);
    }

    public final void k() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }
}
