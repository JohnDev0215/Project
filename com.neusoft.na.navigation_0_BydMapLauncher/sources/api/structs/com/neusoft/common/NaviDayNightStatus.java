package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NaviDayNightStatus {
    DAYNIGHT_STATUS_DAY(0),
    DAYNIGHT_STATUS_NIGHT(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NaviDayNightStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NaviDayNightStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DAYNIGHT_STATUS_DAY;
        }
        if (iIntValue == 1) {
            return DAYNIGHT_STATUS_NIGHT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NaviDayNightStatus.class.getName());
    }

    public static NaviDayNightStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("DAYNIGHT_STATUS_DAY")) {
            return DAYNIGHT_STATUS_DAY;
        }
        if (str.equals("DAYNIGHT_STATUS_NIGHT")) {
            return DAYNIGHT_STATUS_NIGHT;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NaviDayNightStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
