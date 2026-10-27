package ts.car.usb.v1.ext;

/* JADX INFO: loaded from: classes.dex */
public enum UsbPortPower {
    DISABLE_POWER(0),
    ENABLE_POWER(1);

    private int value;

    UsbPortPower(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static UsbPortPower clone(int value) {
        if (value == 0) {
            return DISABLE_POWER;
        }
        if (value == 1) {
            return ENABLE_POWER;
        }
        return DISABLE_POWER;
    }
}
