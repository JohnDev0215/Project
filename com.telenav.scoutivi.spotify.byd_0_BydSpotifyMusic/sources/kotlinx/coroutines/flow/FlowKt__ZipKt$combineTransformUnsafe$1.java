package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.k;
import kotlinx.coroutines.flow.internal.CombineKt;
import r5.a;
import r5.p;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: Zip.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1", f = "Zip.kt", l = {273}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combineTransformUnsafe$1<R> extends l implements p<FlowCollector<? super R>, d<? super r>, Object> {
    final /* synthetic */ Flow[] $flows;
    final /* synthetic */ q $transform;
    Object L$0;
    int label;
    private FlowCollector p$;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Zip.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1$1", f = "Zip.kt", l = {273}, m = "invokeSuspend")
    public static final class AnonymousClass1<T> extends l implements q<FlowCollector<? super R>, T[], d<? super r>, Object> {
        Object L$0;
        Object L$1;
        int label;
        private FlowCollector p$;
        private Object[] p$0;

        public AnonymousClass1(d dVar) {
            super(3, dVar);
        }

        public final d<r> create(FlowCollector<? super R> flowCollector, T[] tArr, d<? super r> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            anonymousClass1.p$ = flowCollector;
            anonymousClass1.p$0 = tArr;
            return anonymousClass1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r5.q
        public final Object invoke(Object obj, Object obj2, d<? super r> dVar) {
            return ((AnonymousClass1) create((FlowCollector) obj, (Object[]) obj2, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                FlowCollector flowCollector = this.p$;
                Object[] objArr = this.p$0;
                q qVar = FlowKt__ZipKt$combineTransformUnsafe$1.this.$transform;
                this.L$0 = flowCollector;
                this.L$1 = objArr;
                this.label = 1;
                if (qVar.invoke(flowCollector, objArr, this) == objD) {
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

        public final Object invokeSuspend$$forInline(Object obj) {
            FlowKt__ZipKt$combineTransformUnsafe$1.this.$transform.invoke(this.p$, this.p$0, this);
            return r.f6563a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combineTransformUnsafe$1(Flow[] flowArr, q qVar, d dVar) {
        super(2, dVar);
        this.$flows = flowArr;
        this.$transform = qVar;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        FlowKt__ZipKt$combineTransformUnsafe$1 flowKt__ZipKt$combineTransformUnsafe$1 = new FlowKt__ZipKt$combineTransformUnsafe$1(this.$flows, this.$transform, dVar);
        flowKt__ZipKt$combineTransformUnsafe$1.p$ = (FlowCollector) obj;
        return flowKt__ZipKt$combineTransformUnsafe$1;
    }

    @Override // r5.p
    public final Object invoke(Object obj, d<? super r> dVar) {
        return ((FlowKt__ZipKt$combineTransformUnsafe$1) create(obj, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        if (i7 == 0) {
            m.b(obj);
            FlowCollector flowCollector = this.p$;
            Flow[] flowArr = this.$flows;
            a aVar = FlowKt__ZipKt$nullArrayFactory$1.INSTANCE;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
            this.L$0 = flowCollector;
            this.label = 1;
            if (CombineKt.combineInternal(flowCollector, flowArr, aVar, anonymousClass1, this) == objD) {
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

    public final Object invokeSuspend$$forInline(Object obj) {
        FlowCollector flowCollector = this.p$;
        Flow[] flowArr = this.$flows;
        a aVar = FlowKt__ZipKt$nullArrayFactory$1.INSTANCE;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
        k.a(0);
        CombineKt.combineInternal(flowCollector, flowArr, aVar, anonymousClass1, this);
        k.a(2);
        k.a(1);
        return r.f6563a;
    }
}
