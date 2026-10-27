package android.hardware.bydauto.tbox;

/* JADX INFO: loaded from: classes.dex */
public enum NetWorkQueryType {
    SUCCESS(0),
    FAILED(1),
    RESERVE(2);

    private int value;

    NetWorkQueryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
