package android.hardware.bydauto.ota;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public enum ReadDTCInfoType {
    DTC_INFO_STATUS_MASK(1),
    DTC_INFO_SNAPSHOT_DATA(2),
    DTC_INFO_EXTEND_DATA_RECORDS(3),
    DTC_INFO_ALL(4);

    private int value;

    ReadDTCInfoType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
