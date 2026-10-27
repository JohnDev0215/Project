package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideNetworkInterceptorFactory implements Factory<NetworkInterceptor> {
    private final FindModule module;

    public FindModule_ProvideNetworkInterceptorFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public NetworkInterceptor get() {
        return provideNetworkInterceptor(this.module);
    }

    public static FindModule_ProvideNetworkInterceptorFactory create(FindModule module) {
        return new FindModule_ProvideNetworkInterceptorFactory(module);
    }

    public static NetworkInterceptor provideNetworkInterceptor(FindModule instance) {
        return (NetworkInterceptor) Preconditions.checkNotNullFromProvides(instance.provideNetworkInterceptor());
    }
}
