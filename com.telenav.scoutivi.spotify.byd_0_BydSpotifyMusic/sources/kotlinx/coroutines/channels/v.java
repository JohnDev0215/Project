package kotlinx.coroutines.channels;

import java.util.concurrent.locks.ReentrantLock;
import kotlinx.coroutines.internal.k0;

/* JADX INFO: compiled from: ConflatedChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public class v<E> extends a<E> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final ReentrantLock f7589i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Object f7590j;

    public v(r5.l<? super E, g5.r> lVar) {
        super(lVar);
        this.f7589i = new ReentrantLock();
        this.f7590j = b.f7541a;
    }

    private final k0 R(Object obj) {
        r5.l<E, g5.r> lVar;
        Object obj2 = this.f7590j;
        k0 k0VarD = null;
        if (obj2 != b.f7541a && (lVar = this.f7549g) != null) {
            k0VarD = kotlinx.coroutines.internal.v.d(lVar, obj2, null, 2, null);
        }
        this.f7590j = obj;
        return k0VarD;
    }

    @Override // kotlinx.coroutines.channels.a
    protected boolean C(b0<? super E> b0Var) {
        ReentrantLock reentrantLock = this.f7589i;
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
        return this.f7590j == b.f7541a;
    }

    @Override // kotlinx.coroutines.channels.a
    protected void H(boolean z6) {
        ReentrantLock reentrantLock = this.f7589i;
        reentrantLock.lock();
        try {
            k0 k0VarR = R(b.f7541a);
            g5.r rVar = g5.r.f6563a;
            reentrantLock.unlock();
            super.H(z6);
            if (k0VarR != null) {
                throw k0VarR;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.a
    protected Object K() {
        ReentrantLock reentrantLock = this.f7589i;
        reentrantLock.lock();
        try {
            Object obj = this.f7590j;
            kotlinx.coroutines.internal.c0 c0Var = b.f7541a;
            if (obj != c0Var) {
                this.f7590j = c0Var;
                g5.r rVar = g5.r.f6563a;
                return obj;
            }
            Object objG = g();
            if (objG == null) {
                objG = b.f7544d;
            }
            return objG;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.a
    protected Object L(kotlinx.coroutines.selects.f<?> fVar) {
        ReentrantLock reentrantLock = this.f7589i;
        reentrantLock.lock();
        try {
            Object obj = this.f7590j;
            kotlinx.coroutines.internal.c0 c0Var = b.f7541a;
            if (obj == c0Var) {
                Object objG = g();
                if (objG == null) {
                    objG = b.f7544d;
                }
                return objG;
            }
            if (!fVar.j()) {
                return kotlinx.coroutines.selects.g.d();
            }
            Object obj2 = this.f7590j;
            this.f7590j = c0Var;
            g5.r rVar = g5.r.f6563a;
            return obj2;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.c
    protected String e() {
        return "(value=" + this.f7590j + ')';
    }

    @Override // kotlinx.coroutines.channels.a, kotlinx.coroutines.channels.c0
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.f7589i;
        reentrantLock.lock();
        try {
            return G();
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
        return false;
    }

    @Override // kotlinx.coroutines.channels.c
    protected Object q(E e7) {
        d0<E> d0VarU;
        ReentrantLock reentrantLock = this.f7589i;
        reentrantLock.lock();
        try {
            r<?> rVarG = g();
            if (rVarG != null) {
                return rVarG;
            }
            if (this.f7590j == b.f7541a) {
                do {
                    d0VarU = u();
                    if (d0VarU != null) {
                        if (d0VarU instanceof r) {
                            kotlin.jvm.internal.l.c(d0VarU);
                            return d0VarU;
                        }
                        kotlin.jvm.internal.l.c(d0VarU);
                    }
                } while (d0VarU.l(e7, null) == null);
                g5.r rVar = g5.r.f6563a;
                reentrantLock.unlock();
                kotlin.jvm.internal.l.c(d0VarU);
                d0VarU.q(e7);
                kotlin.jvm.internal.l.c(d0VarU);
                return d0VarU.g();
            }
            k0 k0VarR = R(e7);
            if (k0VarR == null) {
                return b.f7542b;
            }
            throw k0VarR;
        } finally {
            reentrantLock.unlock();
        }
    }
}
