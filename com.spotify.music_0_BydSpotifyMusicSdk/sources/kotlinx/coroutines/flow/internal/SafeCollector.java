package kotlinx.coroutines.flow.internal;

import kotlin.Result;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.g;
import p.c00;
import p.f20;
import p.fs;
import p.fv0;
import p.fz0;
import p.hz0;
import p.lq2;
import p.mn1;
import p.o72;
import p.p61;
import p.qo;
import p.v03;
import p.w10;
import p.wa0;
import p.x10;
import p.y10;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class SafeCollector<T> extends ContinuationImpl implements fv0 {
    public final y10 collectContext;
    public final int collectContextSize;
    public final fv0 collector;
    private c00<? super lq2> completion;
    private y10 lastEmissionContext;

    public SafeCollector(fv0 fv0Var, y10 y10Var) {
        super(mn1.c, EmptyCoroutineContext.c);
        this.collector = fv0Var;
        this.collectContext = y10Var;
        this.collectContextSize = ((Number) y10Var.c(0, new fz0() { // from class: kotlinx.coroutines.flow.internal.SafeCollector$collectContextSize$1
            @Override // p.fz0
            public final Object g(Object obj, Object obj2) {
                return Integer.valueOf(((Number) obj).intValue() + 1);
            }
        })).intValue();
    }

    @Override // p.fv0
    public final Object b(Object obj, c00 c00Var) {
        try {
            Object objL = l(c00Var, obj);
            return objL == CoroutineSingletons.c ? objL : lq2.a;
        } catch (Throwable th) {
            this.lastEmissionContext = new wa0(c00Var.getContext(), th);
            throw th;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, p.f20
    public final f20 f() {
        c00<? super lq2> c00Var = this.completion;
        if (c00Var instanceof f20) {
            return (f20) c00Var;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, p.c00
    public final y10 getContext() {
        y10 y10Var = this.lastEmissionContext;
        return y10Var == null ? EmptyCoroutineContext.c : y10Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final StackTraceElement h() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        Throwable thA = Result.a(obj);
        if (thA != null) {
            this.lastEmissionContext = new wa0(getContext(), thA);
        }
        c00<? super lq2> c00Var = this.completion;
        if (c00Var != null) {
            c00Var.k(obj);
        }
        return CoroutineSingletons.c;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final void j() {
        super.j();
    }

    public final Object l(c00 c00Var, Object obj) {
        y10 context = c00Var.getContext();
        zu.a0(context);
        y10 y10Var = this.lastEmissionContext;
        if (y10Var != context) {
            if (y10Var instanceof wa0) {
                throw new IllegalStateException(kotlin.text.a.W("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((wa0) y10Var).c + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
            }
            if (((Number) context.c(0, new fz0() { // from class: kotlinx.coroutines.flow.internal.SafeCollector_commonKt$checkContext$result$1
                {
                    super(2);
                }

                @Override // p.fz0
                public final Object g(Object obj2, Object obj3) {
                    int iIntValue = ((Number) obj2).intValue();
                    w10 w10Var = (w10) obj3;
                    x10 key = w10Var.getKey();
                    w10 w10VarE0 = this.$this_checkContext.collectContext.E0(key);
                    if (key != qo.o) {
                        return Integer.valueOf(w10Var != w10VarE0 ? Integer.MIN_VALUE : iIntValue + 1);
                    }
                    p61 p61Var = (p61) w10VarE0;
                    p61 parent = (p61) w10Var;
                    while (true) {
                        if (parent != null) {
                            if (parent == p61Var || !(parent instanceof o72)) {
                                break;
                            }
                            fs fsVar = (fs) g.e.get((g) parent);
                            parent = fsVar != null ? fsVar.getParent() : null;
                        } else {
                            parent = null;
                            break;
                        }
                    }
                    if (parent == p61Var) {
                        if (p61Var != null) {
                            iIntValue++;
                        }
                        return Integer.valueOf(iIntValue);
                    }
                    throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + parent + ", expected child of " + p61Var + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
                }
            })).intValue() != this.collectContextSize) {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.collectContext + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
            this.lastEmissionContext = context;
        }
        this.completion = c00Var;
        hz0 hz0Var = e.a;
        fv0 fv0Var = this.collector;
        v03.g(fv0Var, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        Object objE = hz0Var.e(fv0Var, obj, this);
        if (!v03.b(objE, CoroutineSingletons.c)) {
            this.completion = null;
        }
        return objE;
    }
}
