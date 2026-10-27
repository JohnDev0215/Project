package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.k;
import kotlinx.coroutines.flow.internal.ChannelFlowMerge;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;
import kotlinx.coroutines.internal.d0;
import r5.p;
import r5.q;

/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__MergeKt {
    private static final int DEFAULT_CONCURRENCY = d0.b(FlowKt.DEFAULT_CONCURRENCY_PROPERTY_NAME, 16, 1, Integer.MAX_VALUE);

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapLatest$1, reason: invalid class name */
    /* JADX INFO: compiled from: Merge.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapLatest$1", f = "Merge.kt", l = {190, 216}, m = "invokeSuspend")
    public static final class AnonymousClass1<R, T> extends l implements q<FlowCollector<? super R>, T, d<? super r>, Object> {
        final /* synthetic */ p $transform;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        private FlowCollector p$;
        private Object p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(p pVar, d dVar) {
            super(3, dVar);
            this.$transform = pVar;
        }

        public final d<r> create(FlowCollector<? super R> flowCollector, T t6, d<? super r> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$transform, dVar);
            anonymousClass1.p$ = flowCollector;
            anonymousClass1.p$0 = t6;
            return anonymousClass1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r5.q
        public final Object invoke(Object obj, Object obj2, d<? super r> dVar) {
            return ((AnonymousClass1) create((FlowCollector) obj, obj2, dVar)).invokeSuspend(r.f6563a);
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

        public final Object invokeSuspend$$forInline(Object obj) {
            FlowCollector<? super T> flowCollector = this.p$;
            Flow flow = (Flow) this.$transform.invoke(this.p$0, this);
            k.a(0);
            flow.collect(flowCollector, this);
            k.a(2);
            k.a(1);
            return r.f6563a;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$mapLatest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Merge.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$mapLatest$1", f = "Merge.kt", l = {214, 214}, m = "invokeSuspend")
    static final class C01891<R, T> extends l implements q<FlowCollector<? super R>, T, d<? super r>, Object> {
        final /* synthetic */ p $transform;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        private FlowCollector p$;
        private Object p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01891(p pVar, d dVar) {
            super(3, dVar);
            this.$transform = pVar;
        }

        public final d<r> create(FlowCollector<? super R> flowCollector, T t6, d<? super r> dVar) {
            C01891 c01891 = new C01891(this.$transform, dVar);
            c01891.p$ = flowCollector;
            c01891.p$0 = t6;
            return c01891;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r5.q
        public final Object invoke(Object obj, Object obj2, d<? super r> dVar) {
            return ((C01891) create((FlowCollector) obj, obj2, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            FlowCollector flowCollector;
            Object obj2;
            FlowCollector flowCollector2;
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                FlowCollector flowCollector3 = this.p$;
                Object obj3 = this.p$0;
                p pVar = this.$transform;
                this.L$0 = flowCollector3;
                this.L$1 = obj3;
                this.L$2 = flowCollector3;
                this.label = 1;
                Object objInvoke = pVar.invoke(obj3, this);
                if (objInvoke == objD) {
                    return objD;
                }
                flowCollector = flowCollector3;
                obj2 = obj3;
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
                obj2 = this.L$1;
                FlowCollector flowCollector5 = (FlowCollector) this.L$0;
                m.b(obj);
                flowCollector2 = flowCollector4;
                flowCollector = flowCollector5;
            }
            this.L$0 = flowCollector;
            this.L$1 = obj2;
            this.label = 2;
            if (flowCollector2.emit(obj, this) == objD) {
                return objD;
            }
            return r.f6563a;
        }
    }

    public static final <T, R> Flow<R> flatMapConcat(final Flow<? extends T> flow, final p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt.flattenConcat(new Flow<Flow<? extends R>>() { // from class: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1

            /* JADX INFO: Add missing generic type declarations: [T] */
            /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            public static final class AnonymousClass2<T> implements FlowCollector<T> {
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
                final /* synthetic */ FlowKt__MergeKt$flatMapConcat$$inlined$map$1 this$0;

                /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1, reason: invalid class name */
                @f(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2", f = "Merge.kt", l = {134, 134}, m = "emit")
                public static final class AnonymousClass1 extends kotlin.coroutines.jvm.internal.d {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d dVar) {
                        super(dVar);
                    }

                    @Override // kotlin.coroutines.jvm.internal.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, FlowKt__MergeKt$flatMapConcat$$inlined$map$1 flowKt__MergeKt$flatMapConcat$$inlined$map$1) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.this$0 = flowKt__MergeKt$flatMapConcat$$inlined$map$1;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r11, k5.d r12) throws java.lang.Throwable {
                    /*
                        r10 = this;
                        boolean r0 = r12 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r12
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1
                        r0.<init>(r12)
                    L18:
                        java.lang.Object r12 = r0.result
                        java.lang.Object r1 = l5.b.d()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L63
                        if (r2 == r4) goto L45
                        if (r2 != r3) goto L3d
                        java.lang.Object r11 = r0.L$6
                        kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                        java.lang.Object r11 = r0.L$4
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.AnonymousClass1) r11
                        java.lang.Object r11 = r0.L$2
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.AnonymousClass1) r11
                        java.lang.Object r11 = r0.L$0
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2) r11
                        g5.m.b(r12)
                        goto La5
                    L3d:
                        java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                        java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                        r11.<init>(r12)
                        throw r11
                    L45:
                        java.lang.Object r11 = r0.L$7
                        kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                        java.lang.Object r2 = r0.L$6
                        kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                        java.lang.Object r4 = r0.L$5
                        java.lang.Object r5 = r0.L$4
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r5 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.AnonymousClass1) r5
                        java.lang.Object r6 = r0.L$3
                        java.lang.Object r7 = r0.L$2
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r7 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.AnonymousClass1) r7
                        java.lang.Object r8 = r0.L$1
                        java.lang.Object r9 = r0.L$0
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2 r9 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2) r9
                        g5.m.b(r12)
                        goto L8e
                    L63:
                        g5.m.b(r12)
                        kotlinx.coroutines.flow.FlowCollector r12 = r10.$this_unsafeFlow$inlined
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1 r2 = r10.this$0
                        r5.p r2 = r2
                        r0.L$0 = r10
                        r0.L$1 = r11
                        r0.L$2 = r0
                        r0.L$3 = r11
                        r0.L$4 = r0
                        r0.L$5 = r11
                        r0.L$6 = r12
                        r0.L$7 = r12
                        r0.label = r4
                        java.lang.Object r2 = r2.invoke(r11, r0)
                        if (r2 != r1) goto L85
                        return r1
                    L85:
                        r9 = r10
                        r4 = r11
                        r6 = r4
                        r8 = r6
                        r11 = r12
                        r5 = r0
                        r7 = r5
                        r12 = r2
                        r2 = r11
                    L8e:
                        r0.L$0 = r9
                        r0.L$1 = r8
                        r0.L$2 = r7
                        r0.L$3 = r6
                        r0.L$4 = r5
                        r0.L$5 = r4
                        r0.L$6 = r2
                        r0.label = r3
                        java.lang.Object r11 = r11.emit(r12, r0)
                        if (r11 != r1) goto La5
                        return r1
                    La5:
                        g5.r r11 = g5.r.f6563a
                        return r11
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, k5.d):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this), dVar);
                return objCollect == l5.d.d() ? objCollect : r.f6563a;
            }
        });
    }

    public static final <T, R> Flow<R> flatMapLatest(Flow<? extends T> flow, p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt.transformLatest(flow, new AnonymousClass1(pVar, null));
    }

    public static final <T, R> Flow<R> flatMapMerge(final Flow<? extends T> flow, int i7, final p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt.flattenMerge(new Flow<Flow<? extends R>>() { // from class: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1

            /* JADX INFO: Add missing generic type declarations: [T] */
            /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            public static final class AnonymousClass2<T> implements FlowCollector<T> {
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
                final /* synthetic */ FlowKt__MergeKt$flatMapMerge$$inlined$map$1 this$0;

                /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1, reason: invalid class name */
                @f(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2", f = "Merge.kt", l = {134, 134}, m = "emit")
                public static final class AnonymousClass1 extends kotlin.coroutines.jvm.internal.d {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    Object L$7;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d dVar) {
                        super(dVar);
                    }

                    @Override // kotlin.coroutines.jvm.internal.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, FlowKt__MergeKt$flatMapMerge$$inlined$map$1 flowKt__MergeKt$flatMapMerge$$inlined$map$1) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.this$0 = flowKt__MergeKt$flatMapMerge$$inlined$map$1;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r11, k5.d r12) throws java.lang.Throwable {
                    /*
                        r10 = this;
                        boolean r0 = r12 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r12
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1
                        r0.<init>(r12)
                    L18:
                        java.lang.Object r12 = r0.result
                        java.lang.Object r1 = l5.b.d()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L63
                        if (r2 == r4) goto L45
                        if (r2 != r3) goto L3d
                        java.lang.Object r11 = r0.L$6
                        kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                        java.lang.Object r11 = r0.L$4
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2.AnonymousClass1) r11
                        java.lang.Object r11 = r0.L$2
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2.AnonymousClass1) r11
                        java.lang.Object r11 = r0.L$0
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2) r11
                        g5.m.b(r12)
                        goto La5
                    L3d:
                        java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                        java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                        r11.<init>(r12)
                        throw r11
                    L45:
                        java.lang.Object r11 = r0.L$7
                        kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                        java.lang.Object r2 = r0.L$6
                        kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                        java.lang.Object r4 = r0.L$5
                        java.lang.Object r5 = r0.L$4
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r5 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2.AnonymousClass1) r5
                        java.lang.Object r6 = r0.L$3
                        java.lang.Object r7 = r0.L$2
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r7 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2.AnonymousClass1) r7
                        java.lang.Object r8 = r0.L$1
                        java.lang.Object r9 = r0.L$0
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2 r9 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2) r9
                        g5.m.b(r12)
                        goto L8e
                    L63:
                        g5.m.b(r12)
                        kotlinx.coroutines.flow.FlowCollector r12 = r10.$this_unsafeFlow$inlined
                        kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1 r2 = r10.this$0
                        r5.p r2 = r2
                        r0.L$0 = r10
                        r0.L$1 = r11
                        r0.L$2 = r0
                        r0.L$3 = r11
                        r0.L$4 = r0
                        r0.L$5 = r11
                        r0.L$6 = r12
                        r0.L$7 = r12
                        r0.label = r4
                        java.lang.Object r2 = r2.invoke(r11, r0)
                        if (r2 != r1) goto L85
                        return r1
                    L85:
                        r9 = r10
                        r4 = r11
                        r6 = r4
                        r8 = r6
                        r11 = r12
                        r5 = r0
                        r7 = r5
                        r12 = r2
                        r2 = r11
                    L8e:
                        r0.L$0 = r9
                        r0.L$1 = r8
                        r0.L$2 = r7
                        r0.L$3 = r6
                        r0.L$4 = r5
                        r0.L$5 = r4
                        r0.L$6 = r2
                        r0.label = r3
                        java.lang.Object r11 = r11.emit(r12, r0)
                        if (r11 != r1) goto La5
                        return r1
                    La5:
                        g5.r r11 = g5.r.f6563a
                        return r11
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, k5.d):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this), dVar);
                return objCollect == l5.d.d() ? objCollect : r.f6563a;
            }
        }, i7);
    }

    public static /* synthetic */ Flow flatMapMerge$default(Flow flow, int i7, p pVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i7 = DEFAULT_CONCURRENCY;
        }
        return FlowKt.flatMapMerge(flow, i7, pVar);
    }

    public static final <T> Flow<T> flattenConcat(final Flow<? extends Flow<? extends T>> flow) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__MergeKt$flattenConcat$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(final FlowCollector flowCollector, d dVar) {
                Object objCollect = flow.collect(new FlowCollector<Flow<? extends T>>() { // from class: kotlinx.coroutines.flow.FlowKt__MergeKt$flattenConcat$$inlined$unsafeFlow$1$lambda$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj, d dVar2) {
                        Object objCollect2 = ((Flow) obj).collect(flowCollector, dVar2);
                        return objCollect2 == l5.d.d() ? objCollect2 : r.f6563a;
                    }
                }, dVar);
                return objCollect == l5.d.d() ? objCollect : r.f6563a;
            }
        };
    }

    public static final <T> Flow<T> flattenMerge(Flow<? extends Flow<? extends T>> flow, int i7) {
        if (i7 > 0) {
            return i7 == 1 ? FlowKt.flattenConcat(flow) : new ChannelFlowMerge(flow, i7, null, 0, null, 28, null);
        }
        throw new IllegalArgumentException(("Expected positive concurrency level, but had " + i7).toString());
    }

    public static /* synthetic */ Flow flattenMerge$default(Flow flow, int i7, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i7 = DEFAULT_CONCURRENCY;
        }
        return FlowKt.flattenMerge(flow, i7);
    }

    public static final int getDEFAULT_CONCURRENCY() {
        return DEFAULT_CONCURRENCY;
    }

    public static /* synthetic */ void getDEFAULT_CONCURRENCY$annotations() {
    }

    public static final <T, R> Flow<R> mapLatest(Flow<? extends T> flow, p<? super T, ? super d<? super R>, ? extends Object> pVar) {
        return FlowKt.transformLatest(flow, new C01891(pVar, null));
    }

    public static final <T> Flow<T> merge(Iterable<? extends Flow<? extends T>> iterable) {
        return new ChannelLimitedFlowMerge(iterable, null, 0, null, 14, null);
    }

    public static final <T, R> Flow<R> transformLatest(Flow<? extends T> flow, q<? super FlowCollector<? super R>, ? super T, ? super d<? super r>, ? extends Object> qVar) {
        return new ChannelFlowTransformLatest(qVar, flow, null, 0, null, 28, null);
    }

    public static final <T> Flow<T> merge(Flow<? extends T>... flowArr) {
        return FlowKt.merge(h5.l.m(flowArr));
    }
}
