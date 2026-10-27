package kotlinx.coroutines.flow.internal;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.flow.FlowCollector;
import r5.p;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.internal.UndispatchedContextCollector$emitRef$1", f = "ChannelFlow.kt", l = {224}, m = "invokeSuspend")
final class UndispatchedContextCollector$emitRef$1<T> extends l implements p<T, d<? super r>, Object> {
    final /* synthetic */ FlowCollector $downstream;
    Object L$0;
    int label;
    private Object p$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UndispatchedContextCollector$emitRef$1(FlowCollector flowCollector, d dVar) {
        super(2, dVar);
        this.$downstream = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        UndispatchedContextCollector$emitRef$1 undispatchedContextCollector$emitRef$1 = new UndispatchedContextCollector$emitRef$1(this.$downstream, dVar);
        undispatchedContextCollector$emitRef$1.p$0 = obj;
        return undispatchedContextCollector$emitRef$1;
    }

    @Override // r5.p
    public final Object invoke(Object obj, d<? super r> dVar) {
        return ((UndispatchedContextCollector$emitRef$1) create(obj, dVar)).invokeSuspend(r.f6563a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            Object obj2 = this.p$0;
            FlowCollector flowCollector = this.$downstream;
            this.L$0 = obj2;
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
        return r.f6563a;
    }
}
