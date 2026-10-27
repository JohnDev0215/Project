package api.structs.com.neusoft.types.common.weathertypes;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SpeedUnit {
    SU_UNKNOWN(0),
    SU_KMH(1),
    SU_MPH(2),
    SU_MS(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SpeedUnit(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SpeedUnit getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SU_UNKNOWN;
        }
        if (iIntValue == 1) {
            return SU_KMH;
        }
        if (iIntValue == 2) {
            return SU_MPH;
        }
        if (iIntValue == 3) {
            return SU_MS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SpeedUnit.class.getName());
    }

    public static SpeedUnit valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SU_KMH":
                return SU_KMH;
            case "SU_MPH":
                return SU_MPH;
            case "SU_MS":
                return SU_MS;
            case "SU_UNKNOWN":
                return SU_UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SpeedUnit.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
