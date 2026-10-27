package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SwitchStatus {
    UNKNOWN(-1),
    SWITCH_OFF(0),
    SWITCH_ON(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SwitchStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SwitchStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return UNKNOWN;
        }
        if (iIntValue == 0) {
            return SWITCH_OFF;
        }
        if (iIntValue == 1) {
            return SWITCH_ON;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SwitchStatus.class.getName());
    }

    public static SwitchStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SWITCH_OFF":
                return SWITCH_OFF;
            case "SWITCH_ON":
                return SWITCH_ON;
            case "UNKNOWN":
                return UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SwitchStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
