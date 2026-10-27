package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideNotificationProviderFactory implements Factory<NotificationProvider> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvideNotificationProviderFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public NotificationProvider get() {
        return provideNotificationProvider(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvideNotificationProviderFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvideNotificationProviderFactory(module, contextProvider);
    }

    public static NotificationProvider provideNotificationProvider(ExploreModule instance, Context context) {
        return (NotificationProvider) Preconditions.checkNotNullFromProvides(instance.provideNotificationProvider(context));
    }
}
