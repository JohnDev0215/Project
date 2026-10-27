package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.Ref$ObjectRef;
import p.bv;
import p.cs1;
import p.d41;
import p.e41;
import p.f41;
import p.f60;
import p.fm;
import p.fs;
import p.fz0;
import p.gs;
import p.hv;
import p.js;
import p.oq1;
import p.p61;
import p.pn1;
import p.q30;
import p.q61;
import p.qo;
import p.rd0;
import p.rn1;
import p.sb;
import p.t61;
import p.tz2;
import p.ux2;
import p.v03;
import p.v61;
import p.w10;
import p.w61;
import p.x10;
import p.x61;
import p.y10;
import p.y61;

/* JADX INFO: loaded from: classes.dex */
public class g implements p61, js, cs1 {
    public static final AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "_state");
    public static final AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "_parentHandle");
    private volatile Object _parentHandle;
    private volatile Object _state;

    public g(boolean z) {
        this._state = z ? y61.g : y61.f;
    }

    public static gs Q(kotlinx.coroutines.internal.a aVar) {
        while (aVar.j()) {
            kotlinx.coroutines.internal.a aVarD = aVar.d();
            if (aVarD == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = kotlinx.coroutines.internal.a.e;
                Object obj = atomicReferenceFieldUpdater.get(aVar);
                while (true) {
                    aVar = (kotlinx.coroutines.internal.a) obj;
                    if (!aVar.j()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(aVar);
                }
            } else {
                aVar = aVarD;
            }
        }
        while (true) {
            aVar = aVar.i();
            if (!aVar.j()) {
                if (aVar instanceof gs) {
                    return (gs) aVar;
                }
                if (aVar instanceof pn1) {
                    return null;
                }
            }
        }
    }

    public static String W(Object obj) {
        if (!(obj instanceof w61)) {
            return obj instanceof e41 ? ((e41) obj).b() ? "Active" : "New" : obj instanceof hv ? "Cancelled" : "Completed";
        }
        w61 w61Var = (w61) obj;
        return w61Var.e() ? "Cancelling" : w61Var.f() ? "Completing" : "Active";
    }

    public final Object A(w61 w61Var, Object obj) {
        Throwable thD;
        hv hvVar = obj instanceof hv ? (hv) obj : null;
        Throwable th = hvVar != null ? hvVar.a : null;
        synchronized (w61Var) {
            w61Var.e();
            ArrayList<Throwable> arrayListG = w61Var.g(th);
            thD = D(w61Var, arrayListG);
            if (thD != null && arrayListG.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListG.size()));
                for (Throwable th2 : arrayListG) {
                    if (th2 != thD && th2 != thD && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        kotlin.a.a(thD, th2);
                    }
                }
            }
        }
        if (thD != null && thD != th) {
            obj = new hv(thD, false);
        }
        if (thD != null && (v(thD) || J(thD))) {
            v03.g(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            hv.b.compareAndSet((hv) obj, 0, 1);
        }
        S(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c;
        Object f41Var = obj instanceof e41 ? new f41((e41) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, w61Var, f41Var) && atomicReferenceFieldUpdater.get(this) == w61Var) {
        }
        y(w61Var, obj);
        return obj;
    }

    public final CancellationException B() {
        CancellationException cancellationException;
        Object objI = I();
        if (!(objI instanceof w61)) {
            if (objI instanceof e41) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(objI instanceof hv)) {
                return new JobCancellationException(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            Throwable th = ((hv) objI).a;
            cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return cancellationException == null ? new JobCancellationException(w(), th, this) : cancellationException;
        }
        Throwable thD = ((w61) objI).d();
        if (thD == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String strConcat = getClass().getSimpleName().concat(" is cancelling");
        cancellationException = thD instanceof CancellationException ? (CancellationException) thD : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (strConcat == null) {
            strConcat = w();
        }
        return new JobCancellationException(strConcat, thD, this);
    }

    public final sb C() {
        return new sb(2, new JobSupport$children$1(null, this));
    }

    public final Throwable D(w61 w61Var, ArrayList arrayList) {
        Object next;
        Object obj = null;
        if (arrayList.isEmpty()) {
            if (w61Var.e()) {
                return new JobCancellationException(w(), null, this);
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
        if (th2 instanceof TimeoutCancellationException) {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next2 = it2.next();
                Throwable th3 = (Throwable) next2;
                if (th3 != th2 && (th3 instanceof TimeoutCancellationException)) {
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

    @Override // p.y10
    public final y10 E(x10 x10Var) {
        return ux2.q(this, x10Var);
    }

    @Override // p.y10
    public final w10 E0(x10 x10Var) {
        return ux2.k(this, x10Var);
    }

    public boolean F() {
        return true;
    }

    public boolean G() {
        return this instanceof bv;
    }

    public final pn1 H(e41 e41Var) {
        pn1 pn1VarC = e41Var.c();
        if (pn1VarC != null) {
            return pn1VarC;
        }
        if (e41Var instanceof rd0) {
            return new pn1();
        }
        if (e41Var instanceof t61) {
            U((t61) e41Var);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + e41Var).toString());
    }

    public final Object I() {
        while (true) {
            Object obj = c.get(this);
            if (!(obj instanceof oq1)) {
                return obj;
            }
            ((oq1) obj).a(this);
        }
    }

    public boolean J(Throwable th) {
        return false;
    }

    public void K(CompletionHandlerException completionHandlerException) {
        throw completionHandlerException;
    }

    public final void L(p61 p61Var) {
        int iV;
        rn1 rn1Var = rn1.c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
        if (p61Var == null) {
            atomicReferenceFieldUpdater.set(this, rn1Var);
            return;
        }
        g gVar = (g) p61Var;
        do {
            iV = gVar.V(gVar.I());
            if (iV == 0) {
                break;
            }
        } while (iV != 1);
        fs fsVar = (fs) q30.m(gVar, true, new gs(this), 2);
        atomicReferenceFieldUpdater.set(this, fsVar);
        if (!(I() instanceof e41)) {
            fsVar.dispose();
            atomicReferenceFieldUpdater.set(this, rn1Var);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00b6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.oa0 M(boolean r8, boolean r9, p.ry0 r10) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.g.M(boolean, boolean, p.ry0):p.oa0");
    }

    public boolean N() {
        return this instanceof fm;
    }

    public final Object O(Object obj) {
        Object objX;
        do {
            objX = X(I(), obj);
            if (objX == y61.a) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                hv hvVar = obj instanceof hv ? (hv) obj : null;
                throw new IllegalStateException(str, hvVar != null ? hvVar.a : null);
            }
        } while (objX == y61.c);
        return objX;
    }

    public String P() {
        return getClass().getSimpleName();
    }

    public final void R(pn1 pn1Var, Throwable th) {
        Object objH = pn1Var.h();
        v03.g(objH, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        CompletionHandlerException completionHandlerException = null;
        for (kotlinx.coroutines.internal.a aVarI = (kotlinx.coroutines.internal.a) objH; !v03.b(aVarI, pn1Var); aVarI = aVarI.i()) {
            if (aVarI instanceof q61) {
                t61 t61Var = (t61) aVarI;
                try {
                    t61Var.l(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        kotlin.a.a(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + t61Var + " for " + this, th2);
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            K(completionHandlerException);
        }
        v(th);
    }

    public void S(Object obj) {
    }

    public void T() {
    }

    public final void U(t61 t61Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        pn1 pn1Var = new pn1();
        t61Var.getClass();
        kotlinx.coroutines.internal.a.e.lazySet(pn1Var, t61Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = kotlinx.coroutines.internal.a.c;
        atomicReferenceFieldUpdater2.lazySet(pn1Var, t61Var);
        loop0: while (true) {
            if (t61Var.h() == t61Var) {
                while (!atomicReferenceFieldUpdater2.compareAndSet(t61Var, t61Var, pn1Var)) {
                    if (atomicReferenceFieldUpdater2.get(t61Var) != t61Var) {
                        break;
                    }
                }
                pn1Var.f(t61Var);
                break loop0;
            }
            break;
        }
        kotlinx.coroutines.internal.a aVarI = t61Var.i();
        do {
            atomicReferenceFieldUpdater = c;
            if (atomicReferenceFieldUpdater.compareAndSet(this, t61Var, aVarI)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == t61Var);
    }

    public final int V(Object obj) {
        boolean z = obj instanceof rd0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c;
        if (z) {
            if (((rd0) obj).c) {
                return 0;
            }
            rd0 rd0Var = y61.g;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, rd0Var)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    return -1;
                }
            }
            T();
            return 1;
        }
        if (!(obj instanceof d41)) {
            return 0;
        }
        pn1 pn1Var = ((d41) obj).c;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, pn1Var)) {
            if (atomicReferenceFieldUpdater.get(this) != obj) {
                return -1;
            }
        }
        T();
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [T, java.lang.Throwable] */
    public final Object X(Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        if (!(obj instanceof e41)) {
            return y61.a;
        }
        if (((obj instanceof rd0) || (obj instanceof t61)) && !(obj instanceof gs) && !(obj2 instanceof hv)) {
            e41 e41Var = (e41) obj;
            tz2 tz2Var = y61.a;
            Object f41Var = obj2 instanceof e41 ? new f41((e41) obj2) : obj2;
            do {
                atomicReferenceFieldUpdater = c;
                if (atomicReferenceFieldUpdater.compareAndSet(this, e41Var, f41Var)) {
                    S(obj2);
                    y(e41Var, obj2);
                    return obj2;
                }
            } while (atomicReferenceFieldUpdater.get(this) == e41Var);
            return y61.c;
        }
        e41 e41Var2 = (e41) obj;
        pn1 pn1VarH = H(e41Var2);
        if (pn1VarH == null) {
            return y61.c;
        }
        gs gsVarQ = null;
        w61 w61Var = e41Var2 instanceof w61 ? (w61) e41Var2 : null;
        if (w61Var == null) {
            w61Var = new w61(pn1VarH, null);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (w61Var) {
            if (w61Var.f()) {
                return y61.a;
            }
            w61.e.set(w61Var, 1);
            if (w61Var != e41Var2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = c;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, e41Var2, w61Var)) {
                    if (atomicReferenceFieldUpdater2.get(this) != e41Var2) {
                        return y61.c;
                    }
                }
            }
            boolean zE = w61Var.e();
            hv hvVar = obj2 instanceof hv ? (hv) obj2 : null;
            if (hvVar != null) {
                w61Var.a(hvVar.a);
            }
            ?? D = Boolean.valueOf(zE ^ true).booleanValue() ? w61Var.d() : 0;
            ref$ObjectRef.element = D;
            if (D != 0) {
                R(pn1VarH, D);
            }
            gs gsVar = e41Var2 instanceof gs ? (gs) e41Var2 : null;
            if (gsVar == null) {
                pn1 pn1VarC = e41Var2.c();
                if (pn1VarC != null) {
                    gsVarQ = Q(pn1VarC);
                }
            } else {
                gsVarQ = gsVar;
            }
            if (gsVarQ != null) {
                while (q30.m(gsVarQ.m, false, new v61(this, w61Var, gsVarQ, obj2), 1) == rn1.c) {
                    gsVarQ = Q(gsVarQ);
                    if (gsVarQ == null) {
                    }
                }
                return y61.b;
            }
            return A(w61Var, obj2);
        }
    }

    @Override // p.p61
    public boolean b() {
        Object objI = I();
        return (objI instanceof e41) && ((e41) objI).b();
    }

    @Override // p.y10
    public final Object c(Object obj, fz0 fz0Var) {
        v03.j(fz0Var, "operation");
        return fz0Var.g(obj, this);
    }

    @Override // p.w10
    public final x10 getKey() {
        return qo.o;
    }

    @Override // p.p61
    public void i(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(w(), null, this);
        }
        u(cancellationException);
    }

    @Override // p.y10
    public final y10 o0(y10 y10Var) {
        v03.j(y10Var, "context");
        return kotlin.coroutines.a.a(this, y10Var);
    }

    public final boolean p(Object obj, pn1 pn1Var, t61 t61Var) {
        char c2;
        x61 x61Var = new x61(t61Var, this, obj);
        do {
            kotlinx.coroutines.internal.a aVarD = pn1Var.d();
            if (aVarD == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = kotlinx.coroutines.internal.a.e;
                Object obj2 = atomicReferenceFieldUpdater.get(pn1Var);
                while (true) {
                    aVarD = (kotlinx.coroutines.internal.a) obj2;
                    if (!aVarD.j()) {
                        break;
                    }
                    obj2 = atomicReferenceFieldUpdater.get(aVarD);
                }
            }
            kotlinx.coroutines.internal.a.e.lazySet(t61Var, aVarD);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = kotlinx.coroutines.internal.a.c;
            atomicReferenceFieldUpdater2.lazySet(t61Var, pn1Var);
            x61Var.c = pn1Var;
            while (true) {
                if (atomicReferenceFieldUpdater2.compareAndSet(aVarD, pn1Var, x61Var)) {
                    c2 = x61Var.a(aVarD) == null ? (char) 1 : (char) 2;
                } else if (atomicReferenceFieldUpdater2.get(aVarD) != pn1Var) {
                    c2 = 0;
                    break;
                }
            }
            if (c2 == 1) {
                return true;
            }
        } while (c2 != 2);
        return false;
    }

    public void r(Object obj) {
    }

    public void s(Object obj) {
        r(obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0058, code lost:
    
        r0 = r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean t(java.lang.Object r10) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.g.t(java.lang.Object):boolean");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(P() + '{' + W(I()) + '}');
        sb.append('@');
        sb.append(f60.s(this));
        return sb.toString();
    }

    public void u(CancellationException cancellationException) {
        t(cancellationException);
    }

    public final boolean v(Throwable th) {
        if (N()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        fs fsVar = (fs) e.get(this);
        return (fsVar == null || fsVar == rn1.c) ? z : fsVar.a(th) || z;
    }

    public String w() {
        return "Job was cancelled";
    }

    public boolean x(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return t(th) && F();
    }

    public final void y(e41 e41Var, Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
        fs fsVar = (fs) atomicReferenceFieldUpdater.get(this);
        if (fsVar != null) {
            fsVar.dispose();
            atomicReferenceFieldUpdater.set(this, rn1.c);
        }
        CompletionHandlerException completionHandlerException = null;
        hv hvVar = obj instanceof hv ? (hv) obj : null;
        Throwable th = hvVar != null ? hvVar.a : null;
        if (e41Var instanceof t61) {
            try {
                ((t61) e41Var).l(th);
                return;
            } catch (Throwable th2) {
                K(new CompletionHandlerException("Exception in completion handler " + e41Var + " for " + this, th2));
                return;
            }
        }
        pn1 pn1VarC = e41Var.c();
        if (pn1VarC != null) {
            Object objH = pn1VarC.h();
            v03.g(objH, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            for (kotlinx.coroutines.internal.a aVarI = (kotlinx.coroutines.internal.a) objH; !v03.b(aVarI, pn1VarC); aVarI = aVarI.i()) {
                if (aVarI instanceof t61) {
                    t61 t61Var = (t61) aVarI;
                    try {
                        t61Var.l(th);
                    } catch (Throwable th3) {
                        if (completionHandlerException != null) {
                            kotlin.a.a(completionHandlerException, th3);
                        } else {
                            completionHandlerException = new CompletionHandlerException("Exception in completion handler " + t61Var + " for " + this, th3);
                        }
                    }
                }
            }
            if (completionHandlerException != null) {
                K(completionHandlerException);
            }
        }
    }

    public final Throwable z(Object obj) {
        Throwable thD;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        g gVar = (g) ((cs1) obj);
        Object objI = gVar.I();
        if (objI instanceof w61) {
            thD = ((w61) objI).d();
        } else if (objI instanceof hv) {
            thD = ((hv) objI).a;
        } else {
            if (objI instanceof e41) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + objI).toString());
            }
            thD = null;
        }
        CancellationException jobCancellationException = thD instanceof CancellationException ? (CancellationException) thD : null;
        if (jobCancellationException == null) {
            jobCancellationException = new JobCancellationException("Parent job is ".concat(W(objI)), thD, gVar);
        }
        return jobCancellationException;
    }
}
