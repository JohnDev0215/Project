package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSVideoInfoImpl implements voOSVideoInfo {
    private int mAngle;
    private voOSVideoFormat mFormat;
    private String mVideoDesc;

    @Override // com.visualon.OSMPUtils.voOSVideoInfo
    public String VideoDesc() {
        return null;
    }

    public voOSVideoInfoImpl() {
        this.mFormat = null;
        this.mAngle = 0;
        this.mVideoDesc = null;
    }

    public voOSVideoInfoImpl(voOSVideoFormat voosvideoformat, int i) {
        this.mFormat = voosvideoformat;
        this.mAngle = i;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoInfo
    public boolean parse(Parcel parcel) {
        voOSVideoFormatImpl voosvideoformatimpl = new voOSVideoFormatImpl();
        this.mFormat = voosvideoformatimpl;
        voosvideoformatimpl.parse(parcel);
        this.mAngle = parcel.readInt();
        return true;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoInfo
    public voOSVideoFormat Format() {
        return this.mFormat;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoInfo
    public int Angle() {
        return this.mAngle;
    }
}
