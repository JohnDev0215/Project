package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideCapabilitiesInfoFactory implements Factory<CapabilitiesInfo> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvideCapabilitiesInfoFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public CapabilitiesInfo get() {
        return provideCapabilitiesInfo(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvideCapabilitiesInfoFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvideCapabilitiesInfoFactory(module, contextProvider);
    }

    public static CapabilitiesInfo provideCapabilitiesInfo(ExploreModule instance, Context context) {
        return (CapabilitiesInfo) Preconditions.checkNotNullFromProvides(instance.provideCapabilitiesInfo(context));
    }
}
