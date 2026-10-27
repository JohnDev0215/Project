package spi;

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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel$downloadPoolRegister$1$1", f = "SoftwareDetailViewModel.kt", i = {}, l = {105}, m = "invokeSuspend", n = {}, s = {})
public final class iq extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MutableStateFlow<SoftwareStateData> b;
    public final /* synthetic */ String c;
    public final /* synthetic */ SoftwareDetailViewModel d;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ MutableStateFlow<SoftwareStateData> a;
        public final /* synthetic */ String b;
        public final /* synthetic */ SoftwareDetailViewModel c;

        public a(MutableStateFlow<SoftwareStateData> mutableStateFlow, String str, SoftwareDetailViewModel softwareDetailViewModel) {
            this.a = mutableStateFlow;
            this.b = str;
            this.c = softwareDetailViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            int state = this.a.getValue().getState();
            int percentCount = this.a.getValue().getPercentCount();
            String str = this.b;
            StringBuilder sbL = e.l("operate vm forEach find state:", state, " percentCount:", percentCount, " pkn:");
            sbL.append(str);
            ct.a(sbL.toString(), new Object[0]);
            this.c.o.setValue(this.a.getValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public iq(MutableStateFlow<SoftwareStateData> mutableStateFlow, String str, SoftwareDetailViewModel softwareDetailViewModel, Continuation<? super iq> continuation) {
        super(2, continuation);
        this.b = mutableStateFlow;
        this.c = str;
        this.d = softwareDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new iq(this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((iq) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<SoftwareStateData> mutableStateFlow = this.b;
            a aVar = new a(mutableStateFlow, this.c, this.d);
            this.a = 1;
            if (mutableStateFlow.collect(aVar, this) == coroutine_suspended) {
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
