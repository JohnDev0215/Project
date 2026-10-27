package f.k0.h;

import f.b0;
import f.e0;
import f.t;
import f.x;
import f.y;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okio.AsyncTimeout;

/* JADX INFO: compiled from: Transmitter.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a */
    private final b0 f5216a;

    /* JADX INFO: renamed from: b */
    private final g f5217b;

    /* JADX INFO: renamed from: c */
    private final f.i f5218c;

    /* JADX INFO: renamed from: d */
    private final t f5219d;

    /* JADX INFO: renamed from: e */
    private final AsyncTimeout f5220e;

    /* JADX INFO: renamed from: f */
    @Nullable
    private Object f5221f;

    /* JADX INFO: renamed from: g */
    private e0 f5222g;

    /* JADX INFO: renamed from: h */
    private e f5223h;

    /* JADX INFO: renamed from: i */
    public f f5224i;

    @Nullable
    private d j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;

    /* JADX INFO: compiled from: Transmitter.java */
    class a extends AsyncTimeout {
        a() {
        }

        @Override // okio.AsyncTimeout
        protected void v() {
            k.this.d();
        }
    }

    /* JADX INFO: compiled from: Transmitter.java */
    static final class b extends WeakReference<k> {

        /* JADX INFO: renamed from: a */
        final Object f5225a;

        b(k kVar, Object obj) {
            super(kVar);
            this.f5225a = obj;
        }
    }

    public k(b0 b0Var, f.i iVar) {
        a aVar = new a();
        this.f5220e = aVar;
        this.f5216a = b0Var;
        this.f5217b = f.k0.c.f5131a.h(b0Var.h());
        this.f5218c = iVar;
        this.f5219d = b0Var.n().a(iVar);
        aVar.h(b0Var.e(), TimeUnit.MILLISECONDS);
    }

    private f.e e(x xVar) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierQ;
        f.k kVarF;
        if (xVar.n()) {
            SSLSocketFactory sSLSocketFactoryF = this.f5216a.F();
            hostnameVerifierQ = this.f5216a.q();
            sSLSocketFactory = sSLSocketFactoryF;
            kVarF = this.f5216a.f();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierQ = null;
            kVarF = null;
        }
        return new f.e(xVar.m(), xVar.y(), this.f5216a.m(), this.f5216a.E(), sSLSocketFactory, hostnameVerifierQ, kVarF, this.f5216a.z(), this.f5216a.x(), this.f5216a.w(), this.f5216a.i(), this.f5216a.A());
    }

    @Nullable
    private IOException j(@Nullable IOException iOException, boolean z) {
        f fVar;
        Socket socketN;
        boolean z2;
        synchronized (this.f5217b) {
            if (z) {
                if (this.j != null) {
                    throw new IllegalStateException("cannot release connection while it is in use");
                }
            }
            fVar = this.f5224i;
            socketN = (fVar != null && this.j == null && (z || this.o)) ? n() : null;
            if (this.f5224i != null) {
                fVar = null;
            }
            z2 = this.o && this.j == null;
        }
        f.k0.e.g(socketN);
        if (fVar != null) {
            this.f5219d.h(this.f5218c, fVar);
        }
        if (z2) {
            boolean z3 = iOException != null;
            iOException = q(iOException);
            if (z3) {
                this.f5219d.b(this.f5218c, iOException);
            } else {
                this.f5219d.a(this.f5218c);
            }
        }
        return iOException;
    }

    @Nullable
    private IOException q(@Nullable IOException iOException) {
        if (this.n || !this.f5220e.p()) {
            return iOException;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    void a(f fVar) {
        if (this.f5224i != null) {
            throw new IllegalStateException();
        }
        this.f5224i = fVar;
        fVar.p.add(new b(this, this.f5221f));
    }

    public void b() {
        this.f5221f = f.k0.l.f.j().m("response.body().close()");
        this.f5219d.c(this.f5218c);
    }

    public boolean c() {
        return this.f5223h.f() && this.f5223h.e();
    }

    public void d() {
        d dVar;
        f fVarA;
        synchronized (this.f5217b) {
            this.m = true;
            dVar = this.j;
            e eVar = this.f5223h;
            fVarA = (eVar == null || eVar.a() == null) ? this.f5224i : this.f5223h.a();
        }
        if (dVar != null) {
            dVar.b();
        } else if (fVarA != null) {
            fVarA.d();
        }
    }

    public void f() {
        synchronized (this.f5217b) {
            if (this.o) {
                throw new IllegalStateException();
            }
            this.j = null;
        }
    }

    @Nullable
    IOException g(d dVar, boolean z, boolean z2, @Nullable IOException iOException) {
        boolean z3;
        synchronized (this.f5217b) {
            d dVar2 = this.j;
            if (dVar != dVar2) {
                return iOException;
            }
            boolean z4 = true;
            if (z) {
                z3 = !this.k;
                this.k = true;
            } else {
                z3 = false;
            }
            if (z2) {
                if (!this.l) {
                    z3 = true;
                }
                this.l = true;
            }
            if (this.k && this.l && z3) {
                dVar2.c().m++;
                this.j = null;
            } else {
                z4 = false;
            }
            return z4 ? j(iOException, false) : iOException;
        }
    }

    public boolean h() {
        boolean z;
        synchronized (this.f5217b) {
            z = this.j != null;
        }
        return z;
    }

    public boolean i() {
        boolean z;
        synchronized (this.f5217b) {
            z = this.m;
        }
        return z;
    }

    d k(y.a aVar, boolean z) {
        synchronized (this.f5217b) {
            if (this.o) {
                throw new IllegalStateException("released");
            }
            if (this.j != null) {
                throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
            }
        }
        d dVar = new d(this, this.f5218c, this.f5219d, this.f5223h, this.f5223h.b(this.f5216a, aVar, z));
        synchronized (this.f5217b) {
            this.j = dVar;
            this.k = false;
            this.l = false;
        }
        return dVar;
    }

    @Nullable
    public IOException l(@Nullable IOException iOException) {
        synchronized (this.f5217b) {
            this.o = true;
        }
        return j(iOException, false);
    }

    public void m(e0 e0Var) {
        e0 e0Var2 = this.f5222g;
        if (e0Var2 != null) {
            if (f.k0.e.D(e0Var2.i(), e0Var.i()) && this.f5223h.e()) {
                return;
            }
            if (this.j != null) {
                throw new IllegalStateException();
            }
            if (this.f5223h != null) {
                j(null, true);
                this.f5223h = null;
            }
        }
        this.f5222g = e0Var;
        this.f5223h = new e(this, this.f5217b, e(e0Var.i()), this.f5218c, this.f5219d);
    }

    @Nullable
    Socket n() {
        int i2 = 0;
        int size = this.f5224i.p.size();
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            }
            if (this.f5224i.p.get(i2).get() == this) {
                break;
            }
            i2++;
        }
        if (i2 == -1) {
            throw new IllegalStateException();
        }
        f fVar = this.f5224i;
        fVar.p.remove(i2);
        this.f5224i = null;
        if (!fVar.p.isEmpty()) {
            return null;
        }
        fVar.q = System.nanoTime();
        if (this.f5217b.c(fVar)) {
            return fVar.t();
        }
        return null;
    }

    public void o() {
        if (this.n) {
            throw new IllegalStateException();
        }
        this.n = true;
        this.f5220e.p();
    }

    public void p() {
        this.f5220e.m();
    }
}
