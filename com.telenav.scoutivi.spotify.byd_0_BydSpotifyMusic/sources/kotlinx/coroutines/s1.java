package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import k5.g;
import kotlinx.coroutines.internal.o;
import kotlinx.coroutines.l1;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public class s1 implements l1, r, z1, kotlinx.coroutines.selects.c {
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(s1.class, Object.class, "_state");
    private volatile Object _parentHandle;
    private volatile Object _state;

    /* JADX INFO: compiled from: JobSupport.kt */
    private static final class a<T> extends k<T> {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private final s1 f7722m;

        public a(k5.d<? super T> dVar, s1 s1Var) {
            super(dVar, 1);
            this.f7722m = s1Var;
        }

        @Override // kotlinx.coroutines.k
        protected String F() {
            return "AwaitContinuation";
        }

        @Override // kotlinx.coroutines.k
        public Throwable w(l1 l1Var) {
            Throwable thD;
            Object state$kotlinx_coroutines_core = this.f7722m.getState$kotlinx_coroutines_core();
            return (!(state$kotlinx_coroutines_core instanceof c) || (thD = ((c) state$kotlinx_coroutines_core).d()) == null) ? state$kotlinx_coroutines_core instanceof y ? ((y) state$kotlinx_coroutines_core).f7851a : l1Var.getCancellationException() : thD;
        }
    }

    /* JADX INFO: compiled from: JobSupport.kt */
    private static final class b extends r1<l1> {

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final s1 f7723j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final c f7724k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private final q f7725l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private final Object f7726m;

        public b(s1 s1Var, c cVar, q qVar, Object obj) {
            super(qVar.f7714j);
            this.f7723j = s1Var;
            this.f7724k = cVar;
            this.f7725l = qVar;
            this.f7726m = obj;
        }

        @Override // kotlinx.coroutines.a0
        public void Q(Throwable th) {
            this.f7723j.continueCompleting(this.f7724k, this.f7725l, this.f7726m);
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
            Q(th);
            return g5.r.f6563a;
        }

        @Override // kotlinx.coroutines.internal.o
        public String toString() {
            return "ChildCompletion[" + this.f7725l + ", " + this.f7726m + ']';
        }
    }

    /* JADX INFO: compiled from: JobSupport.kt */
    private static final class c implements g1 {
        private volatile Object _exceptionsHolder = null;
        private volatile int _isCompleting;
        private volatile Object _rootCause;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final w1 f7727e;

        public c(w1 w1Var, boolean z6, Throwable th) {
            this.f7727e = w1Var;
            this._isCompleting = z6 ? 1 : 0;
            this._rootCause = th;
        }

        private final ArrayList<Throwable> b() {
            return new ArrayList<>(4);
        }

        private final Object c() {
            return this._exceptionsHolder;
        }

        private final void j(Object obj) {
            this._exceptionsHolder = obj;
        }

        public final void a(Throwable th) {
            Throwable thD = d();
            if (thD == null) {
                k(th);
                return;
            }
            if (th == thD) {
                return;
            }
            Object objC = c();
            if (objC == null) {
                j(th);
                return;
            }
            if (!(objC instanceof Throwable)) {
                if (objC instanceof ArrayList) {
                    ((ArrayList) objC).add(th);
                    return;
                }
                throw new IllegalStateException(("State is " + objC).toString());
            }
            if (th == objC) {
                return;
            }
            ArrayList<Throwable> arrayListB = b();
            arrayListB.add(objC);
            arrayListB.add(th);
            g5.r rVar = g5.r.f6563a;
            j(arrayListB);
        }

        public final Throwable d() {
            return (Throwable) this._rootCause;
        }

        public final boolean e() {
            return d() != null;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
        public final boolean f() {
            return this._isCompleting;
        }

        public final boolean g() {
            return c() == t1.f7830e;
        }

        public final List<Throwable> h(Throwable th) {
            ArrayList<Throwable> arrayListB;
            Object objC = c();
            if (objC == null) {
                arrayListB = b();
            } else if (objC instanceof Throwable) {
                ArrayList<Throwable> arrayListB2 = b();
                arrayListB2.add(objC);
                arrayListB = arrayListB2;
            } else {
                if (!(objC instanceof ArrayList)) {
                    throw new IllegalStateException(("State is " + objC).toString());
                }
                arrayListB = (ArrayList) objC;
            }
            Throwable thD = d();
            if (thD != null) {
                arrayListB.add(0, thD);
            }
            if (th != null && (!kotlin.jvm.internal.l.a(th, thD))) {
                arrayListB.add(th);
            }
            j(t1.f7830e);
            return arrayListB;
        }

        public final void i(boolean z6) {
            this._isCompleting = z6 ? 1 : 0;
        }

        @Override // kotlinx.coroutines.g1
        public boolean isActive() {
            return d() == null;
        }

        public final void k(Throwable th) {
            this._rootCause = th;
        }

        @Override // kotlinx.coroutines.g1
        public w1 p() {
            return this.f7727e;
        }

        public String toString() {
            return "Finishing[cancelling=" + e() + ", completing=" + f() + ", rootCause=" + d() + ", exceptions=" + c() + ", list=" + p() + ']';
        }
    }

    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static final class d extends o.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ kotlinx.coroutines.internal.o f7728d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ s1 f7729e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ Object f7730f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(kotlinx.coroutines.internal.o oVar, kotlinx.coroutines.internal.o oVar2, s1 s1Var, Object obj) {
            super(oVar2);
            this.f7728d = oVar;
            this.f7729e = s1Var;
            this.f7730f = obj;
        }

        @Override // kotlinx.coroutines.internal.d
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Object i(kotlinx.coroutines.internal.o oVar) {
            if (this.f7729e.getState$kotlinx_coroutines_core() == this.f7730f) {
                return null;
            }
            return kotlinx.coroutines.internal.n.a();
        }
    }

    /* JADX INFO: compiled from: JobSupport.kt */
    @kotlin.coroutines.jvm.internal.f(c = "kotlinx.coroutines.JobSupport$children$1", f = "JobSupport.kt", l = {949, 951}, m = "invokeSuspend")
    static final class e extends kotlin.coroutines.jvm.internal.k implements r5.p<x5.f<? super r>, k5.d<? super g5.r>, Object> {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private x5.f f7731g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Object f7732h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Object f7733i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        Object f7734j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        Object f7735k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        Object f7736l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        Object f7737m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        int f7738n;

        e(k5.d dVar) {
            super(2, dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final k5.d<g5.r> create(Object obj, k5.d<?> dVar) {
            e eVar = s1.this.new e(dVar);
            eVar.f7731g = (x5.f) obj;
            return eVar;
        }

        @Override // r5.p
        public final Object invoke(x5.f<? super r> fVar, k5.d<? super g5.r> dVar) {
            return ((e) create(fVar, dVar)).invokeSuspend(g5.r.f6563a);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0081 -> B:27:0x009d). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x009a -> B:27:0x009d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
            /*
                r10 = this;
                java.lang.Object r0 = l5.b.d()
                int r1 = r10.f7738n
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L3a
                if (r1 == r3) goto L32
                if (r1 != r2) goto L2a
                java.lang.Object r1 = r10.f7737m
                kotlinx.coroutines.q r1 = (kotlinx.coroutines.q) r1
                java.lang.Object r1 = r10.f7736l
                kotlinx.coroutines.internal.o r1 = (kotlinx.coroutines.internal.o) r1
                java.lang.Object r4 = r10.f7735k
                kotlinx.coroutines.internal.m r4 = (kotlinx.coroutines.internal.m) r4
                java.lang.Object r5 = r10.f7734j
                kotlinx.coroutines.w1 r5 = (kotlinx.coroutines.w1) r5
                java.lang.Object r6 = r10.f7733i
                java.lang.Object r7 = r10.f7732h
                x5.f r7 = (x5.f) r7
                g5.m.b(r11)
                r11 = r10
                goto L9d
            L2a:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L32:
                java.lang.Object r0 = r10.f7732h
                x5.f r0 = (x5.f) r0
                g5.m.b(r11)
                goto La2
            L3a:
                g5.m.b(r11)
                x5.f r11 = r10.f7731g
                kotlinx.coroutines.s1 r1 = kotlinx.coroutines.s1.this
                java.lang.Object r1 = r1.getState$kotlinx_coroutines_core()
                boolean r4 = r1 instanceof kotlinx.coroutines.q
                if (r4 == 0) goto L5b
                r2 = r1
                kotlinx.coroutines.q r2 = (kotlinx.coroutines.q) r2
                kotlinx.coroutines.r r2 = r2.f7714j
                r10.f7732h = r11
                r10.f7733i = r1
                r10.f7738n = r3
                java.lang.Object r11 = r11.b(r2, r10)
                if (r11 != r0) goto La2
                return r0
            L5b:
                boolean r4 = r1 instanceof kotlinx.coroutines.g1
                if (r4 == 0) goto La2
                r4 = r1
                kotlinx.coroutines.g1 r4 = (kotlinx.coroutines.g1) r4
                kotlinx.coroutines.w1 r4 = r4.p()
                if (r4 == 0) goto La2
                java.lang.Object r5 = r4.G()
            */
            //  java.lang.String r6 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            /*
                java.util.Objects.requireNonNull(r5, r6)
                kotlinx.coroutines.internal.o r5 = (kotlinx.coroutines.internal.o) r5
                r7 = r11
                r6 = r1
                r1 = r5
                r11 = r10
                r5 = r4
            L78:
                boolean r8 = kotlin.jvm.internal.l.a(r1, r4)
                r8 = r8 ^ r3
                if (r8 == 0) goto La2
                boolean r8 = r1 instanceof kotlinx.coroutines.q
                if (r8 == 0) goto L9d
                r8 = r1
                kotlinx.coroutines.q r8 = (kotlinx.coroutines.q) r8
                kotlinx.coroutines.r r9 = r8.f7714j
                r11.f7732h = r7
                r11.f7733i = r6
                r11.f7734j = r5
                r11.f7735k = r4
                r11.f7736l = r1
                r11.f7737m = r8
                r11.f7738n = r2
                java.lang.Object r8 = r7.b(r9, r11)
                if (r8 != r0) goto L9d
                return r0
            L9d:
                kotlinx.coroutines.internal.o r1 = r1.H()
                goto L78
            La2:
                g5.r r11 = g5.r.f6563a
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.s1.e.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public s1(boolean z6) {
        this._state = z6 ? t1.f7832g : t1.f7831f;
        this._parentHandle = null;
    }

    private final boolean addLastAtomic(Object obj, w1 w1Var, r1<?> r1Var) {
        int iP;
        d dVar = new d(r1Var, r1Var, this, obj);
        do {
            iP = w1Var.I().P(r1Var, w1Var, dVar);
            if (iP == 1) {
                return true;
            }
        } while (iP != 2);
        return false;
    }

    private final void addSuppressedExceptions(Throwable th, List<? extends Throwable> list) {
        if (list.size() <= 1) {
            return;
        }
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(list.size()));
        for (Throwable th2 : list) {
            if (th2 != th && th2 != th && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                g5.b.a(th, th2);
            }
        }
    }

    private final Object cancelMakeCompleting(Object obj) {
        Object objTryMakeCompleting;
        do {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof g1) || ((state$kotlinx_coroutines_core instanceof c) && ((c) state$kotlinx_coroutines_core).f())) {
                return t1.f7826a;
            }
            objTryMakeCompleting = tryMakeCompleting(state$kotlinx_coroutines_core, new y(createCauseException(obj), false, 2, null));
        } while (objTryMakeCompleting == t1.f7828c);
        return objTryMakeCompleting;
    }

    private final boolean cancelParent(Throwable th) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z6 = th instanceof CancellationException;
        p parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        return (parentHandle$kotlinx_coroutines_core == null || parentHandle$kotlinx_coroutines_core == x1.f7849e) ? z6 : parentHandle$kotlinx_coroutines_core.k(th) || z6;
    }

    private final void completeStateFinalization(g1 g1Var, Object obj) throws Throwable {
        p parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core != null) {
            parentHandle$kotlinx_coroutines_core.dispose();
            setParentHandle$kotlinx_coroutines_core(x1.f7849e);
        }
        if (!(obj instanceof y)) {
            obj = null;
        }
        y yVar = (y) obj;
        Throwable th = yVar != null ? yVar.f7851a : null;
        if (!(g1Var instanceof r1)) {
            w1 w1VarP = g1Var.p();
            if (w1VarP != null) {
                notifyCompletion(w1VarP, th);
                return;
            }
            return;
        }
        try {
            ((r1) g1Var).Q(th);
        } catch (Throwable th2) {
            handleOnCompletionException$kotlinx_coroutines_core(new b0("Exception in completion handler " + g1Var + " for " + this, th2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void continueCompleting(c cVar, q qVar, Object obj) {
        q qVarNextChild = nextChild(qVar);
        if (qVarNextChild == null || !tryWaitForChild(cVar, qVarNextChild, obj)) {
            afterCompletion(finalizeFinishingState(cVar, obj));
        }
    }

    private final Throwable createCauseException(Object obj) {
        if (obj != null ? obj instanceof Throwable : true) {
            return obj != null ? (Throwable) obj : new m1(cancellationExceptionMessage(), null, this);
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        return ((z1) obj).getChildJobCancellationCause();
    }

    public static /* synthetic */ m1 defaultCancellationException$kotlinx_coroutines_core$default(s1 s1Var, String str, Throwable th, int i7, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defaultCancellationException");
        }
        if ((i7 & 1) != 0) {
            str = null;
        }
        if ((i7 & 2) != 0) {
            th = null;
        }
        if (str == null) {
            str = s1Var.cancellationExceptionMessage();
        }
        return new m1(str, th, s1Var);
    }

    private final Object finalizeFinishingState(c cVar, Object obj) throws Throwable {
        boolean zE;
        Throwable finalRootCause;
        y yVar = (y) (!(obj instanceof y) ? null : obj);
        Throwable th = yVar != null ? yVar.f7851a : null;
        synchronized (cVar) {
            zE = cVar.e();
            List<Throwable> listH = cVar.h(th);
            finalRootCause = getFinalRootCause(cVar, listH);
            if (finalRootCause != null) {
                addSuppressedExceptions(finalRootCause, listH);
            }
        }
        if (finalRootCause != null && finalRootCause != th) {
            obj = new y(finalRootCause, false, 2, null);
        }
        if (finalRootCause != null) {
            if (cancelParent(finalRootCause) || handleJobException(finalRootCause)) {
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                ((y) obj).b();
            }
        }
        if (!zE) {
            onCancelling(finalRootCause);
        }
        onCompletionInternal(obj);
        j.a(_state$FU, this, cVar, t1.g(obj));
        completeStateFinalization(cVar, obj);
        return obj;
    }

    private final q firstChild(g1 g1Var) {
        q qVar = (q) (!(g1Var instanceof q) ? null : g1Var);
        if (qVar != null) {
            return qVar;
        }
        w1 w1VarP = g1Var.p();
        if (w1VarP != null) {
            return nextChild(w1VarP);
        }
        return null;
    }

    private final Throwable getExceptionOrNull(Object obj) {
        if (!(obj instanceof y)) {
            obj = null;
        }
        y yVar = (y) obj;
        if (yVar != null) {
            return yVar.f7851a;
        }
        return null;
    }

    private final Throwable getFinalRootCause(c cVar, List<? extends Throwable> list) {
        Object obj = null;
        if (list.isEmpty()) {
            if (cVar.e()) {
                return new m1(cancellationExceptionMessage(), null, this);
            }
            return null;
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (!(((Throwable) next) instanceof CancellationException)) {
                obj = next;
                break;
            }
        }
        Throwable th = (Throwable) obj;
        return th != null ? th : list.get(0);
    }

    private final w1 getOrPromoteCancellingList(g1 g1Var) {
        w1 w1VarP = g1Var.p();
        if (w1VarP != null) {
            return w1VarP;
        }
        if (g1Var instanceof x0) {
            return new w1();
        }
        if (g1Var instanceof r1) {
            promoteSingleToNodeList((r1) g1Var);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + g1Var).toString());
    }

    private final boolean isCancelling(g1 g1Var) {
        return (g1Var instanceof c) && ((c) g1Var).e();
    }

    private final boolean joinInternal() {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof g1)) {
                return false;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return true;
    }

    private final Void loopOnState(r5.l<Object, g5.r> lVar) {
        while (true) {
            lVar.invoke(getState$kotlinx_coroutines_core());
        }
    }

    private final Object makeCancelling(Object obj) throws Throwable {
        Throwable thCreateCauseException = null;
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof c) {
                synchronized (state$kotlinx_coroutines_core) {
                    if (((c) state$kotlinx_coroutines_core).g()) {
                        return t1.f7829d;
                    }
                    boolean zE = ((c) state$kotlinx_coroutines_core).e();
                    if (obj != null || !zE) {
                        if (thCreateCauseException == null) {
                            thCreateCauseException = createCauseException(obj);
                        }
                        ((c) state$kotlinx_coroutines_core).a(thCreateCauseException);
                    }
                    Throwable thD = zE ^ true ? ((c) state$kotlinx_coroutines_core).d() : null;
                    if (thD != null) {
                        notifyCancelling(((c) state$kotlinx_coroutines_core).p(), thD);
                    }
                    return t1.f7826a;
                }
            }
            if (!(state$kotlinx_coroutines_core instanceof g1)) {
                return t1.f7829d;
            }
            if (thCreateCauseException == null) {
                thCreateCauseException = createCauseException(obj);
            }
            g1 g1Var = (g1) state$kotlinx_coroutines_core;
            if (!g1Var.isActive()) {
                Object objTryMakeCompleting = tryMakeCompleting(state$kotlinx_coroutines_core, new y(thCreateCauseException, false, 2, null));
                if (objTryMakeCompleting == t1.f7826a) {
                    throw new IllegalStateException(("Cannot happen in " + state$kotlinx_coroutines_core).toString());
                }
                if (objTryMakeCompleting != t1.f7828c) {
                    return objTryMakeCompleting;
                }
            } else if (tryMakeCancelling(g1Var, thCreateCauseException)) {
                return t1.f7826a;
            }
        }
    }

    private final r1<?> makeNode(r5.l<? super Throwable, g5.r> lVar, boolean z6) {
        if (z6) {
            n1 n1Var = (n1) (lVar instanceof n1 ? lVar : null);
            return n1Var != null ? n1Var : new j1(this, lVar);
        }
        r1<?> r1Var = (r1) (lVar instanceof r1 ? lVar : null);
        return r1Var != null ? r1Var : new k1(this, lVar);
    }

    private final q nextChild(kotlinx.coroutines.internal.o oVar) {
        while (oVar.L()) {
            oVar = oVar.I();
        }
        while (true) {
            oVar = oVar.H();
            if (!oVar.L()) {
                if (oVar instanceof q) {
                    return (q) oVar;
                }
                if (oVar instanceof w1) {
                    return null;
                }
            }
        }
    }

    private final void notifyCancelling(w1 w1Var, Throwable th) throws Throwable {
        onCancelling(th);
        Object objG = w1Var.G();
        Objects.requireNonNull(objG, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        b0 b0Var = null;
        for (kotlinx.coroutines.internal.o oVarH = (kotlinx.coroutines.internal.o) objG; !kotlin.jvm.internal.l.a(oVarH, w1Var); oVarH = oVarH.H()) {
            if (oVarH instanceof n1) {
                r1 r1Var = (r1) oVarH;
                try {
                    r1Var.Q(th);
                } catch (Throwable th2) {
                    if (b0Var != null) {
                        g5.b.a(b0Var, th2);
                    } else {
                        b0Var = new b0("Exception in completion handler " + r1Var + " for " + this, th2);
                        g5.r rVar = g5.r.f6563a;
                    }
                }
            }
        }
        if (b0Var != null) {
            handleOnCompletionException$kotlinx_coroutines_core(b0Var);
        }
        cancelParent(th);
    }

    private final void notifyCompletion(w1 w1Var, Throwable th) throws Throwable {
        Object objG = w1Var.G();
        Objects.requireNonNull(objG, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        b0 b0Var = null;
        for (kotlinx.coroutines.internal.o oVarH = (kotlinx.coroutines.internal.o) objG; !kotlin.jvm.internal.l.a(oVarH, w1Var); oVarH = oVarH.H()) {
            if (oVarH instanceof r1) {
                r1 r1Var = (r1) oVarH;
                try {
                    r1Var.Q(th);
                } catch (Throwable th2) {
                    if (b0Var != null) {
                        g5.b.a(b0Var, th2);
                    } else {
                        b0Var = new b0("Exception in completion handler " + r1Var + " for " + this, th2);
                        g5.r rVar = g5.r.f6563a;
                    }
                }
            }
        }
        if (b0Var != null) {
            handleOnCompletionException$kotlinx_coroutines_core(b0Var);
        }
    }

    private final /* synthetic */ <T extends r1<?>> void notifyHandlers(w1 w1Var, Throwable th) throws Throwable {
        Object objG = w1Var.G();
        Objects.requireNonNull(objG, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        b0 b0Var = null;
        for (kotlinx.coroutines.internal.o oVarH = (kotlinx.coroutines.internal.o) objG; !kotlin.jvm.internal.l.a(oVarH, w1Var); oVarH = oVarH.H()) {
            kotlin.jvm.internal.l.k(3, "T");
            if (oVarH instanceof kotlinx.coroutines.internal.o) {
                r1 r1Var = (r1) oVarH;
                try {
                    r1Var.Q(th);
                } catch (Throwable th2) {
                    if (b0Var != null) {
                        g5.b.a(b0Var, th2);
                    } else {
                        b0Var = new b0("Exception in completion handler " + r1Var + " for " + this, th2);
                        g5.r rVar = g5.r.f6563a;
                    }
                }
            }
        }
        if (b0Var != null) {
            handleOnCompletionException$kotlinx_coroutines_core(b0Var);
        }
    }

    private final void promoteEmptyToNodeList(x0 x0Var) {
        w1 w1Var = new w1();
        Object f1Var = w1Var;
        if (!x0Var.isActive()) {
            f1Var = new f1(w1Var);
        }
        j.a(_state$FU, this, x0Var, f1Var);
    }

    private final void promoteSingleToNodeList(r1<?> r1Var) {
        r1Var.C(new w1());
        j.a(_state$FU, this, r1Var, r1Var.H());
    }

    private final int startInternal(Object obj) {
        if (obj instanceof x0) {
            if (((x0) obj).isActive()) {
                return 0;
            }
            if (!j.a(_state$FU, this, obj, t1.f7832g)) {
                return -1;
            }
            onStartInternal$kotlinx_coroutines_core();
            return 1;
        }
        if (!(obj instanceof f1)) {
            return 0;
        }
        if (!j.a(_state$FU, this, obj, ((f1) obj).p())) {
            return -1;
        }
        onStartInternal$kotlinx_coroutines_core();
        return 1;
    }

    private final String stateString(Object obj) {
        if (!(obj instanceof c)) {
            return obj instanceof g1 ? ((g1) obj).isActive() ? "Active" : "New" : obj instanceof y ? "Cancelled" : "Completed";
        }
        c cVar = (c) obj;
        return cVar.e() ? "Cancelling" : cVar.f() ? "Completing" : "Active";
    }

    public static /* synthetic */ CancellationException toCancellationException$default(s1 s1Var, Throwable th, String str, int i7, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
        }
        if ((i7 & 1) != 0) {
            str = null;
        }
        return s1Var.toCancellationException(th, str);
    }

    private final boolean tryFinalizeSimpleState(g1 g1Var, Object obj) throws Throwable {
        if (!j.a(_state$FU, this, g1Var, t1.g(obj))) {
            return false;
        }
        onCancelling(null);
        onCompletionInternal(obj);
        completeStateFinalization(g1Var, obj);
        return true;
    }

    private final boolean tryMakeCancelling(g1 g1Var, Throwable th) throws Throwable {
        w1 orPromoteCancellingList = getOrPromoteCancellingList(g1Var);
        if (orPromoteCancellingList == null) {
            return false;
        }
        if (!j.a(_state$FU, this, g1Var, new c(orPromoteCancellingList, false, th))) {
            return false;
        }
        notifyCancelling(orPromoteCancellingList, th);
        return true;
    }

    private final Object tryMakeCompleting(Object obj, Object obj2) {
        return !(obj instanceof g1) ? t1.f7826a : ((!(obj instanceof x0) && !(obj instanceof r1)) || (obj instanceof q) || (obj2 instanceof y)) ? tryMakeCompletingSlowPath((g1) obj, obj2) : tryFinalizeSimpleState((g1) obj, obj2) ? obj2 : t1.f7828c;
    }

    private final Object tryMakeCompletingSlowPath(g1 g1Var, Object obj) throws Throwable {
        w1 orPromoteCancellingList = getOrPromoteCancellingList(g1Var);
        if (orPromoteCancellingList == null) {
            return t1.f7828c;
        }
        c cVar = (c) (!(g1Var instanceof c) ? null : g1Var);
        if (cVar == null) {
            cVar = new c(orPromoteCancellingList, false, null);
        }
        synchronized (cVar) {
            if (cVar.f()) {
                return t1.f7826a;
            }
            cVar.i(true);
            if (cVar != g1Var && !j.a(_state$FU, this, g1Var, cVar)) {
                return t1.f7828c;
            }
            boolean zE = cVar.e();
            y yVar = (y) (!(obj instanceof y) ? null : obj);
            if (yVar != null) {
                cVar.a(yVar.f7851a);
            }
            Throwable thD = true ^ zE ? cVar.d() : null;
            g5.r rVar = g5.r.f6563a;
            if (thD != null) {
                notifyCancelling(orPromoteCancellingList, thD);
            }
            q qVarFirstChild = firstChild(g1Var);
            return (qVarFirstChild == null || !tryWaitForChild(cVar, qVarFirstChild, obj)) ? finalizeFinishingState(cVar, obj) : t1.f7827b;
        }
    }

    private final boolean tryWaitForChild(c cVar, q qVar, Object obj) {
        while (l1.a.e(qVar.f7714j, false, false, new b(this, cVar, qVar, obj), 1, null) == x1.f7849e) {
            qVar = nextChild(qVar);
            if (qVar == null) {
                return false;
            }
        }
        return true;
    }

    protected void afterCompletion(Object obj) {
    }

    @Override // kotlinx.coroutines.l1
    public final p attachChild(r rVar) {
        v0 v0VarE = l1.a.e(this, true, false, new q(this, rVar), 2, null);
        Objects.requireNonNull(v0VarE, "null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
        return (p) v0VarE;
    }

    public final Object awaitInternal$kotlinx_coroutines_core(k5.d<Object> dVar) throws Throwable {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof g1)) {
                if (state$kotlinx_coroutines_core instanceof y) {
                    throw ((y) state$kotlinx_coroutines_core).f7851a;
                }
                return t1.h(state$kotlinx_coroutines_core);
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return awaitSuspend(dVar);
    }

    final /* synthetic */ Object awaitSuspend(k5.d<Object> dVar) throws Throwable {
        a aVar = new a(l5.c.c(dVar), this);
        m.a(aVar, invokeOnCompletion(new b2(this, aVar)));
        Object objY = aVar.y();
        if (objY == l5.d.d()) {
            kotlin.coroutines.jvm.internal.h.c(dVar);
        }
        return objY;
    }

    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    public final boolean cancelCoroutine(Throwable th) {
        return cancelImpl$kotlinx_coroutines_core(th);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(Object obj) throws Throwable {
        Object objMakeCancelling = t1.f7826a;
        if (getOnCancelComplete$kotlinx_coroutines_core() && (objMakeCancelling = cancelMakeCompleting(obj)) == t1.f7827b) {
            return true;
        }
        if (objMakeCancelling == t1.f7826a) {
            objMakeCancelling = makeCancelling(obj);
        }
        if (objMakeCancelling == t1.f7826a || objMakeCancelling == t1.f7827b) {
            return true;
        }
        if (objMakeCancelling == t1.f7829d) {
            return false;
        }
        afterCompletion(objMakeCancelling);
        return true;
    }

    public void cancelInternal(Throwable th) throws Throwable {
        cancelImpl$kotlinx_coroutines_core(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean childCancelled(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(th) && getHandlesException$kotlinx_coroutines_core();
    }

    public final m1 defaultCancellationException$kotlinx_coroutines_core(String str, Throwable th) {
        if (str == null) {
            str = cancellationExceptionMessage();
        }
        return new m1(str, th, this);
    }

    @Override // k5.g
    public <R> R fold(R r7, r5.p<? super R, ? super g.b, ? extends R> pVar) {
        return (R) l1.a.c(this, r7, pVar);
    }

    @Override // k5.g.b, k5.g
    public <E extends g.b> E get(g.c<E> cVar) {
        return (E) l1.a.d(this, cVar);
    }

    @Override // kotlinx.coroutines.l1
    public final CancellationException getCancellationException() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof c)) {
            if (state$kotlinx_coroutines_core instanceof g1) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (state$kotlinx_coroutines_core instanceof y) {
                return toCancellationException$default(this, ((y) state$kotlinx_coroutines_core).f7851a, null, 1, null);
            }
            return new m1(m0.a(this) + " has completed normally", null, this);
        }
        Throwable thD = ((c) state$kotlinx_coroutines_core).d();
        if (thD != null) {
            CancellationException cancellationException = toCancellationException(thD, m0.a(this) + " is cancelling");
            if (cancellationException != null) {
                return cancellationException;
            }
        }
        throw new IllegalStateException(("Job is still new or active: " + this).toString());
    }

    @Override // kotlinx.coroutines.z1
    public CancellationException getChildJobCancellationCause() {
        Throwable thD;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof c) {
            thD = ((c) state$kotlinx_coroutines_core).d();
        } else if (state$kotlinx_coroutines_core instanceof y) {
            thD = ((y) state$kotlinx_coroutines_core).f7851a;
        } else {
            if (state$kotlinx_coroutines_core instanceof g1) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + state$kotlinx_coroutines_core).toString());
            }
            thD = null;
        }
        CancellationException cancellationException = (CancellationException) (thD instanceof CancellationException ? thD : null);
        if (cancellationException != null) {
            return cancellationException;
        }
        return new m1("Parent job is " + stateString(state$kotlinx_coroutines_core), thD, this);
    }

    public final x5.d<l1> getChildren() {
        return x5.h.b(new e(null));
    }

    public final Object getCompletedInternal$kotlinx_coroutines_core() throws Throwable {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(!(state$kotlinx_coroutines_core instanceof g1))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        }
        if (state$kotlinx_coroutines_core instanceof y) {
            throw ((y) state$kotlinx_coroutines_core).f7851a;
        }
        return t1.h(state$kotlinx_coroutines_core);
    }

    protected final Throwable getCompletionCause() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof c) {
            Throwable thD = ((c) state$kotlinx_coroutines_core).d();
            if (thD != null) {
                return thD;
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        if (!(state$kotlinx_coroutines_core instanceof g1)) {
            if (state$kotlinx_coroutines_core instanceof y) {
                return ((y) state$kotlinx_coroutines_core).f7851a;
            }
            return null;
        }
        throw new IllegalStateException(("Job is still new or active: " + this).toString());
    }

    protected final boolean getCompletionCauseHandled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof y) && ((y) state$kotlinx_coroutines_core).a();
    }

    public final Throwable getCompletionExceptionOrNull() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof g1)) {
            return getExceptionOrNull(state$kotlinx_coroutines_core);
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    @Override // k5.g.b
    public final g.c<?> getKey() {
        return l1.f7703d;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    public final kotlinx.coroutines.selects.c getOnJoin() {
        return this;
    }

    public final p getParentHandle$kotlinx_coroutines_core() {
        return (p) this._parentHandle;
    }

    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof kotlinx.coroutines.internal.w)) {
                return obj;
            }
            ((kotlinx.coroutines.internal.w) obj).c(this);
        }
    }

    protected boolean handleJobException(Throwable th) {
        return false;
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(Throwable th) throws Throwable {
        throw th;
    }

    public final void initParentJobInternal$kotlinx_coroutines_core(l1 l1Var) {
        if (l1Var == null) {
            setParentHandle$kotlinx_coroutines_core(x1.f7849e);
            return;
        }
        l1Var.start();
        p pVarAttachChild = l1Var.attachChild(this);
        setParentHandle$kotlinx_coroutines_core(pVarAttachChild);
        if (isCompleted()) {
            pVarAttachChild.dispose();
            setParentHandle$kotlinx_coroutines_core(x1.f7849e);
        }
    }

    public final v0 invokeOnCompletion(r5.l<? super Throwable, g5.r> lVar) {
        return invokeOnCompletion(false, true, lVar);
    }

    @Override // kotlinx.coroutines.l1
    public boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof g1) && ((g1) state$kotlinx_coroutines_core).isActive();
    }

    @Override // kotlinx.coroutines.l1
    public final boolean isCancelled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof y) || ((state$kotlinx_coroutines_core instanceof c) && ((c) state$kotlinx_coroutines_core).e());
    }

    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof g1);
    }

    public final boolean isCompletedExceptionally() {
        return getState$kotlinx_coroutines_core() instanceof y;
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    @Override // kotlinx.coroutines.l1
    public final Object join(k5.d<? super g5.r> dVar) throws Throwable {
        if (joinInternal()) {
            Object objJoinSuspend = joinSuspend(dVar);
            return objJoinSuspend == l5.d.d() ? objJoinSuspend : g5.r.f6563a;
        }
        m2.a(dVar.getContext());
        return g5.r.f6563a;
    }

    final /* synthetic */ Object joinSuspend(k5.d<? super g5.r> dVar) throws Throwable {
        k kVar = new k(l5.c.c(dVar), 1);
        kVar.A();
        m.a(kVar, invokeOnCompletion(new c2(this, kVar)));
        Object objY = kVar.y();
        if (objY == l5.d.d()) {
            kotlin.coroutines.jvm.internal.h.c(dVar);
        }
        return objY;
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(Object obj) {
        Object objTryMakeCompleting;
        do {
            objTryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj);
            if (objTryMakeCompleting == t1.f7826a) {
                return false;
            }
            if (objTryMakeCompleting == t1.f7827b) {
                return true;
            }
        } while (objTryMakeCompleting == t1.f7828c);
        afterCompletion(objTryMakeCompleting);
        return true;
    }

    public final Object makeCompletingOnce$kotlinx_coroutines_core(Object obj) {
        Object objTryMakeCompleting;
        do {
            objTryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj);
            if (objTryMakeCompleting == t1.f7826a) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + obj, getExceptionOrNull(obj));
            }
        } while (objTryMakeCompleting == t1.f7828c);
        return objTryMakeCompleting;
    }

    @Override // k5.g
    public k5.g minusKey(g.c<?> cVar) {
        return l1.a.f(this, cVar);
    }

    public String nameString$kotlinx_coroutines_core() {
        return m0.a(this);
    }

    protected void onCancelling(Throwable th) {
    }

    protected void onCompletionInternal(Object obj) {
    }

    public void onStartInternal$kotlinx_coroutines_core() {
    }

    @Override // kotlinx.coroutines.r
    public final void parentCancelled(z1 z1Var) throws Throwable {
        cancelImpl$kotlinx_coroutines_core(z1Var);
    }

    @Override // k5.g
    public k5.g plus(k5.g gVar) {
        return l1.a.g(this, gVar);
    }

    public final <R> void registerSelectClause0(kotlinx.coroutines.selects.f<? super R> fVar, r5.l<? super k5.d<? super R>, ? extends Object> lVar) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (fVar.s()) {
                return;
            }
            if (!(state$kotlinx_coroutines_core instanceof g1)) {
                if (fVar.j()) {
                    b6.b.c(lVar, fVar.i());
                    return;
                }
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        fVar.o(invokeOnCompletion(new e2(this, fVar, lVar)));
    }

    public final <T, R> void registerSelectClause1Internal$kotlinx_coroutines_core(kotlinx.coroutines.selects.f<? super R> fVar, r5.p<? super T, ? super k5.d<? super R>, ? extends Object> pVar) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (fVar.s()) {
                return;
            }
            if (!(state$kotlinx_coroutines_core instanceof g1)) {
                if (fVar.j()) {
                    if (state$kotlinx_coroutines_core instanceof y) {
                        fVar.u(((y) state$kotlinx_coroutines_core).f7851a);
                        return;
                    } else {
                        b6.b.d(pVar, t1.h(state$kotlinx_coroutines_core), fVar.i());
                        return;
                    }
                }
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        fVar.o(invokeOnCompletion(new d2(this, fVar, pVar)));
    }

    public final void removeNode$kotlinx_coroutines_core(r1<?> r1Var) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof r1)) {
                if (!(state$kotlinx_coroutines_core instanceof g1) || ((g1) state$kotlinx_coroutines_core).p() == null) {
                    return;
                }
                r1Var.M();
                return;
            }
            if (state$kotlinx_coroutines_core != r1Var) {
                return;
            }
        } while (!j.a(_state$FU, this, state$kotlinx_coroutines_core, t1.f7832g));
    }

    public final <T, R> void selectAwaitCompletion$kotlinx_coroutines_core(kotlinx.coroutines.selects.f<? super R> fVar, r5.p<? super T, ? super k5.d<? super R>, ? extends Object> pVar) {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof y) {
            fVar.u(((y) state$kotlinx_coroutines_core).f7851a);
        } else {
            b6.a.d(pVar, t1.h(state$kotlinx_coroutines_core), fVar.i(), null, 4, null);
        }
    }

    public final void setParentHandle$kotlinx_coroutines_core(p pVar) {
        this._parentHandle = pVar;
    }

    @Override // kotlinx.coroutines.l1
    public final boolean start() {
        int iStartInternal;
        do {
            iStartInternal = startInternal(getState$kotlinx_coroutines_core());
            if (iStartInternal == 0) {
                return false;
            }
        } while (iStartInternal != 1);
        return true;
    }

    protected final CancellationException toCancellationException(Throwable th, String str) {
        CancellationException m1Var = (CancellationException) (!(th instanceof CancellationException) ? null : th);
        if (m1Var == null) {
            if (str == null) {
                str = cancellationExceptionMessage();
            }
            m1Var = new m1(str, th, this);
        }
        return m1Var;
    }

    public final String toDebugString() {
        return nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}';
    }

    public String toString() {
        return toDebugString() + '@' + m0.b(this);
    }

    public /* synthetic */ boolean cancel(Throwable th) throws Throwable {
        Throwable m1Var;
        if (th == null || (m1Var = toCancellationException$default(this, th, null, 1, null)) == null) {
            m1Var = new m1(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(m1Var);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x007c A[SYNTHETIC] */
    @Override // kotlinx.coroutines.l1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.v0 invokeOnCompletion(boolean r8, boolean r9, r5.l<? super java.lang.Throwable, g5.r> r10) {
        /*
            r7 = this;
            r0 = 0
            r1 = r0
        L2:
            java.lang.Object r2 = r7.getState$kotlinx_coroutines_core()
            boolean r3 = r2 instanceof kotlinx.coroutines.x0
            if (r3 == 0) goto L27
            r3 = r2
            kotlinx.coroutines.x0 r3 = (kotlinx.coroutines.x0) r3
            boolean r4 = r3.isActive()
            if (r4 == 0) goto L23
            if (r1 == 0) goto L16
            goto L1a
        L16:
            kotlinx.coroutines.r1 r1 = r7.makeNode(r10, r8)
        L1a:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = kotlinx.coroutines.s1._state$FU
            boolean r2 = kotlinx.coroutines.j.a(r3, r7, r2, r1)
            if (r2 == 0) goto L2
            return r1
        L23:
            r7.promoteEmptyToNodeList(r3)
            goto L2
        L27:
            boolean r3 = r2 instanceof kotlinx.coroutines.g1
            if (r3 == 0) goto L90
            r3 = r2
            kotlinx.coroutines.g1 r3 = (kotlinx.coroutines.g1) r3
            kotlinx.coroutines.w1 r3 = r3.p()
            if (r3 != 0) goto L3f
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.JobNode<*>"
            java.util.Objects.requireNonNull(r2, r3)
            kotlinx.coroutines.r1 r2 = (kotlinx.coroutines.r1) r2
            r7.promoteSingleToNodeList(r2)
            goto L2
        L3f:
            kotlinx.coroutines.x1 r4 = kotlinx.coroutines.x1.f7849e
            if (r8 == 0) goto L79
            boolean r5 = r2 instanceof kotlinx.coroutines.s1.c
            if (r5 == 0) goto L79
            monitor-enter(r2)
            r5 = r2
            kotlinx.coroutines.s1$c r5 = (kotlinx.coroutines.s1.c) r5     // Catch: java.lang.Throwable -> L76
            java.lang.Throwable r5 = r5.d()     // Catch: java.lang.Throwable -> L76
            if (r5 == 0) goto L5e
            boolean r6 = r10 instanceof kotlinx.coroutines.q     // Catch: java.lang.Throwable -> L76
            if (r6 == 0) goto L72
            r6 = r2
            kotlinx.coroutines.s1$c r6 = (kotlinx.coroutines.s1.c) r6     // Catch: java.lang.Throwable -> L76
            boolean r6 = r6.f()     // Catch: java.lang.Throwable -> L76
            if (r6 != 0) goto L72
        L5e:
            if (r1 == 0) goto L61
            goto L65
        L61:
            kotlinx.coroutines.r1 r1 = r7.makeNode(r10, r8)     // Catch: java.lang.Throwable -> L76
        L65:
            boolean r4 = r7.addLastAtomic(r2, r3, r1)     // Catch: java.lang.Throwable -> L76
            if (r4 != 0) goto L6d
            monitor-exit(r2)
            goto L2
        L6d:
            if (r5 != 0) goto L71
            monitor-exit(r2)
            return r1
        L71:
            r4 = r1
        L72:
            g5.r r6 = g5.r.f6563a     // Catch: java.lang.Throwable -> L76
            monitor-exit(r2)
            goto L7a
        L76:
            r8 = move-exception
            monitor-exit(r2)
            throw r8
        L79:
            r5 = r0
        L7a:
            if (r5 == 0) goto L82
            if (r9 == 0) goto L81
            r10.invoke(r5)
        L81:
            return r4
        L82:
            if (r1 == 0) goto L85
            goto L89
        L85:
            kotlinx.coroutines.r1 r1 = r7.makeNode(r10, r8)
        L89:
            boolean r2 = r7.addLastAtomic(r2, r3, r1)
            if (r2 == 0) goto L2
            return r1
        L90:
            if (r9 == 0) goto La0
            boolean r8 = r2 instanceof kotlinx.coroutines.y
            if (r8 != 0) goto L97
            r2 = r0
        L97:
            kotlinx.coroutines.y r2 = (kotlinx.coroutines.y) r2
            if (r2 == 0) goto L9d
            java.lang.Throwable r0 = r2.f7851a
        L9d:
            r10.invoke(r0)
        La0:
            kotlinx.coroutines.x1 r8 = kotlinx.coroutines.x1.f7849e
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.s1.invokeOnCompletion(boolean, boolean, r5.l):kotlinx.coroutines.v0");
    }

    public l1 plus(l1 l1Var) {
        return l1.a.h(this, l1Var);
    }

    @Override // kotlinx.coroutines.l1, kotlinx.coroutines.channels.g
    public void cancel(CancellationException cancellationException) throws Throwable {
        if (cancellationException == null) {
            cancellationException = new m1(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }
}
