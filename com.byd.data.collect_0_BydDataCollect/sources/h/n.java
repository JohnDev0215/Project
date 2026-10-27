package h;

import f.e0;
import f.g0;
import f.h0;
import f.i;
import f.z;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: OkHttpCall.java */
/* JADX INFO: loaded from: classes.dex */
final class n<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final s f5590b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object[] f5591c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final i.a f5592d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h<h0, T> f5593e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile boolean f5594f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @GuardedBy("this")
    @Nullable
    private f.i f5595g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @GuardedBy("this")
    @Nullable
    private Throwable f5596h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @GuardedBy("this")
    private boolean f5597i;

    /* JADX INFO: compiled from: OkHttpCall.java */
    class a implements f.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ f f5598a;

        a(f fVar) {
            this.f5598a = fVar;
        }

        private void c(Throwable th) {
            try {
                this.f5598a.k(n.this, th);
            } catch (Throwable th2) {
                y.s(th2);
                th2.printStackTrace();
            }
        }

        @Override // f.j
        public void a(f.i iVar, g0 g0Var) {
            try {
                try {
                    this.f5598a.A(n.this, n.this.e(g0Var));
                } catch (Throwable th) {
                    y.s(th);
                    th.printStackTrace();
                }
            } catch (Throwable th2) {
                y.s(th2);
                c(th2);
            }
        }

        @Override // f.j
        public void b(f.i iVar, IOException iOException) {
            c(iOException);
        }
    }

    /* JADX INFO: compiled from: OkHttpCall.java */
    static final class b extends h0 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final h0 f5600b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final BufferedSource f5601c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Nullable
        IOException f5602d;

        /* JADX INFO: compiled from: OkHttpCall.java */
        class a extends ForwardingSource {
            a(Source source) {
                super(source);
            }

            @Override // okio.ForwardingSource, okio.Source
            public long p(Buffer buffer, long j) throws IOException {
                try {
                    return super.p(buffer, j);
                } catch (IOException e2) {
                    b.this.f5602d = e2;
                    throw e2;
                }
            }
        }

        b(h0 h0Var) {
            this.f5600b = h0Var;
            this.f5601c = Okio.c(new a(h0Var.S()));
        }

        @Override // f.h0
        public BufferedSource S() {
            return this.f5601c;
        }

        void U() throws IOException {
            IOException iOException = this.f5602d;
            if (iOException != null) {
                throw iOException;
            }
        }

        @Override // f.h0, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f5600b.close();
        }

        @Override // f.h0
        public long j() {
            return this.f5600b.j();
        }

        @Override // f.h0
        public z y() {
            return this.f5600b.y();
        }
    }

    /* JADX INFO: compiled from: OkHttpCall.java */
    static final class c extends h0 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        private final z f5604b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long f5605c;

        c(@Nullable z zVar, long j) {
            this.f5604b = zVar;
            this.f5605c = j;
        }

        @Override // f.h0
        public BufferedSource S() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }

        @Override // f.h0
        public long j() {
            return this.f5605c;
        }

        @Override // f.h0
        public z y() {
            return this.f5604b;
        }
    }

    n(s sVar, Object[] objArr, i.a aVar, h<h0, T> hVar) {
        this.f5590b = sVar;
        this.f5591c = objArr;
        this.f5592d = aVar;
        this.f5593e = hVar;
    }

    private f.i c() {
        f.i iVarB = this.f5592d.b(this.f5590b.a(this.f5591c));
        Objects.requireNonNull(iVarB, "Call.Factory returned null.");
        return iVarB;
    }

    @Override // h.d
    public void C(f<T> fVar) {
        f.i iVar;
        Throwable th;
        Objects.requireNonNull(fVar, "callback == null");
        synchronized (this) {
            if (this.f5597i) {
                throw new IllegalStateException("Already executed.");
            }
            this.f5597i = true;
            iVar = this.f5595g;
            th = this.f5596h;
            if (iVar == null && th == null) {
                try {
                    f.i iVarC = c();
                    this.f5595g = iVarC;
                    iVar = iVarC;
                } catch (Throwable th2) {
                    th = th2;
                    y.s(th);
                    this.f5596h = th;
                }
            }
        }
        if (th != null) {
            fVar.k(this, th);
            return;
        }
        if (this.f5594f) {
            iVar.cancel();
        }
        iVar.y(new a(fVar));
    }

    @Override // h.d
    public synchronized e0 a() {
        f.i iVar = this.f5595g;
        if (iVar != null) {
            return iVar.a();
        }
        Throwable th = this.f5596h;
        if (th != null) {
            if (th instanceof IOException) {
                throw new RuntimeException("Unable to create request.", this.f5596h);
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw ((Error) th);
        }
        try {
            f.i iVarC = c();
            this.f5595g = iVarC;
            return iVarC.a();
        } catch (IOException e2) {
            this.f5596h = e2;
            throw new RuntimeException("Unable to create request.", e2);
        } catch (Error e3) {
            e = e3;
            y.s(e);
            this.f5596h = e;
            throw e;
        } catch (RuntimeException e4) {
            e = e4;
            y.s(e);
            this.f5596h = e;
            throw e;
        }
    }

    @Override // h.d
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public n<T> clone() {
        return new n<>(this.f5590b, this.f5591c, this.f5592d, this.f5593e);
    }

    @Override // h.d
    public void cancel() {
        f.i iVar;
        this.f5594f = true;
        synchronized (this) {
            iVar = this.f5595g;
        }
        if (iVar != null) {
            iVar.cancel();
        }
    }

    @Override // h.d
    public t<T> d() {
        f.i iVarC;
        synchronized (this) {
            if (this.f5597i) {
                throw new IllegalStateException("Already executed.");
            }
            this.f5597i = true;
            Throwable th = this.f5596h;
            if (th != null) {
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                throw ((Error) th);
            }
            iVarC = this.f5595g;
            if (iVarC == null) {
                try {
                    iVarC = c();
                    this.f5595g = iVarC;
                } catch (IOException | Error | RuntimeException e2) {
                    y.s(e2);
                    this.f5596h = e2;
                    throw e2;
                }
            }
        }
        if (this.f5594f) {
            iVarC.cancel();
        }
        return e(iVarC.d());
    }

    t<T> e(g0 g0Var) throws IOException {
        h0 h0VarA = g0Var.a();
        g0 g0VarC = g0Var.V().b(new c(h0VarA.y(), h0VarA.j())).c();
        int iJ = g0VarC.j();
        if (iJ < 200 || iJ >= 300) {
            try {
                return t.c(y.a(h0VarA), g0VarC);
            } finally {
                h0VarA.close();
            }
        }
        if (iJ == 204 || iJ == 205) {
            h0VarA.close();
            return t.f(null, g0VarC);
        }
        b bVar = new b(h0VarA);
        try {
            return t.f(this.f5593e.a(bVar), g0VarC);
        } catch (RuntimeException e2) {
            bVar.U();
            throw e2;
        }
    }

    @Override // h.d
    public boolean isCanceled() {
        boolean z = true;
        if (this.f5594f) {
            return true;
        }
        synchronized (this) {
            f.i iVar = this.f5595g;
            if (iVar == null || !iVar.isCanceled()) {
                z = false;
            }
        }
        return z;
    }
}
