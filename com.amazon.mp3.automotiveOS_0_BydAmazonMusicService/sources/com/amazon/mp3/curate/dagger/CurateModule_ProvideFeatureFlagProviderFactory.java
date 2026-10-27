package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.provider.FeatureFlagProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideFeatureFlagProviderFactory implements Factory<FeatureFlagProvider> {
    public static FeatureFlagProvider provideFeatureFlagProvider(CurateModule instance) {
        return (FeatureFlagProvider) Preconditions.checkNotNullFromProvides(instance.provideFeatureFlagProvider());
    }
}
