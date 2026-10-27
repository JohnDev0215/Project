package spi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Build;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$1;
import com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2;
import com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$3;
import java.util.ArrayList;
import java.util.List;
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
import spi.qq;

/* JADX INFO: loaded from: classes.dex */
public final class ia implements z9 {

    @NotNull
    public final oa a;

    @NotNull
    public final PackageInstaller b;

    @NotNull
    public final DataGenerator c;

    @NotNull
    public final ExecutorDialogRouterMgr d;

    @NotNull
    public final i9 e;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti", f = "ExecutorIuuWorkMulti.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {198}, m = "onInstallFinish", n = {"this", "receivePackageName", "softwareLocalWrap", "context", "primePkn", "sourceType", "state"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "S$0", "I$0"})
    public static final class a extends ContinuationImpl {
        public ia a;
        public String b;
        public qq c;
        public Context d;
        public String e;
        public short f;
        public int g;
        public /* synthetic */ Object h;
        public int j;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.h = obj;
            this.j |= Integer.MIN_VALUE;
            return ia.this.b(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti", f = "ExecutorIuuWorkMulti.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {286, 301}, m = "onReplaceFinish", n = {"this", "receivePackageName", "softwareLocalWrap", "context", "primePkn", "sourceType", "this", "receivePackageName", "softwareLocalWrap", "context", "primePkn", "sourceType"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "S$0", "L$0", "L$1", "L$2", "L$3", "L$4", "S$0"})
    public static final class b extends ContinuationImpl {
        public ia a;
        public String b;
        public qq c;
        public Context d;
        public String e;
        public short f;
        public /* synthetic */ Object g;
        public int i;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.g = obj;
            this.i |= Integer.MIN_VALUE;
            return ia.this.a(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti", f = "ExecutorIuuWorkMulti.kt", i = {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2}, l = {246, 247, 251, 263}, m = "onUninstallFinish", n = {"this", "receivePackageName", "softwareLocalWrap", "context", "primePkn", "this", "receivePackageName", "softwareLocalWrap", "context", "primePkn", "sourceType"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "S$0"})
    public static final class c extends ContinuationImpl {
        public ia a;
        public String b;
        public qq c;
        public Context d;
        public String e;
        public short f;
        public /* synthetic */ Object g;
        public int i;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.g = obj;
            this.i |= Integer.MIN_VALUE;
            return ia.this.e(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti", f = "ExecutorIuuWorkMulti.kt", i = {0, 0, 0}, l = {118}, m = "uninstall", n = {"this", "packageName", "context"}, s = {"L$0", "L$1", "L$2"})
    public static final class d extends ContinuationImpl {
        public ia a;
        public String b;
        public Context c;
        public /* synthetic */ Object d;
        public int f;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.d = obj;
            this.f |= Integer.MIN_VALUE;
            return ia.this.g(null, null, this);
        }
    }

    public ia(@NotNull oa executorSoftwareStateMgr, @NotNull PackageInstaller packageInstaller, @NotNull DataGenerator dataGenerator, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull i9 executorDbMgr) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(packageInstaller, "packageInstaller");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        this.a = executorSoftwareStateMgr;
        this.b = packageInstaller;
        this.c = dataGenerator;
        this.d = executorDialogRouterMgr;
        this.e = executorDbMgr;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00d9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0230 A[SYNTHETIC] */
    @Override // spi.z9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull spi.qq r19, @org.jetbrains.annotations.NotNull android.content.Context r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instruction units count: 682
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.ia.a(java.lang.String, spi.qq, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    @Override // spi.z9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(@org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull spi.qq r19, @org.jetbrains.annotations.NotNull android.content.Context r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.ia.b(java.lang.String, spi.qq, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.z9
    @Nullable
    public final Object c(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context, @NotNull Continuation<? super Unit> continuation) {
        ct.a(e.d("executor install start pkn: ", softwareOptParam.getPackageName(), " multiWork paramObj"), new Object[0]);
        final String packageName = softwareOptParam.getPackageName();
        final Flow flow = FlowKt.flow(new fa(packageName, this, null));
        final Flow flow2 = FlowKt.flow(new ExecutorIuuWorkMulti$install$$inlined$transform$1(new Flow<qq>() { // from class: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ String b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1$2", f = "ExecutorIuuWorkMulti.kt", i = {}, l = {225}, m = "emit", n = {}, s = {})
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
                public final java.lang.Object emit(java.lang.Object r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r11) {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L72
                    L29:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L31:
                        kotlin.ResultKt.throwOnFailure(r11)
                        kotlinx.coroutines.flow.FlowCollector r11 = r9.a
                        r2 = r10
                        spi.qq r2 = (spi.qq) r2
                        r4 = 0
                        if (r2 == 0) goto L3e
                        r5 = r3
                        goto L3f
                    L3e:
                        r5 = r4
                    L3f:
                        java.lang.String r6 = r9.b
                        java.lang.StringBuilder r7 = new java.lang.StringBuilder
                        r7.<init>()
                        java.lang.String r8 = "executor install softwareLocal is "
                        r7.append(r8)
                        r7.append(r5)
                        java.lang.String r5 = " pkn:"
                        r7.append(r5)
                        r7.append(r6)
                        java.lang.String r5 = " multiWork"
                        r7.append(r5)
                        java.lang.String r5 = r7.toString()
                        java.lang.Object[] r6 = new java.lang.Object[r4]
                        spi.ct.a(r5, r6)
                        if (r2 == 0) goto L67
                        r4 = r3
                    L67:
                        if (r4 == 0) goto L72
                        r0.label = r3
                        java.lang.Object r10 = r11.emit(r10, r0)
                        if (r10 != r1) goto L72
                        return r1
                    L72:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super qq> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector, packageName), continuation2);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, null, context));
        Object objCollect = FlowKt.flowOn(FlowKt.m1592catch(FlowKt.flow(new ExecutorIuuWorkMulti$install$$inlined$transform$3(FlowKt.flow(new ExecutorIuuWorkMulti$install$$inlined$transform$2(new Flow<List<? extends String>>() { // from class: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;
                public final /* synthetic */ String b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2$2", f = "ExecutorIuuWorkMulti.kt", i = {}, l = {226}, m = "emit", n = {}, s = {})
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
                public final java.lang.Object emit(java.lang.Object r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r10) {
                    /*
                        r8 = this;
                        boolean r0 = r10 instanceof com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r10
                        com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2$2$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2$2$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2$2$1
                        r0.<init>(r10)
                    L18:
                        java.lang.Object r10 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r10)
                        goto L77
                    L29:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r10)
                        throw r9
                    L31:
                        kotlin.ResultKt.throwOnFailure(r10)
                        kotlinx.coroutines.flow.FlowCollector r10 = r8.a
                        r2 = r9
                        java.util.List r2 = (java.util.List) r2
                        r4 = 0
                        if (r2 == 0) goto L45
                        boolean r2 = r2.isEmpty()
                        if (r2 == 0) goto L43
                        goto L45
                    L43:
                        r2 = r4
                        goto L46
                    L45:
                        r2 = r3
                    L46:
                        java.lang.String r5 = r8.b
                        java.lang.StringBuilder r6 = new java.lang.StringBuilder
                        r6.<init>()
                        java.lang.String r7 = "executor install unZipFileList NullOrEmptyState "
                        r6.append(r7)
                        r6.append(r2)
                        java.lang.String r7 = " pkn:"
                        r6.append(r7)
                        r6.append(r5)
                        java.lang.String r5 = " multiWork"
                        r6.append(r5)
                        java.lang.String r5 = r6.toString()
                        java.lang.Object[] r4 = new java.lang.Object[r4]
                        spi.ct.a(r5, r4)
                        r2 = r2 ^ r3
                        if (r2 == 0) goto L77
                        r0.label = r3
                        java.lang.Object r9 = r10.emit(r9, r0)
                        if (r9 != r1) goto L77
                        return r1
                    L77:
                        kotlin.Unit r9 = kotlin.Unit.INSTANCE
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super List<? extends String>> flowCollector, @NotNull Continuation continuation2) {
                Object objCollect2 = flow2.collect(new AnonymousClass2(flowCollector, packageName), continuation2);
                return objCollect2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect2 : Unit.INSTANCE;
            }
        }, null, this, context, packageName)), null, this, context)), new ga(packageName, null)), Dispatchers.getIO()).collect(ha.a, continuation);
        if (objCollect != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objCollect = Unit.INSTANCE;
        }
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Override // spi.z9
    public final void d(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        Intrinsics.checkNotNullParameter(context, "context");
        ct.a(e.d("executor launch start pkn:", softwareOptParam.getPackageName(), " multiWork paramObj"), new Object[0]);
        String packageName = softwareOptParam.getPackageName();
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(context, "context");
        ct.a(e.d("executor launch start pkn:", packageName, " multiWork"), new Object[0]);
        sr srVar = sr.a;
        Objects.requireNonNull(srVar);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intent launchIntentForPackage = srVar.f(context).getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage == null) {
            ct.b(e.d("executor launch intent is null return pkn:", packageName, " multiWork"), new Object[0]);
        } else {
            context.startActivity(launchIntentForPackage);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    @Override // spi.z9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(@org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull spi.qq r19, @org.jetbrains.annotations.NotNull android.content.Context r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instruction units count: 469
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.ia.e(java.lang.String, spi.qq, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.z9
    @Nullable
    public final Object f(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context, @NotNull Continuation<? super Unit> continuation) {
        ct.a(e.d("executor uninstall pkn:", softwareOptParam.getPackageName(), " multiWork paramObj"), new Object[0]);
        Object objG = g(softwareOptParam.getPackageName(), context, continuation);
        return objG == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objG : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(@org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull android.content.Context r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.ia.g(java.lang.String, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object h(String str, String str2, qq qqVar, Context context) {
        List<oq> list = qqVar.b;
        if (list == null || list.isEmpty()) {
            ct.b("executor uninstall extra.isNullOrEmpty processing_dismiss_fault_tolerant (one situation is extras all uninstalled) receivePkn:" + str + " primePkn:" + str2 + " multiWork", new Object[0]);
            this.d.addBizTypeAndData(3, DataGenerator.parseToIUUState$default(this.c, str, (short) 0, 9, 2, null)).workNotify();
        } else {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (sr.a.j(context, ((oq) obj).a)) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.size() == 0) {
                ct.a("executor uninstall extras all uninstalled receivePkn:" + str + " primePkn:" + str2 + " multiWork", new Object[0]);
                this.d.addBizTypeAndData(3, DataGenerator.parseToIUUState$default(this.c, str, (short) 0, 7, 2, null)).workNotify();
                return Unit.INSTANCE;
            }
            String str3 = ((oq) arrayList.get(0)).a;
            ct.a(hi.f(spi.d.l("executor uninstall extra toUninstallExtraPkn:", str3, " receivePkn:", str, " primePkn:"), str2, " multiWork"), new Object[0]);
            i(str3, context);
        }
        return Unit.INSTANCE;
    }

    public final void i(String str, Context context) {
        PendingIntent broadcast;
        String str2;
        ct.a(e.d("executor uninstallSilentWork openSession pkn:", str, " multiWork"), new Object[0]);
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 31) {
            broadcast = PendingIntent.getBroadcast(context, 0, intent, 67108864);
            str2 = "{\n            // 设备运行的是 …E\n            )\n        }";
        } else {
            broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
            str2 = "{\n            // 设备运行的是 …, 0, intent, 0)\n        }";
        }
        Intrinsics.checkNotNullExpressionValue(broadcast, str2);
        this.b.uninstall(str, broadcast.getIntentSender());
    }
}
