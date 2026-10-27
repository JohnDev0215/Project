package com.byd.overseaappstore.module.ability.util.download.task;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.alone.task.ExecutorTaskSchedule;
import java.io.File;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.ct;
import spi.d;
import spi.e;
import spi.f1;
import spi.h7;
import spi.i9;
import spi.oa;
import spi.pq;
import spi.qq;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
public final class DownloadTaskUpdateStart extends f1 {

    @NotNull
    public final i9 b;

    @NotNull
    public final h7 c;

    @NotNull
    public final DataGenerator d;

    @NotNull
    public final oa e;

    @NotNull
    public final ExecutorTaskSchedule f;

    @NotNull
    public final Context g;

    @NotNull
    public final SoftwareOptParam h;

    @NotNull
    public final CoroutineScope i;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$cancel$1, reason: invalid class name */
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$cancel$1", f = "DownloadTaskUpdateStart.kt", i = {}, l = {214}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$cancel$1$a */
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$cancel$1$1", f = "DownloadTaskUpdateStart.kt", i = {0}, l = {188, 191}, m = "invokeSuspend", n = {"$this$flow"}, s = {"L$0"})
        public static final class a extends SuspendLambda implements Function2<FlowCollector<? super pq>, Continuation<? super Unit>, Object> {
            public int a;
            public /* synthetic */ Object b;
            public final /* synthetic */ DownloadTaskUpdateStart c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(DownloadTaskUpdateStart downloadTaskUpdateStart, Continuation<? super a> continuation) {
                super(2, continuation);
                this.c = downloadTaskUpdateStart;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.c, continuation);
                aVar.b = obj;
                return aVar;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(FlowCollector<? super pq> flowCollector, Continuation<? super Unit> continuation) {
                return ((a) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                FlowCollector flowCollector;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.a;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    flowCollector = (FlowCollector) this.b;
                    DownloadTaskUpdateStart downloadTaskUpdateStart = this.c;
                    i9 i9Var = downloadTaskUpdateStart.b;
                    String packageName = downloadTaskUpdateStart.h.getPackageName();
                    this.b = flowCollector;
                    this.a = 1;
                    obj = i9Var.z(packageName, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    flowCollector = (FlowCollector) this.b;
                    ResultKt.throwOnFailure(obj);
                }
                qq qqVar = (qq) obj;
                if (qqVar == null) {
                    String packageName2 = this.c.h.getPackageName();
                    int versionCode = this.c.h.getVersionCode();
                    ct.a(e.g(d.k("dTask downloadUpdateTask cancel softwareLocal is null pkn:", packageName2, " version:", versionCode, " totalSize:"), this.c.h.getTotalSize(), " bizSrc:", this.c.a), new Object[0]);
                } else {
                    pq pqVar = qqVar.a;
                    this.b = null;
                    this.a = 2;
                    if (flowCollector.emit(pqVar, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return DownloadTaskUpdateStart.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = FlowKt.flow(new DownloadTaskUpdateStart$cancel$1$invokeSuspend$$inlined$transform$1(FlowKt.flow(new a(DownloadTaskUpdateStart.this, null)), null, DownloadTaskUpdateStart.this));
                this.a = 1;
                if (FlowKt.collect(flow, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1, reason: invalid class name and case insensitive filesystem */
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1", f = "DownloadTaskUpdateStart.kt", i = {1, 1, 1, 2, 2, 2}, l = {58, 94, 108, 160}, m = "invokeSuspend", n = {"fileSrc", "softwareOptParam", "fileSrcLength", "fileSrc", "softwareOptParam", "fileSrcLength"}, s = {"L$0", "L$1", "J$0", "L$0", "L$1", "J$0"})
    public static final class C02001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public File a;
        public SoftwareOptParam b;
        public long c;
        public int d;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$a */
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$3", f = "DownloadTaskUpdateStart.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function3<FlowCollector<? super Integer>, Throwable, Continuation<? super Unit>, Object> {
            public /* synthetic */ Throwable a;
            public final /* synthetic */ DownloadTaskUpdateStart b;
            public final /* synthetic */ SoftwareOptParam c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(DownloadTaskUpdateStart downloadTaskUpdateStart, SoftwareOptParam softwareOptParam, Continuation<? super a> continuation) {
                super(3, continuation);
                this.b = downloadTaskUpdateStart;
                this.c = softwareOptParam;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(FlowCollector<? super Integer> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                a aVar = new a(this.b, this.c, continuation);
                aVar.a = th;
                return aVar.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                this.b.b(this.a, this.c);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$b */
        public static final class b<T> implements FlowCollector {
            public final /* synthetic */ DownloadTaskUpdateStart a;

            public b(DownloadTaskUpdateStart downloadTaskUpdateStart) {
                this.a = downloadTaskUpdateStart;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                if (((Number) obj).intValue() == 100) {
                    this.a.a(1);
                }
                return Unit.INSTANCE;
            }
        }

        public C02001(Continuation<? super C02001> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return DownloadTaskUpdateStart.this.new C02001(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x01ee A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0233 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r30) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 567
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart.C02001.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadTaskUpdateStart(@NotNull i9 executorDbMgr, @NotNull h7 downloadRepository, @NotNull DataGenerator dataGenerator, @NotNull oa executorSoftwareStateMgr, @NotNull ExecutorTaskSchedule executorTaskSchedule, @NotNull Context context, @NotNull SoftwareOptParam softwareOptParam, @NotNull a2 bizSrc) {
        super(bizSrc);
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(downloadRepository, "downloadRepository");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorTaskSchedule, "executorTaskSchedule");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        this.b = executorDbMgr;
        this.c = downloadRepository;
        this.d = dataGenerator;
        this.e = executorSoftwareStateMgr;
        this.f = executorTaskSchedule;
        this.g = context;
        this.h = softwareOptParam;
        this.i = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null)));
    }

    @Override // spi.i7
    public final void a(int i) {
        Pair<a2, Triple<String, Integer, SoftwareOptParam>> pairGenerateDownloadTaskProcessPair;
        if (i == 1) {
            pairGenerateDownloadTaskProcessPair = this.d.generateDownloadTaskProcessPair("downloadUpdateTask", this.a, i, this.h);
            String packageName = this.h.getPackageName();
            int versionCode = this.h.getVersionCode();
            long totalSize = this.h.getTotalSize();
            a2 a2Var = this.a;
            StringBuilder sbH = xi.h("dTask downloadUpdateTask downloadFinish send tag:", i, " pkn:", packageName, " version:");
            d.s(sbH, versionCode, " totalSize:", totalSize);
            ct.a(e.i(sbH, " bizSrc:", a2Var), new Object[0]);
        } else if (i == 2) {
            pairGenerateDownloadTaskProcessPair = this.d.generateDownloadTaskProcessPair("downloadUpdateTask", this.a, i, this.h);
            String packageName2 = this.h.getPackageName();
            int versionCode2 = this.h.getVersionCode();
            long totalSize2 = this.h.getTotalSize();
            a2 a2Var2 = this.a;
            StringBuilder sbH2 = xi.h("dTask downloadUpdateTask extNotify cancelFinish send tag:", i, " pkn:", packageName2, " version:");
            d.s(sbH2, versionCode2, " totalSize:", totalSize2);
            ct.a(e.i(sbH2, " bizSrc:", a2Var2), new Object[0]);
        } else {
            if (i != 3) {
                String packageName3 = this.h.getPackageName();
                int versionCode3 = this.h.getVersionCode();
                long totalSize3 = this.h.getTotalSize();
                a2 a2Var3 = this.a;
                StringBuilder sbH3 = xi.h("dTask downloadUpdateTask extNotify noIntervene tag:", i, " pkn:", packageName3, " version:");
                d.s(sbH3, versionCode3, " totalSize:", totalSize3);
                ct.a(e.i(sbH3, " bizSrc:", a2Var3), new Object[0]);
                return;
            }
            pairGenerateDownloadTaskProcessPair = this.d.generateDownloadTaskProcessPair("downloadUpdateTask", this.a, i, this.h);
            String packageName4 = this.h.getPackageName();
            int versionCode4 = this.h.getVersionCode();
            long totalSize4 = this.h.getTotalSize();
            a2 a2Var4 = this.a;
            StringBuilder sbH4 = xi.h("dTask downloadUpdateTask extNotify netInterrupt send tag:", i, " pkn:", packageName4, " version:");
            d.s(sbH4, versionCode4, " totalSize:", totalSize4);
            ct.a(e.i(sbH4, " bizSrc:", a2Var4), new Object[0]);
        }
        this.f.notifyTaskProcess(pairGenerateDownloadTaskProcessPair);
    }

    @Override // spi.i7
    public final synchronized void cancel() {
        boolean zIsActive = CoroutineScopeKt.isActive(this.i);
        ct.a("dTask downloadUpdateTask cancel scopeState:" + zIsActive + " pkn:" + this.h.getPackageName() + " version:" + this.h.getVersionCode() + " totalSize:" + this.h.getTotalSize() + " bizSrc:" + this.a, new Object[0]);
        if (zIsActive) {
            CoroutineScopeKt.cancel$default(this.i, null, 1, null);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null))), null, null, new AnonymousClass1(null), 3, null);
        }
    }

    @Override // spi.i7
    public final synchronized void start() {
        BuildersKt__Builders_commonKt.launch$default(this.i, null, null, new C02001(null), 3, null);
    }
}
