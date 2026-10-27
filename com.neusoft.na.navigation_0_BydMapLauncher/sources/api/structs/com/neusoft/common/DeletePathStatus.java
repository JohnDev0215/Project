package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DeletePathStatus {
    E_SUCCESS(0),
    E_ACCESS_DENIED(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DeletePathStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DeletePathStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_SUCCESS;
        }
        if (iIntValue == 1) {
            return E_ACCESS_DENIED;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DeletePathStatus.class.getName());
    }

    public static DeletePathStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("E_SUCCESS")) {
            return E_SUCCESS;
        }
        if (str.equals("E_ACCESS_DENIED")) {
            return E_ACCESS_DENIED;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DeletePathStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
