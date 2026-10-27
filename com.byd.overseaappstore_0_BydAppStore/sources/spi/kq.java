package spi;

import com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel$downloadTriggerRegister$1", f = "SoftwareDetailViewModel.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {})
public final class kq extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ SoftwareDetailViewModel c;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ CoroutineScope a;
        public final /* synthetic */ SoftwareDetailViewModel b;

        public a(CoroutineScope coroutineScope, SoftwareDetailViewModel softwareDetailViewModel) {
            this.a = coroutineScope;
            this.b = softwareDetailViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            String str = (String) obj;
            ct.a(d.f("operate vm taskTrigger pkn:", str), new Object[0]);
            Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(this.a, null, null, new jq(this.b, str, null), 3, null);
            return jobLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? jobLaunch$default : Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public kq(SoftwareDetailViewModel softwareDetailViewModel, Continuation<? super kq> continuation) {
        super(2, continuation);
        this.c = softwareDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        kq kqVar = new kq(this.c, continuation);
        kqVar.b = obj;
        return kqVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((kq) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.b;
            SharedFlow<String> taskTriggerFlow = this.c.e.getTaskTriggerFlow();
            a aVar = new a(coroutineScope, this.c);
            this.a = 1;
            if (taskTriggerFlow.collect(aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }
}
