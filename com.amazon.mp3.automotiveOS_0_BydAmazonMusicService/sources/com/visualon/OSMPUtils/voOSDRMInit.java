package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSDRMInit {
    private byte[] mDRMData;
    private long mDRMDataHandle;

    public voOSDRMInit(long j, byte[] bArr) {
        this.mDRMDataHandle = j;
        this.mDRMData = bArr;
    }

    public long getDRMInitDataHandle() {
        return this.mDRMDataHandle;
    }

    public int getDRMInitSize() {
        byte[] bArr = this.mDRMData;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public byte[] getDRMInitData() {
        return this.mDRMData;
    }
}
