package com.amazon.mp3.catalog.fragment.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.RefinementKt;
import com.amazon.mp3.view.refinements.BaseViewRefinementFilter;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.google.firebase.perf.util.Constants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewsRefinementManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010#\n\u0002\b\u0007\u0018\u0000 B2\u00020\u0001:\u0002BCB\u000f\u0012\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b@\u0010AJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J8\u0010\r\u001a\u00020\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0002J\u0014\u0010\u0011\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0010\u0010\u0013\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010\u0015\u001a\u00020\u0014J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0014J:\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\u0017\u001a\u00020\u00162\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bJ\u000e\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001bJ\u0006\u0010\u001e\u001a\u00020\u0004J\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0014H\u0014J\u0006\u0010#\u001a\u00020\u0006J\b\u0010%\u001a\u0004\u0018\u00010$J\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010&J\u0010\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010)\u001a\u00020(R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R$\u00100\u001a\u0004\u0018\u00010/8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u00106R*\u00107\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001f0=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?¨\u0006D"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "", "Lcom/amazon/music/views/library/models/VisualRowItemModel;", "model", "", "position", "", "updateFilteredModel", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "filteredBaseViewModels", "pageDescriptorModels", "appendedModels", "updatePageGridViewModelWithFilteredResults", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageData", "observePageData", "pageGridViewModel", "updateUnfilteredData", "", "hasFilterableData", "Landroid/content/Context;", "context", "getFilteredDataList", "areFilterSelectionsEmpty", "applyRefinementFilters", "Lcom/amazon/music/views/library/models/FilterItemModel;", PageUriUtils.FILTER_QUERY_PARAM, "updateSelectedFilterSet", "selectedRefinementCount", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "refinementChanged", Constants.ENABLE_DISABLE, "onFilterToggled", "resetRefinementFilters", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "retrieveRefinementListModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "retrieveFilterableBaseViewModelsContainer", "", "title", "Lcom/amazon/music/views/library/models/FilterModel;", "retrieveFilterModelByTitle", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementDataProvider;", "dataProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementDataProvider;", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "changedListener", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "getChangedListener", "()Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "setChangedListener", "(Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;)V", "Landroidx/lifecycle/MutableLiveData;", "unfilteredFilterableBaseViewModels", "Ljava/util/List;", "getUnfilteredFilterableBaseViewModels", "()Ljava/util/List;", "setUnfilteredFilterableBaseViewModels", "(Ljava/util/List;)V", "", "selectedRefinementFilters", "Ljava/util/Set;", "<init>", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementDataProvider;)V", "Companion", "Provider", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BaseViewsRefinementManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = BaseViewsRefinementManager.class.getSimpleName();
    private OnFilterChangedListener changedListener;
    private final RefinementDataProvider dataProvider;
    private MutableLiveData<PageGridViewModel> pageData;
    private final Set<RefinementFilterType> selectedRefinementFilters;
    private List<? extends BaseViewModel> unfilteredFilterableBaseViewModels;

    /* JADX INFO: compiled from: BaseViewsRefinementManager.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager$Provider;", "", "getRefinementManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Provider {
        BaseViewsRefinementManager getRefinementManager();
    }

    public BaseViewsRefinementManager(RefinementDataProvider dataProvider) {
        Intrinsics.checkNotNullParameter(dataProvider, "dataProvider");
        this.dataProvider = dataProvider;
        this.selectedRefinementFilters = new LinkedHashSet();
    }

    /* JADX INFO: compiled from: BaseViewsRefinementManager.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager$Companion;", "", "()V", "FILTERABLE_MODELS_CONTAINER_TAG", "", "REFINEMENT_METRIC_FILTER_SELECTED", "REFINEMENT_METRIC_FILTER_TYPE", "TAG", "kotlin.jvm.PlatformType", "sendRefinementUiClickMetric", "", "filterModel", "Lcom/amazon/music/views/library/models/FilterModel;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void sendRefinementUiClickMetric(FilterModel filterModel, PageType pageType) {
            Intrinsics.checkNotNullParameter(filterModel, "filterModel");
            Intrinsics.checkNotNullParameter(pageType, "pageType");
            ArrayList arrayList = new ArrayList();
            if (!filterModel.isCollection()) {
                FilterItemModel itemModel = filterModel.getItemModel();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (itemModel != null) {
                    linkedHashMap.put("filters", itemModel.getTitle());
                    linkedHashMap.put("selected", String.valueOf(itemModel.getSelected()));
                    arrayList.add(linkedHashMap);
                }
            }
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_REFINEMENT).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(pageType).withContentInfo(arrayList).build());
        }
    }

    public final OnFilterChangedListener getChangedListener() {
        return this.changedListener;
    }

    public final void setChangedListener(OnFilterChangedListener onFilterChangedListener) {
        this.changedListener = onFilterChangedListener;
    }

    public final List<BaseViewModel> getUnfilteredFilterableBaseViewModels() {
        return this.unfilteredFilterableBaseViewModels;
    }

    public final void observePageData(MutableLiveData<PageGridViewModel> pageData) {
        Intrinsics.checkNotNullParameter(pageData, "pageData");
        this.pageData = pageData;
    }

    public final void updateUnfilteredData(PageGridViewModel pageGridViewModel) {
        this.unfilteredFilterableBaseViewModels = this.dataProvider.createUnfilteredData(pageGridViewModel);
    }

    public final boolean hasFilterableData() {
        if (this.unfilteredFilterableBaseViewModels == null) {
            return false;
        }
        return !r0.isEmpty();
    }

    public final List<BaseViewModel> getFilteredDataList(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<? extends BaseViewModel> list = this.unfilteredFilterableBaseViewModels;
        List<BaseViewModel> listApplyFiltersToModels = list == null ? null : new BaseViewRefinementFilter().applyFiltersToModels(list, this.selectedRefinementFilters, context);
        return listApplyFiltersToModels == null ? CollectionsKt__CollectionsKt.emptyList() : listApplyFiltersToModels;
    }

    public final boolean areFilterSelectionsEmpty() {
        return this.selectedRefinementFilters.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List applyRefinementFilters$default(BaseViewsRefinementManager baseViewsRefinementManager, Context context, List list, List list2, int i, Object obj) {
        if ((i & 2) != 0) {
            list = null;
        }
        if ((i & 4) != 0) {
            list2 = null;
        }
        return baseViewsRefinementManager.applyRefinementFilters(context, list, list2);
    }

    public final List<BaseViewModel> applyRefinementFilters(Context context, List<? extends BaseViewModel> pageDescriptorModels, List<? extends BaseViewModel> appendedModels) {
        Unit unit;
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        List<? extends BaseViewModel> list = this.unfilteredFilterableBaseViewModels;
        if (list == null) {
            unit = null;
        } else {
            if (this.selectedRefinementFilters.isEmpty()) {
                updatePageGridViewModelWithFilteredResults(getUnfilteredFilterableBaseViewModels(), pageDescriptorModels, appendedModels);
                return getUnfilteredFilterableBaseViewModels();
            }
            for (IndexedValue indexedValue : CollectionsKt___CollectionsKt.withIndex(new BaseViewRefinementFilter().applyFiltersToModels(list, this.selectedRefinementFilters, context))) {
                int index = indexedValue.getIndex();
                BaseViewModel baseViewModel = (BaseViewModel) indexedValue.component2();
                if (baseViewModel instanceof VisualRowItemModel) {
                    VisualRowItemModel visualRowItemModelCopy = ((VisualRowItemModel) baseViewModel).copy();
                    updateFilteredModel(visualRowItemModelCopy, index);
                    arrayList.add(visualRowItemModelCopy);
                } else {
                    arrayList.add(baseViewModel);
                }
            }
            updatePageGridViewModelWithFilteredResults(arrayList, pageDescriptorModels, appendedModels);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(TAG, "no models to filter");
        }
        return arrayList;
    }

    private final void updateFilteredModel(VisualRowItemModel model, int position) {
        ContentMetadata metadata = model.getMetadata();
        if (metadata instanceof TrackMetadata) {
            ((TrackMetadata) metadata).setTrackPosition(Integer.valueOf(position));
        }
    }

    public final void updateSelectedFilterSet(FilterItemModel filter) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        RefinementFilterType refinementTypeFromDisplayName = RefinementFilterType.INSTANCE.getRefinementTypeFromDisplayName(filter.getTitle());
        if (refinementTypeFromDisplayName == null) {
            return;
        }
        if (filter.getSelected()) {
            this.selectedRefinementFilters.add(refinementTypeFromDisplayName);
        } else {
            this.selectedRefinementFilters.remove(refinementTypeFromDisplayName);
        }
        onFilterToggled(refinementTypeFromDisplayName, filter.getSelected());
    }

    public final int selectedRefinementCount() {
        return this.selectedRefinementFilters.size();
    }

    protected void onFilterToggled(RefinementFilterType refinementChanged, boolean isEnabled) {
        OnFilterChangedListener onFilterChangedListener;
        Intrinsics.checkNotNullParameter(refinementChanged, "refinementChanged");
        if (refinementChanged != RefinementFilterType.DOWNLOADED || (onFilterChangedListener = this.changedListener) == null) {
            return;
        }
        onFilterChangedListener.downloadSelectionChanged(isEnabled);
    }

    public final void resetRefinementFilters() {
        ExposedRefinementListModel exposedRefinementListModelRetrieveRefinementListModel = retrieveRefinementListModel();
        if (exposedRefinementListModelRetrieveRefinementListModel != null) {
            RefinementKt.resetRefinementFilterModels(exposedRefinementListModelRetrieveRefinementListModel);
        }
        this.selectedRefinementFilters.clear();
        onFilterToggled(RefinementFilterType.DOWNLOADED, false);
        BaseViewContainerModel<BaseViewModel> baseViewContainerModelRetrieveFilterableBaseViewModelsContainer = retrieveFilterableBaseViewModelsContainer();
        List<? extends BaseViewModel> list = this.unfilteredFilterableBaseViewModels;
        if (list == null) {
            return;
        }
        this.dataProvider.updatePageGridViewModelWithFilteredResults(list, baseViewContainerModelRetrieveFilterableBaseViewModelsContainer, this.pageData, null, null);
    }

    public final ExposedRefinementListModel retrieveRefinementListModel() {
        PageGridViewModel value;
        MutableLiveData<PageGridViewModel> mutableLiveData = this.pageData;
        if (mutableLiveData != null && (value = mutableLiveData.getValue()) != null) {
            for (BaseViewModel baseViewModel : value.getModels()) {
                if (baseViewModel instanceof ExposedRefinementListModel) {
                    return (ExposedRefinementListModel) baseViewModel;
                }
            }
        }
        Log.warning(TAG, "Unable to find ExposedRefinementListModel in page model");
        return null;
    }

    public final BaseViewContainerModel<BaseViewModel> retrieveFilterableBaseViewModelsContainer() {
        PageGridViewModel value;
        MutableLiveData<PageGridViewModel> mutableLiveData = this.pageData;
        if (mutableLiveData != null && (value = mutableLiveData.getValue()) != null) {
            for (BaseViewModel baseViewModel : value.getModels()) {
                if (baseViewModel instanceof BaseViewContainerModel) {
                    List<String> tags = baseViewModel.getTags();
                    boolean z = false;
                    if (tags != null && tags.contains("FILTERABLE_MODELS_CONTAINER_TAG")) {
                        z = true;
                    }
                    if (z) {
                        return (BaseViewContainerModel) baseViewModel;
                    }
                }
            }
        }
        Log.warning(TAG, "Unable to container model for filterable BaseView models");
        return null;
    }

    public final FilterModel retrieveFilterModelByTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        ExposedRefinementListModel exposedRefinementListModelRetrieveRefinementListModel = retrieveRefinementListModel();
        if (exposedRefinementListModelRetrieveRefinementListModel != null) {
            for (FilterModel filterModel : exposedRefinementListModelRetrieveRefinementListModel.getFilterModels()) {
                if (!filterModel.isCollection()) {
                    FilterItemModel itemModel = filterModel.getItemModel();
                    if (Intrinsics.areEqual(itemModel == null ? null : itemModel.getTitle(), title)) {
                        return itemModel;
                    }
                } else {
                    FilterCollectionModel collectionModel = filterModel.getCollectionModel();
                    if (Intrinsics.areEqual(collectionModel == null ? null : collectionModel.getTitle(), title)) {
                        return collectionModel;
                    }
                }
            }
        }
        return null;
    }

    private final void updatePageGridViewModelWithFilteredResults(List<? extends BaseViewModel> filteredBaseViewModels, List<? extends BaseViewModel> pageDescriptorModels, List<? extends BaseViewModel> appendedModels) {
        this.dataProvider.updatePageGridViewModelWithFilteredResults(filteredBaseViewModels, retrieveFilterableBaseViewModelsContainer(), this.pageData, pageDescriptorModels, appendedModels);
    }
}
