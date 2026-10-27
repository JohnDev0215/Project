package com.amazon.avod.media.playback;

import com.amazon.avod.playback.sye.SyeVideoPresentationFactory;
import dagger.internal.Factory;
import java.util.Set;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class FirstMatchVideoPresentationFactory_Factory implements Factory<FirstMatchVideoPresentationFactory> {
    private final Provider<Set<VideoPresentationFactory>> factoriesProvider;
    private final Provider<SyeVideoPresentationFactory> syeVideoPresentationFactoryProvider;

    public FirstMatchVideoPresentationFactory_Factory(Provider<SyeVideoPresentationFactory> provider, Provider<Set<VideoPresentationFactory>> provider2) {
        this.syeVideoPresentationFactoryProvider = provider;
        this.factoriesProvider = provider2;
    }

    @Override // javax.inject.Provider
    public FirstMatchVideoPresentationFactory get() {
        return new FirstMatchVideoPresentationFactory(this.syeVideoPresentationFactoryProvider.get(), this.factoriesProvider.get());
    }

    public static Factory<FirstMatchVideoPresentationFactory> create(Provider<SyeVideoPresentationFactory> provider, Provider<Set<VideoPresentationFactory>> provider2) {
        return new FirstMatchVideoPresentationFactory_Factory(provider, provider2);
    }
}
