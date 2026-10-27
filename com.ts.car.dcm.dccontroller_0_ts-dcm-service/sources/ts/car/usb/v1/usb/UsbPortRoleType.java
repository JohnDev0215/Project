package ts.car.usb.v1.usb;

/* JADX INFO: loaded from: classes.dex */
public enum UsbPortRoleType {
    DATA_ROLE(0),
    POWER_ROLE(1),
    MODE(2);

    private int value;

    UsbPortRoleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static UsbPortRoleType clone(int value) {
        if (value == 1) {
            return POWER_ROLE;
        }
        if (value == 2) {
            return MODE;
        }
        return DATA_ROLE;
    }
}
