package kotlinx.coroutines.flow.internal;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.h0;
import r5.p;

/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes.dex */
final class ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1 extends l implements p<h0, d<? super r>, Object> {
    final /* synthetic */ SendingCollector $collector$inlined;
    final /* synthetic */ Flow $flow;
    final /* synthetic */ a0 $scope$inlined;
    Object L$0;
    int label;
    private h0 p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1(Flow flow, d dVar, a0 a0Var, SendingCollector sendingCollector) {
        super(2, dVar);
        this.$flow = flow;
        this.$scope$inlined = a0Var;
        this.$collector$inlined = sendingCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1 channelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1 = new ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1(this.$flow, dVar, this.$scope$inlined, this.$collector$inlined);
        channelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1.p$ = (h0) obj;
        return channelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1;
    }

    @Override // r5.p
    public final Object invoke(h0 h0Var, d<? super r> dVar) {
        return ((ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1) create(h0Var, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            h0 h0Var = this.p$;
            Flow flow = this.$flow;
            SendingCollector sendingCollector = this.$collector$inlined;
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
        return r.f6563a;
    }
}
