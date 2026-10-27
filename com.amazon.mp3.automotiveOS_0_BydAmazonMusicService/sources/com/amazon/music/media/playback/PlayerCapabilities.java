package com.amazon.music.media.playback;

/* JADX INFO: loaded from: classes4.dex */
public class PlayerCapabilities {
    private final int maximumBitDepth;
    private final int maximumSampleRate;
    private final boolean supportsHiRes;

    public PlayerCapabilities(boolean z, int i, int i2) {
        this.supportsHiRes = z;
        this.maximumBitDepth = i;
        this.maximumSampleRate = i2;
    }

    public int getMaximumBitDepth() {
        return this.maximumBitDepth;
    }

    public int getMaximumSampleRate() {
        return this.maximumSampleRate;
    }

    public String toString() {
        return "PlayerCapabilities { supportsHiRes = " + this.supportsHiRes + ",maximumBitDepth=" + this.maximumBitDepth + ",maximumSampleRate=" + this.maximumSampleRate + "}";
    }
}
