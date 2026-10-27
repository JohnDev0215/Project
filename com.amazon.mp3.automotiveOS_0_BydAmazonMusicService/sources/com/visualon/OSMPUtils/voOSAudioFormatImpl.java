package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSAudioFormatImpl implements voOSAudioFormat {
    private int mChannels;
    private int mSampleBits;
    private int mSampleRate;

    public voOSAudioFormatImpl(int i, int i2, int i3) {
        this.mSampleRate = i;
        this.mChannels = i2;
        this.mSampleBits = i3;
    }

    public voOSAudioFormatImpl() {
    }

    @Override // com.visualon.OSMPUtils.voOSAudioFormat
    public boolean parse(Parcel parcel) {
        this.mSampleRate = parcel.readInt();
        this.mChannels = parcel.readInt();
        this.mSampleBits = parcel.readInt();
        return true;
    }

    @Override // com.visualon.OSMPUtils.voOSAudioFormat
    public int SampleRate() {
        return this.mSampleRate;
    }

    @Override // com.visualon.OSMPUtils.voOSAudioFormat
    public int Channels() {
        return this.mChannels;
    }

    @Override // com.visualon.OSMPUtils.voOSAudioFormat
    public int SampleBits() {
        return this.mSampleBits;
    }

    @Override // com.visualon.OSMPUtils.voOSAudioFormat
    public int setSampleRate(int i) {
        this.mSampleRate = i;
        return 0;
    }

    @Override // com.visualon.OSMPUtils.voOSAudioFormat
    public int setChannels(int i) {
        this.mChannels = i;
        return 0;
    }

    @Override // com.visualon.OSMPUtils.voOSAudioFormat
    public int setSampleBits(int i) {
        this.mSampleBits = i;
        return 0;
    }
}
