package api.structs.com.neusoft.onecore.pos.sensordata;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum Status {
    UNKNOWN(0),
    NORMAL(1),
    RESET(2),
    FAILURE(3),
    TIMEOUT(4);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    Status(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static Status getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN;
        }
        if (iIntValue == 1) {
            return NORMAL;
        }
        if (iIntValue == 2) {
            return RESET;
        }
        if (iIntValue == 3) {
            return FAILURE;
        }
        if (iIntValue == 4) {
            return TIMEOUT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + Status.class.getName());
    }

    public static Status valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "NORMAL":
                return NORMAL;
            case "TIMEOUT":
                return TIMEOUT;
            case "FAILURE":
                return FAILURE;
            case "RESET":
                return RESET;
            case "UNKNOWN":
                return UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + Status.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
