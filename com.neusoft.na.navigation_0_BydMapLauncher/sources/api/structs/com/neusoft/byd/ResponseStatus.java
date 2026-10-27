package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ResponseStatus {
    E_SUCCESS(0),
    E_FAILURE(1),
    E_UNKNOWN(99);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ResponseStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ResponseStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_SUCCESS;
        }
        if (iIntValue == 1) {
            return E_FAILURE;
        }
        if (iIntValue == 99) {
            return E_UNKNOWN;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ResponseStatus.class.getName());
    }

    public static ResponseStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "E_UNKNOWN":
                return E_UNKNOWN;
            case "E_SUCCESS":
                return E_SUCCESS;
            case "E_FAILURE":
                return E_FAILURE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ResponseStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
