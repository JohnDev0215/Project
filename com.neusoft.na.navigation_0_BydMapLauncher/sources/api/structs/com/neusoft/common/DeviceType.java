package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DeviceType {
    VIN_CODE(0),
    ECU_ID(1),
    IHUD_ID(2),
    UUID(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DeviceType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DeviceType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return VIN_CODE;
        }
        if (iIntValue == 1) {
            return ECU_ID;
        }
        if (iIntValue == 2) {
            return IHUD_ID;
        }
        if (iIntValue == 3) {
            return UUID;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DeviceType.class.getName());
    }

    public static DeviceType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "VIN_CODE":
                return VIN_CODE;
            case "IHUD_ID":
                return IHUD_ID;
            case "UUID":
                return UUID;
            case "ECU_ID":
                return ECU_ID;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DeviceType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
