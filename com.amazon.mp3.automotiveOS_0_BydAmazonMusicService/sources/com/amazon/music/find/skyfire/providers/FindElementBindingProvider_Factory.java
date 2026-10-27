package com.amazon.music.find.skyfire.providers;

import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class FindElementBindingProvider_Factory implements Factory<FindElementBindingProvider> {
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<StyleSheetProvider> styleSheetProvider;
    private final Provider<UserSubscriptionProvider> userSubscriptionProvider;

    public FindElementBindingProvider_Factory(Provider<UserSubscriptionProvider> userSubscriptionProvider, Provider<StyleSheetProvider> styleSheetProvider, Provider<DownloadProvider> downloadProvider) {
        this.userSubscriptionProvider = userSubscriptionProvider;
        this.styleSheetProvider = styleSheetProvider;
        this.downloadProvider = downloadProvider;
    }

    @Override // javax.inject.Provider
    public FindElementBindingProvider get() {
        return newInstance(this.userSubscriptionProvider.get(), this.styleSheetProvider.get(), this.downloadProvider.get());
    }

    public static FindElementBindingProvider_Factory create(Provider<UserSubscriptionProvider> userSubscriptionProvider, Provider<StyleSheetProvider> styleSheetProvider, Provider<DownloadProvider> downloadProvider) {
        return new FindElementBindingProvider_Factory(userSubscriptionProvider, styleSheetProvider, downloadProvider);
    }

    public static FindElementBindingProvider newInstance(UserSubscriptionProvider userSubscriptionProvider, StyleSheetProvider styleSheetProvider, DownloadProvider downloadProvider) {
        return new FindElementBindingProvider(userSubscriptionProvider, styleSheetProvider, downloadProvider);
    }
}
