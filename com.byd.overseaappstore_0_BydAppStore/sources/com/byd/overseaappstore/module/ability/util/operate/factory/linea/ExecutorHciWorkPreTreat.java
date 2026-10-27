package com.byd.overseaappstore.module.ability.util.operate.factory.linea;

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
import spi.mv;
import spi.oa;
import spi.p9;
import spi.t9;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorHciWorkPreTreat extends t9<mv> {

    @NotNull
    public final oa b;

    @NotNull
    public final p9 c;

    public ExecutorHciWorkPreTreat(@NotNull oa executorSoftwareStateMgr, @NotNull p9 executorHciCheckPlugin) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorHciCheckPlugin, "executorHciCheckPlugin");
        this.b = executorSoftwareStateMgr;
        this.c = executorHciCheckPlugin;
    }

    @Override // spi.t9
    @Nullable
    public final Object a(@NotNull a2 a2Var, @NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objA = t9Var.a(a2Var, str, continuation);
            return objA == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objA : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    @Override // spi.t9
    @Nullable
    public final Object b(@NotNull a2 a2Var, @NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objB = t9Var.b(a2Var, str, continuation);
            return objB == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objB : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object c(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objC = t9Var.c(a2Var, (Flow<? extends T>) flow, continuation);
            return objC == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objC : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object d(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objD = t9Var.d(a2Var, (Flow<? extends T>) flow, continuation);
            return objD == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objD : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object e(@NotNull final a2 a2Var, @NotNull final Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        final Flow<mv> flow2 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ ExecutorHciWorkPreTreat b;
                public final /* synthetic */ a2 c;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1$2", f = "ExecutorHciWorkPreTreat.kt", i = {0, 0}, l = {225, 224}, m = "emit", n = {"value", "$this$filter_u24lambda_u2d0"}, s = {"L$0", "L$1"})
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

                public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat, a2 a2Var) {
                    this.a = flowCollector;
                    this.b = executorHciWorkPreTreat;
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
                        boolean r0 = r10 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r10
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1$2$1
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
                        spi.mv r2 = (spi.mv) r2
                        com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r2 = r2.getOptParam()
                        if (r2 == 0) goto L71
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r3 = r8.b
                        spi.p9 r3 = r3.c
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
                        java.lang.String r5 = "executorHciWorkPreTreat handleResumeActing wifiOnlyCheck optParam is null bizSrc:"
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow<mv> flow3 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ ExecutorHciWorkPreTreat b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                    this.a = flowCollector;
                    this.b = executorHciWorkPreTreat;
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
                        boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2$2$1
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
                        spi.mv r2 = (spi.mv) r2
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                        spi.p9 r2 = r2.c
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect = flow2.collect(new AnonymousClass2(flowCollector, this), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        Flow<mv> flow4 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ ExecutorHciWorkPreTreat b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                    this.a = flowCollector;
                    this.b = executorHciWorkPreTreat;
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
                        boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3$2$1
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
                        spi.mv r2 = (spi.mv) r2
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                        spi.p9 r2 = r2.c
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleResumeActing$$inlined$filter$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect = flow3.collect(new AnonymousClass2(flowCollector, this), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objE = t9Var.e(a2Var, flow4, continuation);
            return objE == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objE : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object f(@NotNull final a2 a2Var, @NotNull final Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow<mv> flow2;
        Objects.requireNonNull(a2Var);
        if (a2Var.c == 1) {
            final Flow<mv> flow3 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
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
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1$2$1
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
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                            spi.p9 r2 = r2.c
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
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            flow2 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
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
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2$2$1
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
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                            spi.p9 r2 = r2.c
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
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow3.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        } else {
            final Flow<mv> flow4 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;
                    public final /* synthetic */ a2 c;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3$2", f = "ExecutorHciWorkPreTreat.kt", i = {0, 0}, l = {225, 225}, m = "emit", n = {"value", "$this$filter_u24lambda_u2d0"}, s = {"L$0", "L$1"})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat, a2 a2Var) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
                        this.c = a2Var;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
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
                            boolean r0 = r10 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r10
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3$2$1
                            r0.<init>(r10)
                        L18:
                            java.lang.Object r10 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 2
                            r4 = 1
                            if (r2 == 0) goto L3e
                            if (r2 == r4) goto L34
                            if (r2 != r3) goto L2c
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L77
                        L2c:
                            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                            r9.<init>(r10)
                            throw r9
                        L34:
                            java.lang.Object r9 = r0.L$1
                            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
                            java.lang.Object r2 = r0.L$0
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L61
                        L3e:
                            kotlin.ResultKt.throwOnFailure(r10)
                            kotlinx.coroutines.flow.FlowCollector r10 = r8.a
                            r2 = r9
                            spi.mv r2 = (spi.mv) r2
                            java.util.List r2 = r2.getOptParamList()
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r5 = r8.b
                            spi.p9 r5 = r5.c
                            spi.a2 r6 = r8.c
                            r0.L$0 = r9
                            r0.L$1 = r10
                            r0.label = r4
                            java.lang.Object r2 = r5.c(r2, r6, r0)
                            if (r2 != r1) goto L5d
                            return r1
                        L5d:
                            r7 = r2
                            r2 = r9
                            r9 = r10
                            r10 = r7
                        L61:
                            java.lang.Boolean r10 = (java.lang.Boolean) r10
                            boolean r10 = r10.booleanValue()
                            if (r10 == 0) goto L77
                            r10 = 0
                            r0.L$0 = r10
                            r0.L$1 = r10
                            r0.label = r3
                            java.lang.Object r9 = r9.emit(r2, r0)
                            if (r9 != r1) goto L77
                            return r1
                        L77:
                            kotlin.Unit r9 = kotlin.Unit.INSTANCE
                            return r9
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            final Flow<mv> flow5 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
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
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4$2$1
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
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                            spi.p9 r2 = r2.c
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
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow4.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            flow2 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
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
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5$2$1
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
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                            spi.p9 r2 = r2.c
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
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartDownloadOnlyTasks$$inlined$filter$5.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow5.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        }
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objF = t9Var.f(a2Var, flow2, continuation);
            return objF == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objF : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object g(@NotNull a2 a2Var, @NotNull final Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        final Flow<mv> flow2 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ ExecutorHciWorkPreTreat b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                    this.a = flowCollector;
                    this.b = executorHciWorkPreTreat;
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
                        boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1$2$1
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
                        spi.mv r2 = (spi.mv) r2
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                        spi.p9 r2 = r2.c
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        Flow<mv> flow3 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ ExecutorHciWorkPreTreat b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                    this.a = flowCollector;
                    this.b = executorHciWorkPreTreat;
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
                        boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2$2$1
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
                        spi.mv r2 = (spi.mv) r2
                        com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                        spi.p9 r2 = r2.c
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartNetErrorTotalOnlyTasks$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect = flow2.collect(new AnonymousClass2(flowCollector, this), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objG = t9Var.g(a2Var, flow3, continuation);
            return objG == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objG : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object h(@NotNull final a2 a2Var, @NotNull final Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow<mv> flow2;
        Objects.requireNonNull(a2Var);
        if (a2Var.c == 1) {
            final Flow<mv> flow3 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
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
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1$2$1
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
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                            spi.p9 r2 = r2.c
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
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            flow2 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
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
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2$2$1
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
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                            spi.p9 r2 = r2.c
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
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow3.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        } else {
            final Flow<mv> flow4 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;
                    public final /* synthetic */ a2 c;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3$2", f = "ExecutorHciWorkPreTreat.kt", i = {0, 0}, l = {225, 225}, m = "emit", n = {"value", "$this$filter_u24lambda_u2d0"}, s = {"L$0", "L$1"})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat, a2 a2Var) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
                        this.c = a2Var;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
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
                            boolean r0 = r10 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r10
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3$2$1
                            r0.<init>(r10)
                        L18:
                            java.lang.Object r10 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 2
                            r4 = 1
                            if (r2 == 0) goto L3e
                            if (r2 == r4) goto L34
                            if (r2 != r3) goto L2c
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L77
                        L2c:
                            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                            r9.<init>(r10)
                            throw r9
                        L34:
                            java.lang.Object r9 = r0.L$1
                            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
                            java.lang.Object r2 = r0.L$0
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L61
                        L3e:
                            kotlin.ResultKt.throwOnFailure(r10)
                            kotlinx.coroutines.flow.FlowCollector r10 = r8.a
                            r2 = r9
                            spi.mv r2 = (spi.mv) r2
                            java.util.List r2 = r2.getOptParamList()
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r5 = r8.b
                            spi.p9 r5 = r5.c
                            spi.a2 r6 = r8.c
                            r0.L$0 = r9
                            r0.L$1 = r10
                            r0.label = r4
                            java.lang.Object r2 = r5.c(r2, r6, r0)
                            if (r2 != r1) goto L5d
                            return r1
                        L5d:
                            r7 = r2
                            r2 = r9
                            r9 = r10
                            r10 = r7
                        L61:
                            java.lang.Boolean r10 = (java.lang.Boolean) r10
                            boolean r10 = r10.booleanValue()
                            if (r10 == 0) goto L77
                            r10 = 0
                            r0.L$0 = r10
                            r0.L$1 = r10
                            r0.label = r3
                            java.lang.Object r9 = r9.emit(r2, r0)
                            if (r9 != r1) goto L77
                            return r1
                        L77:
                            kotlin.Unit r9 = kotlin.Unit.INSTANCE
                            return r9
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            final Flow<mv> flow5 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
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
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4$2$1
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
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                            spi.p9 r2 = r2.c
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
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow4.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            flow2 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
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
                            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5$2$1
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
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r2 = r4.b
                            spi.p9 r2 = r2.c
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
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleStartUpdateOnlyTasks$$inlined$filter$5.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow5.collect(new AnonymousClass2(flowCollector, this), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        }
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objH = t9Var.h(a2Var, flow2, continuation);
            return objH == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objH : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object j(@NotNull final a2 a2Var, @NotNull final Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow<mv> flow2;
        Objects.requireNonNull(a2Var);
        if (a2Var.c == 1) {
            final Flow<mv> flow3 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;
                    public final /* synthetic */ a2 c;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat, a2 a2Var) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
                        this.c = a2Var;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                        /*
                            r6 = this;
                            boolean r0 = r8 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r8
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1$2$1
                            r0.<init>(r8)
                        L18:
                            java.lang.Object r8 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L61
                        L29:
                            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                            r7.<init>(r8)
                            throw r7
                        L31:
                            kotlin.ResultKt.throwOnFailure(r8)
                            kotlinx.coroutines.flow.FlowCollector r8 = r6.a
                            r2 = r7
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r2 = r2.getOptParam()
                            r4 = 0
                            if (r2 == 0) goto L49
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r4 = r6.b
                            spi.p9 r4 = r4.c
                            boolean r4 = r4.e(r2)
                            goto L56
                        L49:
                            spi.a2 r2 = r6.c
                            java.lang.String r5 = "executorHciWorkPreTreat handleWork storageCheck optParam is null bizSrc:"
                            java.lang.String r2 = spi.e.e(r5, r2)
                            java.lang.Object[] r5 = new java.lang.Object[r4]
                            spi.ct.b(r2, r5)
                        L56:
                            if (r4 == 0) goto L61
                            r0.label = r3
                            java.lang.Object r7 = r8.emit(r7, r0)
                            if (r7 != r1) goto L61
                            return r1
                        L61:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            flow2 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;
                    public final /* synthetic */ a2 c;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat, a2 a2Var) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
                        this.c = a2Var;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                        /*
                            r6 = this;
                            boolean r0 = r8 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r8
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2$2$1
                            r0.<init>(r8)
                        L18:
                            java.lang.Object r8 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L7e
                        L29:
                            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                            r7.<init>(r8)
                            throw r7
                        L31:
                            kotlin.ResultKt.throwOnFailure(r8)
                            kotlinx.coroutines.flow.FlowCollector r8 = r6.a
                            r2 = r7
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r2 = r2.getOptParam()
                            r4 = 0
                            if (r2 == 0) goto L66
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r4 = r6.b
                            spi.oa r4 = r4.b
                            java.lang.String r2 = r2.getPackageName()
                            int r2 = r4.b(r2)
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r4 = r6.b
                            spi.p9 r4 = r4.c
                            java.util.Objects.requireNonNull(r4)
                            if (r2 == 0) goto L61
                            r5 = 4
                            if (r2 == r5) goto L61
                            r5 = 7
                            if (r2 == r5) goto L61
                            r5 = 11
                            if (r2 == r5) goto L61
                            r4 = r3
                            goto L73
                        L61:
                            boolean r4 = r4.b()
                            goto L73
                        L66:
                            spi.a2 r2 = r6.c
                            java.lang.String r5 = "executorHciWorkPreTreat handleWork netAvailableCheck optParam is null bizSrc:"
                            java.lang.String r2 = spi.e.e(r5, r2)
                            java.lang.Object[] r5 = new java.lang.Object[r4]
                            spi.ct.b(r2, r5)
                        L73:
                            if (r4 == 0) goto L7e
                            r0.label = r3
                            java.lang.Object r7 = r8.emit(r7, r0)
                            if (r7 != r1) goto L7e
                            return r1
                        L7e:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow3.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        } else {
            final Flow<mv> flow4 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;
                    public final /* synthetic */ a2 c;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3$2", f = "ExecutorHciWorkPreTreat.kt", i = {0, 0}, l = {225, 224}, m = "emit", n = {"value", "$this$filter_u24lambda_u2d0"}, s = {"L$0", "L$1"})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat, a2 a2Var) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
                        this.c = a2Var;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r12) {
                        /*
                            r10 = this;
                            boolean r0 = r12 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r12
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3$2$1
                            r0.<init>(r12)
                        L18:
                            java.lang.Object r12 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 0
                            r4 = 2
                            r5 = 1
                            if (r2 == 0) goto L40
                            if (r2 == r5) goto L36
                            if (r2 != r4) goto L2e
                            kotlin.ResultKt.throwOnFailure(r12)
                            goto Laa
                        L2e:
                            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                            r11.<init>(r12)
                            throw r11
                        L36:
                            java.lang.Object r11 = r0.L$1
                            kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                            java.lang.Object r2 = r0.L$0
                            kotlin.ResultKt.throwOnFailure(r12)
                            goto L84
                        L40:
                            kotlin.ResultKt.throwOnFailure(r12)
                            kotlinx.coroutines.flow.FlowCollector r12 = r10.a
                            r2 = r11
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r2 = r2.getOptParam()
                            if (r2 == 0) goto L8d
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r3 = r10.b
                            spi.p9 r3 = r3.c
                            spi.a2 r6 = r10.c
                            r0.L$0 = r11
                            r0.L$1 = r12
                            r0.label = r5
                            spi.oa r7 = r3.a
                            java.lang.String r8 = r2.getPackageName()
                            int r7 = r7.b(r8)
                            if (r7 == 0) goto L75
                            r8 = 4
                            if (r7 == r8) goto L75
                            r8 = 7
                            if (r7 == r8) goto L75
                            r8 = 11
                            if (r7 == r8) goto L75
                            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
                            goto L7d
                        L75:
                            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
                            java.lang.Object r2 = r3.c(r2, r6, r0)
                        L7d:
                            if (r2 != r1) goto L80
                            return r1
                        L80:
                            r9 = r2
                            r2 = r11
                            r11 = r12
                            r12 = r9
                        L84:
                            java.lang.Boolean r12 = (java.lang.Boolean) r12
                            boolean r3 = r12.booleanValue()
                            r12 = r11
                            r11 = r2
                            goto L9a
                        L8d:
                            spi.a2 r2 = r10.c
                            java.lang.String r5 = "executorHciWorkPreTreat handleWork wifiOnlyCheck optParam is null bizSrc:"
                            java.lang.String r2 = spi.e.e(r5, r2)
                            java.lang.Object[] r5 = new java.lang.Object[r3]
                            spi.ct.b(r2, r5)
                        L9a:
                            if (r3 == 0) goto Laa
                            r2 = 0
                            r0.L$0 = r2
                            r0.L$1 = r2
                            r0.label = r4
                            java.lang.Object r11 = r12.emit(r11, r0)
                            if (r11 != r1) goto Laa
                            return r1
                        Laa:
                            kotlin.Unit r11 = kotlin.Unit.INSTANCE
                            return r11
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            final Flow<mv> flow5 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;
                    public final /* synthetic */ a2 c;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat, a2 a2Var) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
                        this.c = a2Var;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                        /*
                            r6 = this;
                            boolean r0 = r8 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r8
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4$2$1
                            r0.<init>(r8)
                        L18:
                            java.lang.Object r8 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L61
                        L29:
                            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                            r7.<init>(r8)
                            throw r7
                        L31:
                            kotlin.ResultKt.throwOnFailure(r8)
                            kotlinx.coroutines.flow.FlowCollector r8 = r6.a
                            r2 = r7
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r2 = r2.getOptParam()
                            r4 = 0
                            if (r2 == 0) goto L49
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r4 = r6.b
                            spi.p9 r4 = r4.c
                            boolean r4 = r4.e(r2)
                            goto L56
                        L49:
                            spi.a2 r2 = r6.c
                            java.lang.String r5 = "executorHciWorkPreTreat handleWork storageCheck optParam is null bizSrc:"
                            java.lang.String r2 = spi.e.e(r5, r2)
                            java.lang.Object[] r5 = new java.lang.Object[r4]
                            spi.ct.b(r2, r5)
                        L56:
                            if (r4 == 0) goto L61
                            r0.label = r3
                            java.lang.Object r7 = r8.emit(r7, r0)
                            if (r7 != r1) goto L61
                            return r1
                        L61:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow4.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            flow2 = new Flow<mv>() { // from class: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ ExecutorHciWorkPreTreat b;
                    public final /* synthetic */ a2 c;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5$2", f = "ExecutorHciWorkPreTreat.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, ExecutorHciWorkPreTreat executorHciWorkPreTreat, a2 a2Var) {
                        this.a = flowCollector;
                        this.b = executorHciWorkPreTreat;
                        this.c = a2Var;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) {
                        /*
                            r6 = this;
                            boolean r0 = r8 instanceof com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r8
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5$2$1
                            r0.<init>(r8)
                        L18:
                            java.lang.Object r8 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L7e
                        L29:
                            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                            r7.<init>(r8)
                            throw r7
                        L31:
                            kotlin.ResultKt.throwOnFailure(r8)
                            kotlinx.coroutines.flow.FlowCollector r8 = r6.a
                            r2 = r7
                            spi.mv r2 = (spi.mv) r2
                            com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r2 = r2.getOptParam()
                            r4 = 0
                            if (r2 == 0) goto L66
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r4 = r6.b
                            spi.oa r4 = r4.b
                            java.lang.String r2 = r2.getPackageName()
                            int r2 = r4.b(r2)
                            com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat r4 = r6.b
                            spi.p9 r4 = r4.c
                            java.util.Objects.requireNonNull(r4)
                            if (r2 == 0) goto L61
                            r5 = 4
                            if (r2 == r5) goto L61
                            r5 = 7
                            if (r2 == r5) goto L61
                            r5 = 11
                            if (r2 == r5) goto L61
                            r4 = r3
                            goto L73
                        L61:
                            boolean r4 = r4.b()
                            goto L73
                        L66:
                            spi.a2 r2 = r6.c
                            java.lang.String r5 = "executorHciWorkPreTreat handleWork netAvailableCheck optParam is null bizSrc:"
                            java.lang.String r2 = spi.e.e(r5, r2)
                            java.lang.Object[] r5 = new java.lang.Object[r4]
                            spi.ct.b(r2, r5)
                        L73:
                            if (r4 == 0) goto L7e
                            r0.label = r3
                            java.lang.Object r7 = r8.emit(r7, r0)
                            if (r7 != r1) goto L7e
                            return r1
                        L7e:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat$handleWork$$inlined$filter$5.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super mv> flowCollector, @NotNull Continuation continuation2) {
                    Object objCollect = flow5.collect(new AnonymousClass2(flowCollector, this, a2Var), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        }
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objJ = t9Var.j(a2Var, flow2, continuation);
            return objJ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJ : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void i(@NotNull a2 bizSrc, @NotNull mv carrier) {
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        Intrinsics.checkNotNullParameter(carrier, "carrier");
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            t9Var.i(bizSrc, (T) carrier);
        }
    }
}
