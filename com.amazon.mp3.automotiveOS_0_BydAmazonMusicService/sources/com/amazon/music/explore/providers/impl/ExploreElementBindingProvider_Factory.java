package com.amazon.music.explore.providers.impl;

import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class ExploreElementBindingProvider_Factory implements Factory<ExploreElementBindingProvider> {
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<StyleSheetProvider> styleSheetProvider;
    private final Provider<UserSubscriptionProvider> userSubscriptionProvider;

    public ExploreElementBindingProvider_Factory(Provider<UserSubscriptionProvider> provider, Provider<StyleSheetProvider> provider2, Provider<DownloadProvider> provider3) {
        this.userSubscriptionProvider = provider;
        this.styleSheetProvider = provider2;
        this.downloadProvider = provider3;
    }

    @Override // javax.inject.Provider
    public ExploreElementBindingProvider get() {
        return newInstance(this.userSubscriptionProvider.get(), this.styleSheetProvider.get(), this.downloadProvider.get());
    }

    public static ExploreElementBindingProvider_Factory create(Provider<UserSubscriptionProvider> provider, Provider<StyleSheetProvider> provider2, Provider<DownloadProvider> provider3) {
        return new ExploreElementBindingProvider_Factory(provider, provider2, provider3);
    }

    public static ExploreElementBindingProvider newInstance(UserSubscriptionProvider userSubscriptionProvider, StyleSheetProvider styleSheetProvider, DownloadProvider downloadProvider) {
        return new ExploreElementBindingProvider(userSubscriptionProvider, styleSheetProvider, downloadProvider);
    }
}
