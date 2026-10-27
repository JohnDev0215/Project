package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSAudioRenderFormat {
    int BufferSize;
    int Channels;
    int SampleBits;
    int SampleRate;

    public voOSAudioRenderFormat(int i, int i2, int i3, int i4) {
        this.SampleRate = i;
        this.Channels = i2;
        this.SampleBits = i3;
        this.BufferSize = i4;
    }

    public int getSampleRate() {
        return this.SampleRate;
    }

    public void setSampleRate(int i) {
        this.SampleRate = i;
    }

    public int getChannels() {
        return this.Channels;
    }

    public void setChannels(int i) {
        this.Channels = i;
    }

    public int getSampleBits() {
        return this.SampleBits;
    }

    public void setSampleBits(int i) {
        this.SampleBits = i;
    }

    public int getBufferSize() {
        return this.BufferSize;
    }

    public void setBufferSize(int i) {
        this.BufferSize = i;
    }
}
