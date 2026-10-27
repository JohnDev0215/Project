package kotlinx.coroutines.channels;

import g5.l;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import kotlinx.coroutines.channels.j0;
import kotlinx.coroutines.internal.o;
import kotlinx.coroutines.m0;
import kotlinx.coroutines.v0;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<E> extends kotlinx.coroutines.channels.c<E> implements l<E> {

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbstractChannel.kt */
    private static final class C0104a<E> implements n<E> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Object f7520a = kotlinx.coroutines.channels.b.f7544d;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final a<E> f7521b;

        public C0104a(a<E> aVar) {
            this.f7521b = aVar;
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    private static class b<E> extends b0<E> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final kotlinx.coroutines.i<Object> f7522i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final int f7523j;

        public b(kotlinx.coroutines.i<Object> iVar, int i7) {
            this.f7522i = iVar;
            this.f7523j = i7;
        }

        @Override // kotlinx.coroutines.channels.b0
        public void S(r<?> rVar) {
            int i7 = this.f7523j;
            if (i7 == 1 && rVar.f7577i == null) {
                kotlinx.coroutines.i<Object> iVar = this.f7522i;
                l.a aVar = g5.l.f6557e;
                iVar.resumeWith(g5.l.a(null));
            } else {
                if (i7 != 2) {
                    kotlinx.coroutines.i<Object> iVar2 = this.f7522i;
                    Throwable thX = rVar.X();
                    l.a aVar2 = g5.l.f6557e;
                    iVar2.resumeWith(g5.l.a(g5.m.a(thX)));
                    return;
                }
                kotlinx.coroutines.i<Object> iVar3 = this.f7522i;
                j0.b bVar = j0.f7570b;
                j0 j0VarA = j0.a(j0.b(new j0.a(rVar.f7577i)));
                l.a aVar3 = g5.l.f6557e;
                iVar3.resumeWith(g5.l.a(j0VarA));
            }
        }

        public final Object T(E e7) {
            if (this.f7523j != 2) {
                return e7;
            }
            j0.b bVar = j0.f7570b;
            return j0.a(j0.b(e7));
        }

        @Override // kotlinx.coroutines.channels.d0
        public kotlinx.coroutines.internal.c0 l(E e7, o.c cVar) {
            if (this.f7522i.r(T(e7), cVar != null ? cVar.f7663c : null, R(e7)) == null) {
                return null;
            }
            if (cVar != null) {
                cVar.d();
            }
            return kotlinx.coroutines.l.f7702a;
        }

        @Override // kotlinx.coroutines.channels.d0
        public void q(E e7) {
            this.f7522i.v(kotlinx.coroutines.l.f7702a);
        }

        @Override // kotlinx.coroutines.internal.o
        public String toString() {
            return "ReceiveElement@" + m0.b(this) + "[receiveMode=" + this.f7523j + ']';
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    private static final class c<E> extends b<E> {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final r5.l<E, g5.r> f7524k;

        /* JADX WARN: Multi-variable type inference failed */
        public c(kotlinx.coroutines.i<Object> iVar, int i7, r5.l<? super E, g5.r> lVar) {
            super(iVar, i7);
            this.f7524k = lVar;
        }

        @Override // kotlinx.coroutines.channels.b0
        public r5.l<Throwable, g5.r> R(E e7) {
            return kotlinx.coroutines.internal.v.a(this.f7524k, e7, this.f7522i.getContext());
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    private static final class d<R, E> extends b0<E> implements v0 {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final a<E> f7525i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final kotlinx.coroutines.selects.f<R> f7526j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final r5.p<Object, k5.d<? super R>, Object> f7527k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final int f7528l;

        /* JADX WARN: Multi-variable type inference failed */
        public d(a<E> aVar, kotlinx.coroutines.selects.f<? super R> fVar, r5.p<Object, ? super k5.d<? super R>, ? extends Object> pVar, int i7) {
            this.f7525i = aVar;
            this.f7526j = fVar;
            this.f7527k = pVar;
            this.f7528l = i7;
        }

        @Override // kotlinx.coroutines.channels.b0
        public r5.l<Throwable, g5.r> R(E e7) {
            r5.l<E, g5.r> lVar = this.f7525i.f7549g;
            if (lVar != null) {
                return kotlinx.coroutines.internal.v.a(lVar, e7, this.f7526j.i().getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.b0
        public void S(r<?> rVar) {
            if (this.f7526j.j()) {
                int i7 = this.f7528l;
                if (i7 == 0) {
                    this.f7526j.u(rVar.X());
                    return;
                }
                if (i7 == 1) {
                    if (rVar.f7577i == null) {
                        b6.a.d(this.f7527k, null, this.f7526j.i(), null, 4, null);
                        return;
                    } else {
                        this.f7526j.u(rVar.X());
                        return;
                    }
                }
                if (i7 != 2) {
                    return;
                }
                r5.p<Object, k5.d<? super R>, Object> pVar = this.f7527k;
                j0.b bVar = j0.f7570b;
                b6.a.d(pVar, j0.a(j0.b(new j0.a(rVar.f7577i))), this.f7526j.i(), null, 4, null);
            }
        }

        @Override // kotlinx.coroutines.v0
        public void dispose() {
            if (M()) {
                this.f7525i.I();
            }
        }

        @Override // kotlinx.coroutines.channels.d0
        public kotlinx.coroutines.internal.c0 l(E e7, o.c cVar) {
            return (kotlinx.coroutines.internal.c0) this.f7526j.b(cVar);
        }

        @Override // kotlinx.coroutines.channels.d0
        public void q(E e7) {
            Object objA;
            r5.p<Object, k5.d<? super R>, Object> pVar = this.f7527k;
            if (this.f7528l == 2) {
                j0.b bVar = j0.f7570b;
                objA = j0.a(j0.b(e7));
            } else {
                objA = e7;
            }
            b6.a.c(pVar, objA, this.f7526j.i(), R(e7));
        }

        @Override // kotlinx.coroutines.internal.o
        public String toString() {
            return "ReceiveSelect@" + m0.b(this) + '[' + this.f7526j + ",receiveMode=" + this.f7528l + ']';
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    private final class e extends kotlinx.coroutines.c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final b0<?> f7529e;

        public e(b0<?> b0Var) {
            this.f7529e = b0Var;
        }

        @Override // kotlinx.coroutines.h
        public void a(Throwable th) {
            if (this.f7529e.M()) {
                a.this.I();
            }
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
            a(th);
            return g5.r.f6563a;
        }

        public String toString() {
            return "RemoveReceiveOnCancel[" + this.f7529e + ']';
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    protected static final class f<E> extends o.d<f0> {
        public f(kotlinx.coroutines.internal.m mVar) {
            super(mVar);
        }

        @Override // kotlinx.coroutines.internal.o.d, kotlinx.coroutines.internal.o.a
        protected Object e(kotlinx.coroutines.internal.o oVar) {
            if (oVar instanceof r) {
                return oVar;
            }
            if (oVar instanceof f0) {
                return null;
            }
            return kotlinx.coroutines.channels.b.f7544d;
        }

        @Override // kotlinx.coroutines.internal.o.a
        public Object j(o.c cVar) {
            kotlinx.coroutines.internal.o oVar = cVar.f7661a;
            Objects.requireNonNull(oVar, "null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
            kotlinx.coroutines.internal.c0 c0VarT = ((f0) oVar).T(cVar);
            if (c0VarT == null) {
                return kotlinx.coroutines.internal.p.f7667a;
            }
            Object obj = kotlinx.coroutines.internal.c.f7623b;
            if (c0VarT == obj) {
                return obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.o.a
        public void k(kotlinx.coroutines.internal.o oVar) {
            Objects.requireNonNull(oVar, "null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
            ((f0) oVar).U();
        }
    }

    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static final class g extends o.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ kotlinx.coroutines.internal.o f7531d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ a f7532e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(kotlinx.coroutines.internal.o oVar, kotlinx.coroutines.internal.o oVar2, a aVar) {
            super(oVar2);
            this.f7531d = oVar;
            this.f7532e = aVar;
        }

        @Override // kotlinx.coroutines.internal.d
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Object i(kotlinx.coroutines.internal.o oVar) {
            if (this.f7532e.F()) {
                return null;
            }
            return kotlinx.coroutines.internal.n.a();
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    public static final class h implements kotlinx.coroutines.selects.d<E> {
        h() {
        }

        @Override // kotlinx.coroutines.selects.d
        public <R> void b(kotlinx.coroutines.selects.f<? super R> fVar, r5.p<? super E, ? super k5.d<? super R>, ? extends Object> pVar) throws Throwable {
            a aVar = a.this;
            Objects.requireNonNull(pVar, "null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
            aVar.O(fVar, 0, pVar);
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    public static final class i implements kotlinx.coroutines.selects.d<j0<? extends E>> {
        i() {
        }

        @Override // kotlinx.coroutines.selects.d
        public <R> void b(kotlinx.coroutines.selects.f<? super R> fVar, r5.p<? super j0<? extends E>, ? super k5.d<? super R>, ? extends Object> pVar) throws Throwable {
            a aVar = a.this;
            Objects.requireNonNull(pVar, "null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
            aVar.O(fVar, 2, pVar);
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    public static final class j implements kotlinx.coroutines.selects.d<E> {
        j() {
        }

        @Override // kotlinx.coroutines.selects.d
        public <R> void b(kotlinx.coroutines.selects.f<? super R> fVar, r5.p<? super E, ? super k5.d<? super R>, ? extends Object> pVar) throws Throwable {
            a aVar = a.this;
            Objects.requireNonNull(pVar, "null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
            aVar.O(fVar, 1, pVar);
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @kotlin.coroutines.jvm.internal.f(c = "kotlinx.coroutines.channels.AbstractChannel", f = "AbstractChannel.kt", l = {624}, m = "receiveOrClosed-ZYPwvRU")
    static final class k extends kotlin.coroutines.jvm.internal.d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        /* synthetic */ Object f7536e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f7537g;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Object f7539i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        Object f7540j;

        k(k5.d dVar) {
            super(dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            this.f7536e = obj;
            this.f7537g |= Integer.MIN_VALUE;
            return a.this.mo1receiveOrClosedZYPwvRU(this);
        }
    }

    public a(r5.l<? super E, g5.r> lVar) {
        super(lVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean B(b0<? super E> b0Var) {
        boolean zC = C(b0Var);
        if (zC) {
            J();
        }
        return zC;
    }

    private final <R> boolean D(kotlinx.coroutines.selects.f<? super R> fVar, r5.p<Object, ? super k5.d<? super R>, ? extends Object> pVar, int i7) {
        d dVar = new d(this, fVar, pVar, i7);
        boolean zB = B(dVar);
        if (zB) {
            fVar.o(dVar);
        }
        return zB;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final E M(Object obj) throws Throwable {
        if (!(obj instanceof r)) {
            return obj;
        }
        Throwable th = ((r) obj).f7577i;
        if (th == null) {
            return null;
        }
        throw kotlinx.coroutines.internal.b0.a(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void O(kotlinx.coroutines.selects.f<? super R> fVar, int i7, r5.p<Object, ? super k5.d<? super R>, ? extends Object> pVar) throws Throwable {
        while (!fVar.s()) {
            if (!G()) {
                Object objL = L(fVar);
                if (objL == kotlinx.coroutines.selects.g.d()) {
                    return;
                }
                if (objL != kotlinx.coroutines.channels.b.f7544d && objL != kotlinx.coroutines.internal.c.f7623b) {
                    Q(pVar, fVar, i7, objL);
                }
            } else if (D(fVar, pVar, i7)) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void P(kotlinx.coroutines.i<?> iVar, b0<?> b0Var) {
        iVar.d(new e(b0Var));
    }

    private final <R> void Q(r5.p<Object, ? super k5.d<? super R>, ? extends Object> pVar, kotlinx.coroutines.selects.f<? super R> fVar, int i7, Object obj) throws Throwable {
        boolean z6 = obj instanceof r;
        if (!z6) {
            if (i7 != 2) {
                b6.b.d(pVar, obj, fVar.i());
                return;
            } else {
                j0.b bVar = j0.f7570b;
                b6.b.d(pVar, j0.a(z6 ? j0.b(new j0.a(((r) obj).f7577i)) : j0.b(obj)), fVar.i());
                return;
            }
        }
        if (i7 == 0) {
            throw kotlinx.coroutines.internal.b0.a(((r) obj).X());
        }
        if (i7 != 1) {
            if (i7 == 2 && fVar.j()) {
                j0.b bVar2 = j0.f7570b;
                b6.b.d(pVar, j0.a(j0.b(new j0.a(((r) obj).f7577i))), fVar.i());
                return;
            }
            return;
        }
        r rVar = (r) obj;
        if (rVar.f7577i != null) {
            throw kotlinx.coroutines.internal.b0.a(rVar.X());
        }
        if (fVar.j()) {
            b6.b.d(pVar, null, fVar.i());
        }
    }

    protected final f<E> A() {
        return new f<>(h());
    }

    protected boolean C(b0<? super E> b0Var) {
        int iP;
        kotlinx.coroutines.internal.o oVarI;
        if (!E()) {
            kotlinx.coroutines.internal.o oVarH = h();
            g gVar = new g(b0Var, b0Var, this);
            do {
                kotlinx.coroutines.internal.o oVarI2 = oVarH.I();
                if (!(!(oVarI2 instanceof f0))) {
                    return false;
                }
                iP = oVarI2.P(b0Var, oVarH, gVar);
                if (iP != 1) {
                }
            } while (iP != 2);
            return false;
        }
        kotlinx.coroutines.internal.o oVarH2 = h();
        do {
            oVarI = oVarH2.I();
            if (!(!(oVarI instanceof f0))) {
                return false;
            }
        } while (!oVarI.B(b0Var, oVarH2));
        return true;
    }

    protected abstract boolean E();

    protected abstract boolean F();

    protected final boolean G() {
        return !(h().H() instanceof f0) && F();
    }

    protected void H(boolean z6) {
        r<?> rVarG = g();
        if (rVarG == null) {
            throw new IllegalStateException("Cannot happen".toString());
        }
        Object objB = kotlinx.coroutines.internal.l.b(null, 1, null);
        while (true) {
            kotlinx.coroutines.internal.o oVarI = rVarG.I();
            if (oVarI instanceof kotlinx.coroutines.internal.m) {
                break;
            } else if (oVarI.M()) {
                objB = kotlinx.coroutines.internal.l.c(objB, (f0) oVarI);
            } else {
                oVarI.J();
            }
        }
        if (objB == null) {
            return;
        }
        if (!(objB instanceof ArrayList)) {
            ((f0) objB).S(rVarG);
            return;
        }
        ArrayList arrayList = (ArrayList) objB;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((f0) arrayList.get(size)).S(rVarG);
        }
    }

    protected void I() {
    }

    protected void J() {
    }

    protected Object K() {
        while (true) {
            f0 f0VarV = v();
            if (f0VarV == null) {
                return kotlinx.coroutines.channels.b.f7544d;
            }
            if (f0VarV.T(null) != null) {
                f0VarV.Q();
                return f0VarV.R();
            }
            f0VarV.U();
        }
    }

    protected Object L(kotlinx.coroutines.selects.f<?> fVar) {
        f<E> fVarA = A();
        Object objW = fVar.w(fVarA);
        if (objW != null) {
            return objW;
        }
        fVarA.o().Q();
        return fVarA.o().R();
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ <R> Object N(int i7, k5.d<? super R> dVar) throws Throwable {
        b cVar;
        kotlinx.coroutines.k kVarB = kotlinx.coroutines.m.b(l5.c.c(dVar));
        if (this.f7549g == null) {
            Objects.requireNonNull(kVarB, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Any?>");
            cVar = new b(kVarB, i7);
        } else {
            Objects.requireNonNull(kVarB, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Any?>");
            cVar = new c(kVarB, i7, this.f7549g);
        }
        while (true) {
            if (B(cVar)) {
                P(kVarB, cVar);
                break;
            }
            Object objK = K();
            if (objK instanceof r) {
                cVar.S((r) objK);
                break;
            }
            if (objK != kotlinx.coroutines.channels.b.f7544d) {
                kVarB.a(cVar.T(objK), cVar.R(objK));
                break;
            }
        }
        Object objY = kVarB.y();
        if (objY == l5.d.d()) {
            kotlin.coroutines.jvm.internal.h.c(dVar);
        }
        return objY;
    }

    @Override // kotlinx.coroutines.channels.c0
    public final void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException(m0.a(this) + " was cancelled");
        }
        z(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.c0
    public final kotlinx.coroutines.selects.d<E> getOnReceive() {
        return new h();
    }

    @Override // kotlinx.coroutines.channels.c0
    public final kotlinx.coroutines.selects.d<j0<E>> getOnReceiveOrClosed() {
        return new i();
    }

    @Override // kotlinx.coroutines.channels.c0
    public final kotlinx.coroutines.selects.d<E> getOnReceiveOrNull() {
        return new j();
    }

    @Override // kotlinx.coroutines.channels.c0
    public boolean isClosedForReceive() {
        return f() != null && F();
    }

    @Override // kotlinx.coroutines.channels.c0
    public boolean isEmpty() {
        return G();
    }

    @Override // kotlinx.coroutines.channels.c0
    public final n<E> iterator() {
        return new C0104a(this);
    }

    @Override // kotlinx.coroutines.channels.c0
    public final E poll() {
        Object objK = K();
        if (objK == kotlinx.coroutines.channels.b.f7544d) {
            return null;
        }
        return M(objK);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.c0
    public final Object receive(k5.d<? super E> dVar) {
        Object objK = K();
        return (objK == kotlinx.coroutines.channels.b.f7544d || (objK instanceof r)) ? N(0, dVar) : objK;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // kotlinx.coroutines.channels.c0
    /* JADX INFO: renamed from: receiveOrClosed-ZYPwvRU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object mo1receiveOrClosedZYPwvRU(k5.d<? super kotlinx.coroutines.channels.j0<? extends E>> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.a.k
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.a$k r0 = (kotlinx.coroutines.channels.a.k) r0
            int r1 = r0.f7537g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f7537g = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.a$k r0 = new kotlinx.coroutines.channels.a$k
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.f7536e
            java.lang.Object r1 = l5.b.d()
            int r2 = r0.f7537g
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.f7539i
            kotlinx.coroutines.channels.a r0 = (kotlinx.coroutines.channels.a) r0
            g5.m.b(r5)
            goto L69
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            g5.m.b(r5)
            java.lang.Object r5 = r4.K()
            kotlinx.coroutines.internal.c0 r2 = kotlinx.coroutines.channels.b.f7544d
            if (r5 == r2) goto L5b
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.r
            if (r0 == 0) goto L54
            kotlinx.coroutines.channels.j0$b r0 = kotlinx.coroutines.channels.j0.f7570b
            kotlinx.coroutines.channels.r r5 = (kotlinx.coroutines.channels.r) r5
            java.lang.Throwable r5 = r5.f7577i
            kotlinx.coroutines.channels.j0$a r0 = new kotlinx.coroutines.channels.j0$a
            r0.<init>(r5)
            java.lang.Object r5 = kotlinx.coroutines.channels.j0.b(r0)
            goto L5a
        L54:
            kotlinx.coroutines.channels.j0$b r0 = kotlinx.coroutines.channels.j0.f7570b
            java.lang.Object r5 = kotlinx.coroutines.channels.j0.b(r5)
        L5a:
            return r5
        L5b:
            r2 = 2
            r0.f7539i = r4
            r0.f7540j = r5
            r0.f7537g = r3
            java.lang.Object r5 = r4.N(r2, r0)
            if (r5 != r1) goto L69
            return r1
        L69:
            kotlinx.coroutines.channels.j0 r5 = (kotlinx.coroutines.channels.j0) r5
            java.lang.Object r5 = r5.i()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.mo1receiveOrClosedZYPwvRU(k5.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.c0
    public final Object receiveOrNull(k5.d<? super E> dVar) {
        Object objK = K();
        return (objK == kotlinx.coroutines.channels.b.f7544d || (objK instanceof r)) ? N(1, dVar) : objK;
    }

    @Override // kotlinx.coroutines.channels.c
    protected d0<E> u() {
        d0<E> d0VarU = super.u();
        if (d0VarU != null && !(d0VarU instanceof r)) {
            I();
        }
        return d0VarU;
    }

    public final boolean z(Throwable th) {
        boolean zClose = close(th);
        H(zClose);
        return zClose;
    }
}
