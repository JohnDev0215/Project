package com.amazon.avod.media.audioformat;

import com.amazon.avod.config.DeviceCapabilityConfig;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.playbackclient.audiotrack.AudioTrackConfig;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AudioFormatProvider {
    private final AudioTrackConfig mAudioTrackConfig;
    private final DeviceCapabilityConfig mDeviceCapabilityConfig;
    private volatile PlatformAudioSupport mPlatformAudioSupport;

    public interface PlatformAudioSupport {
        boolean isDolbyDigitalPlusWithAtmosSupported();

        boolean isPassThroughAvailable();
    }

    private static class SingletonHolder {
        private static final AudioFormatProvider INSTANCE = new AudioFormatProvider();

        private SingletonHolder() {
        }
    }

    public static AudioFormatProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private AudioFormatProvider() {
        this(AudioTrackConfig.getInstance(), DeviceCapabilityConfig.getInstance());
    }

    AudioFormatProvider(@Nonnull AudioTrackConfig audioTrackConfig, @Nonnull DeviceCapabilityConfig deviceCapabilityConfig) {
        this.mPlatformAudioSupport = new DefaultPlatformAudioSupport();
        this.mAudioTrackConfig = (AudioTrackConfig) Preconditions.checkNotNull(audioTrackConfig);
        this.mDeviceCapabilityConfig = (DeviceCapabilityConfig) Preconditions.checkNotNull(deviceCapabilityConfig);
    }

    @Nonnull
    public AudioFormat determineAudioFormat() {
        return getCustomerPreferredAudioFormat(this.mAudioTrackConfig, this.mPlatformAudioSupport.isPassThroughAvailable() && this.mDeviceCapabilityConfig.supportsDolby51(), this.mPlatformAudioSupport.isDolbyDigitalPlusWithAtmosSupported());
    }

    @Nonnull
    protected static AudioFormat getCustomerPreferredAudioFormat(@Nonnull AudioTrackConfig audioTrackConfig, boolean z, boolean z2) {
        Preconditions.checkNotNull(audioTrackConfig, "audioTrackConfig");
        if (!z) {
            return AudioFormat.STEREO;
        }
        AudioFormat customerPreferredAudioFormat = audioTrackConfig.getCustomerPreferredAudioFormat();
        return (z2 || !AudioFormat.AC_3_7_1.equals(customerPreferredAudioFormat)) ? customerPreferredAudioFormat : AudioFormat.AC_3_5_1;
    }

    private static class DefaultPlatformAudioSupport implements PlatformAudioSupport {
        @Override // com.amazon.avod.media.audioformat.AudioFormatProvider.PlatformAudioSupport
        public boolean isDolbyDigitalPlusWithAtmosSupported() {
            return false;
        }

        @Override // com.amazon.avod.media.audioformat.AudioFormatProvider.PlatformAudioSupport
        public boolean isPassThroughAvailable() {
            return true;
        }

        private DefaultPlatformAudioSupport() {
        }
    }
}
