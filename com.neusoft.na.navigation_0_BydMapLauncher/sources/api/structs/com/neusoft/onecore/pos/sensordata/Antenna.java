package api.structs.com.neusoft.onecore.pos.sensordata;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum Antenna {
    UNKNOWN(0),
    OK(1),
    OPEN(2),
    SHORT_CIRCUIT(3),
    OFF(4);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    Antenna(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static Antenna getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN;
        }
        if (iIntValue == 1) {
            return OK;
        }
        if (iIntValue == 2) {
            return OPEN;
        }
        if (iIntValue == 3) {
            return SHORT_CIRCUIT;
        }
        if (iIntValue == 4) {
            return OFF;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + Antenna.class.getName());
    }

    public static Antenna valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "OK":
                return OK;
            case "OFF":
                return OFF;
            case "OPEN":
                return OPEN;
            case "UNKNOWN":
                return UNKNOWN;
            case "SHORT_CIRCUIT":
                return SHORT_CIRCUIT;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + Antenna.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
