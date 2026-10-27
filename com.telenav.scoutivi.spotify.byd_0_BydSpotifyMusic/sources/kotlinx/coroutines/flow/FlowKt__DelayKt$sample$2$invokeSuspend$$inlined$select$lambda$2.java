package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import r5.p;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2 extends l implements p<r, d<? super r>, Object> {
    final /* synthetic */ FlowCollector $downstream$inlined;
    final /* synthetic */ kotlin.jvm.internal.r $lastValue$inlined;
    final /* synthetic */ c0 $ticker$inlined;
    final /* synthetic */ kotlin.jvm.internal.r $values$inlined;
    Object L$0;
    Object L$1;
    int label;
    private r p$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2(d dVar, kotlin.jvm.internal.r rVar, c0 c0Var, kotlin.jvm.internal.r rVar2, FlowCollector flowCollector) {
        super(2, dVar);
        this.$values$inlined = rVar;
        this.$ticker$inlined = c0Var;
        this.$lastValue$inlined = rVar2;
        this.$downstream$inlined = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2 flowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2 = new FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2(dVar, this.$values$inlined, this.$ticker$inlined, this.$lastValue$inlined, this.$downstream$inlined);
        flowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2.p$0 = (r) obj;
        return flowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2;
    }

    @Override // r5.p
    public final Object invoke(r rVar, d<? super r> dVar) {
        return ((FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2) create(rVar, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            r rVar = this.p$0;
            kotlin.jvm.internal.r rVar2 = this.$lastValue$inlined;
            Object obj2 = rVar2.f7508e;
            if (obj2 == null) {
                return r.f6563a;
            }
            rVar2.f7508e = null;
            FlowCollector flowCollector = this.$downstream$inlined;
            Object obj3 = obj2 != NullSurrogateKt.NULL ? obj2 : null;
            this.L$0 = rVar;
            this.L$1 = obj2;
            this.label = 1;
            if (flowCollector.emit(obj3, this) == objD) {
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
