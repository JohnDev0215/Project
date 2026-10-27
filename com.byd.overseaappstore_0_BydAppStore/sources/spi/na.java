package spi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Build;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.na;
import spi.qq;

/* JADX INFO: loaded from: classes.dex */
public final class na implements z9 {

    @NotNull
    public final oa a;

    @NotNull
    public final PackageInstaller b;

    @NotNull
    public final ExecutorDialogRouterMgr c;

    @NotNull
    public final DataGenerator d;

    @NotNull
    public final i9 e;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle", f = "ExecutorIuuWorkSingle.kt", i = {0, 0, 0, 0}, l = {133}, m = "onInstallFinish", n = {"this", "receivePackageName", "softwareLocalWrap", "state"}, s = {"L$0", "L$1", "L$2", "I$0"})
    public static final class a extends ContinuationImpl {
        public na a;
        public String b;
        public qq c;
        public int d;
        public /* synthetic */ Object e;
        public int g;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.e = obj;
            this.g |= Integer.MIN_VALUE;
            return na.this.b(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle", f = "ExecutorIuuWorkSingle.kt", i = {0, 0}, l = {169}, m = "onReplaceFinish", n = {"this", "softwareLocalWrap"}, s = {"L$0", "L$1"})
    public static final class b extends ContinuationImpl {
        public na a;
        public qq b;
        public /* synthetic */ Object c;
        public int e;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.c = obj;
            this.e |= Integer.MIN_VALUE;
            return na.this.a(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle", f = "ExecutorIuuWorkSingle.kt", i = {0, 0}, l = {155}, m = "onUninstallFinish", n = {"this", "softwareLocalWrap"}, s = {"L$0", "L$1"})
    public static final class c extends ContinuationImpl {
        public na a;
        public qq b;
        public /* synthetic */ Object c;
        public int e;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.c = obj;
            this.e |= Integer.MIN_VALUE;
            return na.this.e(null, null, null, this);
        }
    }

    public na(@NotNull oa executorSoftwareStateMgr, @NotNull PackageInstaller packageInstaller, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull DataGenerator dataGenerator, @NotNull i9 executorDbMgr) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(packageInstaller, "packageInstaller");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        this.a = executorSoftwareStateMgr;
        this.b = packageInstaller;
        this.c = executorDialogRouterMgr;
        this.d = dataGenerator;
        this.e = executorDbMgr;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.z9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.NotNull spi.qq r9, @org.jetbrains.annotations.NotNull android.content.Context r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof spi.na.b
            if (r0 == 0) goto L13
            r0 = r11
            spi.na$b r0 = (spi.na.b) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            spi.na$b r0 = new spi.na$b
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            spi.qq r9 = r0.b
            spi.na r8 = r0.a
            kotlin.ResultKt.throwOnFailure(r11)
            goto L65
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L35:
            kotlin.ResultKt.throwOnFailure(r11)
            spi.pq r11 = r9.a
            short r2 = r11.m
            java.lang.String r11 = r11.a
            short r2 = (short) r2
            java.lang.String r4 = "executor onReplaceFinish common,DelCommonFile doNotify sourceType:"
            java.lang.String r5 = " receivePkn:"
            java.lang.String r6 = " primePkn:"
            java.lang.StringBuilder r2 = spi.xi.h(r4, r2, r5, r8, r6)
            java.lang.String r4 = " singleWork"
            java.lang.String r11 = spi.hi.f(r2, r11, r4)
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            spi.ct.a(r11, r2)
            spi.oa r11 = r7.a
            r0.a = r7
            r0.b = r9
            r0.e = r3
            java.lang.Object r8 = r11.g(r8, r10, r0)
            if (r8 != r1) goto L64
            return r1
        L64:
            r8 = r7
        L65:
            spi.zb r10 = spi.zb.a
            spi.pq r11 = r9.a
            java.lang.String r11 = r11.d
            r10.b(r11)
            com.byd.overseaappstore.module.ability.data.generator.DataGenerator r10 = r8.d
            r11 = 6
            com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState r9 = r10.parseToIUUState(r9, r11)
            com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr r8 = r8.c
            r10 = 3
            com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr r8 = r8.addBizTypeAndData(r10, r9)
            r8.workNotify()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.na.a(java.lang.String, spi.qq, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.z9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(@org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.NotNull spi.qq r9, @org.jetbrains.annotations.NotNull android.content.Context r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r7 = this;
            boolean r10 = r11 instanceof spi.na.a
            if (r10 == 0) goto L13
            r10 = r11
            spi.na$a r10 = (spi.na.a) r10
            int r0 = r10.g
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r0 & r1
            if (r2 == 0) goto L13
            int r0 = r0 - r1
            r10.g = r0
            goto L18
        L13:
            spi.na$a r10 = new spi.na$a
            r10.<init>(r11)
        L18:
            java.lang.Object r11 = r10.e
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.g
            r2 = 6
            r3 = 1
            if (r1 == 0) goto L3a
            if (r1 != r3) goto L32
            int r8 = r10.d
            spi.qq r9 = r10.c
            java.lang.String r0 = r10.b
            spi.na r10 = r10.a
            kotlin.ResultKt.throwOnFailure(r11)
            goto L70
        L32:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3a:
            kotlin.ResultKt.throwOnFailure(r11)
            spi.pq r11 = r9.a
            short r1 = r11.m
            java.lang.String r11 = r11.a
            short r1 = (short) r1
            java.lang.String r4 = "executor onInstallFinish common,DelCommonFile sourceType:"
            java.lang.String r5 = " receivePkn:"
            java.lang.String r6 = " primePkn:"
            java.lang.StringBuilder r1 = spi.xi.h(r4, r1, r5, r8, r6)
            java.lang.String r4 = " singleWork"
            java.lang.String r11 = spi.hi.f(r1, r11, r4)
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            spi.ct.a(r11, r1)
            spi.i9 r11 = r7.e
            r10.a = r7
            r10.b = r8
            r10.c = r9
            r10.d = r2
            r10.g = r3
            java.lang.Object r10 = r11.t(r8, r2, r10)
            if (r10 != r0) goto L6d
            return r0
        L6d:
            r10 = r7
            r0 = r8
            r8 = r2
        L70:
            spi.oa r11 = r10.a
            com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData r1 = new com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData
            r3 = 100
            r1.<init>(r3, r8)
            r11.h(r0, r1)
            spi.zb r8 = spi.zb.a
            spi.pq r11 = r9.a
            java.lang.String r11 = r11.d
            r8.b(r11)
            com.byd.overseaappstore.module.ability.data.generator.DataGenerator r8 = r10.d
            com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState r8 = r8.parseToIUUState(r9, r2)
            com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr r9 = r10.c
            r10 = 3
            com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr r8 = r9.addBizTypeAndData(r10, r8)
            r8.workNotify()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.na.b(java.lang.String, spi.qq, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.z9
    @Nullable
    public final Object c(@NotNull SoftwareOptParam softwareOptParam, @NotNull final Context context, @NotNull Continuation<? super Unit> continuation) {
        ct.a(e.d("executor install start pkn: ", softwareOptParam.getPackageName(), " singleWork paramObj"), new Object[0]);
        final String packageName = softwareOptParam.getPackageName();
        ct.a(e.d("executor install start pkn: ", packageName, " singleWork"), new Object[0]);
        final Flow flow = FlowKt.flow(new ja(packageName, this, null));
        final Flow<qq> flow2 = new Flow<qq>() { // from class: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ String b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1$2", f = "ExecutorIuuWorkSingle.kt", i = {}, l = {225}, m = "emit", n = {}, s = {})
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

                public AnonymousClass2(FlowCollector flowCollector, String str) {
                    this.a = flowCollector;
                    this.b = str;
                }

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
                        boolean r0 = r12 instanceof com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r12
                        com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1$2$1
                        r0.<init>(r12)
                    L18:
                        java.lang.Object r12 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r12)
                        goto L9d
                    L2a:
                        java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                        java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                        r11.<init>(r12)
                        throw r11
                    L32:
                        kotlin.ResultKt.throwOnFailure(r12)
                        kotlinx.coroutines.flow.FlowCollector r12 = r10.a
                        r2 = r11
                        spi.qq r2 = (spi.qq) r2
                        r4 = 0
                        if (r2 == 0) goto L3f
                        r5 = r3
                        goto L40
                    L3f:
                        r5 = r4
                    L40:
                        if (r2 == 0) goto L58
                        spi.pq r6 = r2.a
                        if (r6 == 0) goto L58
                        java.lang.String r6 = r6.d
                        if (r6 == 0) goto L58
                        int r6 = r6.length()
                        if (r6 <= 0) goto L52
                        r6 = r3
                        goto L53
                    L52:
                        r6 = r4
                    L53:
                        java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
                        goto L59
                    L58:
                        r6 = 0
                    L59:
                        java.lang.String r7 = r10.b
                        java.lang.StringBuilder r8 = new java.lang.StringBuilder
                        r8.<init>()
                        java.lang.String r9 = "executor install softwareLocal is "
                        r8.append(r9)
                        r8.append(r5)
                        java.lang.String r5 = " sourceDir:"
                        r8.append(r5)
                        r8.append(r6)
                        java.lang.String r5 = " pkn:"
                        r8.append(r5)
                        java.lang.String r5 = " singleWork"
                        java.lang.String r5 = spi.hi.f(r8, r7, r5)
                        java.lang.Object[] r6 = new java.lang.Object[r4]
                        spi.ct.a(r5, r6)
                        if (r2 == 0) goto L92
                        spi.pq r2 = r2.a
                        java.lang.String r2 = r2.d
                        int r2 = r2.length()
                        if (r2 <= 0) goto L8e
                        r2 = r3
                        goto L8f
                    L8e:
                        r2 = r4
                    L8f:
                        if (r2 == 0) goto L92
                        r4 = r3
                    L92:
                        if (r4 == 0) goto L9d
                        r0.label = r3
                        java.lang.Object r11 = r12.emit(r11, r0)
                        if (r11 != r1) goto L9d
                        return r1
                    L9d:
                        kotlin.Unit r11 = kotlin.Unit.INSTANCE
                        return r11
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super qq> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector, packageName), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow flowOnEmpty = FlowKt.onEmpty(new Flow<qq>() { // from class: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ String b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2$2", f = "ExecutorIuuWorkSingle.kt", i = {}, l = {226}, m = "emit", n = {}, s = {})
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

                public AnonymousClass2(FlowCollector flowCollector, String str) {
                    this.a = flowCollector;
                    this.b = str;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object emit(java.lang.Object r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L89
                    L29:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r9)
                        throw r8
                    L31:
                        kotlin.ResultKt.throwOnFailure(r9)
                        kotlinx.coroutines.flow.FlowCollector r9 = r7.a
                        r2 = r8
                        spi.qq r2 = (spi.qq) r2
                        spi.zb r4 = spi.zb.a
                        if (r2 == 0) goto L45
                        spi.pq r2 = r2.a
                        if (r2 == 0) goto L45
                        java.lang.String r2 = r2.d
                        if (r2 != 0) goto L47
                    L45:
                        java.lang.String r2 = ""
                    L47:
                        java.util.Objects.requireNonNull(r4)
                        java.lang.String r4 = "fileDirName"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
                        java.io.File r4 = new java.io.File
                        r4.<init>(r2)
                        boolean r2 = r4.exists()
                        java.lang.String r4 = r7.b
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder
                        r5.<init>()
                        java.lang.String r6 = "executor install softwareLocal apk is exist "
                        r5.append(r6)
                        r5.append(r2)
                        java.lang.String r6 = " pkn:"
                        r5.append(r6)
                        r5.append(r4)
                        java.lang.String r4 = " singleWork"
                        r5.append(r4)
                        java.lang.String r4 = r5.toString()
                        r5 = 0
                        java.lang.Object[] r5 = new java.lang.Object[r5]
                        spi.ct.a(r4, r5)
                        if (r2 == 0) goto L89
                        r0.label = r3
                        java.lang.Object r8 = r9.emit(r8, r0)
                        if (r8 != r1) goto L89
                        return r1
                    L89:
                        kotlin.Unit r8 = kotlin.Unit.INSTANCE
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super qq> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect = flow2.collect(new AnonymousClass2(flowCollector, packageName), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new ka(packageName, this, null));
        Object objCollect = FlowKt.flowOn(FlowKt.m1592catch(new Flow<Boolean>() { // from class: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ na b;
                public final /* synthetic */ Context c;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$map$1$2", f = "ExecutorIuuWorkSingle.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    public Object L$0;
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

                public AnonymousClass2(FlowCollector flowCollector, na naVar, Context context) {
                    this.a = flowCollector;
                    this.b = naVar;
                    this.c = context;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
                /* JADX WARN: Removed duplicated region for block: B:91:0x01c6  */
                /* JADX WARN: Removed duplicated region for block: B:92:0x01ec  */
                /* JADX WARN: Removed duplicated region for block: B:95:0x0201 A[RETURN] */
                /* JADX WARN: Type inference failed for: r16v1 */
                /* JADX WARN: Type inference failed for: r16v10 */
                /* JADX WARN: Type inference failed for: r16v11 */
                /* JADX WARN: Type inference failed for: r16v12 */
                /* JADX WARN: Type inference failed for: r16v13 */
                /* JADX WARN: Type inference failed for: r16v14 */
                /* JADX WARN: Type inference failed for: r16v15 */
                /* JADX WARN: Type inference failed for: r16v16 */
                /* JADX WARN: Type inference failed for: r16v17 */
                /* JADX WARN: Type inference failed for: r16v18 */
                /* JADX WARN: Type inference failed for: r16v2 */
                /* JADX WARN: Type inference failed for: r16v3 */
                /* JADX WARN: Type inference failed for: r16v5 */
                /* JADX WARN: Type inference failed for: r16v6 */
                /* JADX WARN: Type inference failed for: r16v8, types: [long] */
                /* JADX WARN: Type inference failed for: r16v9 */
                /* JADX WARN: Type inference failed for: r1v6, types: [kotlinx.coroutines.flow.FlowCollector] */
                /* JADX WARN: Type inference failed for: r4v1, types: [kotlinx.coroutines.flow.FlowCollector] */
                /* JADX WARN: Type inference failed for: r4v16 */
                /* JADX WARN: Type inference failed for: r4v17 */
                /* JADX WARN: Type inference failed for: r4v3 */
                /* JADX WARN: Type inference failed for: r4v5 */
                /* JADX WARN: Type inference failed for: r6v2, types: [android.content.pm.PackageInstaller$Session, java.io.Closeable] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object emit(java.lang.Object r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r23) {
                    /*
                        Method dump skipped, instruction units count: 517
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super Boolean> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect2 = flowOnEmpty.collect(new AnonymousClass2(flowCollector, this, context), continuation2);
                return objCollect2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect2 : Unit.INSTANCE;
            }
        }, new la(packageName, this, null)), Dispatchers.getIO()).collect(new ma(packageName, this), continuation);
        if (objCollect != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objCollect = Unit.INSTANCE;
        }
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Override // spi.z9
    public final void d(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        Intrinsics.checkNotNullParameter(context, "context");
        ct.a(e.d("executor launch start pkn: ", softwareOptParam.getPackageName(), " singleWork paramObj"), new Object[0]);
        String packageName = softwareOptParam.getPackageName();
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(context, "context");
        ct.a(e.d("executor launch start pkn: ", packageName, " singleWork"), new Object[0]);
        sr srVar = sr.a;
        Objects.requireNonNull(srVar);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intent launchIntentForPackage = srVar.f(context).getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage == null) {
            ct.b(e.d("executor launch intent is null return pkn: ", packageName, " singleWork"), new Object[0]);
        } else {
            context.startActivity(launchIntentForPackage);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.z9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(@org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull spi.qq r8, @org.jetbrains.annotations.NotNull android.content.Context r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r6 = this;
            boolean r9 = r10 instanceof spi.na.c
            if (r9 == 0) goto L13
            r9 = r10
            spi.na$c r9 = (spi.na.c) r9
            int r0 = r9.e
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r0 & r1
            if (r2 == 0) goto L13
            int r0 = r0 - r1
            r9.e = r0
            goto L18
        L13:
            spi.na$c r9 = new spi.na$c
            r9.<init>(r10)
        L18:
            java.lang.Object r10 = r9.c
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.e
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            spi.qq r8 = r9.b
            spi.na r7 = r9.a
            kotlin.ResultKt.throwOnFailure(r10)
            goto L65
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L35:
            kotlin.ResultKt.throwOnFailure(r10)
            spi.pq r10 = r8.a
            short r1 = r10.m
            java.lang.String r10 = r10.a
            short r1 = (short) r1
            java.lang.String r3 = "executor onUninstallFinish common,DelCommonFile doNotify sourceType:"
            java.lang.String r4 = " receivePkn:"
            java.lang.String r5 = " primePkn:"
            java.lang.StringBuilder r1 = spi.xi.h(r3, r1, r4, r7, r5)
            java.lang.String r3 = " singleWork"
            java.lang.String r10 = spi.hi.f(r1, r10, r3)
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            spi.ct.a(r10, r1)
            spi.oa r10 = r6.a
            r9.a = r6
            r9.b = r8
            r9.e = r2
            java.lang.Object r7 = r10.f(r7, r9)
            if (r7 != r0) goto L64
            return r0
        L64:
            r7 = r6
        L65:
            com.byd.overseaappstore.module.ability.data.generator.DataGenerator r9 = r7.d
            r10 = 7
            com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState r8 = r9.parseToIUUState(r8, r10)
            com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr r7 = r7.c
            r9 = 3
            com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr r7 = r7.addBizTypeAndData(r9, r8)
            r7.workNotify()
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.na.e(java.lang.String, spi.qq, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.z9
    @Nullable
    public final Object f(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context, @NotNull Continuation<? super Unit> continuation) {
        PendingIntent broadcast;
        String str;
        ct.a(e.d("executor uninstall pkn: ", softwareOptParam.getPackageName(), " singleWork paramObj"), new Object[0]);
        String packageName = softwareOptParam.getPackageName();
        ct.a(e.d("executor uninstall pkn: ", packageName, " singleWork"), new Object[0]);
        ct.a("executor uninstallSilentWork openSession pkn:" + packageName + " singleWork", new Object[0]);
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 31) {
            broadcast = PendingIntent.getBroadcast(context, 0, intent, 67108864);
            str = "{\n            // 设备运行的是 …E\n            )\n        }";
        } else {
            broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
            str = "{\n            // 设备运行的是 …, 0, intent, 0)\n        }";
        }
        Intrinsics.checkNotNullExpressionValue(broadcast, str);
        this.b.uninstall(packageName, broadcast.getIntentSender());
        Unit unit = Unit.INSTANCE;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return unit;
    }
}
