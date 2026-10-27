package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.CoroutineStart;
import p.c00;
import p.e20;
import p.e60;
import p.fv0;
import p.fz0;
import p.lq2;
import p.q30;
import p.rx1;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.flow.internal.ChannelFlow$collect$2", f = "ChannelFlow.kt", l = {123}, m = "invokeSuspend")
final class ChannelFlow$collect$2 extends SuspendLambda implements fz0 {
    final /* synthetic */ fv0 $collector;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ a this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlow$collect$2(c00 c00Var, fv0 fv0Var, a aVar) {
        super(2, c00Var);
        this.$collector = fv0Var;
        this.this$0 = aVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        ChannelFlow$collect$2 channelFlow$collect$2 = new ChannelFlow$collect$2(c00Var, this.$collector, this.this$0);
        channelFlow$collect$2.L$0 = obj;
        return channelFlow$collect$2;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((ChannelFlow$collect$2) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        lq2 lq2Var = lq2.a;
        if (i == 0) {
            kotlin.a.d(obj);
            e20 e20Var = (e20) this.L$0;
            fv0 fv0Var = this.$collector;
            a aVar = this.this$0;
            int i2 = aVar.e;
            if (i2 == -3) {
                i2 = -2;
            }
            CoroutineStart coroutineStart = CoroutineStart.k;
            fz0 channelFlow$collectToFun$1 = new ChannelFlow$collectToFun$1(aVar, null);
            rx1 rx1Var = new rx1(kotlinx.coroutines.a.c(e20Var, aVar.c), q30.a(i2, aVar.k, 4));
            rx1Var.a0(coroutineStart, rx1Var, channelFlow$collectToFun$1);
            this.label = 1;
            Object objB = kotlinx.coroutines.flow.d.b(fv0Var, rx1Var, true, this);
            if (objB != coroutineSingletons) {
                objB = lq2Var;
            }
            if (objB == coroutineSingletons) {
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
