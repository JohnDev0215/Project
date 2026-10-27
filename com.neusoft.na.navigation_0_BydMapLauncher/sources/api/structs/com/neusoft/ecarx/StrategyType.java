package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum StrategyType {
    ACCORDING_PREFERENCE_SET_INSIDE_MAP(-1),
    AVOID_CHARGES(1),
    AVOID_HIGH_SPEED(2),
    AVOID_CONGESTION(3),
    HIGH_SPEED_FIRST(4),
    INTELLIGENT_RECOMMENDATION(5),
    TIME_FIRST(6),
    DISTANCE_FIRST(7);

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
            case -1:
                return ACCORDING_PREFERENCE_SET_INSIDE_MAP;
            case 0:
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + StrategyType.class.getName());
            case 1:
                return AVOID_CHARGES;
            case 2:
                return AVOID_HIGH_SPEED;
            case 3:
                return AVOID_CONGESTION;
            case 4:
                return HIGH_SPEED_FIRST;
            case 5:
                return INTELLIGENT_RECOMMENDATION;
            case 6:
                return TIME_FIRST;
            case 7:
                return DISTANCE_FIRST;
        }
    }

    public static StrategyType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "TIME_FIRST":
                return TIME_FIRST;
            case "DISTANCE_FIRST":
                return DISTANCE_FIRST;
            case "AVOID_HIGH_SPEED":
                return AVOID_HIGH_SPEED;
            case "HIGH_SPEED_FIRST":
                return HIGH_SPEED_FIRST;
            case "AVOID_CONGESTION":
                return AVOID_CONGESTION;
            case "AVOID_CHARGES":
                return AVOID_CHARGES;
            case "ACCORDING_PREFERENCE_SET_INSIDE_MAP":
                return ACCORDING_PREFERENCE_SET_INSIDE_MAP;
            case "INTELLIGENT_RECOMMENDATION":
                return INTELLIGENT_RECOMMENDATION;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + StrategyType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
