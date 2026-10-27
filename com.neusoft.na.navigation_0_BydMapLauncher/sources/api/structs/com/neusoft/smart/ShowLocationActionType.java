package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ShowLocationActionType {
    SHOW_LOCATION_MAP_AND_GET_LOCATION_INFO(0),
    GET_LOCATION_INFO_ONLY(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ShowLocationActionType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ShowLocationActionType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SHOW_LOCATION_MAP_AND_GET_LOCATION_INFO;
        }
        if (iIntValue == 1) {
            return GET_LOCATION_INFO_ONLY;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ShowLocationActionType.class.getName());
    }

    public static ShowLocationActionType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("GET_LOCATION_INFO_ONLY")) {
            return GET_LOCATION_INFO_ONLY;
        }
        if (str.equals("SHOW_LOCATION_MAP_AND_GET_LOCATION_INFO")) {
            return SHOW_LOCATION_MAP_AND_GET_LOCATION_INFO;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ShowLocationActionType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
