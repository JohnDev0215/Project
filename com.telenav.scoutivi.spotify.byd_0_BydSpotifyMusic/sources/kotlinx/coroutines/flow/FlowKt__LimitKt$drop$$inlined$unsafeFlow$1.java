package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlin.jvm.internal.p;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__LimitKt$drop$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ int $count$inlined;
    final /* synthetic */ Flow $this_drop$inlined;

    public FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(Flow flow, int i7) {
        this.$this_drop$inlined = flow;
        this.$count$inlined = i7;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(final FlowCollector flowCollector, d dVar) {
        final p pVar = new p();
        pVar.f7506e = 0;
        Object objCollect = this.$this_drop$inlined.collect(new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__LimitKt$drop$$inlined$unsafeFlow$1$lambda$1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public Object emit(Object obj, d dVar2) {
                p pVar2 = pVar;
                int i7 = pVar2.f7506e;
                if (i7 >= this.$count$inlined) {
                    Object objEmit = flowCollector.emit(obj, dVar2);
                    if (objEmit == l5.d.d()) {
                        return objEmit;
                    }
                } else {
                    pVar2.f7506e = i7 + 1;
                }
                return r.f6563a;
            }
        }, dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }
}
