package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioAttributes {
    final PlayerAudioQuality audioQuality;
    final PlayerAudioQuality bestAvailableAudioQuality;
    final short bestAvailableBitDepth;
    final int bestAvailableSampleRate;
    final short bitDepth;
    final int bitrate;
    final DeviceCapability deviceCapability;
    final boolean isDownloaded;
    final int sampleRate;

    public AudioAttributes(PlayerAudioQuality playerAudioQuality, int i, short s, int i2, short s2, int i3, PlayerAudioQuality playerAudioQuality2, DeviceCapability deviceCapability, boolean z) {
        this.audioQuality = playerAudioQuality;
        this.bitrate = i;
        this.bitDepth = s;
        this.sampleRate = i2;
        this.bestAvailableBitDepth = s2;
        this.bestAvailableSampleRate = i3;
        this.bestAvailableAudioQuality = playerAudioQuality2;
        this.deviceCapability = deviceCapability;
        this.isDownloaded = z;
    }

    public PlayerAudioQuality getAudioQuality() {
        return this.audioQuality;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public short getBitDepth() {
        return this.bitDepth;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public short getBestAvailableBitDepth() {
        return this.bestAvailableBitDepth;
    }

    public int getBestAvailableSampleRate() {
        return this.bestAvailableSampleRate;
    }

    public PlayerAudioQuality getBestAvailableAudioQuality() {
        return this.bestAvailableAudioQuality;
    }

    public DeviceCapability getDeviceCapability() {
        return this.deviceCapability;
    }

    public boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributes)) {
            return false;
        }
        AudioAttributes audioAttributes = (AudioAttributes) obj;
        return this.audioQuality == audioAttributes.audioQuality && this.bitrate == audioAttributes.bitrate && this.bitDepth == audioAttributes.bitDepth && this.sampleRate == audioAttributes.sampleRate && this.bestAvailableBitDepth == audioAttributes.bestAvailableBitDepth && this.bestAvailableSampleRate == audioAttributes.bestAvailableSampleRate && this.bestAvailableAudioQuality == audioAttributes.bestAvailableAudioQuality && this.deviceCapability.equals(audioAttributes.deviceCapability) && this.isDownloaded == audioAttributes.isDownloaded;
    }

    public int hashCode() {
        return ((((((((((((((((527 + this.audioQuality.hashCode()) * 31) + this.bitrate) * 31) + this.bitDepth) * 31) + this.sampleRate) * 31) + this.bestAvailableBitDepth) * 31) + this.bestAvailableSampleRate) * 31) + this.bestAvailableAudioQuality.hashCode()) * 31) + this.deviceCapability.hashCode()) * 31) + (this.isDownloaded ? 1 : 0);
    }

    public String toString() {
        return "AudioAttributes{audioQuality=" + this.audioQuality + ",bitrate=" + this.bitrate + ",bitDepth=" + ((int) this.bitDepth) + ",sampleRate=" + this.sampleRate + ",bestAvailableBitDepth=" + ((int) this.bestAvailableBitDepth) + ",bestAvailableSampleRate=" + this.bestAvailableSampleRate + ",bestAvailableAudioQuality=" + this.bestAvailableAudioQuality + ",deviceCapability=" + this.deviceCapability + ",isDownloaded=" + this.isDownloaded + "}";
    }
}
