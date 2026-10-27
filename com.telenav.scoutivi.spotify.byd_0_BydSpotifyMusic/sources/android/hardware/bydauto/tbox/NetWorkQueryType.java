package android.hardware.bydauto.tbox;

/* JADX INFO: loaded from: classes.dex */
public enum NetWorkQueryType {
    SUCCESS(0),
    FAILED(1),
    RESERVE(2);

    private int value;

    NetWorkQueryType(int i7) {
        this.value = i7;
    }

    public int getValue() {
        return this.value;
    }
}
