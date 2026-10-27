package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.provider.SubscriptionProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideSubscriptionProviderFactory implements Factory<SubscriptionProvider> {
    public static SubscriptionProvider provideSubscriptionProvider(CurateModule instance) {
        return (SubscriptionProvider) Preconditions.checkNotNullFromProvides(instance.provideSubscriptionProvider());
    }
}
