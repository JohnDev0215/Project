package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.BannerRsp;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$4", f = "BannerRepository.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
public final class d1 extends SuspendLambda implements Function3<FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends BannerRsp>>>>, Throwable, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ FlowCollector b;
    public /* synthetic */ Throwable c;
    public final /* synthetic */ e1 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d1(e1 e1Var, Continuation<? super d1> continuation) {
        super(3, continuation);
        this.d = e1Var;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends BannerRsp>>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        d1 d1Var = new d1(this.d, continuation);
        d1Var.b = flowCollector;
        d1Var.c = th;
        return d1Var.invokeSuspend(Unit.INSTANCE);
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
            ct.b(d.g("repo-vm fetchList url:upgrade/software error class:", th.getClass().getSimpleName(), " catch:", th.getMessage()), new Object[0]);
            int iA = this.d.a(th);
            String message = th.getMessage();
            if (message == null) {
                message = "unknown error";
            }
            SourceDataBody sourceDataBody = new SourceDataBody(iA, message, null, 4, null);
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
