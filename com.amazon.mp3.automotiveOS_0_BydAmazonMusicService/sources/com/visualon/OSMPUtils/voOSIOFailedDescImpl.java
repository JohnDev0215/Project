package com.visualon.OSMPUtils;

import android.os.Parcel;
import com.visualon.OSMPUtils.voOSIOFailedDesc;

/* JADX INFO: loaded from: classes7.dex */
public class voOSIOFailedDescImpl implements voOSIOFailedDesc {
    private int mReason;
    private String mResponse;
    private byte[] mResponsePayload;
    private String mUrl;

    public voOSIOFailedDescImpl() {
        this.mReason = -1;
        this.mResponse = null;
        this.mUrl = null;
        this.mResponsePayload = null;
    }

    public voOSIOFailedDescImpl(int i, String str, String str2, byte[] bArr) {
        this.mReason = i;
        this.mResponse = str;
        this.mUrl = str2;
        this.mResponsePayload = bArr;
    }

    public boolean parse(Parcel parcel) {
        if (parcel == null) {
            return false;
        }
        parcel.setDataPosition(0);
        this.mReason = parcel.readInt();
        this.mResponse = parcel.readString();
        this.mUrl = parcel.readString();
        if (parcel.readInt() > 0) {
            this.mResponsePayload = parcel.createByteArray();
        } else {
            this.mResponsePayload = null;
        }
        parcel.recycle();
        return true;
    }

    @Override // com.visualon.OSMPUtils.voOSIOFailedDesc
    public String getURL() {
        return this.mUrl;
    }

    @Override // com.visualon.OSMPUtils.voOSIOFailedDesc
    public voOSIOFailedDesc.VOOSMP_SRC_IO_DOWNLOAD_FAIL_REASON getReason() {
        return voOSIOFailedDesc.VOOSMP_SRC_IO_DOWNLOAD_FAIL_REASON.valueOf(this.mReason);
    }

    @Override // com.visualon.OSMPUtils.voOSIOFailedDesc
    public String getResponse() {
        return this.mResponse;
    }

    @Override // com.visualon.OSMPUtils.voOSIOFailedDesc
    public byte[] getResponsePayload() {
        return this.mResponsePayload;
    }
}
