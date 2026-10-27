package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum FocusStatus {
    E_UNKNOWN(-1),
    E_FOREGROUND(0),
    E_BACKGROUND(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    FocusStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static FocusStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return E_UNKNOWN;
        }
        if (iIntValue == 0) {
            return E_FOREGROUND;
        }
        if (iIntValue == 1) {
            return E_BACKGROUND;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + FocusStatus.class.getName());
    }

    public static FocusStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "E_UNKNOWN":
                return E_UNKNOWN;
            case "E_FOREGROUND":
                return E_FOREGROUND;
            case "E_BACKGROUND":
                return E_BACKGROUND;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + FocusStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
