package com.amazon.mp3.find.dagger;

import com.amazon.mp3.brush.converters.BrushPageGridConverter;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.find.service.SearchHistoryService;
import com.amazon.music.find.service.SearchResultTransformerService;
import com.amazon.music.find.service.SearchService;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.viewmodels.CarModeSearchViewModelFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesCarModeSearchViewModelFactoryFactory implements Factory<CarModeSearchViewModelFactory> {
    public static CarModeSearchViewModelFactory providesCarModeSearchViewModelFactory(FindModule instance, BrushPageGridConverter pageGridConverter, ConverterService converterService, SearchResultTransformerService transformerService, SearchService searchService, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService, SearchHistoryService searchHistoryService) {
        return (CarModeSearchViewModelFactory) Preconditions.checkNotNullFromProvides(instance.providesCarModeSearchViewModelFactory(pageGridConverter, converterService, transformerService, searchService, searchMetricsService, searchFeaturesProvider, uiConfigurationService, searchHistoryService));
    }
}
