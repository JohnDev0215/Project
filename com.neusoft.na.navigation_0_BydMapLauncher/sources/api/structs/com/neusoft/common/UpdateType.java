package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum UpdateType {
    UNKNOWN(0),
    FULL(1),
    INCREMENTAL(2),
    PARTIAL(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    UpdateType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static UpdateType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN;
        }
        if (iIntValue == 1) {
            return FULL;
        }
        if (iIntValue == 2) {
            return INCREMENTAL;
        }
        if (iIntValue == 3) {
            return PARTIAL;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + UpdateType.class.getName());
    }

    public static UpdateType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "INCREMENTAL":
                return INCREMENTAL;
            case "PARTIAL":
                return PARTIAL;
            case "FULL":
                return FULL;
            case "UNKNOWN":
                return UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + UpdateType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
