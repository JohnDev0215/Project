package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum PowerProvidingCapability {
    Unknown(-1),
    None(0),
    LightningReceptaclePassthrough(1),
    Advanced(2);

    private int mPower;

    PowerProvidingCapability(int power) {
        this.mPower = power;
    }

    public int getValue() {
        return this.mPower;
    }

    public static PowerProvidingCapability fromTypeValue(int power) {
        for (PowerProvidingCapability value : values()) {
            if (value.getValue() == power) {
                return value;
            }
        }
        return Unknown;
    }
}
