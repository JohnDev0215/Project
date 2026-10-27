package com.amazon.mp3.find.dagger;

import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.EntityTrackingService;
import com.amazon.music.find.service.UIConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideEntityTrackingServiceFactory implements Factory<EntityTrackingService> {
    public static EntityTrackingService provideEntityTrackingService(FindModule instance, SearchEntityDao searchEntityDao, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        return (EntityTrackingService) Preconditions.checkNotNullFromProvides(instance.provideEntityTrackingService(searchEntityDao, uiConfigurationService, searchFeaturesProvider));
    }
}
