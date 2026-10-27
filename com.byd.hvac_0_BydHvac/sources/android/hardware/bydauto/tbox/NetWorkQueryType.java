package android.hardware.bydauto.tbox;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public enum NetWorkQueryType {
    SUCCESS(0),
    FAILED(1),
    RESERVE(2);

    private int value;

    NetWorkQueryType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
