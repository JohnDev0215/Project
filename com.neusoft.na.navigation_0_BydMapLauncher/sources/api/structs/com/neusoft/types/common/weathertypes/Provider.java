package api.structs.com.neusoft.types.common.weathertypes;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum Provider {
    NONE(0),
    OPEN_WEATHER_MAP(1),
    FORECA(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    Provider(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static Provider getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return NONE;
        }
        if (iIntValue == 1) {
            return OPEN_WEATHER_MAP;
        }
        if (iIntValue == 2) {
            return FORECA;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + Provider.class.getName());
    }

    public static Provider valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "OPEN_WEATHER_MAP":
                return OPEN_WEATHER_MAP;
            case "NONE":
                return NONE;
            case "FORECA":
                return FORECA;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + Provider.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
