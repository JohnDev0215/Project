package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSPCMBufferImpl extends voOSBufferImpl implements voOSPCMBuffer {
    public voOSPCMBufferImpl() {
    }

    public voOSPCMBufferImpl(long j, int i, byte[] bArr) {
        super(j, i, bArr);
    }

    public boolean parse(Parcel parcel) {
        if (parcel == null) {
            return false;
        }
        parcel.setDataPosition(0);
        this.mTimestamp = parcel.readLong();
        int i = parcel.readInt();
        this.mBufferSize = i;
        if (i <= 0) {
            return true;
        }
        byte[] bArr = new byte[i];
        this.mBuffer = bArr;
        parcel.readByteArray(bArr);
        return true;
    }
}
