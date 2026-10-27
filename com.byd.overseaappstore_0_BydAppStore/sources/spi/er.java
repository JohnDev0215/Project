package spi;

import com.byd.overseaappstore.module.ability.data.ApiDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$2", f = "SoftwareRepository.kt", i = {}, l = {160}, m = "invokeSuspend", n = {}, s = {})
public final class er extends SuspendLambda implements Function2<FlowCollector<? super ApiDataBody<List<? extends SoftwareDeviceInstalledRsp>>>, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ SoftwareRepository c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public er(SoftwareRepository softwareRepository, Continuation<? super er> continuation) {
        super(2, continuation);
        this.c = softwareRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        er erVar = new er(this.c, continuation);
        erVar.b = obj;
        return erVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(FlowCollector<? super ApiDataBody<List<? extends SoftwareDeviceInstalledRsp>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((er) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.b;
            ApiDataBody<List<SoftwareDeviceInstalledRsp>> apiDataBodyA = this.c.c.a();
            this.a = 1;
            if (flowCollector.emit(apiDataBodyA, this) == coroutine_suspended) {
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
