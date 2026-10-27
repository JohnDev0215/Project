package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.req.UpgradeSoftwareReq;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareUpdateRsp;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchToUpdateSoftwareList$2", f = "SoftwareRepository.kt", i = {}, l = {374, 374}, m = "invokeSuspend", n = {}, s = {})
public final class or extends SuspendLambda implements Function2<FlowCollector<? super NetRspDataBody<List<? extends SoftwareUpdateRsp>>>, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ SoftwareRepository c;
    public final /* synthetic */ UpgradeSoftwareReq d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public or(SoftwareRepository softwareRepository, UpgradeSoftwareReq upgradeSoftwareReq, Continuation<? super or> continuation) {
        super(2, continuation);
        this.c = softwareRepository;
        this.d = upgradeSoftwareReq;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        or orVar = new or(this.c, this.d, continuation);
        orVar.b = obj;
        return orVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(FlowCollector<? super NetRspDataBody<List<? extends SoftwareUpdateRsp>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((or) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            bq bqVar = this.c.a;
            UpgradeSoftwareReq upgradeSoftwareReq = this.d;
            this.b = flowCollector;
            this.a = 1;
            obj = bqVar.d(upgradeSoftwareReq, this);
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
