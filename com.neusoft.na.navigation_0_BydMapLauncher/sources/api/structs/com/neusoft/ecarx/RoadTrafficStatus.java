package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RoadTrafficStatus {
    NO_TMC_DATA(-1),
    STATE_UNKNOWN(0),
    STATE_NO_BLOCK(1),
    STATE_SLOW(2),
    STATE_CONGESTION(3),
    STATE_SERIOUS_CONGESTION(4),
    SECTION_PASSED(10);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RoadTrafficStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RoadTrafficStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return NO_TMC_DATA;
        }
        if (iIntValue == 0) {
            return STATE_UNKNOWN;
        }
        if (iIntValue == 1) {
            return STATE_NO_BLOCK;
        }
        if (iIntValue == 2) {
            return STATE_SLOW;
        }
        if (iIntValue == 3) {
            return STATE_CONGESTION;
        }
        if (iIntValue == 4) {
            return STATE_SERIOUS_CONGESTION;
        }
        if (iIntValue == 10) {
            return SECTION_PASSED;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RoadTrafficStatus.class.getName());
    }

    public static RoadTrafficStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "STATE_SERIOUS_CONGESTION":
                return STATE_SERIOUS_CONGESTION;
            case "SECTION_PASSED":
                return SECTION_PASSED;
            case "STATE_NO_BLOCK":
                return STATE_NO_BLOCK;
            case "STATE_CONGESTION":
                return STATE_CONGESTION;
            case "STATE_SLOW":
                return STATE_SLOW;
            case "NO_TMC_DATA":
                return NO_TMC_DATA;
            case "STATE_UNKNOWN":
                return STATE_UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RoadTrafficStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
