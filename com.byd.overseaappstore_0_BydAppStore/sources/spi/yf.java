package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.uistate.InstalledItemUiState;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareInstalled;
import com.byd.overseaappstore.ui.vm.InstalledViewModel;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.InstalledViewModel$freshDataForLanguage$2", f = "InstalledViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class yf extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ InstalledViewModel a;
    public final /* synthetic */ Context b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public yf(InstalledViewModel installedViewModel, Context context, Continuation<? super yf> continuation) {
        super(2, continuation);
        this.a = installedViewModel;
        this.b = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new yf(this.a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((yf) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        InstalledItemUiState value = this.a.j.getValue();
        if (value.getVisible()) {
            List<SoftwareInstalled> languageToUpdateInstalledSoftwareList = this.a.f.parseLanguageToUpdateInstalledSoftwareList(value.getData(), this.b);
            if (languageToUpdateInstalledSoftwareList == null || languageToUpdateInstalledSoftwareList.isEmpty()) {
                ct.a("freshDataForLanguage no intervene updateSoftwareInstalledList isNullOrEmpty", new Object[0]);
            } else {
                ct.a("freshDataForLanguage freshData go", new Object[0]);
                this.a.i.setValue(this.a.b.generateInstalledItemUiState(languageToUpdateInstalledSoftwareList, value));
            }
        } else {
            ct.a("freshDataForLanguage no intervene installedListViewUiState.visible is false", new Object[0]);
        }
        return Unit.INSTANCE;
    }
}
