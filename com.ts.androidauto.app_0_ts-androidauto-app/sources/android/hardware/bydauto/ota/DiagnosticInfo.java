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

    public DiagnosticInfo(String ecuId, String ecuDiaId, String ecuReprogramType, ReadDTCInfoType readDtcInfoType, String snapshotRecordNumber, String extDataRecordNumber, String statusMask, String recordMask) {
        this.ecuId = ecuId;
        this.ecuDiaId = ecuDiaId;
        this.ecuReprogramType = ecuReprogramType;
        this.readDtcInfoType = readDtcInfoType;
        this.snapshotRecordNumber = snapshotRecordNumber;
        this.extDataRecordNumber = extDataRecordNumber;
        this.statusMask = statusMask;
        this.recordMask = recordMask;
    }

    public DiagnosticInfo(String ecuId, String ecuDiaId, String ecuReprogramType, String ecuDtcId) {
        this.ecuId = ecuId;
        this.ecuDiaId = ecuDiaId;
        this.ecuReprogramType = ecuReprogramType;
        this.ecuDtcId = ecuDtcId;
    }

    public DiagnosticInfo() {
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

    public ReadDTCInfoType getReadDtcInfoType() {
        return this.readDtcInfoType;
    }

    public void setReadDtcInfoType(ReadDTCInfoType readDtcInfoType) {
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
