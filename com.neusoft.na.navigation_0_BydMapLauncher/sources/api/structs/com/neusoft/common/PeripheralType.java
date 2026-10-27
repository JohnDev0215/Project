package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum PeripheralType {
    DEVICE_BLUETOOTH(0),
    DEVICE_WIFI(1),
    DEVICE_MOBILE(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    PeripheralType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static PeripheralType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DEVICE_BLUETOOTH;
        }
        if (iIntValue == 1) {
            return DEVICE_WIFI;
        }
        if (iIntValue == 2) {
            return DEVICE_MOBILE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + PeripheralType.class.getName());
    }

    public static PeripheralType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DEVICE_BLUETOOTH":
                return DEVICE_BLUETOOTH;
            case "DEVICE_MOBILE":
                return DEVICE_MOBILE;
            case "DEVICE_WIFI":
                return DEVICE_WIFI;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + PeripheralType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
