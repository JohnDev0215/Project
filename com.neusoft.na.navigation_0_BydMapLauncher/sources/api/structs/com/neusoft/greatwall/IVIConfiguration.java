package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum IVIConfiguration {
    UNKNOWN(0),
    HIGH_LEVEL(1),
    LOW_LEVEL(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    IVIConfiguration(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static IVIConfiguration getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN;
        }
        if (iIntValue == 1) {
            return HIGH_LEVEL;
        }
        if (iIntValue == 2) {
            return LOW_LEVEL;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + IVIConfiguration.class.getName());
    }

    public static IVIConfiguration valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "LOW_LEVEL":
                return LOW_LEVEL;
            case "UNKNOWN":
                return UNKNOWN;
            case "HIGH_LEVEL":
                return HIGH_LEVEL;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + IVIConfiguration.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
