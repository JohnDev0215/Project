package coil;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.kf0;
import p.lq2;
import p.q31;
import p.r31;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "coil.RealImageLoader$enqueue$job$1", f = "RealImageLoader.kt", l = {117}, m = "invokeSuspend")
final class RealImageLoader$enqueue$job$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ q31 $request;
    int label;
    final /* synthetic */ b this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealImageLoader$enqueue$job$1(b bVar, q31 q31Var, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = bVar;
        this.$request = q31Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new RealImageLoader$enqueue$job$1(this.this$0, this.$request, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((RealImageLoader$enqueue$job$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            b bVar = this.this$0;
            q31 q31Var = this.$request;
            this.label = 1;
            obj = b.a(bVar, q31Var, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        b bVar2 = this.this$0;
        if (((r31) obj) instanceof kf0) {
            bVar2.getClass();
        }
        return obj;
    }
}
