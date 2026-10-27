package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.k;
import r5.p;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [R, T] */
/* JADX INFO: compiled from: Zip.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2", f = "Zip.kt", l = {238, 238}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2<R, T> extends l implements q<FlowCollector<? super R>, T[], d<? super r>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private FlowCollector p$;
    private Object[] p$0;
    final /* synthetic */ FlowKt__ZipKt$combine$$inlined$unsafeFlow$2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2(d dVar, FlowKt__ZipKt$combine$$inlined$unsafeFlow$2 flowKt__ZipKt$combine$$inlined$unsafeFlow$2) {
        super(3, dVar);
        this.this$0 = flowKt__ZipKt$combine$$inlined$unsafeFlow$2;
    }

    public final d<r> create(FlowCollector<? super R> flowCollector, T[] tArr, d<? super r> dVar) {
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2 flowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2(dVar, this.this$0);
        flowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2.p$ = flowCollector;
        flowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2.p$0 = tArr;
        return flowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r5.q
    public final Object invoke(Object obj, Object obj2, d<? super r> dVar) {
        return ((FlowKt__ZipKt$combine$$inlined$unsafeFlow$2$lambda$2) create((FlowCollector) obj, (Object[]) obj2, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        FlowCollector flowCollector;
        Object[] objArr;
        FlowCollector flowCollector2;
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            FlowCollector flowCollector3 = this.p$;
            Object[] objArr2 = this.p$0;
            p pVar = this.this$0.$transform$inlined;
            this.L$0 = flowCollector3;
            this.L$1 = objArr2;
            this.L$2 = flowCollector3;
            this.label = 1;
            Object objInvoke = pVar.invoke(objArr2, this);
            if (objInvoke == objD) {
                return objD;
            }
            flowCollector = flowCollector3;
            objArr = objArr2;
            obj = objInvoke;
            flowCollector2 = flowCollector3;
        } else {
            if (i7 != 1) {
                if (i7 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                m.b(obj);
                return r.f6563a;
            }
            FlowCollector flowCollector4 = (FlowCollector) this.L$2;
            objArr = (Object[]) this.L$1;
            FlowCollector flowCollector5 = (FlowCollector) this.L$0;
            m.b(obj);
            flowCollector2 = flowCollector4;
            flowCollector = flowCollector5;
        }
        this.L$0 = flowCollector;
        this.L$1 = objArr;
        this.label = 2;
        if (flowCollector2.emit(obj, this) == objD) {
            return objD;
        }
        return r.f6563a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object invokeSuspend$$forInline(Object obj) {
        FlowCollector flowCollector = this.p$;
        Object objInvoke = this.this$0.$transform$inlined.invoke(this.p$0, this);
        k.a(0);
        flowCollector.emit(objInvoke, this);
        k.a(2);
        k.a(1);
        return r.f6563a;
    }
}
