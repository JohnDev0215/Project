package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSAudioFormat {
    int Channels();

    int SampleBits();

    int SampleRate();

    boolean parse(Parcel parcel);

    int setChannels(int i);

    int setSampleBits(int i);

    int setSampleRate(int i);
}
