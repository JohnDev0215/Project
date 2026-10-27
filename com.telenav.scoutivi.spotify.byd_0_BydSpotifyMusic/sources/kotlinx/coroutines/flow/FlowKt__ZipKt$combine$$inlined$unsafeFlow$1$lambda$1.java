package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.k;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: Zip.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1<R> extends l implements q<FlowCollector<? super R>, Object[], d<? super r>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private FlowCollector p$;
    private Object[] p$0;
    final /* synthetic */ FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1(d dVar, FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1) {
        super(3, dVar);
        this.this$0 = flowKt__ZipKt$combine$$inlined$unsafeFlow$1;
    }

    public final d<r> create(FlowCollector<? super R> flowCollector, Object[] objArr, d<? super r> dVar) {
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1(dVar, this.this$0);
        flowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1.p$ = flowCollector;
        flowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1.p$0 = objArr;
        return flowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1;
    }

    @Override // r5.q
    public final Object invoke(Object obj, Object[] objArr, d<? super r> dVar) {
        return ((FlowKt__ZipKt$combine$$inlined$unsafeFlow$1$lambda$1) create((FlowCollector) obj, objArr, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        FlowCollector flowCollector;
        FlowCollector flowCollector2;
        Object[] objArr;
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            flowCollector = this.p$;
            Object[] objArr2 = this.p$0;
            q qVar = this.this$0.$transform$inlined;
            Object obj2 = objArr2[0];
            Object obj3 = objArr2[1];
            this.L$0 = flowCollector;
            this.L$1 = objArr2;
            this.L$2 = flowCollector;
            this.label = 1;
            k.a(6);
            Object objInvoke = qVar.invoke(obj2, obj3, this);
            k.a(7);
            if (objInvoke == objD) {
                return objD;
            }
            flowCollector2 = flowCollector;
            objArr = objArr2;
            obj = objInvoke;
        } else {
            if (i7 != 1) {
                if (i7 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                m.b(obj);
                return r.f6563a;
            }
            flowCollector = (FlowCollector) this.L$2;
            objArr = (Object[]) this.L$1;
            flowCollector2 = (FlowCollector) this.L$0;
            m.b(obj);
        }
        this.L$0 = flowCollector2;
        this.L$1 = objArr;
        this.label = 2;
        if (flowCollector.emit(obj, this) == objD) {
            return objD;
        }
        return r.f6563a;
    }
}
