package S8;

import F0.C0223q;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.InterfaceC2192f;
import q7.InterfaceC2193g;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public class d0 implements W, j0 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater l = AtomicReferenceFieldUpdater.newUpdater(d0.class, Object.class, "_state$volatile");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f8111m = AtomicReferenceFieldUpdater.newUpdater(d0.class, Object.class, "_parentHandle$volatile");
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    public d0(boolean z6) {
        this._state$volatile = z6 ? AbstractC0727y.f8151j : AbstractC0727y.f8150i;
    }

    public static C0716m W(X8.i iVar) {
        while (iVar.i()) {
            X8.i iVarF = iVar.f();
            if (iVarF == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = X8.i.f10564m;
                Object obj = atomicReferenceFieldUpdater.get(iVar);
                while (true) {
                    iVar = (X8.i) obj;
                    if (!iVar.i()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(iVar);
                }
            } else {
                iVar = iVarF;
            }
        }
        while (true) {
            iVar = iVar.h();
            if (!iVar.i()) {
                if (iVar instanceof C0716m) {
                    return (C0716m) iVar;
                }
                if (iVar instanceof f0) {
                    return null;
                }
            }
        }
    }

    public static String d0(Object obj) {
        if (!(obj instanceof c0)) {
            return obj instanceof S ? ((S) obj).b() ? "Active" : "New" : obj instanceof C0720q ? "Cancelled" : "Completed";
        }
        c0 c0Var = (c0) obj;
        return c0Var.e() ? "Cancelling" : c0.f8108m.get(c0Var) != 0 ? "Completing" : "Active";
    }

    public final boolean A(Throwable th) {
        if (S()) {
            return true;
        }
        boolean z6 = th instanceof CancellationException;
        InterfaceC0715l interfaceC0715l = (InterfaceC0715l) f8111m.get(this);
        return (interfaceC0715l == null || interfaceC0715l == h0.l) ? z6 : interfaceC0715l.c(th) || z6;
    }

    public String B() {
        return "Job was cancelled";
    }

    public boolean D(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return w(th) && L();
    }

    public final void E(S s6, Object obj) throws IllegalAccessException, InvocationTargetException {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8111m;
        InterfaceC0715l interfaceC0715l = (InterfaceC0715l) atomicReferenceFieldUpdater.get(this);
        if (interfaceC0715l != null) {
            interfaceC0715l.a();
            atomicReferenceFieldUpdater.set(this, h0.l);
        }
        A9.k kVar = null;
        C0720q c0720q = obj instanceof C0720q ? (C0720q) obj : null;
        Throwable th = c0720q != null ? c0720q.f8130a : null;
        if (s6 instanceof Z) {
            try {
                ((Z) s6).l(th);
                return;
            } catch (Throwable th2) {
                P(new A9.k("Exception in completion handler " + s6 + " for " + this, th2));
                return;
            }
        }
        f0 f0VarD = s6.d();
        if (f0VarD != null) {
            f0VarD.e(new X8.h(1), 1);
            Object obj2 = X8.i.l.get(f0VarD);
            A7.m.d(obj2, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
            for (X8.i iVarH = (X8.i) obj2; !iVarH.equals(f0VarD); iVarH = iVarH.h()) {
                if (iVarH instanceof Z) {
                    try {
                        ((Z) iVarH).l(th);
                    } catch (Throwable th3) {
                        if (kVar != null) {
                            h6.q.l(kVar, th3);
                        } else {
                            kVar = new A9.k("Exception in completion handler " + iVarH + " for " + this, th3);
                        }
                    }
                }
            }
            if (kVar != null) {
                P(kVar);
            }
        }
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2192f F(InterfaceC2193g interfaceC2193g) {
        return h6.q.r(this, interfaceC2193g);
    }

    public final Throwable H(Object obj) {
        Throwable thC;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        d0 d0Var = (d0) ((j0) obj);
        Object obj2 = l.get(d0Var);
        if (obj2 instanceof c0) {
            thC = ((c0) obj2).c();
        } else if (obj2 instanceof C0720q) {
            thC = ((C0720q) obj2).f8130a;
        } else {
            if (obj2 instanceof S) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + obj2).toString());
            }
            thC = null;
        }
        CancellationException x8 = thC instanceof CancellationException ? (CancellationException) thC : null;
        if (x8 == null) {
            x8 = new X("Parent job is ".concat(d0(obj2)), thC, d0Var);
        }
        return x8;
    }

    public final Object I(c0 c0Var, Object obj) throws IllegalAccessException, InvocationTargetException {
        Throwable thJ;
        C0720q c0720q = obj instanceof C0720q ? (C0720q) obj : null;
        Throwable th = c0720q != null ? c0720q.f8130a : null;
        synchronized (c0Var) {
            c0Var.e();
            ArrayList<Throwable> arrayListF = c0Var.f(th);
            thJ = J(c0Var, arrayListF);
            if (thJ != null && arrayListF.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListF.size()));
                for (Throwable th2 : arrayListF) {
                    if (th2 != thJ && th2 != thJ && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        h6.q.l(thJ, th2);
                    }
                }
            }
        }
        if (thJ != null && thJ != th) {
            obj = new C0720q(thJ, false);
        }
        if (thJ != null && (A(thJ) || O(thJ))) {
            A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            C0720q.f8129b.compareAndSet((C0720q) obj, 0, 1);
        }
        Y(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = l;
        Object t10 = obj instanceof S ? new T((S) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, c0Var, t10) && atomicReferenceFieldUpdater.get(this) == c0Var) {
        }
        E(c0Var, obj);
        return obj;
    }

    public final Throwable J(c0 c0Var, ArrayList arrayList) {
        Object next;
        Object obj = null;
        if (arrayList.isEmpty()) {
            if (c0Var.e()) {
                return new X(B(), null, this);
            }
            return null;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (!(((Throwable) next) instanceof CancellationException)) {
                break;
            }
        }
        Throwable th = (Throwable) next;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) arrayList.get(0);
        if (th2 instanceof p0) {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next2 = it2.next();
                Throwable th3 = (Throwable) next2;
                if (th3 != th2 && (th3 instanceof p0)) {
                    obj = next2;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    @Override // S8.W
    public final Object K(AbstractC2410c abstractC2410c) {
        Object obj;
        m7.q qVar;
        do {
            obj = l.get(this);
            boolean z6 = obj instanceof S;
            qVar = m7.q.f19267a;
            if (!z6) {
                AbstractC0727y.k(abstractC2410c.getContext());
                return qVar;
            }
        } while (c0(obj) < 0);
        C0712i c0712i = new C0712i(1, h6.e.k(abstractC2410c));
        c0712i.s();
        c0712i.v(new C0708e(AbstractC0727y.r(this, true, new C0714k(c0712i, 1)), 1));
        Object objQ = c0712i.q();
        EnumC2303a enumC2303a = EnumC2303a.l;
        if (objQ != enumC2303a) {
            objQ = qVar;
        }
        return objQ == enumC2303a ? objQ : qVar;
    }

    public boolean L() {
        return true;
    }

    public boolean M() {
        return this instanceof C0718o;
    }

    public final f0 N(S s6) {
        f0 f0VarD = s6.d();
        if (f0VarD != null) {
            return f0VarD;
        }
        if (s6 instanceof H) {
            return new f0();
        }
        if (s6 instanceof Z) {
            b0((Z) s6);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + s6).toString());
    }

    public boolean O(Throwable th) {
        return false;
    }

    public void P(A9.k kVar) {
        throw kVar;
    }

    public final void Q(W w10) {
        h0 h0Var = h0.l;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8111m;
        if (w10 == null) {
            atomicReferenceFieldUpdater.set(this, h0Var);
            return;
        }
        w10.start();
        InterfaceC0715l interfaceC0715lQ = w10.q(this);
        atomicReferenceFieldUpdater.set(this, interfaceC0715lQ);
        if (l.get(this) instanceof S) {
            return;
        }
        interfaceC0715lQ.a();
        atomicReferenceFieldUpdater.set(this, h0Var);
    }

    public final F R(boolean z6, Z z10) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        h0 h0Var;
        boolean z11;
        boolean zE;
        z10.o = this;
        loop0: while (true) {
            atomicReferenceFieldUpdater = l;
            Object obj = atomicReferenceFieldUpdater.get(this);
            boolean z12 = obj instanceof H;
            h0Var = h0.l;
            z11 = true;
            if (!z12) {
                if (!(obj instanceof S)) {
                    z11 = false;
                    break;
                }
                S s6 = (S) obj;
                f0 f0VarD = s6.d();
                if (f0VarD == null) {
                    A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    b0((Z) obj);
                } else {
                    if (z10.k()) {
                        c0 c0Var = s6 instanceof c0 ? (c0) s6 : null;
                        Throwable thC = c0Var != null ? c0Var.c() : null;
                        if (thC != null) {
                            if (z6) {
                                z10.l(thC);
                            }
                            return h0Var;
                        }
                        zE = f0VarD.e(z10, 5);
                    } else {
                        zE = f0VarD.e(z10, 1);
                    }
                    if (zE) {
                        break;
                    }
                }
            } else {
                H h10 = (H) obj;
                if (h10.l) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, z10)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj) {
                            break;
                        }
                    }
                    break loop0;
                }
                a0(h10);
            }
        }
        if (z11) {
            return z10;
        }
        if (z6) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            C0720q c0720q = obj2 instanceof C0720q ? (C0720q) obj2 : null;
            z10.l(c0720q != null ? c0720q.f8130a : null);
        }
        return h0Var;
    }

    public boolean S() {
        return this instanceof C0706c;
    }

    public final boolean T(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objE0;
        do {
            objE0 = e0(l.get(this), obj);
            if (objE0 == AbstractC0727y.d) {
                return false;
            }
            if (objE0 == AbstractC0727y.f8146e) {
                return true;
            }
        } while (objE0 == AbstractC0727y.f8147f);
        s(objE0);
        return true;
    }

    public final Object U(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objE0;
        do {
            objE0 = e0(l.get(this), obj);
            if (objE0 == AbstractC0727y.d) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                C0720q c0720q = obj instanceof C0720q ? (C0720q) obj : null;
                throw new IllegalStateException(str, c0720q != null ? c0720q.f8130a : null);
            }
        } while (objE0 == AbstractC0727y.f8147f);
        return objE0;
    }

    public String V() {
        return getClass().getSimpleName();
    }

    public final void X(f0 f0Var, Throwable th) throws IllegalAccessException, InvocationTargetException {
        f0Var.e(new X8.h(4), 4);
        Object obj = X8.i.l.get(f0Var);
        A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        A9.k kVar = null;
        for (X8.i iVarH = (X8.i) obj; !iVarH.equals(f0Var); iVarH = iVarH.h()) {
            if ((iVarH instanceof Z) && ((Z) iVarH).k()) {
                try {
                    ((Z) iVarH).l(th);
                } catch (Throwable th2) {
                    if (kVar != null) {
                        h6.q.l(kVar, th2);
                    } else {
                        kVar = new A9.k("Exception in completion handler " + iVarH + " for " + this, th2);
                    }
                }
            }
        }
        if (kVar != null) {
            P(kVar);
        }
        A(th);
    }

    public void Y(Object obj) {
    }

    public void Z() {
    }

    public final void a0(H h10) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        f0 f0Var = new f0();
        Object q4 = f0Var;
        if (!h10.l) {
            q4 = new Q(f0Var);
        }
        do {
            atomicReferenceFieldUpdater = l;
            if (atomicReferenceFieldUpdater.compareAndSet(this, h10, q4)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == h10);
    }

    @Override // S8.W
    public boolean b() {
        Object obj = l.get(this);
        return (obj instanceof S) && ((S) obj).b();
    }

    public final void b0(Z z6) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        f0 f0Var = new f0();
        z6.getClass();
        X8.i.f10564m.set(f0Var, z6);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = X8.i.l;
        atomicReferenceFieldUpdater2.set(f0Var, z6);
        loop0: while (true) {
            if (atomicReferenceFieldUpdater2.get(z6) == z6) {
                while (!atomicReferenceFieldUpdater2.compareAndSet(z6, z6, f0Var)) {
                    if (atomicReferenceFieldUpdater2.get(z6) != z6) {
                        break;
                    }
                }
                f0Var.g(z6);
                break loop0;
            }
            break;
        }
        X8.i iVarH = z6.h();
        do {
            atomicReferenceFieldUpdater = l;
            if (atomicReferenceFieldUpdater.compareAndSet(this, z6, iVarH)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == z6);
    }

    public final int c0(Object obj) {
        boolean z6 = obj instanceof H;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = l;
        if (z6) {
            if (((H) obj).l) {
                return 0;
            }
            H h10 = AbstractC0727y.f8151j;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, h10)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    return -1;
                }
            }
            Z();
            return 1;
        }
        if (!(obj instanceof Q)) {
            return 0;
        }
        f0 f0Var = ((Q) obj).l;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, f0Var)) {
            if (atomicReferenceFieldUpdater.get(this) != obj) {
                return -1;
            }
        }
        Z();
        return 1;
    }

    public final Object e0(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        if (!(obj instanceof S)) {
            return AbstractC0727y.d;
        }
        if (((obj instanceof H) || (obj instanceof Z)) && !(obj instanceof C0716m) && !(obj2 instanceof C0720q)) {
            S s6 = (S) obj;
            Object t10 = obj2 instanceof S ? new T((S) obj2) : obj2;
            do {
                atomicReferenceFieldUpdater = l;
                if (atomicReferenceFieldUpdater.compareAndSet(this, s6, t10)) {
                    Y(obj2);
                    E(s6, obj2);
                    return obj2;
                }
            } while (atomicReferenceFieldUpdater.get(this) == s6);
            return AbstractC0727y.f8147f;
        }
        S s10 = (S) obj;
        f0 f0VarN = N(s10);
        if (f0VarN == null) {
            return AbstractC0727y.f8147f;
        }
        c0 c0Var = s10 instanceof c0 ? (c0) s10 : null;
        if (c0Var == null) {
            c0Var = new c0(f0VarN, null);
        }
        synchronized (c0Var) {
            try {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = c0.f8108m;
                if (atomicIntegerFieldUpdater.get(c0Var) != 0) {
                    return AbstractC0727y.d;
                }
                atomicIntegerFieldUpdater.set(c0Var, 1);
                if (c0Var != s10) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = l;
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, s10, c0Var)) {
                        if (atomicReferenceFieldUpdater2.get(this) != s10) {
                            return AbstractC0727y.f8147f;
                        }
                    }
                }
                boolean zE = c0Var.e();
                C0720q c0720q = obj2 instanceof C0720q ? (C0720q) obj2 : null;
                if (c0720q != null) {
                    c0Var.a(c0720q.f8130a);
                }
                Throwable thC = zE ? null : c0Var.c();
                if (thC != null) {
                    X(f0VarN, thC);
                }
                C0716m c0716mW = W(f0VarN);
                if (c0716mW != null && f0(c0Var, c0716mW, obj2)) {
                    return AbstractC0727y.f8146e;
                }
                f0VarN.e(new X8.h(2), 2);
                C0716m c0716mW2 = W(f0VarN);
                return (c0716mW2 == null || !f0(c0Var, c0716mW2, obj2)) ? I(c0Var, obj2) : AbstractC0727y.f8146e;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean f0(c0 c0Var, C0716m c0716m, Object obj) {
        while (AbstractC0727y.r(c0716m.f8121p, false, new b0(this, c0Var, c0716m, obj)) == h0.l) {
            c0716m = W(c0716m);
            if (c0716m == null) {
                return false;
            }
        }
        return true;
    }

    @Override // S8.W
    public void g(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        if (cancellationException == null) {
            cancellationException = new X(B(), null, this);
        }
        x(cancellationException);
    }

    @Override // q7.InterfaceC2192f
    public final InterfaceC2193g getKey() {
        return C0722t.f8137m;
    }

    @Override // q7.InterfaceC2194h
    public final Object j(Object obj, InterfaceC2785n interfaceC2785n) {
        return interfaceC2785n.invoke(obj, this);
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h k(InterfaceC2193g interfaceC2193g) {
        return h6.q.w(this, interfaceC2193g);
    }

    @Override // S8.W
    public final F m(boolean z6, boolean z10, C0223q c0223q) {
        return R(z10, z6 ? new V(c0223q) : new G(c0223q, 1));
    }

    @Override // S8.W
    public final InterfaceC0715l q(d0 d0Var) {
        C0716m c0716m = new C0716m(d0Var);
        c0716m.o = this;
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = l;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof H) {
                H h10 = (H) obj;
                if (h10.l) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, c0716m)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj) {
                            break;
                        }
                    }
                    break loop0;
                }
                a0(h10);
            } else {
                boolean z6 = obj instanceof S;
                h0 h0Var = h0.l;
                if (!z6) {
                    Object obj2 = atomicReferenceFieldUpdater.get(this);
                    C0720q c0720q = obj2 instanceof C0720q ? (C0720q) obj2 : null;
                    c0716m.l(c0720q != null ? c0720q.f8130a : null);
                    return h0Var;
                }
                f0 f0VarD = ((S) obj).d();
                if (f0VarD == null) {
                    A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    b0((Z) obj);
                } else if (!f0VarD.e(c0716m, 7)) {
                    boolean zE = f0VarD.e(c0716m, 3);
                    Object obj3 = atomicReferenceFieldUpdater.get(this);
                    if (obj3 instanceof c0) {
                        thC = ((c0) obj3).c();
                    } else {
                        C0720q c0720q2 = obj3 instanceof C0720q ? (C0720q) obj3 : null;
                        if (c0720q2 != null) {
                            thC = c0720q2.f8130a;
                        }
                    }
                    c0716m.l(thC);
                    if (zE) {
                        break loop0;
                    }
                    return h0Var;
                }
            }
        }
        return c0716m;
    }

    public void s(Object obj) {
    }

    @Override // S8.W
    public final boolean start() {
        int iC0;
        do {
            iC0 = c0(l.get(this));
            if (iC0 == 0) {
                return false;
            }
        } while (iC0 != 1);
        return true;
    }

    public void t(Object obj) {
        s(obj);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V() + '{' + d0(l.get(this)) + '}');
        sb.append('@');
        sb.append(AbstractC0727y.n(this));
        return sb.toString();
    }

    public final Object u(AbstractC2410c abstractC2410c) throws Throwable {
        Object obj;
        int i7 = 1;
        do {
            obj = l.get(this);
            if (!(obj instanceof S)) {
                if (obj instanceof C0720q) {
                    throw ((C0720q) obj).f8130a;
                }
                return AbstractC0727y.D(obj);
            }
        } while (c0(obj) < 0);
        a0 a0Var = new a0(h6.e.k(abstractC2410c), this);
        a0Var.s();
        a0Var.v(new C0708e(AbstractC0727y.r(this, true, new G(a0Var, 2)), i7));
        Object objQ = a0Var.q();
        EnumC2303a enumC2303a = EnumC2303a.l;
        return objQ;
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h v(InterfaceC2194h interfaceC2194h) {
        return h6.q.x(this, interfaceC2194h);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0069, code lost:
    
        r0 = r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean w(java.lang.Object r10) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: S8.d0.w(java.lang.Object):boolean");
    }

    public void x(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        w(cancellationException);
    }

    @Override // S8.W
    public final CancellationException y() {
        CancellationException cancellationException;
        Object obj = l.get(this);
        if (!(obj instanceof c0)) {
            if (obj instanceof S) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(obj instanceof C0720q)) {
                return new X(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            Throwable th = ((C0720q) obj).f8130a;
            cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return cancellationException == null ? new X(B(), th, this) : cancellationException;
        }
        Throwable thC = ((c0) obj).c();
        if (thC == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String strConcat = getClass().getSimpleName().concat(" is cancelling");
        cancellationException = thC instanceof CancellationException ? (CancellationException) thC : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (strConcat == null) {
            strConcat = B();
        }
        return new X(strConcat, thC, this);
    }

    @Override // S8.W
    public final F z(InterfaceC2782k interfaceC2782k) {
        return R(true, new G(interfaceC2782k, 1));
    }
}
