package com.amazon.mp3.find.dagger;

import com.amazon.mp3.brush.converters.BrushPageGridConverter;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.find.service.LibraryStateService;
import com.amazon.music.find.service.SearchHistoryService;
import com.amazon.music.find.service.SearchService;
import com.amazon.music.find.service.SearchSuggestionService;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.viewmodels.SearchViewModelFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchViewModelFactoryFactory implements Factory<SearchViewModelFactory> {
    public static SearchViewModelFactory provideSearchViewModelFactory(FindModule instance, BrushPageGridConverter pageGridConverter, SearchSuggestionService searchSuggestionService, SearchHistoryService searchHistoryService, ConverterService converterService, SearchService searchService, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService, LibraryStateService libraryStateService) {
        return (SearchViewModelFactory) Preconditions.checkNotNullFromProvides(instance.provideSearchViewModelFactory(pageGridConverter, searchSuggestionService, searchHistoryService, converterService, searchService, searchMetricsService, searchFeaturesProvider, uiConfigurationService, libraryStateService));
    }
}
