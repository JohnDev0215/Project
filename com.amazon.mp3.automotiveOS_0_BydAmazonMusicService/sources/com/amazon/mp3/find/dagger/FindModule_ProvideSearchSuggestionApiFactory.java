package com.amazon.mp3.find.dagger;

import com.amazon.music.find.api.searchsuggestion.SearchSuggestionApi;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchSuggestionApiFactory implements Factory<SearchSuggestionApi> {
    public static SearchSuggestionApi provideSearchSuggestionApi(FindModule instance, SearchSuggestionServiceConfigurationProvider configurationProvider, InstantSearchSuggestionServiceConfigurationProvider instantSearchConfigProvider, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        return (SearchSuggestionApi) Preconditions.checkNotNullFromProvides(instance.provideSearchSuggestionApi(configurationProvider, instantSearchConfigProvider, uiConfigurationService, searchFeaturesProvider));
    }
}
