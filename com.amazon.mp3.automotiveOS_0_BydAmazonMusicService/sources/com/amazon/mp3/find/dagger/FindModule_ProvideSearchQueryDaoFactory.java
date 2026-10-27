package com.amazon.mp3.find.dagger;

import com.amazon.music.find.data.database.AppDatabase;
import com.amazon.music.find.data.database.SearchQueryDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchQueryDaoFactory implements Factory<SearchQueryDao> {
    public static SearchQueryDao provideSearchQueryDao(FindModule instance, AppDatabase appDatabase) {
        return (SearchQueryDao) Preconditions.checkNotNullFromProvides(instance.provideSearchQueryDao(appDatabase));
    }
}
