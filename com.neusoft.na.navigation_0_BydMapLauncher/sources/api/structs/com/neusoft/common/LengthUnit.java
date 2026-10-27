package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum LengthUnit {
    INVALID(0),
    METRIC_UNIT(1),
    IMPERIAL_UNIT(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    LengthUnit(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static LengthUnit getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return INVALID;
        }
        if (iIntValue == 1) {
            return METRIC_UNIT;
        }
        if (iIntValue == 2) {
            return IMPERIAL_UNIT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + LengthUnit.class.getName());
    }

    public static LengthUnit valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "INVALID":
                return INVALID;
            case "IMPERIAL_UNIT":
                return IMPERIAL_UNIT;
            case "METRIC_UNIT":
                return METRIC_UNIT;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + LengthUnit.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
