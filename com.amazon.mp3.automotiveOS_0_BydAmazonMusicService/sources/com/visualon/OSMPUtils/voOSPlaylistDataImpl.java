package com.visualon.OSMPUtils;

import android.os.Parcel;
import com.visualon.OSMPUtils.voOSType;
import java.util.Arrays;

/* JADX INFO: loaded from: classes7.dex */
public class voOSPlaylistDataImpl implements voOSPlaylistData {
    private static final String TAG = "@@@voOSPlaylistDataImpl.java";
    private byte[] mData;
    private int mErrorCode;
    private voOSType.VOOSMP_STREAMING_ERROR_TYPE mErrorType;
    private String mNewUrl;
    private voOSType.VOOSMP_SRC_PLAYLIST_TYPE mPlaylistType;
    private String mRootUrl;
    private String mUrl;

    @Override // com.visualon.OSMPUtils.voOSPlaylistData
    public String getSZRootUrl() {
        return this.mRootUrl;
    }

    @Override // com.visualon.OSMPUtils.voOSPlaylistData
    public String getSZUrl() {
        return this.mUrl;
    }

    @Override // com.visualon.OSMPUtils.voOSPlaylistData
    public String getSZNewUrl() {
        return this.mNewUrl;
    }

    @Override // com.visualon.OSMPUtils.voOSPlaylistData
    public byte[] getData() {
        return this.mData;
    }

    @Override // com.visualon.OSMPUtils.voOSPlaylistData
    public int getDataSize() {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    @Override // com.visualon.OSMPUtils.voOSPlaylistData
    public voOSType.VOOSMP_SRC_PLAYLIST_TYPE getPlaylistType() {
        return this.mPlaylistType;
    }

    @Override // com.visualon.OSMPUtils.voOSPlaylistData
    public voOSType.VOOSMP_STREAMING_ERROR_TYPE getErrorType() {
        return this.mErrorType;
    }

    @Override // com.visualon.OSMPUtils.voOSPlaylistData
    public int getErrorCode() {
        return this.mErrorCode;
    }

    public boolean parse(Parcel parcel) {
        if (parcel == null) {
            return false;
        }
        parcel.setDataPosition(0);
        this.mRootUrl = parcel.readString();
        this.mUrl = parcel.readString();
        this.mNewUrl = parcel.readString();
        int i = parcel.readInt();
        if (i > 0) {
            byte[] bArr = new byte[i];
            this.mData = bArr;
            parcel.readByteArray(bArr);
        }
        this.mPlaylistType = voOSType.VOOSMP_SRC_PLAYLIST_TYPE.valueOf(parcel.readInt());
        this.mErrorType = voOSType.VOOSMP_STREAMING_ERROR_TYPE.valueOf(parcel.readInt());
        this.mErrorCode = parcel.readInt();
        parcel.recycle();
        return true;
    }

    public String toString() {
        return "voOSPlaylistDataImpl [mRootUrl=" + this.mRootUrl + ", mUrl=" + this.mUrl + ", mNewUrl=" + this.mNewUrl + ", mData=" + Arrays.toString(this.mData) + ", mErrorType=" + this.mErrorType + ", mErrorCode=" + this.mErrorCode + ", mPlaylistType=" + this.mPlaylistType + "]";
    }
}
