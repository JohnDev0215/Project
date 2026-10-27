package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RoutePreference {
    POI_NAVI_TYPE_DEFAULT(0),
    POI_NAVI_TYPE_FAST_SPEED(1),
    POI_NAVI_TYPE_LESS_COST(2),
    POI_NAVI_TYPE_SHORT_DISTANCE(3),
    POI_NAVI_TYPE_NO_HIGH_SPEED(4),
    POI_NAVI_TYPE_AVOID_CONGESTION(5),
    POI_NAVI_TYPE_NO_HIGH_SPEED_AND_LESS_COST(6),
    POI_NAVI_TYPE_NO_HIGH_SPEED_AND_AVOID_CONGESTION(7),
    POI_NAVI_TYPE_LESS_COST_AND_AVOID_CONGESTION(8),
    POI_NAVI_TYPE_NO_HIGH_SPEED_AND_AVOID_CONGESTION_AND_LESS_COST(9),
    POI_NAVI_TYPE_HIGH_SPEED(10),
    POI_NAVI_TYPE_HIGH_SPEED_AVOID_CONGESTION(11),
    RECOMMENDATION(12),
    TIME_PRIORITY(13),
    DISTANCE_PRIORITY(14),
    AVOID_CONGESTION(15),
    HIGHWAY_PRIORITY(16),
    AVOID_HIGHWAY(17),
    ECONOMIC(18);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RoutePreference(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RoutePreference getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return POI_NAVI_TYPE_DEFAULT;
            case 1:
                return POI_NAVI_TYPE_FAST_SPEED;
            case 2:
                return POI_NAVI_TYPE_LESS_COST;
            case 3:
                return POI_NAVI_TYPE_SHORT_DISTANCE;
            case 4:
                return POI_NAVI_TYPE_NO_HIGH_SPEED;
            case 5:
                return POI_NAVI_TYPE_AVOID_CONGESTION;
            case 6:
                return POI_NAVI_TYPE_NO_HIGH_SPEED_AND_LESS_COST;
            case 7:
                return POI_NAVI_TYPE_NO_HIGH_SPEED_AND_AVOID_CONGESTION;
            case 8:
                return POI_NAVI_TYPE_LESS_COST_AND_AVOID_CONGESTION;
            case 9:
                return POI_NAVI_TYPE_NO_HIGH_SPEED_AND_AVOID_CONGESTION_AND_LESS_COST;
            case 10:
                return POI_NAVI_TYPE_HIGH_SPEED;
            case 11:
                return POI_NAVI_TYPE_HIGH_SPEED_AVOID_CONGESTION;
            case 12:
                return RECOMMENDATION;
            case 13:
                return TIME_PRIORITY;
            case 14:
                return DISTANCE_PRIORITY;
            case 15:
                return AVOID_CONGESTION;
            case 16:
                return HIGHWAY_PRIORITY;
            case 17:
                return AVOID_HIGHWAY;
            case 18:
                return ECONOMIC;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RoutePreference.class.getName());
        }
    }

    public static RoutePreference valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "POI_NAVI_TYPE_LESS_COST":
                return POI_NAVI_TYPE_LESS_COST;
            case "POI_NAVI_TYPE_AVOID_CONGESTION":
                return POI_NAVI_TYPE_AVOID_CONGESTION;
            case "POI_NAVI_TYPE_DEFAULT":
                return POI_NAVI_TYPE_DEFAULT;
            case "DISTANCE_PRIORITY":
                return DISTANCE_PRIORITY;
            case "POI_NAVI_TYPE_NO_HIGH_SPEED":
                return POI_NAVI_TYPE_NO_HIGH_SPEED;
            case "POI_NAVI_TYPE_SHORT_DISTANCE":
                return POI_NAVI_TYPE_SHORT_DISTANCE;
            case "POI_NAVI_TYPE_NO_HIGH_SPEED_AND_AVOID_CONGESTION":
                return POI_NAVI_TYPE_NO_HIGH_SPEED_AND_AVOID_CONGESTION;
            case "AVOID_CONGESTION":
                return AVOID_CONGESTION;
            case "TIME_PRIORITY":
                return TIME_PRIORITY;
            case "AVOID_HIGHWAY":
                return AVOID_HIGHWAY;
            case "HIGHWAY_PRIORITY":
                return HIGHWAY_PRIORITY;
            case "POI_NAVI_TYPE_FAST_SPEED":
                return POI_NAVI_TYPE_FAST_SPEED;
            case "POI_NAVI_TYPE_NO_HIGH_SPEED_AND_LESS_COST":
                return POI_NAVI_TYPE_NO_HIGH_SPEED_AND_LESS_COST;
            case "RECOMMENDATION":
                return RECOMMENDATION;
            case "POI_NAVI_TYPE_LESS_COST_AND_AVOID_CONGESTION":
                return POI_NAVI_TYPE_LESS_COST_AND_AVOID_CONGESTION;
            case "POI_NAVI_TYPE_HIGH_SPEED_AVOID_CONGESTION":
                return POI_NAVI_TYPE_HIGH_SPEED_AVOID_CONGESTION;
            case "POI_NAVI_TYPE_NO_HIGH_SPEED_AND_AVOID_CONGESTION_AND_LESS_COST":
                return POI_NAVI_TYPE_NO_HIGH_SPEED_AND_AVOID_CONGESTION_AND_LESS_COST;
            case "ECONOMIC":
                return ECONOMIC;
            case "POI_NAVI_TYPE_HIGH_SPEED":
                return POI_NAVI_TYPE_HIGH_SPEED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RoutePreference.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
