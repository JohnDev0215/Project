package com.amazon.mp3.catalog.fragment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;

/* JADX INFO: compiled from: RefinementDataProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016JX\u0010\u000f\u001a\u00020\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\n2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/VisualRowRefinementDataProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementDataProvider;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "createUnfilteredData", "filteredBaseViewModels", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "filterableModelsContainer", "Landroidx/lifecycle/MutableLiveData;", "pageData", "pageDescriptorModels", "appendedModels", "", "updatePageGridViewModelWithFilteredResults", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VisualRowRefinementDataProvider implements RefinementDataProvider {
    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinementDataProvider
    public List<BaseViewModel> createUnfilteredData(PageGridViewModel pageGridViewModel) {
        List<BaseViewModel> models;
        ArrayList arrayList = null;
        if (pageGridViewModel != null && (models = pageGridViewModel.getModels()) != null) {
            arrayList = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof VisualRowItemModel) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinementDataProvider
    public void updatePageGridViewModelWithFilteredResults(List<? extends BaseViewModel> filteredBaseViewModels, BaseViewContainerModel<BaseViewModel> filterableModelsContainer, MutableLiveData<PageGridViewModel> pageData, List<? extends BaseViewModel> pageDescriptorModels, List<? extends BaseViewModel> appendedModels) {
        PageGridViewModel value;
        PageGridViewModel value2;
        List<BaseViewModel> models;
        PageGridViewModel pageGridViewModel = null;
        if (pageDescriptorModels == null) {
            if (pageData == null || (value2 = pageData.getValue()) == null || (models = value2.getModels()) == null) {
                pageDescriptorModels = null;
            } else {
                ArrayList arrayList = new ArrayList();
                for (Object obj : models) {
                    BaseViewModel baseViewModel = (BaseViewModel) obj;
                    if (((baseViewModel instanceof VisualRowItemModel) || (baseViewModel instanceof TextRowItemModel)) ? false : true) {
                        arrayList.add(obj);
                    }
                }
                pageDescriptorModels = arrayList;
            }
        }
        List listFlatten = CollectionsKt__IterablesKt.flatten(CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new List[]{pageDescriptorModels, filteredBaseViewModels}));
        if (!(appendedModels == null || appendedModels.isEmpty())) {
            listFlatten = CollectionsKt__IterablesKt.flatten(CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new List[]{listFlatten, appendedModels}));
        }
        if (pageData != null && (value = pageData.getValue()) != null) {
            value.updateModels(listFlatten);
            pageGridViewModel = value;
        }
        if (pageData == null) {
            return;
        }
        pageData.postValue(pageGridViewModel);
    }
}
