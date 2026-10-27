package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ServiceProvider {
    UNKNOWN(0),
    NAIS_CLOUD(1),
    NEUSOFT_CLOUD(2),
    BIGDATA_CLOUD(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ServiceProvider(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ServiceProvider getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN;
        }
        if (iIntValue == 1) {
            return NAIS_CLOUD;
        }
        if (iIntValue == 2) {
            return NEUSOFT_CLOUD;
        }
        if (iIntValue == 3) {
            return BIGDATA_CLOUD;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ServiceProvider.class.getName());
    }

    public static ServiceProvider valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "NEUSOFT_CLOUD":
                return NEUSOFT_CLOUD;
            case "BIGDATA_CLOUD":
                return BIGDATA_CLOUD;
            case "UNKNOWN":
                return UNKNOWN;
            case "NAIS_CLOUD":
                return NAIS_CLOUD;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ServiceProvider.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
