package kotlinx.coroutines.channels;

import java.util.concurrent.locks.ReentrantLock;
import kotlinx.coroutines.channels.a;
import kotlinx.coroutines.internal.k0;

/* JADX INFO: compiled from: ArrayChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public class f<E> extends a<E> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final ReentrantLock f7561i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Object[] f7562j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f7563k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final int f7564l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final k f7565m;
    private volatile int size;

    public f(int i7, k kVar, r5.l<? super E, g5.r> lVar) {
        super(lVar);
        this.f7564l = i7;
        this.f7565m = kVar;
        if (!(i7 >= 1)) {
            throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i7 + " was specified").toString());
        }
        this.f7561i = new ReentrantLock();
        Object[] objArr = new Object[Math.min(i7, 8)];
        h5.k.k(objArr, b.f7541a, 0, 0, 6, null);
        g5.r rVar = g5.r.f6563a;
        this.f7562j = objArr;
        this.size = 0;
    }

    private final void R(int i7, E e7) {
        if (i7 < this.f7564l) {
            S(i7);
            Object[] objArr = this.f7562j;
            objArr[(this.f7563k + i7) % objArr.length] = e7;
        } else {
            Object[] objArr2 = this.f7562j;
            int i8 = this.f7563k;
            objArr2[i8 % objArr2.length] = null;
            objArr2[(i7 + i8) % objArr2.length] = e7;
            this.f7563k = (i8 + 1) % objArr2.length;
        }
    }

    private final void S(int i7) {
        Object[] objArr = this.f7562j;
        if (i7 >= objArr.length) {
            int iMin = Math.min(objArr.length * 2, this.f7564l);
            Object[] objArr2 = new Object[iMin];
            for (int i8 = 0; i8 < i7; i8++) {
                Object[] objArr3 = this.f7562j;
                objArr2[i8] = objArr3[(this.f7563k + i8) % objArr3.length];
            }
            h5.k.j(objArr2, b.f7541a, i7, iMin);
            this.f7562j = objArr2;
            this.f7563k = 0;
        }
    }

    private final kotlinx.coroutines.internal.c0 T(int i7) {
        if (i7 < this.f7564l) {
            this.size = i7 + 1;
            return null;
        }
        int i8 = e.f7560a[this.f7565m.ordinal()];
        if (i8 == 1) {
            return b.f7543c;
        }
        if (i8 == 2) {
            return b.f7542b;
        }
        if (i8 == 3) {
            return null;
        }
        throw new g5.j();
    }

    @Override // kotlinx.coroutines.channels.a
    protected boolean C(b0<? super E> b0Var) {
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            return super.C(b0Var);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.a
    protected final boolean E() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.a
    protected final boolean F() {
        return this.size == 0;
    }

    @Override // kotlinx.coroutines.channels.a
    protected void H(boolean z6) {
        r5.l<E, g5.r> lVar = this.f7549g;
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            int i7 = this.size;
            k0 k0VarC = null;
            for (int i8 = 0; i8 < i7; i8++) {
                Object obj = this.f7562j[this.f7563k];
                if (lVar != null && obj != b.f7541a) {
                    k0VarC = kotlinx.coroutines.internal.v.c(lVar, obj, k0VarC);
                }
                Object[] objArr = this.f7562j;
                int i9 = this.f7563k;
                objArr[i9] = b.f7541a;
                this.f7563k = (i9 + 1) % objArr.length;
            }
            this.size = 0;
            g5.r rVar = g5.r.f6563a;
            reentrantLock.unlock();
            super.H(z6);
            if (k0VarC != null) {
                throw k0VarC;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.a
    protected Object K() {
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            int i7 = this.size;
            if (i7 == 0) {
                Object objG = g();
                if (objG == null) {
                    objG = b.f7544d;
                }
                return objG;
            }
            Object[] objArr = this.f7562j;
            int i8 = this.f7563k;
            Object obj = objArr[i8];
            f0 f0Var = null;
            objArr[i8] = null;
            this.size = i7 - 1;
            Object objR = b.f7544d;
            boolean z6 = false;
            if (i7 == this.f7564l) {
                f0 f0Var2 = null;
                while (true) {
                    f0 f0VarV = v();
                    if (f0VarV == null) {
                        f0Var = f0Var2;
                        break;
                    }
                    kotlin.jvm.internal.l.c(f0VarV);
                    if (f0VarV.T(null) != null) {
                        kotlin.jvm.internal.l.c(f0VarV);
                        objR = f0VarV.R();
                        z6 = true;
                        f0Var = f0VarV;
                        break;
                    }
                    kotlin.jvm.internal.l.c(f0VarV);
                    f0VarV.U();
                    f0Var2 = f0VarV;
                }
            }
            if (objR != b.f7544d && !(objR instanceof r)) {
                this.size = i7;
                Object[] objArr2 = this.f7562j;
                objArr2[(this.f7563k + i7) % objArr2.length] = objR;
            }
            this.f7563k = (this.f7563k + 1) % this.f7562j.length;
            g5.r rVar = g5.r.f6563a;
            if (z6) {
                kotlin.jvm.internal.l.c(f0Var);
                f0Var.Q();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.a
    protected Object L(kotlinx.coroutines.selects.f<?> fVar) {
        boolean z6;
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            int i7 = this.size;
            if (i7 == 0) {
                Object objG = g();
                if (objG == null) {
                    objG = b.f7544d;
                }
                return objG;
            }
            Object[] objArr = this.f7562j;
            int i8 = this.f7563k;
            Object obj = objArr[i8];
            f0 f0VarO = null;
            objArr[i8] = null;
            this.size = i7 - 1;
            Object objR = b.f7544d;
            if (i7 == this.f7564l) {
                while (true) {
                    a.f<E> fVarA = A();
                    Object objW = fVar.w(fVarA);
                    if (objW == null) {
                        f0VarO = fVarA.o();
                        kotlin.jvm.internal.l.c(f0VarO);
                        objR = f0VarO.R();
                        z6 = true;
                        break;
                    }
                    if (objW == b.f7544d) {
                        break;
                    }
                    if (objW != kotlinx.coroutines.internal.c.f7623b) {
                        if (objW == kotlinx.coroutines.selects.g.d()) {
                            this.size = i7;
                            this.f7562j[this.f7563k] = obj;
                            return objW;
                        }
                        if (!(objW instanceof r)) {
                            throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + objW).toString());
                        }
                        f0VarO = (f0) objW;
                        z6 = true;
                        objR = objW;
                    }
                }
            } else {
                z6 = false;
            }
            if (objR != b.f7544d && !(objR instanceof r)) {
                this.size = i7;
                Object[] objArr2 = this.f7562j;
                objArr2[(this.f7563k + i7) % objArr2.length] = objR;
            } else if (!fVar.j()) {
                this.size = i7;
                this.f7562j[this.f7563k] = obj;
                return kotlinx.coroutines.selects.g.d();
            }
            this.f7563k = (this.f7563k + 1) % this.f7562j.length;
            g5.r rVar = g5.r.f6563a;
            if (z6) {
                kotlin.jvm.internal.l.c(f0VarO);
                f0VarO.Q();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.c
    protected Object d(f0 f0Var) {
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            return super.d(f0Var);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.c
    protected String e() {
        return "(buffer:capacity=" + this.f7564l + ",size=" + this.size + ')';
    }

    @Override // kotlinx.coroutines.channels.a, kotlinx.coroutines.channels.c0
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.a, kotlinx.coroutines.channels.c0
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            return G();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.c, kotlinx.coroutines.channels.g0
    public boolean isFull() {
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            return p();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.c
    protected final boolean n() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.c
    protected final boolean o() {
        return this.size == this.f7564l && this.f7565m == k.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.c
    protected Object q(E e7) {
        d0<E> d0VarU;
        ReentrantLock reentrantLock = this.f7561i;
        reentrantLock.lock();
        try {
            int i7 = this.size;
            r<?> rVarG = g();
            if (rVarG != null) {
                return rVarG;
            }
            kotlinx.coroutines.internal.c0 c0VarT = T(i7);
            if (c0VarT != null) {
                return c0VarT;
            }
            if (i7 == 0) {
                do {
                    d0VarU = u();
                    if (d0VarU != null) {
                        if (d0VarU instanceof r) {
                            this.size = i7;
                            kotlin.jvm.internal.l.c(d0VarU);
                            return d0VarU;
                        }
                        kotlin.jvm.internal.l.c(d0VarU);
                    }
                } while (d0VarU.l(e7, null) == null);
                this.size = i7;
                g5.r rVar = g5.r.f6563a;
                reentrantLock.unlock();
                kotlin.jvm.internal.l.c(d0VarU);
                d0VarU.q(e7);
                kotlin.jvm.internal.l.c(d0VarU);
                return d0VarU.g();
            }
            R(i7, e7);
            return b.f7542b;
        } finally {
            reentrantLock.unlock();
        }
    }
}
