package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DayNightModeStatus {
    DAYNIGHT_MODE_STATUS_AUTO(0),
    DAYNIGHT_MODE_STATUS_DAY(1),
    DAYNIGHT_MODE_STATUS_NIGHT(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DayNightModeStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DayNightModeStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DAYNIGHT_MODE_STATUS_AUTO;
        }
        if (iIntValue == 1) {
            return DAYNIGHT_MODE_STATUS_DAY;
        }
        if (iIntValue == 2) {
            return DAYNIGHT_MODE_STATUS_NIGHT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DayNightModeStatus.class.getName());
    }

    public static DayNightModeStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DAYNIGHT_MODE_STATUS_DAY":
                return DAYNIGHT_MODE_STATUS_DAY;
            case "DAYNIGHT_MODE_STATUS_AUTO":
                return DAYNIGHT_MODE_STATUS_AUTO;
            case "DAYNIGHT_MODE_STATUS_NIGHT":
                return DAYNIGHT_MODE_STATUS_NIGHT;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DayNightModeStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
