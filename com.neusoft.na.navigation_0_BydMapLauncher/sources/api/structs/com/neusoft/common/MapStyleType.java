package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MapStyleType {
    NIGHT(0),
    DAY(1),
    AUTO(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    MapStyleType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static MapStyleType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return NIGHT;
        }
        if (iIntValue == 1) {
            return DAY;
        }
        if (iIntValue == 2) {
            return AUTO;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MapStyleType.class.getName());
    }

    public static MapStyleType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DAY":
                return DAY;
            case "AUTO":
                return AUTO;
            case "NIGHT":
                return NIGHT;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MapStyleType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
