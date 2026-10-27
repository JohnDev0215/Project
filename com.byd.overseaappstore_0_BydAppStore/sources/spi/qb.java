package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.uistate.FeatureThemGroupUiState;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.ui.vm.FeatureViewModel;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$freshDataForLanguage$2", f = "FeatureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class qb extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ FeatureViewModel a;
    public final /* synthetic */ Context b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qb(FeatureViewModel featureViewModel, Context context, Continuation<? super qb> continuation) {
        super(2, continuation);
        this.a = featureViewModel;
        this.b = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new qb(this.a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((qb) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        FeatureThemGroupUiState value = this.a.p.getValue();
        if (Intrinsics.areEqual(value.getRet(), Boxing.boxBoolean(false))) {
            ct.a("freshDataForLanguage no intervene ret is false", new Object[0]);
        } else {
            List<FeatureThemTopicGroup> languageToUpdateFeatureThemTopicGroupList = this.a.h.parseLanguageToUpdateFeatureThemTopicGroupList(value.getData(), this.b);
            if (languageToUpdateFeatureThemTopicGroupList == null || languageToUpdateFeatureThemTopicGroupList.isEmpty()) {
                ct.a("freshDataForLanguage no intervene updateFeatureThemTopicGroupList is isNullOrEmpty", new Object[0]);
            } else {
                ct.a("freshDataForLanguage freshData go", new Object[0]);
                this.a.o.setValue(this.a.b.generateFeatureThemGroupUiState(languageToUpdateFeatureThemTopicGroupList, value));
            }
        }
        return Unit.INSTANCE;
    }
}
