package e.d0.f;

import e.c0;
import e.j;
import e.w;
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
    public final e.a f3384a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f3385b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j f3386c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f3387d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f3388e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3389f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f3390g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3391h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3392i;
    public e.d0.g.c j;

    /* JADX INFO: compiled from: StreamAllocation.java */
    public static final class a extends WeakReference<f> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f3393a;

        public a(f fVar, Object obj) {
            super(fVar);
            this.f3393a = obj;
        }
    }

    public f(j jVar, e.a aVar, Object obj) {
        this.f3386c = jVar;
        this.f3384a = aVar;
        this.f3388e = new e(aVar, n());
        this.f3387d = obj;
    }

    public void a(c cVar) {
        if (this.f3390g != null) {
            throw new IllegalStateException();
        }
        this.f3390g = cVar;
        cVar.n.add(new a(this, this.f3387d));
    }

    public void b() {
        e.d0.g.c cVar;
        c cVar2;
        synchronized (this.f3386c) {
            this.f3392i = true;
            cVar = this.j;
            cVar2 = this.f3390g;
        }
        if (cVar != null) {
            cVar.cancel();
        } else if (cVar2 != null) {
            cVar2.e();
        }
    }

    public e.d0.g.c c() {
        e.d0.g.c cVar;
        synchronized (this.f3386c) {
            cVar = this.j;
        }
        return cVar;
    }

    public synchronized c d() {
        return this.f3390g;
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
            r1.f3391h = r4
        La:
            e.d0.f.c r3 = r1.f3390g
            if (r3 == 0) goto L4a
            if (r2 == 0) goto L12
            r3.k = r4
        L12:
            e.d0.g.c r2 = r1.j
            if (r2 != 0) goto L4a
            boolean r2 = r1.f3391h
            if (r2 != 0) goto L1e
            boolean r2 = r3.k
            if (r2 == 0) goto L4a
        L1e:
            r1.l(r3)
            e.d0.f.c r2 = r1.f3390g
            java.util.List<java.lang.ref.Reference<e.d0.f.f>> r2 = r2.n
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L46
            e.d0.f.c r2 = r1.f3390g
            long r3 = java.lang.System.nanoTime()
            r2.o = r3
            e.d0.a r2 = e.d0.a.f3334a
            e.j r3 = r1.f3386c
            e.d0.f.c r4 = r1.f3390g
            boolean r2 = r2.e(r3, r4)
            if (r2 == 0) goto L46
            e.d0.f.c r2 = r1.f3390g
            java.net.Socket r2 = r2.r()
            goto L47
        L46:
            r2 = r0
        L47:
            r1.f3390g = r0
            r0 = r2
        L4a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: e.d0.f.f.e(boolean, boolean, boolean):java.net.Socket");
    }

    public final c f(int i2, int i3, int i4, boolean z) throws Throwable {
        synchronized (this.f3386c) {
            if (this.f3391h) {
                throw new IllegalStateException("released");
            }
            if (this.j != null) {
                throw new IllegalStateException("codec != null");
            }
            if (this.f3392i) {
                throw new IOException("Canceled");
            }
            c cVar = this.f3390g;
            if (cVar != null && !cVar.k) {
                return cVar;
            }
            Socket socketF = null;
            e.d0.a.f3334a.h(this.f3386c, this.f3384a, this, null);
            c cVar2 = this.f3390g;
            if (cVar2 != null) {
                return cVar2;
            }
            c0 c0VarG = this.f3385b;
            if (c0VarG == null) {
                c0VarG = this.f3388e.g();
            }
            synchronized (this.f3386c) {
                if (this.f3392i) {
                    throw new IOException("Canceled");
                }
                e.d0.a.f3334a.h(this.f3386c, this.f3384a, this, c0VarG);
                c cVar3 = this.f3390g;
                if (cVar3 != null) {
                    return cVar3;
                }
                this.f3385b = c0VarG;
                this.f3389f = 0;
                c cVar4 = new c(this.f3386c, c0VarG);
                a(cVar4);
                cVar4.f(i2, i3, i4, z);
                n().a(cVar4.b());
                synchronized (this.f3386c) {
                    e.d0.a.f3334a.i(this.f3386c, cVar4);
                    if (cVar4.p()) {
                        socketF = e.d0.a.f3334a.f(this.f3386c, this.f3384a, this);
                        cVar4 = this.f3390g;
                    }
                }
                e.d0.c.d(socketF);
                return cVar4;
            }
        }
    }

    public final c g(int i2, int i3, int i4, boolean z, boolean z2) throws Throwable {
        while (true) {
            c cVarF = f(i2, i3, i4, z);
            synchronized (this.f3386c) {
                if (cVarF.l == 0) {
                    return cVarF;
                }
                if (cVarF.o(z2)) {
                    return cVarF;
                }
                j();
            }
        }
    }

    public boolean h() {
        return this.f3385b != null || this.f3388e.c();
    }

    public e.d0.g.c i(w wVar, boolean z) {
        try {
            e.d0.g.c cVarQ = g(wVar.f(), wVar.z(), wVar.F(), wVar.A(), z).q(wVar, this);
            synchronized (this.f3386c) {
                this.j = cVarQ;
            }
            return cVarQ;
        } catch (IOException e2) {
            throw new RouteException(e2);
        }
    }

    public void j() {
        Socket socketE;
        synchronized (this.f3386c) {
            socketE = e(true, false, false);
        }
        e.d0.c.d(socketE);
    }

    public void k() {
        Socket socketE;
        synchronized (this.f3386c) {
            socketE = e(false, true, false);
        }
        e.d0.c.d(socketE);
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
        if (this.j != null || this.f3390g.n.size() != 1) {
            throw new IllegalStateException();
        }
        Reference<f> reference = this.f3390g.n.get(0);
        Socket socketE = e(true, false, false);
        this.f3390g = cVar;
        cVar.n.add(reference);
        return socketE;
    }

    public final d n() {
        return e.d0.a.f3334a.j(this.f3386c);
    }

    public void o(IOException iOException) {
        boolean z;
        Socket socketE;
        synchronized (this.f3386c) {
            if (iOException instanceof StreamResetException) {
                ErrorCode errorCode = ((StreamResetException) iOException).errorCode;
                ErrorCode errorCode2 = ErrorCode.REFUSED_STREAM;
                if (errorCode == errorCode2) {
                    this.f3389f++;
                }
                if (errorCode != errorCode2 || this.f3389f > 1) {
                    this.f3385b = null;
                    z = true;
                }
                z = false;
            } else {
                c cVar = this.f3390g;
                if (cVar != null && (!cVar.p() || (iOException instanceof ConnectionShutdownException))) {
                    if (this.f3390g.l == 0) {
                        c0 c0Var = this.f3385b;
                        if (c0Var != null && iOException != null) {
                            this.f3388e.a(c0Var, iOException);
                        }
                        this.f3385b = null;
                    }
                    z = true;
                }
                z = false;
            }
            socketE = e(z, false, true);
        }
        e.d0.c.d(socketE);
    }

    public void p(boolean z, e.d0.g.c cVar) {
        Socket socketE;
        synchronized (this.f3386c) {
            if (cVar != null) {
                if (cVar == this.j) {
                    if (!z) {
                        this.f3390g.l++;
                    }
                    socketE = e(z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.j + " but was " + cVar);
        }
        e.d0.c.d(socketE);
    }

    public String toString() {
        c cVarD = d();
        return cVarD != null ? cVarD.toString() : this.f3384a.toString();
    }
}
