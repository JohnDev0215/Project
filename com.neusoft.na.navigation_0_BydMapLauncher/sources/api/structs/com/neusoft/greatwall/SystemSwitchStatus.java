package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SystemSwitchStatus {
    SWITCH_ON(0),
    SWITCH_OFF(1),
    SWITCH_NONE(99);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SystemSwitchStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SystemSwitchStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SWITCH_ON;
        }
        if (iIntValue == 1) {
            return SWITCH_OFF;
        }
        if (iIntValue == 99) {
            return SWITCH_NONE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SystemSwitchStatus.class.getName());
    }

    public static SystemSwitchStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SWITCH_NONE":
                return SWITCH_NONE;
            case "SWITCH_OFF":
                return SWITCH_OFF;
            case "SWITCH_ON":
                return SWITCH_ON;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SystemSwitchStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
