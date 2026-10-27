package api.structs.com.neusoft.types.common.weathertypes;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum AirPressureUnit {
    APU_UNKNOWN(0),
    APU_PA(1),
    APU_KPA(2),
    APU_MPA(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    AirPressureUnit(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static AirPressureUnit getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return APU_UNKNOWN;
        }
        if (iIntValue == 1) {
            return APU_PA;
        }
        if (iIntValue == 2) {
            return APU_KPA;
        }
        if (iIntValue == 3) {
            return APU_MPA;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + AirPressureUnit.class.getName());
    }

    public static AirPressureUnit valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "APU_KPA":
                return APU_KPA;
            case "APU_MPA":
                return APU_MPA;
            case "APU_UNKNOWN":
                return APU_UNKNOWN;
            case "APU_PA":
                return APU_PA;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + AirPressureUnit.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
