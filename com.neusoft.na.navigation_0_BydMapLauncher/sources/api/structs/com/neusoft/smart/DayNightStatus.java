package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DayNightStatus {
    DAYNIGHT_STATUS_DAY(30),
    DAYNIGHT_STATUS_NIGHT(32);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DayNightStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DayNightStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 30) {
            return DAYNIGHT_STATUS_DAY;
        }
        if (iIntValue == 32) {
            return DAYNIGHT_STATUS_NIGHT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DayNightStatus.class.getName());
    }

    public static DayNightStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("DAYNIGHT_STATUS_DAY")) {
            return DAYNIGHT_STATUS_DAY;
        }
        if (str.equals("DAYNIGHT_STATUS_NIGHT")) {
            return DAYNIGHT_STATUS_NIGHT;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DayNightStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
