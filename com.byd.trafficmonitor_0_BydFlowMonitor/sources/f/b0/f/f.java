package f.b0.f;

import f.a0;
import f.j;
import f.u;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: compiled from: StreamAllocation.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f.a f5063a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a0 f5064b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j f5065c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f5066d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f5067e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5068f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f5069g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f5070h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5071i;
    public f.b0.g.c j;

    /* JADX INFO: compiled from: StreamAllocation.java */
    public static final class a extends WeakReference<f> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f5072a;

        public a(f fVar, Object obj) {
            super(fVar);
            this.f5072a = obj;
        }
    }

    public f(j jVar, f.a aVar, Object obj) {
        this.f5065c = jVar;
        this.f5063a = aVar;
        this.f5067e = new e(aVar, n());
        this.f5066d = obj;
    }

    public void a(c cVar) {
        if (this.f5069g != null) {
            throw new IllegalStateException();
        }
        this.f5069g = cVar;
        cVar.n.add(new a(this, this.f5066d));
    }

    public void b() {
        f.b0.g.c cVar;
        c cVar2;
        synchronized (this.f5065c) {
            this.f5071i = true;
            cVar = this.j;
            cVar2 = this.f5069g;
        }
        if (cVar != null) {
            cVar.cancel();
        } else if (cVar2 != null) {
            cVar2.d();
        }
    }

    public f.b0.g.c c() {
        f.b0.g.c cVar;
        synchronized (this.f5065c) {
            cVar = this.j;
        }
        return cVar;
    }

    public synchronized c d() {
        return this.f5069g;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.net.Socket e(boolean r2, boolean r3, boolean r4) {
        /*
            r1 = this;
            r0 = 0
            if (r4 == 0) goto L5
            r1.j = r0
        L5:
            r4 = 1
            if (r3 == 0) goto La
            r1.f5070h = r4
        La:
            f.b0.f.c r3 = r1.f5069g
            if (r3 == 0) goto L4a
            if (r2 == 0) goto L12
            r3.k = r4
        L12:
            f.b0.g.c r2 = r1.j
            if (r2 != 0) goto L4a
            boolean r2 = r1.f5070h
            if (r2 != 0) goto L1e
            boolean r2 = r3.k
            if (r2 == 0) goto L4a
        L1e:
            r1.l(r3)
            f.b0.f.c r2 = r1.f5069g
            java.util.List<java.lang.ref.Reference<f.b0.f.f>> r2 = r2.n
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L46
            f.b0.f.c r2 = r1.f5069g
            long r3 = java.lang.System.nanoTime()
            r2.o = r3
            f.b0.a r2 = f.b0.a.f5010a
            f.j r3 = r1.f5065c
            f.b0.f.c r4 = r1.f5069g
            boolean r2 = r2.e(r3, r4)
            if (r2 == 0) goto L46
            f.b0.f.c r2 = r1.f5069g
            java.net.Socket r2 = r2.q()
            goto L47
        L46:
            r2 = r0
        L47:
            r1.f5069g = r0
            r0 = r2
        L4a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: f.b0.f.f.e(boolean, boolean, boolean):java.net.Socket");
    }

    public final c f(int i2, int i3, int i4, boolean z) throws Throwable {
        c cVar;
        synchronized (this.f5065c) {
            if (this.f5070h) {
                throw new IllegalStateException("released");
            }
            if (this.j != null) {
                throw new IllegalStateException("codec != null");
            }
            if (this.f5071i) {
                throw new IOException("Canceled");
            }
            c cVar2 = this.f5069g;
            if (cVar2 != null && !cVar2.k) {
                return cVar2;
            }
            f.b0.a.f5010a.g(this.f5065c, this.f5063a, this);
            c cVar3 = this.f5069g;
            if (cVar3 != null) {
                return cVar3;
            }
            a0 a0VarG = this.f5064b;
            if (a0VarG == null) {
                a0VarG = this.f5067e.g();
            }
            synchronized (this.f5065c) {
                this.f5064b = a0VarG;
                this.f5068f = 0;
                cVar = new c(this.f5065c, a0VarG);
                a(cVar);
                if (this.f5071i) {
                    throw new IOException("Canceled");
                }
            }
            cVar.e(i2, i3, i4, z);
            n().a(cVar.a());
            Socket socketF = null;
            synchronized (this.f5065c) {
                f.b0.a.f5010a.h(this.f5065c, cVar);
                if (cVar.o()) {
                    socketF = f.b0.a.f5010a.f(this.f5065c, this.f5063a, this);
                    cVar = this.f5069g;
                }
            }
            f.b0.c.d(socketF);
            return cVar;
        }
    }

    public final c g(int i2, int i3, int i4, boolean z, boolean z2) throws Throwable {
        while (true) {
            c cVarF = f(i2, i3, i4, z);
            synchronized (this.f5065c) {
                if (cVarF.l == 0) {
                    return cVarF;
                }
                if (cVarF.n(z2)) {
                    return cVarF;
                }
                j();
            }
        }
    }

    public boolean h() {
        return this.f5064b != null || this.f5067e.c();
    }

    public f.b0.g.c i(u uVar, boolean z) {
        try {
            f.b0.g.c cVarP = g(uVar.c(), uVar.t(), uVar.B(), uVar.u(), z).p(uVar, this);
            synchronized (this.f5065c) {
                this.j = cVarP;
            }
            return cVarP;
        } catch (IOException e2) {
            throw new RouteException(e2);
        }
    }

    public void j() {
        Socket socketE;
        synchronized (this.f5065c) {
            socketE = e(true, false, false);
        }
        f.b0.c.d(socketE);
    }

    public void k() {
        Socket socketE;
        synchronized (this.f5065c) {
            socketE = e(false, true, false);
        }
        f.b0.c.d(socketE);
    }

    public final void l(c cVar) {
        int size = cVar.n.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (cVar.n.get(i2).get() == this) {
                cVar.n.remove(i2);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public Socket m(c cVar) {
        if (this.j != null || this.f5069g.n.size() != 1) {
            throw new IllegalStateException();
        }
        Reference<f> reference = this.f5069g.n.get(0);
        Socket socketE = e(true, false, false);
        this.f5069g = cVar;
        cVar.n.add(reference);
        return socketE;
    }

    public final d n() {
        return f.b0.a.f5010a.i(this.f5065c);
    }

    public void o(IOException iOException) {
        boolean z;
        Socket socketE;
        synchronized (this.f5065c) {
            if (iOException instanceof StreamResetException) {
                ErrorCode errorCode = ((StreamResetException) iOException).errorCode;
                ErrorCode errorCode2 = ErrorCode.REFUSED_STREAM;
                if (errorCode == errorCode2) {
                    this.f5068f++;
                }
                if (errorCode != errorCode2 || this.f5068f > 1) {
                    this.f5064b = null;
                    z = true;
                }
                z = false;
            } else {
                c cVar = this.f5069g;
                if (cVar != null && (!cVar.o() || (iOException instanceof ConnectionShutdownException))) {
                    if (this.f5069g.l == 0) {
                        a0 a0Var = this.f5064b;
                        if (a0Var != null && iOException != null) {
                            this.f5067e.a(a0Var, iOException);
                        }
                        this.f5064b = null;
                    }
                    z = true;
                }
                z = false;
            }
            socketE = e(z, false, true);
        }
        f.b0.c.d(socketE);
    }

    public void p(boolean z, f.b0.g.c cVar) {
        Socket socketE;
        synchronized (this.f5065c) {
            if (cVar != null) {
                if (cVar == this.j) {
                    if (!z) {
                        this.f5069g.l++;
                    }
                    socketE = e(z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.j + " but was " + cVar);
        }
        f.b0.c.d(socketE);
    }

    public String toString() {
        c cVarD = d();
        return cVarD != null ? cVarD.toString() : this.f5063a.toString();
    }
}
