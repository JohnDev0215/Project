package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ResultStatus {
    E_SUCCESS(0),
    E_FAILURE(1),
    E_DEFAULT(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ResultStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ResultStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_SUCCESS;
        }
        if (iIntValue == 1) {
            return E_FAILURE;
        }
        if (iIntValue == 2) {
            return E_DEFAULT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ResultStatus.class.getName());
    }

    public static ResultStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "E_DEFAULT":
                return E_DEFAULT;
            case "E_SUCCESS":
                return E_SUCCESS;
            case "E_FAILURE":
                return E_FAILURE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ResultStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
