package com.amazon.avod.playback.renderer.visualon;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class VisualOnNativeLibrary_Factory implements Factory<VisualOnNativeLibrary> {
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public VisualOnNativeLibrary_Factory(Provider<MediaSystemSharedContext> provider) {
        this.sharedContextProvider = provider;
    }

    @Override // javax.inject.Provider
    public VisualOnNativeLibrary get() {
        return new VisualOnNativeLibrary(this.sharedContextProvider.get());
    }

    public static Factory<VisualOnNativeLibrary> create(Provider<MediaSystemSharedContext> provider) {
        return new VisualOnNativeLibrary_Factory(provider);
    }
}
