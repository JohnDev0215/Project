package h6;

import c6.p;
import c6.r;
import c6.u;
import c6.x;
import c6.z;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: RealCall.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e implements c6.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h f6690e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final r f6691g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final c f6692h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final AtomicBoolean f6693i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Object f6694j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private d f6695k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private f f6696l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f6697m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private h6.c f6698n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f6699o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f6700p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f6701q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private volatile boolean f6702r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private volatile h6.c f6703s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private volatile f f6704t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final x f6705u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final z f6706v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final boolean f6707w;

    /* JADX INFO: compiled from: RealCall.kt */
    public final class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private volatile AtomicInteger f6708e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final c6.f f6709g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ e f6710h;

        public a(e eVar, c6.f responseCallback) {
            l.e(responseCallback, "responseCallback");
            this.f6710h = eVar;
            this.f6709g = responseCallback;
            this.f6708e = new AtomicInteger(0);
        }

        public final void a(ExecutorService executorService) {
            l.e(executorService, "executorService");
            p pVarL = this.f6710h.j().l();
            if (d6.b.f5967h && Thread.holdsLock(pVarL)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Thread ");
                Thread threadCurrentThread = Thread.currentThread();
                l.d(threadCurrentThread, "Thread.currentThread()");
                sb.append(threadCurrentThread.getName());
                sb.append(" MUST NOT hold lock on ");
                sb.append(pVarL);
                throw new AssertionError(sb.toString());
            }
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e7) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e7);
                    this.f6710h.u(interruptedIOException);
                    this.f6709g.d(this.f6710h, interruptedIOException);
                    this.f6710h.j().l().e(this);
                }
            } catch (Throwable th) {
                this.f6710h.j().l().e(this);
                throw th;
            }
        }

        public final e b() {
            return this.f6710h;
        }

        public final AtomicInteger c() {
            return this.f6708e;
        }

        public final String d() {
            return this.f6710h.p().i().h();
        }

        public final void e(a other) {
            l.e(other, "other");
            this.f6708e = other.f6708e;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z6;
            Throwable th;
            IOException e7;
            p pVarL;
            String str = "OkHttp " + this.f6710h.v();
            Thread currentThread = Thread.currentThread();
            l.d(currentThread, "currentThread");
            String name = currentThread.getName();
            currentThread.setName(str);
            try {
                try {
                    this.f6710h.f6692h.r();
                    try {
                        z6 = true;
                        try {
                            this.f6709g.f(this.f6710h, this.f6710h.q());
                            pVarL = this.f6710h.j().l();
                        } catch (IOException e8) {
                            e7 = e8;
                            if (z6) {
                                l6.h.f8004c.g().j("Callback failure for " + this.f6710h.B(), 4, e7);
                            } else {
                                this.f6709g.d(this.f6710h, e7);
                            }
                            pVarL = this.f6710h.j().l();
                        } catch (Throwable th2) {
                            th = th2;
                            this.f6710h.cancel();
                            if (!z6) {
                                IOException iOException = new IOException("canceled due to " + th);
                                g5.b.a(iOException, th);
                                this.f6709g.d(this.f6710h, iOException);
                            }
                            throw th;
                        }
                    } catch (IOException e9) {
                        z6 = false;
                        e7 = e9;
                    } catch (Throwable th3) {
                        z6 = false;
                        th = th3;
                    }
                    pVarL.e(this);
                } catch (Throwable th4) {
                    this.f6710h.j().l().e(this);
                    throw th4;
                }
            } finally {
                currentThread.setName(name);
            }
        }
    }

    /* JADX INFO: compiled from: RealCall.kt */
    public static final class b extends WeakReference<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f6711a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(e referent, Object obj) {
            super(referent);
            l.e(referent, "referent");
            this.f6711a = obj;
        }

        public final Object a() {
            return this.f6711a;
        }
    }

    /* JADX INFO: compiled from: RealCall.kt */
    public static final class c extends p6.d {
        c() {
        }

        @Override // p6.d
        protected void x() {
            e.this.cancel();
        }
    }

    public e(x client, z originalRequest, boolean z6) {
        l.e(client, "client");
        l.e(originalRequest, "originalRequest");
        this.f6705u = client;
        this.f6706v = originalRequest;
        this.f6707w = z6;
        this.f6690e = client.i().a();
        this.f6691g = client.o().a(this);
        c cVar = new c();
        cVar.g(client.f(), TimeUnit.MILLISECONDS);
        g5.r rVar = g5.r.f6563a;
        this.f6692h = cVar;
        this.f6693i = new AtomicBoolean();
        this.f6701q = true;
    }

    private final <E extends IOException> E A(E e7) {
        if (this.f6697m || !this.f6692h.s()) {
            return e7;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (e7 != null) {
            interruptedIOException.initCause(e7);
        }
        return interruptedIOException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String B() {
        StringBuilder sb = new StringBuilder();
        sb.append(s() ? "canceled " : "");
        sb.append(this.f6707w ? "web socket" : "call");
        sb.append(" to ");
        sb.append(v());
        return sb.toString();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final <E extends IOException> E d(E e7) {
        Socket socketW;
        boolean z6 = d6.b.f5967h;
        if (z6 && Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        f fVar = this.f6696l;
        if (fVar != null) {
            if (z6 && Thread.holdsLock(fVar)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Thread ");
                Thread threadCurrentThread2 = Thread.currentThread();
                l.d(threadCurrentThread2, "Thread.currentThread()");
                sb2.append(threadCurrentThread2.getName());
                sb2.append(" MUST NOT hold lock on ");
                sb2.append(fVar);
                throw new AssertionError(sb2.toString());
            }
            synchronized (fVar) {
                socketW = w();
            }
            if (this.f6696l == null) {
                if (socketW != null) {
                    d6.b.j(socketW);
                }
                this.f6691g.k(this, fVar);
            } else {
                if (!(socketW == null)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }
        E e8 = (E) A(e7);
        if (e7 != null) {
            r rVar = this.f6691g;
            l.c(e8);
            rVar.d(this, e8);
        } else {
            this.f6691g.c(this);
        }
        return e8;
    }

    private final void e() {
        this.f6694j = l6.h.f8004c.g().h("response.body().close()");
        this.f6691g.e(this);
    }

    private final c6.a g(u uVar) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierS;
        c6.g gVarG;
        if (uVar.i()) {
            SSLSocketFactory sSLSocketFactoryD = this.f6705u.D();
            hostnameVerifierS = this.f6705u.s();
            sSLSocketFactory = sSLSocketFactoryD;
            gVarG = this.f6705u.g();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierS = null;
            gVarG = null;
        }
        return new c6.a(uVar.h(), uVar.l(), this.f6705u.n(), this.f6705u.C(), sSLSocketFactory, hostnameVerifierS, gVarG, this.f6705u.y(), this.f6705u.x(), this.f6705u.w(), this.f6705u.j(), this.f6705u.z());
    }

    public final void c(f connection) {
        l.e(connection, "connection");
        if (!d6.b.f5967h || Thread.holdsLock(connection)) {
            if (!(this.f6696l == null)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.f6696l = connection;
            connection.n().add(new b(this, this.f6694j));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread threadCurrentThread = Thread.currentThread();
        l.d(threadCurrentThread, "Thread.currentThread()");
        sb.append(threadCurrentThread.getName());
        sb.append(" MUST hold lock on ");
        sb.append(connection);
        throw new AssertionError(sb.toString());
    }

    @Override // c6.e
    public void cancel() {
        if (this.f6702r) {
            return;
        }
        this.f6702r = true;
        h6.c cVar = this.f6703s;
        if (cVar != null) {
            cVar.b();
        }
        f fVar = this.f6704t;
        if (fVar != null) {
            fVar.d();
        }
        this.f6691g.f(this);
    }

    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public e clone() {
        return new e(this.f6705u, this.f6706v, this.f6707w);
    }

    public final void h(z request, boolean z6) {
        l.e(request, "request");
        if (!(this.f6698n == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        synchronized (this) {
            if (!(!this.f6700p)) {
                throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
            }
            if (!(!this.f6699o)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            g5.r rVar = g5.r.f6563a;
        }
        if (z6) {
            this.f6695k = new d(this.f6690e, g(request.i()), this, this.f6691g);
        }
    }

    public final void i(boolean z6) {
        h6.c cVar;
        synchronized (this) {
            if (!this.f6701q) {
                throw new IllegalStateException("released".toString());
            }
            g5.r rVar = g5.r.f6563a;
        }
        if (z6 && (cVar = this.f6703s) != null) {
            cVar.d();
        }
        this.f6698n = null;
    }

    public final x j() {
        return this.f6705u;
    }

    public final f k() {
        return this.f6696l;
    }

    public final r l() {
        return this.f6691g;
    }

    @Override // c6.e
    public void m(c6.f responseCallback) {
        l.e(responseCallback, "responseCallback");
        if (!this.f6693i.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        e();
        this.f6705u.l().a(new a(this, responseCallback));
    }

    public final boolean n() {
        return this.f6707w;
    }

    public final h6.c o() {
        return this.f6698n;
    }

    public final z p() {
        return this.f6706v;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final c6.b0 q() throws java.lang.Throwable {
        /*
            r12 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            c6.x r0 = r12.f6705u
            java.util.List r0 = r0.t()
            h5.n.r(r2, r0)
            i6.j r0 = new i6.j
            c6.x r1 = r12.f6705u
            r0.<init>(r1)
            r2.add(r0)
            i6.a r0 = new i6.a
            c6.x r1 = r12.f6705u
            c6.n r1 = r1.k()
            r0.<init>(r1)
            r2.add(r0)
            f6.a r0 = new f6.a
            c6.x r1 = r12.f6705u
            r1.e()
            r9 = 0
            r0.<init>(r9)
            r2.add(r0)
            h6.a r0 = h6.a.f6658a
            r2.add(r0)
            boolean r0 = r12.f6707w
            if (r0 != 0) goto L46
            c6.x r0 = r12.f6705u
            java.util.List r0 = r0.u()
            h5.n.r(r2, r0)
        L46:
            i6.b r0 = new i6.b
            boolean r1 = r12.f6707w
            r0.<init>(r1)
            r2.add(r0)
            i6.g r10 = new i6.g
            r3 = 0
            r4 = 0
            c6.z r5 = r12.f6706v
            c6.x r0 = r12.f6705u
            int r6 = r0.h()
            c6.x r0 = r12.f6705u
            int r7 = r0.A()
            c6.x r0 = r12.f6705u
            int r8 = r0.F()
            r0 = r10
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            c6.z r1 = r12.f6706v     // Catch: java.lang.Throwable -> L89 java.io.IOException -> L8b
            c6.b0 r1 = r10.b(r1)     // Catch: java.lang.Throwable -> L89 java.io.IOException -> L8b
            boolean r2 = r12.s()     // Catch: java.lang.Throwable -> L89 java.io.IOException -> L8b
            if (r2 != 0) goto L7e
            r12.u(r9)
            return r1
        L7e:
            d6.b.i(r1)     // Catch: java.lang.Throwable -> L89 java.io.IOException -> L8b
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L89 java.io.IOException -> L8b
            java.lang.String r2 = "Canceled"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L89 java.io.IOException -> L8b
            throw r1     // Catch: java.lang.Throwable -> L89 java.io.IOException -> L8b
        L89:
            r1 = move-exception
            goto La0
        L8b:
            r0 = move-exception
            r1 = 1
            java.io.IOException r0 = r12.u(r0)     // Catch: java.lang.Throwable -> L9c
            if (r0 != 0) goto L9b
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L9c
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Throwable"
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L9c
            throw r0     // Catch: java.lang.Throwable -> L9c
        L9b:
            throw r0     // Catch: java.lang.Throwable -> L9c
        L9c:
            r0 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
        La0:
            if (r0 != 0) goto La5
            r12.u(r9)
        La5:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.e.q():c6.b0");
    }

    public final h6.c r(i6.g chain) throws IOException {
        l.e(chain, "chain");
        synchronized (this) {
            if (!this.f6701q) {
                throw new IllegalStateException("released".toString());
            }
            if (!(!this.f6700p)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!(!this.f6699o)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            g5.r rVar = g5.r.f6563a;
        }
        d dVar = this.f6695k;
        l.c(dVar);
        h6.c cVar = new h6.c(this, this.f6691g, dVar, dVar.a(this.f6705u, chain));
        this.f6698n = cVar;
        this.f6703s = cVar;
        synchronized (this) {
            this.f6699o = true;
            this.f6700p = true;
        }
        if (this.f6702r) {
            throw new IOException("Canceled");
        }
        return cVar;
    }

    public boolean s() {
        return this.f6702r;
    }

    public final <E extends IOException> E t(h6.c exchange, boolean z6, boolean z7, E e7) {
        boolean z8;
        l.e(exchange, "exchange");
        boolean z9 = true;
        if (!l.a(exchange, this.f6703s)) {
            return e7;
        }
        synchronized (this) {
            z8 = false;
            if (z6) {
                try {
                    if (!this.f6699o) {
                        if (z7 || !this.f6700p) {
                            z9 = false;
                        }
                    }
                    if (z6) {
                        this.f6699o = false;
                    }
                    if (z7) {
                        this.f6700p = false;
                    }
                    boolean z10 = this.f6699o;
                    boolean z11 = (z10 || this.f6700p) ? false : true;
                    if (z10 || this.f6700p || this.f6701q) {
                        z9 = false;
                    }
                    z8 = z11;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                if (z7) {
                }
                z9 = false;
            }
            g5.r rVar = g5.r.f6563a;
        }
        if (z8) {
            this.f6703s = null;
            f fVar = this.f6696l;
            if (fVar != null) {
                fVar.s();
            }
        }
        return z9 ? (E) d(e7) : e7;
    }

    public final IOException u(IOException iOException) {
        boolean z6;
        synchronized (this) {
            z6 = false;
            if (this.f6701q) {
                this.f6701q = false;
                if (!this.f6699o && !this.f6700p) {
                    z6 = true;
                }
            }
            g5.r rVar = g5.r.f6563a;
        }
        return z6 ? d(iOException) : iOException;
    }

    public final String v() {
        return this.f6706v.i().n();
    }

    public final Socket w() {
        f fVar = this.f6696l;
        l.c(fVar);
        if (d6.b.f5967h && !Thread.holdsLock(fVar)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(fVar);
            throw new AssertionError(sb.toString());
        }
        List<Reference<e>> listN = fVar.n();
        Iterator<Reference<e>> it = listN.iterator();
        int i7 = 0;
        while (true) {
            if (!it.hasNext()) {
                i7 = -1;
                break;
            }
            if (l.a(it.next().get(), this)) {
                break;
            }
            i7++;
        }
        if (!(i7 != -1)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        listN.remove(i7);
        this.f6696l = null;
        if (listN.isEmpty()) {
            fVar.B(System.nanoTime());
            if (this.f6690e.c(fVar)) {
                return fVar.D();
            }
        }
        return null;
    }

    public final boolean x() {
        d dVar = this.f6695k;
        l.c(dVar);
        return dVar.e();
    }

    public final void y(f fVar) {
        this.f6704t = fVar;
    }

    public final void z() {
        if (!(!this.f6697m)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.f6697m = true;
        this.f6692h.s();
    }
}
