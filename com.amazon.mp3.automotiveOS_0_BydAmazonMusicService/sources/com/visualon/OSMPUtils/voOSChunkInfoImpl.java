package com.visualon.OSMPUtils;

import android.os.Parcel;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public class voOSChunkInfoImpl implements voOSChunkInfo {
    private static final int uSize = 2048;
    private byte[] datablock;
    int mBitrate;
    long mChunkID;
    int mDownloadSize;
    long mDownloadTime;
    long mDuration;
    int mErrorCode;
    voOSType.VOOSMP_STREAMING_ERROR_TYPE mErrorType;
    int mPeriodSequenceNumber;
    String mRootUrl;
    long mStartTime;
    long mTimeScale;
    int mType;
    String mUrl;

    public voOSChunkInfoImpl() {
        this.datablock = new byte[2048];
    }

    public voOSChunkInfoImpl(int i, String str, String str2, long j, int i2, long j2, long j3, int i3, int i4, long j4, long j5, int i5, int i6) {
        this.datablock = new byte[2048];
        this.mType = i;
        this.mRootUrl = str;
        this.mUrl = str2;
        this.mStartTime = j;
        this.mPeriodSequenceNumber = i2;
        this.mDuration = j2;
        this.mTimeScale = j3;
        this.mErrorType = voOSType.VOOSMP_STREAMING_ERROR_TYPE.valueOf(i3);
        this.mErrorCode = i4;
        this.mChunkID = j4;
        this.mDownloadTime = j5;
        this.mDownloadSize = i5;
        this.mBitrate = i6;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public int Type() {
        return this.mType;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public String RootUrl() {
        return this.mRootUrl;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public String Url() {
        return this.mUrl;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public long StartTime() {
        return this.mStartTime;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public int PeriodSequenceNumber() {
        return this.mPeriodSequenceNumber;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public long Duration() {
        return this.mDuration;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public long TimeScale() {
        return this.mTimeScale;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public voOSType.VOOSMP_STREAMING_ERROR_TYPE ErrorType() {
        return this.mErrorType;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public int ErrorCode() {
        return this.mErrorCode;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public long ChunkID() {
        return this.mChunkID;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public long DownloadTime() {
        return this.mDownloadTime;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public int DownloadSize() {
        return this.mDownloadSize;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkInfo
    public int Bitrate() {
        return this.mBitrate;
    }

    public boolean parse(Parcel parcel) {
        int i;
        if (parcel == null) {
            return false;
        }
        parcel.setDataPosition(0);
        this.mType = parcel.readInt();
        parcel.readByteArray(this.datablock);
        int i2 = 0;
        while (true) {
            i = 2048;
            if (i2 >= 2048) {
                i2 = 2048;
                break;
            }
            if (this.datablock[i2] == 0) {
                break;
            }
            i2++;
        }
        this.mRootUrl = new String(this.datablock, 0, i2);
        parcel.readByteArray(this.datablock);
        int i3 = 0;
        while (true) {
            if (i3 >= 2048) {
                break;
            }
            if (this.datablock[i3] == 0) {
                i = i3;
                break;
            }
            i3++;
        }
        this.mUrl = new String(this.datablock, 0, i);
        this.mStartTime = parcel.readLong();
        this.mPeriodSequenceNumber = parcel.readInt();
        this.mDuration = parcel.readLong();
        this.mTimeScale = parcel.readLong();
        this.mErrorType = voOSType.VOOSMP_STREAMING_ERROR_TYPE.valueOf(parcel.readInt());
        this.mErrorCode = parcel.readInt();
        this.mChunkID = parcel.readLong();
        this.mDownloadTime = parcel.readLong();
        this.mDownloadSize = parcel.readInt();
        this.mBitrate = parcel.readInt();
        parcel.recycle();
        return true;
    }
}
