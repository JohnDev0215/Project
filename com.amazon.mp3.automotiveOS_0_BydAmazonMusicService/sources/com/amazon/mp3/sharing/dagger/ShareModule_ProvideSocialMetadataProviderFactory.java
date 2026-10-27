package com.amazon.mp3.sharing.dagger;

import android.content.Context;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class ShareModule_ProvideSocialMetadataProviderFactory implements Factory<SocialMetadataProvider> {
    private final Provider<Context> contextProvider;
    private final ShareModule module;

    public ShareModule_ProvideSocialMetadataProviderFactory(ShareModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public SocialMetadataProvider get() {
        return provideSocialMetadataProvider(this.module, this.contextProvider.get());
    }

    public static ShareModule_ProvideSocialMetadataProviderFactory create(ShareModule module, Provider<Context> contextProvider) {
        return new ShareModule_ProvideSocialMetadataProviderFactory(module, contextProvider);
    }

    public static SocialMetadataProvider provideSocialMetadataProvider(ShareModule instance, Context context) {
        return (SocialMetadataProvider) Preconditions.checkNotNullFromProvides(instance.provideSocialMetadataProvider(context));
    }
}
