package com.amazon.mp3.find.dagger;

import com.amazon.music.find.api.SearchApi;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.SearchService;
import com.amazon.music.find.service.UIConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesSearchServiceFactory implements Factory<SearchService> {
    public static SearchService providesSearchService(FindModule instance, SearchApi<GenericSearchResponse> catalogSearchApi, SearchApi<GenericSearchResponse> librarySearchApi, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        return (SearchService) Preconditions.checkNotNullFromProvides(instance.providesSearchService(catalogSearchApi, librarySearchApi, uiConfigurationService, searchFeaturesProvider));
    }
}
