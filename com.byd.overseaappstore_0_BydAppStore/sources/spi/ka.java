package spi;

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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkSingle$install$5", f = "ExecutorIuuWorkSingle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ka extends SuspendLambda implements Function2<FlowCollector<? super qq>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String a;
    public final /* synthetic */ na b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ka(String str, na naVar, Continuation<? super ka> continuation) {
        super(2, continuation);
        this.a = str;
        this.b = naVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ka(this.a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(FlowCollector<? super qq> flowCollector, Continuation<? super Unit> continuation) {
        return ((ka) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        ct.b(e.d("executor install empty pkn:", this.a, " singleWork"), new Object[0]);
        this.b.c.addBizTypeAndData(3, this.b.d.parseToIUUState(this.a, (short) 1, 8)).workNotify();
        return Unit.INSTANCE;
    }
}
