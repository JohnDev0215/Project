package com.amazon.mp3.find.dagger;

import com.amazon.music.find.api.SearchApi;
import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.providers.SearchEntityFactory;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesRemoteSearchApiFactory implements Factory<SearchApi<GenericSearchResponse>> {
    public static SearchApi<GenericSearchResponse> providesRemoteSearchApi(FindModule instance, SearchServiceConfigurationProvider configurationProvider, SearchFeaturesProvider searchFeaturesProvider, SearchEntityFactory searchEntityFactory, UIConfigurationService uiConfigurationService, LibrarySearchDao librarySearchDao) {
        return (SearchApi) Preconditions.checkNotNullFromProvides(instance.providesRemoteSearchApi(configurationProvider, searchFeaturesProvider, searchEntityFactory, uiConfigurationService, librarySearchDao));
    }
}
