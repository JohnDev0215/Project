package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NaviStatus {
    GUIDE_START(16),
    CRUISE_START(28),
    SIMULATE_GUIDE_START(33),
    GUIDE_STOP(17),
    CRUISE_STOP(29),
    SIMULATE_GUIDE_STOP(35);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NaviStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NaviStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 16) {
            return GUIDE_START;
        }
        if (iIntValue == 17) {
            return GUIDE_STOP;
        }
        if (iIntValue == 28) {
            return CRUISE_START;
        }
        if (iIntValue == 29) {
            return CRUISE_STOP;
        }
        if (iIntValue == 33) {
            return SIMULATE_GUIDE_START;
        }
        if (iIntValue == 35) {
            return SIMULATE_GUIDE_STOP;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NaviStatus.class.getName());
    }

    public static NaviStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SIMULATE_GUIDE_STOP":
                return SIMULATE_GUIDE_STOP;
            case "CRUISE_START":
                return CRUISE_START;
            case "CRUISE_STOP":
                return CRUISE_STOP;
            case "SIMULATE_GUIDE_START":
                return SIMULATE_GUIDE_START;
            case "GUIDE_STOP":
                return GUIDE_STOP;
            case "GUIDE_START":
                return GUIDE_START;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NaviStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
