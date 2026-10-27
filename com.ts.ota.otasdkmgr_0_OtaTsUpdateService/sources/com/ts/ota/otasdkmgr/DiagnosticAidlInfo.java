package com.ts.ota.otasdkmgr;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DiagnosticAidlInfo implements Parcelable {
    public static final Parcelable.Creator<DiagnosticAidlInfo> CREATOR = new Parcelable.Creator<DiagnosticAidlInfo>() { // from class: com.ts.ota.otasdkmgr.DiagnosticAidlInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DiagnosticAidlInfo createFromParcel(Parcel in) {
            return new DiagnosticAidlInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DiagnosticAidlInfo[] newArray(int size) {
            return new DiagnosticAidlInfo[size];
        }
    };
    public String ecuDiaId;
    public String ecuDtcId;
    public String ecuDtcInfo;
    public String ecuId;
    public String ecuReprogramType;
    public String extDataRecordNumber;
    public int readDtcInfoType;
    public String recordMask;
    public String snapshotRecordNumber;
    public int status;
    public String statusMask;

    public DiagnosticAidlInfo(String ecuId, String ecuDiaId, String ecuReprogramType, int readDtcInfoType, String ecuDtcInfo, String ecuDtcId) {
        this.ecuId = ecuId;
        this.ecuDiaId = ecuDiaId;
        this.ecuReprogramType = ecuReprogramType;
        this.readDtcInfoType = readDtcInfoType;
        this.ecuDtcInfo = ecuDtcInfo;
        this.ecuDtcId = ecuDtcId;
    }

    public DiagnosticAidlInfo(String ecuId, String ecuDiaId, String ecuReprogramType, int readDtcInfoType, String ecuDtcInfo, String ecuDtcId, int status) {
        this.ecuId = ecuId;
        this.ecuDiaId = ecuDiaId;
        this.ecuReprogramType = ecuReprogramType;
        this.readDtcInfoType = readDtcInfoType;
        this.ecuDtcInfo = ecuDtcInfo;
        this.ecuDtcId = ecuDtcId;
        this.status = status;
    }

    public DiagnosticAidlInfo() {
    }

    protected DiagnosticAidlInfo(Parcel in) {
        this.ecuId = in.readString();
        this.ecuDiaId = in.readString();
        this.ecuReprogramType = in.readString();
        this.readDtcInfoType = in.readInt();
        this.ecuDtcInfo = in.readString();
        this.ecuDtcId = in.readString();
        this.status = in.readInt();
        this.snapshotRecordNumber = in.readString();
        this.extDataRecordNumber = in.readString();
        this.statusMask = in.readString();
        this.recordMask = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ecuId);
        dest.writeString(this.ecuDiaId);
        dest.writeString(this.ecuReprogramType);
        dest.writeInt(this.readDtcInfoType);
        dest.writeString(this.ecuDtcInfo);
        dest.writeString(this.ecuDtcId);
        dest.writeInt(this.status);
        dest.writeString(this.snapshotRecordNumber);
        dest.writeString(this.extDataRecordNumber);
        dest.writeString(this.statusMask);
        dest.writeString(this.recordMask);
    }

    public String toString() {
        return "DiagnosticAidlInfo{ecuId=" + this.ecuId + ", ecuDiaId=" + this.ecuDiaId + ", ecuReprogramType=" + this.ecuReprogramType + ", readDtcInfoType=" + this.readDtcInfoType + ", ecuDtcInfo=" + this.ecuDtcInfo + ", ecuDtcId=" + this.ecuDtcId + ", status=" + this.status + ", snapshotRecordNumber=" + this.snapshotRecordNumber + ", extDataRecordNumber=" + this.extDataRecordNumber + ", statusMask=" + this.statusMask + ", recordMask=" + this.recordMask + '}';
    }

    public String getEcuId() {
        return this.ecuId;
    }

    public void setEcuId(String ecuId) {
        this.ecuId = ecuId;
    }

    public String getEcuDiaId() {
        return this.ecuDiaId;
    }

    public void setEcuDiaId(String ecuDiaId) {
        this.ecuDiaId = ecuDiaId;
    }

    public String getEcuReprogramType() {
        return this.ecuReprogramType;
    }

    public void setEcuReprogramType(String ecuReprogramType) {
        this.ecuReprogramType = ecuReprogramType;
    }

    public int getReadDtcInfoType() {
        return this.readDtcInfoType;
    }

    public void setReadDtcInfoType(int readDtcInfoType) {
        this.readDtcInfoType = readDtcInfoType;
    }

    public String getEcuDtcInfo() {
        return this.ecuDtcInfo;
    }

    public void setEcuDtcInfo(String ecuDtcInfo) {
        this.ecuDtcInfo = ecuDtcInfo;
    }

    public String getEcuDtcId() {
        return this.ecuDtcId;
    }

    public void setEcuDtcId(String ecuDtcId) {
        this.ecuDtcId = ecuDtcId;
    }

    public int getEcuDtcCleanStatus() {
        return this.status;
    }

    public void setEcuDtcCleanStatus(int status) {
        this.status = status;
    }

    public String getSnapshotRecordNumber() {
        return this.snapshotRecordNumber;
    }

    public void setSnapshotRecordNumber(String snapshotRecordNumber) {
        this.snapshotRecordNumber = snapshotRecordNumber;
    }

    public String getExtDataRecordNumber() {
        return this.extDataRecordNumber;
    }

    public void setExtDataRecordNumber(String extDataRecordNumber) {
        this.extDataRecordNumber = extDataRecordNumber;
    }

    public String getStatusMask() {
        return this.statusMask;
    }

    public void setStatusMask(String statusMask) {
        this.statusMask = statusMask;
    }

    public String getRecordMask() {
        return this.recordMask;
    }

    public void setRecordMask(String recordMask) {
        this.recordMask = recordMask;
    }
}
