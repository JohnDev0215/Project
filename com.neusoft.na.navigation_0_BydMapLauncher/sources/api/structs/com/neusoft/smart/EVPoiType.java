package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum EVPoiType {
    SEARCH_EV_CHARGING_STATION_ONLINE(0),
    SEARCH_EV_CHARGING_STATION_OFFLINE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    EVPoiType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static EVPoiType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SEARCH_EV_CHARGING_STATION_ONLINE;
        }
        if (iIntValue == 1) {
            return SEARCH_EV_CHARGING_STATION_OFFLINE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + EVPoiType.class.getName());
    }

    public static EVPoiType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("SEARCH_EV_CHARGING_STATION_OFFLINE")) {
            return SEARCH_EV_CHARGING_STATION_OFFLINE;
        }
        if (str.equals("SEARCH_EV_CHARGING_STATION_ONLINE")) {
            return SEARCH_EV_CHARGING_STATION_ONLINE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + EVPoiType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
