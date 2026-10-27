package X8;

import S8.AbstractC0721s;
import S8.AbstractC0727y;
import S8.C0720q;
import S8.D;
import S8.N;
import S8.n0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import s7.AbstractC2410c;
import s7.InterfaceC2411d;

/* JADX INFO: loaded from: classes.dex */
public final class f extends D implements InterfaceC2411d, InterfaceC2189c {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f10554s = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "_reusableCancellableContinuation$volatile");
    private volatile /* synthetic */ Object _reusableCancellableContinuation$volatile;
    public final AbstractC0721s o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AbstractC2410c f10555p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Object f10556q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Object f10557r;

    public f(AbstractC0721s abstractC0721s, AbstractC2410c abstractC2410c) {
        super(-1);
        this.o = abstractC0721s;
        this.f10555p = abstractC2410c;
        this.f10556q = a.f10548b;
        this.f10557r = a.m(abstractC2410c.getContext());
    }

    @Override // S8.D
    public final InterfaceC2189c c() {
        return this;
    }

    @Override // S8.D
    public final Object g() {
        Object obj = this.f10556q;
        this.f10556q = a.f10548b;
        return obj;
    }

    @Override // s7.InterfaceC2411d
    public final InterfaceC2411d getCallerFrame() {
        return this.f10555p;
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return this.f10555p.getContext();
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        Throwable thA = m7.l.a(obj);
        Object c0720q = thA == null ? obj : new C0720q(thA, false);
        AbstractC2410c abstractC2410c = this.f10555p;
        InterfaceC2194h context = abstractC2410c.getContext();
        AbstractC0721s abstractC0721s = this.o;
        if (abstractC0721s.N(context)) {
            this.f10556q = c0720q;
            this.f8083n = 0;
            abstractC0721s.L(abstractC2410c.getContext(), this);
            return;
        }
        N nA = n0.a();
        if (nA.T()) {
            this.f10556q = c0720q;
            this.f8083n = 0;
            nA.Q(this);
            return;
        }
        nA.S(true);
        try {
            InterfaceC2194h context2 = abstractC2410c.getContext();
            Object objN = a.n(context2, this.f10557r);
            try {
                abstractC2410c.resumeWith(obj);
                while (nA.V()) {
                }
            } finally {
                a.i(context2, objN);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.o + ", " + AbstractC0727y.C(this.f10555p) + ']';
    }
}
