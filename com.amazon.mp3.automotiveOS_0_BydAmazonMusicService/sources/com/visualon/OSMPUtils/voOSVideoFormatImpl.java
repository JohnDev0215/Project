package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSVideoFormatImpl implements voOSVideoFormat {
    private int mHeight;
    private int mType;
    private int mWidth;

    public voOSVideoFormatImpl() {
    }

    public voOSVideoFormatImpl(int i, int i2, int i3) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mType = i3;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoFormat
    public boolean parse(Parcel parcel) {
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
        this.mType = parcel.readInt();
        return true;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoFormat
    public int Width() {
        return this.mWidth;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoFormat
    public int Height() {
        return this.mHeight;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoFormat
    public int Type() {
        return this.mType;
    }
}
