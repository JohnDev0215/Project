package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioFormat {
    public static final long BACK_CENTER = 256;
    public static final long BACK_LEFT = 16;
    public static final long BACK_RIGHT = 32;
    public static final long FRONT_CENTER = 4;
    public static final long FRONT_LEFT = 1;
    public static final long FRONT_LEFT_OF_CENTER = 64;
    public static final long FRONT_RIGHT = 2;
    public static final long FRONT_RIGHT_OF_CENTER = 128;
    public static final long LOW_FREQUENCY = 8;
    public static final long LOW_FREQUENCY_2 = 34359738368L;
    public static final long SIDE_LEFT = 512;
    public static final long SIDE_RIGHT = 1024;
    public static final long STEREO_LEFT = 536870912;
    public static final long STEREO_RIGHT = 1073741824;
    public static final long SURROUND_DIRECT_LEFT = 8589934592L;
    public static final long SURROUND_DIRECT_RIGHT = 17179869184L;
    public static final long TOP_BACK_CENTER = 65536;
    public static final long TOP_BACK_LEFT = 32768;
    public static final long TOP_BACK_RIGHT = 131072;
    public static final long TOP_CENTER = 2048;
    public static final long TOP_FRONT_CENTER = 8192;
    public static final long TOP_FRONT_LEFT = 4096;
    public static final long TOP_FRONT_RIGHT = 16384;
    public static final long WIDE_LEFT = 2147483648L;
    public static final long WIDE_RIGHT = 4294967296L;
    final int channelCount;
    final long channelLayout;
    final AudioSampleFormat sampleFormat;
    final long sampleRate;

    public AudioFormat(long j, long j2, AudioSampleFormat audioSampleFormat, int i) {
        this.sampleRate = j;
        this.channelLayout = j2;
        this.sampleFormat = audioSampleFormat;
        this.channelCount = i;
    }

    public long getSampleRate() {
        return this.sampleRate;
    }

    public long getChannelLayout() {
        return this.channelLayout;
    }

    public AudioSampleFormat getSampleFormat() {
        return this.sampleFormat;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public String toString() {
        return "AudioFormat{sampleRate=" + this.sampleRate + ",channelLayout=" + this.channelLayout + ",sampleFormat=" + this.sampleFormat + ",channelCount=" + this.channelCount + "}";
    }
}
