package com.amazon.mp3.find.dagger;

import com.amazon.music.find.data.database.AppDatabase;
import com.amazon.music.find.data.database.SearchEntityDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchEntityDaoFactory implements Factory<SearchEntityDao> {
    public static SearchEntityDao provideSearchEntityDao(FindModule instance, AppDatabase appDatabase) {
        return (SearchEntityDao) Preconditions.checkNotNullFromProvides(instance.provideSearchEntityDao(appDatabase));
    }
}
