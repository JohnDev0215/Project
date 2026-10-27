package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSStreamInfo {
    int getBitrate();

    int getSelInfo();

    int getStreamID();

    int getTrackCount();

    voOSTrackInfo[] getTrackInfo();

    boolean parse(Parcel parcel);
}
