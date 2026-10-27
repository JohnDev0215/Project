package com.amazon.mp3.catalog.fragment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: RefinementDataProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&JX\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H&¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementDataProvider;", "", "createUnfilteredData", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "updatePageGridViewModelWithFilteredResults", "", "filteredBaseViewModels", "filterableModelsContainer", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "pageData", "Landroidx/lifecycle/MutableLiveData;", "pageDescriptorModels", "appendedModels", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface RefinementDataProvider {
    List<BaseViewModel> createUnfilteredData(PageGridViewModel pageGridViewModel);

    void updatePageGridViewModelWithFilteredResults(List<? extends BaseViewModel> filteredBaseViewModels, BaseViewContainerModel<BaseViewModel> filterableModelsContainer, MutableLiveData<PageGridViewModel> pageData, List<? extends BaseViewModel> pageDescriptorModels, List<? extends BaseViewModel> appendedModels);
}
