package f;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: RealCall.java */
/* JADX INFO: loaded from: classes.dex */
final class d0 implements i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b0 f5037b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private f.k0.h.k f5038c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final e0 f5039d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f5040e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f5041f;

    /* JADX INFO: compiled from: RealCall.java */
    final class a extends f.k0.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final j f5042c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private volatile AtomicInteger f5043d;

        a(j jVar) {
            super("OkHttp %s", d0.this.g());
            this.f5043d = new AtomicInteger(0);
            this.f5042c = jVar;
        }

        @Override // f.k0.d
        protected void k() {
            boolean z;
            Throwable th;
            IOException e2;
            d0.this.f5038c.p();
            try {
                try {
                    z = true;
                } catch (IOException e3) {
                    z = false;
                    e2 = e3;
                } catch (Throwable th2) {
                    z = false;
                    th = th2;
                }
                try {
                    this.f5042c.a(d0.this, d0.this.e());
                } catch (IOException e4) {
                    e2 = e4;
                    if (z) {
                        f.k0.l.f.j().p(4, "Callback failure for " + d0.this.h(), e2);
                    } else {
                        this.f5042c.b(d0.this, e2);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    d0.this.cancel();
                    if (!z) {
                        IOException iOException = new IOException("canceled due to " + th);
                        iOException.addSuppressed(th);
                        this.f5042c.b(d0.this, iOException);
                    }
                    throw th;
                }
                d0.this.f5037b.l().f(this);
            } catch (Throwable th4) {
                d0.this.f5037b.l().f(this);
                throw th4;
            }
        }

        AtomicInteger l() {
            return this.f5043d;
        }

        void m(ExecutorService executorService) {
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e2) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e2);
                    d0.this.f5038c.l(interruptedIOException);
                    this.f5042c.b(d0.this, interruptedIOException);
                    d0.this.f5037b.l().f(this);
                }
            } catch (Throwable th) {
                d0.this.f5037b.l().f(this);
                throw th;
            }
        }

        d0 n() {
            return d0.this;
        }

        String o() {
            return d0.this.f5039d.i().m();
        }

        void p(a aVar) {
            this.f5043d = aVar.f5043d;
        }
    }

    private d0(b0 b0Var, e0 e0Var, boolean z) {
        this.f5037b = b0Var;
        this.f5039d = e0Var;
        this.f5040e = z;
    }

    static d0 f(b0 b0Var, e0 e0Var, boolean z) {
        d0 d0Var = new d0(b0Var, e0Var, z);
        d0Var.f5038c = new f.k0.h.k(b0Var, d0Var);
        return d0Var;
    }

    @Override // f.i
    public e0 a() {
        return this.f5039d;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d0 clone() {
        return f(this.f5037b, this.f5039d, this.f5040e);
    }

    @Override // f.i
    public void cancel() {
        this.f5038c.d();
    }

    @Override // f.i
    public g0 d() {
        synchronized (this) {
            if (this.f5041f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f5041f = true;
        }
        this.f5038c.p();
        this.f5038c.b();
        try {
            this.f5037b.l().b(this);
            return e();
        } finally {
            this.f5037b.l().g(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    f.g0 e() throws java.lang.Throwable {
        /*
            r12 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            f.b0 r0 = r12.f5037b
            java.util.List r0 = r0.r()
            r1.addAll(r0)
            f.k0.i.j r0 = new f.k0.i.j
            f.b0 r2 = r12.f5037b
            r0.<init>(r2)
            r1.add(r0)
            f.k0.i.a r0 = new f.k0.i.a
            f.b0 r2 = r12.f5037b
            f.q r2 = r2.k()
            r0.<init>(r2)
            r1.add(r0)
            f.k0.g.a r0 = new f.k0.g.a
            f.b0 r2 = r12.f5037b
            f.k0.g.d r2 = r2.s()
            r0.<init>(r2)
            r1.add(r0)
            f.k0.h.b r0 = new f.k0.h.b
            f.b0 r2 = r12.f5037b
            r0.<init>(r2)
            r1.add(r0)
            boolean r0 = r12.f5040e
            if (r0 != 0) goto L4b
            f.b0 r0 = r12.f5037b
            java.util.List r0 = r0.t()
            r1.addAll(r0)
        L4b:
            f.k0.i.b r0 = new f.k0.i.b
            boolean r2 = r12.f5040e
            r0.<init>(r2)
            r1.add(r0)
            f.k0.i.g r10 = new f.k0.i.g
            f.k0.h.k r2 = r12.f5038c
            r3 = 0
            r4 = 0
            f.e0 r5 = r12.f5039d
            f.b0 r0 = r12.f5037b
            int r7 = r0.g()
            f.b0 r0 = r12.f5037b
            int r8 = r0.B()
            f.b0 r0 = r12.f5037b
            int r9 = r0.G()
            r0 = r10
            r6 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r0 = 0
            r1 = 0
            f.e0 r2 = r12.f5039d     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L97
            f.g0 r2 = r10.d(r2)     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L97
            f.k0.h.k r3 = r12.f5038c     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L97
            boolean r3 = r3.i()     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L97
            if (r3 != 0) goto L8a
            f.k0.h.k r0 = r12.f5038c
            r0.l(r1)
            return r2
        L8a:
            f.k0.e.f(r2)     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L97
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L97
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L97
            throw r2     // Catch: java.lang.Throwable -> L95 java.io.IOException -> L97
        L95:
            r2 = move-exception
            goto La4
        L97:
            r0 = move-exception
            r2 = 1
            f.k0.h.k r3 = r12.f5038c     // Catch: java.lang.Throwable -> La0
            java.io.IOException r0 = r3.l(r0)     // Catch: java.lang.Throwable -> La0
            throw r0     // Catch: java.lang.Throwable -> La0
        La0:
            r0 = move-exception
            r11 = r2
            r2 = r0
            r0 = r11
        La4:
            if (r0 != 0) goto Lab
            f.k0.h.k r0 = r12.f5038c
            r0.l(r1)
        Lab:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: f.d0.e():f.g0");
    }

    String g() {
        return this.f5039d.i().B();
    }

    String h() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.f5040e ? "web socket" : "call");
        sb.append(" to ");
        sb.append(g());
        return sb.toString();
    }

    @Override // f.i
    public boolean isCanceled() {
        return this.f5038c.i();
    }

    @Override // f.i
    public void y(j jVar) {
        synchronized (this) {
            if (this.f5041f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f5041f = true;
        }
        this.f5038c.b();
        this.f5037b.l().a(new a(jVar));
    }
}
