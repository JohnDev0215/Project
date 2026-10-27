package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.uistate.SearchResultItemUiState;
import com.byd.overseaappstore.module.ability.data.upper.SearchResult;
import com.byd.overseaappstore.ui.vm.SearchActiveViewModel;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SearchActiveViewModel$freshDataForLanguage$2", f = "SearchActiveViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class jo extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SearchActiveViewModel a;
    public final /* synthetic */ Context b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public jo(SearchActiveViewModel searchActiveViewModel, Context context, Continuation<? super jo> continuation) {
        super(2, continuation);
        this.a = searchActiveViewModel;
        this.b = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new jo(this.a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((jo) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        SearchResultItemUiState value = this.a.j.getValue();
        if (value.getVisible()) {
            SearchResult languageToUpdateSearchResultItem = this.a.g.parseLanguageToUpdateSearchResultItem(value.getData(), this.b);
            if (languageToUpdateSearchResultItem == null) {
                ct.a("freshDataForLanguage no intervene updateSearchResultItem isNull", new Object[0]);
            } else {
                ct.a("freshDataForLanguage freshData go", new Object[0]);
                this.a.i.setValue(this.a.c.generateSearchResultUiState(languageToUpdateSearchResultItem, value));
            }
        } else {
            ct.a("freshDataForLanguage no intervene searchResultItemUiState.visible is false", new Object[0]);
        }
        return Unit.INSTANCE;
    }
}
