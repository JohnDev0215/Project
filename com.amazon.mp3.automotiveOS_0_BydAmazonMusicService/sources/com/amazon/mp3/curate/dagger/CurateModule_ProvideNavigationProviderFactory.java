package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.provider.NavigationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideNavigationProviderFactory implements Factory<NavigationProvider> {
    public static NavigationProvider provideNavigationProvider(CurateModule instance) {
        return (NavigationProvider) Preconditions.checkNotNullFromProvides(instance.provideNavigationProvider());
    }
}
