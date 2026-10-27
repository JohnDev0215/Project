package spi;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.ExceptionsKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class vl implements c3 {
    public final am a;

    @NotNull
    public final u8 b;
    public final c c;
    public final AtomicBoolean d;
    public Object e;
    public a9 f;

    @Nullable
    public wl g;
    public boolean h;

    @Nullable
    public y8 i;
    public boolean j;
    public boolean k;
    public boolean l;
    public volatile boolean m;
    public volatile y8 n;

    @Nullable
    public volatile wl o;

    @NotNull
    public final dk p;

    @NotNull
    public final im q;
    public final boolean r;

    public final class a implements Runnable {

        @NotNull
        public volatile AtomicInteger a;
        public final e3 b;
        public final /* synthetic */ vl c;

        public a(@NotNull vl vlVar, e3 responseCallback) {
            Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
            this.c = vlVar;
            this.b = responseCallback;
            this.a = new AtomicInteger(0);
        }

        @NotNull
        public final String a() {
            return this.c.q.b.e;
        }

        @Override // java.lang.Runnable
        public final void run() {
            dk dkVar;
            StringBuilder sbK = e.k("OkHttp ");
            sbK.append(this.c.q.b.h());
            String string = sbK.toString();
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(currentThread, "currentThread");
            String name = currentThread.getName();
            currentThread.setName(string);
            boolean z = false;
            try {
                try {
                    this.c.c.h();
                    try {
                    } catch (IOException e) {
                        e = e;
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        this.b.onResponse(this.c, this.c.g());
                        dkVar = this.c.p;
                    } catch (IOException e2) {
                        e = e2;
                        z = true;
                        if (z) {
                            Objects.requireNonNull(zk.c);
                            zk.a.i("Callback failure for " + vl.a(this.c), 4, e);
                        } else {
                            this.b.onFailure(this.c, e);
                        }
                        dkVar = this.c.p;
                    } catch (Throwable th2) {
                        th = th2;
                        z = true;
                        this.c.cancel();
                        if (!z) {
                            IOException iOException = new IOException("canceled due to " + th);
                            ExceptionsKt.addSuppressed(iOException, th);
                            this.b.onFailure(this.c, iOException);
                        }
                        throw th;
                    }
                    dkVar.a.b(this);
                } catch (Throwable th3) {
                    this.c.p.a.b(this);
                    throw th3;
                }
            } finally {
                currentThread.setName(name);
            }
        }
    }

    public static final class b extends WeakReference<vl> {

        @Nullable
        public final Object a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(@NotNull vl referent, @Nullable Object obj) {
            super(referent);
            Intrinsics.checkNotNullParameter(referent, "referent");
            this.a = obj;
        }
    }

    public static final class c extends q0 {
        public c() {
        }

        @Override // spi.q0
        public final void k() {
            vl.this.cancel();
        }
    }

    public vl(@NotNull dk client, @NotNull im originalRequest, boolean z) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        this.p = client;
        this.q = originalRequest;
        this.r = z;
        this.a = client.b.a;
        this.b = client.e.a(this);
        c cVar = new c();
        long j = client.x;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        cVar.g(j);
        Unit unit = Unit.INSTANCE;
        this.c = cVar;
        this.d = new AtomicBoolean();
        this.l = true;
    }

    public static final String a(vl vlVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(vlVar.m ? "canceled " : "");
        sb.append(vlVar.r ? "web socket" : NotificationCompat.CATEGORY_CALL);
        sb.append(" to ");
        sb.append(vlVar.q.b.h());
        return sb.toString();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void b(@NotNull wl connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        byte[] bArr = uu.a;
        if (!(this.g == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.g = connection;
        connection.o.add(new b(this, this.e));
    }

    public final <E extends IOException> E c(E e) {
        E ioe;
        Socket socketJ;
        byte[] bArr = uu.a;
        wl connection = this.g;
        if (connection != null) {
            synchronized (connection) {
                socketJ = j();
            }
            if (this.g == null) {
                if (socketJ != null) {
                    uu.d(socketJ);
                }
                Objects.requireNonNull(this.b);
                Intrinsics.checkNotNullParameter(this, "call");
                Intrinsics.checkNotNullParameter(connection, "connection");
            } else {
                if (!(socketJ == null)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }
        if (!this.h && this.c.i()) {
            ioe = new InterruptedIOException("timeout");
            if (e != null) {
                ioe.initCause(e);
            }
        } else {
            ioe = e;
        }
        if (e != null) {
            u8 u8Var = this.b;
            Intrinsics.checkNotNull(ioe);
            Objects.requireNonNull(u8Var);
            Intrinsics.checkNotNullParameter(this, "call");
            Intrinsics.checkNotNullParameter(ioe, "ioe");
        } else {
            Objects.requireNonNull(this.b);
            Intrinsics.checkNotNullParameter(this, "call");
        }
        return ioe;
    }

    @Override // spi.c3
    public final void cancel() {
        Socket socket;
        if (this.m) {
            return;
        }
        this.m = true;
        y8 y8Var = this.n;
        if (y8Var != null) {
            y8Var.f.cancel();
        }
        wl wlVar = this.o;
        if (wlVar != null && (socket = wlVar.b) != null) {
            uu.d(socket);
        }
        Objects.requireNonNull(this.b);
        Intrinsics.checkNotNullParameter(this, "call");
    }

    public final Object clone() {
        return new vl(this.p, this.q, this.r);
    }

    public final void d() {
        Objects.requireNonNull(zk.c);
        this.e = zk.a.g();
        Objects.requireNonNull(this.b);
        Intrinsics.checkNotNullParameter(this, "call");
    }

    @Override // spi.c3
    public final void e(@NotNull e3 responseCallback) {
        a other;
        Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
        if (!this.d.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        d();
        p6 p6Var = this.p.a;
        a call = new a(this, responseCallback);
        Objects.requireNonNull(p6Var);
        Intrinsics.checkNotNullParameter(call, "call");
        synchronized (p6Var) {
            p6Var.d.add(call);
            if (!call.c.r) {
                String strA = call.a();
                Iterator<a> it = p6Var.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        Iterator<a> it2 = p6Var.d.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                other = null;
                                break;
                            } else {
                                other = it2.next();
                                if (Intrinsics.areEqual(other.a(), strA)) {
                                    break;
                                }
                            }
                        }
                    } else {
                        other = it.next();
                        if (Intrinsics.areEqual(other.a(), strA)) {
                            break;
                        }
                    }
                }
                if (other != null) {
                    Intrinsics.checkNotNullParameter(other, "other");
                    call.a = other.a;
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        p6Var.c();
    }

    @Override // spi.c3
    @NotNull
    public final ln execute() {
        if (!this.d.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        this.c.h();
        d();
        try {
            p6 p6Var = this.p.a;
            synchronized (p6Var) {
                Intrinsics.checkNotNullParameter(this, "call");
                p6Var.f.add(this);
            }
            return g();
        } finally {
            p6 p6Var2 = this.p.a;
            Objects.requireNonNull(p6Var2);
            Intrinsics.checkNotNullParameter(this, NotificationCompat.CATEGORY_CALL);
            p6Var2.a(p6Var2.f, this);
        }
    }

    public final void f(boolean z) {
        y8 y8Var;
        synchronized (this) {
            if (!this.l) {
                throw new IllegalStateException("released".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        if (z && (y8Var = this.n) != null) {
            y8Var.f.cancel();
            y8Var.c.h(y8Var, true, true, null);
        }
        this.i = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008f  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final spi.ln g() throws java.lang.Throwable {
        /*
            r11 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            spi.dk r0 = r11.p
            java.util.List<spi.ag> r0 = r0.c
            kotlin.collections.CollectionsKt.addAll(r2, r0)
            spi.pn r0 = new spi.pn
            spi.dk r1 = r11.p
            r0.<init>(r1)
            r2.add(r0)
            spi.e2 r0 = new spi.e2
            spi.dk r1 = r11.p
            spi.q4 r1 = r1.j
            r0.<init>(r1)
            r2.add(r0)
            spi.y2 r0 = new spi.y2
            spi.dk r1 = r11.p
            spi.w2 r1 = r1.k
            r0.<init>(r1)
            r2.add(r0)
            spi.a4 r0 = spi.a4.a
            r2.add(r0)
            boolean r0 = r11.r
            if (r0 != 0) goto L3e
            spi.dk r0 = r11.p
            java.util.List<spi.ag> r0 = r0.d
            kotlin.collections.CollectionsKt.addAll(r2, r0)
        L3e:
            spi.d3 r0 = new spi.d3
            boolean r1 = r11.r
            r0.<init>(r1)
            r2.add(r0)
            spi.bm r9 = new spi.bm
            r3 = 0
            r4 = 0
            spi.im r5 = r11.q
            spi.dk r0 = r11.p
            int r6 = r0.y
            int r7 = r0.z
            int r8 = r0.A
            r0 = r9
            r1 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            r1 = 0
            spi.im r2 = r11.q     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            spi.ln r2 = r9.b(r2)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            boolean r3 = r11.m     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            if (r3 != 0) goto L6b
            r11.i(r1)
            return r2
        L6b:
            spi.uu.c(r2)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
            throw r2     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L78
        L76:
            r2 = move-exception
            goto L8d
        L78:
            r0 = move-exception
            r2 = 1
            java.io.IOException r0 = r11.i(r0)     // Catch: java.lang.Throwable -> L89
            if (r0 != 0) goto L88
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L89
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Throwable"
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L89
            throw r0     // Catch: java.lang.Throwable -> L89
        L88:
            throw r0     // Catch: java.lang.Throwable -> L89
        L89:
            r0 = move-exception
            r10 = r2
            r2 = r0
            r0 = r10
        L8d:
            if (r0 != 0) goto L92
            r11.i(r1)
        L92:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.vl.g():spi.ln");
    }

    public final <E extends IOException> E h(@NotNull y8 exchange, boolean z, boolean z2, E e) {
        boolean z3;
        boolean z4;
        Intrinsics.checkNotNullParameter(exchange, "exchange");
        if (!Intrinsics.areEqual(exchange, this.n)) {
            return e;
        }
        synchronized (this) {
            z3 = false;
            if (z) {
                try {
                    if (!this.j) {
                        if (z2 || !this.k) {
                            z4 = false;
                        }
                    }
                    if (z) {
                        this.j = false;
                    }
                    if (z2) {
                        this.k = false;
                    }
                    boolean z5 = this.j;
                    boolean z6 = (z5 || this.k) ? false : true;
                    if (!z5 && !this.k && !this.l) {
                        z3 = true;
                    }
                    z4 = z3;
                    z3 = z6;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                if (z2) {
                }
                z4 = false;
            }
            Unit unit = Unit.INSTANCE;
        }
        if (z3) {
            this.n = null;
            wl wlVar = this.g;
            if (wlVar != null) {
                synchronized (wlVar) {
                    wlVar.l++;
                }
            }
        }
        return z4 ? (E) c(e) : e;
    }

    @Nullable
    public final IOException i(@Nullable IOException iOException) {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.l) {
                this.l = false;
                if (!this.j && !this.k) {
                    z = true;
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return z ? c(iOException) : iOException;
    }

    @Override // spi.c3
    public final boolean isCanceled() {
        return this.m;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List, java.util.List<java.lang.ref.Reference<spi.vl>>] */
    @Nullable
    public final Socket j() {
        wl connection = this.g;
        Intrinsics.checkNotNull(connection);
        byte[] bArr = uu.a;
        ?? r1 = connection.o;
        Iterator it = r1.iterator();
        boolean z = false;
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (Intrinsics.areEqual((vl) ((Reference) it.next()).get(), this)) {
                break;
            }
            i++;
        }
        if (!(i != -1)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        r1.remove(i);
        this.g = null;
        if (r1.isEmpty()) {
            connection.p = System.nanoTime();
            am amVar = this.a;
            Objects.requireNonNull(amVar);
            Intrinsics.checkNotNullParameter(connection, "connection");
            byte[] bArr2 = uu.a;
            if (connection.i || amVar.e == 0) {
                connection.i = true;
                amVar.d.remove(connection);
                if (amVar.d.isEmpty()) {
                    amVar.b.a();
                }
                z = true;
            } else {
                amVar.b.c(amVar.c, 0L);
            }
            if (z) {
                Socket socket = connection.c;
                Intrinsics.checkNotNull(socket);
                return socket;
            }
        }
        return null;
    }

    @Override // spi.c3
    @NotNull
    public final im request() {
        return this.q;
    }

    @Override // spi.c3
    public final dt timeout() {
        return this.c;
    }
}
