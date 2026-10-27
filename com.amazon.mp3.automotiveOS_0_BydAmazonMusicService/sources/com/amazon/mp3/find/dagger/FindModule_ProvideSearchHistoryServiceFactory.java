package com.amazon.mp3.find.dagger;

import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.data.database.SearchQueryDao;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.SearchHistoryService;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.service.UserSettingsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchHistoryServiceFactory implements Factory<SearchHistoryService> {
    public static SearchHistoryService provideSearchHistoryService(FindModule instance, SearchQueryDao searchQueryDao, SearchEntityDao searchEntityDao, UIConfigurationService uiConfigurationService, UserSettingsService userSettingsService, SearchFeaturesProvider searchFeaturesProvider) {
        return (SearchHistoryService) Preconditions.checkNotNullFromProvides(instance.provideSearchHistoryService(searchQueryDao, searchEntityDao, uiConfigurationService, userSettingsService, searchFeaturesProvider));
    }
}
