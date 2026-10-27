package com.amazon.mp3.find.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchSuggestionConfigurationProviderFactory implements Factory<SearchSuggestionServiceConfigurationProvider> {
    public static SearchSuggestionServiceConfigurationProvider provideSearchSuggestionConfigurationProvider(FindModule instance, Context context) {
        return (SearchSuggestionServiceConfigurationProvider) Preconditions.checkNotNullFromProvides(instance.provideSearchSuggestionConfigurationProvider(context));
    }
}
