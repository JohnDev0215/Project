package spi;

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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$7", f = "ExecutorIuuWorkSingle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class la extends SuspendLambda implements Function3<FlowCollector<? super Boolean>, Throwable, Continuation<? super Unit>, Object> {
    public /* synthetic */ Throwable a;
    public final /* synthetic */ String b;
    public final /* synthetic */ na c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public la(String str, na naVar, Continuation<? super la> continuation) {
        super(3, continuation);
        this.b = str;
        this.c = naVar;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super Boolean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        la laVar = new la(this.b, this.c, continuation);
        laVar.a = th;
        return laVar.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        ct.b("executor install error msg " + this.a.getMessage() + " pkn:" + this.b + " singleWork", new Object[0]);
        this.c.c.addBizTypeAndData(3, this.c.d.parseToIUUState(this.b, (short) 1, 8)).workNotify();
        return Unit.INSTANCE;
    }
}
