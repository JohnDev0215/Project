package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.flow.internal.ChildCancelledException;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import r5.p;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1 extends l implements p<Object, d<? super r>, Object> {
    final /* synthetic */ FlowCollector $downstream$inlined;
    final /* synthetic */ kotlin.jvm.internal.r $lastValue$inlined;
    final /* synthetic */ c0 $ticker$inlined;
    final /* synthetic */ kotlin.jvm.internal.r $values$inlined;
    int label;
    private Object p$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1(d dVar, kotlin.jvm.internal.r rVar, c0 c0Var, kotlin.jvm.internal.r rVar2, FlowCollector flowCollector) {
        super(2, dVar);
        this.$values$inlined = rVar;
        this.$ticker$inlined = c0Var;
        this.$lastValue$inlined = rVar2;
        this.$downstream$inlined = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1 flowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1 = new FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1(dVar, this.$values$inlined, this.$ticker$inlined, this.$lastValue$inlined, this.$downstream$inlined);
        flowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1.p$0 = obj;
        return flowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1;
    }

    @Override // r5.p
    public final Object invoke(Object obj, d<? super r> dVar) {
        return ((FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1) create(obj, dVar)).invokeSuspend(r.f6563a);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, kotlinx.coroutines.internal.c0] */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        l5.d.d();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        m.b(obj);
        ?? r22 = this.p$0;
        if (r22 == 0) {
            this.$ticker$inlined.cancel(new ChildCancelledException());
            this.$lastValue$inlined.f7508e = NullSurrogateKt.DONE;
        } else {
            this.$lastValue$inlined.f7508e = r22;
        }
        return r.f6563a;
    }
}
