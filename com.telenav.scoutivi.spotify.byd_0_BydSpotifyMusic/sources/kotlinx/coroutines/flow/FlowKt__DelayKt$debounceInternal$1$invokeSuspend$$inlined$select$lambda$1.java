package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.q;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.c0;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$1 extends l implements r5.l<d<? super r>, Object> {
    final /* synthetic */ FlowCollector $downstream$inlined;
    final /* synthetic */ kotlin.jvm.internal.r $lastValue$inlined;
    final /* synthetic */ q $timeoutMillis$inlined;
    final /* synthetic */ kotlin.jvm.internal.r $values$inlined;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$1(d dVar, kotlin.jvm.internal.r rVar, q qVar, FlowCollector flowCollector, kotlin.jvm.internal.r rVar2) {
        super(1, dVar);
        this.$lastValue$inlined = rVar;
        this.$timeoutMillis$inlined = qVar;
        this.$downstream$inlined = flowCollector;
        this.$values$inlined = rVar2;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(d<?> dVar) {
        return new FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$1(dVar, this.$lastValue$inlined, this.$timeoutMillis$inlined, this.$downstream$inlined, this.$values$inlined);
    }

    @Override // r5.l
    public final Object invoke(d<? super r> dVar) {
        return ((FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$1) create(dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            FlowCollector flowCollector = this.$downstream$inlined;
            c0 c0Var = NullSurrogateKt.NULL;
            Object obj2 = this.$lastValue$inlined.f7508e;
            if (obj2 == c0Var) {
                obj2 = null;
            }
            this.label = 1;
            if (flowCollector.emit(obj2, this) == objD) {
                return objD;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            m.b(obj);
        }
        this.$lastValue$inlined.f7508e = null;
        return r.f6563a;
    }
}
