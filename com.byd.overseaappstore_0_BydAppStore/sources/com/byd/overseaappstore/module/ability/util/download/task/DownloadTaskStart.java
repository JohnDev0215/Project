package com.byd.overseaappstore.module.ability.util.download.task;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.alone.task.ExecutorTaskSchedule;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
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
public final class DownloadTaskStart extends f1 {

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
    public final ExecutorSoftwareNewTaskInTrigger g;

    @NotNull
    public final Context h;

    @NotNull
    public final SoftwareOptParam i;

    @NotNull
    public final CoroutineScope j;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$cancel$1, reason: invalid class name */
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$cancel$1", f = "DownloadTaskStart.kt", i = {}, l = {206}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$cancel$1$a */
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$cancel$1$1", f = "DownloadTaskStart.kt", i = {0}, l = {180, 183}, m = "invokeSuspend", n = {"$this$flow"}, s = {"L$0"})
        public static final class a extends SuspendLambda implements Function2<FlowCollector<? super pq>, Continuation<? super Unit>, Object> {
            public int a;
            public /* synthetic */ Object b;
            public final /* synthetic */ DownloadTaskStart c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(DownloadTaskStart downloadTaskStart, Continuation<? super a> continuation) {
                super(2, continuation);
                this.c = downloadTaskStart;
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
                    DownloadTaskStart downloadTaskStart = this.c;
                    i9 i9Var = downloadTaskStart.b;
                    String packageName = downloadTaskStart.i.getPackageName();
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
                    String packageName2 = this.c.i.getPackageName();
                    int versionCode = this.c.i.getVersionCode();
                    ct.a(e.g(d.k("dTask downloadTask cancel softwareLocal is null pkn:", packageName2, " version:", versionCode, " totalSize:"), this.c.i.getTotalSize(), " bizSrc:", this.c.a), new Object[0]);
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
            return DownloadTaskStart.this.new AnonymousClass1(continuation);
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
                Flow flow = FlowKt.flow(new DownloadTaskStart$cancel$1$invokeSuspend$$inlined$transform$1(FlowKt.flow(new a(DownloadTaskStart.this, null)), null, DownloadTaskStart.this));
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

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$start$1, reason: invalid class name and case insensitive filesystem */
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$start$1", f = "DownloadTaskStart.kt", i = {0, 0, 1, 1, 2, 2}, l = {83, 99, 101, 152}, m = "invokeSuspend", n = {"fileSrc", "fileSrcLength", "fileSrc", "fileSrcLength", "fileSrc", "fileSrcLength"}, s = {"L$0", "J$0", "L$0", "J$0", "L$0", "J$0"})
    public static final class C01981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public File a;
        public long b;
        public int c;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$start$1$a */
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$start$1$4", f = "DownloadTaskStart.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function3<FlowCollector<? super Integer>, Throwable, Continuation<? super Unit>, Object> {
            public /* synthetic */ Throwable a;
            public final /* synthetic */ DownloadTaskStart b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(DownloadTaskStart downloadTaskStart, Continuation<? super a> continuation) {
                super(3, continuation);
                this.b = downloadTaskStart;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(FlowCollector<? super Integer> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                a aVar = new a(this.b, continuation);
                aVar.a = th;
                return aVar.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                Throwable th = this.a;
                DownloadTaskStart downloadTaskStart = this.b;
                downloadTaskStart.b(th, downloadTaskStart.i);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart$start$1$b */
        public static final class b<T> implements FlowCollector {
            public final /* synthetic */ DownloadTaskStart a;

            public b(DownloadTaskStart downloadTaskStart) {
                this.a = downloadTaskStart;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                if (((Number) obj).intValue() == 100) {
                    this.a.a(1);
                }
                return Unit.INSTANCE;
            }
        }

        public C01981(Continuation<? super C01981> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return DownloadTaskStart.this.new C01981(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0161 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x01a4 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 424
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart.C01981.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadTaskStart(@NotNull i9 executorDbMgr, @NotNull h7 downloadRepository, @NotNull DataGenerator dataGenerator, @NotNull oa executorSoftwareStateMgr, @NotNull ExecutorTaskSchedule executorTaskSchedule, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull Context context, @NotNull SoftwareOptParam softwareOptParam, @NotNull a2 bizSrc) {
        super(bizSrc);
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(downloadRepository, "downloadRepository");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorTaskSchedule, "executorTaskSchedule");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        this.b = executorDbMgr;
        this.c = downloadRepository;
        this.d = dataGenerator;
        this.e = executorSoftwareStateMgr;
        this.f = executorTaskSchedule;
        this.g = executorSoftwareNewTaskInTrigger;
        this.h = context;
        this.i = softwareOptParam;
        this.j = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null)));
    }

    @Override // spi.i7
    public final void a(int i) {
        Pair<a2, Triple<String, Integer, SoftwareOptParam>> pairGenerateDownloadTaskProcessPair;
        if (i == 1) {
            pairGenerateDownloadTaskProcessPair = this.d.generateDownloadTaskProcessPair("downloadTask", this.a, i, this.i);
            String packageName = this.i.getPackageName();
            int versionCode = this.i.getVersionCode();
            long totalSize = this.i.getTotalSize();
            a2 a2Var = this.a;
            StringBuilder sbH = xi.h("dTask downloadTask extNotify downloadFinish send tag:", i, " pkn:", packageName, " version:");
            d.s(sbH, versionCode, " totalSize:", totalSize);
            ct.a(e.i(sbH, " bizSrc:", a2Var), new Object[0]);
        } else if (i == 2) {
            pairGenerateDownloadTaskProcessPair = this.d.generateDownloadTaskProcessPair("downloadTask", this.a, i, this.i);
            String packageName2 = this.i.getPackageName();
            int versionCode2 = this.i.getVersionCode();
            long totalSize2 = this.i.getTotalSize();
            a2 a2Var2 = this.a;
            StringBuilder sbH2 = xi.h("dTask downloadTask extNotify cancelFinish send tag:", i, " pkn:", packageName2, " version:");
            d.s(sbH2, versionCode2, " totalSize:", totalSize2);
            ct.a(e.i(sbH2, " bizSrc:", a2Var2), new Object[0]);
        } else {
            if (i != 3) {
                String packageName3 = this.i.getPackageName();
                int versionCode3 = this.i.getVersionCode();
                long totalSize3 = this.i.getTotalSize();
                a2 a2Var3 = this.a;
                StringBuilder sbH3 = xi.h("dTask downloadTask extNotify noIntervene tag:", i, " pkn:", packageName3, " version:");
                d.s(sbH3, versionCode3, " totalSize:", totalSize3);
                ct.a(e.i(sbH3, " bizSrc:", a2Var3), new Object[0]);
                return;
            }
            pairGenerateDownloadTaskProcessPair = this.d.generateDownloadTaskProcessPair("downloadTask", this.a, i, this.i);
            String packageName4 = this.i.getPackageName();
            int versionCode4 = this.i.getVersionCode();
            long totalSize4 = this.i.getTotalSize();
            a2 a2Var4 = this.a;
            StringBuilder sbH4 = xi.h("dTask downloadTask extNotify netInterrupt send tag:", i, " pkn:", packageName4, " version:");
            d.s(sbH4, versionCode4, " totalSize:", totalSize4);
            ct.a(e.i(sbH4, " bizSrc:", a2Var4), new Object[0]);
        }
        this.f.notifyTaskProcess(pairGenerateDownloadTaskProcessPair);
    }

    @Override // spi.i7
    public final synchronized void cancel() {
        boolean zIsActive = CoroutineScopeKt.isActive(this.j);
        ct.a("dTask downloadTask cancel scopeState:" + zIsActive + " pkn:" + this.i.getPackageName() + " version:" + this.i.getVersionCode() + " totalSize:" + this.i.getTotalSize() + " bizSrc:" + this.a, new Object[0]);
        if (zIsActive) {
            CoroutineScopeKt.cancel$default(this.j, null, 1, null);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null))), null, null, new AnonymousClass1(null), 3, null);
        }
    }

    @Override // spi.i7
    public final synchronized void start() {
        BuildersKt__Builders_commonKt.launch$default(this.j, null, null, new C01981(null), 3, null);
    }
}
