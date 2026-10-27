package ts.car.usb.v1.usb;

/* JADX INFO: loaded from: classes.dex */
public enum UsbStatus {
    SUCCESS(0),
    ERROR(1),
    INVALID_ARGUMENT(2),
    UNRECOGNIZED_ROLE(3);

    private int value;

    UsbStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static UsbStatus clone(int value) {
        if (value == 0) {
            return SUCCESS;
        }
        if (value == 2) {
            return INVALID_ARGUMENT;
        }
        if (value == 3) {
            return UNRECOGNIZED_ROLE;
        }
        return ERROR;
    }
}
