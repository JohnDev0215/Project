package com.android.car.audio;

import android.media.AudioDeviceInfo;
import android.media.AudioDevicePort;
import android.media.AudioGain;
import android.media.AudioGainConfig;
import android.media.AudioManager;
import android.util.Log;
import com.android.car.CarLog;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
class CarAudioDeviceInfo {
    private final AudioDeviceInfo mAudioDeviceInfo;
    private final int mChannelCount;
    private int mCurrentGain;
    private final int mDefaultGain;
    private final int mEncodingFormat;
    private final int mMaxGain;
    private final int mMinGain;
    private final int mSampleRate;
    private final int mStepValue;

    CarAudioDeviceInfo(AudioDeviceInfo audioDeviceInfo) {
        this.mAudioDeviceInfo = audioDeviceInfo;
        this.mSampleRate = getMaxSampleRate(audioDeviceInfo);
        this.mEncodingFormat = getEncodingFormat(audioDeviceInfo);
        this.mChannelCount = getMaxChannels(audioDeviceInfo);
        AudioGain audioGain = getAudioGain();
        Objects.requireNonNull(audioGain, "No audio gain on device port " + audioDeviceInfo);
        AudioGain audioGain2 = audioGain;
        this.mDefaultGain = audioGain2.defaultValue();
        this.mMaxGain = audioGain2.maxValue();
        this.mMinGain = audioGain2.minValue();
        this.mStepValue = audioGain2.stepValue();
        this.mCurrentGain = -1;
    }

    AudioDeviceInfo getAudioDeviceInfo() {
        return this.mAudioDeviceInfo;
    }

    AudioDevicePort getAudioDevicePort() {
        return this.mAudioDeviceInfo.getPort();
    }

    String getAddress() {
        return this.mAudioDeviceInfo.getAddress();
    }

    int getDefaultGain() {
        return this.mDefaultGain;
    }

    int getMaxGain() {
        return this.mMaxGain;
    }

    int getMinGain() {
        return this.mMinGain;
    }

    int getSampleRate() {
        return this.mSampleRate;
    }

    int getEncodingFormat() {
        return this.mEncodingFormat;
    }

    int getChannelCount() {
        return this.mChannelCount;
    }

    int getStepValue() {
        return this.mStepValue;
    }

    void setCurrentGain(int gainInMillibels) {
        if (gainInMillibels < this.mMinGain) {
            gainInMillibels = this.mMinGain;
        } else if (gainInMillibels > this.mMaxGain) {
            gainInMillibels = this.mMaxGain;
        }
        AudioGain audioGain = getAudioGain();
        if (audioGain == null) {
            Log.e(CarLog.TAG_AUDIO, "getAudioGain() returned null.");
            return;
        }
        AudioGainConfig audioGainConfig = audioGain.buildConfig(1, audioGain.channelMask(), new int[]{gainInMillibels}, 0);
        if (audioGainConfig == null) {
            Log.e(CarLog.TAG_AUDIO, "Failed to construct AudioGainConfig");
            return;
        }
        int r = AudioManager.setAudioPortGain(getAudioDevicePort(), audioGainConfig);
        if (r == 0) {
            this.mCurrentGain = gainInMillibels;
            return;
        }
        Log.e(CarLog.TAG_AUDIO, "Failed to setAudioPortGain: " + r);
    }

    private int getMaxSampleRate(AudioDeviceInfo info) {
        int[] sampleRates = info.getSampleRates();
        if (sampleRates == null || sampleRates.length == 0) {
            return 48000;
        }
        int sampleRate = sampleRates[0];
        for (int i = 1; i < sampleRates.length; i++) {
            if (sampleRates[i] > sampleRate) {
                sampleRate = sampleRates[i];
            }
        }
        return sampleRate;
    }

    private int getEncodingFormat(AudioDeviceInfo info) {
        return 2;
    }

    private int getMaxChannels(AudioDeviceInfo info) {
        int numChannels = 1;
        int[] channelMasks = info.getChannelMasks();
        if (channelMasks == null) {
            return 1;
        }
        for (int channelMask : channelMasks) {
            int currentNumChannels = Integer.bitCount(channelMask);
            if (currentNumChannels > numChannels) {
                numChannels = currentNumChannels;
            }
        }
        return numChannels;
    }

    AudioGain getAudioGain() {
        AudioDevicePort audioPort = getAudioDevicePort();
        if (audioPort != null && audioPort.gains().length > 0) {
            for (AudioGain audioGain : audioPort.gains()) {
                if ((audioGain.mode() & 1) != 0) {
                    return checkAudioGainConfiguration(audioGain);
                }
            }
            return null;
        }
        return null;
    }

    private AudioGain checkAudioGainConfiguration(AudioGain audioGain) {
        Preconditions.checkArgument(audioGain.maxValue() >= audioGain.minValue());
        Preconditions.checkArgument(audioGain.defaultValue() >= audioGain.minValue() && audioGain.defaultValue() <= audioGain.maxValue());
        Preconditions.checkArgument((audioGain.maxValue() - audioGain.minValue()) % audioGain.stepValue() == 0);
        Preconditions.checkArgument((audioGain.defaultValue() - audioGain.minValue()) % audioGain.stepValue() == 0);
        return audioGain;
    }

    public String toString() {
        return "address: " + this.mAudioDeviceInfo.getAddress() + " sampleRate: " + getSampleRate() + " encodingFormat: " + getEncodingFormat() + " channelCount: " + getChannelCount() + " currentGain: " + this.mCurrentGain + " maxGain: " + this.mMaxGain + " minGain: " + this.mMinGain;
    }

    void dump(String indent, PrintWriter writer) {
        writer.printf("%sCarAudioDeviceInfo Device(%s)\n ", indent, this.mAudioDeviceInfo.getAddress());
        writer.printf("%s\tsample rate / encoding format / channel count: %d %d %d\n", indent, Integer.valueOf(getSampleRate()), Integer.valueOf(getEncodingFormat()), Integer.valueOf(getChannelCount()));
        writer.printf("%s\tGain values (min / max / default/ current): %d %d %d %d\n", indent, Integer.valueOf(this.mMinGain), Integer.valueOf(this.mMaxGain), Integer.valueOf(this.mDefaultGain), Integer.valueOf(this.mCurrentGain));
    }
}
