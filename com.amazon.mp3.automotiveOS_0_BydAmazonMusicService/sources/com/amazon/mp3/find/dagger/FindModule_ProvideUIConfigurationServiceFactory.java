package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.featuregating.FindFeatureConfigurationProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideUIConfigurationServiceFactory implements Factory<UIConfigurationService> {
    public static UIConfigurationService provideUIConfigurationService(FindModule instance, Context context, SearchFeaturesProvider searchFeaturesProvider, FindFeatureConfigurationProvider findFeatureConfigProvider) {
        return (UIConfigurationService) Preconditions.checkNotNullFromProvides(instance.provideUIConfigurationService(context, searchFeaturesProvider, findFeatureConfigProvider));
    }
}
