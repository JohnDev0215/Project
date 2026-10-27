package com.amazon.mp3.detailpages.actions;

import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewModelFetcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B(\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tJ\u001a\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R)\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/GenericBaseViewModelFetcher;", "Lcom/amazon/mp3/detailpages/actions/BaseViewModelFetcher;", "shouldIncludeModel", "Lkotlin/Function1;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lkotlin/ParameterName;", "name", "currentModel", "", "(Lkotlin/jvm/functions/Function1;)V", "fetchModels", "", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenericBaseViewModelFetcher implements BaseViewModelFetcher {
    private final Function1<BaseViewModel, Boolean> shouldIncludeModel;

    /* JADX WARN: Multi-variable type inference failed */
    public GenericBaseViewModelFetcher(Function1<? super BaseViewModel, Boolean> shouldIncludeModel) {
        Intrinsics.checkNotNullParameter(shouldIncludeModel, "shouldIncludeModel");
        this.shouldIncludeModel = shouldIncludeModel;
    }

    @Override // com.amazon.mp3.detailpages.actions.BaseViewModelFetcher
    public List<BaseViewModel> fetchModels(PageGridViewModel pageGridViewModel) {
        ArrayList arrayList = new ArrayList();
        if (pageGridViewModel == null) {
            return null;
        }
        for (BaseViewModel baseViewModel : pageGridViewModel.getModels()) {
            if (this.shouldIncludeModel.invoke(baseViewModel).booleanValue()) {
                arrayList.add(baseViewModel);
            }
        }
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }
}
