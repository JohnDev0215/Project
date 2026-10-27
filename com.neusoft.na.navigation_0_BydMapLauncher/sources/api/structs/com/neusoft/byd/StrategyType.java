package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum StrategyType {
    INVALID(0),
    TIME_PRIORITY(1),
    AVOID_HIGHWAY(2),
    AVOID_CONGESTION(3),
    RECOMMENDATION(4),
    HIGHWAY_PRIORITY(5),
    DISTANCE_PRIORITY(6);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    StrategyType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static StrategyType getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return INVALID;
            case 1:
                return TIME_PRIORITY;
            case 2:
                return AVOID_HIGHWAY;
            case 3:
                return AVOID_CONGESTION;
            case 4:
                return RECOMMENDATION;
            case 5:
                return HIGHWAY_PRIORITY;
            case 6:
                return DISTANCE_PRIORITY;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + StrategyType.class.getName());
        }
    }

    public static StrategyType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "INVALID":
                return INVALID;
            case "DISTANCE_PRIORITY":
                return DISTANCE_PRIORITY;
            case "AVOID_CONGESTION":
                return AVOID_CONGESTION;
            case "TIME_PRIORITY":
                return TIME_PRIORITY;
            case "AVOID_HIGHWAY":
                return AVOID_HIGHWAY;
            case "HIGHWAY_PRIORITY":
                return HIGHWAY_PRIORITY;
            case "RECOMMENDATION":
                return RECOMMENDATION;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + StrategyType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
