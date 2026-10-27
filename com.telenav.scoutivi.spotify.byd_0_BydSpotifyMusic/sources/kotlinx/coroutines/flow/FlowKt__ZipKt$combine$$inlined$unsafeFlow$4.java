package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.l;
import kotlinx.coroutines.flow.internal.CombineKt;
import r5.p;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$4<R> implements Flow<R> {
    final /* synthetic */ Flow[] $flowArray$inlined;
    final /* synthetic */ p $transform$inlined;

    public FlowKt__ZipKt$combine$$inlined$unsafeFlow$4(Flow[] flowArr, p pVar) {
        this.$flowArray$inlined = flowArr;
        this.$transform$inlined = pVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, d dVar) {
        Flow[] flowArr = this.$flowArray$inlined;
        l.j();
        l.j();
        Object objCombineInternal = CombineKt.combineInternal(flowCollector, flowArr, new FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$1(this), new FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2(null, this), dVar);
        return objCombineInternal == l5.d.d() ? objCombineInternal : r.f6563a;
    }

    public Object collect$$forInline(FlowCollector flowCollector, d dVar) {
        k.a(4);
        new kotlin.coroutines.jvm.internal.d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$4.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__ZipKt$combine$$inlined$unsafeFlow$4.this.collect(null, this);
            }
        };
        k.a(5);
        Flow[] flowArr = this.$flowArray$inlined;
        l.j();
        l.j();
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$1(this);
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2 flowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2(null, this);
        k.a(0);
        CombineKt.combineInternal(flowCollector, flowArr, flowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$1, flowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2, dVar);
        k.a(2);
        k.a(1);
        return r.f6563a;
    }
}
