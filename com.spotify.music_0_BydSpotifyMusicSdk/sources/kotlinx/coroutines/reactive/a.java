package kotlinx.coroutines.reactive;

import kotlin.a;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import p.c00;
import p.fa0;
import p.he1;
import p.lq2;
import p.ry0;
import p.v03;
import p.vr2;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final class a implements c00 {
    public final /* synthetic */ y10 c;
    public final /* synthetic */ b e;

    public a(y10 y10Var, b bVar) {
        this.c = y10Var;
        this.e = bVar;
    }

    @Override // p.c00
    public final y10 getContext() {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p.c00
    public final void k(Object obj) {
        c00 c00VarC;
        final b bVar = this.e;
        final FlowSubscription$createInitialContinuation$1$1 flowSubscription$createInitialContinuation$1$1 = new FlowSubscription$createInitialContinuation$1$1(1, bVar, b.class, "flowProcessing", "flowProcessing(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
        try {
            v03.j(bVar, "completion");
            if (flowSubscription$createInitialContinuation$1$1 instanceof BaseContinuationImpl) {
                c00VarC = ((BaseContinuationImpl) flowSubscription$createInitialContinuation$1$1).c(bVar);
            } else {
                final y10 y10Var = bVar.k;
                c00VarC = y10Var == EmptyCoroutineContext.c ? new RestrictedContinuationImpl(bVar) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$1
                    private int label;

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object i(Object obj2) {
                        int i = this.label;
                        if (i != 0) {
                            if (i != 1) {
                                throw new IllegalStateException("This coroutine had already completed".toString());
                            }
                            this.label = 2;
                            a.d(obj2);
                            return obj2;
                        }
                        this.label = 1;
                        a.d(obj2);
                        v03.g(flowSubscription$createInitialContinuation$1$1, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
                        ry0 ry0Var = flowSubscription$createInitialContinuation$1$1;
                        he1.d(1, ry0Var);
                        return ry0Var.invoke(this);
                    }
                } : new ContinuationImpl(bVar, y10Var) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$2
                    private int label;

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object i(Object obj2) {
                        int i = this.label;
                        if (i != 0) {
                            if (i != 1) {
                                throw new IllegalStateException("This coroutine had already completed".toString());
                            }
                            this.label = 2;
                            a.d(obj2);
                            return obj2;
                        }
                        this.label = 1;
                        a.d(obj2);
                        v03.g(flowSubscription$createInitialContinuation$1$1, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
                        ry0 ry0Var = flowSubscription$createInitialContinuation$1$1;
                        he1.d(1, ry0Var);
                        return ry0Var.invoke(this);
                    }
                };
            }
            fa0.a(vr2.k(c00VarC), lq2.a, null);
        } catch (Throwable th) {
            bVar.k(kotlin.a.b(th));
            throw th;
        }
    }
}
