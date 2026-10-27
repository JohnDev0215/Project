package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum TopTipUnit {
    UNIT_INVALID(0),
    UNIT_KM(1),
    UNIT_MILES(2),
    UNIT_M(3),
    UNIT_YARDS(4),
    UNIT_FT(5);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    TopTipUnit(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static TopTipUnit getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNIT_INVALID;
        }
        if (iIntValue == 1) {
            return UNIT_KM;
        }
        if (iIntValue == 2) {
            return UNIT_MILES;
        }
        if (iIntValue == 3) {
            return UNIT_M;
        }
        if (iIntValue == 4) {
            return UNIT_YARDS;
        }
        if (iIntValue == 5) {
            return UNIT_FT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + TopTipUnit.class.getName());
    }

    public static TopTipUnit valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "UNIT_M":
                return UNIT_M;
            case "UNIT_MILES":
                return UNIT_MILES;
            case "UNIT_YARDS":
                return UNIT_YARDS;
            case "UNIT_FT":
                return UNIT_FT;
            case "UNIT_KM":
                return UNIT_KM;
            case "UNIT_INVALID":
                return UNIT_INVALID;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + TopTipUnit.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
