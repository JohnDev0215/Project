package api.structs.com.neusoft.onecore.pos.sensordata;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MapMatchStatus {
    INVALID(0),
    OFFROAD(1),
    ONROAD(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    MapMatchStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static MapMatchStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return INVALID;
        }
        if (iIntValue == 1) {
            return OFFROAD;
        }
        if (iIntValue == 2) {
            return ONROAD;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MapMatchStatus.class.getName());
    }

    public static MapMatchStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "ONROAD":
                return ONROAD;
            case "INVALID":
                return INVALID;
            case "OFFROAD":
                return OFFROAD;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MapMatchStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
