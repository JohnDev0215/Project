package com.amazon.music.find.intents;

import com.amazon.music.find.service.EntityTrackingService;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class EntityTrackingIntentService_MembersInjector implements MembersInjector<EntityTrackingIntentService> {
    private final Provider<EntityTrackingService> entityTrackingServiceProvider;

    public EntityTrackingIntentService_MembersInjector(Provider<EntityTrackingService> entityTrackingServiceProvider) {
        this.entityTrackingServiceProvider = entityTrackingServiceProvider;
    }

    public static MembersInjector<EntityTrackingIntentService> create(Provider<EntityTrackingService> entityTrackingServiceProvider) {
        return new EntityTrackingIntentService_MembersInjector(entityTrackingServiceProvider);
    }

    public void injectMembers(EntityTrackingIntentService instance) {
        injectEntityTrackingService(instance, this.entityTrackingServiceProvider.get());
    }

    public static void injectEntityTrackingService(EntityTrackingIntentService instance, EntityTrackingService entityTrackingService) {
        instance.entityTrackingService = entityTrackingService;
    }
}
