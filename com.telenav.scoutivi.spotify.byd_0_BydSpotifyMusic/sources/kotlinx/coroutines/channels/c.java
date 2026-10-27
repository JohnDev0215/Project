package kotlinx.coroutines.channels;

import g5.l;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.internal.k0;
import kotlinx.coroutines.internal.o;
import kotlinx.coroutines.m0;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<E> implements g0<E> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7547h = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "onCloseHandler");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected final r5.l<E, g5.r> f7549g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final kotlinx.coroutines.internal.m f7548e = new kotlinx.coroutines.internal.m();
    private volatile Object onCloseHandler = null;

    /* JADX INFO: compiled from: AbstractChannel.kt */
    public static final class a<E> extends f0 {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final E f7550i;

        public a(E e7) {
            this.f7550i = e7;
        }

        @Override // kotlinx.coroutines.channels.f0
        public void Q() {
        }

        @Override // kotlinx.coroutines.channels.f0
        public Object R() {
            return this.f7550i;
        }

        @Override // kotlinx.coroutines.channels.f0
        public void S(r<?> rVar) {
        }

        @Override // kotlinx.coroutines.channels.f0
        public kotlinx.coroutines.internal.c0 T(o.c cVar) {
            kotlinx.coroutines.internal.c0 c0Var = kotlinx.coroutines.l.f7702a;
            if (cVar != null) {
                cVar.d();
            }
            return c0Var;
        }

        @Override // kotlinx.coroutines.internal.o
        public String toString() {
            return "SendBuffered@" + m0.b(this) + '(' + this.f7550i + ')';
        }
    }

    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static final class b extends o.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ kotlinx.coroutines.internal.o f7551d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ c f7552e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(kotlinx.coroutines.internal.o oVar, kotlinx.coroutines.internal.o oVar2, c cVar) {
            super(oVar2);
            this.f7551d = oVar;
            this.f7552e = cVar;
        }

        @Override // kotlinx.coroutines.internal.d
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Object i(kotlinx.coroutines.internal.o oVar) {
            if (this.f7552e.o()) {
                return null;
            }
            return kotlinx.coroutines.internal.n.a();
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbstractChannel.kt */
    public static final class C0105c implements kotlinx.coroutines.selects.e<E, g0<? super E>> {
        C0105c() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c(r5.l<? super E, g5.r> lVar) {
        this.f7549g = lVar;
    }

    private final int b() {
        Object objG = this.f7548e.G();
        Objects.requireNonNull(objG, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        int i7 = 0;
        for (kotlinx.coroutines.internal.o oVarH = (kotlinx.coroutines.internal.o) objG; !kotlin.jvm.internal.l.a(oVarH, r0); oVarH = oVarH.H()) {
            if (oVarH instanceof kotlinx.coroutines.internal.o) {
                i7++;
            }
        }
        return i7;
    }

    private final String i() {
        String string;
        kotlinx.coroutines.internal.o oVarH = this.f7548e.H();
        if (oVarH == this.f7548e) {
            return "EmptyQueue";
        }
        if (oVarH instanceof r) {
            string = oVarH.toString();
        } else if (oVarH instanceof b0) {
            string = "ReceiveQueued";
        } else if (oVarH instanceof f0) {
            string = "SendQueued";
        } else {
            string = "UNEXPECTED:" + oVarH;
        }
        kotlinx.coroutines.internal.o oVarI = this.f7548e.I();
        if (oVarI == oVarH) {
            return string;
        }
        String str = string + ",queueSize=" + b();
        if (!(oVarI instanceof r)) {
            return str;
        }
        return str + ",closedForSend=" + oVarI;
    }

    private final void j(r<?> rVar) {
        Object objB = kotlinx.coroutines.internal.l.b(null, 1, null);
        while (true) {
            kotlinx.coroutines.internal.o oVarI = rVar.I();
            if (!(oVarI instanceof b0)) {
                oVarI = null;
            }
            b0 b0Var = (b0) oVarI;
            if (b0Var == null) {
                break;
            } else if (b0Var.M()) {
                objB = kotlinx.coroutines.internal.l.c(objB, b0Var);
            } else {
                b0Var.J();
            }
        }
        if (objB != null) {
            if (objB instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) objB;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ((b0) arrayList.get(size)).S(rVar);
                }
            } else {
                ((b0) objB).S(rVar);
            }
        }
        r(rVar);
    }

    private final Throwable k(E e7, r<?> rVar) {
        k0 k0VarD;
        j(rVar);
        r5.l<E, g5.r> lVar = this.f7549g;
        if (lVar == null || (k0VarD = kotlinx.coroutines.internal.v.d(lVar, e7, null, 2, null)) == null) {
            return rVar.Y();
        }
        g5.b.a(k0VarD, rVar.Y());
        throw k0VarD;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void l(k5.d<?> dVar, E e7, r<?> rVar) {
        k0 k0VarD;
        j(rVar);
        Throwable thY = rVar.Y();
        r5.l<E, g5.r> lVar = this.f7549g;
        if (lVar == null || (k0VarD = kotlinx.coroutines.internal.v.d(lVar, e7, null, 2, null)) == null) {
            l.a aVar = g5.l.f6557e;
            dVar.resumeWith(g5.l.a(g5.m.a(thY)));
        } else {
            g5.b.a(k0VarD, thY);
            l.a aVar2 = g5.l.f6557e;
            dVar.resumeWith(g5.l.a(g5.m.a(k0VarD)));
        }
    }

    private final void m(Throwable th) {
        kotlinx.coroutines.internal.c0 c0Var;
        Object obj = this.onCloseHandler;
        if (obj == null || obj == (c0Var = kotlinx.coroutines.channels.b.f7546f) || !kotlinx.coroutines.j.a(f7547h, this, obj, c0Var)) {
            return;
        }
        ((r5.l) kotlin.jvm.internal.v.b(obj, 1)).invoke(th);
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean close(Throwable th) {
        boolean z6;
        r<?> rVar = new r<>(th);
        kotlinx.coroutines.internal.o oVar = this.f7548e;
        while (true) {
            kotlinx.coroutines.internal.o oVarI = oVar.I();
            z6 = true;
            if (!(!(oVarI instanceof r))) {
                z6 = false;
                break;
            }
            if (oVarI.B(rVar, oVar)) {
                break;
            }
        }
        if (!z6) {
            kotlinx.coroutines.internal.o oVarI2 = this.f7548e.I();
            Objects.requireNonNull(oVarI2, "null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
            rVar = (r) oVarI2;
        }
        j(rVar);
        if (z6) {
            m(th);
        }
        return z6;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0034, code lost:
    
        if (r3 != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0038, code lost:
    
        return kotlinx.coroutines.channels.b.f7545e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0039, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object d(kotlinx.coroutines.channels.f0 r5) {
        /*
            r4 = this;
            boolean r0 = r4.n()
            if (r0 == 0) goto L18
            kotlinx.coroutines.internal.m r0 = r4.f7548e
        L8:
            kotlinx.coroutines.internal.o r1 = r0.I()
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.d0
            if (r2 == 0) goto L11
            return r1
        L11:
            boolean r1 = r1.B(r5, r0)
            if (r1 == 0) goto L8
            goto L39
        L18:
            kotlinx.coroutines.internal.m r0 = r4.f7548e
            kotlinx.coroutines.channels.c$b r1 = new kotlinx.coroutines.channels.c$b
            r1.<init>(r5, r5, r4)
        L1f:
            kotlinx.coroutines.internal.o r2 = r0.I()
            boolean r3 = r2 instanceof kotlinx.coroutines.channels.d0
            if (r3 == 0) goto L28
            return r2
        L28:
            int r2 = r2.P(r5, r0, r1)
            r3 = 1
            if (r2 == r3) goto L34
            r3 = 2
            if (r2 == r3) goto L33
            goto L1f
        L33:
            r3 = 0
        L34:
            if (r3 != 0) goto L39
            kotlinx.coroutines.internal.c0 r5 = kotlinx.coroutines.channels.b.f7545e
            return r5
        L39:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.c.d(kotlinx.coroutines.channels.f0):java.lang.Object");
    }

    protected String e() {
        return "";
    }

    protected final r<?> f() {
        kotlinx.coroutines.internal.o oVarH = this.f7548e.H();
        if (!(oVarH instanceof r)) {
            oVarH = null;
        }
        r<?> rVar = (r) oVarH;
        if (rVar == null) {
            return null;
        }
        j(rVar);
        return rVar;
    }

    protected final r<?> g() {
        kotlinx.coroutines.internal.o oVarI = this.f7548e.I();
        if (!(oVarI instanceof r)) {
            oVarI = null;
        }
        r<?> rVar = (r) oVarI;
        if (rVar == null) {
            return null;
        }
        j(rVar);
        return rVar;
    }

    @Override // kotlinx.coroutines.channels.g0
    public final kotlinx.coroutines.selects.e<E, g0<E>> getOnSend() {
        return new C0105c();
    }

    protected final kotlinx.coroutines.internal.m h() {
        return this.f7548e;
    }

    @Override // kotlinx.coroutines.channels.g0
    public void invokeOnClose(r5.l<? super Throwable, g5.r> lVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f7547h;
        if (kotlinx.coroutines.j.a(atomicReferenceFieldUpdater, this, null, lVar)) {
            r<?> rVarG = g();
            if (rVarG == null || !kotlinx.coroutines.j.a(atomicReferenceFieldUpdater, this, lVar, kotlinx.coroutines.channels.b.f7546f)) {
                return;
            }
            lVar.invoke(rVarG.f7577i);
            return;
        }
        Object obj = this.onCloseHandler;
        if (obj == kotlinx.coroutines.channels.b.f7546f) {
            throw new IllegalStateException("Another handler was already registered and successfully invoked");
        }
        throw new IllegalStateException("Another handler was already registered: " + obj);
    }

    @Override // kotlinx.coroutines.channels.g0
    public final boolean isClosedForSend() {
        return g() != null;
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean isFull() {
        return p();
    }

    protected abstract boolean n();

    protected abstract boolean o();

    @Override // kotlinx.coroutines.channels.g0
    public final boolean offer(E e7) throws Throwable {
        Object objQ = q(e7);
        if (objQ == kotlinx.coroutines.channels.b.f7542b) {
            return true;
        }
        if (objQ == kotlinx.coroutines.channels.b.f7543c) {
            r<?> rVarG = g();
            if (rVarG == null) {
                return false;
            }
            throw kotlinx.coroutines.internal.b0.a(k(e7, rVarG));
        }
        if (objQ instanceof r) {
            throw kotlinx.coroutines.internal.b0.a(k(e7, (r) objQ));
        }
        throw new IllegalStateException(("offerInternal returned " + objQ).toString());
    }

    protected final boolean p() {
        return !(this.f7548e.H() instanceof d0) && o();
    }

    protected Object q(E e7) {
        d0<E> d0VarU;
        do {
            d0VarU = u();
            if (d0VarU == null) {
                return kotlinx.coroutines.channels.b.f7543c;
            }
        } while (d0VarU.l(e7, null) == null);
        d0VarU.q(e7);
        return d0VarU.g();
    }

    protected void r(kotlinx.coroutines.internal.o oVar) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final d0<?> s(E e7) {
        kotlinx.coroutines.internal.o oVarI;
        kotlinx.coroutines.internal.m mVar = this.f7548e;
        a aVar = new a(e7);
        do {
            oVarI = mVar.I();
            if (oVarI instanceof d0) {
                return (d0) oVarI;
            }
        } while (!oVarI.B(aVar, mVar));
        return null;
    }

    @Override // kotlinx.coroutines.channels.g0
    public final Object send(E e7, k5.d<? super g5.r> dVar) {
        Object objT;
        return (q(e7) != kotlinx.coroutines.channels.b.f7542b && (objT = t(e7, dVar)) == l5.d.d()) ? objT : g5.r.f6563a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0060 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object t(E r4, k5.d<? super g5.r> r5) throws java.lang.Throwable {
        /*
            r3 = this;
            k5.d r0 = l5.b.c(r5)
            kotlinx.coroutines.k r0 = kotlinx.coroutines.m.b(r0)
        L8:
            boolean r1 = r3.p()
            if (r1 == 0) goto L58
            r5.l<E, g5.r> r1 = r3.f7549g
            if (r1 != 0) goto L18
            kotlinx.coroutines.channels.h0 r1 = new kotlinx.coroutines.channels.h0
            r1.<init>(r4, r0)
            goto L1f
        L18:
            kotlinx.coroutines.channels.i0 r1 = new kotlinx.coroutines.channels.i0
            r5.l<E, g5.r> r2 = r3.f7549g
            r1.<init>(r4, r0, r2)
        L1f:
            java.lang.Object r2 = r3.d(r1)
            if (r2 != 0) goto L29
            kotlinx.coroutines.m.c(r0, r1)
            goto L7a
        L29:
            boolean r1 = r2 instanceof kotlinx.coroutines.channels.r
            if (r1 == 0) goto L33
            kotlinx.coroutines.channels.r r2 = (kotlinx.coroutines.channels.r) r2
            a(r3, r0, r4, r2)
            goto L7a
        L33:
            kotlinx.coroutines.internal.c0 r1 = kotlinx.coroutines.channels.b.f7545e
            if (r2 != r1) goto L38
            goto L58
        L38:
            boolean r1 = r2 instanceof kotlinx.coroutines.channels.b0
            if (r1 == 0) goto L3d
            goto L58
        L3d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "enqueueSend returned "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            throw r5
        L58:
            java.lang.Object r1 = r3.q(r4)
            kotlinx.coroutines.internal.c0 r2 = kotlinx.coroutines.channels.b.f7542b
            if (r1 != r2) goto L6c
            g5.r r4 = g5.r.f6563a
            g5.l$a r1 = g5.l.f6557e
            java.lang.Object r4 = g5.l.a(r4)
            r0.resumeWith(r4)
            goto L7a
        L6c:
            kotlinx.coroutines.internal.c0 r2 = kotlinx.coroutines.channels.b.f7543c
            if (r1 != r2) goto L71
            goto L8
        L71:
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.r
            if (r2 == 0) goto L88
            kotlinx.coroutines.channels.r r1 = (kotlinx.coroutines.channels.r) r1
            a(r3, r0, r4, r1)
        L7a:
            java.lang.Object r4 = r0.y()
            java.lang.Object r0 = l5.b.d()
            if (r4 != r0) goto L87
            kotlin.coroutines.jvm.internal.h.c(r5)
        L87:
            return r4
        L88:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "offerInternal returned "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.c.t(java.lang.Object, k5.d):java.lang.Object");
    }

    public String toString() {
        return m0.a(this) + '@' + m0.b(this) + '{' + i() + '}' + e();
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.internal.o] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected kotlinx.coroutines.channels.d0<E> u() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.m r0 = r4.f7548e
        L2:
            java.lang.Object r1 = r0.G()
        */
        //  java.lang.String r2 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            java.util.Objects.requireNonNull(r1, r2)
            kotlinx.coroutines.internal.o r1 = (kotlinx.coroutines.internal.o) r1
            r2 = 0
            if (r1 != r0) goto L12
        L10:
            r1 = r2
            goto L2b
        L12:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.d0
            if (r3 != 0) goto L17
            goto L10
        L17:
            r2 = r1
            kotlinx.coroutines.channels.d0 r2 = (kotlinx.coroutines.channels.d0) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.r
            if (r2 == 0) goto L25
            boolean r2 = r1.L()
            if (r2 != 0) goto L25
            goto L2b
        L25:
            kotlinx.coroutines.internal.o r2 = r1.N()
            if (r2 != 0) goto L2e
        L2b:
            kotlinx.coroutines.channels.d0 r1 = (kotlinx.coroutines.channels.d0) r1
            return r1
        L2e:
            r2.K()
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.c.u():kotlinx.coroutines.channels.d0");
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
    
        r1 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final kotlinx.coroutines.channels.f0 v() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.m r0 = r4.f7548e
        L2:
            java.lang.Object r1 = r0.G()
        */
        //  java.lang.String r2 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            java.util.Objects.requireNonNull(r1, r2)
            kotlinx.coroutines.internal.o r1 = (kotlinx.coroutines.internal.o) r1
            r2 = 0
            if (r1 != r0) goto L12
        L10:
            r1 = r2
            goto L2b
        L12:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.f0
            if (r3 != 0) goto L17
            goto L10
        L17:
            r2 = r1
            kotlinx.coroutines.channels.f0 r2 = (kotlinx.coroutines.channels.f0) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.r
            if (r2 == 0) goto L25
            boolean r2 = r1.L()
            if (r2 != 0) goto L25
            goto L2b
        L25:
            kotlinx.coroutines.internal.o r2 = r1.N()
            if (r2 != 0) goto L2e
        L2b:
            kotlinx.coroutines.channels.f0 r1 = (kotlinx.coroutines.channels.f0) r1
            return r1
        L2e:
            r2.K()
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.c.v():kotlinx.coroutines.channels.f0");
    }
}
