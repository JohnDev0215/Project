package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DayNightInvalidStatus {
    DAYNIGHT_STATUS_DAY(0),
    DAYNIGHT_STATUS_NIGHT(1),
    DAYNIGHT_STATUS_INVALID(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DayNightInvalidStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DayNightInvalidStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DAYNIGHT_STATUS_DAY;
        }
        if (iIntValue == 1) {
            return DAYNIGHT_STATUS_NIGHT;
        }
        if (iIntValue == 2) {
            return DAYNIGHT_STATUS_INVALID;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DayNightInvalidStatus.class.getName());
    }

    public static DayNightInvalidStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DAYNIGHT_STATUS_DAY":
                return DAYNIGHT_STATUS_DAY;
            case "DAYNIGHT_STATUS_INVALID":
                return DAYNIGHT_STATUS_INVALID;
            case "DAYNIGHT_STATUS_NIGHT":
                return DAYNIGHT_STATUS_NIGHT;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DayNightInvalidStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
