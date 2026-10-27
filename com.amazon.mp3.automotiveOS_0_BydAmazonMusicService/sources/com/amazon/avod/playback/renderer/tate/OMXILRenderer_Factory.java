package com.amazon.avod.playback.renderer.tate;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class OMXILRenderer_Factory implements Factory<OMXILRenderer> {
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public OMXILRenderer_Factory(Provider<MediaSystemSharedContext> provider) {
        this.sharedContextProvider = provider;
    }

    @Override // javax.inject.Provider
    public OMXILRenderer get() {
        return new OMXILRenderer(this.sharedContextProvider.get());
    }

    public static Factory<OMXILRenderer> create(Provider<MediaSystemSharedContext> provider) {
        return new OMXILRenderer_Factory(provider);
    }
}
