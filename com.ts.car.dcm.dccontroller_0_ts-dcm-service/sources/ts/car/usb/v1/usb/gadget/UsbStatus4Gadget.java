package ts.car.usb.v1.usb.gadget;

/* JADX INFO: loaded from: classes.dex */
public enum UsbStatus4Gadget {
    SUCCESS(0),
    ERROR(1),
    FUNCTIONS_APPLIED(2),
    FUNCTIONS_NOT_APPLIED(3),
    CONFIGURATION_NOT_SUPPORTED(4);

    private int value;

    UsbStatus4Gadget(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static UsbStatus4Gadget clone(int value) {
        if (value == 0) {
            return SUCCESS;
        }
        if (value == 2) {
            return FUNCTIONS_APPLIED;
        }
        if (value == 3) {
            return FUNCTIONS_NOT_APPLIED;
        }
        if (value == 4) {
            return CONFIGURATION_NOT_SUPPORTED;
        }
        return ERROR;
    }
}
