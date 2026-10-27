package com.amazon.mp3.find.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchConfigurationProviderFactory implements Factory<SearchServiceConfigurationProvider> {
    public static SearchServiceConfigurationProvider provideSearchConfigurationProvider(FindModule instance, Context context) {
        return (SearchServiceConfigurationProvider) Preconditions.checkNotNullFromProvides(instance.provideSearchConfigurationProvider(context));
    }
}
