package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum CheckboxBtnStatus {
    STATUS_UNCHECKED(0),
    STATUS_CHECKED(1),
    STATUS_INDETERMINATE(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    CheckboxBtnStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static CheckboxBtnStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return STATUS_UNCHECKED;
        }
        if (iIntValue == 1) {
            return STATUS_CHECKED;
        }
        if (iIntValue == 2) {
            return STATUS_INDETERMINATE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + CheckboxBtnStatus.class.getName());
    }

    public static CheckboxBtnStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "STATUS_INDETERMINATE":
                return STATUS_INDETERMINATE;
            case "STATUS_UNCHECKED":
                return STATUS_UNCHECKED;
            case "STATUS_CHECKED":
                return STATUS_CHECKED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + CheckboxBtnStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
