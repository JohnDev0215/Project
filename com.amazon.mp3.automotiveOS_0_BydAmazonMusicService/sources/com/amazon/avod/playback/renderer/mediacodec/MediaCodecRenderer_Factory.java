package com.amazon.avod.playback.renderer.mediacodec;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaCodecRenderer_Factory implements Factory<MediaCodecRenderer> {
    private final Provider<MediaCodecDeviceCapabilityDetector> capabilityDetectorProvider;
    private final Provider<DrmFramework> drmFrameworkProvider;
    private final Provider<MediaSystemSharedContext> mediaSystemContextProvider;

    public MediaCodecRenderer_Factory(Provider<MediaSystemSharedContext> provider, Provider<DrmFramework> provider2, Provider<MediaCodecDeviceCapabilityDetector> provider3) {
        this.mediaSystemContextProvider = provider;
        this.drmFrameworkProvider = provider2;
        this.capabilityDetectorProvider = provider3;
    }

    @Override // javax.inject.Provider
    public MediaCodecRenderer get() {
        return new MediaCodecRenderer(this.mediaSystemContextProvider.get(), this.drmFrameworkProvider.get(), this.capabilityDetectorProvider.get());
    }

    public static Factory<MediaCodecRenderer> create(Provider<MediaSystemSharedContext> provider, Provider<DrmFramework> provider2, Provider<MediaCodecDeviceCapabilityDetector> provider3) {
        return new MediaCodecRenderer_Factory(provider, provider2, provider3);
    }
}
