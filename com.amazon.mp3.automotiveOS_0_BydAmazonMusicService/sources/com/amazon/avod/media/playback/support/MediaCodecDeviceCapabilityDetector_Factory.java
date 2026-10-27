package com.amazon.avod.media.playback.support;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaCodecDeviceCapabilityDetector_Factory implements Factory<MediaCodecDeviceCapabilityDetector> {
    private final Provider<MediaSystemSharedContext> contextProvider;
    private final Provider<DrmFramework> drmFrameworkProvider;

    public MediaCodecDeviceCapabilityDetector_Factory(Provider<MediaSystemSharedContext> provider, Provider<DrmFramework> provider2) {
        this.contextProvider = provider;
        this.drmFrameworkProvider = provider2;
    }

    @Override // javax.inject.Provider
    public MediaCodecDeviceCapabilityDetector get() {
        return new MediaCodecDeviceCapabilityDetector(this.contextProvider.get(), this.drmFrameworkProvider.get());
    }

    public static Factory<MediaCodecDeviceCapabilityDetector> create(Provider<MediaSystemSharedContext> provider, Provider<DrmFramework> provider2) {
        return new MediaCodecDeviceCapabilityDetector_Factory(provider, provider2);
    }
}
