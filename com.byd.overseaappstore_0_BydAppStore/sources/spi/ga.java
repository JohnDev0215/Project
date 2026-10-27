package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareFileUnzipInfo;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$8", f = "ExecutorIuuWorkMulti.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ga extends SuspendLambda implements Function3<FlowCollector<? super SoftwareFileUnzipInfo>, Throwable, Continuation<? super Unit>, Object> {
    public /* synthetic */ Throwable a;
    public final /* synthetic */ String b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ga(String str, Continuation<? super ga> continuation) {
        super(3, continuation);
        this.b = str;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super SoftwareFileUnzipInfo> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        ga gaVar = new ga(this.b, continuation);
        gaVar.a = th;
        return gaVar.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        ct.b("executor install error msg " + this.a.getMessage() + " pkn:" + this.b + " multiWork", new Object[0]);
        return Unit.INSTANCE;
    }
}
