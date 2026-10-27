package com.byd.overseaappstore.module.ability.util.operate.factory.lineb;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.m9;
import spi.p9;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorHciCachePreTreat extends m9<CacheCarrier> {

    @NotNull
    public final p9 b;

    public ExecutorHciCachePreTreat(@NotNull p9 hciCondition) {
        Intrinsics.checkNotNullParameter(hciCondition, "hciCondition");
        this.b = hciCondition;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.m9
    @Nullable
    public final Object a(@NotNull final a2 a2Var, @NotNull final Flow<? extends CacheCarrier> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow<CacheCarrier> flow2;
        Objects.requireNonNull(a2Var);
        if (a2Var.c == 1) {
            final Flow<CacheCarrier> flow3 = new Flow<CacheCarrier>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciCachePreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1$2", f = "ExecutorHciCachePreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        public Object L$0;
                        public Object L$1;
                        public int label;
                        public /* synthetic */ Object result;

                        public AnonymousClass1(Continuation continuation) {
                            super(continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciCachePreTreat executorHciCachePreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciCachePreTreat;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r6) {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L4c
                        L29:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L31:
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlinx.coroutines.flow.FlowCollector r6 = r4.a
                            r2 = r5
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier r2 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat r2 = r4.b
                            spi.p9 r2 = r2.b
                            boolean r2 = r2.d()
                            if (r2 == 0) goto L4c
                            r0.label = r3
                            java.lang.Object r5 = r6.emit(r5, r0)
                            if (r5 != r1) goto L4c
                            return r1
                        L4c:
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super CacheCarrier> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            flow2 = new Flow<CacheCarrier>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciCachePreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2$2", f = "ExecutorHciCachePreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        public Object L$0;
                        public Object L$1;
                        public int label;
                        public /* synthetic */ Object result;

                        public AnonymousClass1(Continuation continuation) {
                            super(continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciCachePreTreat executorHciCachePreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciCachePreTreat;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r6) {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L4c
                        L29:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L31:
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlinx.coroutines.flow.FlowCollector r6 = r4.a
                            r2 = r5
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier r2 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat r2 = r4.b
                            spi.p9 r2 = r2.b
                            boolean r2 = r2.b()
                            if (r2 == 0) goto L4c
                            r0.label = r3
                            java.lang.Object r5 = r6.emit(r5, r0)
                            if (r5 != r1) goto L4c
                            return r1
                        L4c:
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super CacheCarrier> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow3.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        } else {
            final Flow<CacheCarrier> flow4 = new Flow<CacheCarrier>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciCachePreTreat b;
                    public final /* synthetic */ a2 c;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3$2", f = "ExecutorHciCachePreTreat.kt", i = {0, 0}, l = {225, 224}, m = "emit", n = {"value", "$this$filter_u24lambda_u2d0"}, s = {"L$0", "L$1"})
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        public Object L$0;
                        public Object L$1;
                        public int label;
                        public /* synthetic */ Object result;

                        public AnonymousClass1(Continuation continuation) {
                            super(continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciCachePreTreat executorHciCachePreTreat, a2 a2Var) {
                        this.a = flowCollector;
                        this.b = executorHciCachePreTreat;
                        this.c = a2Var;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Removed duplicated region for block: B:25:0x0080  */
                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r10) {
                        /*
                            r8 = this;
                            boolean r0 = r10 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r10
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3$2$1
                            r0.<init>(r10)
                        L18:
                            java.lang.Object r10 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 0
                            r4 = 2
                            r5 = 1
                            if (r2 == 0) goto L3f
                            if (r2 == r5) goto L35
                            if (r2 != r4) goto L2d
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L8e
                        L2d:
                            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                            r9.<init>(r10)
                            throw r9
                        L35:
                            java.lang.Object r9 = r0.L$1
                            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
                            java.lang.Object r2 = r0.L$0
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L68
                        L3f:
                            kotlin.ResultKt.throwOnFailure(r10)
                            kotlinx.coroutines.flow.FlowCollector r10 = r8.a
                            r2 = r9
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier r2 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier) r2
                            com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r2 = r2.getOptParam()
                            if (r2 == 0) goto L71
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat r3 = r8.b
                            spi.p9 r3 = r3.b
                            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
                            spi.a2 r6 = r8.c
                            r0.L$0 = r9
                            r0.L$1 = r10
                            r0.label = r5
                            java.lang.Object r2 = r3.c(r2, r6, r0)
                            if (r2 != r1) goto L64
                            return r1
                        L64:
                            r7 = r2
                            r2 = r9
                            r9 = r10
                            r10 = r7
                        L68:
                            java.lang.Boolean r10 = (java.lang.Boolean) r10
                            boolean r3 = r10.booleanValue()
                            r10 = r9
                            r9 = r2
                            goto L7e
                        L71:
                            spi.a2 r2 = r8.c
                            java.lang.String r5 = "executorHciCachePreTreat handleWaitToWork wifiOnlyCheck optParam is null bizSrc:"
                            java.lang.String r2 = spi.e.e(r5, r2)
                            java.lang.Object[] r5 = new java.lang.Object[r3]
                            spi.ct.b(r2, r5)
                        L7e:
                            if (r3 == 0) goto L8e
                            r2 = 0
                            r0.L$0 = r2
                            r0.L$1 = r2
                            r0.label = r4
                            java.lang.Object r9 = r10.emit(r9, r0)
                            if (r9 != r1) goto L8e
                            return r1
                        L8e:
                            kotlin.Unit r9 = kotlin.Unit.INSTANCE
                            return r9
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super CacheCarrier> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            final Flow<CacheCarrier> flow5 = new Flow<CacheCarrier>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciCachePreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4$2", f = "ExecutorHciCachePreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        public Object L$0;
                        public Object L$1;
                        public int label;
                        public /* synthetic */ Object result;

                        public AnonymousClass1(Continuation continuation) {
                            super(continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciCachePreTreat executorHciCachePreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciCachePreTreat;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r6) {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L4c
                        L29:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L31:
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlinx.coroutines.flow.FlowCollector r6 = r4.a
                            r2 = r5
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier r2 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat r2 = r4.b
                            spi.p9 r2 = r2.b
                            boolean r2 = r2.d()
                            if (r2 == 0) goto L4c
                            r0.label = r3
                            java.lang.Object r5 = r6.emit(r5, r0)
                            if (r5 != r1) goto L4c
                            return r1
                        L4c:
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super CacheCarrier> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow4.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            flow2 = new Flow<CacheCarrier>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciCachePreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5$2", f = "ExecutorHciCachePreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        public Object L$0;
                        public Object L$1;
                        public int label;
                        public /* synthetic */ Object result;

                        public AnonymousClass1(Continuation continuation) {
                            super(continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciCachePreTreat executorHciCachePreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciCachePreTreat;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r6) {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L4c
                        L29:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L31:
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlinx.coroutines.flow.FlowCollector r6 = r4.a
                            r2 = r5
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier r2 = (com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat r2 = r4.b
                            spi.p9 r2 = r2.b
                            boolean r2 = r2.b()
                            if (r2 == 0) goto L4c
                            r0.label = r3
                            java.lang.Object r5 = r6.emit(r5, r0)
                            if (r5 != r1) goto L4c
                            return r1
                        L4c:
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat$handleWaitToWork$$inlined$filter$5.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super CacheCarrier> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow5.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        }
        m9<T> m9Var = this.a;
        if (m9Var != 0) {
            Object objA = m9Var.a(a2Var, flow2, continuation);
            return objA == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objA : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.m9
    @Nullable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Object b(@NotNull a2 a2Var, @NotNull CacheCarrier cacheCarrier, @NotNull Continuation<? super Unit> continuation) {
        m9<T> m9Var = this.a;
        if (m9Var != 0) {
            Object objB = m9Var.b(a2Var, (T) cacheCarrier, continuation);
            return objB == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objB : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }
}
