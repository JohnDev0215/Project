package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ExpiredDateStatus {
    E_FAILURE(0),
    E_SUCCESS(1),
    E_UNKNOWN(99);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ExpiredDateStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ExpiredDateStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_FAILURE;
        }
        if (iIntValue == 1) {
            return E_SUCCESS;
        }
        if (iIntValue == 99) {
            return E_UNKNOWN;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ExpiredDateStatus.class.getName());
    }

    public static ExpiredDateStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "E_UNKNOWN":
                return E_UNKNOWN;
            case "E_SUCCESS":
                return E_SUCCESS;
            case "E_FAILURE":
                return E_FAILURE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ExpiredDateStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
