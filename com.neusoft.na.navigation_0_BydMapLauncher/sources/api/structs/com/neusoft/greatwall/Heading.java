package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum Heading {
    NORTH(0),
    NORTH_EAST(1),
    EAST(2),
    SOUTH_EAST(3),
    SOUTH(4),
    SOUTH_WEST(5),
    WEST(6),
    NORTH_WEST(7);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    Heading(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static Heading getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return NORTH;
            case 1:
                return NORTH_EAST;
            case 2:
                return EAST;
            case 3:
                return SOUTH_EAST;
            case 4:
                return SOUTH;
            case 5:
                return SOUTH_WEST;
            case 6:
                return WEST;
            case 7:
                return NORTH_WEST;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + Heading.class.getName());
        }
    }

    public static Heading valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "EAST":
                return EAST;
            case "WEST":
                return WEST;
            case "NORTH":
                return NORTH;
            case "SOUTH":
                return SOUTH;
            case "SOUTH_EAST":
                return SOUTH_EAST;
            case "SOUTH_WEST":
                return SOUTH_WEST;
            case "NORTH_EAST":
                return NORTH_EAST;
            case "NORTH_WEST":
                return NORTH_WEST;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + Heading.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
