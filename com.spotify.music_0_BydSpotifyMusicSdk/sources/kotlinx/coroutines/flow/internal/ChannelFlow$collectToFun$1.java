package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e60;
import p.fz0;
import p.lq2;
import p.sx1;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.flow.internal.ChannelFlow$collectToFun$1", f = "ChannelFlow.kt", l = {60}, m = "invokeSuspend")
final class ChannelFlow$collectToFun$1 extends SuspendLambda implements fz0 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ a this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlow$collectToFun$1(a aVar, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = aVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        ChannelFlow$collectToFun$1 channelFlow$collectToFun$1 = new ChannelFlow$collectToFun$1(this.this$0, c00Var);
        channelFlow$collectToFun$1.L$0 = obj;
        return channelFlow$collectToFun$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((ChannelFlow$collectToFun$1) a((sx1) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            sx1 sx1Var = (sx1) this.L$0;
            a aVar = this.this$0;
            this.label = 1;
            if (aVar.b(sx1Var, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return lq2.a;
    }
}
