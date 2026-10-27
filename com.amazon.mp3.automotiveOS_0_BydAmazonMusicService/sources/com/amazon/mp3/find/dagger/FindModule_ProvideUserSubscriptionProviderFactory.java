package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideUserSubscriptionProviderFactory implements Factory<UserSubscriptionProvider> {
    private final FindModule module;

    public FindModule_ProvideUserSubscriptionProviderFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public UserSubscriptionProvider get() {
        return provideUserSubscriptionProvider(this.module);
    }

    public static FindModule_ProvideUserSubscriptionProviderFactory create(FindModule module) {
        return new FindModule_ProvideUserSubscriptionProviderFactory(module);
    }

    public static UserSubscriptionProvider provideUserSubscriptionProvider(FindModule instance) {
        return (UserSubscriptionProvider) Preconditions.checkNotNullFromProvides(instance.provideUserSubscriptionProvider());
    }
}
