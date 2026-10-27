package com.amazon.mp3.find.dagger;

import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.LibraryStateService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideLibraryStateServiceFactory implements Factory<LibraryStateService> {
    public static LibraryStateService provideLibraryStateService(FindModule instance, LibrarySearchDao librarySearchDao, SearchFeaturesProvider searchFeaturesProvider) {
        return (LibraryStateService) Preconditions.checkNotNullFromProvides(instance.provideLibraryStateService(librarySearchDao, searchFeaturesProvider));
    }
}
