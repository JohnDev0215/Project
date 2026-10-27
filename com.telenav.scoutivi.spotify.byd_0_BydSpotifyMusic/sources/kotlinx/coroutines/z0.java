package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.q0;

/* JADX INFO: compiled from: EventLoop.common.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class z0 extends a1 implements q0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7857j = AtomicReferenceFieldUpdater.newUpdater(z0.class, Object.class, "_queue");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7858k = AtomicReferenceFieldUpdater.newUpdater(z0.class, Object.class, "_delayed");
    private volatile Object _queue = null;
    private volatile Object _delayed = null;
    private volatile int _isCompleted = 0;

    /* JADX INFO: compiled from: EventLoop.common.kt */
    private final class a extends c {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final i<g5.r> f7859i;

        /* JADX WARN: Multi-variable type inference failed */
        public a(long j7, i<? super g5.r> iVar) {
            super(j7);
            this.f7859i = iVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f7859i.h(z0.this, g5.r.f6563a);
        }

        @Override // kotlinx.coroutines.z0.c
        public String toString() {
            return super.toString() + this.f7859i.toString();
        }
    }

    /* JADX INFO: compiled from: EventLoop.common.kt */
    private static final class b extends c {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final Runnable f7861i;

        public b(long j7, Runnable runnable) {
            super(j7);
            this.f7861i = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f7861i.run();
        }

        @Override // kotlinx.coroutines.z0.c
        public String toString() {
            return super.toString() + this.f7861i.toString();
        }
    }

    /* JADX INFO: compiled from: EventLoop.common.kt */
    public static abstract class c implements Runnable, Comparable<c>, v0, kotlinx.coroutines.internal.i0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Object f7862e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f7863g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public long f7864h;

        public c(long j7) {
            this.f7864h = j7;
        }

        @Override // kotlinx.coroutines.internal.i0
        public void a(int i7) {
            this.f7863g = i7;
        }

        @Override // kotlinx.coroutines.internal.i0
        public void b(kotlinx.coroutines.internal.h0<?> h0Var) {
            if (!(this.f7862e != c1.f7517a)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            this.f7862e = h0Var;
        }

        @Override // kotlinx.coroutines.internal.i0
        public kotlinx.coroutines.internal.h0<?> c() {
            Object obj = this.f7862e;
            if (!(obj instanceof kotlinx.coroutines.internal.h0)) {
                obj = null;
            }
            return (kotlinx.coroutines.internal.h0) obj;
        }

        @Override // kotlinx.coroutines.internal.i0
        public int d() {
            return this.f7863g;
        }

        @Override // kotlinx.coroutines.v0
        public final synchronized void dispose() {
            Object obj = this.f7862e;
            if (obj == c1.f7517a) {
                return;
            }
            if (!(obj instanceof d)) {
                obj = null;
            }
            d dVar = (d) obj;
            if (dVar != null) {
                dVar.g(this);
            }
            this.f7862e = c1.f7517a;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compareTo(c cVar) {
            long j7 = this.f7864h - cVar.f7864h;
            if (j7 > 0) {
                return 1;
            }
            return j7 < 0 ? -1 : 0;
        }

        public final synchronized int f(long j7, d dVar, z0 z0Var) {
            if (this.f7862e == c1.f7517a) {
                return 2;
            }
            synchronized (dVar) {
                c cVarB = dVar.b();
                if (z0Var.u0()) {
                    return 1;
                }
                if (cVarB == null) {
                    dVar.f7865b = j7;
                } else {
                    long j8 = cVarB.f7864h;
                    if (j8 - j7 < 0) {
                        j7 = j8;
                    }
                    if (j7 - dVar.f7865b > 0) {
                        dVar.f7865b = j7;
                    }
                }
                long j9 = this.f7864h;
                long j10 = dVar.f7865b;
                if (j9 - j10 < 0) {
                    this.f7864h = j10;
                }
                dVar.a(this);
                return 0;
            }
        }

        public final boolean g(long j7) {
            return j7 - this.f7864h >= 0;
        }

        public String toString() {
            return "Delayed[nanos=" + this.f7864h + ']';
        }
    }

    /* JADX INFO: compiled from: EventLoop.common.kt */
    public static final class d extends kotlinx.coroutines.internal.h0<c> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f7865b;

        public d(long j7) {
            this.f7865b = j7;
        }
    }

    private final int A0(long j7, c cVar) {
        if (u0()) {
            return 1;
        }
        d dVar = (d) this._delayed;
        if (dVar == null) {
            j.a(f7858k, this, null, new d(j7));
            Object obj = this._delayed;
            kotlin.jvm.internal.l.c(obj);
            dVar = (d) obj;
        }
        return cVar.f(j7, dVar, this);
    }

    private final void C0(boolean z6) {
        this._isCompleted = z6 ? 1 : 0;
    }

    private final boolean D0(c cVar) {
        d dVar = (d) this._delayed;
        return (dVar != null ? dVar.e() : null) == cVar;
    }

    private final void q0() {
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                if (j.a(f7857j, this, null, c1.f7518b)) {
                    return;
                }
            } else if (obj instanceof kotlinx.coroutines.internal.r) {
                ((kotlinx.coroutines.internal.r) obj).d();
                return;
            } else {
                if (obj == c1.f7518b) {
                    return;
                }
                kotlinx.coroutines.internal.r rVar = new kotlinx.coroutines.internal.r(8, true);
                rVar.a((Runnable) obj);
                if (j.a(f7857j, this, obj, rVar)) {
                    return;
                }
            }
        }
    }

    private final Runnable r0() {
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                return null;
            }
            if (obj instanceof kotlinx.coroutines.internal.r) {
                kotlinx.coroutines.internal.r rVar = (kotlinx.coroutines.internal.r) obj;
                Object objJ = rVar.j();
                if (objJ != kotlinx.coroutines.internal.r.f7671g) {
                    return (Runnable) objJ;
                }
                j.a(f7857j, this, obj, rVar.i());
            } else {
                if (obj == c1.f7518b) {
                    return null;
                }
                if (j.a(f7857j, this, obj, null)) {
                    return (Runnable) obj;
                }
            }
        }
    }

    private final boolean t0(Runnable runnable) {
        while (true) {
            Object obj = this._queue;
            if (u0()) {
                return false;
            }
            if (obj == null) {
                if (j.a(f7857j, this, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof kotlinx.coroutines.internal.r) {
                kotlinx.coroutines.internal.r rVar = (kotlinx.coroutines.internal.r) obj;
                int iA = rVar.a(runnable);
                if (iA == 0) {
                    return true;
                }
                if (iA == 1) {
                    j.a(f7857j, this, obj, rVar.i());
                } else if (iA == 2) {
                    return false;
                }
            } else {
                if (obj == c1.f7518b) {
                    return false;
                }
                kotlinx.coroutines.internal.r rVar2 = new kotlinx.coroutines.internal.r(8, true);
                rVar2.a((Runnable) obj);
                rVar2.a(runnable);
                if (j.a(f7857j, this, obj, rVar2)) {
                    return true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean u0() {
        return this._isCompleted;
    }

    private final void x0() {
        c cVarI;
        i2 i2VarA = j2.a();
        long jA = i2VarA != null ? i2VarA.a() : System.nanoTime();
        while (true) {
            d dVar = (d) this._delayed;
            if (dVar == null || (cVarI = dVar.i()) == null) {
                return;
            } else {
                n0(jA, cVarI);
            }
        }
    }

    @Override // kotlinx.coroutines.q0
    public void A(long j7, i<? super g5.r> iVar) {
        long jC = c1.c(j7);
        if (jC < 4611686018427387903L) {
            i2 i2VarA = j2.a();
            long jA = i2VarA != null ? i2VarA.a() : System.nanoTime();
            a aVar = new a(jC + jA, iVar);
            m.a(iVar, aVar);
            z0(jA, aVar);
        }
    }

    protected final v0 B0(long j7, Runnable runnable) {
        long jC = c1.c(j7);
        if (jC >= 4611686018427387903L) {
            return x1.f7849e;
        }
        i2 i2VarA = j2.a();
        long jA = i2VarA != null ? i2VarA.a() : System.nanoTime();
        b bVar = new b(jC + jA, runnable);
        z0(jA, bVar);
        return bVar;
    }

    public v0 N(long j7, Runnable runnable, k5.g gVar) {
        return q0.a.a(this, j7, runnable, gVar);
    }

    @Override // kotlinx.coroutines.e0
    public final void a0(k5.g gVar, Runnable runnable) {
        s0(runnable);
    }

    @Override // kotlinx.coroutines.y0
    protected long g0() {
        c cVarE;
        if (super.g0() == 0) {
            return 0L;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (!(obj instanceof kotlinx.coroutines.internal.r)) {
                return obj == c1.f7518b ? Long.MAX_VALUE : 0L;
            }
            if (!((kotlinx.coroutines.internal.r) obj).g()) {
                return 0L;
            }
        }
        d dVar = (d) this._delayed;
        if (dVar == null || (cVarE = dVar.e()) == null) {
            return Long.MAX_VALUE;
        }
        long j7 = cVarE.f7864h;
        i2 i2VarA = j2.a();
        return v5.h.c(j7 - (i2VarA != null ? i2VarA.a() : System.nanoTime()), 0L);
    }

    public final void s0(Runnable runnable) {
        if (t0(runnable)) {
            o0();
        } else {
            n0.f7710m.s0(runnable);
        }
    }

    @Override // kotlinx.coroutines.y0
    protected void shutdown() {
        h2.f7614b.b();
        C0(true);
        q0();
        while (w0() <= 0) {
        }
        x0();
    }

    protected boolean v0() {
        if (!k0()) {
            return false;
        }
        d dVar = (d) this._delayed;
        if (dVar != null && !dVar.d()) {
            return false;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (obj instanceof kotlinx.coroutines.internal.r) {
                return ((kotlinx.coroutines.internal.r) obj).g();
            }
            if (obj != c1.f7518b) {
                return false;
            }
        }
        return true;
    }

    public long w0() {
        c cVarH;
        if (l0()) {
            return 0L;
        }
        d dVar = (d) this._delayed;
        if (dVar != null && !dVar.d()) {
            i2 i2VarA = j2.a();
            long jA = i2VarA != null ? i2VarA.a() : System.nanoTime();
            do {
                synchronized (dVar) {
                    c cVarB = dVar.b();
                    if (cVarB != null) {
                        c cVar = cVarB;
                        cVarH = cVar.g(jA) ? t0(cVar) : false ? dVar.h(0) : null;
                    }
                }
            } while (cVarH != null);
        }
        Runnable runnableR0 = r0();
        if (runnableR0 == null) {
            return g0();
        }
        runnableR0.run();
        return 0L;
    }

    protected final void y0() {
        this._queue = null;
        this._delayed = null;
    }

    public final void z0(long j7, c cVar) {
        int iA0 = A0(j7, cVar);
        if (iA0 == 0) {
            if (D0(cVar)) {
                o0();
            }
        } else if (iA0 == 1) {
            n0(j7, cVar);
        } else if (iA0 != 2) {
            throw new IllegalStateException("unexpected result".toString());
        }
    }
}
