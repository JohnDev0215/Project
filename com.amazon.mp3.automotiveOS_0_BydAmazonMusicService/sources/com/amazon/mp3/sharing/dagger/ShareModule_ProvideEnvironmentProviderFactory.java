package com.amazon.mp3.sharing.dagger;

import android.content.Context;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class ShareModule_ProvideEnvironmentProviderFactory implements Factory<EnvironmentProvider> {
    private final Provider<Context> contextProvider;
    private final ShareModule module;

    public ShareModule_ProvideEnvironmentProviderFactory(ShareModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public EnvironmentProvider get() {
        return provideEnvironmentProvider(this.module, this.contextProvider.get());
    }

    public static ShareModule_ProvideEnvironmentProviderFactory create(ShareModule module, Provider<Context> contextProvider) {
        return new ShareModule_ProvideEnvironmentProviderFactory(module, contextProvider);
    }

    public static EnvironmentProvider provideEnvironmentProvider(ShareModule instance, Context context) {
        return (EnvironmentProvider) Preconditions.checkNotNullFromProvides(instance.provideEnvironmentProvider(context));
    }
}
