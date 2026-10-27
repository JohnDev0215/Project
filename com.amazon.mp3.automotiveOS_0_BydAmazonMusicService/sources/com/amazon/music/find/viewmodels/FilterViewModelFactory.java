package com.amazon.music.find.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.find.converter.PageGridViewModelConverter;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ%\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/find/viewmodels/FilterViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "pageGridConverter", "Lcom/amazon/music/find/converter/PageGridViewModelConverter;", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/converter/PageGridViewModelConverter;Lcom/amazon/music/find/metrics/SearchMetricsService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FilterViewModelFactory implements ViewModelProvider.Factory {
    private final PageGridViewModelConverter<SearchFilterSelections> pageGridConverter;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchMetricsService searchMetricsService;

    public FilterViewModelFactory(PageGridViewModelConverter<SearchFilterSelections> pageGridConverter, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(pageGridConverter, "pageGridConverter");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.pageGridConverter = pageGridConverter;
        this.searchMetricsService = searchMetricsService;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return new FilterViewModel(this.pageGridConverter, this.searchMetricsService, this.searchFeaturesProvider);
    }
}
