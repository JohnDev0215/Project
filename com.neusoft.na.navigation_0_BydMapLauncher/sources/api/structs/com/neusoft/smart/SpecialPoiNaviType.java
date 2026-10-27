package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SpecialPoiNaviType {
    NAVIGATE_DIRECT(0),
    ROUTE_PLANNING(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SpecialPoiNaviType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SpecialPoiNaviType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return NAVIGATE_DIRECT;
        }
        if (iIntValue == 1) {
            return ROUTE_PLANNING;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SpecialPoiNaviType.class.getName());
    }

    public static SpecialPoiNaviType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("ROUTE_PLANNING")) {
            return ROUTE_PLANNING;
        }
        if (str.equals("NAVIGATE_DIRECT")) {
            return NAVIGATE_DIRECT;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SpecialPoiNaviType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
