package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.b;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.k;
import kotlinx.coroutines.flow.internal.NopCollector;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.l1;
import r5.p;
import r5.q;

/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__CollectKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectKt$collect$3, reason: invalid class name */
    /* JADX INFO: compiled from: Collect.kt */
    public static final class AnonymousClass3<T> implements FlowCollector<T> {
        final /* synthetic */ p $action;

        public AnonymousClass3(p pVar) {
            this.$action = pVar;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public Object emit(T t6, d<? super r> dVar) {
            Object objInvoke = this.$action.invoke(t6, dVar);
            return objInvoke == l5.d.d() ? objInvoke : r.f6563a;
        }

        public Object emit$$forInline(Object obj, final d dVar) {
            k.a(4);
            new kotlin.coroutines.jvm.internal.d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__CollectKt$collect$3$emit$1
                int label;
                /* synthetic */ Object result;

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj2) {
                    this.result = obj2;
                    this.label |= Integer.MIN_VALUE;
                    return this.this$0.emit(null, this);
                }
            };
            k.a(5);
            return this.$action.invoke(obj, dVar);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectKt$collectIndexed$2, reason: invalid class name */
    /* JADX INFO: compiled from: Collect.kt */
    public static final class AnonymousClass2<T> implements FlowCollector<T> {
        final /* synthetic */ q $action;
        private int index;

        public AnonymousClass2(q qVar) {
            this.$action = qVar;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public Object emit(T t6, d<? super r> dVar) {
            q qVar = this.$action;
            int i7 = this.index;
            this.index = i7 + 1;
            if (i7 < 0) {
                throw new ArithmeticException("Index overflow has happened");
            }
            Object objInvoke = qVar.invoke(b.b(i7), t6, dVar);
            return objInvoke == l5.d.d() ? objInvoke : r.f6563a;
        }

        public Object emit$$forInline(Object obj, final d dVar) {
            k.a(4);
            new kotlin.coroutines.jvm.internal.d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__CollectKt$collectIndexed$2$emit$1
                int label;
                /* synthetic */ Object result;

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj2) {
                    this.result = obj2;
                    this.label |= Integer.MIN_VALUE;
                    return this.this$0.emit(null, this);
                }
            };
            k.a(5);
            q qVar = this.$action;
            int i7 = this.index;
            this.index = i7 + 1;
            if (i7 >= 0) {
                return qVar.invoke(Integer.valueOf(i7), obj, dVar);
            }
            throw new ArithmeticException("Index overflow has happened");
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectKt$launchIn$1, reason: invalid class name */
    /* JADX INFO: compiled from: Collect.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__CollectKt$launchIn$1", f = "Collect.kt", l = {50}, m = "invokeSuspend")
    static final class AnonymousClass1 extends l implements p<h0, d<? super r>, Object> {
        final /* synthetic */ Flow $this_launchIn;
        Object L$0;
        int label;
        private h0 p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Flow flow, d dVar) {
            super(2, dVar);
            this.$this_launchIn = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_launchIn, dVar);
            anonymousClass1.p$ = (h0) obj;
            return anonymousClass1;
        }

        @Override // r5.p
        public final Object invoke(h0 h0Var, d<? super r> dVar) {
            return ((AnonymousClass1) create(h0Var, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                h0 h0Var = this.p$;
                Flow flow = this.$this_launchIn;
                this.L$0 = h0Var;
                this.label = 1;
                if (FlowKt.collect(flow, this) == objD) {
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

    public static final Object collect(Flow<?> flow, d<? super r> dVar) {
        Object objCollect = flow.collect(NopCollector.INSTANCE, dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    private static final Object collect$$forInline(Flow flow, p pVar, d dVar) {
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(pVar);
        k.a(0);
        Object objCollect = flow.collect(anonymousClass3, dVar);
        k.a(2);
        k.a(1);
        return objCollect;
    }

    public static final <T> Object collectIndexed(Flow<? extends T> flow, q<? super Integer, ? super T, ? super d<? super r>, ? extends Object> qVar, d<? super r> dVar) {
        Object objCollect = flow.collect(new AnonymousClass2(qVar), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    private static final Object collectIndexed$$forInline(Flow flow, q qVar, d dVar) {
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(qVar);
        k.a(0);
        Object objCollect = flow.collect(anonymousClass2, dVar);
        k.a(2);
        k.a(1);
        return objCollect;
    }

    public static final <T> Object collectLatest(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar, d<? super r> dVar) {
        Object objCollect = FlowKt.collect(FlowKt__ContextKt.buffer$default(FlowKt.mapLatest(flow, pVar), 0, null, 2, null), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    public static final <T> Object emitAll(FlowCollector<? super T> flowCollector, Flow<? extends T> flow, d<? super r> dVar) {
        Object objCollect = flow.collect(flowCollector, dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    private static final Object emitAll$$forInline(FlowCollector flowCollector, Flow flow, d dVar) {
        k.a(0);
        Object objCollect = flow.collect(flowCollector, dVar);
        k.a(2);
        k.a(1);
        return objCollect;
    }

    public static final <T> l1 launchIn(Flow<? extends T> flow, h0 h0Var) {
        return kotlinx.coroutines.f.b(h0Var, null, null, new AnonymousClass1(flow, null), 3, null);
    }

    public static final <T> Object collect(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar, d<? super r> dVar) {
        Object objCollect = flow.collect(new AnonymousClass3(pVar), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }
}
