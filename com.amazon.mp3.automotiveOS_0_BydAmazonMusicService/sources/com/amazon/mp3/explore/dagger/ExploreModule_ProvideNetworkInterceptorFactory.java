package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideNetworkInterceptorFactory implements Factory<NetworkInterceptor> {
    private final ExploreModule module;

    public ExploreModule_ProvideNetworkInterceptorFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public NetworkInterceptor get() {
        return provideNetworkInterceptor(this.module);
    }

    public static ExploreModule_ProvideNetworkInterceptorFactory create(ExploreModule module) {
        return new ExploreModule_ProvideNetworkInterceptorFactory(module);
    }

    public static NetworkInterceptor provideNetworkInterceptor(ExploreModule instance) {
        return (NetworkInterceptor) Preconditions.checkNotNullFromProvides(instance.provideNetworkInterceptor());
    }
}
