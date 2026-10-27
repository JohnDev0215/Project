package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlin.jvm.internal.k;
import kotlinx.coroutines.flow.internal.CombineKt;
import r5.a;
import r5.p;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1<R> implements Flow<R> {
    final /* synthetic */ Flow[] $flows$inlined;
    final /* synthetic */ p $transform$inlined;

    public FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1(Flow[] flowArr, p pVar) {
        this.$flows$inlined = flowArr;
        this.$transform$inlined = pVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, d dVar) {
        Object objCombineInternal = CombineKt.combineInternal(flowCollector, this.$flows$inlined, FlowKt__ZipKt$nullArrayFactory$1.INSTANCE, new FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1$lambda$1(null, this), dVar);
        return objCombineInternal == l5.d.d() ? objCombineInternal : r.f6563a;
    }

    public Object collect$$forInline(FlowCollector flowCollector, d dVar) {
        k.a(4);
        new kotlin.coroutines.jvm.internal.d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1.this.collect(null, this);
            }
        };
        k.a(5);
        Flow[] flowArr = this.$flows$inlined;
        a aVar = FlowKt__ZipKt$nullArrayFactory$1.INSTANCE;
        FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1$lambda$1 flowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1$lambda$1 = new FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1$lambda$1(null, this);
        k.a(0);
        CombineKt.combineInternal(flowCollector, flowArr, aVar, flowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1$lambda$1, dVar);
        k.a(2);
        k.a(1);
        return r.f6563a;
    }
}
