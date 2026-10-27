package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import r5.p;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [R, T] */
/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1", f = "Migration.kt", l = {190, 216}, m = "invokeSuspend")
public final class FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1<R, T> extends l implements q<FlowCollector<? super R>, T, d<? super r>, Object> {
    final /* synthetic */ p $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private FlowCollector p$;
    private Object p$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1(p pVar, d dVar) {
        super(3, dVar);
        this.$transform = pVar;
    }

    public final d<r> create(FlowCollector<? super R> flowCollector, T t6, d<? super r> dVar) {
        FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1 flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1 = new FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1(this.$transform, dVar);
        flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1.p$ = flowCollector;
        flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1.p$0 = t6;
        return flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r5.q
    public final Object invoke(Object obj, Object obj2, d<? super r> dVar) {
        return ((FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1) create((FlowCollector) obj, obj2, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        FlowCollector<? super T> flowCollector;
        FlowCollector<? super T> flowCollector2;
        Object obj2;
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            flowCollector = this.p$;
            Object obj3 = this.p$0;
            p pVar = this.$transform;
            this.L$0 = flowCollector;
            this.L$1 = obj3;
            this.L$2 = flowCollector;
            this.label = 1;
            Object objInvoke = pVar.invoke(obj3, this);
            if (objInvoke == objD) {
                return objD;
            }
            flowCollector2 = flowCollector;
            obj2 = obj3;
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
            obj2 = this.L$1;
            flowCollector2 = (FlowCollector) this.L$0;
            m.b(obj);
        }
        Flow flow = (Flow) obj;
        this.L$0 = flowCollector2;
        this.L$1 = obj2;
        this.L$2 = flowCollector;
        this.L$3 = flow;
        this.label = 2;
        if (flow.collect(flowCollector, this) == objD) {
            return objD;
        }
        return r.f6563a;
    }
}
