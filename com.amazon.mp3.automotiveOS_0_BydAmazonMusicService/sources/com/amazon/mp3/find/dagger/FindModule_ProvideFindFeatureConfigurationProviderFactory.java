package com.amazon.mp3.find.dagger;

import com.amazon.music.find.featuregating.FindFeatureConfigurationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideFindFeatureConfigurationProviderFactory implements Factory<FindFeatureConfigurationProvider> {
    public static FindFeatureConfigurationProvider provideFindFeatureConfigurationProvider(FindModule instance) {
        return (FindFeatureConfigurationProvider) Preconditions.checkNotNullFromProvides(instance.provideFindFeatureConfigurationProvider());
    }
}
