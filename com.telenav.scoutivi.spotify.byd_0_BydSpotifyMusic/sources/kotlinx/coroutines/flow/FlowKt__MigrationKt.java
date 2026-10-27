package kotlinx.coroutines.flow;

import g5.e;
import g5.m;
import g5.r;
import k5.d;
import k5.g;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.r0;
import r5.p;
import r5.q;
import r5.s;
import r5.t;

/* JADX INFO: compiled from: Migration.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__MigrationKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MigrationKt$delayEach$1, reason: invalid class name */
    /* JADX INFO: compiled from: Migration.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$delayEach$1", f = "Migration.kt", l = {423}, m = "invokeSuspend")
    static final class AnonymousClass1<T> extends l implements p<T, d<? super r>, Object> {
        final /* synthetic */ long $timeMillis;
        Object L$0;
        int label;
        private Object p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j7, d dVar) {
            super(2, dVar);
            this.$timeMillis = j7;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$timeMillis, dVar);
            anonymousClass1.p$0 = obj;
            return anonymousClass1;
        }

        @Override // r5.p
        public final Object invoke(Object obj, d<? super r> dVar) {
            return ((AnonymousClass1) create(obj, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                Object obj2 = this.p$0;
                long j7 = this.$timeMillis;
                this.L$0 = obj2;
                this.label = 1;
                if (r0.a(j7, this) == objD) {
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

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MigrationKt$delayFlow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Migration.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$delayFlow$1", f = "Migration.kt", l = {411}, m = "invokeSuspend")
    static final class C01901<T> extends l implements p<FlowCollector<? super T>, d<? super r>, Object> {
        final /* synthetic */ long $timeMillis;
        Object L$0;
        int label;
        private FlowCollector p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01901(long j7, d dVar) {
            super(2, dVar);
            this.$timeMillis = j7;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            C01901 c01901 = new C01901(this.$timeMillis, dVar);
            c01901.p$ = (FlowCollector) obj;
            return c01901;
        }

        @Override // r5.p
        public final Object invoke(Object obj, d<? super r> dVar) {
            return ((C01901) create(obj, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                FlowCollector flowCollector = this.p$;
                long j7 = this.$timeMillis;
                this.L$0 = flowCollector;
                this.label = 1;
                if (r0.a(j7, this) == objD) {
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

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MigrationKt$onErrorReturn$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Migration.kt */
    static final class C01911 extends kotlin.jvm.internal.m implements r5.l<Throwable, Boolean> {
        public static final C01911 INSTANCE = new C01911();

        C01911() {
            super(1);
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ Boolean invoke(Throwable th) {
            return Boolean.valueOf(invoke2(th));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(Throwable th) {
            return true;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MigrationKt$onErrorReturn$2, reason: invalid class name */
    /* JADX INFO: compiled from: Migration.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$onErrorReturn$2", f = "Migration.kt", l = {306}, m = "invokeSuspend")
    static final class AnonymousClass2<T> extends l implements q<FlowCollector<? super T>, Throwable, d<? super r>, Object> {
        final /* synthetic */ Object $fallback;
        final /* synthetic */ r5.l $predicate;
        Object L$0;
        Object L$1;
        int label;
        private FlowCollector p$;
        private Throwable p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(r5.l lVar, Object obj, d dVar) {
            super(3, dVar);
            this.$predicate = lVar;
            this.$fallback = obj;
        }

        public final d<r> create(FlowCollector<? super T> flowCollector, Throwable th, d<? super r> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$predicate, this.$fallback, dVar);
            anonymousClass2.p$ = flowCollector;
            anonymousClass2.p$0 = th;
            return anonymousClass2;
        }

        @Override // r5.q
        public final Object invoke(Object obj, Throwable th, d<? super r> dVar) {
            return ((AnonymousClass2) create((FlowCollector) obj, th, dVar)).invokeSuspend(r.f6563a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                FlowCollector flowCollector = this.p$;
                Throwable th = this.p$0;
                if (!((Boolean) this.$predicate.invoke(th)).booleanValue()) {
                    throw th;
                }
                Object obj2 = this.$fallback;
                this.L$0 = flowCollector;
                this.L$1 = th;
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

    public static final <T> Flow<T> cache(Flow<? extends T> flow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T1, T2, R> Flow<R> combineLatest(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return FlowKt.combine(flow, flow2, qVar);
    }

    public static final <T, R> Flow<R> compose(Flow<? extends T> flow, r5.l<? super Flow<? extends T>, ? extends Flow<? extends R>> lVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T, R> Flow<R> concatMap(Flow<? extends T> flow, r5.l<? super T, ? extends Flow<? extends R>> lVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> concatWith(Flow<? extends T> flow, T t6) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> delayEach(Flow<? extends T> flow, long j7) {
        return FlowKt.onEach(flow, new AnonymousClass1(j7, null));
    }

    public static final <T> Flow<T> delayFlow(Flow<? extends T> flow, long j7) {
        return FlowKt.onStart(flow, new C01901(j7, null));
    }

    public static final <T, R> Flow<R> flatMap(Flow<? extends T> flow, p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> flatten(Flow<? extends Flow<? extends T>> flow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> void forEach(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> merge(Flow<? extends Flow<? extends T>> flow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final Void noImpl() {
        throw new UnsupportedOperationException("Not implemented, should not be called");
    }

    public static final <T> Flow<T> observeOn(Flow<? extends T> flow, g gVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> onErrorResume(Flow<? extends T> flow, Flow<? extends T> flow2) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> onErrorResumeNext(Flow<? extends T> flow, Flow<? extends T> flow2) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> onErrorReturn(Flow<? extends T> flow, T t6) {
        FlowKt.noImpl();
        throw new e();
    }

    public static /* synthetic */ Flow onErrorReturn$default(Flow flow, Object obj, r5.l lVar, int i7, Object obj2) {
        if ((i7 & 2) != 0) {
            lVar = C01911.INSTANCE;
        }
        return FlowKt.onErrorReturn(flow, obj, lVar);
    }

    public static final <T> Flow<T> publish(Flow<? extends T> flow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> publishOn(Flow<? extends T> flow, g gVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> replay(Flow<? extends T> flow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T, R> Flow<R> scanFold(Flow<? extends T> flow, R r7, q<? super R, ? super T, ? super d<? super R>, ? extends Object> qVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> scanReduce(Flow<? extends T> flow, q<? super T, ? super T, ? super d<? super T>, ? extends Object> qVar) {
        return FlowKt.runningReduce(flow, qVar);
    }

    public static final <T> Flow<T> skip(Flow<? extends T> flow, int i7) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> startWith(Flow<? extends T> flow, T t6) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> void subscribe(Flow<? extends T> flow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> subscribeOn(Flow<? extends T> flow, g gVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T, R> Flow<R> switchMap(Flow<? extends T> flow, p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt.transformLatest(flow, new FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1(pVar, null));
    }

    public static final <T1, T2, T3, R> Flow<R> combineLatest(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, r5.r<? super T1, ? super T2, ? super T3, ? super d<? super R>, ? extends Object> rVar) {
        return FlowKt.combine(flow, flow2, flow3, rVar);
    }

    public static final <T> Flow<T> concatWith(Flow<? extends T> flow, Flow<? extends T> flow2) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> onErrorReturn(Flow<? extends T> flow, T t6, r5.l<? super Throwable, Boolean> lVar) {
        return FlowKt.m3catch(flow, new AnonymousClass2(lVar, t6, null));
    }

    public static final <T> Flow<T> publish(Flow<? extends T> flow, int i7) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> replay(Flow<? extends T> flow, int i7) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> startWith(Flow<? extends T> flow, Flow<? extends T> flow2) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> void subscribe(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T1, T2, T3, T4, R> Flow<R> combineLatest(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, s<? super T1, ? super T2, ? super T3, ? super T4, ? super d<? super R>, ? extends Object> sVar) {
        return FlowKt.combine(flow, flow2, flow3, flow4, sVar);
    }

    public static final <T> void subscribe(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar, p<? super Throwable, ? super d<? super r>, ? extends Object> pVar2) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineLatest(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Flow<? extends T5> flow5, t<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super d<? super R>, ? extends Object> tVar) {
        return FlowKt.combine(flow, flow2, flow3, flow4, flow5, tVar);
    }
}
