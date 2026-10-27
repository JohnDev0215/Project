package spi;

import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$4", f = "SoftwareRepository.kt", i = {}, l = {308}, m = "invokeSuspend", n = {}, s = {})
public final class rr extends SuspendLambda implements Function3<FlowCollector<? super SourceDataBody<List<? extends qq>>>, Throwable, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ FlowCollector b;
    public /* synthetic */ Throwable c;

    public rr(Continuation<? super rr> continuation) {
        super(3, continuation);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super SourceDataBody<List<? extends qq>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        rr rrVar = new rr(continuation);
        rrVar.b = flowCollector;
        rrVar.c = th;
        return rrVar.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.b;
            Throwable th = this.c;
            ct.b(d.g("repo-vm fetchUpdatingList error class:", th.getClass().getSimpleName(), " catch = ", th.getMessage()), new Object[0]);
            String message = th.getMessage();
            if (message == null) {
                message = "unknown error";
            }
            SourceDataBody sourceDataBody = new SourceDataBody(0, message, null, 5, null);
            this.b = null;
            this.a = 1;
            if (flowCollector.emit(sourceDataBody, this) == coroutine_suspended) {
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
