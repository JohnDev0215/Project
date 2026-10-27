package com.amazon.mp3.find.dagger;

import com.amazon.music.find.api.searchsuggestion.SearchSuggestionApi;
import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.SearchSuggestionService;
import com.amazon.music.find.service.UIConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchSuggestionServiceFactory implements Factory<SearchSuggestionService> {
    public static SearchSuggestionService provideSearchSuggestionService(FindModule instance, SearchEntityDao searchEntityDao, SearchSuggestionApi searchSuggestionApi, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        return (SearchSuggestionService) Preconditions.checkNotNullFromProvides(instance.provideSearchSuggestionService(searchEntityDao, searchSuggestionApi, uiConfigurationService, searchFeaturesProvider));
    }
}
