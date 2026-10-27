package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ActivateStatus {
    E_SUCCESS(0),
    E_FAILURE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ActivateStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ActivateStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_SUCCESS;
        }
        if (iIntValue == 1) {
            return E_FAILURE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ActivateStatus.class.getName());
    }

    public static ActivateStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("E_SUCCESS")) {
            return E_SUCCESS;
        }
        if (str.equals("E_FAILURE")) {
            return E_FAILURE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ActivateStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
