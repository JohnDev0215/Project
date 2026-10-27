package defpackage;

import defpackage.m00;
import defpackage.m31;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.internal.a;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public class r31 implements m31, jn, uv1 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(r31.class, Object.class, "_state");
    private volatile /* synthetic */ Object _parentHandle;
    private volatile /* synthetic */ Object _state;

    /* JADX INFO: compiled from: JobSupport.kt */
    public static final class a extends q31 {
        public final r31 i;
        public final b j;
        public final in k;
        public final Object l;

        public a(r31 r31Var, b bVar, in inVar, Object obj) {
            this.i = r31Var;
            this.j = bVar;
            this.k = inVar;
            this.l = obj;
        }

        @Override // defpackage.jr0
        public final /* bridge */ /* synthetic */ vi3 c(Throwable th) {
            l(th);
            return vi3.a;
        }

        @Override // defpackage.tv
        public final void l(Throwable th) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = r31.e;
            r31 r31Var = this.i;
            r31Var.getClass();
            in inVarW = r31.w(this.k);
            b bVar = this.j;
            Object obj = this.l;
            if (inVarW == null || !r31Var.E(bVar, inVarW, obj)) {
                r31Var.d(r31Var.j(bVar, obj));
            }
        }
    }

    /* JADX INFO: compiled from: JobSupport.kt */
    public static final class b implements lz0 {
        private volatile /* synthetic */ Object _rootCause;
        public final yn1 e;
        private volatile /* synthetic */ int _isCompleting = 0;
        private volatile /* synthetic */ Object _exceptionsHolder = null;

        public b(yn1 yn1Var, Throwable th) {
            this.e = yn1Var;
            this._rootCause = th;
        }

        public final void a(Throwable th) {
            Throwable th2 = (Throwable) this._rootCause;
            if (th2 == null) {
                this._rootCause = th;
                return;
            }
            if (th == th2) {
                return;
            }
            Object obj = this._exceptionsHolder;
            if (obj == null) {
                this._exceptionsHolder = th;
                return;
            }
            if (!(obj instanceof Throwable)) {
                if (!(obj instanceof ArrayList)) {
                    throw new IllegalStateException(i21.g(obj, "State is ").toString());
                }
                ((ArrayList) obj).add(th);
            } else {
                if (th == obj) {
                    return;
                }
                ArrayList arrayList = new ArrayList(4);
                arrayList.add(obj);
                arrayList.add(th);
                this._exceptionsHolder = arrayList;
            }
        }

        @Override // defpackage.lz0
        public final yn1 b() {
            return this.e;
        }

        public final Throwable c() {
            return (Throwable) this._rootCause;
        }

        public final boolean d() {
            return ((Throwable) this._rootCause) != null;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
        public final boolean e() {
            return this._isCompleting;
        }

        public final boolean f() {
            return this._exceptionsHolder == n3.q;
        }

        public final ArrayList g(Throwable th) {
            ArrayList arrayList;
            Object obj = this._exceptionsHolder;
            if (obj == null) {
                arrayList = new ArrayList(4);
            } else if (obj instanceof Throwable) {
                ArrayList arrayList2 = new ArrayList(4);
                arrayList2.add(obj);
                arrayList = arrayList2;
            } else {
                if (!(obj instanceof ArrayList)) {
                    throw new IllegalStateException(i21.g(obj, "State is ").toString());
                }
                arrayList = (ArrayList) obj;
            }
            Throwable th2 = (Throwable) this._rootCause;
            if (th2 != null) {
                arrayList.add(0, th2);
            }
            if (th != null && !i21.a(th, th2)) {
                arrayList.add(th);
            }
            this._exceptionsHolder = n3.q;
            return arrayList;
        }

        public final void h() {
            this._isCompleting = 1;
        }

        @Override // defpackage.lz0
        public final boolean isActive() {
            return ((Throwable) this._rootCause) == null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v3, types: [boolean, int] */
        public final String toString() {
            return "Finishing[cancelling=" + d() + ", completing=" + ((boolean) this._isCompleting) + ", rootCause=" + ((Throwable) this._rootCause) + ", exceptions=" + this._exceptionsHolder + ", list=" + this.e + ']';
        }
    }

    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static final class c extends a.AbstractC0060a {
        public final /* synthetic */ r31 d;
        public final /* synthetic */ Object e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(kotlinx.coroutines.internal.a aVar, r31 r31Var, Object obj) {
            super(aVar);
            this.d = r31Var;
            this.e = obj;
        }

        @Override // defpackage.k9
        public final a41 c(Object obj) {
            if (this.d.n() == this.e) {
                return null;
            }
            return z7.m;
        }
    }

    public r31(boolean z) {
        this._state = z ? n3.s : n3.r;
        this._parentHandle = null;
    }

    public static String B(Object obj) {
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (bVar.d()) {
                return "Cancelling";
            }
            if (bVar.e()) {
                return "Completing";
            }
        } else {
            if (!(obj instanceof lz0)) {
                return obj instanceof rv ? "Cancelled" : "Completed";
            }
            if (!((lz0) obj).isActive()) {
                return "New";
            }
        }
        return "Active";
    }

    public static in w(kotlinx.coroutines.internal.a aVar) {
        while (aVar.j()) {
            aVar = aVar.i();
        }
        while (true) {
            aVar = aVar.h();
            if (!aVar.j()) {
                if (aVar instanceof in) {
                    return (in) aVar;
                }
                if (aVar instanceof yn1) {
                    return null;
                }
            }
        }
    }

    public final void A(q31 q31Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        boolean z;
        yn1 yn1Var = new yn1();
        q31Var.getClass();
        kotlinx.coroutines.internal.a.f.lazySet(yn1Var, q31Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = kotlinx.coroutines.internal.a.e;
        atomicReferenceFieldUpdater2.lazySet(yn1Var, q31Var);
        while (true) {
            if (q31Var.g() != q31Var) {
                break;
            }
            while (true) {
                if (atomicReferenceFieldUpdater2.compareAndSet(q31Var, q31Var, yn1Var)) {
                    z = true;
                    break;
                } else if (atomicReferenceFieldUpdater2.get(q31Var) != q31Var) {
                    z = false;
                    break;
                }
            }
            if (z) {
                yn1Var.e(q31Var);
                break;
            }
        }
        kotlinx.coroutines.internal.a aVarH = q31Var.h();
        do {
            atomicReferenceFieldUpdater = e;
            if (atomicReferenceFieldUpdater.compareAndSet(this, q31Var, aVarH)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == q31Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00ae A[SYNTHETIC] */
    @Override // defpackage.m31
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.gb0 C(boolean r8, boolean r9, defpackage.q31 r10) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r31.C(boolean, boolean, q31):gb0");
    }

    public final Object D(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        boolean z;
        if (!(obj instanceof lz0)) {
            return n3.m;
        }
        boolean z2 = true;
        boolean z3 = false;
        if (((obj instanceof tg0) || (obj instanceof q31)) && !(obj instanceof in) && !(obj2 instanceof rv)) {
            lz0 lz0Var = (lz0) obj;
            Object mz0Var = obj2 instanceof lz0 ? new mz0((lz0) obj2) : obj2;
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
                if (atomicReferenceFieldUpdater.compareAndSet(this, lz0Var, mz0Var)) {
                    z = true;
                    break;
                }
                if (atomicReferenceFieldUpdater.get(this) != lz0Var) {
                    z = false;
                    break;
                }
            }
            if (z) {
                y(obj2);
                h(lz0Var, obj2);
            } else {
                z2 = false;
            }
            return z2 ? obj2 : n3.o;
        }
        lz0 lz0Var2 = (lz0) obj;
        yn1 yn1VarL = l(lz0Var2);
        if (yn1VarL == null) {
            return n3.o;
        }
        in inVarW = null;
        b bVar = lz0Var2 instanceof b ? (b) lz0Var2 : null;
        if (bVar == null) {
            bVar = new b(yn1VarL, null);
        }
        synchronized (bVar) {
            if (bVar.e()) {
                return n3.m;
            }
            bVar.h();
            if (bVar != lz0Var2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = e;
                while (true) {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, lz0Var2, bVar)) {
                        z3 = true;
                        break;
                    }
                    if (atomicReferenceFieldUpdater2.get(this) != lz0Var2) {
                        break;
                    }
                }
                if (!z3) {
                    return n3.o;
                }
            }
            boolean zD = bVar.d();
            rv rvVar = obj2 instanceof rv ? (rv) obj2 : null;
            if (rvVar != null) {
                bVar.a(rvVar.a);
            }
            Throwable thC = bVar.c();
            if (!(true ^ zD)) {
                thC = null;
            }
            vi3 vi3Var = vi3.a;
            if (thC != null) {
                x(yn1VarL, thC);
            }
            in inVar = lz0Var2 instanceof in ? (in) lz0Var2 : null;
            if (inVar == null) {
                yn1 yn1VarB = lz0Var2.b();
                if (yn1VarB != null) {
                    inVarW = w(yn1VarB);
                }
            } else {
                inVarW = inVar;
            }
            return (inVarW == null || !E(bVar, inVarW, obj2)) ? j(bVar, obj2) : n3.n;
        }
    }

    public final boolean E(b bVar, in inVar, Object obj) {
        while (inVar.i.C(false, false, new a(this, bVar, inVar, obj)) == zn1.e) {
            inVar = w(inVar);
            if (inVar == null) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.jn
    public final void F(r31 r31Var) throws IllegalAccessException, InvocationTargetException {
        e(r31Var);
    }

    @Override // defpackage.uv1
    public final CancellationException I0() {
        Throwable thC;
        Object objN = n();
        if (objN instanceof b) {
            thC = ((b) objN).c();
        } else if (objN instanceof rv) {
            thC = ((rv) objN).a;
        } else {
            if (objN instanceof lz0) {
                throw new IllegalStateException(i21.g(objN, "Cannot be cancelling child in this state: ").toString());
            }
            thC = null;
        }
        CancellationException cancellationException = thC instanceof CancellationException ? (CancellationException) thC : null;
        return cancellationException == null ? new n31(i21.g(B(objN), "Parent job is "), thC, this) : cancellationException;
    }

    @Override // defpackage.m31
    public final void Q0(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        if (cancellationException == null) {
            cancellationException = new n31(g(), null, this);
        }
        e(cancellationException);
    }

    @Override // defpackage.m31
    public final CancellationException R() {
        Object objN = n();
        if (!(objN instanceof b)) {
            if (objN instanceof lz0) {
                throw new IllegalStateException(i21.g(this, "Job is still new or active: ").toString());
            }
            if (!(objN instanceof rv)) {
                return new n31(i21.g(" has completed normally", getClass().getSimpleName()), null, this);
            }
            Throwable th = ((rv) objN).a;
            n31Var = th instanceof CancellationException ? (CancellationException) th : null;
            return n31Var == null ? new n31(g(), th, this) : n31Var;
        }
        Throwable thC = ((b) objN).c();
        if (thC != null) {
            String strG = i21.g(" is cancelling", getClass().getSimpleName());
            n31Var = thC instanceof CancellationException ? (CancellationException) thC : null;
            if (n31Var == null) {
                if (strG == null) {
                    strG = g();
                }
                n31Var = new n31(strG, thC, this);
            }
        }
        if (n31Var != null) {
            return n31Var;
        }
        throw new IllegalStateException(i21.g(this, "Job is still new or active: ").toString());
    }

    public final boolean b(Object obj, yn1 yn1Var, q31 q31Var) {
        boolean z;
        char c2;
        c cVar = new c(q31Var, this, obj);
        do {
            kotlinx.coroutines.internal.a aVarI = yn1Var.i();
            kotlinx.coroutines.internal.a.f.lazySet(q31Var, aVarI);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = kotlinx.coroutines.internal.a.e;
            atomicReferenceFieldUpdater.lazySet(q31Var, yn1Var);
            cVar.c = yn1Var;
            while (true) {
                if (atomicReferenceFieldUpdater.compareAndSet(aVarI, yn1Var, cVar)) {
                    z = true;
                    break;
                }
                if (atomicReferenceFieldUpdater.get(aVarI) != yn1Var) {
                    z = false;
                    break;
                }
            }
            c2 = !z ? (char) 0 : cVar.a(aVarI) == null ? (char) 1 : (char) 2;
            if (c2 == 1) {
                return true;
            }
        } while (c2 != 2);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x003d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00c3 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e(java.lang.Object r10) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r31.e(java.lang.Object):boolean");
    }

    public final boolean f(Throwable th) {
        if (s()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        hn hnVar = (hn) this._parentHandle;
        return (hnVar == null || hnVar == zn1.e) ? z : hnVar.a(th) || z;
    }

    @Override // defpackage.m00
    public final <R> R fold(R r, nr0<? super R, ? super m00.b, ? extends R> nr0Var) {
        return nr0Var.d(r, this);
    }

    public String g() {
        return "Job was cancelled";
    }

    @Override // m00.b, defpackage.m00
    public final <E extends m00.b> E get(m00.c<E> cVar) {
        return (E) m00.b.a.a(this, cVar);
    }

    @Override // m00.b
    public final m00.c<?> getKey() {
        return m31.a.e;
    }

    public final void h(lz0 lz0Var, Object obj) throws IllegalAccessException, InvocationTargetException {
        uv uvVar;
        hn hnVar = (hn) this._parentHandle;
        if (hnVar != null) {
            hnVar.f();
            this._parentHandle = zn1.e;
        }
        rv rvVar = obj instanceof rv ? (rv) obj : null;
        Throwable th = rvVar == null ? null : rvVar.a;
        if (lz0Var instanceof q31) {
            try {
                ((q31) lz0Var).l(th);
                return;
            } catch (Throwable th2) {
                p(new uv("Exception in completion handler " + lz0Var + " for " + this, th2));
                return;
            }
        }
        yn1 yn1VarB = lz0Var.b();
        if (yn1VarB == null) {
            return;
        }
        uv uvVar2 = null;
        for (kotlinx.coroutines.internal.a aVarH = (kotlinx.coroutines.internal.a) yn1VarB.g(); !i21.a(aVarH, yn1VarB); aVarH = aVarH.h()) {
            if (aVarH instanceof q31) {
                q31 q31Var = (q31) aVarH;
                try {
                    q31Var.l(th);
                } catch (Throwable th3) {
                    if (uvVar2 == null) {
                        uvVar = null;
                    } else {
                        n3.l(uvVar2, th3);
                        uvVar = uvVar2;
                    }
                    if (uvVar == null) {
                        uvVar2 = new uv("Exception in completion handler " + q31Var + " for " + this, th3);
                    }
                }
            }
        }
        if (uvVar2 == null) {
            return;
        }
        p(uvVar2);
    }

    public final Throwable i(Object obj) {
        if (obj == null ? true : obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            return th == null ? new n31(g(), null, this) : th;
        }
        if (obj != null) {
            return ((uv1) obj).I0();
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
    }

    @Override // defpackage.m31
    public boolean isActive() {
        Object objN = n();
        return (objN instanceof lz0) && ((lz0) objN).isActive();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object j(b bVar, Object obj) throws IllegalAccessException, InvocationTargetException {
        Object obj2 = null;
        Throwable n31Var = null;
        rv rvVar = obj instanceof rv ? (rv) obj : null;
        Throwable th = rvVar == null ? null : rvVar.a;
        synchronized (bVar) {
            bVar.d();
            ArrayList<Throwable> arrayListG = bVar.g(th);
            if (!arrayListG.isEmpty()) {
                Iterator it = arrayListG.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (!(((Throwable) next) instanceof CancellationException)) {
                        obj2 = next;
                        break;
                    }
                }
                n31Var = (Throwable) obj2;
                if (n31Var == null) {
                    n31Var = (Throwable) arrayListG.get(0);
                }
            } else if (bVar.d()) {
                n31Var = new n31(g(), null, this);
            }
            if (n31Var != null && arrayListG.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListG.size()));
                for (Throwable th2 : arrayListG) {
                    if (th2 != n31Var && th2 != n31Var && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        n3.l(n31Var, th2);
                    }
                }
            }
        }
        if (n31Var != null && n31Var != th) {
            obj = new rv(n31Var);
        }
        if (n31Var != null) {
            if ((f(n31Var) || o(n31Var)) != false) {
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                }
                rv.b.compareAndSet((rv) obj, 0, 1);
            }
        }
        y(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
        Object mz0Var = obj instanceof lz0 ? new mz0((lz0) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, bVar, mz0Var) && atomicReferenceFieldUpdater.get(this) == bVar) {
        }
        h(bVar, obj);
        return obj;
    }

    public boolean k() {
        return true;
    }

    public final yn1 l(lz0 lz0Var) {
        yn1 yn1VarB = lz0Var.b();
        if (yn1VarB != null) {
            return yn1VarB;
        }
        if (lz0Var instanceof tg0) {
            return new yn1();
        }
        if (!(lz0Var instanceof q31)) {
            throw new IllegalStateException(i21.g(lz0Var, "State should have list: ").toString());
        }
        A((q31) lz0Var);
        return null;
    }

    public final hn m() {
        return (hn) this._parentHandle;
    }

    @Override // defpackage.m00
    public final m00 minusKey(m00.c<?> cVar) {
        return m00.b.a.b(this, cVar);
    }

    public final Object n() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof ts1)) {
                return obj;
            }
            ((ts1) obj).a(this);
        }
    }

    public boolean o(Throwable th) {
        return false;
    }

    @Override // defpackage.m00
    public final m00 plus(m00 m00Var) {
        i21.e(m00Var, "context");
        return m00.a.a(this, m00Var);
    }

    public final void r(m31 m31Var) {
        zn1 zn1Var = zn1.e;
        if (m31Var == null) {
            this._parentHandle = zn1Var;
            return;
        }
        m31Var.start();
        hn hnVarT = m31Var.t(this);
        this._parentHandle = hnVarT;
        if (!(n() instanceof lz0)) {
            hnVarT.f();
            this._parentHandle = zn1Var;
        }
    }

    public boolean s() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x004d  */
    @Override // defpackage.m31
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean start() {
        /*
            r7 = this;
        L0:
            java.lang.Object r0 = r7.n()
            boolean r1 = r0 instanceof defpackage.tg0
            r2 = 0
            r3 = 1
            r4 = -1
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = defpackage.r31.e
            if (r1 == 0) goto L2d
            r1 = r0
            tg0 r1 = (defpackage.tg0) r1
            boolean r1 = r1.e
            if (r1 == 0) goto L15
            goto L4d
        L15:
            tg0 r1 = defpackage.n3.s
        L17:
            boolean r6 = r5.compareAndSet(r7, r0, r1)
            if (r6 == 0) goto L1f
            r0 = r3
            goto L26
        L1f:
            java.lang.Object r6 = r5.get(r7)
            if (r6 == r0) goto L17
            r0 = r2
        L26:
            if (r0 != 0) goto L29
            goto L4e
        L29:
            r7.z()
            goto L4b
        L2d:
            boolean r1 = r0 instanceof defpackage.vx0
            if (r1 == 0) goto L4d
            r1 = r0
            vx0 r1 = (defpackage.vx0) r1
            yn1 r1 = r1.e
        L36:
            boolean r6 = r5.compareAndSet(r7, r0, r1)
            if (r6 == 0) goto L3e
            r0 = r3
            goto L45
        L3e:
            java.lang.Object r6 = r5.get(r7)
            if (r6 == r0) goto L36
            r0 = r2
        L45:
            if (r0 != 0) goto L48
            goto L4e
        L48:
            r7.z()
        L4b:
            r4 = r3
            goto L4e
        L4d:
            r4 = r2
        L4e:
            if (r4 == 0) goto L54
            if (r4 == r3) goto L53
            goto L0
        L53:
            return r3
        L54:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r31.start():boolean");
    }

    @Override // defpackage.m31
    public final hn t(r31 r31Var) {
        return (hn) C(true, true, new in(r31Var));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(v() + '{' + B(n()) + '}');
        sb.append('@');
        sb.append(d70.d(this));
        return sb.toString();
    }

    public final Object u(Object obj) {
        Object objD;
        do {
            objD = D(n(), obj);
            if (objD == n3.m) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                rv rvVar = obj instanceof rv ? (rv) obj : null;
                throw new IllegalStateException(str, rvVar != null ? rvVar.a : null);
            }
        } while (objD == n3.o);
        return objD;
    }

    public String v() {
        return getClass().getSimpleName();
    }

    public final void x(yn1 yn1Var, Throwable th) throws IllegalAccessException, InvocationTargetException {
        uv uvVar;
        uv uvVar2 = null;
        for (kotlinx.coroutines.internal.a aVarH = (kotlinx.coroutines.internal.a) yn1Var.g(); !i21.a(aVarH, yn1Var); aVarH = aVarH.h()) {
            if (aVarH instanceof o31) {
                q31 q31Var = (q31) aVarH;
                try {
                    q31Var.l(th);
                } catch (Throwable th2) {
                    if (uvVar2 == null) {
                        uvVar = null;
                    } else {
                        n3.l(uvVar2, th2);
                        uvVar = uvVar2;
                    }
                    if (uvVar == null) {
                        uvVar2 = new uv("Exception in completion handler " + q31Var + " for " + this, th2);
                    }
                }
            }
        }
        if (uvVar2 != null) {
            p(uvVar2);
        }
        f(th);
    }

    public void z() {
    }

    public void d(Object obj) {
    }

    public void p(uv uvVar) {
        throw uvVar;
    }

    public void y(Object obj) {
    }
}
