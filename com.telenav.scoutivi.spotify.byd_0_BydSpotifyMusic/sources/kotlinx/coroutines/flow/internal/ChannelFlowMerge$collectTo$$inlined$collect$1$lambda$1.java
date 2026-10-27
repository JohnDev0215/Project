package kotlinx.coroutines.flow.internal;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.h0;
import r5.p;

/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes.dex */
final class ChannelFlowMerge$collectTo$$inlined$collect$1$lambda$1 extends l implements p<h0, d<? super r>, Object> {
    final /* synthetic */ Flow $inner;
    Object L$0;
    int label;
    private h0 p$;
    final /* synthetic */ ChannelFlowMerge$collectTo$$inlined$collect$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelFlowMerge$collectTo$$inlined$collect$1$lambda$1(Flow flow, d dVar, ChannelFlowMerge$collectTo$$inlined$collect$1 channelFlowMerge$collectTo$$inlined$collect$1) {
        super(2, dVar);
        this.$inner = flow;
        this.this$0 = channelFlowMerge$collectTo$$inlined$collect$1;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        ChannelFlowMerge$collectTo$$inlined$collect$1$lambda$1 channelFlowMerge$collectTo$$inlined$collect$1$lambda$1 = new ChannelFlowMerge$collectTo$$inlined$collect$1$lambda$1(this.$inner, dVar, this.this$0);
        channelFlowMerge$collectTo$$inlined$collect$1$lambda$1.p$ = (h0) obj;
        return channelFlowMerge$collectTo$$inlined$collect$1$lambda$1;
    }

    @Override // r5.p
    public final Object invoke(h0 h0Var, d<? super r> dVar) {
        return ((ChannelFlowMerge$collectTo$$inlined$collect$1$lambda$1) create(h0Var, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        try {
            if (i7 == 0) {
                m.b(obj);
                h0 h0Var = this.p$;
                Flow flow = this.$inner;
                SendingCollector sendingCollector = this.this$0.$collector$inlined;
                this.L$0 = h0Var;
                this.label = 1;
                if (flow.collect(sendingCollector, this) == objD) {
                    return objD;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                m.b(obj);
            }
            this.this$0.$semaphore$inlined.a();
            return r.f6563a;
        } catch (Throwable th) {
            this.this$0.$semaphore$inlined.a();
            throw th;
        }
    }
}
