package com.amazon.mp3.alps.dagger;

import com.amazon.music.subscription.UserSubscription;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ALPSModule_ProvideUserSubscriptionFactory implements Factory<UserSubscription> {
    private final ALPSModule module;

    public ALPSModule_ProvideUserSubscriptionFactory(ALPSModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public UserSubscription get() {
        return provideUserSubscription(this.module);
    }

    public static ALPSModule_ProvideUserSubscriptionFactory create(ALPSModule module) {
        return new ALPSModule_ProvideUserSubscriptionFactory(module);
    }

    public static UserSubscription provideUserSubscription(ALPSModule instance) {
        return (UserSubscription) Preconditions.checkNotNullFromProvides(instance.provideUserSubscription());
    }
}
