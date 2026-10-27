package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MessageType {
    UNKNOWN(0),
    NAVI_STARTUP(100),
    NAVI_EXIT(101),
    NAVI_FOCUS(109),
    ENTER_DASHBOARD_NAVI_MIRROR(900),
    EXIT_DASHBOARD_NAVI_MIRROR(901);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    MessageType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static MessageType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN;
        }
        if (iIntValue == 109) {
            return NAVI_FOCUS;
        }
        if (iIntValue == 100) {
            return NAVI_STARTUP;
        }
        if (iIntValue == 101) {
            return NAVI_EXIT;
        }
        if (iIntValue == 900) {
            return ENTER_DASHBOARD_NAVI_MIRROR;
        }
        if (iIntValue == 901) {
            return EXIT_DASHBOARD_NAVI_MIRROR;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MessageType.class.getName());
    }

    public static MessageType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "NAVI_FOCUS":
                return NAVI_FOCUS;
            case "NAVI_STARTUP":
                return NAVI_STARTUP;
            case "NAVI_EXIT":
                return NAVI_EXIT;
            case "ENTER_DASHBOARD_NAVI_MIRROR":
                return ENTER_DASHBOARD_NAVI_MIRROR;
            case "EXIT_DASHBOARD_NAVI_MIRROR":
                return EXIT_DASHBOARD_NAVI_MIRROR;
            case "UNKNOWN":
                return UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MessageType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
