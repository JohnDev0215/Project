package com.amazon.mp3.find.dagger;

import com.amazon.music.find.data.database.CirrusDatabaseProvider;
import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.providers.SearchEntityFactory;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesLibrarySearchDaoFactory implements Factory<LibrarySearchDao> {
    public static LibrarySearchDao providesLibrarySearchDao(FindModule instance, CirrusDatabaseProvider cirrusDatabaseProvider, SearchEntityFactory searchEntityFactory, SearchFeaturesProvider searchFeaturesProvider) {
        return (LibrarySearchDao) Preconditions.checkNotNullFromProvides(instance.providesLibrarySearchDao(cirrusDatabaseProvider, searchEntityFactory, searchFeaturesProvider));
    }
}
