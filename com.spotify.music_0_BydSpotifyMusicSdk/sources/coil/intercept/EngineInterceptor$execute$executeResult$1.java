package coil.intercept;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import p.br1;
import p.c00;
import p.e20;
import p.e60;
import p.et0;
import p.ew;
import p.fe2;
import p.fz0;
import p.ii0;
import p.lq2;
import p.q31;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "coil.intercept.EngineInterceptor$execute$executeResult$1", f = "EngineInterceptor.kt", l = {127}, m = "invokeSuspend")
final class EngineInterceptor$execute$executeResult$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ Ref$ObjectRef<ew> $components;
    final /* synthetic */ ii0 $eventListener;
    final /* synthetic */ Ref$ObjectRef<et0> $fetchResult;
    final /* synthetic */ Object $mappedData;
    final /* synthetic */ Ref$ObjectRef<br1> $options;
    final /* synthetic */ q31 $request;
    int label;
    final /* synthetic */ a this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EngineInterceptor$execute$executeResult$1(a aVar, Ref$ObjectRef ref$ObjectRef, Ref$ObjectRef ref$ObjectRef2, q31 q31Var, Object obj, Ref$ObjectRef ref$ObjectRef3, ii0 ii0Var, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = aVar;
        this.$fetchResult = ref$ObjectRef;
        this.$components = ref$ObjectRef2;
        this.$request = q31Var;
        this.$mappedData = obj;
        this.$options = ref$ObjectRef3;
        this.$eventListener = ii0Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new EngineInterceptor$execute$executeResult$1(this.this$0, this.$fetchResult, this.$components, this.$request, this.$mappedData, this.$options, this.$eventListener, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((EngineInterceptor$execute$executeResult$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            a aVar = this.this$0;
            fe2 fe2Var = (fe2) this.$fetchResult.element;
            ew ewVar = this.$components.element;
            q31 q31Var = this.$request;
            Object obj2 = this.$mappedData;
            br1 br1Var = this.$options.element;
            ii0 ii0Var = this.$eventListener;
            this.label = 1;
            obj = a.b(aVar, fe2Var, ewVar, q31Var, obj2, br1Var, ii0Var, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return obj;
    }
}
