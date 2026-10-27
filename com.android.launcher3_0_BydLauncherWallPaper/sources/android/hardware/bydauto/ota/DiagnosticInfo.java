package android.hardware.bydauto.ota;

/* JADX INFO: loaded from: classes.dex */
public class DiagnosticInfo {
    public String ecuDiaId;
    public String ecuDtcId;
    public String ecuDtcInfo;
    public String ecuId;
    public String ecuReprogramType;
    public String extDataRecordNumber;
    public ReadDTCInfoType readDtcInfoType;
    public String recordMask;
    public String snapshotRecordNumber;
    public String statusMask;

    public DiagnosticInfo(String str, String str2, String str3, ReadDTCInfoType readDTCInfoType, String str4, String str5, String str6, String str7) {
        this.ecuId = str;
        this.ecuDiaId = str2;
        this.ecuReprogramType = str3;
        this.readDtcInfoType = readDTCInfoType;
        this.snapshotRecordNumber = str4;
        this.extDataRecordNumber = str5;
        this.statusMask = str6;
        this.recordMask = str7;
    }

    public DiagnosticInfo(String str, String str2, String str3, String str4) {
        this.ecuId = str;
        this.ecuDiaId = str2;
        this.ecuReprogramType = str3;
        this.ecuDtcId = str4;
    }

    public DiagnosticInfo() {
    }

    public String getEcuId() {
        return this.ecuId;
    }

    public void setEcuId(String str) {
        this.ecuId = str;
    }

    public String getEcuDiaId() {
        return this.ecuDiaId;
    }

    public void setEcuDiaId(String str) {
        this.ecuDiaId = str;
    }

    public String getEcuReprogramType() {
        return this.ecuReprogramType;
    }

    public void setEcuReprogramType(String str) {
        this.ecuReprogramType = str;
    }

    public ReadDTCInfoType getReadDtcInfoType() {
        return this.readDtcInfoType;
    }

    public void setReadDtcInfoType(ReadDTCInfoType readDTCInfoType) {
        this.readDtcInfoType = readDTCInfoType;
    }

    public String getEcuDtcInfo() {
        return this.ecuDtcInfo;
    }

    public void setEcuDtcInfo(String str) {
        this.ecuDtcInfo = str;
    }

    public String getEcuDtcId() {
        return this.ecuDtcId;
    }

    public void setEcuDtcId(String str) {
        this.ecuDtcId = str;
    }

    public String getSnapshotRecordNumber() {
        return this.snapshotRecordNumber;
    }

    public void setSnapshotRecordNumber(String str) {
        this.snapshotRecordNumber = str;
    }

    public String getExtDataRecordNumber() {
        return this.extDataRecordNumber;
    }

    public void setExtDataRecordNumber(String str) {
        this.extDataRecordNumber = str;
    }

    public String getStatusMask() {
        return this.statusMask;
    }

    public void setStatusMask(String str) {
        this.statusMask = str;
    }

    public String getRecordMask() {
        return this.recordMask;
    }

    public void setRecordMask(String str) {
        this.recordMask = str;
    }
}
