package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum AccessCodingStatus {
    E_SUCCESS(0),
    E_USB_NOT_INSERTED(1),
    E_ACTIVATION_CODE_MISMATCH(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    AccessCodingStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static AccessCodingStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_SUCCESS;
        }
        if (iIntValue == 1) {
            return E_USB_NOT_INSERTED;
        }
        if (iIntValue == 2) {
            return E_ACTIVATION_CODE_MISMATCH;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + AccessCodingStatus.class.getName());
    }

    public static AccessCodingStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "E_ACTIVATION_CODE_MISMATCH":
                return E_ACTIVATION_CODE_MISMATCH;
            case "E_SUCCESS":
                return E_SUCCESS;
            case "E_USB_NOT_INSERTED":
                return E_USB_NOT_INSERTED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + AccessCodingStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
