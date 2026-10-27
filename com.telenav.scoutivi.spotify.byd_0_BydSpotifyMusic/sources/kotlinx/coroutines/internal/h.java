package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.h2;
import kotlinx.coroutines.m0;
import kotlinx.coroutines.s0;
import kotlinx.coroutines.y0;

/* JADX INFO: compiled from: DispatchedContinuation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h<T> extends s0<T> implements kotlin.coroutines.jvm.internal.e, k5.d<T> {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7640n = AtomicReferenceFieldUpdater.newUpdater(h.class, Object.class, "_reusableCancellableContinuation");
    private volatile Object _reusableCancellableContinuation;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f7641i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final kotlin.coroutines.jvm.internal.e f7642j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Object f7643k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlinx.coroutines.e0 f7644l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k5.d<T> f7645m;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [k5.d<? super T>, k5.d<T>] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type k5.d<? super T> to ?? for r3v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.instructions.args.InsnArg.wrapInstruction(InsnArg.java:138)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.inline(CodeShrinkVisitor.java:213)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:143)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:68)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:48)
        	at jadx.core.dex.visitors.regions.TernaryMod.replaceWithTernary(TernaryMod.java:347)
        	at jadx.core.dex.visitors.regions.TernaryMod.processOneBranchTernary(TernaryMod.java:273)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:77)
        	at jadx.core.dex.visitors.regions.TernaryMod.processRegion(TernaryMod.java:62)
        	at jadx.core.dex.visitors.regions.TernaryMod.enterRegion(TernaryMod.java:45)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:67)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.TernaryMod.process(TernaryMod.java:35)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.process(IfRegionVisitor.java:44)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:30)
        */
    public h(kotlinx.coroutines.e0 r2, k5.d<? super T> r3) {
        /*
            r1 = this;
            r0 = -1
            r1.<init>(r0)
            r1.f7644l = r2
            r1.f7645m = r3
            kotlinx.coroutines.internal.c0 r2 = kotlinx.coroutines.internal.i.a()
            r1.f7641i = r2
            boolean r2 = r3 instanceof kotlin.coroutines.jvm.internal.e
            r0 = 0
            if (r2 != 0) goto L14
            r3 = r0
        L14:
            kotlin.coroutines.jvm.internal.e r3 = (kotlin.coroutines.jvm.internal.e) r3
            r1.f7642j = r3
            k5.g r2 = r1.getContext()
            java.lang.Object r2 = kotlinx.coroutines.internal.g0.b(r2)
            r1.f7643k = r2
            r1._reusableCancellableContinuation = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.h.<init>(kotlinx.coroutines.e0, k5.d):void");
    }

    @Override // kotlinx.coroutines.s0
    public void b(Object obj, Throwable th) {
        if (obj instanceof kotlinx.coroutines.z) {
            ((kotlinx.coroutines.z) obj).f7856b.invoke(th);
        }
    }

    @Override // kotlinx.coroutines.s0
    public k5.d<T> c() {
        return this;
    }

    @Override // k5.d
    public k5.g getContext() {
        return this.f7645m.getContext();
    }

    @Override // kotlinx.coroutines.s0
    public Object j() {
        Object obj = this.f7641i;
        this.f7641i = i.f7647a;
        return obj;
    }

    public final Throwable k(kotlinx.coroutines.i<?> iVar) {
        c0 c0Var;
        do {
            Object obj = this._reusableCancellableContinuation;
            c0Var = i.f7648b;
            if (obj != c0Var) {
                if (obj == null) {
                    return null;
                }
                if (obj instanceof Throwable) {
                    if (kotlinx.coroutines.j.a(f7640n, this, obj, null)) {
                        return (Throwable) obj;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!kotlinx.coroutines.j.a(f7640n, this, c0Var, iVar));
        return null;
    }

    public final kotlinx.coroutines.k<T> l() {
        Object obj;
        do {
            obj = this._reusableCancellableContinuation;
            if (obj == null) {
                this._reusableCancellableContinuation = i.f7648b;
                return null;
            }
            if (!(obj instanceof kotlinx.coroutines.k)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!kotlinx.coroutines.j.a(f7640n, this, obj, i.f7648b));
        return (kotlinx.coroutines.k) obj;
    }

    public final void m(k5.g gVar, T t6) {
        this.f7641i = t6;
        this.f7721h = 1;
        this.f7644l.b0(gVar, this);
    }

    public final kotlinx.coroutines.k<?> n() {
        Object obj = this._reusableCancellableContinuation;
        if (!(obj instanceof kotlinx.coroutines.k)) {
            obj = null;
        }
        return (kotlinx.coroutines.k) obj;
    }

    public final boolean o(kotlinx.coroutines.k<?> kVar) {
        Object obj = this._reusableCancellableContinuation;
        if (obj != null) {
            return !(obj instanceof kotlinx.coroutines.k) || obj == kVar;
        }
        return false;
    }

    public final boolean p(Throwable th) {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            c0 c0Var = i.f7648b;
            if (kotlin.jvm.internal.l.a(obj, c0Var)) {
                if (kotlinx.coroutines.j.a(f7640n, this, c0Var, th)) {
                    return true;
                }
            } else {
                if (obj instanceof Throwable) {
                    return true;
                }
                if (kotlinx.coroutines.j.a(f7640n, this, obj, null)) {
                    return false;
                }
            }
        }
    }

    @Override // k5.d
    public void resumeWith(Object obj) {
        k5.g context = this.f7645m.getContext();
        Object objD = kotlinx.coroutines.c0.d(obj, null, 1, null);
        if (this.f7644l.c0(context)) {
            this.f7641i = objD;
            this.f7721h = 0;
            this.f7644l.a0(context, this);
            return;
        }
        y0 y0VarA = h2.f7614b.a();
        if (y0VarA.j0()) {
            this.f7641i = objD;
            this.f7721h = 0;
            y0VarA.f0(this);
            return;
        }
        y0VarA.h0(true);
        try {
            k5.g context2 = getContext();
            Object objC = g0.c(context2, this.f7643k);
            try {
                this.f7645m.resumeWith(obj);
                g5.r rVar = g5.r.f6563a;
                while (y0VarA.l0()) {
                }
            } finally {
                g0.a(context2, objC);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public String toString() {
        return "DispatchedContinuation[" + this.f7644l + ", " + m0.c(this.f7645m) + ']';
    }
}
