package h;

import e.a0;
import e.b0;
import e.u;
import f.q;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: OkHttpCall.java */
/* JADX INFO: loaded from: classes.dex */
public final class h<T> implements h.b<T> {

    @GuardedBy("this")
    public boolean E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final n<T, ?> f5766c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    public final Object[] f5767d;
    public volatile boolean s;

    @GuardedBy("this")
    @Nullable
    public e.e x;

    @GuardedBy("this")
    @Nullable
    public Throwable y;

    /* JADX INFO: compiled from: OkHttpCall.java */
    public class a implements e.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f5768a;

        public a(d dVar) {
            this.f5768a = dVar;
        }

        @Override // e.f
        public void a(e.e eVar, a0 a0Var) throws IOException {
            try {
                d(h.this.e(a0Var));
            } catch (Throwable th) {
                c(th);
            }
        }

        @Override // e.f
        public void b(e.e eVar, IOException iOException) {
            try {
                this.f5768a.a(h.this, iOException);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public final void c(Throwable th) {
            try {
                this.f5768a.a(h.this, th);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }

        public final void d(l<T> lVar) {
            try {
                this.f5768a.b(h.this, lVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: OkHttpCall.java */
    public static final class b extends b0 {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b0 f5770d;
        public IOException s;

        /* JADX INFO: compiled from: OkHttpCall.java */
        public class a extends f.g {
            public a(q qVar) {
                super(qVar);
            }

            @Override // f.g, f.q
            public long n(f.c cVar, long j) throws IOException {
                try {
                    return super.n(cVar, j);
                } catch (IOException e2) {
                    b.this.s = e2;
                    throw e2;
                }
            }
        }

        public b(b0 b0Var) {
            this.f5770d = b0Var;
        }

        @Override // e.b0
        public u H() {
            return this.f5770d.H();
        }

        @Override // e.b0
        public f.e K() {
            return f.k.b(new a(this.f5770d.K()));
        }

        public void L() throws IOException {
            IOException iOException = this.s;
            if (iOException != null) {
                throw iOException;
            }
        }

        @Override // e.b0, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f5770d.close();
        }

        @Override // e.b0
        public long v() {
            return this.f5770d.v();
        }
    }

    /* JADX INFO: compiled from: OkHttpCall.java */
    public static final class c extends b0 {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final u f5772d;
        public final long s;

        public c(u uVar, long j) {
            this.f5772d = uVar;
            this.s = j;
        }

        @Override // e.b0
        public u H() {
            return this.f5772d;
        }

        @Override // e.b0
        public f.e K() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }

        @Override // e.b0
        public long v() {
            return this.s;
        }
    }

    public h(n<T, ?> nVar, @Nullable Object[] objArr) {
        this.f5766c = nVar;
        this.f5767d = objArr;
    }

    @Override // h.b
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public h<T> m1clone() {
        return new h<>(this.f5766c, this.f5767d);
    }

    public final e.e b() throws IOException {
        e.e eVarA = this.f5766c.f5834c.a(this.f5766c.c(this.f5767d));
        Objects.requireNonNull(eVarA, "Call.Factory returned null.");
        return eVarA;
    }

    @Override // h.b
    public l<T> c() throws IOException {
        e.e eVarB;
        synchronized (this) {
            if (this.E3) {
                throw new IllegalStateException("Already executed.");
            }
            this.E3 = true;
            Throwable th = this.y;
            if (th != null) {
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw ((RuntimeException) th);
            }
            eVarB = this.x;
            if (eVarB == null) {
                try {
                    eVarB = b();
                    this.x = eVarB;
                } catch (IOException | RuntimeException e2) {
                    this.y = e2;
                    throw e2;
                }
            }
        }
        if (this.s) {
            eVarB.cancel();
        }
        return e(eVarB.c());
    }

    @Override // h.b
    public void cancel() {
        e.e eVar;
        this.s = true;
        synchronized (this) {
            eVar = this.x;
        }
        if (eVar != null) {
            eVar.cancel();
        }
    }

    @Override // h.b
    public boolean d() {
        boolean z = true;
        if (this.s) {
            return true;
        }
        synchronized (this) {
            e.e eVar = this.x;
            if (eVar == null || !eVar.d()) {
                z = false;
            }
        }
        return z;
    }

    public l<T> e(a0 a0Var) throws IOException {
        b0 b0VarC = a0Var.c();
        a0 a0VarC = a0Var.M().b(new c(b0VarC.H(), b0VarC.v())).c();
        int iS = a0VarC.s();
        if (iS < 200 || iS >= 300) {
            try {
                return l.c(o.a(b0VarC), a0VarC);
            } finally {
                b0VarC.close();
            }
        }
        if (iS == 204 || iS == 205) {
            b0VarC.close();
            return l.f(null, a0VarC);
        }
        b bVar = new b(b0VarC);
        try {
            return l.f(this.f5766c.d(bVar), a0VarC);
        } catch (RuntimeException e2) {
            bVar.L();
            throw e2;
        }
    }

    @Override // h.b
    public void v(d<T> dVar) {
        e.e eVar;
        Throwable th;
        o.b(dVar, "callback == null");
        synchronized (this) {
            if (this.E3) {
                throw new IllegalStateException("Already executed.");
            }
            this.E3 = true;
            eVar = this.x;
            th = this.y;
            if (eVar == null && th == null) {
                try {
                    e.e eVarB = b();
                    this.x = eVarB;
                    eVar = eVarB;
                } catch (Throwable th2) {
                    th = th2;
                    this.y = th;
                }
            }
        }
        if (th != null) {
            dVar.a(this, th);
            return;
        }
        if (this.s) {
            eVar.cancel();
        }
        eVar.s(new a(dVar));
    }
}
