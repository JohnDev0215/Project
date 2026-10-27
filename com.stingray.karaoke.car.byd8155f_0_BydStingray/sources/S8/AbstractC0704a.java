package S8;

import h8.AbstractC1386c;
import java.lang.reflect.InvocationTargetException;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.AbstractC2408a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: S8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0704a extends d0 implements InterfaceC2189c, InterfaceC0725w {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC2194h f8101n;

    public AbstractC0704a(InterfaceC2194h interfaceC2194h, boolean z6) {
        super(z6);
        Q((W) interfaceC2194h.F(C0722t.f8137m));
        this.f8101n = interfaceC2194h.v(this);
    }

    @Override // S8.d0
    public final String B() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    @Override // S8.d0
    public final void P(A9.k kVar) throws IllegalAccessException, InvocationTargetException {
        AbstractC0727y.q(kVar, this.f8101n);
    }

    @Override // S8.d0
    public final void Y(Object obj) {
        if (!(obj instanceof C0720q)) {
            h0(obj);
        } else {
            C0720q c0720q = (C0720q) obj;
            g0(c0720q.f8130a, C0720q.f8129b.get(c0720q) != 0);
        }
    }

    public void g0(Throwable th, boolean z6) {
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return this.f8101n;
    }

    @Override // S8.InterfaceC0725w
    public final InterfaceC2194h getCoroutineContext() {
        return this.f8101n;
    }

    public void h0(Object obj) {
    }

    public final void i0(EnumC0726x enumC0726x, AbstractC0704a abstractC0704a, InterfaceC2785n interfaceC2785n) {
        Object objInvoke;
        int iOrdinal = enumC0726x.ordinal();
        m7.q qVar = m7.q.f19267a;
        if (iOrdinal == 0) {
            try {
                X8.a.j(qVar, h6.e.k(h6.e.g(abstractC0704a, this, interfaceC2785n)));
                return;
            } finally {
                resumeWith(AbstractC1386c.f(th));
            }
        }
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                A7.m.f(interfaceC2785n, "<this>");
                h6.e.k(h6.e.g(abstractC0704a, this, interfaceC2785n)).resumeWith(qVar);
                return;
            }
            if (iOrdinal != 3) {
                throw new A9.k();
            }
            try {
                InterfaceC2194h interfaceC2194h = this.f8101n;
                Object objN = X8.a.n(interfaceC2194h, null);
                try {
                    if (interfaceC2785n instanceof AbstractC2408a) {
                        A7.E.e(2, interfaceC2785n);
                        objInvoke = interfaceC2785n.invoke(abstractC0704a, this);
                    } else {
                        objInvoke = h6.e.t(interfaceC2785n, abstractC0704a, this);
                    }
                    X8.a.i(interfaceC2194h, objN);
                    if (objInvoke != EnumC2303a.l) {
                        resumeWith(objInvoke);
                    }
                } catch (Throwable th) {
                    X8.a.i(interfaceC2194h, objN);
                    throw th;
                }
            } catch (Throwable th2) {
            }
        }
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        Throwable thA = m7.l.a(obj);
        if (thA != null) {
            obj = new C0720q(thA, false);
        }
        Object objU = U(obj);
        if (objU == AbstractC0727y.f8146e) {
            return;
        }
        t(objU);
    }
}
