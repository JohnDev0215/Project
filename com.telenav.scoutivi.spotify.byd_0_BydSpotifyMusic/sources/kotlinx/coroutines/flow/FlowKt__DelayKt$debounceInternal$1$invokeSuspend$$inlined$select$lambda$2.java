package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.q;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import r5.p;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$2 extends l implements p<Object, d<? super r>, Object> {
    final /* synthetic */ FlowCollector $downstream$inlined;
    final /* synthetic */ kotlin.jvm.internal.r $lastValue$inlined;
    final /* synthetic */ q $timeoutMillis$inlined;
    final /* synthetic */ kotlin.jvm.internal.r $values$inlined;
    Object L$0;
    int label;
    private Object p$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$2(d dVar, kotlin.jvm.internal.r rVar, q qVar, FlowCollector flowCollector, kotlin.jvm.internal.r rVar2) {
        super(2, dVar);
        this.$lastValue$inlined = rVar;
        this.$timeoutMillis$inlined = qVar;
        this.$downstream$inlined = flowCollector;
        this.$values$inlined = rVar2;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$2 flowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$2 = new FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$2(dVar, this.$lastValue$inlined, this.$timeoutMillis$inlined, this.$downstream$inlined, this.$values$inlined);
        flowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$2.p$0 = obj;
        return flowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$2;
    }

    @Override // r5.p
    public final Object invoke(Object obj, d<? super r> dVar) {
        return ((FlowKt__DelayKt$debounceInternal$1$invokeSuspend$$inlined$select$lambda$2) create(obj, dVar)).invokeSuspend(r.f6563a);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, kotlinx.coroutines.internal.c0] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            ?? r62 = this.p$0;
            if (r62 != 0) {
                this.$lastValue$inlined.f7508e = r62;
                return r.f6563a;
            }
            Object obj2 = this.$lastValue$inlined.f7508e;
            if (obj2 != null) {
                FlowCollector flowCollector = this.$downstream$inlined;
                if (obj2 == NullSurrogateKt.NULL) {
                    obj2 = null;
                }
                this.L$0 = r62;
                this.label = 1;
                if (flowCollector.emit(obj2, this) == objD) {
                    return objD;
                }
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            m.b(obj);
        }
        this.$lastValue$inlined.f7508e = NullSurrogateKt.DONE;
        return r.f6563a;
    }
}
