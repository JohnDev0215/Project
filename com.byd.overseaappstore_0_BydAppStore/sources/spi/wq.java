package spi;

import com.byd.overseaappstore.module.ability.data.ApiDataBody;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$2", f = "SoftwareRepository.kt", i = {}, l = {278, 278}, m = "invokeSuspend", n = {}, s = {})
public final class wq extends SuspendLambda implements Function2<FlowCollector<? super ApiDataBody<List<? extends qq>>>, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ SoftwareRepository c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public wq(SoftwareRepository softwareRepository, Continuation<? super wq> continuation) {
        super(2, continuation);
        this.c = softwareRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        wq wqVar = new wq(this.c, continuation);
        wqVar.b = obj;
        return wqVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(FlowCollector<? super ApiDataBody<List<? extends qq>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((wq) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            i9 i9Var = this.c.b;
            this.b = flowCollector;
            this.a = 1;
            obj = i9Var.d(3, this);
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
