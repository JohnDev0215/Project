package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareSearchRsp;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SearchRepository$search$4", f = "SearchRepository.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class po extends SuspendLambda implements Function3<FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends SoftwareSearchRsp>>>>, Throwable, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ FlowCollector b;
    public /* synthetic */ Throwable c;
    public final /* synthetic */ String d;
    public final /* synthetic */ qo e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public po(String str, qo qoVar, Continuation<? super po> continuation) {
        super(3, continuation);
        this.d = str;
        this.e = qoVar;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends SoftwareSearchRsp>>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        po poVar = new po(this.d, this.e, continuation);
        poVar.b = flowCollector;
        poVar.c = th;
        return poVar.invokeSuspend(Unit.INSTANCE);
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
            String str = this.d;
            String simpleName = th.getClass().getSimpleName();
            String message = th.getMessage();
            StringBuilder sbL = d.l("repo-vm search url:search/software keyword:", str, " error class:", simpleName, " catch:");
            sbL.append(message);
            ct.b(sbL.toString(), new Object[0]);
            int iA = this.e.a(th);
            String message2 = th.getMessage();
            if (message2 == null) {
                message2 = "unknown error";
            }
            SourceDataBody sourceDataBody = new SourceDataBody(iA, message2, null, 4, null);
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
