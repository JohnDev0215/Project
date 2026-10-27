package com.amazon.music.find.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.find.service.LibraryStateService;
import com.amazon.music.find.service.SearchHistoryService;
import com.amazon.music.find.service.SearchService;
import com.amazon.music.find.service.SearchSuggestionService;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.musicensembleservice.brush.Page;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBS\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J%\u0010\u0016\u001a\u0002H\u0017\"\b\b\u0000\u0010\u0017*\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001aH\u0016¢\u0006\u0002\u0010\u001bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "pageGridConverter", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Page;", "searchSuggestionService", "Lcom/amazon/music/find/service/SearchSuggestionService;", "searchHistoryService", "Lcom/amazon/music/find/service/SearchHistoryService;", "converterService", "Lcom/amazon/music/find/service/ConverterService;", "searchService", "Lcom/amazon/music/find/service/SearchService;", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "libraryStateService", "Lcom/amazon/music/find/service/LibraryStateService;", "(Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;Lcom/amazon/music/find/service/SearchSuggestionService;Lcom/amazon/music/find/service/SearchHistoryService;Lcom/amazon/music/find/service/ConverterService;Lcom/amazon/music/find/service/SearchService;Lcom/amazon/music/find/metrics/SearchMetricsService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/service/LibraryStateService;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchViewModelFactory implements ViewModelProvider.Factory {
    private static final String TAG = "SPL";
    private final ConverterService converterService;
    private final LibraryStateService libraryStateService;
    private final BaseContainerModelConverter<Page> pageGridConverter;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchHistoryService searchHistoryService;
    private final SearchMetricsService searchMetricsService;
    private final SearchService searchService;
    private final SearchSuggestionService searchSuggestionService;
    private final UIConfigurationService uiConfigurationService;
    private static final Logger logger = LoggerFactory.getLogger(SearchViewModelFactory.class.getName());

    public SearchViewModelFactory(BaseContainerModelConverter<Page> pageGridConverter, SearchSuggestionService searchSuggestionService, SearchHistoryService searchHistoryService, ConverterService converterService, SearchService searchService, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService, LibraryStateService libraryStateService) {
        Intrinsics.checkNotNullParameter(pageGridConverter, "pageGridConverter");
        Intrinsics.checkNotNullParameter(searchSuggestionService, "searchSuggestionService");
        Intrinsics.checkNotNullParameter(searchHistoryService, "searchHistoryService");
        Intrinsics.checkNotNullParameter(converterService, "converterService");
        Intrinsics.checkNotNullParameter(searchService, "searchService");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(libraryStateService, "libraryStateService");
        this.pageGridConverter = pageGridConverter;
        this.searchSuggestionService = searchSuggestionService;
        this.searchHistoryService = searchHistoryService;
        this.converterService = converterService;
        this.searchService = searchService;
        this.searchMetricsService = searchMetricsService;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.uiConfigurationService = uiConfigurationService;
        this.libraryStateService = libraryStateService;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        logger.debug("SPL::create->creating SearchViewModel");
        return new SearchViewModel(this.pageGridConverter, this.searchSuggestionService, this.searchHistoryService, this.converterService, this.searchService, this.searchMetricsService, this.searchFeaturesProvider, this.uiConfigurationService, this.libraryStateService);
    }
}
