package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.uistate.SoftwareDetailUiState;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetail;
import com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel$freshDataForLanguage$2", f = "SoftwareDetailViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class lq extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SoftwareDetailViewModel a;
    public final /* synthetic */ Context b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public lq(SoftwareDetailViewModel softwareDetailViewModel, Context context, Continuation<? super lq> continuation) {
        super(2, continuation);
        this.a = softwareDetailViewModel;
        this.b = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new lq(this.a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((lq) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        SoftwareDetailUiState value = this.a.j.getValue();
        if (value.getVisible()) {
            SoftwareDetail languageToUpdateSoftwareDetail = this.a.g.parseLanguageToUpdateSoftwareDetail(value.getData(), this.b);
            if (languageToUpdateSoftwareDetail == null) {
                ct.a("freshDataForLanguage no intervene updateSoftwareDetail isNull", new Object[0]);
            } else if (languageToUpdateSoftwareDetail.getSoftware() == null) {
                ct.a("freshDataForLanguage no intervene updateSoftwareDetail.software isNull", new Object[0]);
            } else {
                ct.a("freshDataForLanguage freshData go", new Object[0]);
                this.a.i.setValue(this.a.b.generateSoftwareDetailUiState(languageToUpdateSoftwareDetail, value));
            }
        } else {
            ct.a("freshDataForLanguage no intervene softwareDetailUiState.visible is false", new Object[0]);
        }
        return Unit.INSTANCE;
    }
}
