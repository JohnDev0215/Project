package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RoadType {
    UNKNOWN_ADMIN_ROAD_CLASS(0),
    MOST_IMPORTANT_ADMIN_ROAD_CLASS(1),
    SECOND_IMPORTANT_ADMIN_ROAD_CLASS(2),
    THIRD_IMPORTANT_ADMIN_ROAD_CLASS(3),
    FOURTH_IMPORTANT_ADMIN_ROAD_CLASS(4),
    FIFTH_IMPORTANT_ADMIN_ROAD_CLASS(5),
    SIXTH_IMPORTANT_ADMIN_ROAD_CLASS(6),
    LEAST_IMPORTANT_ADMIN_ROAD_CLASS(7);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RoadType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RoadType getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return UNKNOWN_ADMIN_ROAD_CLASS;
            case 1:
                return MOST_IMPORTANT_ADMIN_ROAD_CLASS;
            case 2:
                return SECOND_IMPORTANT_ADMIN_ROAD_CLASS;
            case 3:
                return THIRD_IMPORTANT_ADMIN_ROAD_CLASS;
            case 4:
                return FOURTH_IMPORTANT_ADMIN_ROAD_CLASS;
            case 5:
                return FIFTH_IMPORTANT_ADMIN_ROAD_CLASS;
            case 6:
                return SIXTH_IMPORTANT_ADMIN_ROAD_CLASS;
            case 7:
                return LEAST_IMPORTANT_ADMIN_ROAD_CLASS;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RoadType.class.getName());
        }
    }

    public static RoadType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SIXTH_IMPORTANT_ADMIN_ROAD_CLASS":
                return SIXTH_IMPORTANT_ADMIN_ROAD_CLASS;
            case "LEAST_IMPORTANT_ADMIN_ROAD_CLASS":
                return LEAST_IMPORTANT_ADMIN_ROAD_CLASS;
            case "FIFTH_IMPORTANT_ADMIN_ROAD_CLASS":
                return FIFTH_IMPORTANT_ADMIN_ROAD_CLASS;
            case "SECOND_IMPORTANT_ADMIN_ROAD_CLASS":
                return SECOND_IMPORTANT_ADMIN_ROAD_CLASS;
            case "FOURTH_IMPORTANT_ADMIN_ROAD_CLASS":
                return FOURTH_IMPORTANT_ADMIN_ROAD_CLASS;
            case "MOST_IMPORTANT_ADMIN_ROAD_CLASS":
                return MOST_IMPORTANT_ADMIN_ROAD_CLASS;
            case "THIRD_IMPORTANT_ADMIN_ROAD_CLASS":
                return THIRD_IMPORTANT_ADMIN_ROAD_CLASS;
            case "UNKNOWN_ADMIN_ROAD_CLASS":
                return UNKNOWN_ADMIN_ROAD_CLASS;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RoadType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
