package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.data.database.CirrusDatabaseProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesCirrusDatabaseProviderFactory implements Factory<CirrusDatabaseProvider> {
    public static CirrusDatabaseProvider providesCirrusDatabaseProvider(FindModule instance, Context context) {
        return (CirrusDatabaseProvider) Preconditions.checkNotNullFromProvides(instance.providesCirrusDatabaseProvider(context));
    }
}
