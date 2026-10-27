package spi;

import com.byd.overseaappstore.module.ability.data.uistate.UpgradeUiState;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.vm.UpgradeViewModel;
import java.util.Collection;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.UpgradeViewModel$updateTriggerRegister$1$1$1", f = "UpgradeViewModel.kt", i = {}, l = {192}, m = "invokeSuspend", n = {}, s = {})
public final class lu extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ UpgradeViewModel b;
    public final /* synthetic */ String c;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ UpgradeViewModel a;
        public final /* synthetic */ String b;

        public a(UpgradeViewModel upgradeViewModel, String str) {
            this.a = upgradeViewModel;
            this.b = str;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            SoftwareStateData softwareStateData = (SoftwareStateData) obj;
            List<? extends Software> data = this.a.m.getValue().getData();
            List mutableList = data != null ? CollectionsKt.toMutableList((Collection) data) : null;
            if (mutableList != null) {
                String str = this.b;
                int i = 0;
                for (T t : mutableList) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Software software = (Software) t;
                    ct.a(d.c("softwareStateData.percentCount  ", softwareStateData.getPercentCount(), "  ", softwareStateData.getState()), new Object[0]);
                    if (Intrinsics.areEqual(software.getPackageName(), str)) {
                        mutableList.set(i, software.newSoftwareDownloaded(softwareStateData.getPercentCount(), softwareStateData.getState()));
                    }
                    i = i2;
                }
            }
            if (mutableList != null) {
                UpgradeViewModel upgradeViewModel = this.a;
                upgradeViewModel.l.setValue(new UpgradeUiState(mutableList, upgradeViewModel.m.getValue().getVisible()));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public lu(UpgradeViewModel upgradeViewModel, String str, Continuation<? super lu> continuation) {
        super(2, continuation);
        this.b = upgradeViewModel;
        this.c = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new lu(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((lu) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<SoftwareStateData> mutableStateFlowD = this.b.e.d(this.c);
            if (mutableStateFlowD == null) {
                return Unit.INSTANCE;
            }
            a aVar = new a(this.b, this.c);
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
