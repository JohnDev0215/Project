package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SearchType {
    KEY_WORD_SEARCH(0),
    TOILET(1),
    ATM(2),
    REPAIR_STATION(3),
    GAS_STATION(4),
    CHARGING_STATION(5),
    HOTEL(6),
    KEY_WORD_ALONG_ROUTE_SEARCH(7);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SearchType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SearchType getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return KEY_WORD_SEARCH;
            case 1:
                return TOILET;
            case 2:
                return ATM;
            case 3:
                return REPAIR_STATION;
            case 4:
                return GAS_STATION;
            case 5:
                return CHARGING_STATION;
            case 6:
                return HOTEL;
            case 7:
                return KEY_WORD_ALONG_ROUTE_SEARCH;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SearchType.class.getName());
        }
    }

    public static SearchType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "TOILET":
                return TOILET;
            case "GAS_STATION":
                return GAS_STATION;
            case "KEY_WORD_ALONG_ROUTE_SEARCH":
                return KEY_WORD_ALONG_ROUTE_SEARCH;
            case "ATM":
                return ATM;
            case "HOTEL":
                return HOTEL;
            case "KEY_WORD_SEARCH":
                return KEY_WORD_SEARCH;
            case "REPAIR_STATION":
                return REPAIR_STATION;
            case "CHARGING_STATION":
                return CHARGING_STATION;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SearchType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
