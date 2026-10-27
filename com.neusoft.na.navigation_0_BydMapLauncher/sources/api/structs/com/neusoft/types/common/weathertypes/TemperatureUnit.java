package api.structs.com.neusoft.types.common.weathertypes;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum TemperatureUnit {
    TU_UNKNOWN(0),
    TU_KELVIN(1),
    TU_CELSIUS(2),
    TU_FAHRENHEIT(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    TemperatureUnit(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static TemperatureUnit getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return TU_UNKNOWN;
        }
        if (iIntValue == 1) {
            return TU_KELVIN;
        }
        if (iIntValue == 2) {
            return TU_CELSIUS;
        }
        if (iIntValue == 3) {
            return TU_FAHRENHEIT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + TemperatureUnit.class.getName());
    }

    public static TemperatureUnit valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "TU_UNKNOWN":
                return TU_UNKNOWN;
            case "TU_FAHRENHEIT":
                return TU_FAHRENHEIT;
            case "TU_CELSIUS":
                return TU_CELSIUS;
            case "TU_KELVIN":
                return TU_KELVIN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + TemperatureUnit.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
