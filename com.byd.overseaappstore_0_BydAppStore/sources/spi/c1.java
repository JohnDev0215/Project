package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.req.BannerReq;
import com.byd.overseaappstore.module.ability.data.rsp.BannerRsp;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$2", f = "BannerRepository.kt", i = {}, l = {28, 28}, m = "invokeSuspend", n = {}, s = {})
public final class c1 extends SuspendLambda implements Function2<FlowCollector<? super NetRspDataBody<List<? extends BannerRsp>>>, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ e1 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c1(e1 e1Var, Continuation<? super c1> continuation) {
        super(2, continuation);
        this.c = e1Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        c1 c1Var = new c1(this.c, continuation);
        c1Var.b = obj;
        return c1Var;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(FlowCollector<? super NetRspDataBody<List<? extends BannerRsp>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((c1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            b1 b1Var = this.c.a;
            BannerReq bannerReq = new BannerReq();
            this.b = flowCollector;
            this.a = 1;
            obj = b1Var.a(bannerReq, this);
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
