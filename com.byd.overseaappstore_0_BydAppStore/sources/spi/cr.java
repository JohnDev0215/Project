package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.req.FeatureTopicGroupExpendReq;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicSoftwareRsp;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$2", f = "SoftwareRepository.kt", i = {}, l = {117, 117}, m = "invokeSuspend", n = {}, s = {})
public final class cr extends SuspendLambda implements Function2<FlowCollector<? super NetRspDataBody<List<? extends FeatureTopicSoftwareRsp>>>, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ SoftwareRepository c;
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cr(SoftwareRepository softwareRepository, int i, int i2, int i3, Continuation<? super cr> continuation) {
        super(2, continuation);
        this.c = softwareRepository;
        this.d = i;
        this.e = i2;
        this.f = i3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        cr crVar = new cr(this.c, this.d, this.e, this.f, continuation);
        crVar.b = obj;
        return crVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(FlowCollector<? super NetRspDataBody<List<? extends FeatureTopicSoftwareRsp>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((cr) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            FeatureTopicGroupExpendReq featureTopicGroupExpendReq = new FeatureTopicGroupExpendReq(this.d, this.e, this.f);
            this.b = flowCollector;
            this.a = 1;
            obj = bqVar.e(featureTopicGroupExpendReq, this);
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
