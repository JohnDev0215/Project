package kotlinx.coroutines.internal;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: LockFreeLinkedList.kt */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final AtomicReferenceFieldUpdater f7656e = AtomicReferenceFieldUpdater.newUpdater(o.class, Object.class, "_next");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final AtomicReferenceFieldUpdater f7657g = AtomicReferenceFieldUpdater.newUpdater(o.class, Object.class, "_prev");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7658h = AtomicReferenceFieldUpdater.newUpdater(o.class, Object.class, "_removedRef");
    volatile Object _next = this;
    volatile Object _prev = this;
    private volatile Object _removedRef = null;

    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static abstract class a extends kotlinx.coroutines.internal.b {
        @Override // kotlinx.coroutines.internal.b
        public final void a(kotlinx.coroutines.internal.d<?> dVar, Object obj) {
            o oVarI;
            boolean z6 = obj == null;
            o oVarH = h();
            if (oVarH == null || (oVarI = i()) == null) {
                return;
            }
            if (kotlinx.coroutines.j.a(o.f7656e, oVarH, dVar, z6 ? n(oVarH, oVarI) : oVarI) && z6) {
                f(oVarH, oVarI);
            }
        }

        @Override // kotlinx.coroutines.internal.b
        public final Object c(kotlinx.coroutines.internal.d<?> dVar) {
            while (true) {
                o oVarM = m(dVar);
                if (oVarM == null) {
                    return kotlinx.coroutines.internal.c.f7623b;
                }
                Object obj = oVarM._next;
                if (obj == dVar || dVar.h()) {
                    return null;
                }
                if (obj instanceof w) {
                    w wVar = (w) obj;
                    if (dVar.b(wVar)) {
                        return kotlinx.coroutines.internal.c.f7623b;
                    }
                    wVar.c(oVarM);
                } else {
                    Object objE = e(oVarM);
                    if (objE != null) {
                        return objE;
                    }
                    if (l(oVarM, obj)) {
                        continue;
                    } else {
                        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                        c cVar = new c(oVarM, (o) obj, this);
                        if (kotlinx.coroutines.j.a(o.f7656e, oVarM, obj, cVar)) {
                            try {
                                if (cVar.c(oVarM) != p.f7667a) {
                                    return null;
                                }
                            } catch (Throwable th) {
                                kotlinx.coroutines.j.a(o.f7656e, oVarM, cVar, obj);
                                throw th;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        protected abstract Object e(o oVar);

        protected abstract void f(o oVar, o oVar2);

        public abstract void g(c cVar);

        protected abstract o h();

        protected abstract o i();

        public Object j(c cVar) {
            g(cVar);
            return null;
        }

        public void k(o oVar) {
        }

        protected abstract boolean l(o oVar, Object obj);

        protected abstract o m(w wVar);

        public abstract Object n(o oVar, o oVar2);
    }

    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static abstract class b extends kotlinx.coroutines.internal.d<o> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public o f7659b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final o f7660c;

        public b(o oVar) {
            this.f7660c = oVar;
        }

        @Override // kotlinx.coroutines.internal.d
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void d(o oVar, Object obj) {
            boolean z6 = obj == null;
            o oVar2 = z6 ? this.f7660c : this.f7659b;
            if (oVar2 != null && kotlinx.coroutines.j.a(o.f7656e, oVar, this, oVar2) && z6) {
                o oVar3 = this.f7660c;
                o oVar4 = this.f7659b;
                kotlin.jvm.internal.l.c(oVar4);
                oVar3.F(oVar4);
            }
        }
    }

    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static final class c extends w {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o f7661a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final o f7662b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final a f7663c;

        public c(o oVar, o oVar2, a aVar) {
            this.f7661a = oVar;
            this.f7662b = oVar2;
            this.f7663c = aVar;
        }

        @Override // kotlinx.coroutines.internal.w
        public kotlinx.coroutines.internal.d<?> a() {
            return this.f7663c.b();
        }

        @Override // kotlinx.coroutines.internal.w
        public Object c(Object obj) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            o oVar = (o) obj;
            Object objJ = this.f7663c.j(this);
            Object obj2 = p.f7667a;
            if (objJ != obj2) {
                Object objE = objJ != null ? a().e(objJ) : a().f();
                kotlinx.coroutines.j.a(o.f7656e, oVar, this, objE == kotlinx.coroutines.internal.c.f7622a ? a() : objE == null ? this.f7663c.n(oVar, this.f7662b) : this.f7662b);
                return null;
            }
            o oVar2 = this.f7662b;
            if (kotlinx.coroutines.j.a(o.f7656e, oVar, this, oVar2.O())) {
                this.f7663c.k(oVar);
                oVar2.D(null);
            }
            return obj2;
        }

        public final void d() {
            this.f7663c.g(this);
        }

        @Override // kotlinx.coroutines.internal.w
        public String toString() {
            return "PrepareOp(op=" + a() + ')';
        }
    }

    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static class d<T> extends a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final AtomicReferenceFieldUpdater f7664c = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "_affectedNode");

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final AtomicReferenceFieldUpdater f7665d = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "_originalNext");
        private volatile Object _affectedNode = null;
        private volatile Object _originalNext = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final o f7666b;

        public d(o oVar) {
            this.f7666b = oVar;
        }

        @Override // kotlinx.coroutines.internal.o.a
        protected Object e(o oVar) {
            if (oVar == this.f7666b) {
                return n.b();
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.o.a
        protected final void f(o oVar, o oVar2) {
            oVar2.D(null);
        }

        @Override // kotlinx.coroutines.internal.o.a
        public void g(c cVar) {
            kotlinx.coroutines.j.a(f7664c, this, null, cVar.f7661a);
            kotlinx.coroutines.j.a(f7665d, this, null, cVar.f7662b);
        }

        @Override // kotlinx.coroutines.internal.o.a
        protected final o h() {
            return (o) this._affectedNode;
        }

        @Override // kotlinx.coroutines.internal.o.a
        protected final o i() {
            return (o) this._originalNext;
        }

        @Override // kotlinx.coroutines.internal.o.a
        protected final boolean l(o oVar, Object obj) {
            if (!(obj instanceof x)) {
                return false;
            }
            ((x) obj).f7687a.K();
            return true;
        }

        @Override // kotlinx.coroutines.internal.o.a
        protected final o m(w wVar) {
            o oVar = this.f7666b;
            while (true) {
                Object obj = oVar._next;
                if (!(obj instanceof w)) {
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                    return (o) obj;
                }
                w wVar2 = (w) obj;
                if (wVar.b(wVar2)) {
                    return null;
                }
                wVar2.c(this.f7666b);
            }
        }

        @Override // kotlinx.coroutines.internal.o.a
        public final Object n(o oVar, o oVar2) {
            return oVar2.O();
        }

        public final T o() {
            T t6 = (T) h();
            kotlin.jvm.internal.l.c(t6);
            return t6;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0048, code lost:
    
        if (kotlinx.coroutines.j.a(kotlinx.coroutines.internal.o.f7656e, r3, r2, ((kotlinx.coroutines.internal.x) r4).f7687a) != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.internal.o D(kotlinx.coroutines.internal.w r7) {
        /*
            r6 = this;
        L0:
            java.lang.Object r0 = r6._prev
            kotlinx.coroutines.internal.o r0 = (kotlinx.coroutines.internal.o) r0
            r1 = 0
            r2 = r0
        L6:
            r3 = r1
        L7:
            java.lang.Object r4 = r2._next
            if (r4 != r6) goto L18
            if (r0 != r2) goto Le
            return r2
        Le:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.internal.o.f7657g
            boolean r0 = kotlinx.coroutines.j.a(r1, r6, r0, r2)
            if (r0 != 0) goto L17
            goto L0
        L17:
            return r2
        L18:
            boolean r5 = r6.L()
            if (r5 == 0) goto L1f
            return r1
        L1f:
            if (r4 != r7) goto L22
            return r2
        L22:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.w
            if (r5 == 0) goto L38
            if (r7 == 0) goto L32
            r0 = r4
            kotlinx.coroutines.internal.w r0 = (kotlinx.coroutines.internal.w) r0
            boolean r0 = r7.b(r0)
            if (r0 == 0) goto L32
            return r1
        L32:
            kotlinx.coroutines.internal.w r4 = (kotlinx.coroutines.internal.w) r4
            r4.c(r2)
            goto L0
        L38:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.x
            if (r5 == 0) goto L52
            if (r3 == 0) goto L4d
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.internal.o.f7656e
            kotlinx.coroutines.internal.x r4 = (kotlinx.coroutines.internal.x) r4
            kotlinx.coroutines.internal.o r4 = r4.f7687a
            boolean r2 = kotlinx.coroutines.j.a(r5, r3, r2, r4)
            if (r2 != 0) goto L4b
            goto L0
        L4b:
            r2 = r3
            goto L6
        L4d:
            java.lang.Object r2 = r2._prev
            kotlinx.coroutines.internal.o r2 = (kotlinx.coroutines.internal.o) r2
            goto L7
        L52:
        */
        //  java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            java.util.Objects.requireNonNull(r4, r3)
            kotlinx.coroutines.internal.o r4 = (kotlinx.coroutines.internal.o) r4
            r3 = r2
            r2 = r4
            goto L7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.o.D(kotlinx.coroutines.internal.w):kotlinx.coroutines.internal.o");
    }

    private final o E(o oVar) {
        while (oVar.L()) {
            oVar = (o) oVar._prev;
        }
        return oVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void F(o oVar) {
        o oVar2;
        do {
            oVar2 = (o) oVar._prev;
            if (G() != oVar) {
                return;
            }
        } while (!kotlinx.coroutines.j.a(f7657g, oVar, oVar2, this));
        if (L()) {
            oVar.D(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final x O() {
        x xVar = (x) this._removedRef;
        if (xVar != null) {
            return xVar;
        }
        x xVar2 = new x(this);
        f7658h.lazySet(this, xVar2);
        return xVar2;
    }

    public final void A(o oVar) {
        while (!I().B(oVar, this)) {
        }
    }

    public final boolean B(o oVar, o oVar2) {
        f7657g.lazySet(oVar, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f7656e;
        atomicReferenceFieldUpdater.lazySet(oVar, oVar2);
        if (!kotlinx.coroutines.j.a(atomicReferenceFieldUpdater, this, oVar2, oVar)) {
            return false;
        }
        oVar.F(oVar2);
        return true;
    }

    public final boolean C(o oVar) {
        f7657g.lazySet(oVar, this);
        f7656e.lazySet(oVar, this);
        while (G() == this) {
            if (kotlinx.coroutines.j.a(f7656e, this, this, oVar)) {
                oVar.F(this);
                return true;
            }
        }
        return false;
    }

    public final Object G() {
        while (true) {
            Object obj = this._next;
            if (!(obj instanceof w)) {
                return obj;
            }
            ((w) obj).c(this);
        }
    }

    public final o H() {
        return n.c(G());
    }

    public final o I() {
        o oVarD = D(null);
        return oVarD != null ? oVarD : E((o) this._prev);
    }

    public final void J() {
        Object objG = G();
        Objects.requireNonNull(objG, "null cannot be cast to non-null type kotlinx.coroutines.internal.Removed");
        ((x) objG).f7687a.D(null);
    }

    public final void K() {
        o oVar = this;
        while (true) {
            Object objG = oVar.G();
            if (!(objG instanceof x)) {
                oVar.D(null);
                return;
            }
            oVar = ((x) objG).f7687a;
        }
    }

    public boolean L() {
        return G() instanceof x;
    }

    public boolean M() {
        return N() == null;
    }

    public final o N() {
        Object objG;
        o oVar;
        do {
            objG = G();
            if (objG instanceof x) {
                return ((x) objG).f7687a;
            }
            if (objG == this) {
                return (o) objG;
            }
            Objects.requireNonNull(objG, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            oVar = (o) objG;
        } while (!kotlinx.coroutines.j.a(f7656e, this, objG, oVar.O()));
        oVar.D(null);
        return null;
    }

    public final int P(o oVar, o oVar2, b bVar) {
        f7657g.lazySet(oVar, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f7656e;
        atomicReferenceFieldUpdater.lazySet(oVar, oVar2);
        bVar.f7659b = oVar2;
        if (kotlinx.coroutines.j.a(atomicReferenceFieldUpdater, this, oVar2, bVar)) {
            return bVar.c(this) == null ? 1 : 2;
        }
        return 0;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + Integer.toHexString(System.identityHashCode(this));
    }
}
