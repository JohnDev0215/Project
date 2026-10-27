package ts.car.usb.v1.ext;

/* JADX INFO: loaded from: classes.dex */
public enum UsbUevent {
    CONNECT(0),
    DISCONNECT(1);

    private int value;

    UsbUevent(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static UsbUevent clone(int value) {
        if (value == 0) {
            return CONNECT;
        }
        return DISCONNECT;
    }
}
