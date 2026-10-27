package com.nuance.android.vocalizer;

import android.car.trust.TrustedDeviceInfo;

/* JADX INFO: loaded from: classes.dex */
public class VocalizerAudioSettings {
    public static final int MONO = 1;
    public static final int STEREO = 2;
    protected int mAudioBufferSize;
    public int mChannels;
    public int mRate;

    protected VocalizerAudioSettings() {
        this(-1, 1);
    }

    public VocalizerAudioSettings(int i, int i2) {
        this.mRate = i;
        this.mChannels = i2;
        this.mAudioBufferSize = 0;
    }

    protected static int convertTimeToFrames(VocalizerAudioSettings vocalizerAudioSettings, int i) {
        if (vocalizerAudioSettings != null) {
            return (i * vocalizerAudioSettings.mRate) / 1000;
        }
        return 0;
    }

    protected static int convertBytesToFrames(VocalizerAudioSettings vocalizerAudioSettings, int i) {
        if (vocalizerAudioSettings == null) {
            return 0;
        }
        int i2 = i / 2;
        return vocalizerAudioSettings.mChannels == 2 ? i2 / 2 : i2;
    }

    public boolean isStereo() {
        return this.mChannels == 2;
    }

    protected int convertToAudioStreamChannel() {
        return this.mChannels == 1 ? 4 : 12;
    }

    public String toString() {
        if (this.mRate == -1 || this.mChannels == -1) {
            return TrustedDeviceInfo.DEFAULT_NAME;
        }
        return "" + (this.mRate / 1000) + " kHz " + (isStereo() ? "Stereo" : "Mono");
    }
}
