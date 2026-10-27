package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSSEIUserDataImpl implements voOSSEIUserData {
    private static final String TAG = "@@@voOSSEIUserDataImpl";
    private int nCount;
    private int[] nSize;
    private byte[] pBuffer;

    @Override // com.visualon.OSMPUtils.voOSSEIUserData
    public int getFieldCount() {
        return this.nCount;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIUserData
    public int getFieldLength(int i) {
        int[] iArr = this.nSize;
        if (iArr != null && iArr.length > i) {
            return iArr[i];
        }
        return 0;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIUserData
    public byte[] getDataBuffer() {
        return this.pBuffer;
    }

    public void parse(Parcel parcel) {
        parcel.setDataPosition(0);
        int i = parcel.readInt();
        this.nCount = i;
        if (i <= 0) {
            i = 1;
        }
        int[] iArr = new int[i];
        this.nSize = iArr;
        iArr[0] = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.nCount; i3++) {
            this.nSize[i3] = parcel.readInt();
            i2 += this.nSize[i3];
        }
        if (i2 > 0) {
            byte[] bArr = new byte[i2];
            this.pBuffer = bArr;
            parcel.readByteArray(bArr);
        }
        parcel.recycle();
    }
}
