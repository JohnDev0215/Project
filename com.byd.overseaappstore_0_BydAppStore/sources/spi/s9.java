package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import java.util.List;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class s9 {

    @NotNull
    public final q9 a;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.ExecutorHciMgr$work$1", f = "ExecutorHciMgr.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;
        public final /* synthetic */ a2 c;
        public final /* synthetic */ SoftwareOptParam d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(a2 a2Var, SoftwareOptParam softwareOptParam, Continuation<? super a> continuation) {
            super(2, continuation);
            this.c = a2Var;
            this.d = softwareOptParam;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return s9.this.new a(this.c, this.d, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                q9 q9Var = s9.this.a;
                a2 a2Var = this.c;
                SoftwareOptParam softwareOptParam = this.d;
                this.a = 1;
                y9 y9Var = q9Var.a;
                Objects.requireNonNull(y9Var);
                ct.a("executorHciWorkLine handleWork pkn:" + softwareOptParam.getPackageName() + " bizSrc:" + a2Var, new Object[0]);
                Object objJ = y9Var.a.j(a2Var, FlowKt.flow(new x9(y9Var.b.parseToWorkCarrier(softwareOptParam), null)), this);
                if (objJ != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objJ = Unit.INSTANCE;
                }
                if (objJ != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objJ = Unit.INSTANCE;
                }
                if (objJ == coroutine_suspended) {
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

    public s9(@NotNull q9 executorHciFactory) {
        Intrinsics.checkNotNullParameter(executorHciFactory, "executorHciFactory");
        this.a = executorHciFactory;
    }

    public static Object a(s9 s9Var, String str, Continuation continuation) {
        a2 a2Var = new a2((byte) 0, (byte) 0, (byte) 0, 7, null);
        y9 y9Var = s9Var.a.a;
        Objects.requireNonNull(y9Var);
        ct.a("executorHciWorkLine handleDeleteTask pkn:" + str + " bizSrc:" + a2Var, new Object[0]);
        Object objB = y9Var.a.b(a2Var, str, continuation);
        if (objB != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objB = Unit.INSTANCE;
        }
        if (objB != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objB = Unit.INSTANCE;
        }
        return objB == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objB : Unit.INSTANCE;
    }

    public static Object b(s9 s9Var, List list, Continuation continuation) {
        a2 a2Var = new a2((byte) 0, (byte) 0, (byte) 0, 7, null);
        y9 y9Var = s9Var.a.a;
        Objects.requireNonNull(y9Var);
        ct.a("executorHciWorkLine handlePauseTasks size:" + list.size() + " bizSrc:" + a2Var, new Object[0]);
        Object objD = y9Var.a.d(a2Var, FlowKt.flow(new v9(y9Var.b.parseToWorkCarrier((List<SoftwareOptParam>) list), null)), continuation);
        if (objD != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objD = Unit.INSTANCE;
        }
        if (objD != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objD = Unit.INSTANCE;
        }
        return objD == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objD : Unit.INSTANCE;
    }

    public static void c(s9 s9Var, SoftwareOptParam optParam) {
        a2 bizSrc = new a2((byte) 0, (byte) 0, (byte) 0, 7, null);
        Objects.requireNonNull(s9Var);
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        Intrinsics.checkNotNullParameter(optParam, "optParam");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain())), null, null, new r9(s9Var, bizSrc, optParam, null), 3, null);
    }

    @Nullable
    public final Object d(@NotNull a2 a2Var, @NotNull List<SoftwareOptParam> list, @NotNull Continuation<? super Unit> continuation) {
        y9 y9Var = this.a.a;
        Objects.requireNonNull(y9Var);
        ct.a("executorHciWorkLine handleStartDownloadOnlyTasks size:" + list.size() + " bizSrc:" + a2Var, new Object[0]);
        Object objF = y9Var.a.f(a2Var, FlowKt.flowOf(y9Var.b.parseToWorkCarrier(list)), continuation);
        if (objF != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objF = Unit.INSTANCE;
        }
        if (objF != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objF = Unit.INSTANCE;
        }
        return objF == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objF : Unit.INSTANCE;
    }

    @Nullable
    public final Object e(@NotNull a2 a2Var, @NotNull List<SoftwareOptParam> list, @NotNull Continuation<? super Unit> continuation) {
        y9 y9Var = this.a.a;
        Objects.requireNonNull(y9Var);
        ct.a("executorHciWorkLine handleStartUpdateOnlyTasks size:" + list.size() + " bizSrc:" + a2Var, new Object[0]);
        Object objH = y9Var.a.h(a2Var, FlowKt.flowOf(y9Var.b.parseToWorkCarrier(list)), continuation);
        if (objH != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objH = Unit.INSTANCE;
        }
        if (objH != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objH = Unit.INSTANCE;
        }
        return objH == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objH : Unit.INSTANCE;
    }

    public final void f(@NotNull a2 bizSrc, @NotNull SoftwareOptParam optParam) {
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        Intrinsics.checkNotNullParameter(optParam, "optParam");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain())), null, null, new a(bizSrc, optParam, null), 3, null);
    }
}
