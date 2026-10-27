package spi;

import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetail;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel$downloadTriggerRegister$1$1$1", f = "SoftwareDetailViewModel.kt", i = {}, l = {124}, m = "invokeSuspend", n = {}, s = {})
public final class jq extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ SoftwareDetailViewModel b;
    public final /* synthetic */ String c;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ String a;
        public final /* synthetic */ SoftwareDetailViewModel b;

        public a(String str, SoftwareDetailViewModel softwareDetailViewModel) {
            this.a = str;
            this.b = softwareDetailViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            Software software;
            SoftwareStateData softwareStateData = (SoftwareStateData) obj;
            ct.a(d.c("operate vm taskTrigger find state:", softwareStateData.getState(), " percentCount:", softwareStateData.getPercentCount()), new Object[0]);
            String str = this.a;
            SoftwareDetail data = this.b.j.getValue().getData();
            if (Intrinsics.areEqual(str, (data == null || (software = data.getSoftware()) == null) ? null : software.getPackageName())) {
                this.b.o.setValue(softwareStateData);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public jq(SoftwareDetailViewModel softwareDetailViewModel, String str, Continuation<? super jq> continuation) {
        super(2, continuation);
        this.b = softwareDetailViewModel;
        this.c = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new jq(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((jq) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<SoftwareStateData> mutableStateFlowD = this.b.f.d(this.c);
            if (mutableStateFlowD == null) {
                return Unit.INSTANCE;
            }
            a aVar = new a(this.c, this.b);
            this.a = 1;
            if (mutableStateFlowD.collect(aVar, this) == coroutine_suspended) {
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
