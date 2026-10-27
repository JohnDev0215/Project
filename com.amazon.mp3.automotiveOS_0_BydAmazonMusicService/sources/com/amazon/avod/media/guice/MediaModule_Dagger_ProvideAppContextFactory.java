package com.amazon.avod.media.guice;

import android.content.Context;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideAppContextFactory implements Factory<Context> {
    private final Provider<MediaSystemSharedContext> contextProvider;
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvideAppContextFactory(MediaModule_Dagger mediaModule_Dagger, Provider<MediaSystemSharedContext> provider) {
        this.module = mediaModule_Dagger;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public Context get() {
        return (Context) Preconditions.checkNotNull(this.module.provideAppContext(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Context> create(MediaModule_Dagger mediaModule_Dagger, Provider<MediaSystemSharedContext> provider) {
        return new MediaModule_Dagger_ProvideAppContextFactory(mediaModule_Dagger, provider);
    }

    public static Context proxyProvideAppContext(MediaModule_Dagger mediaModule_Dagger, MediaSystemSharedContext mediaSystemSharedContext) {
        return mediaModule_Dagger.provideAppContext(mediaSystemSharedContext);
    }
}
