package kotlinx.coroutines.flow;

import g5.r;
import k5.g;
import kotlin.coroutines.jvm.internal.b;
import kotlin.coroutines.jvm.internal.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.jvm.internal.m;
import kotlinx.coroutines.l1;
import r5.l;
import r5.p;
import r5.q;

/* JADX INFO: compiled from: Errors.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__ErrorsKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Errors.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt", f = "Errors.kt", l = {230}, m = "catchImpl")
    static final class AnonymousClass1 extends d {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(k5.d dVar) {
            super(dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.catchImpl(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$onErrorCollect$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Errors.kt */
    static final class C01861 extends m implements l<Throwable, Boolean> {
        public static final C01861 INSTANCE = new C01861();

        C01861() {
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
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$onErrorCollect$2, reason: invalid class name */
    /* JADX INFO: compiled from: Errors.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$onErrorCollect$2", f = "Errors.kt", l = {227}, m = "invokeSuspend")
    static final class AnonymousClass2<T> extends kotlin.coroutines.jvm.internal.l implements q<FlowCollector<? super T>, Throwable, k5.d<? super r>, Object> {
        final /* synthetic */ Flow $fallback;
        final /* synthetic */ l $predicate;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        private FlowCollector p$;
        private Throwable p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(l lVar, Flow flow, k5.d dVar) {
            super(3, dVar);
            this.$predicate = lVar;
            this.$fallback = flow;
        }

        public final k5.d<r> create(FlowCollector<? super T> flowCollector, Throwable th, k5.d<? super r> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$predicate, this.$fallback, dVar);
            anonymousClass2.p$ = flowCollector;
            anonymousClass2.p$0 = th;
            return anonymousClass2;
        }

        @Override // r5.q
        public final Object invoke(Object obj, Throwable th, k5.d<? super r> dVar) {
            return ((AnonymousClass2) create((FlowCollector) obj, th, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                g5.m.b(obj);
                FlowCollector<? super T> flowCollector = this.p$;
                Throwable th = this.p$0;
                if (!((Boolean) this.$predicate.invoke(th)).booleanValue()) {
                    throw th;
                }
                Flow flow = this.$fallback;
                this.L$0 = flowCollector;
                this.L$1 = th;
                this.L$2 = flowCollector;
                this.L$3 = flow;
                this.label = 1;
                if (flow.collect(flowCollector, this) == objD) {
                    return objD;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                g5.m.b(obj);
            }
            return r.f6563a;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Errors.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$1", f = "Errors.kt", l = {}, m = "invokeSuspend")
    static final class C01871 extends kotlin.coroutines.jvm.internal.l implements p<Throwable, k5.d<? super Boolean>, Object> {
        int label;
        private Throwable p$0;

        C01871(k5.d dVar) {
            super(2, dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final k5.d<r> create(Object obj, k5.d<?> dVar) {
            C01871 c01871 = new C01871(dVar);
            c01871.p$0 = (Throwable) obj;
            return c01871;
        }

        @Override // r5.p
        public final Object invoke(Throwable th, k5.d<? super Boolean> dVar) {
            return ((C01871) create(th, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            l5.d.d();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            g5.m.b(obj);
            return b.a(true);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$3, reason: invalid class name */
    /* JADX INFO: compiled from: Errors.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$3", f = "Errors.kt", l = {124}, m = "invokeSuspend")
    static final class AnonymousClass3<T> extends kotlin.coroutines.jvm.internal.l implements r5.r<FlowCollector<? super T>, Throwable, Long, k5.d<? super Boolean>, Object> {
        final /* synthetic */ p $predicate;
        final /* synthetic */ long $retries;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        private FlowCollector p$;
        private Throwable p$0;
        private long p$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(long j7, p pVar, k5.d dVar) {
            super(4, dVar);
            this.$retries = j7;
            this.$predicate = pVar;
        }

        public final k5.d<r> create(FlowCollector<? super T> flowCollector, Throwable th, long j7, k5.d<? super Boolean> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$retries, this.$predicate, dVar);
            anonymousClass3.p$ = flowCollector;
            anonymousClass3.p$0 = th;
            anonymousClass3.p$1 = j7;
            return anonymousClass3;
        }

        @Override // r5.r
        public final Object invoke(Object obj, Throwable th, Long l7, k5.d<? super Boolean> dVar) {
            return ((AnonymousClass3) create((FlowCollector) obj, th, l7.longValue(), dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                g5.m.b(obj);
                FlowCollector flowCollector = this.p$;
                Throwable th = this.p$0;
                long j7 = this.p$1;
                if (j7 < this.$retries) {
                    p pVar = this.$predicate;
                    this.L$0 = flowCollector;
                    this.L$1 = th;
                    this.J$0 = j7;
                    this.label = 1;
                    obj = pVar.invoke(th, this);
                    if (obj == objD) {
                        return objD;
                    }
                }
                return b.a(z);
            }
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            g5.m.b(obj);
            boolean z6 = ((Boolean) obj).booleanValue();
            return b.a(z6);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$4, reason: invalid class name */
    /* JADX INFO: compiled from: Errors.kt */
    static final class AnonymousClass4 extends m implements l<Throwable, Boolean> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        AnonymousClass4() {
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
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$6, reason: invalid class name */
    /* JADX INFO: compiled from: Errors.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$6", f = "Errors.kt", l = {}, m = "invokeSuspend")
    static final class AnonymousClass6<T> extends kotlin.coroutines.jvm.internal.l implements r5.r<FlowCollector<? super T>, Throwable, Long, k5.d<? super Boolean>, Object> {
        final /* synthetic */ l $predicate;
        final /* synthetic */ int $retries;
        int label;
        private FlowCollector p$;
        private Throwable p$0;
        private long p$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(int i7, l lVar, k5.d dVar) {
            super(4, dVar);
            this.$retries = i7;
            this.$predicate = lVar;
        }

        public final k5.d<r> create(FlowCollector<? super T> flowCollector, Throwable th, long j7, k5.d<? super Boolean> dVar) {
            AnonymousClass6 anonymousClass6 = new AnonymousClass6(this.$retries, this.$predicate, dVar);
            anonymousClass6.p$ = flowCollector;
            anonymousClass6.p$0 = th;
            anonymousClass6.p$1 = j7;
            return anonymousClass6;
        }

        @Override // r5.r
        public final Object invoke(Object obj, Throwable th, Long l7, k5.d<? super Boolean> dVar) {
            return ((AnonymousClass6) create((FlowCollector) obj, th, l7.longValue(), dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            l5.d.d();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            g5.m.b(obj);
            return b.a(((Boolean) this.$predicate.invoke(this.p$0)).booleanValue() && this.p$1 < ((long) this.$retries));
        }
    }

    public static /* synthetic */ void ExceptionPredicate$annotations() {
    }

    /* JADX INFO: renamed from: catch, reason: not valid java name */
    public static final <T> Flow<T> m8catch(Flow<? extends T> flow, q<? super FlowCollector<? super T>, ? super Throwable, ? super k5.d<? super r>, ? extends Object> qVar) {
        return new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(flow, qVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object catchImpl(kotlinx.coroutines.flow.Flow<? extends T> r5, kotlinx.coroutines.flow.FlowCollector<? super T> r6, k5.d<? super java.lang.Throwable> r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = l5.b.d()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L44
            if (r2 != r4) goto L3c
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.flow.Flow r5 = (kotlinx.coroutines.flow.Flow) r5
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.internal.r r5 = (kotlin.jvm.internal.r) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
            g5.m.b(r7)     // Catch: java.lang.Throwable -> L3a
            goto L64
        L3a:
            r6 = move-exception
            goto L67
        L3c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L44:
            g5.m.b(r7)
            kotlin.jvm.internal.r r7 = new kotlin.jvm.internal.r
            r7.<init>()
            r7.f7508e = r3
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1 r2 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1     // Catch: java.lang.Throwable -> L65
            r2.<init>(r6, r7)     // Catch: java.lang.Throwable -> L65
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L65
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L65
            r0.L$2 = r7     // Catch: java.lang.Throwable -> L65
            r0.L$3 = r5     // Catch: java.lang.Throwable -> L65
            r0.label = r4     // Catch: java.lang.Throwable -> L65
            java.lang.Object r5 = r5.collect(r2, r0)     // Catch: java.lang.Throwable -> L65
            if (r5 != r1) goto L64
            return r1
        L64:
            return r3
        L65:
            r6 = move-exception
            r5 = r7
        L67:
            T r5 = r5.f7508e
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            boolean r5 = isSameExceptionAs$FlowKt__ErrorsKt(r6, r5)
            if (r5 != 0) goto L7c
            k5.g r5 = r0.getContext()
            boolean r5 = isCancellationCause$FlowKt__ErrorsKt(r6, r5)
            if (r5 != 0) goto L7c
            return r6
        L7c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl(kotlinx.coroutines.flow.Flow, kotlinx.coroutines.flow.FlowCollector, k5.d):java.lang.Object");
    }

    private static final boolean isCancellationCause$FlowKt__ErrorsKt(Throwable th, g gVar) {
        l1 l1Var = (l1) gVar.get(l1.f7703d);
        if (l1Var == null || !l1Var.isCancelled()) {
            return false;
        }
        return isSameExceptionAs$FlowKt__ErrorsKt(th, l1Var.getCancellationException());
    }

    private static final boolean isSameExceptionAs$FlowKt__ErrorsKt(Throwable th, Throwable th2) {
        return th2 != null && kotlin.jvm.internal.l.a(th2, th);
    }

    public static final <T> Flow<T> onErrorCollect(Flow<? extends T> flow, Flow<? extends T> flow2, l<? super Throwable, Boolean> lVar) {
        return FlowKt.m3catch(flow, new AnonymousClass2(lVar, flow2, null));
    }

    public static /* synthetic */ Flow onErrorCollect$default(Flow flow, Flow flow2, l lVar, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            lVar = C01861.INSTANCE;
        }
        return FlowKt.onErrorCollect(flow, flow2, lVar);
    }

    public static final <T> Flow<T> retry(Flow<? extends T> flow, long j7, p<? super Throwable, ? super k5.d<? super Boolean>, ? extends Object> pVar) {
        if (j7 > 0) {
            return FlowKt.retryWhen(flow, new AnonymousClass3(j7, pVar, null));
        }
        throw new IllegalArgumentException(("Expected positive amount of retries, but had " + j7).toString());
    }

    public static /* synthetic */ Flow retry$default(Flow flow, long j7, p pVar, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            j7 = Long.MAX_VALUE;
        }
        if ((i7 & 2) != 0) {
            pVar = new C01871(null);
        }
        return FlowKt.retry(flow, j7, (p<? super Throwable, ? super k5.d<? super Boolean>, ? extends Object>) pVar);
    }

    public static final <T> Flow<T> retryWhen(Flow<? extends T> flow, r5.r<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super k5.d<? super Boolean>, ? extends Object> rVar) {
        return new FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(flow, rVar);
    }

    public static /* synthetic */ Flow retry$default(Flow flow, int i7, l lVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i7 = Integer.MAX_VALUE;
        }
        if ((i8 & 2) != 0) {
            lVar = AnonymousClass4.INSTANCE;
        }
        return retry(flow, i7, (l<? super Throwable, Boolean>) lVar);
    }

    public static final /* synthetic */ <T> Flow<T> retry(Flow<? extends T> flow, int i7, l<? super Throwable, Boolean> lVar) {
        if (i7 > 0) {
            return FlowKt.retryWhen(flow, new AnonymousClass6(i7, lVar, null));
        }
        throw new IllegalArgumentException(("Expected positive amount of retries, but had " + i7).toString());
    }
}
