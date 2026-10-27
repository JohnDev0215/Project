package com.amazon.mp3.find.dagger;

import com.amazon.music.find.converter.PageGridViewModelConverter;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.viewmodels.FilterViewModelFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideFilterViewModelFactoryFactory implements Factory<FilterViewModelFactory> {
    public static FilterViewModelFactory provideFilterViewModelFactory(FindModule instance, PageGridViewModelConverter<SearchFilterSelections> pageGridConverter, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider) {
        return (FilterViewModelFactory) Preconditions.checkNotNullFromProvides(instance.provideFilterViewModelFactory(pageGridConverter, searchMetricsService, searchFeaturesProvider));
    }
}
