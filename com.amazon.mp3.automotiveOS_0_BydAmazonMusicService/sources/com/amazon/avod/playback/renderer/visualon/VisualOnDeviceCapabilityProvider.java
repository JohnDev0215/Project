package com.amazon.avod.playback.renderer.visualon;

import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class VisualOnDeviceCapabilityProvider implements Provider<DeviceCapability> {
    final PlaybackNativeLibrariesLoader mNativeLibrariesLoader;

    public VisualOnDeviceCapabilityProvider(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this.mNativeLibrariesLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    @Nullable
    public DeviceCapability get() {
        if (this.mNativeLibrariesLoader.waitForInitialization()) {
            return VisualOnRendererJni.getDeviceCapability();
        }
        return null;
    }
}
