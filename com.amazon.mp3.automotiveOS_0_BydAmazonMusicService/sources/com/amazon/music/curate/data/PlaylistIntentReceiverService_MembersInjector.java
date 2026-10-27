package com.amazon.music.curate.data;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class PlaylistIntentReceiverService_MembersInjector implements MembersInjector<PlaylistIntentReceiverService> {
    private final Provider<FeatureFlagProvider> featureFlagProvider;
    private final Provider<RequestInterceptor> requestInterceptorProvider;

    public PlaylistIntentReceiverService_MembersInjector(Provider<RequestInterceptor> requestInterceptorProvider, Provider<FeatureFlagProvider> featureFlagProvider) {
        this.requestInterceptorProvider = requestInterceptorProvider;
        this.featureFlagProvider = featureFlagProvider;
    }

    public static MembersInjector<PlaylistIntentReceiverService> create(Provider<RequestInterceptor> requestInterceptorProvider, Provider<FeatureFlagProvider> featureFlagProvider) {
        return new PlaylistIntentReceiverService_MembersInjector(requestInterceptorProvider, featureFlagProvider);
    }

    public void injectMembers(PlaylistIntentReceiverService instance) {
        injectRequestInterceptor(instance, this.requestInterceptorProvider.get());
        injectFeatureFlagProvider(instance, this.featureFlagProvider.get());
    }

    public static void injectRequestInterceptor(PlaylistIntentReceiverService instance, RequestInterceptor requestInterceptor) {
        instance.requestInterceptor = requestInterceptor;
    }

    public static void injectFeatureFlagProvider(PlaylistIntentReceiverService instance, FeatureFlagProvider featureFlagProvider) {
        instance.featureFlagProvider = featureFlagProvider;
    }
}
