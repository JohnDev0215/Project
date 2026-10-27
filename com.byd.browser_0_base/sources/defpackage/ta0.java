package defpackage;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: DispatchedContinuation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ta0<T> extends va0<T> implements u00, b00<T> {
    public final p00 h;
    public final b00<T> i;
    public Object j = a8.p;
    public final Object k = v83.b(getContext());
    private volatile /* synthetic */ Object _reusableCancellableContinuation = null;

    static {
        AtomicReferenceFieldUpdater.newUpdater(ta0.class, Object.class, "_reusableCancellableContinuation");
    }

    public ta0(p00 p00Var, c00 c00Var) {
        this.h = p00Var;
        this.i = c00Var;
    }

    @Override // defpackage.u00
    public final u00 a() {
        b00<T> b00Var = this.i;
        if (b00Var instanceof u00) {
            return (u00) b00Var;
        }
        return null;
    }

    @Override // defpackage.va0
    public final void b(Object obj, CancellationException cancellationException) {
        if (obj instanceof sv) {
            ((sv) obj).b.c(cancellationException);
        }
    }

    @Override // defpackage.b00
    public final void c(Object obj) {
        m00 context;
        Object objC;
        b00<T> b00Var = this.i;
        m00 context2 = b00Var.getContext();
        Throwable thA = vb2.a(obj);
        Object rvVar = thA == null ? obj : new rv(thA);
        p00 p00Var = this.h;
        if (p00Var.i1()) {
            this.j = rvVar;
            this.g = 0;
            p00Var.h1(context2, this);
            return;
        }
        ThreadLocal<ai0> threadLocal = w83.a;
        ai0 hfVar = threadLocal.get();
        if (hfVar == null) {
            hfVar = new hf(Thread.currentThread());
            threadLocal.set(hfVar);
        }
        long j = hfVar.f;
        if (j >= 4294967296L) {
            this.j = rvVar;
            this.g = 0;
            hfVar.k1(this);
            return;
        }
        hfVar.f = 4294967296L + j;
        try {
            context = getContext();
            objC = v83.c(context, this.k);
        } finally {
            try {
            } finally {
            }
        }
        try {
            b00Var.c(obj);
            vi3 vi3Var = vi3.a;
            while (hfVar.l1()) {
            }
        } finally {
            v83.a(context, objC);
        }
    }

    @Override // defpackage.b00
    public final m00 getContext() {
        return this.i.getContext();
    }

    @Override // defpackage.va0
    public final Object h() {
        Object obj = this.j;
        this.j = a8.p;
        return obj;
    }

    public final void i() {
        Object obj = this._reusableCancellableContinuation;
        zl zlVar = obj instanceof zl ? (zl) obj : null;
        if (zlVar == null || zlVar.h == null) {
            return;
        }
        zlVar.h = zn1.e;
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.h + ", " + d70.h(this.i) + ']';
    }

    @Override // defpackage.va0
    public final b00<T> d() {
        return this;
    }
}
