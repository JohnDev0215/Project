package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class aa {

    @NotNull
    public final z9 a;

    @NotNull
    public final z9 b;

    @NotNull
    public final ExecutorDialogRouterMgr c;

    @NotNull
    public final i9 d;

    @NotNull
    public final DataGenerator e;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMgr$install$1", f = "ExecutorIuuWorkMgr.kt", i = {}, l = {50}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;
        public final /* synthetic */ SoftwareOptParam c;
        public final /* synthetic */ Context d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(SoftwareOptParam softwareOptParam, Context context, Continuation<? super a> continuation) {
            super(2, continuation);
            this.c = softwareOptParam;
            this.d = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return aa.this.new a(this.c, this.d, continuation);
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
                z9 z9VarA = aa.this.a(this.c.getSourceType());
                SoftwareOptParam softwareOptParam = this.c;
                Context context = this.d;
                this.a = 1;
                if (z9VarA.c(softwareOptParam, context, this) == coroutine_suspended) {
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

    public aa(@NotNull z9 executorIuuWorkSingle, @NotNull z9 executorIuuWorkMulti, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull i9 executorDbMgr, @NotNull DataGenerator dataGenerator) {
        Intrinsics.checkNotNullParameter(executorIuuWorkSingle, "executorIuuWorkSingle");
        Intrinsics.checkNotNullParameter(executorIuuWorkMulti, "executorIuuWorkMulti");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        this.a = executorIuuWorkSingle;
        this.b = executorIuuWorkMulti;
        this.c = executorDialogRouterMgr;
        this.d = executorDbMgr;
        this.e = dataGenerator;
    }

    public final z9 a(short s) {
        if (sr.a.k(s)) {
            ct.a(e.c("executor softwareWorkMulti go sourceType:", s), new Object[0]);
            return this.b;
        }
        ct.a(e.c("executor softwareWorkSingle go sourceType:", s), new Object[0]);
        return this.a;
    }

    public final void b(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain())), null, null, new a(softwareOptParam, context, null), 3, null);
    }
}
