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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$2", f = "ExecutorIuuWorkMulti.kt", i = {}, l = {53, 53}, m = "invokeSuspend", n = {}, s = {})
public final class fa extends SuspendLambda implements Function2<FlowCollector<? super qq>, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ String c;
    public final /* synthetic */ ia d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fa(String str, ia iaVar, Continuation<? super fa> continuation) {
        super(2, continuation);
        this.c = str;
        this.d = iaVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        fa faVar = new fa(this.c, this.d, continuation);
        faVar.b = obj;
        return faVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(FlowCollector<? super qq> flowCollector, Continuation<? super Unit> continuation) {
        return ((fa) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            ct.a(e.d("executor install start pkn:", this.c, " multiWork"), new Object[0]);
            i9 i9Var = this.d.e;
            String str = this.c;
            this.b = flowCollector;
            this.a = 1;
            obj = i9Var.z(str, this);
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
