package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideNotificationProviderFactory implements Factory<NotificationProvider> {
    private final Provider<Context> contextProvider;
    private final FindModule module;

    public FindModule_ProvideNotificationProviderFactory(FindModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public NotificationProvider get() {
        return provideNotificationProvider(this.module, this.contextProvider.get());
    }

    public static FindModule_ProvideNotificationProviderFactory create(FindModule module, Provider<Context> contextProvider) {
        return new FindModule_ProvideNotificationProviderFactory(module, contextProvider);
    }

    public static NotificationProvider provideNotificationProvider(FindModule instance, Context context) {
        return (NotificationProvider) Preconditions.checkNotNullFromProvides(instance.provideNotificationProvider(context));
    }
}
