package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum PoiDetailType {
    SEARCH_TYPE_DEFAULT(0),
    SEARCH_TYPE_KEYWORD(1),
    SEARCH_TYPE_AROUND(2),
    SEARCH_TYPE_ALONG_ROUTE(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    PoiDetailType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static PoiDetailType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SEARCH_TYPE_DEFAULT;
        }
        if (iIntValue == 1) {
            return SEARCH_TYPE_KEYWORD;
        }
        if (iIntValue == 2) {
            return SEARCH_TYPE_AROUND;
        }
        if (iIntValue == 3) {
            return SEARCH_TYPE_ALONG_ROUTE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + PoiDetailType.class.getName());
    }

    public static PoiDetailType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SEARCH_TYPE_DEFAULT":
                return SEARCH_TYPE_DEFAULT;
            case "SEARCH_TYPE_AROUND":
                return SEARCH_TYPE_AROUND;
            case "SEARCH_TYPE_ALONG_ROUTE":
                return SEARCH_TYPE_ALONG_ROUTE;
            case "SEARCH_TYPE_KEYWORD":
                return SEARCH_TYPE_KEYWORD;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + PoiDetailType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
