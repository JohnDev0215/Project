package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.data.database.AppDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideAppDatabaseFactory implements Factory<AppDatabase> {
    public static AppDatabase provideAppDatabase(FindModule instance, Context context) {
        return (AppDatabase) Preconditions.checkNotNullFromProvides(instance.provideAppDatabase(context));
    }
}
