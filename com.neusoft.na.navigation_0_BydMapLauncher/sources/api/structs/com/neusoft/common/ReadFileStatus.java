package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ReadFileStatus {
    E_SUCCESS(0),
    E_FILE_NOT_EXIST(1),
    E_ACCESS_DENIED(2),
    E_UNKNOWN(99);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ReadFileStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ReadFileStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_SUCCESS;
        }
        if (iIntValue == 1) {
            return E_FILE_NOT_EXIST;
        }
        if (iIntValue == 2) {
            return E_ACCESS_DENIED;
        }
        if (iIntValue == 99) {
            return E_UNKNOWN;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ReadFileStatus.class.getName());
    }

    public static ReadFileStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "E_UNKNOWN":
                return E_UNKNOWN;
            case "E_FILE_NOT_EXIST":
                return E_FILE_NOT_EXIST;
            case "E_SUCCESS":
                return E_SUCCESS;
            case "E_ACCESS_DENIED":
                return E_ACCESS_DENIED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ReadFileStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
