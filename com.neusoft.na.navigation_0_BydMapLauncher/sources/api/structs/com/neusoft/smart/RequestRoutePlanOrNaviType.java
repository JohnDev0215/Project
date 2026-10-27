package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RequestRoutePlanOrNaviType {
    ROUTE_PLANNING(0),
    NAVIGATE_DIRECT(1),
    RETURN_DATA_ONLY(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RequestRoutePlanOrNaviType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RequestRoutePlanOrNaviType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ROUTE_PLANNING;
        }
        if (iIntValue == 1) {
            return NAVIGATE_DIRECT;
        }
        if (iIntValue == 2) {
            return RETURN_DATA_ONLY;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RequestRoutePlanOrNaviType.class.getName());
    }

    public static RequestRoutePlanOrNaviType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "RETURN_DATA_ONLY":
                return RETURN_DATA_ONLY;
            case "ROUTE_PLANNING":
                return ROUTE_PLANNING;
            case "NAVIGATE_DIRECT":
                return NAVIGATE_DIRECT;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RequestRoutePlanOrNaviType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
