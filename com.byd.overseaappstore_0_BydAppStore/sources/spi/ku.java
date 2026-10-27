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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.UpgradeViewModel$updatePoolRegister$1$1", f = "UpgradeViewModel.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {})
public final class ku extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MutableStateFlow<SoftwareStateData> b;
    public final /* synthetic */ String c;
    public final /* synthetic */ UpgradeViewModel d;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ String a;
        public final /* synthetic */ UpgradeViewModel b;

        public a(String str, UpgradeViewModel upgradeViewModel) {
            this.a = str;
            this.b = upgradeViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            SoftwareStateData softwareStateData = (SoftwareStateData) obj;
            int state = softwareStateData.getState();
            int percentCount = softwareStateData.getPercentCount();
            String str = this.a;
            StringBuilder sbL = e.l("operate vm forEach find state:", state, " percentCount:", percentCount, " pkn:");
            sbL.append(str);
            ct.a(sbL.toString(), new Object[0]);
            List<? extends Software> data = this.b.l.getValue().getData();
            List mutableList = data != null ? CollectionsKt.toMutableList((Collection) data) : null;
            if (mutableList != null) {
                String str2 = this.a;
                int i = 0;
                for (T t : mutableList) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Software software = (Software) t;
                    if (Intrinsics.areEqual(software.getPackageName(), str2)) {
                        ct.a(d.c("softwareStateData.percentCount  ", softwareStateData.getPercentCount(), "  ", softwareStateData.getState()), new Object[0]);
                        mutableList.set(i, software.newSoftwareDownloaded(softwareStateData.getPercentCount(), softwareStateData.getState()));
                    }
                    i = i2;
                }
            }
            if (mutableList != null) {
                MutableStateFlow<UpgradeUiState> mutableStateFlow = this.b.l;
                mutableStateFlow.setValue(new UpgradeUiState(mutableList, mutableStateFlow.getValue().getVisible()));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ku(MutableStateFlow<SoftwareStateData> mutableStateFlow, String str, UpgradeViewModel upgradeViewModel, Continuation<? super ku> continuation) {
        super(2, continuation);
        this.b = mutableStateFlow;
        this.c = str;
        this.d = upgradeViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ku(this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ku) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<SoftwareStateData> mutableStateFlow = this.b;
            a aVar = new a(this.c, this.d);
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
