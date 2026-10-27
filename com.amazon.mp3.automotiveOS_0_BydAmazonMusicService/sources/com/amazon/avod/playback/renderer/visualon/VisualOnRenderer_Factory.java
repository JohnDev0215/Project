package com.amazon.avod.playback.renderer.visualon;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class VisualOnRenderer_Factory implements Factory<VisualOnRenderer> {
    private final Provider<FileSystem> fileSystemProvider;
    private final Provider<PlaybackNativeLibrariesLoader> nativeLibrariesLoaderProvider;
    private final Provider<VisualOnRendererType> rendererTypeProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public VisualOnRenderer_Factory(Provider<MediaSystemSharedContext> provider, Provider<VisualOnRendererType> provider2, Provider<FileSystem> provider3, Provider<PlaybackNativeLibrariesLoader> provider4) {
        this.sharedContextProvider = provider;
        this.rendererTypeProvider = provider2;
        this.fileSystemProvider = provider3;
        this.nativeLibrariesLoaderProvider = provider4;
    }

    @Override // javax.inject.Provider
    public VisualOnRenderer get() {
        return new VisualOnRenderer(this.sharedContextProvider.get(), this.rendererTypeProvider.get(), this.fileSystemProvider.get(), this.nativeLibrariesLoaderProvider.get());
    }

    public static Factory<VisualOnRenderer> create(Provider<MediaSystemSharedContext> provider, Provider<VisualOnRendererType> provider2, Provider<FileSystem> provider3, Provider<PlaybackNativeLibrariesLoader> provider4) {
        return new VisualOnRenderer_Factory(provider, provider2, provider3, provider4);
    }
}
