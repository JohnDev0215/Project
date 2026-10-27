package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SearchEVType {
    SEARCH_EV_CHARGING_STATION_DEFAULT(0),
    SEARCH_EV_CHARGING_STATION_AC(1),
    SEARCH_EV_CHARGING_STATION_DC(2),
    SEARCH_EV_CHARGING_STATION_FAST(3),
    SEARCH_EV_CHARGING_STATION_NORMAL(4);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SearchEVType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SearchEVType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SEARCH_EV_CHARGING_STATION_DEFAULT;
        }
        if (iIntValue == 1) {
            return SEARCH_EV_CHARGING_STATION_AC;
        }
        if (iIntValue == 2) {
            return SEARCH_EV_CHARGING_STATION_DC;
        }
        if (iIntValue == 3) {
            return SEARCH_EV_CHARGING_STATION_FAST;
        }
        if (iIntValue == 4) {
            return SEARCH_EV_CHARGING_STATION_NORMAL;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SearchEVType.class.getName());
    }

    public static SearchEVType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SEARCH_EV_CHARGING_STATION_DEFAULT":
                return SEARCH_EV_CHARGING_STATION_DEFAULT;
            case "SEARCH_EV_CHARGING_STATION_NORMAL":
                return SEARCH_EV_CHARGING_STATION_NORMAL;
            case "SEARCH_EV_CHARGING_STATION_AC":
                return SEARCH_EV_CHARGING_STATION_AC;
            case "SEARCH_EV_CHARGING_STATION_DC":
                return SEARCH_EV_CHARGING_STATION_DC;
            case "SEARCH_EV_CHARGING_STATION_FAST":
                return SEARCH_EV_CHARGING_STATION_FAST;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SearchEVType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
