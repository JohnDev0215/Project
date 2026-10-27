package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum WifiSecurityType {
    WifiSecurityUnknown(-1),
    WifiSecurityNone(0),
    WifiSecurityWep(1),
    WifiSecurityWpa(2),
    WifiSecurityWpa3PersonTransitionMode(3),
    WifiSecurityWpa3PersonalOnly(4);

    private int mType;

    WifiSecurityType(int type) {
        this.mType = type;
    }

    public int getSecurityType() {
        return this.mType;
    }

    public static WifiSecurityType fromTypeValue(int type) {
        for (WifiSecurityType value : values()) {
            if (value.getSecurityType() == type) {
                return value;
            }
        }
        return WifiSecurityUnknown;
    }
}
