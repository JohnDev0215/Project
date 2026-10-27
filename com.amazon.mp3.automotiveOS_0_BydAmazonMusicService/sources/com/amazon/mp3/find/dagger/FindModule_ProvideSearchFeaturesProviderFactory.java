package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchFeaturesProviderFactory implements Factory<SearchFeaturesProvider> {
    public static SearchFeaturesProvider provideSearchFeaturesProvider(FindModule instance, Context context) {
        return (SearchFeaturesProvider) Preconditions.checkNotNullFromProvides(instance.provideSearchFeaturesProvider(context));
    }
}
