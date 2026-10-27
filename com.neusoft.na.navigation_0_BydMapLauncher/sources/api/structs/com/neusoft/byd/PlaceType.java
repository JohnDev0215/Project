package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum PlaceType {
    UNKNOWN(0),
    POI(1),
    ADDRESS(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    PlaceType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static PlaceType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN;
        }
        if (iIntValue == 1) {
            return POI;
        }
        if (iIntValue == 2) {
            return ADDRESS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + PlaceType.class.getName());
    }

    public static PlaceType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "ADDRESS":
                return ADDRESS;
            case "POI":
                return POI;
            case "UNKNOWN":
                return UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + PlaceType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
