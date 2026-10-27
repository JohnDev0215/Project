package com.amazon.mp3.find.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideInstantSearchSuggestionConfigurationProviderFactory implements Factory<InstantSearchSuggestionServiceConfigurationProvider> {
    public static InstantSearchSuggestionServiceConfigurationProvider provideInstantSearchSuggestionConfigurationProvider(FindModule instance, Context context) {
        return (InstantSearchSuggestionServiceConfigurationProvider) Preconditions.checkNotNullFromProvides(instance.provideInstantSearchSuggestionConfigurationProvider(context));
    }
}
