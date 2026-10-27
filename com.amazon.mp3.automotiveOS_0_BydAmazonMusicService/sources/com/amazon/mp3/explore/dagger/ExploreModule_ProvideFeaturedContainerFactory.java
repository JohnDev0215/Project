package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.explore.containers.FeaturedContainer;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideFeaturedContainerFactory implements Factory<FeaturedContainer> {
    public static FeaturedContainer provideFeaturedContainer(ExploreModule instance, Context context, SkyFireProvider skyFireProvider, SkyFireConfigurationProvider skyFireConfigurationProvider, ExternalActionProvider externalActionProvider, MetricsProvider metricsProvider, NavigationProvider navigationProvider, CastingProvider castingProvider) {
        return (FeaturedContainer) Preconditions.checkNotNullFromProvides(instance.provideFeaturedContainer(context, skyFireProvider, skyFireConfigurationProvider, externalActionProvider, metricsProvider, navigationProvider, castingProvider));
    }
}
