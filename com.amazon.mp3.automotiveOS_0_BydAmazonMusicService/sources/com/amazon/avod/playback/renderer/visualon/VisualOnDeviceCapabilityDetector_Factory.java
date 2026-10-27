package com.amazon.avod.playback.renderer.visualon;

import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class VisualOnDeviceCapabilityDetector_Factory implements Factory<VisualOnDeviceCapabilityDetector> {
    private final Provider<PlaybackNativeLibrariesLoader> nativeLibrariesLoaderProvider;

    public VisualOnDeviceCapabilityDetector_Factory(Provider<PlaybackNativeLibrariesLoader> provider) {
        this.nativeLibrariesLoaderProvider = provider;
    }

    @Override // javax.inject.Provider
    public VisualOnDeviceCapabilityDetector get() {
        return new VisualOnDeviceCapabilityDetector(this.nativeLibrariesLoaderProvider.get());
    }

    public static Factory<VisualOnDeviceCapabilityDetector> create(Provider<PlaybackNativeLibrariesLoader> provider) {
        return new VisualOnDeviceCapabilityDetector_Factory(provider);
    }
}
