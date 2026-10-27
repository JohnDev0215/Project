package com.amazon.mp3.find.dagger;

import com.amazon.music.find.api.SearchApi;
import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesLibrarySearchApiFactory implements Factory<SearchApi<GenericSearchResponse>> {
    public static SearchApi<GenericSearchResponse> providesLibrarySearchApi(FindModule instance, LibrarySearchDao librarySearchDao, SearchFeaturesProvider searchFeaturesProvider) {
        return (SearchApi) Preconditions.checkNotNullFromProvides(instance.providesLibrarySearchApi(librarySearchDao, searchFeaturesProvider));
    }
}
