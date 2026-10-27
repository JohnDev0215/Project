package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.flow.FlowKt__DelayKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import r5.p;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1", f = "Delay.kt", l = {352}, m = "invokeSuspend")
final class FlowKt__DelayKt$sample$2$values$1 extends l implements p<a0<? super Object>, d<? super r>, Object> {
    Object L$0;
    Object L$1;
    int label;
    private a0 p$;
    final /* synthetic */ FlowKt__DelayKt.C01852 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$sample$2$values$1(FlowKt__DelayKt.C01852 c01852, d dVar) {
        super(2, dVar);
        this.this$0 = c01852;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        FlowKt__DelayKt$sample$2$values$1 flowKt__DelayKt$sample$2$values$1 = new FlowKt__DelayKt$sample$2$values$1(this.this$0, dVar);
        flowKt__DelayKt$sample$2$values$1.p$ = (a0) obj;
        return flowKt__DelayKt$sample$2$values$1;
    }

    @Override // r5.p
    public final Object invoke(a0<? super Object> a0Var, d<? super r> dVar) {
        return ((FlowKt__DelayKt$sample$2$values$1) create(a0Var, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            final a0 a0Var = this.p$;
            Flow flow = this.this$0.$this_sample;
            Object obj2 = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1$invokeSuspend$$inlined$collect$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public Object emit(Object obj3, d dVar) {
                    a0 a0Var2 = a0Var;
                    if (obj3 == null) {
                        obj3 = NullSurrogateKt.NULL;
                    }
                    Object objSend = a0Var2.send(obj3, dVar);
                    return objSend == l5.d.d() ? objSend : r.f6563a;
                }
            };
            this.L$0 = a0Var;
            this.L$1 = flow;
            this.label = 1;
            if (flow.collect(obj2, this) == objD) {
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
