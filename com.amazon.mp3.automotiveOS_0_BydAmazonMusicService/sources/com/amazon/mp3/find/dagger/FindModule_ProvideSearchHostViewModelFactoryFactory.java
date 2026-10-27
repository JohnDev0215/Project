package com.amazon.mp3.find.dagger;

import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.viewmodels.SearchHostViewModelFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchHostViewModelFactoryFactory implements Factory<SearchHostViewModelFactory> {
    public static SearchHostViewModelFactory provideSearchHostViewModelFactory(FindModule instance, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService) {
        return (SearchHostViewModelFactory) Preconditions.checkNotNullFromProvides(instance.provideSearchHostViewModelFactory(searchMetricsService, searchFeaturesProvider, uiConfigurationService));
    }
}
