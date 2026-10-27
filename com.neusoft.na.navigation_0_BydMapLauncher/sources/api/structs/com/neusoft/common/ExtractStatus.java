package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ExtractStatus {
    E_UNKNOWN(-1),
    E_SUCCESS(0),
    E_FILE_NOT_EXIST(1),
    E_ACCESS_DENIED(2),
    E_EXTRACT_FAILED(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ExtractStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ExtractStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return E_UNKNOWN;
        }
        if (iIntValue == 0) {
            return E_SUCCESS;
        }
        if (iIntValue == 1) {
            return E_FILE_NOT_EXIST;
        }
        if (iIntValue == 2) {
            return E_ACCESS_DENIED;
        }
        if (iIntValue == 3) {
            return E_EXTRACT_FAILED;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ExtractStatus.class.getName());
    }

    public static ExtractStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "E_UNKNOWN":
                return E_UNKNOWN;
            case "E_FILE_NOT_EXIST":
                return E_FILE_NOT_EXIST;
            case "E_SUCCESS":
                return E_SUCCESS;
            case "E_EXTRACT_FAILED":
                return E_EXTRACT_FAILED;
            case "E_ACCESS_DENIED":
                return E_ACCESS_DENIED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ExtractStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
