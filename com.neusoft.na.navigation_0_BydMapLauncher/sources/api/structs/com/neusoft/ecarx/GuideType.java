package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum GuideType {
    GPS_NAVIGATION(0),
    SIMULATE_NAVIGATION(1),
    CRUISE_NAVIGATION(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    GuideType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static GuideType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return GPS_NAVIGATION;
        }
        if (iIntValue == 1) {
            return SIMULATE_NAVIGATION;
        }
        if (iIntValue == 2) {
            return CRUISE_NAVIGATION;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + GuideType.class.getName());
    }

    public static GuideType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "CRUISE_NAVIGATION":
                return CRUISE_NAVIGATION;
            case "SIMULATE_NAVIGATION":
                return SIMULATE_NAVIGATION;
            case "GPS_NAVIGATION":
                return GPS_NAVIGATION;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + GuideType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
