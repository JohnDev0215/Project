package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSStreamInfoImpl implements voOSStreamInfo {
    int Bitrate;
    int SelInfo;
    int StreamID;
    int TrackCount;
    private voOSTrackInfo[] TrackInfo;

    public voOSStreamInfoImpl() {
    }

    public voOSStreamInfoImpl(int i, int i2, int i3, int i4, voOSTrackInfo[] voostrackinfoArr) {
        this.StreamID = i;
        this.SelInfo = i2;
        this.Bitrate = i3;
        this.TrackCount = i4;
        this.TrackInfo = voostrackinfoArr;
    }

    @Override // com.visualon.OSMPUtils.voOSStreamInfo
    public boolean parse(Parcel parcel) {
        this.StreamID = parcel.readInt();
        this.SelInfo = parcel.readInt();
        this.Bitrate = parcel.readInt();
        int i = parcel.readInt();
        this.TrackCount = i;
        if (i <= 0) {
            return true;
        }
        this.TrackInfo = new voOSTrackInfo[i];
        for (int i2 = 0; i2 < this.TrackCount; i2++) {
            this.TrackInfo[i2] = new voOSTrackInfoImpl();
            this.TrackInfo[i2].parse(parcel);
        }
        return true;
    }

    @Override // com.visualon.OSMPUtils.voOSStreamInfo
    public int getStreamID() {
        return this.StreamID;
    }

    @Override // com.visualon.OSMPUtils.voOSStreamInfo
    public int getSelInfo() {
        return this.SelInfo;
    }

    @Override // com.visualon.OSMPUtils.voOSStreamInfo
    public int getBitrate() {
        return this.Bitrate;
    }

    @Override // com.visualon.OSMPUtils.voOSStreamInfo
    public int getTrackCount() {
        return this.TrackCount;
    }

    @Override // com.visualon.OSMPUtils.voOSStreamInfo
    public voOSTrackInfo[] getTrackInfo() {
        return this.TrackInfo;
    }
}
