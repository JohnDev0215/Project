package com.amazon.avod.media.guice;

import com.amazon.avod.media.framework.profiling.MediaProfiler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideMediaProfilerFactory implements Factory<MediaProfiler> {
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvideMediaProfilerFactory(MediaModule_Dagger mediaModule_Dagger) {
        this.module = mediaModule_Dagger;
    }

    @Override // javax.inject.Provider
    public MediaProfiler get() {
        return (MediaProfiler) Preconditions.checkNotNull(this.module.provideMediaProfiler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaProfiler> create(MediaModule_Dagger mediaModule_Dagger) {
        return new MediaModule_Dagger_ProvideMediaProfilerFactory(mediaModule_Dagger);
    }
}
