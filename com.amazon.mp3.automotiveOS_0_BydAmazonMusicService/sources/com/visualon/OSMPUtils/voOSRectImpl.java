package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSRectImpl implements voOSRect {
    int mBottom;
    int mLeft;
    int mRight;
    int mTop;

    public voOSRectImpl(int i, int i2, int i3, int i4) {
        this.mLeft = i;
        this.mTop = i2;
        this.mRight = i3;
        this.mBottom = i4;
    }

    public static voOSRectImpl parse(Parcel parcel) {
        if (parcel == null) {
            return null;
        }
        parcel.setDataPosition(0);
        voOSRectImpl voosrectimpl = new voOSRectImpl(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        parcel.recycle();
        return voosrectimpl;
    }

    @Override // com.visualon.OSMPUtils.voOSRect
    public int Left() {
        return this.mLeft;
    }

    @Override // com.visualon.OSMPUtils.voOSRect
    public int Top() {
        return this.mTop;
    }

    @Override // com.visualon.OSMPUtils.voOSRect
    public int Right() {
        return this.mRight;
    }

    @Override // com.visualon.OSMPUtils.voOSRect
    public int Bottom() {
        return this.mBottom;
    }
}
