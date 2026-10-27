package kotlinx.coroutines.flow.internal;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.a0;
import r5.p;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.internal.ChannelFlow$collectToFun$1", f = "ChannelFlow.kt", l = {60}, m = "invokeSuspend")
final class ChannelFlow$collectToFun$1<T> extends l implements p<a0<? super T>, d<? super r>, Object> {
    Object L$0;
    int label;
    private a0 p$0;
    final /* synthetic */ ChannelFlow this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelFlow$collectToFun$1(ChannelFlow channelFlow, d dVar) {
        super(2, dVar);
        this.this$0 = channelFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        ChannelFlow$collectToFun$1 channelFlow$collectToFun$1 = new ChannelFlow$collectToFun$1(this.this$0, dVar);
        channelFlow$collectToFun$1.p$0 = (a0) obj;
        return channelFlow$collectToFun$1;
    }

    @Override // r5.p
    public final Object invoke(Object obj, d<? super r> dVar) {
        return ((ChannelFlow$collectToFun$1) create(obj, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            a0<? super T> a0Var = this.p$0;
            ChannelFlow channelFlow = this.this$0;
            this.L$0 = a0Var;
            this.label = 1;
            if (channelFlow.collectTo(a0Var, this) == objD) {
                return objD;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            m.b(obj);
        }
        return r.f6563a;
    }
}
