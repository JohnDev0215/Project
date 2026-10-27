package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.alone.task.ExecutorTaskSchedule;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class n9 extends m9<CacheCarrier> {

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
    public final l9 h;

    @NotNull
    public final Context i;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ a2 b;

        /* JADX INFO: renamed from: spi.n9$a$a, reason: collision with other inner class name */
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCacheExecute$handleWaitToWork$2", f = "ExecutorHciCacheExecute.kt", i = {0, 0, 1, 1}, l = {91, 114}, m = "emit", n = {"this", "optParam", "this", "optParam"}, s = {"L$0", "L$1", "L$0", "L$1"})
        public static final class C0136a extends ContinuationImpl {
            public a a;
            public SoftwareOptParam b;
            public /* synthetic */ Object c;
            public final /* synthetic */ a<T> d;
            public int e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C0136a(a<? super T> aVar, Continuation<? super C0136a> continuation) {
                super(continuation);
                this.d = aVar;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.c = obj;
                this.e |= Integer.MIN_VALUE;
                return this.d.emit(null, this);
            }
        }

        public a(a2 a2Var) {
            this.b = a2Var;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(@org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
            /*
                Method dump skipped, instruction units count: 300
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.n9.a.emit(com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCacheExecute", f = "ExecutorHciCacheExecute.kt", i = {0, 0, 1, 1}, l = {55, 66}, m = "handleWorkToWait", n = {"this", "optParam", "this", "optParam"}, s = {"L$0", "L$1", "L$0", "L$1"})
    public static final class b extends ContinuationImpl {
        public n9 a;
        public SoftwareOptParam b;
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
            return n9.this.b(null, null, this);
        }
    }

    public n9(@NotNull i9 executorDbMgr, @NotNull h7 downloadRepository, @NotNull DataGenerator dataGenerator, @NotNull oa executorSoftwareStateMgr, @NotNull ExecutorTaskSchedule executorTaskSchedule, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull l9 executorDownloadTaskMgr, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(downloadRepository, "downloadRepository");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorTaskSchedule, "executorTaskSchedule");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(executorDownloadTaskMgr, "executorDownloadTaskMgr");
        Intrinsics.checkNotNullParameter(context, "context");
        this.b = executorDbMgr;
        this.c = downloadRepository;
        this.d = dataGenerator;
        this.e = executorSoftwareStateMgr;
        this.f = executorTaskSchedule;
        this.g = executorSoftwareNewTaskInTrigger;
        this.h = executorDownloadTaskMgr;
        this.i = context;
    }

    @Override // spi.m9
    @Nullable
    public final Object a(@NotNull a2 a2Var, @NotNull Flow<? extends CacheCarrier> flow, @NotNull Continuation<? super Unit> continuation) {
        ct.a(e.e("executorHciCacheExecute handleWaitToWork bizSrc:", a2Var), new Object[0]);
        Object objCollect = flow.collect(new a(a2Var), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.m9
    @org.jetbrains.annotations.Nullable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(@org.jetbrains.annotations.NotNull spi.a2 r10, @org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.n9.b(spi.a2, com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
