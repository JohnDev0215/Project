package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.uistate.UpgradeUiState;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.vm.UpgradeViewModel;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.UpgradeViewModel$freshDataForLanguage$2", f = "UpgradeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class hu extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ UpgradeViewModel a;
    public final /* synthetic */ Context b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public hu(UpgradeViewModel upgradeViewModel, Context context, Continuation<? super hu> continuation) {
        super(2, continuation);
        this.a = upgradeViewModel;
        this.b = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new hu(this.a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((hu) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        UpgradeUiState value = this.a.m.getValue();
        if (value.getVisible()) {
            List<Software> languageToUpdateSoftwareList = this.a.g.parseLanguageToUpdateSoftwareList(value.getData(), this.b);
            if (languageToUpdateSoftwareList == null || languageToUpdateSoftwareList.isEmpty()) {
                ct.a("freshDataForLanguage no intervene updateSoftwareList isNullOrEmpty", new Object[0]);
            } else {
                ct.a("freshDataForLanguage freshData go", new Object[0]);
                this.a.l.setValue(this.a.b.generateUpgradeSoftwareUiState(languageToUpdateSoftwareList, value));
            }
        } else {
            ct.a("freshDataForLanguage no intervene updateListViewUiState.visible is false", new Object[0]);
        }
        return Unit.INSTANCE;
    }
}
