package ts.car.usb.v1.usb;

/* JADX INFO: loaded from: classes.dex */
public enum UsbPortMode {
    NONE(0),
    UFP(1),
    DFP(2),
    DRP(3),
    NUM_MODES(4);

    private int value;

    UsbPortMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static UsbPortMode clone(int value) {
        if (value == 1) {
            return UFP;
        }
        if (value == 2) {
            return DFP;
        }
        if (value == 3) {
            return DRP;
        }
        if (value == 4) {
            return NUM_MODES;
        }
        return NONE;
    }
}
