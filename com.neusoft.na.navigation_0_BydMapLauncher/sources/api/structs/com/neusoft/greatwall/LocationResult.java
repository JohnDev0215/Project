package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum LocationResult {
    SUCCESSFUL(0),
    FAILED(1),
    FAILED_NETWORK(2),
    FAILED_NOT_EXIST(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    LocationResult(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static LocationResult getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SUCCESSFUL;
        }
        if (iIntValue == 1) {
            return FAILED;
        }
        if (iIntValue == 2) {
            return FAILED_NETWORK;
        }
        if (iIntValue == 3) {
            return FAILED_NOT_EXIST;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + LocationResult.class.getName());
    }

    public static LocationResult valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SUCCESSFUL":
                return SUCCESSFUL;
            case "FAILED_NETWORK":
                return FAILED_NETWORK;
            case "FAILED_NOT_EXIST":
                return FAILED_NOT_EXIST;
            case "FAILED":
                return FAILED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + LocationResult.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
