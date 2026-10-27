package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideElementBindingProviderFactory implements Factory<ElementBindingProvider> {
    public static ElementBindingProvider provideElementBindingProvider(ExploreModule instance, UserSubscriptionProvider userSubscriptionProvider, StyleSheetProvider styleSheetProvider, DownloadProvider downloadProvider) {
        return (ElementBindingProvider) Preconditions.checkNotNullFromProvides(instance.provideElementBindingProvider(userSubscriptionProvider, styleSheetProvider, downloadProvider));
    }
}
