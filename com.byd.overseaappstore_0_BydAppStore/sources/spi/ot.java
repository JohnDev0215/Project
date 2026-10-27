package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.uistate.TopicDetailUiState;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.vm.TopicDetailViewModel;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.TopicDetailViewModel$freshDataForLanguage$2", f = "TopicDetailViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ot extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ TopicDetailViewModel a;
    public final /* synthetic */ List<FeatureThemTopicGroup> b;
    public final /* synthetic */ int c;
    public final /* synthetic */ String d;
    public final /* synthetic */ Context e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ot(TopicDetailViewModel topicDetailViewModel, List<FeatureThemTopicGroup> list, int i, String str, Context context, Continuation<? super ot> continuation) {
        super(2, continuation);
        this.a = topicDetailViewModel;
        this.b = list;
        this.c = i;
        this.d = str;
        this.e = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ot(this.a, this.b, this.c, this.d, this.e, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ot) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        TopicDetailUiState value = this.a.j.getValue();
        if (value.getVisible()) {
            List<FeatureThemTopicGroup> list = this.b;
            boolean z = true;
            if (list == null || list.isEmpty()) {
                ct.a("freshDataForLanguage featureThemTopicGroupList.isNullOrEmpty ", new Object[0]);
            } else {
                String languageToUpdateTopicTitle = this.a.g.parseLanguageToUpdateTopicTitle(this.c, this.d, this.b);
                TopicDetailViewModel topicDetailViewModel = this.a;
                topicDetailViewModel.k.setValue(topicDetailViewModel.b.generateTopicTitleUiState(languageToUpdateTopicTitle));
                List<Software> languageToUpdateSoftwareList = this.a.g.parseLanguageToUpdateSoftwareList(value.getData(), this.e);
                if (languageToUpdateSoftwareList != null && !languageToUpdateSoftwareList.isEmpty()) {
                    z = false;
                }
                if (z) {
                    ct.a("freshDataForLanguage no intervene parseLanguageToUpdateSoftwareList is isNullOrEmpty", new Object[0]);
                } else {
                    ct.a("freshDataForLanguage freshData go", new Object[0]);
                    value.setData(languageToUpdateSoftwareList);
                    this.a.i.setValue(value);
                }
            }
        } else {
            ct.a("freshDataForLanguage no intervene uiState.visible is false", new Object[0]);
        }
        return Unit.INSTANCE;
    }
}
