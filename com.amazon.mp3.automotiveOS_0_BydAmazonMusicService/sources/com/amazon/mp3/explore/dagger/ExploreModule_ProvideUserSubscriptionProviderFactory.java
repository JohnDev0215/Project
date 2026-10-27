package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideUserSubscriptionProviderFactory implements Factory<UserSubscriptionProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideUserSubscriptionProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public UserSubscriptionProvider get() {
        return provideUserSubscriptionProvider(this.module);
    }

    public static ExploreModule_ProvideUserSubscriptionProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideUserSubscriptionProviderFactory(module);
    }

    public static UserSubscriptionProvider provideUserSubscriptionProvider(ExploreModule instance) {
        return (UserSubscriptionProvider) Preconditions.checkNotNullFromProvides(instance.provideUserSubscriptionProvider());
    }
}
