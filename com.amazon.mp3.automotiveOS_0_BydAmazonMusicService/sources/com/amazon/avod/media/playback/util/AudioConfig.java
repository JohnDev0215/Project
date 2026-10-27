package com.amazon.avod.media.playback.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class AudioConfig extends CodecConfig {
    private final int mBitRate;
    private final int mChannels;
    private final int mSampleRate;

    public AudioConfig(String str, int i, int i2, int i3) {
        super(str);
        Preconditions.checkArgument(i > 0 && i < 10000000);
        Preconditions.checkArgument(i2 > 0 && i2 < 256);
        Preconditions.checkArgument(i3 > 0 && i3 < 100000000);
        this.mSampleRate = i;
        this.mChannels = i2;
        this.mBitRate = i3;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("FourCC", getFourCC()).add("SampleRate", getSampleRate()).add("Channels", getChannels()).add("BitRate", getBitRate()).toString();
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getChannels() {
        return this.mChannels;
    }

    public int getBitRate() {
        return this.mBitRate;
    }
}
