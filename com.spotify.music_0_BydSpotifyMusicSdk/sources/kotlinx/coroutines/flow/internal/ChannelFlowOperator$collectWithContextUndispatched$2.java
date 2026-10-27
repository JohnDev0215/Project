package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.ar;
import p.c00;
import p.e60;
import p.fv0;
import p.fz0;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2", f = "ChannelFlow.kt", l = {152}, m = "invokeSuspend")
final class ChannelFlowOperator$collectWithContextUndispatched$2 extends SuspendLambda implements fz0 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ b this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlowOperator$collectWithContextUndispatched$2(b bVar, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = bVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        ChannelFlowOperator$collectWithContextUndispatched$2 channelFlowOperator$collectWithContextUndispatched$2 = new ChannelFlowOperator$collectWithContextUndispatched$2(this.this$0, c00Var);
        channelFlowOperator$collectWithContextUndispatched$2.L$0 = obj;
        return channelFlowOperator$collectWithContextUndispatched$2;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((ChannelFlowOperator$collectWithContextUndispatched$2) a((fv0) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        lq2 lq2Var = lq2.a;
        if (i == 0) {
            kotlin.a.d(obj);
            fv0 fv0Var = (fv0) this.L$0;
            b bVar = this.this$0;
            this.label = 1;
            Object objC = ((ar) bVar).l.c(fv0Var, this);
            if (objC != coroutineSingletons) {
                objC = lq2Var;
            }
            if (objC == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return lq2Var;
    }
}
