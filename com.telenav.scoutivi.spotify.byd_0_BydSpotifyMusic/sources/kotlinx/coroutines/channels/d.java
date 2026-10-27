package kotlinx.coroutines.channels;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: ArrayBroadcastChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d<E> extends c<E> implements g<E> {
    private volatile long _head;
    private volatile int _size;
    private volatile long _tail;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final ReentrantLock f7554i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Object[] f7555j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final List<a<E>> f7556k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final int f7557l;

    /* JADX INFO: compiled from: ArrayBroadcastChannel.kt */
    private static final class a<E> extends kotlinx.coroutines.channels.a<E> implements c0<E> {
        private volatile long _subHead;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final ReentrantLock f7558i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final d<E> f7559j;

        public a(d<E> dVar) {
            super(null);
            this.f7559j = dVar;
            this.f7558i = new ReentrantLock();
            this._subHead = 0L;
        }

        private final boolean T() {
            if (f() != null) {
                return false;
            }
            return (F() && this.f7559j.f() == null) ? false : true;
        }

        private final Object U() {
            long jS = S();
            r<?> rVarF = this.f7559j.f();
            if (jS >= this.f7559j.E()) {
                if (rVarF == null) {
                    rVarF = f();
                }
                return rVarF != null ? rVarF : b.f7544d;
            }
            Object objB = this.f7559j.B(jS);
            r<?> rVarF2 = f();
            return rVarF2 != null ? rVarF2 : objB;
        }

        @Override // kotlinx.coroutines.channels.a
        protected boolean E() {
            return false;
        }

        @Override // kotlinx.coroutines.channels.a
        protected boolean F() {
            return S() >= this.f7559j.E();
        }

        @Override // kotlinx.coroutines.channels.a
        protected Object K() {
            boolean z6;
            ReentrantLock reentrantLock = this.f7558i;
            reentrantLock.lock();
            try {
                Object objU = U();
                if ((objU instanceof r) || objU == b.f7544d) {
                    z6 = false;
                } else {
                    V(S() + 1);
                    z6 = true;
                }
                reentrantLock.unlock();
                r rVar = (r) (!(objU instanceof r) ? null : objU);
                if (rVar != null) {
                    close(rVar.f7577i);
                }
                if (R() ? true : z6) {
                    d.J(this.f7559j, null, null, 3, null);
                }
                return objU;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        @Override // kotlinx.coroutines.channels.a
        protected Object L(kotlinx.coroutines.selects.f<?> fVar) {
            ReentrantLock reentrantLock = this.f7558i;
            reentrantLock.lock();
            try {
                Object objU = U();
                boolean z6 = false;
                if (!(objU instanceof r) && objU != b.f7544d) {
                    if (fVar.j()) {
                        V(S() + 1);
                        z6 = true;
                    } else {
                        objU = kotlinx.coroutines.selects.g.d();
                    }
                }
                reentrantLock.unlock();
                r rVar = (r) (!(objU instanceof r) ? null : objU);
                if (rVar != null) {
                    close(rVar.f7577i);
                }
                if (R() ? true : z6) {
                    d.J(this.f7559j, null, null, 3, null);
                }
                return objU;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
        
            r2 = (kotlinx.coroutines.channels.r) r1;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean R() {
            /*
                r8 = this;
                r0 = 0
            L1:
                boolean r1 = r8.T()
                r2 = 0
                if (r1 == 0) goto L5b
                java.util.concurrent.locks.ReentrantLock r1 = r8.f7558i
                boolean r1 = r1.tryLock()
                if (r1 != 0) goto L11
                goto L5b
            L11:
                java.lang.Object r1 = r8.U()     // Catch: java.lang.Throwable -> L54
                kotlinx.coroutines.internal.c0 r3 = kotlinx.coroutines.channels.b.f7544d     // Catch: java.lang.Throwable -> L54
                if (r1 != r3) goto L1f
            L19:
                java.util.concurrent.locks.ReentrantLock r1 = r8.f7558i
                r1.unlock()
                goto L1
            L1f:
                boolean r3 = r1 instanceof kotlinx.coroutines.channels.r     // Catch: java.lang.Throwable -> L54
                if (r3 == 0) goto L2c
                r2 = r1
                kotlinx.coroutines.channels.r r2 = (kotlinx.coroutines.channels.r) r2     // Catch: java.lang.Throwable -> L54
            L26:
                java.util.concurrent.locks.ReentrantLock r1 = r8.f7558i
                r1.unlock()
                goto L5b
            L2c:
                kotlinx.coroutines.channels.d0 r3 = r8.u()     // Catch: java.lang.Throwable -> L54
                if (r3 == 0) goto L26
                boolean r4 = r3 instanceof kotlinx.coroutines.channels.r     // Catch: java.lang.Throwable -> L54
                if (r4 == 0) goto L37
                goto L26
            L37:
                kotlinx.coroutines.internal.c0 r2 = r3.l(r1, r2)     // Catch: java.lang.Throwable -> L54
                if (r2 == 0) goto L19
                long r4 = r8.S()     // Catch: java.lang.Throwable -> L54
                r6 = 1
                long r4 = r4 + r6
                r8.V(r4)     // Catch: java.lang.Throwable -> L54
                r0 = 1
                java.util.concurrent.locks.ReentrantLock r2 = r8.f7558i
                r2.unlock()
                kotlin.jvm.internal.l.c(r3)
                r3.q(r1)
                goto L1
            L54:
                r0 = move-exception
                java.util.concurrent.locks.ReentrantLock r1 = r8.f7558i
                r1.unlock()
                throw r0
            L5b:
                if (r2 == 0) goto L62
                java.lang.Throwable r1 = r2.f7577i
                r8.close(r1)
            L62:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.d.a.R():boolean");
        }

        public final long S() {
            return this._subHead;
        }

        public final void V(long j7) {
            this._subHead = j7;
        }

        @Override // kotlinx.coroutines.channels.c, kotlinx.coroutines.channels.g0
        public boolean close(Throwable th) {
            boolean zClose = super.close(th);
            if (zClose) {
                d.J(this.f7559j, null, this, 1, null);
                ReentrantLock reentrantLock = this.f7558i;
                reentrantLock.lock();
                try {
                    V(this.f7559j.E());
                    g5.r rVar = g5.r.f6563a;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return zClose;
        }

        @Override // kotlinx.coroutines.channels.c
        protected boolean n() {
            throw new IllegalStateException("Should not be used".toString());
        }

        @Override // kotlinx.coroutines.channels.c
        protected boolean o() {
            throw new IllegalStateException("Should not be used".toString());
        }
    }

    public d(int i7) {
        super(null);
        this.f7557l = i7;
        if (!(i7 >= 1)) {
            throw new IllegalArgumentException(("ArrayBroadcastChannel capacity must be at least 1, but " + i7 + " was specified").toString());
        }
        this.f7554i = new ReentrantLock();
        this.f7555j = new Object[i7];
        this._head = 0L;
        this._tail = 0L;
        this._size = 0;
        this.f7556k = kotlinx.coroutines.internal.e.a();
    }

    private final long A() {
        Iterator<a<E>> it = this.f7556k.iterator();
        long jE = Long.MAX_VALUE;
        while (it.hasNext()) {
            jE = v5.h.e(jE, it.next().S());
        }
        return jE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final E B(long j7) {
        return (E) this.f7555j[(int) (j7 % ((long) this.f7557l))];
    }

    private final long C() {
        return this._head;
    }

    private final int D() {
        return this._size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long E() {
        return this._tail;
    }

    private final void F(long j7) {
        this._head = j7;
    }

    private final void G(int i7) {
        this._size = i7;
    }

    private final void H(long j7) {
        this._tail = j7;
    }

    private final void I(a<E> aVar, a<E> aVar2) {
        f0 f0VarV;
        while (true) {
            ReentrantLock reentrantLock = this.f7554i;
            reentrantLock.lock();
            if (aVar != null) {
                try {
                    aVar.V(E());
                    boolean zIsEmpty = this.f7556k.isEmpty();
                    this.f7556k.add(aVar);
                    if (!zIsEmpty) {
                        return;
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
            if (aVar2 != null) {
                this.f7556k.remove(aVar2);
                if (C() != aVar2.S()) {
                    return;
                }
            }
            long jA = A();
            long jE = E();
            long jC = C();
            long jE2 = v5.h.e(jA, jE);
            if (jE2 <= jC) {
                return;
            }
            int iD = D();
            while (jC < jE2) {
                Object[] objArr = this.f7555j;
                int i7 = this.f7557l;
                objArr[(int) (jC % ((long) i7))] = null;
                boolean z6 = iD >= i7;
                jC++;
                F(jC);
                iD--;
                G(iD);
                if (z6) {
                    do {
                        f0VarV = v();
                        if (f0VarV != null && !(f0VarV instanceof r)) {
                            kotlin.jvm.internal.l.c(f0VarV);
                        }
                    } while (f0VarV.T(null) == null);
                    this.f7555j[(int) (jE % ((long) this.f7557l))] = f0VarV.R();
                    G(iD + 1);
                    H(jE + 1);
                    g5.r rVar = g5.r.f6563a;
                    reentrantLock.unlock();
                    kotlin.jvm.internal.l.c(f0VarV);
                    f0VarV.Q();
                    z();
                    aVar = null;
                    aVar2 = null;
                }
            }
            return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void J(d dVar, a aVar, a aVar2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            aVar = null;
        }
        if ((i7 & 2) != 0) {
            aVar2 = null;
        }
        dVar.I(aVar, aVar2);
    }

    private final boolean y(Throwable th) {
        boolean zClose = close(th);
        Iterator<a<E>> it = this.f7556k.iterator();
        while (it.hasNext()) {
            it.next().z(th);
        }
        return zClose;
    }

    private final void z() {
        Iterator<a<E>> it = this.f7556k.iterator();
        boolean z6 = false;
        boolean z7 = false;
        while (it.hasNext()) {
            if (it.next().R()) {
                z6 = true;
            }
            z7 = true;
        }
        if (z6 || !z7) {
            J(this, null, null, 3, null);
        }
    }

    @Override // kotlinx.coroutines.channels.g
    public c0<E> c() {
        a aVar = new a(this);
        J(this, aVar, null, 2, null);
        return aVar;
    }

    @Override // kotlinx.coroutines.channels.g
    public void cancel(CancellationException cancellationException) {
        y(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.c, kotlinx.coroutines.channels.g0
    public boolean close(Throwable th) {
        if (!super.close(th)) {
            return false;
        }
        z();
        return true;
    }

    @Override // kotlinx.coroutines.channels.c
    protected String e() {
        return "(buffer:capacity=" + this.f7555j.length + ",size=" + D() + ')';
    }

    @Override // kotlinx.coroutines.channels.c
    protected boolean n() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.c
    protected boolean o() {
        return D() >= this.f7557l;
    }

    @Override // kotlinx.coroutines.channels.c
    protected Object q(E e7) {
        ReentrantLock reentrantLock = this.f7554i;
        reentrantLock.lock();
        try {
            r<?> rVarG = g();
            if (rVarG != null) {
                return rVarG;
            }
            int iD = D();
            if (iD >= this.f7557l) {
                return b.f7543c;
            }
            long jE = E();
            this.f7555j[(int) (jE % ((long) this.f7557l))] = e7;
            G(iD + 1);
            H(jE + 1);
            g5.r rVar = g5.r.f6563a;
            reentrantLock.unlock();
            z();
            return b.f7542b;
        } finally {
            reentrantLock.unlock();
        }
    }
}
