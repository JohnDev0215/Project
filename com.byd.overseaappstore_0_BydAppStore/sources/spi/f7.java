package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.req.DownloadUrlRefreshReq;
import com.byd.overseaappstore.module.ability.data.rsp.DownloadUrlRefreshRsp;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$2", f = "DownloadRepository.kt", i = {}, l = {44, 44}, m = "invokeSuspend", n = {}, s = {})
public final class f7 extends SuspendLambda implements Function2<FlowCollector<? super NetRspDataBody<List<? extends DownloadUrlRefreshRsp>>>, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ h7 c;
    public final /* synthetic */ DownloadUrlRefreshReq d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f7(h7 h7Var, DownloadUrlRefreshReq downloadUrlRefreshReq, Continuation<? super f7> continuation) {
        super(2, continuation);
        this.c = h7Var;
        this.d = downloadUrlRefreshReq;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        f7 f7Var = new f7(this.c, this.d, continuation);
        f7Var.b = obj;
        return f7Var;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(FlowCollector<? super NetRspDataBody<List<? extends DownloadUrlRefreshRsp>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((f7) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            x6 x6Var = this.c.a;
            DownloadUrlRefreshReq downloadUrlRefreshReq = this.d;
            this.b = flowCollector;
            this.a = 1;
            obj = x6Var.b(downloadUrlRefreshReq, this);
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
        this.b = null;
        this.a = 2;
        if (flowCollector.emit(obj, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
