package com.amazon.mp3.find.dagger;

import com.amazon.music.find.metrics.SearchMetricsApi;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesSearchMetricsServiceFactory implements Factory<SearchMetricsService> {
    public static SearchMetricsService providesSearchMetricsService(FindModule instance, SearchMetricsApi searchMetricsApi, SearchFeaturesProvider searchFeaturesProvider) {
        return (SearchMetricsService) Preconditions.checkNotNullFromProvides(instance.providesSearchMetricsService(searchMetricsApi, searchFeaturesProvider));
    }
}
