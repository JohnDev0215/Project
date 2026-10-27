package com.amazon.music.find.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.music.find.converter.PageGridViewModelConverter;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.model.filter.HideFilterDialogReason;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ExecutionContextDefault;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FilterViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 32\u00020\u0001:\u00013B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\u001a\u001a\u00020\u001bJB\u0010\u001c\u001a\u00020\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001e2\u001c\u0010\"\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u001e\u0012\u0004\u0012\u00020\u001b0#j\u0002`%H\u0016J\n\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0010\u0010(\u001a\u00020\u001b2\b\b\u0002\u0010)\u001a\u00020*J\b\u0010+\u001a\u00020\u001bH\u0016J\b\u0010,\u001a\u00020\u001bH\u0016J\u0006\u0010-\u001a\u00020\u001bJ\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020\u0004H\u0002J\u0006\u00100\u001a\u00020\fJ\u000e\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020!R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/amazon/music/find/viewmodels/FilterViewModel;", "Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "pageGridConverter", "Lcom/amazon/music/find/converter/PageGridViewModelConverter;", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/converter/PageGridViewModelConverter;Lcom/amazon/music/find/metrics/SearchMetricsService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "isFilterDialogVisible", "Landroidx/lifecycle/LiveData;", "", "()Landroidx/lifecycle/LiveData;", "isPristine", "mCurrentFilterSelections", "mInitialFilterSelections", "mIsFilterDialogVisible", "Landroidx/lifecycle/MutableLiveData;", "mIsPristine", "parentViewModel", "Lcom/amazon/music/find/viewmodels/SearchViewModel;", "getParentViewModel", "()Lcom/amazon/music/find/viewmodels/SearchViewModel;", "setParentViewModel", "(Lcom/amazon/music/find/viewmodels/SearchViewModel;)V", "applyFilters", "", "fetchRequestState", "trackIds", "", "", "trackAsins", "", "callback", "Lkotlin/Function1;", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "Lcom/amazon/music/find/model/search/ContentOwnershipCallback;", "getUiPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "hide", "hideFilterDialogReason", "Lcom/amazon/music/find/model/filter/HideFilterDialogReason;", "onRefresh", "onResume", "resetFilterSelection", "show", "filterSelections", "showIvyAudioFilters", "updateFilterSelection", "filterId", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class FilterViewModel extends BasePageGridViewModel {
    private static final String TAG = "SPL";
    private SearchFilterSelections mCurrentFilterSelections;
    private SearchFilterSelections mInitialFilterSelections;
    private final MutableLiveData<Boolean> mIsFilterDialogVisible;
    private final MutableLiveData<Boolean> mIsPristine;
    private final PageGridViewModelConverter<SearchFilterSelections> pageGridConverter;
    private SearchViewModel parentViewModel;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchMetricsService searchMetricsService;
    private static final Logger logger = LoggerFactory.getLogger(FilterViewModel.class.getName());

    /* JADX INFO: compiled from: FilterViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HideFilterDialogReason.values().length];
            iArr[HideFilterDialogReason.CANCEL.ordinal()] = 1;
            iArr[HideFilterDialogReason.APPLY_FILTERS.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    public void fetchRequestState(List<Long> trackIds, List<String> trackAsins, Function1<? super List<ContentOwnershipItem>, Unit> callback) {
        Intrinsics.checkNotNullParameter(trackIds, "trackIds");
        Intrinsics.checkNotNullParameter(trackAsins, "trackAsins");
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onRefresh() {
    }

    public FilterViewModel(PageGridViewModelConverter<SearchFilterSelections> pageGridConverter, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(pageGridConverter, "pageGridConverter");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.pageGridConverter = pageGridConverter;
        this.searchMetricsService = searchMetricsService;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.mIsPristine = new MutableLiveData<>();
        this.mIsFilterDialogVisible = new MutableLiveData<>();
    }

    public final SearchViewModel getParentViewModel() {
        return this.parentViewModel;
    }

    public final void setParentViewModel(SearchViewModel searchViewModel) {
        this.parentViewModel = searchViewModel;
    }

    public final LiveData<Boolean> isPristine() {
        return this.mIsPristine;
    }

    public final LiveData<Boolean> isFilterDialogVisible() {
        return this.mIsFilterDialogVisible;
    }

    public final boolean showIvyAudioFilters() {
        return this.searchFeaturesProvider.getAreQualityFiltersEnabled();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onResume() {
        Logger logger2 = logger;
        SearchViewModel searchViewModel = this.parentViewModel;
        logger2.debug(Intrinsics.stringPlus("SPL::onResume. parentViewModel=", searchViewModel == null ? null : searchViewModel.toString()));
        SearchViewModel searchViewModel2 = this.parentViewModel;
        if (searchViewModel2 == null) {
            return;
        }
        this.mCurrentFilterSelections = searchViewModel2.cloneFilterSelections();
        SearchFilterSelections searchFilterSelectionsCloneFilterSelections = searchViewModel2.cloneFilterSelections();
        this.mInitialFilterSelections = searchFilterSelectionsCloneFilterSelections;
        if (searchFilterSelectionsCloneFilterSelections == null) {
            return;
        }
        show(searchFilterSelectionsCloneFilterSelections);
    }

    private final void show(SearchFilterSelections filterSelections) {
        ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().build();
        this.mCurrentFilterSelections = filterSelections;
        if (filterSelections == null) {
            return;
        }
        setIsFetching(true);
        try {
            try {
                updatePageData(executionContextDefaultBuild, this.pageGridConverter.convert(filterSelections));
            } catch (Exception e) {
                logger.error(Intrinsics.stringPlus("SPL::Error updating filter selection: ", e.getMessage()));
            }
        } finally {
            setIsFetching(false);
        }
    }

    public static /* synthetic */ void hide$default(FilterViewModel filterViewModel, HideFilterDialogReason hideFilterDialogReason, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: hide");
        }
        if ((i & 1) != 0) {
            hideFilterDialogReason = HideFilterDialogReason.CANCEL;
        }
        filterViewModel.hide(hideFilterDialogReason);
    }

    public final void hide(HideFilterDialogReason hideFilterDialogReason) {
        Intrinsics.checkNotNullParameter(hideFilterDialogReason, "hideFilterDialogReason");
        this.mIsFilterDialogVisible.postValue(Boolean.FALSE);
        int i = WhenMappings.$EnumSwitchMapping$0[hideFilterDialogReason.ordinal()];
        boolean z = false;
        if (i == 1) {
            SearchMetricsService searchMetricsService = this.searchMetricsService;
            SearchExperienceMode searchExperienceMode = SearchExperienceMode.SEARCH_FILTER;
            SearchViewModel searchViewModel = this.parentViewModel;
            if (searchViewModel != null && searchViewModel.isLibrary()) {
                z = true;
            }
            searchMetricsService.trackCancelFilterButtonUiClick(searchExperienceMode, Boolean.valueOf(z));
            return;
        }
        if (i != 2) {
            return;
        }
        SearchMetricsService searchMetricsService2 = this.searchMetricsService;
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_FILTER;
        SearchViewModel searchViewModel2 = this.parentViewModel;
        if (searchViewModel2 != null && searchViewModel2.isLibrary()) {
            z = true;
        }
        searchMetricsService2.trackApplyFilterButtonUiClick(searchExperienceMode2, Boolean.valueOf(z));
    }

    public final void applyFilters() {
        SearchViewModel parentViewModel;
        hide(HideFilterDialogReason.APPLY_FILTERS);
        SearchFilterSelections searchFilterSelections = this.mCurrentFilterSelections;
        if (searchFilterSelections != null && (parentViewModel = getParentViewModel()) != null) {
            parentViewModel.updateFilterSelections(searchFilterSelections);
        }
        this.mCurrentFilterSelections = null;
        this.mInitialFilterSelections = null;
    }

    @Override // com.amazon.music.find.viewmodels.BasePageGridViewModel
    public PageType getUiPageType() {
        return PageType.SEARCH_FILTER;
    }

    public final void updateFilterSelection(String filterId) {
        Intrinsics.checkNotNullParameter(filterId, "filterId");
        SearchFilterSelections searchFilterSelections = this.mCurrentFilterSelections;
        if (searchFilterSelections == null) {
            return;
        }
        if (searchFilterSelections.containsSelection(filterId)) {
            searchFilterSelections.removeSelection(filterId);
        } else {
            searchFilterSelections.addSelection(filterId);
        }
        SearchFilterSelections searchFilterSelections2 = this.mInitialFilterSelections;
        if (searchFilterSelections2 == null) {
            return;
        }
        boolean zSelectionsEqual = searchFilterSelections.selectionsEqual(searchFilterSelections2.getSelections());
        if (Intrinsics.areEqual(this.mIsPristine.getValue(), Boolean.valueOf(zSelectionsEqual))) {
            return;
        }
        this.mIsPristine.postValue(Boolean.valueOf(zSelectionsEqual));
    }

    public final void resetFilterSelection() {
        SearchFilterSelections searchFilterSelections = this.mCurrentFilterSelections;
        if (searchFilterSelections == null) {
            return;
        }
        searchFilterSelections.reset();
        SearchMetricsService searchMetricsService = this.searchMetricsService;
        SearchExperienceMode searchExperienceMode = SearchExperienceMode.SEARCH_FILTER;
        SearchViewModel parentViewModel = getParentViewModel();
        boolean z = false;
        if (parentViewModel != null && parentViewModel.isLibrary()) {
            z = true;
        }
        searchMetricsService.trackClearFilterButtonUiClick(searchExperienceMode, Boolean.valueOf(z));
    }
}
