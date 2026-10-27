package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.ExecutorHciMgr$resumeActing$1", f = "ExecutorHciMgr.kt", i = {}, l = {78}, m = "invokeSuspend", n = {}, s = {})
public final class r9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ s9 b;
    public final /* synthetic */ a2 c;
    public final /* synthetic */ SoftwareOptParam d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r9(s9 s9Var, a2 a2Var, SoftwareOptParam softwareOptParam, Continuation<? super r9> continuation) {
        super(2, continuation);
        this.b = s9Var;
        this.c = a2Var;
        this.d = softwareOptParam;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new r9(this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((r9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            q9 q9Var = this.b.a;
            a2 a2Var = this.c;
            SoftwareOptParam softwareOptParam = this.d;
            this.a = 1;
            y9 y9Var = q9Var.a;
            Objects.requireNonNull(y9Var);
            ct.a("executorHciWorkLine handleResumeActing pkn:" + softwareOptParam.getPackageName() + " bizSrc:" + a2Var, new Object[0]);
            Object objE = y9Var.a.e(a2Var, FlowKt.flowOf(y9Var.b.parseToWorkCarrier(softwareOptParam)), this);
            if (objE != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                objE = Unit.INSTANCE;
            }
            if (objE != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                objE = Unit.INSTANCE;
            }
            if (objE == coroutine_suspended) {
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
